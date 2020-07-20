import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
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

	private static int[] hutX = Hut.posX();
	private static int[] hutY = Hut.posY();

	private static int backgroundImgCounter = 0;

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
		g.drawImage(Hut.hutImage(), hutX[0], hutY[0], null);
		g.drawImage(Hut.hutImage(), hutX[1], hutY[1], null);
		g.drawImage(Hut.hutImage(), hutX[2], hutY[2], null);

		// Adding Trees to the game!
		g.drawImage(Tree.getRedWoodImg()[0], Tree.getTreeX()[0], Tree.getTreeY()[0], null);
		g.drawImage(Tree.getRedWoodImg()[1], Tree.getTreeX()[1], Tree.getTreeY()[1], null);
		g.drawImage(Tree.getRedWoodImg()[2], Tree.getTreeX()[2], Tree.getTreeY()[2], null);

	}

	public static Image backgroundImage() {
		return backgroundimg;
	}

	public static void backgroundImageExport() {
		try {
			ImageIO.write(backgroundimg, "png", new File("Background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
