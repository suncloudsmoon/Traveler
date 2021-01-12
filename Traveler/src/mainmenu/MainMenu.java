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

package mainmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.Main;

public class MainMenu implements MouseListener {

	public static Logger log;

	public static JFrame frame;
	protected static JPanel panel;

	private static JLabel play;
	private static JLabel help;
	private static JLabel settings;
	private static JLabel welcome;

	private static boolean mainMenuGenerate = true;

	public MainMenu() {
		if (mainMenuGenerate) {

			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info(e.getMessage());
			}
			new File("C:\\Traveler\\LocalData").mkdirs();

			// Execute this only once!
			log = Logger.getLogger(""); // getlogger!
			FileHandler handleit = null;
			try {
				handleit = new FileHandler("C:\\Traveler\\Log.log", true);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info(e.getMessage());

			}
			SimpleFormatter formatit = new SimpleFormatter();
			log.addHandler(handleit);
			handleit.setFormatter(formatit);

			frame = new JFrame();

			panel = new JPanel();
			panel.setLayout(null);
			panel.setBackground(Color.LIGHT_GRAY);

			welcome = new JLabel("Traveler");
			welcome.setFont(new Font("Big Font", Font.ITALIC, 65));
			welcome.setBounds(300, 45, 285, 100);

			play = new JLabel();
			play.setBounds(330, 300, 200, 100);
			play.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Play.png")));
			play.addMouseListener(this);

			help = new JLabel();
			help.setBounds(330, 410, 200, 100);
			help.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Help.png")));
			help.addMouseListener(this);

			settings = new JLabel();
			settings.setBounds(330, 520, 200, 100);
			settings.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Settings.png")));
			settings.addMouseListener(this);

			// Finalizing stuff...
			Image appIcon = null;
			try {
				appIcon = ImageIO.read(getClass().getClassLoader().getResource("Left Player Position.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.setIconImage(appIcon);
			
			frame.setTitle("Welcome to Traveler");
			frame.setSize(915, 840);
			frame.setLocationRelativeTo(null);
			frame.setResizable(false);
			frame.setVisible(true);

			mainMenuGenerate = false;
		} else {
			panel.setBackground(Color.LIGHT_GRAY);
			panel.setEnabled(true);
			welcome.setEnabled(true);
			play.setEnabled(true);
			help.setEnabled(true);
			settings.setEnabled(true);		
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(welcome);
		panel.add(play);
		panel.add(help);
		panel.add(settings);
		frame.add(panel, BorderLayout.CENTER);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == play) {
			System.out.println("Clicked on the play button!");
			
			welcome.setEnabled(false);
			play.setEnabled(false);
			help.setEnabled(false);
			settings.setEnabled(false);
			panel.setEnabled(false);
			
			panel.removeAll();
			panel.revalidate();
			panel.repaint();
			
			frame.remove(panel);
			frame.revalidate();
			frame.repaint();
			new Main();

		} else if (e.getSource() == help) {
			System.out.println("Clicked on help button!");
			
			welcome.setEnabled(false);
			play.setEnabled(false);
			help.setEnabled(false);
			settings.setEnabled(false);
			panel.setEnabled(false);
			
			panel.removeAll();
			panel.revalidate();
			panel.repaint();
			new Help();

		} else if (e.getSource() == settings) {
			System.out.println("Clicked on settings button!");
			
			welcome.setEnabled(false);
			play.setEnabled(false);
			help.setEnabled(false);
			settings.setEnabled(false);
			panel.setEnabled(false);
			
			panel.removeAll();
			panel.revalidate();
			panel.repaint();
			new Settings();
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
