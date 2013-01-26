package application;

import ui.javaFX.JavaFxApplication;

/**
 * Entry point for this project. Here, the used UI framework is chosen. The
 * application is started in a new thread.
 * 
 * @author Steven Schwenke
 * 
 */
public class Start {

	public static void main(String[] args) throws InterruptedException {

		// If there were other user interfaces, here would be the decision which
		// of them to start. Right now, there is only JavaFX.

		Thread t = new Thread() {
			@Override
			public void run() {
				System.out.println("Starting JavaFX ...");
				JavaFxApplication.main(new String[0]);
				System.out.println("JavaFX ended");
			}
		};

		t.start();
		t.join();
		System.out.println("terminating or closing java program");
		System.exit(1);
	}
}
