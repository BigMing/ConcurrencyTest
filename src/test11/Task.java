package test11;

import java.util.Random;

public class Task implements Runnable {

	@Override
	public void run() {
		int result;
		Random random = new Random(Thread.currentThread().getId());
		while (true) {
			result = 1000 / (int) (random.nextDouble() * 1000);
			// 我们将会抛出一个 AritmethicException 异常。这样，我们要用1000除以一个随机数字，直到随机生成的数为0的时候，异常就会被抛出。
			System.out.printf("%s : %d\n", Thread.currentThread().getId(), result);
			
			if (Thread.currentThread().isInterrupted()) {
				System.out.printf("%d : Interrupted\n", Thread.currentThread().getId());
				return;
			}
		}
	}

}
