package test19;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
	private boolean freePrinters[];
	// ���ţ�����һ����ΪlockPrinters��Lock���󡣽�Ҫʹ���������������freePrinters array�ķ��ʡ�
	private Lock lockPrinters;

	private final Semaphore semaphore;

	public PrintQueue() {
		semaphore = new Semaphore(3);
		freePrinters = new boolean[3];
		for (int i = 0; i < 3; i++) {
			freePrinters[i] = true;
		}
		lockPrinters = new ReentrantLock();
	}

	public void printJob(Object document) {
		try {
			semaphore.acquire();
			int assignedPrinter = getPrinter();

			long duration = (long) (Math.random() * 10);
			System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(),
					duration);
			TimeUnit.SECONDS.sleep(duration);
			// ��󣬵���release() ���������semaphore����Ǵ�ӡ��Ϊ���У�ͨ���ڶ�Ӧ��freePrinters
			// array�����ڷ�����ֵ��
			freePrinters[assignedPrinter] = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}

	private int getPrinter() {
		// ���ȣ�����һ��int����������printer������ֵ��
		int ret = -1;
		// Ȼ�� ���lockPrinters���� object�ķ��ʡ�
		try {
			lockPrinters.lock();
			// Ȼ����freePrinters
			// array���ҵ���һ����ֵ����һ�������б����������ֵ���޸�ֵΪfalse����Ϊ�Ȼ������ӡ���ͻᱻʹ�á�
			for (int i = 0; i < freePrinters.length; i++) {
				if (freePrinters[i]) {
					ret = i;
					freePrinters[i] = false;
					break;
				}
			}
			// ��󣬽��lockPrinters���󲢷�����������Ϊ��ֵ��
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lockPrinters.unlock();
		}
		return ret;
	}
}
