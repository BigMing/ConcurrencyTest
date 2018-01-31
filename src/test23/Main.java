package test23;

import test23.MyPhaser.Student;

public class Main {
	public static void main(String[] args) {
		// ���� MyPhaser����
		MyPhaser phaser = new MyPhaser();

		// ����5�� Student ����ʹ��register()������phaser��ע�����ǡ�
		MyPhaser.Student students[] = new Student[5];
		for (int i = 0; i < students.length; i++) {
			students[i] = phaser.new Student(phaser);
			phaser.register();
		}

		// ����5���߳�������students����ʼ���ǡ�
		Thread threads[] = new Thread[students.length];
		for (int i = 0; i < students.length; i++) {
			threads[i] = new Thread(students[i], "Student " + i);
			threads[i].start();
		}

		// �ȴ�5���̵߳��սᡣ
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// ����isTerminated()������дһ����Ϣ����phaser����termination״̬��
		System.out.printf("Main: The phaser has finished: %s.\n", phaser.isTerminated());
	}
}
