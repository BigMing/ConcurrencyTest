package test19;

public class Main {
	public static void main(String[] args) {
		// ����PrintQueue������ΪprintQueue��
		PrintQueue printQueue = new PrintQueue();

		// ����10��threads��ÿ���̻߳�ִ��һ�������ĵ���print queue��Job����
		Thread thread[] = new Thread[10];
		
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new Job(printQueue), "Thread" + i);
		}

		// ��󣬿�ʼ��10���߳��ǡ�
		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}
	}
}
