package test20;

import java.util.concurrent.TimeUnit;

public class Participant implements Runnable {
	// 声明一个私有 Videoconference 属性名为 conference.
	private Videoconference conference;
	// 声明一个私有 String 属性名为 name。
	private String name;

	// 实现类的构造函数，初始化那2个属性。
	public Participant(Videoconference conference, String name) {
		this.conference = conference;
		this.name = name;
	}

	// 实现参与者的run() 方法。
	@Override
	public void run() {
		// 首先，让线程随机休眠一段时间。
		long duration = (long) (Math.random() * 10);
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 然后，使用Videoconference 对象的arrive() 方法来表明参与者的到达。
		conference.arrive(name);
	}
}
