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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import hut.BigHut;
import mainmenu.MainMenu;
import player.Inventory;
import road.DirtRoad;
import tree.RedWood;
import wanderer.Trade;

public class Background {

	/*
	 * You create one background image to mess with and change it or transfer it to
	 * onscreen method and delete it on the backgroundrender method and add it back
	 * again when the object is still for more than five seconds Also, add an
	 * ActionListener anonymous class to drop in some bomb shells
	 */

	private static BufferedImage backgroundimg;

	private static int backgroundImgCounter = 0;

	private static BigHut bigHut = new BigHut();
	protected static RedWood redWood = new RedWood();
	protected static DirtRoad dirtRoad = new DirtRoad();

	public static void setWandererStopped(boolean wandererStopped) {
	}

	public static void backgroundImageRender(Graphics g) {

		if (backgroundImgCounter == 0) {
			backgroundimg = new BufferedImage(900, 800, BufferedImage.TYPE_INT_RGB);
			g = backgroundimg.createGraphics();
			backgroundImgCounter++;
		} else {
			g = backgroundimg.getGraphics();
		}

		// Setting the background to grass color...
		g.setColor(Color.getColor("Grass", Color.HSBtoRGB(107, 66, 62)));
		g.fillRect(0, 0, 900, 800);

		// Stuff at the top...
		g.setColor(Color.black);
		g.drawString("Money: " +  Inventory.money + " coins", 275, 10);
		g.drawString("Experience: Level 1", 370, 10);
		g.drawString("Time: " + Trade.timeEra + " AD", 490, 10);

		// Adding huts to this game!
		for (int i = 0; i < BigHut.hutNum; i++) {
			g.drawImage(bigHut.getHutImg(), BigHut.getHutX()[i], BigHut.getHutY()[i], null);
		}

		// Adding Trees to the game!
		for (int i = 0; i < 3; i++) {
			if (!(RedWood.getStage(i) == 4) && !(RedWood.getStage(i) == 6)) {
				g.drawImage(redWood.getTreeImg()[i], RedWood.getTreeX()[i], RedWood.getTreeY()[i], null);
			}
		}

		// Adding random patches of road to random places on this map!

		for (int i = 0; i < DirtRoad.randRoads; i++) {
			g.drawImage(dirtRoad.getRoadImg(), DirtRoad.dirtRoadX[i], DirtRoad.dirtRoadY[i], null);
		}

	}

	public static BufferedImage backgroundImage() {
		return backgroundimg;
	}

	public static void backgroundImageExport() {
		try {
			new File("C:\\Traveler\\Export\\Background.png").mkdirs();
			ImageIO.write(backgroundimg, "png", new File("C:\\Traveler\\Export\\Background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MainMenu.log.info(e.getMessage());
		}

	}

}
