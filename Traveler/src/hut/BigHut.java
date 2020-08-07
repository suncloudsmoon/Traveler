package hut;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BigHut implements Hut {

	public static final int hutNum = (int) (Math.random() * 4) + 1;
	
	private static BufferedImage hut;
	
	private static int[] x = new int[hutNum]; // Access this by x[1] etc
	private static int[] y = new int[hutNum];
	
	private static int[] hutX = posX();
	private static int[] hutY = posY();
	

	public static int[] getHutX() {
		return hutX;
	}

	public static int[] getHutY() {
		return hutY;
	}

	public BufferedImage getHutImg() {
		try {
			hut = ImageIO.read(getClass().getClassLoader().getResource("Hut.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hut;
	}

	// Make sure the huts don't interfere with each other!
	private static int[] posX() {
		for (int i = 0; i < hutNum; i++) {
			x[i] = (int) (Math.random() * 742);
		}
		return x;
	}

	private static int[] posY() {
		for (int i = 0; i < hutNum; i++) {
			y[i] = (int) (Math.random() * 610);
		}
		return y;
	}

}
