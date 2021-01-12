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

package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import builder.RoadBuilder;
import player.Inventory;
import player.Player;
import tree.RedWood;
import wanderer.Trade;
import wanderer.Wanderer;

public class Draw extends JPanel {

	// Player's Position
	public static int x = Player.playerPosX;
	public static int y = Player.playerPosY;

	// To check whether the player is walking to the left or to the right!
	private static boolean goingLeft = true;
	private static boolean keyPressedI = false;
	public static boolean openInventory = false;
	private static boolean backgroundRenderNeeded = true;
	private static boolean treeCuttingLine = false;

	private static Font bigOne = new Font("Num", Font.TRUETYPE_FONT, 25);
	private static Font smallFont = new Font("Description", Font.PLAIN, 10);
	private static Font nullFont = new Font(null);

	protected Inventory inventory = new Inventory();
	protected RoadBuilder roadBuilder = new RoadBuilder();
	private static Trade trade = new Trade();

	public static JPanel sliderPanel = new JPanel();

	// Tree number to cut
	private static int needToCutTreeNumber;

	private static Player player = new Player();

	private static int sliderCounter = 0;

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

		if (openInventory) {
			g.drawImage(inventory.getTextBoxImg(), Draw.x - 75, Draw.y - 120, null);
			if (Inventory.item[0] > 0) {
				g.drawImage(Background.redWood.getTreeIcon(), Draw.x + Inventory.slotX[0], Draw.y + Inventory.slotY[0],
						null);
				g.drawRoundRect(Draw.x + Inventory.slotX[0] - 7, Draw.y + Inventory.slotY[0] - 5, 45, 50, 10, 10);
				g.drawString(Inventory.item[0] + " logs", Draw.x + Inventory.slotX[0] - 2,
						Draw.y + Inventory.slotY[0] + 35);
			}

		}
		
		g.drawString("X: " + x + " Y: " + y, 10, 10);
		g.drawString("In Hand: None", 95, 10);
		g.drawString("Health: 100%", 190, 10);

		if (treeCuttingLine) {
			// After the tree cutting ends, the backgroundRenderNeeded should be false!
			g.setColor(Color.blue);
			g.drawRect(RedWood.getTreeX()[needToCutTreeNumber] - 10, RedWood.getTreeY()[needToCutTreeNumber] - 10, 50,
					90);
			if (RedWood.getStage(needToCutTreeNumber) == 4) {
				g.drawImage(Background.redWood.getTreeFallingAnimation(), RedWood.getTreeX()[Draw.needToCutTreeNumber],
						RedWood.getTreeY()[Draw.needToCutTreeNumber], null);
			}

		} else if (Trade.userTradeBoxOpen) {
			g.drawImage(inventory.getUserTradeTextBoxImg(), Draw.x - 75, Draw.y - 120, null);

			for (int i = 0; i < Inventory.item.length; i++) {
				if (Inventory.item[i] > 0) {
					g.drawImage(Background.redWood.getTreeIcon(), Draw.x + Inventory.slotX[i],
							Draw.y + Inventory.slotY[i], null);
					g.drawString(Inventory.item[i] + " logs", Draw.x + Inventory.slotX[i] - 2,
							Draw.y + Inventory.slotY[i] + 35);

					if (Inventory.selectedItem == i) {
						g.drawRoundRect(Draw.x + Inventory.slotX[i] - 7, Draw.y + Inventory.slotY[i] - 5, 45, 50, 10,
								10);
					}
				}
			}

		} else if (Trade.wandererTradeBoxOpen && Wanderer.wandererSelected) {
			g.drawImage(trade.getTradeBox(), Wanderer.getWandererX()[Wanderer.wandererSelectedNum] - 75,
					Wanderer.getWandererY()[Wanderer.wandererSelectedNum] - 120, null);

			g.setFont(bigOne);
			g.drawString(Integer.toString(Trade.tradeAmount.getValue()),
					Wanderer.getWandererX()[Wanderer.wandererSelectedNum] + Trade.numX[0],
					Wanderer.getWandererY()[Wanderer.wandererSelectedNum] + Trade.numY[0]);

			g.drawString(Integer.toString(Trade.tradeAmount.getValue()),
					Wanderer.getWandererX()[Wanderer.wandererSelectedNum] + Trade.numX[1],
					Wanderer.getWandererY()[Wanderer.wandererSelectedNum] + Trade.numY[1]);

			g.setFont(smallFont);
			g.drawString("Logs", Wanderer.getWandererX()[Wanderer.wandererSelectedNum] + Trade.numX[0] - 5,
					Wanderer.getWandererY()[Wanderer.wandererSelectedNum] + Trade.numY[0] + 15);

			g.drawString("Coins", Wanderer.getWandererX()[Wanderer.wandererSelectedNum] + Trade.numX[1] - 5,
					Wanderer.getWandererY()[Wanderer.wandererSelectedNum] + Trade.numY[1] + 15);

			if (!Trade.tradeAmount.isEnabled()) {
				Trade.tradeAmount.setBounds(Wanderer.getWandererX()[Wanderer.wandererSelectedNum] + Trade.numX[0] - 20,
						Wanderer.getWandererY()[Wanderer.wandererSelectedNum] + Trade.numY[0] + 22, 165, 10);
				Trade.tradeAmount.setMajorTickSpacing(Inventory.item[0] / 10);
				Trade.tradeAmount.setMinimum(Inventory.item[0] / 10);
				Trade.tradeAmount.setMaximum(Inventory.item[0]);
				Trade.tradeAmount.setEnabled(true);
				add(Trade.tradeAmount);

				Wanderer.stopWanderer(Wanderer.wandererSelectedNum);
			} else if (Trade.tradeAmount.isEnabled()) {

				Trade.tradeAmount.setLocation(
						Wanderer.getWandererX()[Wanderer.wandererSelectedNum] + Trade.numX[0] - 20,
						Wanderer.getWandererY()[Wanderer.wandererSelectedNum] + Trade.numY[0] + 22);

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
		roadBuilder.startBuilderWalk(g);

	}

}
