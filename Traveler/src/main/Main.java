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
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import builder.RoadBuilder;
import hut.BigHut;
import mainmenu.MainMenu;
import mainmenu.SaveMenu;
import player.Experience;
import player.Inventory;
import player.Player;
import tree.RedWood;
import wanderer.Trade;
import wanderer.Wanderer;

public class Main implements MouseListener, WindowListener {

	/*
	 * In this game, the traveler will meet a group of villages & display some huts
	 * & trades with them! Also, make sure to have a main menu, help, and settings
	 * page! You can enable and disable components!
	 */

	// Setup town halls, trading centers?, mini stock market?, connected roads,
	// attack, vikings

	protected static Wanderer wanderer = new Wanderer();
	public static Draw draw = new Draw();

	private static ActionListener listen;
	public static Timer timer;

	private static boolean timerStart = true;
	private static boolean coordUpdate = true;

	private static int FPS = 40;

	private Action goUp;
	private Action goDown;
	private Action goLeft;
	private Action goRight;
	private Action cutTree;
	private Action saveMenu;
	private Action openInventory;
	private Action collectItem;
	private Action enter;

	public Main() {

		MainMenu.frame.add(draw);
		MainMenu.frame.addWindowListener(this);
		MainMenu.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		MainMenu.frame.setVisible(true);
		MainMenu.frame.setTitle("Traveler");
		startAnimation();

		goUp = new goUp();
		goDown = new goDown();
		goLeft = new goLeft();
		goRight = new goRight();
		cutTree = new cutTree();
		saveMenu = new saveMenu();
		openInventory = new openInventory();
		collectItem = new collectItem();
		enter = new enter();

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

		rootOfAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "saveMenu");
		rootOfAll.getActionMap().put("saveMenu", saveMenu);

		rootOfAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("O"), "openInventory");
		rootOfAll.getActionMap().put("openInventory", openInventory);

		rootOfAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("U"), "collectItem");
		rootOfAll.getActionMap().put("collectItem", collectItem);

		rootOfAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "enter");
		rootOfAll.getActionMap().put("enter", enter);

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
				if (coordUpdate) {

					for (int i = 0; i < Wanderer.wandererNum; i++) {
						Wanderer.timeToCheck[i] = true;
						Wanderer.doit[i] = true;
						if (i < BigHut.hutNum) {
							Wanderer.setPos(i, BigHut.getHutX()[i / 3] + (int) (Math.random() * 100),
									BigHut.getHutX()[i / 3] + (int) (Math.random() * 100));
						} else {
							Wanderer.setPos(i, (int) (Math.random() * 800), (int) (Math.random() * 800));
						}

					}
					for (int i = 0; i < RoadBuilder.builderNum; i++) {
						RoadBuilder.timeToCheck[i] = true;
						RoadBuilder.doit[i] = true;
						if (i < BigHut.hutNum) {
							RoadBuilder.setPos(i, BigHut.getHutX()[i / 3] + (int) (Math.random() * 100),
									BigHut.getHutX()[i / 3] + (int) (Math.random() * 100));
						} else {
							RoadBuilder.setPos(i, (int) (Math.random() * 800), (int) (Math.random() * 800));
						}

					}
					RoadBuilder.startsignal = true;
					Wanderer.startsignal = true;
					coordUpdate = false;
					for (int i = 0; i < Wanderer.vx.length; i++) {
						Wanderer.vx[i] = 0;
						Wanderer.vy[i] = 0;
					}
					for (int i = 0; i < RoadBuilder.vx.length; i++) {
						RoadBuilder.vx[i] = 0;
						RoadBuilder.vy[i] = 0;
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
		} else if (!timer.isRunning()) {
			timer.restart();
			coordUpdate = true;
		}

	}

	public static void proximitySensor() {
		// Tree: 30x70
		int treecutcounter = 0;

		for (int i = 0; i < 3; i++) {
			if (RedWood.getStage(i) < 6) {
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
				Draw.openInventory = false;
				Trade.userTradeBoxOpen = false;
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
				Draw.openInventory = false;
				Trade.userTradeBoxOpen = false;
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
				Draw.openInventory = false;
				Trade.userTradeBoxOpen = false;
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
				Draw.openInventory = false;
				Trade.userTradeBoxOpen = false;
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

			if (Draw.isTreeCuttingLine() && RedWood.getStage(Draw.getNeedToCutTreeNumber()) < 6) {
				Draw.setKeyPressedI(true);
				Draw.setBackgroundRenderNeeded(true);
			}

		}

	}

	public class openInventory extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("O key pressed!");
			if (Draw.openInventory) {
				Draw.openInventory = false;
			} else {
				Draw.openInventory = true;
			}

		}

	}

	public class collectItem extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("U Key Pressed!");
			if (Draw.isTreeCuttingLine()) {
				if (RedWood.getStage(Draw.getNeedToCutTreeNumber()) == 5) {
					RedWood.setStage(Draw.getNeedToCutTreeNumber(), 1);
					Inventory.item[0] += 5;
					Draw.setBackgroundRenderNeeded(true);
					Draw.setTreeCuttingLine(false);
					System.out.println("RedWood Stage: " + RedWood.getStage(Draw.getNeedToCutTreeNumber()));

				}
			} else if (Wanderer.wandererSelected) {
				if (Trade.wandererTradeBoxOpen == false) {
					if (Trade.userTradeBoxOpen) {
						Trade.userTradeBoxOpen = false;
					} else {
						Trade.userTradeBoxOpen = true;
					}
				} else {
					Trade.wandererTradeBoxOpen = false;
				}

			}

		}

	}

	public class enter extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Enter key pressed!");

			if (Trade.wandererTradeBoxOpen) {
				System.out.println("Clicked on trade button!");
				Inventory.money += Trade.tradeAmount.getValue();
				Inventory.item[0] -= Trade.tradeAmount.getValue();
				Trade.wandererTradeBoxOpen = false;
				Draw.setBackgroundRenderNeeded(true);
				Trade.tradeAmount.setEnabled(false);
				draw.remove(Trade.tradeAmount);
				Wanderer.startWanderer(Wanderer.wandererSelectedNum);
			}

			if (Trade.userTradeBoxOpen) {
				Trade.userTradeBoxOpen = false;
				Trade.wandererTradeBoxOpen = true;
			}

		}

	}

	public class saveMenu extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Esc button clicked!");
			if (timer.isRunning())
				timer.stop();
			Draw.openInventory = false;
			Wanderer.startsignal = false;
			Wanderer.startsignal2 = false;
			RoadBuilder.startsignal = false;
			RoadBuilder.startsignal2 = false;
			MainMenu.frame.remove(draw);
			MainMenu.frame.revalidate();
			MainMenu.frame.repaint();
			new SaveMenu();

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

	public static int mouseX;
	public static int mouseY;

	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		if (Draw.isTreeCuttingLine()) {
			if (e.getX() > RedWood.getTreeX()[Draw.getNeedToCutTreeNumber()] - 10
					&& e.getX() < RedWood.getTreeX()[Draw.getNeedToCutTreeNumber()] + 50
					&& e.getY() > RedWood.getTreeY()[Draw.getNeedToCutTreeNumber()] - 10
					&& e.getY() < RedWood.getTreeY()[Draw.getNeedToCutTreeNumber()] + 90) {
				Draw.setBackgroundRenderNeeded(true);
				Draw.setKeyPressedI(true);
			}

		}

		if (Wanderer.wandererSelected) {
			if (e.getX() > Wanderer.getWandererX()[Wanderer.wandererSelectedNum] - 50
					&& Draw.x < Wanderer.getWandererX()[Wanderer.wandererSelectedNum] + 85
					&& Draw.y > Wanderer.getWandererY()[Wanderer.wandererSelectedNum] - 50
					&& Draw.y < Wanderer.getWandererY()[Wanderer.wandererSelectedNum] + 80) {
			}
		}

		if (Trade.wandererTradeBoxOpen) {
			if (e.getX() >= Wanderer.getWandererX()[Wanderer.wandererSelectedNum] - 15
					&& e.getX() <= Wanderer.getWandererX()[Wanderer.wandererSelectedNum] + 20
					&& e.getY() >= Wanderer.getWandererY()[Wanderer.wandererSelectedNum] - 15
					&& e.getY() <= Wanderer.getWandererY()[Wanderer.wandererSelectedNum] + 1) {

				Inventory.money += Trade.tradeAmount.getValue();
				Inventory.item[0] -= Trade.tradeAmount.getValue();
				Trade.wandererTradeBoxOpen = false;
				Draw.setBackgroundRenderNeeded(true);
				Trade.tradeAmount.setEnabled(false);
				draw.remove(Trade.tradeAmount);
				Wanderer.startWanderer(Wanderer.wandererSelectedNum);
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

		int option = JOptionPane.showConfirmDialog(null,
				'"' + "Untitled LostWorld #1.txt" + '"' + " has unsaved changes!\nDo you want to save the file?",
				"Unsaved Changes", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

		if (option == 0) {

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
		} else if (option == 1) {
			System.exit(0);
		}

	}

	public static int getFPS() {
		return FPS;
	}

	public static void setFPS(int fPS) {
		FPS = fPS;
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

}
