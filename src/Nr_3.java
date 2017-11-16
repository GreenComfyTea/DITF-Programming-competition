import java.io.IOException;

public class Nr_3 {

	FastScanner fs = new FastScanner("input3.txt");
	
	public Nr_3() throws IOException {
		float currentStorey = fs.nextInt();
		int goalStorey = fs.nextInt();
		float ñoefficientOfRestitution = fs.nextFloat();
		
		int i = -1;
		for(; currentStorey >= goalStorey; i += 2) {
			currentStorey *= ñoefficientOfRestitution;
		}
		
		System.out.println(i);
	}
	
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < 1; i++) {
			long t0 = System.nanoTime();
			new Nr_3();
			long t1 = System.nanoTime();
			
			System.out.format("%f\n", (float) (t1 - t0) / 1000000000);
		}
	}

}
