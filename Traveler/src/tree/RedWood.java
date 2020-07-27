package tree;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import main.Draw;
import main.SystemTimer;
import mainmenu.MainMenu;

public class RedWood implements Tree {

	public static Image[] redWood = new Image[3];

	private static int[] x = new int[4];
	private static int[] y = new int[4];

	private static final int[] treeX = posX();
	private static final int[] treeY = posY();

	private static int[] stage = { 1, 1, 1, 1 };
	private static int treeRenderOncecounter = 0;

	private static Image treeFalling;
	private static boolean redWoodRender = true;
	private static boolean redWoodAnimationAgain = true;

	private static SystemTimer systemTimer1 = new SystemTimer();

	public static void setRedWoodRender(boolean redWoodRender) {
		RedWood.redWoodRender = redWoodRender;
	}

	public static final int[] getTreeX() {
		return treeX;
	}

	public static final int[] getTreeY() {
		return treeY;
	}

	// Create different types of trees
	public Image[] getTreeImg() {
		if (redWoodRender) {
			try {
				if (treeRenderOncecounter == 0) {
					for (int i = 0; i < 3; i++) {
						redWood[i] = ImageIO.read(getClass().getClassLoader().getResource("Red Wood.png"));
					}
					treeRenderOncecounter++;
				}

				if (Draw.isKeyPressedI()) {

					if (stage[Draw.getNeedToCutTreeNumber()] < 4) {
						redWood[Draw.getNeedToCutTreeNumber()] = ImageIO.read(getClass().getClassLoader()
								.getResource("Red Wood #" + getStage()[Draw.getNeedToCutTreeNumber()] + ".png"));
						stage[Draw.getNeedToCutTreeNumber()]++;

						if (stage[Draw.getNeedToCutTreeNumber()] == 4) {
							System.out.println("Stage: " + RedWood.getStage()[Draw.getNeedToCutTreeNumber()]);
							redWood[Draw.getNeedToCutTreeNumber()] = getTreeFallingAnimation();

						}

					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				MainMenu.log.info(e.getMessage());
			}

			redWoodRender = false;
		}

		return redWood;
	}

	private static final int[] posX() {
		for (int i = 0; i < 3; i++) {
			x[i] = (int) (Math.random() * 862);
		}
		return x;
	}

	private static final int[] posY() {
		for (int i = 0; i < 3; i++) {
			y[i] = (int) (Math.random() * 750);
		}
		return y;
	}

	@Override
	public int[] getTreeEstimatedPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	public Image getTreeFallingAnimation() {
		// if (redWoodAnimationAgain) {
		ImageIcon falling = new ImageIcon(getClass().getClassLoader().getResource("Red Wood Animation.gif"));
		treeFalling = falling.getImage();
		// redWoodAnimationAgain = false;
		// }
		return treeFalling;
	}

	public static int[] getStage() {
		return stage;
	}

	public static void setStage(int[] stage) {
		RedWood.stage = stage;
	}

}
