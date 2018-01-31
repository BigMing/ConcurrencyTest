package test24;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Core {
	public static void main(String[] args) {
		// ����2��buffers���ֱ��producer��consumerʹ��.
		List<String> buffer1 = new ArrayList<String>();
		List<String> buffer2 = new ArrayList<String>();

		// ����Exchanger��������ͬ��producer��consumer��
		Exchanger<List<String>> exchanger = new Exchanger<List<String>>();

		// ����Producer�����Consumer����
		Producer producer = new Producer(buffer1, exchanger);
		Consumer consumer = new Consumer(buffer2, exchanger);

		// �����߳���ִ��producer��consumer����ʼ�̡߳�
		Thread threadProducer = new Thread(producer);
		Thread threadConsumer = new Thread(consumer);
		threadProducer.start();
		threadConsumer.start();
	}
}
