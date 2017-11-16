import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class Nr_2 {

	FastScanner fs = new FastScanner("input2.txt");
	
	public Nr_2() throws IOException {
		int runnersCount = fs.nextInt();
		
		List<Runner> runners = new ArrayList<Runner>();
		
		for(int i = 1; i <= runnersCount; i++) {
			runners.add(new Runner(i, Integer.parseInt(fs.next(" ")), Integer.parseInt(fs.next(" "))));
		}
		
		int maxRunningTime = 0;
		for(Runner runner : runners) {
			if(runner.getRunningTime() > maxRunningTime) {
				maxRunningTime = runner.getRunningTime();
			}
		}
		
		int relaxSum = 0;
		for(Runner runner : runners) {
			relaxSum += runner.getRelaxTime(); 
		}
		
		boolean firstAlg = true;
		if(maxRunningTime > relaxSum) {
			firstAlg = false;
		}
		
		MyComparator1 myComparator1 = null;
		MyComparator2 myComparator2 = null;
		if(firstAlg) {
			myComparator1 = new MyComparator1();
			runners.sort(myComparator1);
		}
		else {
			myComparator2 = new MyComparator2();
			runners.sort(myComparator2);
		}
		
		Runner currentRunner = runners.get(0);
		
		List<Runner> relaxingRunners = new ArrayList<Runner>(runnersCount);
		
		Runner patternRunner = currentRunner;
		int[] pattern = null;
		int patternRunnerRun = 0;
		
		boolean infiniteCycle = false;
		
		int i = 1;
		for(;;) {
			
			if(currentRunner == patternRunner) {
				patternRunnerRun++;
				if(patternRunnerRun == 2) {
					int j = 0;
					pattern = new int[runners.size()];
					for(Runner runner : runners) {
						pattern[j++] = runner.getCurrentRelaxTime();
					}
				}
				
				if(patternRunnerRun > 2) {
					int j = 0;
					for(Runner runner : runners) {
						if(pattern[j++] == runner.getCurrentRelaxTime()) {
							infiniteCycle = true;
						}
					}
				}
			}
			
			if(infiniteCycle) {
				i = 0;
				break;
			}
			
			System.out.printf("%d) Runner %d (%d %d)\n", i, currentRunner.getId(), currentRunner.getRunningTime(), currentRunner.getRelaxTime());
			
			Iterator<Runner> iterator = relaxingRunners.iterator();
			for(; iterator.hasNext();) {
				Runner runner = iterator.next();
				System.out.printf("Relaxing: Runner %d - %d / %d\n", runner.getId(), runner.getCurrentRelaxTime(), runner.getRelaxTime());
				runner.incrementCurrentRelaxingTime(currentRunner.getRunningTime());
				if(runner.mustBeFree()) {
					runner.setFree(true);
					iterator.remove();
					runners.add(runner);
				}
			}
			
			i += currentRunner.getRunningTime();
			runners.remove(currentRunner);
			relaxingRunners.add(currentRunner);
			currentRunner.setFree(false);
			
			if(runners.isEmpty()) {
				break;
			}
			else {
				if(firstAlg) {
					runners.sort(myComparator1);
				}
				else {
					runners.sort(myComparator2);
				}
				currentRunner = runners.get(0);
			}
			
		}
		System.out.println(i - 1);
	}
	
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < 1; i++) {
			long t0 = System.nanoTime();
			new Nr_2();
			long t1 = System.nanoTime();
			
			System.out.format("%f\n", (float) (t1 - t0) / 1000000000);
		}
	}
}

class MyComparator1 implements Comparator<Runner> {
	@Override
	public int compare(Runner a, Runner b) {
		int x = a.getRelaxTime();
		int y = b.getRelaxTime();
		if(x == y) {
			return b.getRunningTime() - a.getRunningTime();
		}
		else {
		 return x - y;
		}
	}
}


class MyComparator2 implements Comparator<Runner> {
	@Override
	public int compare(Runner a, Runner b) {
		
		if (a.getRunningTime() >= a.getRelaxTime() && b.getRunningTime() >= b.getRelaxTime()) {
			int x = Math.abs(a.getRunningTime() - a.getRelaxTime());
			int y = Math.abs(b.getRunningTime() - b.getRelaxTime());
			return y - x;
		}
		
		if (a.getRunningTime() >= a.getRelaxTime() && b.getRunningTime() < b.getRelaxTime()) {
			return 1;
		}
		
		if (a.getRunningTime() < a.getRelaxTime() && b.getRunningTime() >= b.getRelaxTime()) {
			return -1;
		}
		
		if (a.getRunningTime() < a.getRelaxTime() && b.getRunningTime() < b.getRelaxTime()) {
			int x = Math.abs(a.getRunningTime() - a.getRelaxTime());
			int y = Math.abs(b.getRunningTime() - b.getRelaxTime());
			return x - y;
		}
		
		return 0;
	}
}

class Runner {
	private int id;
	private int runningTime;
	private int relaxTime;
	private int currentRelaxTime;
	private boolean isFree;
	
	
	public Runner(int id, int runningTime, int relaxTime) {
		this.id = id;
		this.runningTime = runningTime;
		this.relaxTime = relaxTime;
		currentRelaxTime = 1;
		isFree = true;
	}

	public boolean isFree() {
		return isFree;
	}
	
	public int getId() {
		return id;
	}
	
	public void incrementCurrentRelaxingTime(int value) {
		currentRelaxTime += value;
	}
	
	public boolean mustBeFree() {
		if(currentRelaxTime > relaxTime) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setFree(boolean isFree) {
		this.isFree = isFree;
		if(isFree) {
			currentRelaxTime = 1;
		}
	}
	
	public void setCurrentRelaxTime(int value) {
		currentRelaxTime = value;
	}
	
	public void relax(int value) {
		currentRelaxTime += value;
	}

	public int getCurrentRelaxTime() {
		return currentRelaxTime;
	}
	
	public int getRunningTime() {
		return runningTime;
	}

	public int getRelaxTime() {
		return relaxTime;
	}
}