package test21;

public class Grouper implements Runnable {
	// ����һ��˽�� Results ���ԣ���Ϊ results��
	private Results results;

	// ʵ����Ĺ��캯��������ʼ�� Results ���ԡ�
	public Grouper(Results results) {
		this.results = results;
	}

	// ʵ�� run() ����������������array�����ֳ��ִ������ܺ͡�
	@Override
	public void run() {
		// ����һ�� int ������д�ڲٿ�̨дһ����Ϣ������ʼ�����ˡ�
		int finalResult = 0;
		System.out.printf("Grouper: Processing results...\n");
		// ʹ�� results ����� getData() ���������ÿ�����ֳ��ֵĴ�����Ȼ�󣬴���array��ȫ��Ԫ�أ���ÿ��Ԫ�ص�ֵ�Ӹ�
		// finalResult ������
		int data[] = results.getData();
		for (int number : data) {
			finalResult += number;
		}

		// �ڲٿ�̨��ӡ�����
		System.out.printf("Grouper: Total result: %d.\n", finalResult);
	}
}
