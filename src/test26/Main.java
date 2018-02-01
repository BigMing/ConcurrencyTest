package test26;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		// ʹ��Executors���newFixedThreadPool()��������ThreadPoolExecutor���������񡣴������2��
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

		// ����Future<Integer>��������С�
		List<Future<Integer>> resultList = new ArrayList<>();

		// ����Random��������������
		Random random = new Random();

		// ����0��10֮���10���������
		for (int i = 0; i < 10; i++) {
			Integer number = random.nextInt(10);
			// ����һ��FactorialCaculator���󣬴����������Ϊ������
			FactorialCalculator calculator = new FactorialCalculator(number);
			// ����ִ���ߵ�submit()�������ύFactorialCalculator�����ִ���ߡ������������Future<Integer>�������������񣬲������ջ�ȡ���Ľ����
			Future<Integer> result = executor.submit(calculator);
			// ���Future����֮ǰ���������С�
			resultList.add(result);
		}

		// ����һ��doѭ�������ִ���ߵ�״̬��
		do {
			// ���ȣ�д����Ϣ������̨������ʹ��ִ���ߵ�getCompletedTaskNumber()������õ�����ɵ���������
			System.out.printf("Main: Number of Completed Tasks:%d\n", executor.getCompletedTaskCount());
			// Ȼ�󣬶��������е�10��Future����ʹ��isDone()����������Ϣд�루������̨����������������������Ƿ��Ѿ����
			for (int i = 0; i < resultList.size(); i++) {
				Future<Integer> result = resultList.get(i);
				System.out.printf("Main: Task %d: %s\n", i, result.isDone());
			}
			// ������߳�˯��50����
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// ���ִ�����е������������С��10���ظ����ѭ����
		} while (executor.getCompletedTaskCount() < resultList.size());

		// ����õ�ÿ������Ľ��д�����̨������ÿ��Future����ͨ����������ʹ��get()������ȡ���ص�Integer����
		System.out.printf("Main: Results\n");
		for (int i = 0; i < resultList.size(); i++) {
			Future<Integer> result = resultList.get(i);
			Integer number = null;
			try {
				number = result.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			// Ȼ���ڿ���̨��ӡ�������
			System.out.printf("Main: Task %d: %d\n", i, number);
		}

		// ��󣬵���ִ���ߵ�shutdown()�������������ִ���ߡ�
		executor.shutdown();

	}
}
