package test24;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Producer implements Runnable {
	// 声明 List<String>对象，名为 buffer。这是等等要被相互交换的数据类型。
	private List<String> buffer;

	// 声明 Exchanger<List<String>>; 对象，名为exchanger。这个 exchanger
	// 对象是用来同步producer和consumer的。
	private final Exchanger<List<String>> exchanger;

	// 实现类的构造函数，初始化这2个属性。
	public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	// 实现 run() 方法. 在方法内，实现10次交换。
	@Override
	public void run() {
		int cycle = 1;
		for (int i = 0; i < 10; i++) {
			System.out.printf("Producer: Cycle %d\n", cycle);
			// 在每次循环中，加10个字符串到buffer。
			for (int j = 0; j < 10; j++) {
				String message = "Event " + ((i * 10) + j);
				System.out.printf("Producer: %s\n", message);
				buffer.add(message);
			}
			// 调用 exchange() 方法来与consumer交换数据。此方法可能会抛出InterruptedException
			// 异常, 加上处理代码。
			try {
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Producer: " + buffer.size());
			cycle++;
		}
	}
}
