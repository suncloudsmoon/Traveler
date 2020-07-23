import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Wanderer extends JPanel {

	private static BufferedImage[] wanderderLeft = new BufferedImage[3];
	private static BufferedImage[] wanderderRight = new BufferedImage[3];

	private BufferedImage[] wandererLeftRendered = wandererLeftImageRender();
	private BufferedImage[] wandererRightRendered = wandererRightImageRender();
	private static int[] wandererPosX = posX();
	private static int[] wandererPosY = posY();

	// All the combinations of velocity!
	private static int[] velocityComboX = { 0, 0, 0 }; // 2 and -2 is for some crazy hut people
	private static int[] velocityComboY = { 0, 0, 0 };

	// Coordination Targets for each of the objects
	private static int[] targetCoordX = { 0, 0, 0 };
	private static int[] targetCoordY = { 0, 0, 0 };

	public static boolean[] moveWanderNum = new boolean[3];

	// Counters for special purposes
	protected static int[] targetPosCounter = { 0, 0, 0 };

	public BufferedImage[] getWandererLeftRendered() {
		return wandererLeftRendered;
	}

	public BufferedImage[] getWandererRightRendered() {
		return wandererRightRendered;
	}

	public static int[] getWandererPosX() {
		return wandererPosX;
	}

	public void setWandererPosX(int[] wandererPosX) {
		this.wandererPosX = wandererPosX;
	}

	public static int[] getWandererPosY() {
		return wandererPosY;
	}

	public void setWandererPosY(int[] wandererPosY) {
		this.wandererPosY = wandererPosY;
	}

	public static int[] getVelocityComboX() {
		return velocityComboX;
	}

	public static int[] getVelocityComboY() {
		return velocityComboY;
	}

	private static int[] wx = new int[3];
	private static int[] wy = new int[3];

	protected static boolean[] stopObjectPosX = new boolean[3];
	private static boolean[] stopObjectPosY = new boolean[3];
	private static int[] objectNum = new int[3];
	private static int[] velocity = new int[3];
	
	private static int randCounter = 0;

	public static void setPosition(int objectNum, int velocity, int x, int y) {
		wx[objectNum] = wandererPosX[objectNum];
		wy[objectNum] = wandererPosY[objectNum];
		targetCoordX[objectNum] = x;
		targetCoordY[objectNum] = y;
		Wanderer.objectNum[objectNum] = objectNum;
		Wanderer.velocity[objectNum] = velocity;

	}

	/*
	 * Make sure that only one to two hut people walk at a time and the other one
	 * just stays still for at least one minute And they switch the idle roles every
	 * minute!
	 */

	// Make a background graphics g and a new BufferedImage for the idle images
	protected static boolean[] coordCheck = { true, true, true };

	public void startWandererWalk(Graphics g) {
		
		for (int i = 0; i < 3; i++) {
			if (!(stopObjectPosX[i] && stopObjectPosY[i])
					&& (wandererPosX[i] > 0 && wandererPosX[i] < 867 && wandererPosY[i] > 0 && wandererPosY[i] < 765)) {
				if (wandererPosX[i] == targetCoordX[i]) {
					stopObjectPosX[i] = true;
					coordCheck[i] = true;

				}
				if (wandererPosY[i] == targetCoordY[i]) {
					stopObjectPosY[i] = true;
					coordCheck[i] = true;
				}

				if (i == objectNum[i] && coordCheck[i] == true) {
					if (targetCoordX[i] - wx[i] > targetCoordY[i] - wy[i]) {
						// Starts moving in the x direction
						if (!stopObjectPosX[i]) {
							if (!(targetCoordX[i] - wx[i] < 0)) {
								velocityComboX[i] = velocity[i];
							} else {
								velocityComboX[i] = -velocity[i];
							}

						} else {
							velocityComboX[i] = 0;
							if (!(targetCoordY[i] - wy[i] < 0)) {
								velocityComboY[i] = velocity[i];
							} else {
								velocityComboY[i] = -velocity[i];
							}
						}

					} else if (targetCoordX[i] - wx[i] < targetCoordY[i] - wy[i]) {
						// Starts moving in the y direction
						if (!stopObjectPosY[i]) {
							if (!(targetCoordY[i] - wy[i] < 0)) {
								velocityComboY[i] = velocity[i];
							} else {
								velocityComboY[i] = -velocity[i];
							}

						} else {
							velocityComboY[i] = 0;
							if (!(targetCoordX[i] - wx[i] < 0)) {
								velocityComboX[i] = velocity[i];
							} else {
								velocityComboX[i] = -velocity[i];
							}

						}
					} else {
						// Starts moving in the x direction
						if (!stopObjectPosX[i]) {
							if (!(targetCoordX[i] - wx[i] < 0)) {
								velocityComboX[i] = velocity[i];
							} else {
								velocityComboX[i] = -velocity[i];
							}
						} else {
							velocityComboX[i] = 0;
							if (!(targetCoordY[i] - wy[i] < 0)) {
								velocityComboY[i] = velocity[i];
							} else {
								velocityComboY[i] = -velocity[i];
							}
						}

					}
					coordCheck[i] = false;
				}

				g.drawImage(wandererLeftRendered[i], wandererPosX[i] += velocityComboX[i],
						wandererPosY[i] += velocityComboY[i], null);
			} else {
				// Move to background
				if (targetPosCounter[i] == 0) {
					Background.setWandererStopped(true);
					moveWanderNum[i] = true;
					Draw.setBackgroundRenderNeeded(true);
					Background.backgroundImageRender(g);
					
					for (int j = 0; j < 3; j++) {
						if (moveWanderNum[j] == true) {
							randCounter++;
							if (j == 2) {
								Main.coordUpdate = true;
							}
						}
					}
					targetPosCounter[i]++;
				}
			}
		}

	}

	private BufferedImage[] wandererLeftImageRender() {
		try {
			wanderderLeft[0] = ImageIO.read(getClass().getClassLoader().getResource("Left Wanderer.png"));
			for (int i = 1; i < 3; i++) {
				wanderderLeft[i] = wanderderLeft[0];
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wanderderLeft;

	}

	private BufferedImage[] wandererRightImageRender() {
		try {
			wanderderRight[0] = ImageIO.read(getClass().getClassLoader().getResource("Right Wanderer.png"));
			for (int i = 1; i < 3; i++) {
				wanderderRight[i] = wanderderRight[0];
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wanderderRight;

	}

	public static int[] posX() {
		int[] x = new int[3];
		for (int i = 0; i < 3; i++) {
			x[i] = (int) (Math.random() * 867);
		}
		return x;

	}

	public static int[] posY() {
		int[] y = new int[3];
		for (int i = 0; i < 3; i++) {
			y[i] = (int) (Math.random() * 765);
		}
		return y;
	}

}
