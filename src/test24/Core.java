package test24;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Core {
	public static void main(String[] args) {
		// 创建2个buffers。分别给producer和consumer使用.
		List<String> buffer1 = new ArrayList<String>();
		List<String> buffer2 = new ArrayList<String>();

		// 创建Exchanger对象，用来同步producer和consumer。
		Exchanger<List<String>> exchanger = new Exchanger<List<String>>();

		// 创建Producer对象和Consumer对象。
		Producer producer = new Producer(buffer1, exchanger);
		Consumer consumer = new Consumer(buffer2, exchanger);

		// 创建线程来执行producer和consumer并开始线程。
		Thread threadProducer = new Thread(producer);
		Thread threadConsumer = new Thread(consumer);
		threadProducer.start();
		threadConsumer.start();
	}
}
