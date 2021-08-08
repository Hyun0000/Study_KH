package com.java.socket.lesson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket server;
	int port;
	Socket serverSocket;
	
	public void makeServer(int port) throws IOException {
		server = new ServerSocket(port);
		System.out.println("서버가 만들어졌다...연결 대기중...");
		
		serverSocket = server.accept();
		System.out.println("소켓을 받음");
	}
	
	
	
	public static void main(String[] args) {
		try {
			new Server().makeServer(3000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
