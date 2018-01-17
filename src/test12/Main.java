package test12;

public class Main {
	public static void main(String[] args) {
		MyThreadFactory threadFactory = new MyThreadFactory("MyThreadFactory");
		Task task = new Task();
		Thread thread;
		System.out.println("Starting the Threads");
		for (int i = 0; i < 10; i++) {
			thread = threadFactory.newThread(task);
			thread.start();
		}
		System.out.printf("Factory stats:\n");
		System.out.printf("%s\n", threadFactory.getStats());
	}
}
