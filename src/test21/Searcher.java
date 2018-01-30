package test21;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Searcher implements Runnable {
	// ����2��˽��int������Ϊ firstRow �� lastRow����2������������ȷ����Ҫ�õ��Ӽ����С�
	private int firstRow;
	private int lastRow;

	// ����һ��˽�� MatrixMock ���ԣ���Ϊ mock��
	private MatrixMock mock;

	// ����һ��˽�� Results���ԣ���Ϊ results��
	private Results results;

	// ����һ��˽�� int ������Ϊ number��������������Ҫ���ҵ����֡�
	private int number;

	// ����һ�� CyclicBarrier ������Ϊ barrier��
	private final CyclicBarrier barrier;

	// ʵ����Ĺ��캯��������ʼ��֮ǰ������ȫ�����ԡ�
	public Searcher(int firstRow, int lastRow, MatrixMock mock, Results results, int number, CyclicBarrier barrier) {
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.mock = mock;
		this.results = results;
		this.number = number;
		this.barrier = barrier;
	}

	// ʵ�� run() �����������������֡���ʹ���ڲ���������Ϊcounter����������������ÿ�г��ֵĴ�����
	@Override
	public void run() {
		int counter;
		// �ڲٿ�̨��ӡһ����Ϣ���������䵽���������С�
		System.out.printf("%s: Processing lines from %d to %d.\n", Thread.currentThread().getName(), firstRow, lastRow);
		// ������������̵߳�ȫ���С�����ÿ�У���¼���ڲ��ҵ����ֳ��ֵĴ�������������ڵ� Results �����б�������ݡ�
		for (int i = firstRow; i < lastRow; i++) {
			int row[] = mock.getRow(i);
			counter = 0;
			for (int j = 0; j < row.length; j++) {
				if (row[j] == number) {
					counter++;
				}
			}

			results.setData(i, counter);
		}

		// ��ӡ��Ϣ���ٿ�̨�����˶����Ѿ�����������
		System.out.printf("%s: Lines processed.\n", Thread.currentThread().getName());

		// ���� CyclicBarrier ����� await() ���� �����ڿ����׳����쳣��Ҫ���봦��
		// InterruptedException and BrokenBarrierException �쳣�ı�����롣
		try {
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
