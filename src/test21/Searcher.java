package test21;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Searcher implements Runnable {
	// 声明2个私有int属性名为 firstRow 和 lastRow。这2个属性是用来确定将要用的子集的行。
	private int firstRow;
	private int lastRow;

	// 声明一个私有 MatrixMock 属性，名为 mock。
	private MatrixMock mock;

	// 声明一个私有 Results属性，名为 results。
	private Results results;

	// 声明一个私有 int 属性名为 number，用来储存我们要查找的数字。
	private int number;

	// 声明一个 CyclicBarrier 对象，名为 barrier。
	private final CyclicBarrier barrier;

	// 实现类的构造函数，并初始化之前声明的全部属性。
	public Searcher(int firstRow, int lastRow, MatrixMock mock, Results results, int number, CyclicBarrier barrier) {
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.mock = mock;
		this.results = results;
		this.number = number;
		this.barrier = barrier;
	}

	// 实现 run() 方法，用来查找数字。它使用内部变量，名为counter，用来储存数字在每行出现的次数。
	@Override
	public void run() {
		int counter;
		// 在操控台打印一条信息表明被分配到这个对象的行。
		System.out.printf("%s: Processing lines from %d to %d.\n", Thread.currentThread().getName(), firstRow, lastRow);
		// 处理分配给这个线程的全部行。对于每行，记录正在查找的数字出现的次数，并在相对于的 Results 对象中保存此数据。
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

		// 打印信息到操控台表明此对象已经结束搜索。
		System.out.printf("%s: Lines processed.\n", Thread.currentThread().getName());

		// 调用 CyclicBarrier 对象的 await() 方法 ，由于可能抛出的异常，要加入处理
		// InterruptedException and BrokenBarrierException 异常的必需代码。
		try {
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
