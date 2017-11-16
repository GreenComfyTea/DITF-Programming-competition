import java.io.IOException;
import java.math.BigInteger;

public class Nr_5 {

	FastScanner fs = new FastScanner("input5.txt");
	
	public Nr_5() throws IOException {
		int numberCount = fs.nextInt();
		
		BigInteger bigInteger = BigInteger.valueOf(0);

		for(int i = 0; i < numberCount; i++) {
			bigInteger = bigInteger.add(new BigInteger(fs.next()));
		}
		System.out.println(bigInteger.toString());
	}
	
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < 1; i++) {
			long t0 = System.nanoTime();
			new Nr_5();
			long t1 = System.nanoTime();
			
			System.out.format("%f\n", (float) (t1 - t0) / 1000000000);
		}
	}
}