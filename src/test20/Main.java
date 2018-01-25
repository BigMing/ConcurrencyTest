package test20;

public class Main {
	public static void main(String[] args) {
		// ���� Videoconference ������Ϊ conference�����ȴ�10�������ߡ�
		Videoconference conference = new Videoconference(10);

		// ���� Thread ��������� Videoconference ���󲢿�ʼ���С�
		Thread threadConference = new Thread(conference);
		threadConference.start();

		// ���� 10�� Participant ����Ϊÿ�����������һ�� Thread �������������ǣ���ʼ����ȫ�����̡߳�
		for (int i = 0; i < 10; i++) {
			Participant p = new Participant(conference, "Participant " + i);
			Thread t = new Thread(p);
			t.start();
		}
	}
}
