package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import hut.BigHut;
import mainmenu.MainMenu;
import player.Experience;
import player.Player;
import tree.RedWood;
import wanderer.Wanderer;

public class Main implements MouseListener, WindowListener {

	/*
	 * In this game, the traveler will meet a group of villages & display some huts
	 * & trades with them! Also, make sure to have a main menu, help, and settings
	 * page! You can enable and disable components!
	 */

	protected static Wanderer wanderer = new Wanderer();
	protected static Draw draw = new Draw();

	private static ActionListener listen;
	private static Timer timer;

	private static boolean timerStart = true;
	private static boolean randPosition = false;
	private static boolean coordUpdate = true;

	private static int FPS = 40;

	private Action goUp;
	private Action goDown;
	private Action goLeft;
	private Action goRight;
	private Action cutTree;

	public Main() {

		MainMenu.frame.add(draw);
		MainMenu.frame.addWindowListener(this);
		MainMenu.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		startAnimation();

		MainMenu.frame.setVisible(true);

		MainMenu.frame.setTitle("Traveler");
		goUp = new goUp();
		goDown = new goDown();
		goLeft = new goLeft();
		goRight = new goRight();
		cutTree = new cutTree();

		JRootPane rootOfAll = MainMenu.frame.getRootPane();
		rootOfAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "goUp");
		rootOfAll.getActionMap().put("goUp", goUp);

		rootOfAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "goDown");
		rootOfAll.getActionMap().put("goDown", goDown);

		rootOfAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "goLeft");
		rootOfAll.getActionMap().put("goLeft", goLeft);

		rootOfAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "goRight");
		rootOfAll.getActionMap().put("goRight", goRight);

		rootOfAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("I"), "cutTree");
		rootOfAll.getActionMap().put("cutTree", cutTree);
		MainMenu.frame.addMouseListener(this);

		Experience.writeLevelsUnlocked();
		Experience.checkLevelsUnlocked();

		if (Boolean.parseBoolean(Experience.levels[0]) == false) {
			Experience.levelUnlocked = true;
			Experience.levelUnlockedNum = 0;
			Experience.levels[0] = "true";
			Experience.writeLevelsUnlocked();
		}

		MainMenu.log.info("User is in the playing screen");

	}

	public static void main(String[] args) {
		new MainMenu();

	}

	public static void startAnimation() {
		listen = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isCoordUpdate()) {

					if (randPosition == false) {

						for (int i = 0; i < Wanderer.wandererNum; i++) {
							Wanderer.timeToCheck[i] = true;
							Wanderer.doit[i] = true;
							Wanderer.setPos(i, BigHut.getHutX()[i / 3] + (int) (Math.random() * 100),
									BigHut.getHutX()[i / 3] + (int) (Math.random() * 100));

						}

						Wanderer.startsignal = true;
						randPosition = true;
						setCoordUpdate(false);
					}

				}

				// only repaint for the wanderers
				// when all the wanderers stop, try only repainting with the keys!
				draw.repaint();

			}

		};
		if (timerStart) {
			timer = new Timer(FPS, listen);
			timer.setRepeats(true);
			timer.start();
			timerStart = false;
		}

	}

	public static void proximitySensor() {
		// Tree: 30x70
		int treecutcounter = 0;

		for (int i = 0; i < 3; i++) {
			if (Draw.x > RedWood.getTreeX()[i] - 80 && Draw.x < RedWood.getTreeX()[i] + 60
					&& Draw.y > RedWood.getTreeY()[i] - 60 && Draw.y < RedWood.getTreeY()[i] + 100) {
				Draw.setNeedToCutTreeNumber(i);
				Draw.setTreeCuttingLine(true);
				System.out.println("Inside the tree area!");
			} else {
				treecutcounter++;
				if (treecutcounter == 3) {
					Draw.setTreeCuttingLine(false);
				}
			}

		}

		int wandererSelectionOut = 0;
		for (int i = 0; i < Wanderer.wandererNum; i++) {

			if (Draw.x > Wanderer.getWandererX()[i] - 50 && Draw.x < Wanderer.getWandererX()[i] + 85
					&& Draw.y > Wanderer.getWandererY()[i] - 50 && Draw.y < Wanderer.getWandererY()[i] + 80) {
				Wanderer.wandererSelectedNum = i;
				Wanderer.wandererSelected = true;
			} else {
				wandererSelectionOut++;
				if (wandererSelectionOut == Wanderer.wandererNum) {
					Wanderer.wandererSelected = false;
				}

			}
		}
		// draw.repaint();

	}

	public class goUp extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Draw.y > 10) {
				Draw.y -= Player.getPlayerSpeed();
			}

			proximitySensor();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				MainMenu.log.info(e1.getMessage());
			}

		}

	}

	public class goDown extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Draw.y < 729) {
				Draw.y += Player.getPlayerSpeed();
			}
			proximitySensor();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				MainMenu.log.info(e1.getMessage());
			}

		}

	}

	public class goLeft extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Draw.x > 10) {
				Draw.x -= Player.getPlayerSpeed();
			}
			proximitySensor();
			Draw.setGoingLeft(true);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				MainMenu.log.info(e1.getMessage());
			}

		}

	}

	public class goRight extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Draw.x < 842) {
				Draw.x += Player.getPlayerSpeed();
			}
			proximitySensor();
			Draw.setGoingLeft(false);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				MainMenu.log.info(e1.getMessage());
			}

		}

	}

	public class cutTree extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (Draw.isTreeCuttingLine()) {
				Draw.setBackgroundRenderNeeded(true);
				Draw.setKeyPressedI(true);
			}

		}

	}

	public static boolean isCoordUpdate() {
		return coordUpdate;
	}

	public static void setCoordUpdate(boolean coordUpdate) {
		Main.coordUpdate = coordUpdate;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (Draw.isTreeCuttingLine()) {
			if (e.getX() > RedWood.getTreeX()[Draw.getNeedToCutTreeNumber()] - 10
					&& e.getX() < RedWood.getTreeX()[Draw.getNeedToCutTreeNumber()] + 50
					&& e.getY() > RedWood.getTreeY()[Draw.getNeedToCutTreeNumber()] - 10
					&& e.getY() < RedWood.getTreeY()[Draw.getNeedToCutTreeNumber()] + 90) {
				Draw.setBackgroundRenderNeeded(true);
				Draw.setKeyPressedI(true);
			}
			if (Wanderer.wandererSelected) {
				if (e.getX() > Wanderer.getWandererX()[Wanderer.wandererSelectedNum] - 50
						&& Draw.x < Wanderer.getWandererX()[Wanderer.wandererSelectedNum] + 85
						&& Draw.y > Wanderer.getWandererY()[Wanderer.wandererSelectedNum] - 50
						&& Draw.y < Wanderer.getWandererY()[Wanderer.wandererSelectedNum] + 80) {
					System.out.println("Within the area!");
				}
			}

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {

		BufferedWriter writeAchievements = null;
		int arrayCounter = 0;
		try {

			writeAchievements = new BufferedWriter(
					new FileWriter(new File("C:\\Traveler\\LocalData\\Achievements.txt")));

			for (int i = 0; i < Experience.levelDescription.length; i++) {
				if (i == Experience.levelUnlockedNum) {
					// System.out.println("Appending while closing...");
					writeAchievements.append(Experience.levels[i]);
					writeAchievements.newLine();
				} else {
					writeAchievements.append(Boolean.toString(false));
					writeAchievements.newLine();
				}

			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				writeAchievements.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		System.exit(0);

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	public static int getFPS() {
		return FPS;
	}

	public static void setFPS(int fPS) {
		FPS = fPS;
	}

}
