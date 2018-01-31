package test25;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
	// ����һ������ΪDate����ΪinitDate�����ԣ����洢���񴴽����ڣ���һ������ΪString����Ϊname�����ԣ����洢��������ơ�
	private Date initDate;
	private String name;

	// ʵ��Task����������ʼ�����������ԡ�
	public Task(String name) {
		initDate = new Date();
		this.name = name;
	}

	@Override
	public void run() {
		// ���ȣ���initDate���Ժ�ʵ�����ڣ���������Ŀ�ʼ���ڣ�д�뵽����̨��
		System.out.printf("%s: Task %s: Created on: %s\n", Thread.currentThread().getName(), name, initDate);
		System.out.printf("%s: Task %s: Started on: %s\n", Thread.currentThread().getName(), name, new Date());
		// Ȼ��ʹ����˯��һ�����ʱ�䡣
		try {
			Long duration = (long) (Math.random() * 10);
			System.out.printf("%s: Task %s: Doing a task during %dseconds\n", Thread.currentThread().getName(), name,
					duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// ��󣬽��������ʱ��д�����̨��
		System.out.printf("%s: Task %s: Finished on: %s\n", Thread.currentThread().getName(), name, new Date());
	}
}
