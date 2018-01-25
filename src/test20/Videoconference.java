package test20;

import java.util.concurrent.CountDownLatch;

public class Videoconference implements Runnable {
	// ���� CountDownLatch ������Ϊ controller��
	private final CountDownLatch controller;

	// ʵ����Ĺ��캯������ʼ CountDownLatch ���ԡ�Videoconference ����ս�Ҫ�ȴ��Ĳ����ߵ���Ϊ������
	public Videoconference(int number) {
		controller = new CountDownLatch(number);
	}

	// ʵ�� arrive() ������ÿ���в����ߵ��ﶼ����ô˷�����������String���͵Ĳ�����Ϊ name��
	public void arrive(String name) {
		// ���ȣ������ĳĳ�����Ѿ����
		System.out.printf("%s has arrived.", name);
		// Ȼ�󣬵���CountDownLatch����� countDown() ������
		controller.countDown();
		// ���ʹ��CountDownLatch����� getCount() ���������һ�����ڻ�δȷ������Ĳ���������
		System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
	}

	// ʵ��video-conference ϵͳ��������������ÿ��Runnable�������е� run() ������
	@Override
	public void run() {
		// ���ȣ�ʹ�� getCount() ������������video conference�Ĳ���ֵ��������Ϣ��
		System.out.printf("VideoConference: Initialization: %d participants.\n", controller.getCount());
		// Ȼ�� ʹ�� await() �������ȴ�ȫ���Ĳ����ߡ����ڴ˷����׳� InterruptedException �쳣������Ҫ����������롣
		try {
			controller.await();
			// ��������Ϣ����ȫ���������Ѿ����
			System.out.printf("VideoConference: All the participants have come\n");
			System.out.printf("VideoConference: Let's start...\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
