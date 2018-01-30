package test22;

import java.util.concurrent.Phaser;

public class Main {
	public static void main(String[] args) {
		// ���� ��3�������ߵ� Phaser ����
		Phaser phaser = new Phaser(3);

		// ����3�� FileSearch ����ÿ���ڲ�ͬ�ĳ�ʼ�ļ���������.log��չ�����ļ���
		FileSearch system = new FileSearch("C:\\Windows", "log", phaser);
		FileSearch apps = new FileSearch("C:\\Program Files", "log", phaser);
		FileSearch documents = new FileSearch("C:\\Documents And Settings", "log", phaser);

		// ��������ʼһ���߳���ִ�е�һ�� FileSearch ����
		Thread systemThread = new Thread(system, "System");
		systemThread.start();

		// ��������ʼһ���߳���ִ�еڶ��� FileSearch ����
		Thread appsThread = new Thread(apps, "Apps");
		appsThread.start();

		// ��������ʼһ���߳���ִ�е����� FileSearch ����
		Thread documentsThread = new Thread(documents, "Documents");
		documentsThread.start();
	}
}
