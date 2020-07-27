package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import hut.BigHut;
import mainmenu.MainMenu;
import tree.RedWood;

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

		// Adding huts to this game!
		for (int i = 0; i < 3; i++) {
			g.drawImage(bigHut.getHutImg(), BigHut.getHutX()[i], BigHut.getHutY()[i], null);
		}

		// Adding Trees to the game!
		for (int i = 0; i < 3; i++) {
			if (!(RedWood.getStage()[i] == 4)) {
				g.drawImage(redWood.getTreeImg()[i], RedWood.getTreeX()[i], RedWood.getTreeY()[i], null);
			}
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
