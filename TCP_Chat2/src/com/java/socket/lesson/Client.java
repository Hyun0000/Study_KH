package com.java.socket.lesson;

import java.io.IOException;
import java.net.Socket;

public class Client {
	Socket clientSocket;
	String host;
	int port;
	
	// 서버로 소켓을 던져주는 메소드
	public void throwSocket(String host, int port) throws IOException {
		clientSocket = new Socket(host, port);
		System.out.println("소켓을 서버로 던진다.");
	}
	
	
	
	public static void main(String[] args) {
		try {
			new Client().throwSocket("localhost", 3000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
