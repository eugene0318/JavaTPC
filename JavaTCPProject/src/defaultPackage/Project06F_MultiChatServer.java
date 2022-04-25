package defaultPackage;

import java.io.*;
import java.net.*;
import java.util.*;

public class Project06F_MultiChatServer {
	
	HashMap clients;
	Project06F_MultiChatServer() {
		clients = new HashMap();
		Collections.synchronizedMap(clients);
	}
	public void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("start server...");
			while (true) {
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress()+":"+socket.getPort()+" connect!");
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	void sendToAll(String msg) { //브로드캐스팅
		Iterator iterator = clients.keySet().iterator();
		while (iterator.hasNext()) {
			try {
				DataOutputStream out = (DataOutputStream)clients.get(iterator.next());
				out.writeUTF(msg);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new Project06F_MultiChatServer().start();
	}
	class ServerReceiver extends Thread{
		Socket socket; DataOutputStream out; DataInputStream in;
		public ServerReceiver(Socket socket) {
			// TODO Auto-generated constructor stub
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		public void run() {
			String name = "";
			try {
				name = in.readUTF();
				if(clients.get(name) != null) {//같은 사용자 존재
					out.writeUTF("already exist name : "+ name);
					out.writeUTF("please reconnect by other name");
					System.out.println(socket.getInetAddress()+":"+socket.getPort()+"disconnect");
					in.close();
					out.close();
					socket.close();
					socket = null;
				}else {
					sendToAll(name+" join!");
					clients.put(name, out);
					while (in !=null) {
						sendToAll(in.readUTF());
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				if(socket !=null) {
					sendToAll(name + " exit!");
					clients.remove(name);
					System.out.println(socket.getInetAddress()+":"+socket.getPort()+" disconnect");
				}
			}
		}
	}
}
