package test25;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
	// 声明一个类型为ThreadPoolExecutor，名为executor的属性。
	private ThreadPoolExecutor executor;

	// 实现Server构造器，使用Executors类初始化ThreadPoolExecutor对象。
	public Server() {
		executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	}

	// 实现executeTask()方法，接收Task对象作为参数并将其提交到执行者。首先，写入一条信息到控制台，表明有一个新的任务到达。
	public void executeTask(Task task) {
		System.out.printf("Server: A new task has arrived\n");
		// 然后，调用执行者的execute(）方法来提交这个任务。
		executor.execute(task);
		// 最后，将执行者的数据写入到控制台来看它们的状态。
		System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());
		System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
		System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
	}

	// 实现endServer()方法，在这个方法中，调用执行者的shutdown()方法来结束任务执行。
	public void endServer() {
		executor.shutdown();
	}

}
