package test19;

public class Job implements Runnable {
	private PrintQueue printQueue;

	public Job(PrintQueue printQueue) {
		this.printQueue = printQueue;
	}

	@Override
	public void run() {
		// 首先， 此方法写信息到操控台表明任务已经开始执行了。
		System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());

		// 然后，调用PrintQueue 对象的printJob()方法。
		printQueue.printJob(new Object());

		// 最后， 此方法写信息到操控台表明它已经结束运行了。
		System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
	}

}
