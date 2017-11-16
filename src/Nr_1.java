import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Nr_1 {

	FastScanner fs = new FastScanner("input1.txt");

	HashSet<String> numbers;
	
	public Nr_1() throws IOException, InterruptedException {
		int numberCount = fs.nextInt();
		
		numbers = new LinkedHashSet<String>();
		
		a: for(int i = 0; i < numberCount; i++) {
			String number = fs.next();
			char[] charArray = number.toCharArray();
			
			if(charArray.length == 8 && charArray[0] != '0') {
				for(int j = 0; j < 8; j++) {
					switch(charArray[j]) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9': break;
						default: continue a;
					}
				}
				numbers.add(number);
			}
		}
		//System.out.println(numbers.size());
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		for(int i = 0; i < 100; i++) {
			long t0 = System.nanoTime();
			new Nr_1();
			long t1 = System.nanoTime();

			System.out.format("%f\n", (float) (t1 - t0) / 1000000000);
		}
	}
}

class FastScanner {
    BufferedReader reader;
    StringTokenizer tokenizer;

    //Конструктор для чтения из файла
    public FastScanner(String fileName) throws IOException {
    	reader = new BufferedReader(new FileReader(fileName));
    }
    
    //Конструктор для чтения с клавиатуры
    public FastScanner() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public String next() throws IOException {
    	return reader.readLine();
    }

    public String next(String delim) throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            String line = reader.readLine();
            if (line == null) {
                throw new EOFException();
            }
            tokenizer = new StringTokenizer(line, delim);
        }
        return tokenizer.nextToken();
    }
    
    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
    
    public float nextFloat() throws IOException {
        return Float.parseFloat(next());
    }
}