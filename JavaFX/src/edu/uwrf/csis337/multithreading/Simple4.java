package edu.uwrf.csis337.multithreading;

public class Simple4 implements Runnable{
	private int id;
	public Simple4(int i){ 
		id = i;
	}
	private void process( int iterations){
		for (int i=1; i<=iterations; i++)
			System.out.printf("    process %d at iteration %04d%n", id, i);
	}
	public void run(){
		process( 1000 );
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread th0 = new Thread( new Simple4(0) );
		Thread th1 = new Thread( new Simple4(1) );
		th0.start();
		th1.start();
	}

}
