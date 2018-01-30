package test21;

import java.util.Random;

public class MatrixMock {
	// ����˽�� int matrix����Ϊ data��
	private int data[][];

	// ʵ����Ĺ��캯�����˹��캯�������վ�����������еĳ��ȣ������ǽ�Ҫ���ҵ�������Ϊ������3������ȫ��int ���͡�
	public MatrixMock(int size, int length, int number) {
		// ��ʼ�����캯����ʹ�õı����Ͷ���
		int counter = 0;
		data = new int[size][length];
		Random random = new Random();

		// ���������������ÿ����һ�����־���Ҫ���ҵ����ֶԱȣ������ȣ�������counterֵ��
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < length; j++) {
				data[i][j] = random.nextInt(10);
				if (data[i][j] == number) {
					counter++;
				}
			}
		}
		// ����ڲٿ�̨��ӡһ����Ϣ����ʾ���ҵ����������ɵľ�����ĳ��ִ���������Ϣ����������߳��ǻ�õ���ȷ����ġ�
		System.out.printf("Mock: There are %d ocurrences of %d in generated data.\n", counter, number);
	}

	// ʵ�� getRow() �������˷�������һ�� intΪ�������Ǿ������������������������ڣ����򷵻�null��
	public int[] getRow(int row) {
		if ((row >= 0) && (row < data.length)) {
			return data[row];
		}
		return null;
	}

}
