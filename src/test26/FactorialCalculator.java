package test26;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FactorialCalculator implements Callable<Integer> {
	// 声明一个私有的，类型为Integer，名为number的属性，用来存储任务将要计算出的数。
	private Integer number;

	// 实现FactorialCalculator构造器，初始化这个属性。
	public FactorialCalculator(Integer number) {
		this.number = number;
	}

	// 实现call()方法。这个方法将返回FactorialCalculator的number属性的阶乘。
	@Override
	public Integer call() throws Exception {
		// 首先，创建和初始化在这个方法中使用的局部变量。
		int result = 1;
		// 如果数是1或0，则返回1。否则，计算这个数的阶乘。出于教学目的，在两次乘之间，令这个任务睡眠20毫秒。
		if ((number == 0) || (number == 1)) {
			result = 1;
		} else {
			for (int i = 2; i <= number; i++) {
				result *= i;
				TimeUnit.MILLISECONDS.sleep(100);
			}
		}
		// 操作结果的信息写入控制台。
		System.out.printf("%s: %d\n", Thread.currentThread().getName(), result);
		// 返回操作结果。
		return result;
	}

}
