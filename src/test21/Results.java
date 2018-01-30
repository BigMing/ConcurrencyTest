package test21;

public class Results {
	// 声明私有 int array 名为 data。
	private int data[];

	// 实现类的构造函数。此构造函数接收一个表明array元素量的整数作为参数。
	public Results(int size) {
		data = new int[size];
	}

	// 实现 setData() 方法。此方法接收array的某个位置和一个值作为参数，然后把array的那个位置设定为那个值。
	public void setData(int position, int value) {
		data[position] = value;
	}

	// 实现 getData() 方法。此方法返回结果 array。
	public int[] getData() {
		return data;
	}

}
