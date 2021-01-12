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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Help implements MouseListener {

	private static JTextArea getHelp;
	private static JScrollPane scrollit;
	private static JLabel goBack;

	private static boolean helpMenuGenerate = true;

	public Help() {

		if (helpMenuGenerate) {
			getHelp = new JTextArea();
			getHelp.append(
					"Use W A S D keys to move the main character of this game! Either click on the tree or Press I key to chop down trees! Have Fun!");
			getHelp.setEditable(false);
			getHelp.setLineWrap(true);

			scrollit = new JScrollPane(getHelp);
			scrollit.setBounds(120, 25, 600, 600);
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
