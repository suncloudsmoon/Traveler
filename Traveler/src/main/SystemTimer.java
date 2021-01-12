/*
* Copyright (C) 2020 Ganesha Ajjampura
* 
* This program is free software: you can redistribute it and/or modify it under
* the terms of the GNU General Public License as published by the Free Software
* Foundation, either version 3 of the License, or (at your option) any later
* version.
* 
* This program is distributed in the hope that it will be useful, but WITHOUT
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
* FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
* details.
* 
* You should have received a copy of the GNU General Public License along with
* this program. If not, see <https://www.gnu.org/licenses/>.
*/

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
