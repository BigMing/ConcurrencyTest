package test21;

import java.util.concurrent.CyclicBarrier;

public class Main {
	public static void main(String[] args) {
		// ��������ʼ5������������Ӧ�õĲ�����
		final int ROWS = 10000;
		final int NUMBERS = 1000;
		final int SEARCH = 5;
		final int PARTICIPANTS = 5;
		final int LINES_PARTICIPANT = 2000;

		// Create a MatrixMock ������Ϊ mock. ������ 10,000
		// �У�ÿ��1000��Ԫ�ء����ڣ���Ҫ���ҵ�������5��
		MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);

		// ���� Results ������Ϊ results�������� 10,000 Ԫ�ء�
		Results results = new Results(ROWS);

		// ���� Grouper ������Ϊ grouper��
		Grouper grouper = new Grouper(results);

		// ���� CyclicBarrier ������Ϊ barrier���˶����ȴ�5���̡߳������߳̽���������ִ��ǰ�洴���� Grouper
		// ����
		CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);

		// ����5�� Searcher ����5��ִ�����ǵ��̣߳�����ʼ��5���̡߳�
		Searcher searchers[] = new Searcher[PARTICIPANTS];
		for (int i = 0; i < PARTICIPANTS; i++) {
			searchers[i] = new Searcher(i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT, mock,
					results, 5, barrier);
			Thread thread = new Thread(searchers[i]);
			thread.start();
		}
		System.out.printf("Main: The main thread has finished.\n");
	}
}
