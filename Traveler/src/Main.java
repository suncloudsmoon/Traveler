import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

class Draw extends JPanel {

	public static boolean treeCuttingLine = false;

	// To Check whether the player is walking to the left or to the right
	public static boolean isLeft = true;
	public static boolean isKeyPressedI = false;

	// Tree number to cut
	public static int needToCutTreeNumber;

	// Player's Position
	public static int x = Player.playerPosX;
	public static int y = Player.playerPosY;

	public static boolean isBackgroundRenderNeeded = true;

	public void onScreen(Graphics g) {
		g.drawImage(Background.backgroundImage(), 0, 0, null);

		// Adding the player to the game!
		if (isLeft == true) {
			g.drawImage(Player.playerImage()[0], x, y, null);
		} else {
			g.drawImage(Player.playerImage()[1], x, y, null);
		}

		g.drawString("X: " + x + " Y: " + y, 10, 10);

		// System.gc();

		if (treeCuttingLine == true) {
			// After the tree cutting ends, the backgroundRenderNeeded should be false!
			g.setColor(Color.blue);
			g.drawRect(Tree.getTreeX()[needToCutTreeNumber] - 10, Tree.getTreeY()[needToCutTreeNumber] - 10, 50, 90);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Only re-render the background if something has changed!
		if (isBackgroundRenderNeeded == true) {
			if (isKeyPressedI == true) {
				Tree.redWoodRender = true;
				Tree.getRedWoodImg();
				isKeyPressedI = false;
			}
			Background.backgroundImageRender(g);
			isBackgroundRenderNeeded = false;
		}

		onScreen(g);
	}

}

public class Main implements KeyListener {

	/*
	 * In this game, the traveler will meet a group of villages & display some huts
	 * & trades with them! Also, make sure to have a main menu, help, and settings
	 * page! You can enable and disable components!
	 */

	private static JFrame frame;
	private static Draw draw = new Draw();

	private static Logger log;

	public Main() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new File("C:\\Traveler\\LocalData").mkdirs();

		// Execute this only once!
		log = Logger.getLogger(""); // getlogger!
		FileHandler handleit = null;
		try {
			handleit = new FileHandler("C:\\Traveler\\Log.log");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleFormatter formatit = new SimpleFormatter();
		log.addHandler(handleit);
		handleit.setFormatter(formatit);

		frame = new JFrame();
		frame.add(draw);
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

	public static void proximitySensor() {
		// Tree: 30x70
		int treecutcounter = 0;
		for (int i = 0; i < 3; i++) {
			if (Draw.x > Tree.getTreeX()[i] - 80 && Draw.x < Tree.getTreeX()[i] + 60 && Draw.y > Tree.getTreeY()[i] - 60
					&& Draw.y < Tree.getTreeY()[i] + 100) {
				Draw.needToCutTreeNumber = i;
				Draw.treeCuttingLine = true;
				System.out.println("Inside the tree area!");
			} else {
				treecutcounter++;
				if (treecutcounter == 3) {
					Draw.treeCuttingLine = false;
				}
			}
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
				Draw.y -= 2;
			}

			proximitySensor();
			draw.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			if (Draw.y < 729) {
				Draw.y += 2;
			}	
			proximitySensor();
			draw.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			if (Draw.x > 10) {
				Draw.x -= 2;
			}
			proximitySensor();
			Draw.isLeft = true;
			draw.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			if (Draw.x < 842) {
				Draw.x += 2;
			}			
			proximitySensor();
			Draw.isLeft = false;
			draw.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		// Cuts the tree
		if (e.getKeyCode() == KeyEvent.VK_I && Draw.treeCuttingLine == true) {
			Draw.isBackgroundRenderNeeded = true;
			Draw.isKeyPressedI = true;
			draw.repaint();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.out.println("Esc key released!");
		}

	}

}
