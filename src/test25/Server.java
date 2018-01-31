package test25;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
	// ����һ������ΪThreadPoolExecutor����Ϊexecutor�����ԡ�
	private ThreadPoolExecutor executor;

	// ʵ��Server��������ʹ��Executors���ʼ��ThreadPoolExecutor����
	public Server() {
		executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	}

	// ʵ��executeTask()����������Task������Ϊ�����������ύ��ִ���ߡ����ȣ�д��һ����Ϣ������̨��������һ���µ����񵽴
	public void executeTask(Task task) {
		System.out.printf("Server: A new task has arrived\n");
		// Ȼ�󣬵���ִ���ߵ�execute(���������ύ�������
		executor.execute(task);
		// ��󣬽�ִ���ߵ�����д�뵽����̨�������ǵ�״̬��
		System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());
		System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
		System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
	}

	// ʵ��endServer()����������������У�����ִ���ߵ�shutdown()��������������ִ�С�
	public void endServer() {
		executor.shutdown();
	}

}
