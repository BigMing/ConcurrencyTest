package test20;

import java.util.concurrent.TimeUnit;

public class Participant implements Runnable {
	// ����һ��˽�� Videoconference ������Ϊ conference.
	private Videoconference conference;
	// ����һ��˽�� String ������Ϊ name��
	private String name;

	// ʵ����Ĺ��캯������ʼ����2�����ԡ�
	public Participant(Videoconference conference, String name) {
		this.conference = conference;
		this.name = name;
	}

	// ʵ�ֲ����ߵ�run() ������
	@Override
	public void run() {
		// ���ȣ����߳��������һ��ʱ�䡣
		long duration = (long) (Math.random() * 10);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Ȼ��ʹ��Videoconference �����arrive() ���������������ߵĵ��
		conference.arrive(name);
	}
}
