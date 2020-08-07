package mainmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;

public class SaveMenu implements MouseListener {

	private static JLabel resume;
	private static JLabel settings;
	private static JLabel save;

	private static boolean saveMenuLoad = true;

	public SaveMenu() {
		if (saveMenuLoad) {

			// Adding new JPanel!
			MainMenu.panel.setLayout(null);
			MainMenu.panel.setBounds(0, 0, 900, 800);
			MainMenu.panel.setBackground(Color.darkGray);

			// Added a resume image!
			resume = new JLabel();
			resume.setBounds(330, 250, 200, 100);
			resume.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Resume.png")));
			resume.addMouseListener(this);

			// Added a settings image!
			settings = new JLabel();
			settings.setBounds(330, 370, 200, 100);
			settings.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Settings.png")));
			settings.addMouseListener(this);

			// Added a save option!
			save = new JLabel();
			save.setBounds(330, 490, 200, 100);
			save.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Save.png")));
			save.addMouseListener(this);

			MainMenu.frame.add(MainMenu.panel, BorderLayout.CENTER);
		} else {
			resume.setEnabled(true);
			settings.setEnabled(true);
			save.setEnabled(true);
		}
		if (!MainMenu.panel.isEnabled()) {
			MainMenu.panel.setBackground(Color.darkGray);
			MainMenu.panel.setEnabled(true);
			MainMenu.frame.add(MainMenu.panel, BorderLayout.CENTER);
		}

		MainMenu.panel.add(resume);
		MainMenu.panel.add(settings);
		MainMenu.panel.add(save);
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
		if (e.getSource() == resume) {
			resume.setEnabled(false);
			settings.setEnabled(false);
			save.setEnabled(false);
			MainMenu.panel.removeAll();
			MainMenu.panel.revalidate();
			MainMenu.panel.repaint();

			new Main();

		} else if (e.getSource() == settings) {
			resume.setEnabled(false);
			settings.setEnabled(false);
			save.setEnabled(false);
			MainMenu.panel.removeAll();
			MainMenu.panel.revalidate();
			MainMenu.panel.repaint();

			new Settings();

		} else if (e.getSource() == save) {
			resume.setEnabled(false);
			settings.setEnabled(false);
			save.setEnabled(false);
			MainMenu.panel.removeAll();
			MainMenu.panel.revalidate();
			MainMenu.panel.repaint();

			new MainMenu();
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
