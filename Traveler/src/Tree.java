import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tree {

	private static BufferedImage[] redWood = new BufferedImage[3];

	private static int[] x = new int[4];
	private static int[] y = new int[4];

	private static int[] treeX = posX();
	private static int[] treeY = posY();
	
	private static int[] stage = {1,1,1,1};
	private static int treeRenderOncecounter = 0;

	// private static ArrayList<Tree> treeImgEditor = new ArrayList<>();

	public static boolean redWoodRender = true;

	public static int[] getTreeX() {
		return treeX;
	}

	public static void setTreeX(int[] treeX) {
		Tree.treeX = treeX;
	}

	public static int[] getTreeY() {
		return treeY;
	}

	public static void setTreeY(int[] treeY) {
		Tree.treeY = treeY;
	}

	// Create different types of trees
	public Image[] getRedWoodImg() {
		if (redWoodRender == true) {
			try {
				if (treeRenderOncecounter == 0) {
					for (int i = 0; i < 3; i++) {
						redWood[i] = ImageIO.read(getClass().getClassLoader().getResource("Red Wood.png"));
					}
					treeRenderOncecounter++;
				}
				
				if (Draw.isKeyPressedI) {
					redWood[Draw.needToCutTreeNumber] = ImageIO.read(getClass().getClassLoader().getResource("Red Wood #" + stage[Draw.needToCutTreeNumber] + ".png"));
					
					if (stage[Draw.needToCutTreeNumber] < 3) {
						stage[Draw.needToCutTreeNumber] += 1;
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

	public static int[] posX() {
		for (int i = 0; i < 3; i++) {
			x[i] = (int) (Math.random() * 895);
		}
		return x;
	}

	public static int[] posY() {
		for (int i = 0; i < 3; i++) {
			y[i] = (int) (Math.random() * 770);
		}
		return y;
	}
}
