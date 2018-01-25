package test19;

public class Main {
	public static void main(String[] args) {
		// 创建PrintQueue对象名为printQueue。
		PrintQueue printQueue = new PrintQueue();

		// 创建10个threads。每个线程会执行一个发送文档到print queue的Job对象。
		Thread thread[] = new Thread[10];
		
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new Job(printQueue), "Thread" + i);
		}

		// 最后，开始这10个线程们。
		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}
	}
}
