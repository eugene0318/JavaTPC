package defaultPackage;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Project06A_Server {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(9999); //서버를 연다
			System.out.println("server ready...");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		while (true) {
			try {
				Socket socket = serverSocket.accept(); //blocking 
				System.out.println("client connect success!");
				InputStream in = socket.getInputStream(); //client로부터 읽어들이는 스트림 생성(in)
				DataInputStream dis = new DataInputStream(in);  //한글 깨짐 방지
				String message = dis.readUTF();
				OutputStream out = socket.getOutputStream(); //client에게 출력
				DataOutputStream dos =new DataOutputStream(out); //한글 깨짐 방지
				dos.writeUTF("[ECHP]"+message+"from Server!)");
				dos.close();
				dis.close();
				socket.close();
				System.out.println("client socket close...");
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}//while
	}//main

}//class
