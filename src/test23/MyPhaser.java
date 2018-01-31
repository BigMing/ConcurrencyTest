package test23;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class MyPhaser extends Phaser {
	// ���� onAdvance() ���������� phase �����Ե�ֵ�����ǽ����ò�ͬ�ĸ������������ phase ���� 0������
	// studentsArrived() ����������� phase ���� 1������ finishFirstExercise() ����������� phase
	// ���� 2������ finishSecondExercise() ����������� phase ���� 3������ finishExam()
	// ���������򣬷�����ֵ����ʾphaser�Ѿ��սᡣ
	@Override
	protected boolean onAdvance(int phase, int registeredParties) {
		switch (phase) {
		case 0:
			return studentsArrived();
		case 1:
			return finishFirstExercise();
		case 2:
			return finishSecondExercise();
		case 3:
			return finishExam();
		default:
			return true;
		}
	}

	// ʵ�ָ������� studentsArrived()�����ڲٿ�̨д2����Ϣ��������falseֵ������phaser������ִ�С�
	private boolean studentsArrived() {
		System.out.printf("Phaser: The exam are going to start. The students are ready.\n");
		System.out.printf("Phaser: We have %d students.\n", getRegisteredParties());
		return false;
	}

	// ʵ�ָ������� finishFirstExercise()�����ڲٿ�̨д2����Ϣ��������falseֵ������phaser������ִ�С�
	private boolean finishFirstExercise() {
		System.out.printf("Phaser: All the students have finished the first exercise.\n");
		System.out.printf("Phaser: It's time for the second one.\n");
		return false;
	}

	// ʵ�ָ������� finishSecondExercise()�����ڲٿ�̨д2����Ϣ��������falseֵ������phaser������ִ�С�
	private boolean finishSecondExercise() {
		System.out.printf("Phaser: All the students have finished the second exercise.\n");
		System.out.printf("Phaser: It's time for the third one.\n");
		return false;
	}

	// ʵ�ָ������� finishExam()�����ڲٿ�̨д2����Ϣ��������falseֵ������phaser������ִ�С�
	private boolean finishExam() {
		System.out.printf("Phaser: All the students have finished the exam.\n");
		System.out.printf("Phaser: Thank you for your time.\n");
		return true;
	}

	public class Student implements Runnable {
		// ���� a Phaser ������Ϊ phaser.
		private Phaser phaser;

		// ʵ����Ĺ��캯������ʼ Phaser ����
		public Student(Phaser phaser) {
			this.phaser = phaser;
		}

		// ʵ�� run() ������ģ����ʵ���顣
		@Override
		public void run() {
			// ���ȣ�����дһ����Ϣ���ٿ�̨����ѧ�����￼�������� phaser �� arriveAndAwaitAdvance()
			// �������ȴ������߳��ǡ�
			System.out.printf("%s: Has arrived to do the exam. %s\n", Thread.currentThread().getName(), new Date());
			phaser.arriveAndAwaitAdvance();
			// Ȼ��д��Ϣ���ٿ�̨������˽�� doExercise1() ����ģ���һ�����飬д��һ����Ϣ���ٿ�̨������ phaser
			// �� arriveAndAwaitAdvance() �������ȴ�����ѧ��������һ�����顣
			System.out.printf("%s: Is going to do the first exercise. %s\n", Thread.currentThread().getName(),
					new Date());
			doExercise1();
			System.out.printf("%s: Has done the first exercise. %s\n", Thread.currentThread().getName(), new Date());
			phaser.arriveAndAwaitAdvance();
			// Ϊ�ڶ����͵�����ʵ����ͬ�Ĵ��롣
			System.out.printf("%s: Is going to do the second exercise.%s\n", Thread.currentThread().getName(),
					new Date());
			doExercise2();
			System.out.printf("%s: Has done the second exercise. %s\n", Thread.currentThread().getName(), new Date());
			phaser.arriveAndAwaitAdvance();
			System.out.printf("%s: Is going to do the third exercise. %s\n", Thread.currentThread().getName(),
					new Date());
			doExercise3();
			System.out.printf("%s: Has finished the exam. %s\n", Thread.currentThread().getName(), new Date());
			phaser.arriveAndAwaitAdvance();
		}

		// ʵ�ָ������� doExercise1()���˷������߳��������һ��ʱ�䡣
		private void doExercise1() {
			try {
				long duration = (long) (Math.random() * 10);
				TimeUnit.SECONDS.sleep(duration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// ʵ�ָ������� doExercise2()���˷������߳��������һ��ʱ�䡣
		private void doExercise2() {
			try {
				long duration = (long) (Math.random() * 10);
				TimeUnit.SECONDS.sleep(duration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// ʵ�ָ������� doExercise3()���˷������߳��������һ��ʱ�䡣
		private void doExercise3() {
			try {
				long duration = (long) (Math.random() * 10);
				TimeUnit.SECONDS.sleep(duration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}