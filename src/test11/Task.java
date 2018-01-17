package test11;

import java.util.Random;

public class Task implements Runnable {

	@Override
	public void run() {
		int result;
		Random random = new Random(Thread.currentThread().getId());
		while (true) {
			result = 1000 / (int) (random.nextDouble() * 1000);
			// ���ǽ����׳�һ�� AritmethicException �쳣������������Ҫ��1000����һ��������֣�ֱ��������ɵ���Ϊ0��ʱ���쳣�ͻᱻ�׳���
			System.out.printf("%s : %d\n", Thread.currentThread().getId(), result);
			
			if (Thread.currentThread().isInterrupted()) {
				System.out.printf("%d : Interrupted\n", Thread.currentThread().getId());
				return;
			}
		}
	}

}
