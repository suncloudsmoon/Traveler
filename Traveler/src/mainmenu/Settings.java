/*
* Copyright (C) 2020, suncloudsmoon
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

package mainmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;

import main.Main;
import player.Player;
import wanderer.Wanderer;

public class Settings implements MouseListener {

	private static JLabel playerSpeed;
	private static JSlider pickSpeed;

	private static JLabel FPSLabel;
	private static JSlider pickFPS;

	private static JLabel difficulty;
	private static JComboBox pickDifficulty;
	private static String[] difficultyLevels = { "Noob", "Intermediate", "Very Hard" };

	private static JLabel goBack;
	private static Font bigFont;

	private static boolean settingsLoadingNeeded = true;

	public Settings() {

		if (settingsLoadingNeeded) {
			MainMenu.panel.setBackground(Color.LIGHT_GRAY);
			bigFont = new Font("Big Font", Font.ITALIC, 20);

			// Adding playerSpeed label!
			playerSpeed = new JLabel("Player Speed: ");
			playerSpeed.setBounds(15, 100, 150, 25);
			playerSpeed.setFont(bigFont);

			// Adding a JSlider to choose player's speed!
			pickSpeed = new JSlider(0, 5, Player.getPlayerSpeed());
			pickSpeed.setBounds(175, 25, 200, 200);
			pickSpeed.setToolTipText("Pick the player's speed");
			pickSpeed.setMajorTickSpacing(1);
			pickSpeed.setPaintLabels(true);
			pickSpeed.setPaintTicks(true);
			pickSpeed.setOpaque(false);

			// Difficulty text
			difficulty = new JLabel("Difficulty: ");
			difficulty.setBounds(55, 210, 150, 25);
			difficulty.setFont(bigFont);

			// Difficulty combobox
			pickDifficulty = new JComboBox(difficultyLevels);
			pickDifficulty.setBounds(175, 200, 200, 50);

			// Adding FPS label!
			FPSLabel = new JLabel("FPS: ");
			FPSLabel.setBounds(85, 300, 150, 25);
			FPSLabel.setFont(bigFont);

			// Adding a JSlider to choose FPS!
			pickFPS = new JSlider(0, 60, Main.getFPS());
			pickFPS.setBounds(175, 225, 200, 200);
			pickFPS.setToolTipText("Pick the FPS of the whole game");
			pickFPS.setMajorTickSpacing(10);
			pickFPS.setPaintLabels(true);
			pickFPS.setPaintTicks(true);
			pickFPS.setOpaque(false);

			goBack = new JLabel();
			goBack.setBounds(350, 700, 200, 100);
			goBack.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Go Back.png")));
			goBack.addMouseListener(this);

			settingsLoadingNeeded = false;

		} else {
			if (!MainMenu.panel.isEnabled()) {
				MainMenu.panel.setBackground(Color.LIGHT_GRAY);
				MainMenu.panel.setEnabled(true);
				MainMenu.frame.add(MainMenu.panel, BorderLayout.CENTER);

			}
			pickSpeed.setFocusable(true);
			playerSpeed.setEnabled(true);
			pickSpeed.setEnabled(true);
			goBack.setEnabled(true);
			difficulty.setEnabled(true);
			pickDifficulty.setEnabled(true);
			FPSLabel.setEnabled(true);
			pickFPS.setEnabled(true);
		}

		MainMenu.panel.add(playerSpeed);
		MainMenu.panel.add(pickSpeed);
		MainMenu.panel.add(difficulty);
		MainMenu.panel.add(pickDifficulty);
		MainMenu.panel.add(FPSLabel);
		MainMenu.panel.add(pickFPS);
		MainMenu.panel.add(goBack);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == goBack) {
			Player.setPlayerSpeed(pickSpeed.getValue());
			Main.setFPS(pickFPS.getValue());

			Wanderer.wandererNum -= pickDifficulty.getSelectedIndex();

			pickSpeed.setFocusable(false);
			playerSpeed.setEnabled(false);
			pickSpeed.setEnabled(false);
			goBack.setEnabled(false);
			difficulty.setEnabled(false);
			pickDifficulty.setEnabled(false);
			FPSLabel.setEnabled(false);
			pickFPS.setEnabled(false);

			MainMenu.panel.removeAll();
			MainMenu.panel.revalidate();
			MainMenu.panel.repaint();
			new MainMenu();

			System.out.println(pickSpeed.isEnabled());
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
