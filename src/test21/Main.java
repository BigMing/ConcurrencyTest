package test21;

import java.util.concurrent.CyclicBarrier;

public class Main {
	public static void main(String[] args) {
		// 声明并初始5个常熟来储存应用的参数。
		final int ROWS = 10000;
		final int NUMBERS = 1000;
		final int SEARCH = 5;
		final int PARTICIPANTS = 5;
		final int LINES_PARTICIPANT = 2000;

		// Create a MatrixMock 对象，名为 mock. 它将有 10,000
		// 行，每行1000个元素。现在，你要查找的数字是5。
		MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);

		// 创建 Results 对象，名为 results。它将有 10,000 元素。
		Results results = new Results(ROWS);

		// 创建 Grouper 对象，名为 grouper。
		Grouper grouper = new Grouper(results);

		// 创建 CyclicBarrier 对象，名为 barrier。此对象会等待5个线程。当此线程结束后，它会执行前面创建的 Grouper
		// 对象。
		CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);

		// 创建5个 Searcher 对象，5个执行他们的线程，并开始这5个线程。
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
