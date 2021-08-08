package est.main;

import est.tcp.TCPEchoClient;

public class Main {
	public static void main(String[] args) {
		new TCPEchoClient().clientTCP("localhost", 9007);
	}
}
