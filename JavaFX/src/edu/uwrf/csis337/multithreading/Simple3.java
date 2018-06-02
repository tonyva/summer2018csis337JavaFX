package edu.uwrf.csis337.multithreading;

public class Simple3 extends java.lang.Thread{
	private int id;
	public Simple3(int i){
		id = i;
	}
	private void process( int iterations){
		for (int i=1; i<=iterations; i++) {
			try {
				sleep( 100 /* milliseconds */ );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("    process %d at iteration %04d%n", id, i);
		}
	}
	public void run(){
		process( 10 );
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread th0 = new Simple3( 0 );
		Thread th1 = new Simple3( 1 );		

		th0.start();
		th1.start();
	}

}
