import java.io.IOException;

public class Nr_4 {

	FastScanner fs = new FastScanner("input4.txt");
	
	public Nr_4() throws IOException {
		int sidesCount = fs.nextInt();
		float sideLength = fs.nextFloat();
		
		double figureArea = ((sidesCount * sideLength * sideLength) / (4 * Math.tan(Math.toRadians(180 / sidesCount))));
		double roundRadius = sideLength / (2 * Math.tan(Math.toRadians(360 / (2 * sidesCount))));
		double roundArea = Math.PI * roundRadius * roundRadius;
		double diff = figureArea - roundArea;
		//System.out.printf("%.3f\n", diff);
	}
	
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < 100; i++) {
			long t0 = System.nanoTime();
			new Nr_4();
			long t1 = System.nanoTime();
			
			System.out.format("%f\n", (float) (t1 - t0) / 1000000000);
		}
	}
}