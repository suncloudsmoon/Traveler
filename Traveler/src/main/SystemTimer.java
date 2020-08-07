package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class SystemTimer {

	private static Timer tellTime = null;
	private static boolean timerFinished = false;
	private static int counter = 0;

	public boolean isTimerFinished() {
		return timerFinished;
	}

	public boolean setTimer(int delay, long duration) {
		ActionListener time = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				counter += delay;
				if (counter == duration) {
					tellTime.stop();
					setTimerFinished(true);
					counter = 0;

					System.out.println("Timer is up!");
					Background.redWood.changeTreeForCutting();
				}

			}

		};

		if (counter == 0 && !timerFinished) {
			tellTime = new Timer(delay, time);
			tellTime.setRepeats(true);
			tellTime.start();

			setTimerFinished(false);

		}

		return timerFinished;
	}

	public static void setTimerFinished(boolean timerFinished) {
		SystemTimer.timerFinished = timerFinished;
	}

}
