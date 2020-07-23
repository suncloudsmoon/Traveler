import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class SystemTimer {

	private static Timer tellTime = null;
	private static boolean timerFinished = false;
	private static int counter = 0;
	
	public boolean getTimer() {
		return timerFinished;
	}

	public boolean setTimer(long duration) {
		ActionListener time = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				counter++;
				if (counter == duration) {
					tellTime.stop();
					timerFinished = true;
					counter = 0;
				}

			}

		};

		if (counter == 0 && !timerFinished) {
			timerFinished = false;
			tellTime.setDelay(1000);
			tellTime.start();
		}

		return timerFinished;
	}
}
