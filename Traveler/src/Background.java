import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background {

	/*
	 * You create one background image to mess with and change it or transfer it to
	 * onscreen method and delete it on the backgroundrender method and add it back
	 * again when the object is still for more than five seconds Also, add an
	 * ActionListener anonymous class to drop in some bomb shells
	 */

	private static BufferedImage backgroundimg;

	private static int backgroundImgCounter = 0;

	private static Hut hut = new Hut();
	protected static Tree tree = new Tree();
	
	private static BufferedImage moveImage;
	private static boolean wandererStopped = false;
	
	

	public static void setWandererStopped(boolean wandererStopped) {
		Background.wandererStopped = wandererStopped;
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
		g.drawImage(hut.hutImage(), Hut.getHutX()[0], Hut.getHutY()[0], null);
		g.drawImage(hut.hutImage(), Hut.getHutX()[1], Hut.getHutY()[1], null);
		g.drawImage(hut.hutImage(), Hut.getHutX()[2], Hut.getHutY()[2], null);

		// Adding Trees to the game!
		g.drawImage(tree.getRedWoodImg()[0], Tree.getTreeX()[0], Tree.getTreeY()[0], null);
		g.drawImage(tree.getRedWoodImg()[1], Tree.getTreeX()[1], Tree.getTreeY()[1], null);
		g.drawImage(tree.getRedWoodImg()[2], Tree.getTreeX()[2], Tree.getTreeY()[2], null);
		
		if (wandererStopped) {
			System.out.println(true);
			if (Wanderer.moveWanderNum[0]) {
				g.drawImage(Main.wanderer.getWandererLeftRendered()[0], Wanderer.getWandererPosX()[0], Wanderer.getWandererPosY()[0], null);
			}
			if (Wanderer.moveWanderNum[1]) {
				g.drawImage(Main.wanderer.getWandererLeftRendered()[1], Wanderer.getWandererPosX()[1], Wanderer.getWandererPosY()[1], null);
			}
			if (Wanderer.moveWanderNum[2]) {
				g.drawImage(Main.wanderer.getWandererLeftRendered()[2], Wanderer.getWandererPosX()[2], Wanderer.getWandererPosY()[2], null);
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
			Main.log.info(e.getMessage());
		}

	}

}
