package test19;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
	private boolean freePrinters[];
	// 接着，声明一个名为lockPrinters的Lock对象。将要使用这个对象来保护freePrinters array的访问。
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
			// 最后，调用release() 方法来解放semaphore并标记打印机为空闲，通过在对应的freePrinters
			// array引索内分配真值。
			freePrinters[assignedPrinter] = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}

	private int getPrinter() {
		// 首先，声明一个int变量来保存printer的引索值。
		int ret = -1;
		// 然后， 获得lockPrinters对象 object的访问。
		try {
			lockPrinters.lock();
			// 然后，在freePrinters
			// array内找到第一个真值并在一个变量中保存这个引索值。修改值为false，因为等会这个打印机就会被使用。
			for (int i = 0; i < freePrinters.length; i++) {
				if (freePrinters[i]) {
					ret = i;
					freePrinters[i] = false;
					break;
				}
			}
			// 最后，解放lockPrinters对象并返回引索对象为真值。
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lockPrinters.unlock();
		}
		return ret;
	}
}
