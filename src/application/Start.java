package application;

import ui.javaFX.JavaFxApplication;

/**
 * Entry point for this project. Here, the used UI framework is chosen.
 * 
 * @author Steven Schwenke
 * 
 */
public class Start {

	public static void main(String[] args) {

		// If there were other user interfaces, here would be the decision which
		// of them to start. Right now, there is only JavaFX.
		JavaFxApplication.main(new String[0]);
	}
}
