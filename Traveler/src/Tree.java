import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tree {

	private static BufferedImage[] redWood = new BufferedImage[3];

	private static int[] x = new int[4];
	private static int[] y = new int[4];

	private static int[] treeX = posX();
	private static int[] treeY = posY();

	private static int[] stage = { 1, 1, 1, 1 };
	private static int treeRenderOncecounter = 0;

	private static boolean redWoodRender = true;

	public static void setRedWoodRender(boolean redWoodRender) {
		Tree.redWoodRender = redWoodRender;
	}

	public static int[] getTreeX() {
		return treeX;
	}

	public static int[] getTreeY() {
		return treeY;
	}

	// Create different types of trees
	public BufferedImage[] getRedWoodImg() {
		if (redWoodRender) {
			try {
				if (treeRenderOncecounter == 0) {
					for (int i = 0; i < 3; i++) {
						redWood[i] = ImageIO.read(getClass().getClassLoader().getResource("Red Wood.png"));
					}
					treeRenderOncecounter++;
				}

				if (Draw.isKeyPressedI()) {
					redWood[Draw.getNeedToCutTreeNumber()] = ImageIO.read(getClass().getClassLoader()
							.getResource("Red Wood #" + stage[Draw.getNeedToCutTreeNumber()] + ".png"));

					if (stage[Draw.getNeedToCutTreeNumber()] < 3) {
						stage[Draw.getNeedToCutTreeNumber()] += 1;
					}

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Main.log.info(e.getMessage());
			}

			redWoodRender = false;
		}

		return redWood;
	}

	private static int[] posX() {
		for (int i = 0; i < 3; i++) {
			x[i] = (int) (Math.random() * 862);
		}
		return x;
	}

	private static int[] posY() {
		for (int i = 0; i < 3; i++) {
			y[i] = (int) (Math.random() * 750);
		}
		return y;
	}
}
