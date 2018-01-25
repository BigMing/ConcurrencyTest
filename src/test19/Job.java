package test19;

public class Job implements Runnable {
	private PrintQueue printQueue;

	public Job(PrintQueue printQueue) {
		this.printQueue = printQueue;
	}

	@Override
	public void run() {
		// ���ȣ� �˷���д��Ϣ���ٿ�̨���������Ѿ���ʼִ���ˡ�
		System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());

		// Ȼ�󣬵���PrintQueue �����printJob()������
		printQueue.printJob(new Object());

		// ��� �˷���д��Ϣ���ٿ�̨�������Ѿ����������ˡ�
		System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
	}

}
