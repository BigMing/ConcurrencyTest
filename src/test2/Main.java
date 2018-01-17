package test2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

public class Main {
	public static void main(String[] args) throws IOException {
		Thread threads[] = new Thread[10];
		Thread.State status[] = new Thread.State[10];

		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Calculator(i));
			if (i % 2 == 0) {
				threads[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			threads[i].setName("Thread " + i);
		}

		FileWriter file = new FileWriter("log1.txt", true);
		PrintWriter pw = new PrintWriter(file);

		for (int i = 0; i < 10; i++) {
			// ��10���̵߳�״̬д���ĵ�
			pw.println("Main : Status of Thread " + i + " : " + threads[i].getState());
			status[i] = threads[i].getState();
		}

		// ִ��10���߳�
		for (int i = 0; i < 10; i++) {
			threads[i].start();
		}

		// ֱ����10���߳�ִ�н��������ǻ�һֱ������ǵ�״̬�������������״̬�ı䣬�Ͱ�״̬�����ı�
		boolean finish = false;
		while (!finish) {
			for (int i = 0; i < 10; i++) {
				if (threads[i].getState() != status[i]) {
					writeThreadInfo(pw, threads[i], status[i]);
					status[i] = threads[i].getState();
				}
			}
			finish = true;
			for (int i = 0; i < 10; i++) {
				finish = finish & (threads[i].getState() == State.TERMINATED);
			}
		}
		pw.close();
		file.close();
	}

	private static void writeThreadInfo(PrintWriter pw, Thread thread, State state) {
		pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
		pw.printf("Main : Priority: %d\n", thread.getPriority());
		pw.printf("Main : Old State: %s\n", state);
		pw.printf("Main : New State: %s\n", thread.getState());
		pw.printf("Main : ************************************\n");
	}
}