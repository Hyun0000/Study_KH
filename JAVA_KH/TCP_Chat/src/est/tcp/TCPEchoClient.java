package est.tcp;

import java.io.*;
import java.net.*;

public class TCPEchoClient {
	public void clientTCP(String ip, int port) {
		Socket sock = null;
		
		InputStream in = null;
		OutputStream out = null;
		BufferedReader br = null;
		PrintWriter wr = null;
		BufferedReader stdin = null;
		
		try {
			// 서버로 접속
			sock = new Socket(ip, port);
			System.out.println("접속 성공!!");
			
			// 서버와의 통신을 위한 stream, 표준입력 변수
			 in = sock.getInputStream();
			 out = sock.getOutputStream();
			 br = new BufferedReader(new InputStreamReader(in));
			 wr = new PrintWriter(new OutputStreamWriter(out));
			 stdin = new BufferedReader(new InputStreamReader(System.in));
			 
			 String msg = null;
			 String echo = null;
			 
			 // 서버와 통신
			 do {
				System.out.print("\tInset Msg> ");
				msg = stdin.readLine();
				
				 // exit 입력으로 접속 끊기
				 if (msg.equals("exit")) {
					sock.close();
					break;
				}
				 
				 // 에코 메시지 출력
				 wr.println(msg);
				 wr.flush();
				 echo = br.readLine();
				 System.out.println("\tSERVER>" + echo);
			} while (msg != null);
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				 stdin.close();
				 br.close();
				 wr.close();
				 in.close();
				 out.close();
				 sock.close();
				 System.out.println("종료.");
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}