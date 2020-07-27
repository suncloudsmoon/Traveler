package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import player.Inventory;
import player.Player;
import tree.RedWood;

public class Draw extends JPanel {

	// Player's Position
	public static int x = Player.playerPosX;
	public static int y = Player.playerPosY;

	// To check whether the player is walking to the left or to the right!
	private static boolean goingLeft = true;
	private static boolean keyPressedI = false;
	private static boolean backgroundRenderNeeded = true;
	private static boolean treeCuttingLine = false;

	protected Inventory inventory = new Inventory();

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
		g.drawString("Health: 100%", 90, 10);
		g.drawString("Experience: Level 1", 170, 10);

		// g.drawImage(inventory.getTextBoxImg(), 200, 200, null);

		if (treeCuttingLine) {
			// After the tree cutting ends, the backgroundRenderNeeded should be false!
			g.setColor(Color.blue);
			g.drawRect(RedWood.getTreeX()[needToCutTreeNumber] - 10, RedWood.getTreeY()[needToCutTreeNumber] - 10, 50,
					90);

			if (RedWood.getStage()[getNeedToCutTreeNumber()] == 4) {
				g.drawImage(Background.redWood.getTreeFallingAnimation(), RedWood.getTreeX()[needToCutTreeNumber],
						RedWood.getTreeY()[needToCutTreeNumber], null);
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Only re-render the background if something has changed!
		if (backgroundRenderNeeded) {
			if (keyPressedI) {
				RedWood.setRedWoodRender(true);
				Background.redWood.getTreeImg();
				keyPressedI = false;
			}
			Background.backgroundImageRender(g);
			backgroundRenderNeeded = false;
		}
		onScreen(g);
		Main.wanderer.startWandererWalk(g);
	}

}
