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

package player;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Experience {

	// Update this
	public static boolean levelUnlocked = false;
	public static String[] levels = new String[10];
	public static int levelUnlockedNum;

	public static String[] levelDescription = { "Welcome to the unknown lands!" };

	private static int counter = 0;

	public static void checkLevelsUnlocked() {
		String line;
		String[] splitRecord;

		BufferedReader check = null;
		try {
			FileReader readit = new FileReader(new File("C:\\Traveler\\LocalData\\Achievements.txt"));
			check = new BufferedReader(readit);
			while ((line = check.readLine()) != null) {
				System.out.println("Line: " + line);
				levels[counter] = line;
				counter++;
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				check.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void writeLevelsUnlocked() {

		BufferedWriter writeit = null;
		BufferedReader readit = null;

		String line;

		try {

			// It was executing everytime the program launched!
			if (!new File("C:\\Traveler\\LocalData\\Achievements.txt").exists()) {

				writeit = new BufferedWriter(new FileWriter(new File("C:\\Traveler\\LocalData\\Achievements.txt")));

				for (int i = 0; i < levelDescription.length; i++) {
					// System.out.println("Appending...");
					writeit.append(Boolean.toString(false));
					writeit.newLine();
				}
				writeit.close();

			}

			if (levelUnlocked) {
				if (SystemTray.isSupported()) {
					SystemTray displayLevel1 = SystemTray.getSystemTray();
					Image defaultone = Toolkit.getDefaultToolkit().getImage("level1.png");
					TrayIcon descriptionofLevel1 = new TrayIcon(defaultone, "Welcome");
					descriptionofLevel1.setImageAutoSize(true);
					try {
						displayLevel1.add(descriptionofLevel1);
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					descriptionofLevel1.displayMessage("Level " + (levelUnlockedNum + 1) + " Unlocked",
							levelDescription[levelUnlockedNum], MessageType.INFO);

				} else {
					JOptionPane.showInternalMessageDialog(null, "Level " + levelUnlockedNum + " Unlocked",
							levelDescription[levelUnlockedNum], JOptionPane.INFORMATION_MESSAGE);
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
