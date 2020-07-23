import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

class Draw extends JPanel {

	// Player's Position
	public static int x = Player.playerPosX;
	public static int y = Player.playerPosY;

	// To check whether the player is walking to the left or to the right!
	private static boolean goingLeft = true;
	private static boolean keyPressedI = false;
	private static boolean backgroundRenderNeeded = true;
	private static boolean treeCuttingLine = false;

	// Tree number to cut
	private static int needToCutTreeNumber;

	private static Player player = new Player();

	public static int getNeedToCutTreeNumber() {
		return needToCutTreeNumber;
	}

	public static void setNeedToCutTreeNumber(int needToCutTreeNumber) {
		Draw.needToCutTreeNumber = needToCutTreeNumber;
	}

	public static boolean isBackgroundRenderNeeded() {
		return backgroundRenderNeeded;
	}

	public static boolean isTreeCuttingLine() {
		return treeCuttingLine;
	}

	public static void setTreeCuttingLine(boolean treeCuttingLine) {
		Draw.treeCuttingLine = treeCuttingLine;
	}

	public static void setBackgroundRenderNeeded(boolean isBackgroundRenderNeeded) {
		Draw.backgroundRenderNeeded = isBackgroundRenderNeeded;
	}

	public static boolean isGoingLeft() {
		return goingLeft;
	}

	public static void setGoingLeft(boolean goingLeft) {
		Draw.goingLeft = goingLeft;
	}

	public static boolean isKeyPressedI() {
		return keyPressedI;
	}

	public static void setKeyPressedI(boolean keyPressedI) {
		Draw.keyPressedI = keyPressedI;
	}

	public void onScreen(Graphics g) {

		g.drawImage(Background.backgroundImage(), 0, 0, null);

		// Adding the player to the game!
		if (goingLeft) {
			g.drawImage(player.getPlayerImage()[0], x, y, null);
		} else {
			g.drawImage(player.getPlayerImage()[1], x, y, null);
		}

		g.drawString("X: " + x + " Y: " + y, 10, 10);

		if (treeCuttingLine) {
			// After the tree cutting ends, the backgroundRenderNeeded should be false!
			g.setColor(Color.blue);
			g.drawRect(Tree.getTreeX()[needToCutTreeNumber] - 10, Tree.getTreeY()[needToCutTreeNumber] - 10, 50, 90);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Only re-render the background if something has changed!
		if (backgroundRenderNeeded) {
			if (keyPressedI) {
				Tree.setRedWoodRender(true);
				Background.tree.getRedWoodImg();
				keyPressedI = false;
			}
			Background.backgroundImageRender(g);
			backgroundRenderNeeded = false;
		}
		onScreen(g);
		Main.wanderer.startWandererWalk(g);
	}

}

public class Main implements KeyListener {

	/*
	 * In this game, the traveler will meet a group of villages & display some huts
	 * & trades with them! Also, make sure to have a main menu, help, and settings
	 * page! You can enable and disable components!
	 */

	private static JFrame frame;

	protected static Draw draw = new Draw();
	protected static Wanderer wanderer = new Wanderer();

	public static Logger log;

	public Main() {

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
		frame.add(draw);
		startAnimation();

		frame.addKeyListener(this);

		// Finalizing stuff...
		frame.setTitle("Traveler");
		frame.setSize(915, 840);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		log.info("User is in the playing screen");

	}

	public static void main(String[] args) {
		new Main();

	}

	private static ActionListener listen;
	private static Timer timer;

	private static boolean timerStart = true;
	private static boolean randPosition = false;
	protected static boolean coordUpdate = true;

	// if one of the wanderers stop, then move them to the background!
	public static void startAnimation() {
		listen = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (coordUpdate) {
					System.out.println("Hut #0: " + Hut.getHutX()[0] + "," + Hut.getHutY()[0]);
					System.out.println("Hut #1: " + Hut.getHutX()[1] + "," + Hut.getHutY()[1]);
					System.out.println("Hut #2: " + Hut.getHutX()[2] + "," + Hut.getHutY()[2]);

					if (randPosition == false) {
						Wanderer.setPosition(0, 1, Hut.getHutX()[0] + 50, Hut.getHutY()[0] + 130);
						Wanderer.setPosition(1, 1, Hut.getHutX()[1] + 50, Hut.getHutY()[1] + 130);
						Wanderer.setPosition(2, 1, Hut.getHutX()[2] + 50, Hut.getHutY()[2] + 130);
						randPosition = true;
						coordUpdate = false;
					} else {
						Background.setWandererStopped(false);
						
						
						Wanderer.setPosition(0, 1, Wanderer.posX()[0], Wanderer.posY()[0]);
						Wanderer.setPosition(1, 1, Wanderer.posX()[1], Wanderer.posY()[1]);
						Wanderer.setPosition(2, 1, Wanderer.posX()[2], Wanderer.posY()[2]);		
						for (int i = 0; i < 3; i++) {
							Wanderer.moveWanderNum[i] = false;
							Wanderer.stopObjectPosX[i] = false;
							Wanderer.coordCheck[i] = true;
							Wanderer.targetPosCounter[i] = 0;
						}
						Draw.setBackgroundRenderNeeded(true);
						coordUpdate = false;
						
					}

				}

				// only repaint for the wanderers
				// when all the wanderers stop, try only repainting with the keys!
				if (!(Wanderer.moveWanderNum[0] && Wanderer.moveWanderNum[1] && Wanderer.moveWanderNum[2])) {
					draw.repaint();
				} else {
					draw.repaint();
					// timer.stop();
				}

			}

		};
		if (timerStart) {
			timer = new Timer(40, listen);
			timer.setRepeats(true);
			timer.start();
			timerStart = false;
		}

	}

	public static void proximitySensor() {
		// Tree: 30x70
		int treecutcounter = 0;
		for (int i = 0; i < 3; i++) {
			if (Draw.x > Tree.getTreeX()[i] - 80 && Draw.x < Tree.getTreeX()[i] + 60 && Draw.y > Tree.getTreeY()[i] - 60
					&& Draw.y < Tree.getTreeY()[i] + 100) {
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
		if ((Wanderer.moveWanderNum[0] && Wanderer.moveWanderNum[1] && Wanderer.moveWanderNum[2])) {
			draw.repaint();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		// Moving parts...
		if (e.getKeyCode() == KeyEvent.VK_W) {
			if (Draw.y > 10) {
				Draw.y -= Player.playerSpeed;
			}

			proximitySensor();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.info(e1.getMessage());
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			if (Draw.y < 729) {
				Draw.y += Player.playerSpeed;
			}
			proximitySensor();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.info(e1.getMessage());
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			if (Draw.x > 10) {
				Draw.x -= Player.playerSpeed;
			}
			proximitySensor();
			Draw.setGoingLeft(true);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.info(e1.getMessage());
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			if (Draw.x < 842) {
				Draw.x += Player.playerSpeed;
			}
			proximitySensor();
			Draw.setGoingLeft(false);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.info(e1.getMessage());
			}
		}

		// Cuts the tree
		if (e.getKeyCode() == KeyEvent.VK_I && Draw.isTreeCuttingLine()) {
			Draw.setBackgroundRenderNeeded(true);
			Draw.setKeyPressedI(true);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.out.println("Esc key released!");
		}

	}

}
