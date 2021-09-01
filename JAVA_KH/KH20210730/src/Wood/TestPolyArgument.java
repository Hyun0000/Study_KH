package Wood;

public class TestPolyArgument {
	public static void main(String[] args) {
		Buyer b = new Buyer();
		b.buy(new Chair());
		b.buy(new Desk());
	}
}