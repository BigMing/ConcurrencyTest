package test21;

public class Results {
	// ����˽�� int array ��Ϊ data��
	private int data[];

	// ʵ����Ĺ��캯�����˹��캯������һ������arrayԪ������������Ϊ������
	public Results(int size) {
		data = new int[size];
	}

	// ʵ�� setData() �������˷�������array��ĳ��λ�ú�һ��ֵ��Ϊ������Ȼ���array���Ǹ�λ���趨Ϊ�Ǹ�ֵ��
	public void setData(int position, int value) {
		data[position] = value;
	}

	// ʵ�� getData() �������˷������ؽ�� array��
	public int[] getData() {
		return data;
	}

}
