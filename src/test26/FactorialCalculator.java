package test26;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FactorialCalculator implements Callable<Integer> {
	// ����һ��˽�еģ�����ΪInteger����Ϊnumber�����ԣ������洢����Ҫ�����������
	private Integer number;

	// ʵ��FactorialCalculator����������ʼ��������ԡ�
	public FactorialCalculator(Integer number) {
		this.number = number;
	}

	// ʵ��call()�������������������FactorialCalculator��number���ԵĽ׳ˡ�
	@Override
	public Integer call() throws Exception {
		// ���ȣ������ͳ�ʼ�������������ʹ�õľֲ�������
		int result = 1;
		// �������1��0���򷵻�1�����򣬼���������Ľ׳ˡ����ڽ�ѧĿ�ģ������γ�֮�䣬���������˯��20���롣
		if ((number == 0) || (number == 1)) {
			result = 1;
		} else {
			for (int i = 2; i <= number; i++) {
				result *= i;
				TimeUnit.MILLISECONDS.sleep(100);
			}
		}
		// �����������Ϣд�����̨��
		System.out.printf("%s: %d\n", Thread.currentThread().getName(), result);
		// ���ز��������
		return result;
	}

}
