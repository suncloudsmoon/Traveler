package mainmenu;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import player.Player;

public class Help implements MouseListener {

	private static JTextArea getHelp;
	private static JScrollPane scrollit;
	private static JLabel goBack;

	private static boolean helpMenuGenerate = true;

	public Help() {

		if (helpMenuGenerate) {
			getHelp = new JTextArea();
			getHelp.append("Hello");
			getHelp.setEditable(false);
			getHelp.setLineWrap(true);

			scrollit = new JScrollPane(getHelp);
			scrollit.setBounds(25, 25, 600, 600);
			scrollit.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollit.setOpaque(false);
			scrollit.setBorder(new TitledBorder(new EtchedBorder(), "Unnamed Wanderer"));

			goBack = new JLabel();
			goBack.setBounds(350, 700, 200, 100);
			goBack.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Go Back.png")));
			goBack.addMouseListener(this);

			helpMenuGenerate = false;
		} else {
			scrollit.setEnabled(true);
			goBack.setEnabled(true);
		}

		getHelp.setWrapStyleWord(true);
		MainMenu.panel.add(scrollit, BorderLayout.EAST);
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
			getHelp.setFocusable(false);
			scrollit.setEnabled(false);
			goBack.setEnabled(false);
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
