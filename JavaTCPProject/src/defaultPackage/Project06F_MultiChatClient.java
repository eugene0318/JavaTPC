package defaultPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Project06F_MultiChatClient {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("192.168.35.252", 9999);
			Scanner scanner = new Scanner(System.in);
			System.out.println("name :");
			String name = scanner.nextLine();
			Thread sender =new Thread(new ClientSender(socket, name));
			Thread receiver = new Thread(new ClientReceiver(socket));
			sender.start();
			receiver.start();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	static class ClientSender extends Thread{
		Socket socket; String name; DataOutputStream out;
		public ClientSender(Socket socket, String name) {
			// TODO Auto-generated constructor stub
			this.socket = socket;
			this.name = name;
			try {
				out = new DataOutputStream(socket.getOutputStream());
;			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
			public void run() {
				Scanner scanner = new Scanner(System.in);
				try {
					if(out != null) out.writeUTF(name);
					while (out !=null) {
						String message = scanner.nextLine();
						if(message.equals("quite")) break;
						out.writeUTF(message);
					}
					out.close();
					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			}
		}
	
	static class ClientReceiver extends Thread{
		Socket socket; DataInputStream in;
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			// TODO Auto-generated constructor stub
		}
		public void run() {
			while (in !=null) {
				try {
					System.out.println(in.readUTF());
				} catch (Exception e) {
					e.printStackTrace();
					break;
					// TODO: handle exception
				}
			}
			try {
				in.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

}
