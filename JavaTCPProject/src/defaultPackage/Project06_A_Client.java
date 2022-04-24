package defaultPackage;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Project06_A_Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket("192.168.35.252", 9999);
			System.out.println("Connection Success! ");
			Scanner scanner = new Scanner(System.in);
			String message = scanner.nextLine();
			OutputStream out = socket.getOutputStream(); //소켓 통해서 입력한 정보를 서버로 보냄
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeUTF(message); //dos를 통해서 키보드로 입력한 메세지를 서버로 보내준다
			/////////////////////////////////////////////////////////
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			System.out.println("Receive:"+dis.readUTF()); //서버로부터 날라온 정보를 읽어줌
			dis.close();
			dos.close();
			socket.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
