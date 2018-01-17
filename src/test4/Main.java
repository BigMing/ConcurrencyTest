package test4;

import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		FileSearch searcher = new FileSearch("D:\\", "ubuntu-16.04.3-desktop-amd64.iso");
		Thread thread = new Thread(searcher);
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		thread.interrupt();
	}
}
