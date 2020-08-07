package builder;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import road.DirtRoad;

public class RoadBuilder extends DirtRoad implements Builder {

	// Store this in a text file!
	public static final int builderNum = (int) (Math.random() * 4) + 1;


	private static BufferedImage[] builder = new BufferedImage[builderNum];

	@Override
	public BufferedImage[] getBuilderImg() {
		try {
			for (int i = 0; i < builderNum; i++) {
				builder[i] = ImageIO.read(getClass().getClassLoader().getResource("Builder.png"));

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return builder;
	}

	public static int[] posX() {
		int[] x = new int[builderNum];
		for (int i = 0; i < builderNum; i++) {
			x[i] = (int) (Math.random() * 862);
		}
		return x;

	}

	public static int[] posY() {
		int[] y = new int[builderNum];
		for (int i = 0; i < builderNum; i++) {
			y[i] = (int) (Math.random() * 760);
		}
		return y;
	}

	private static BufferedImage[] builderLeft = new BufferedImage[builderNum];
	private static BufferedImage[] builderRight = new BufferedImage[builderNum];

	private BufferedImage[] builderLeftRendered = getBuilderImg();

	// NEW
	private static int[] builderX = posX();
	private static int[] builderY = posY();

	// Calculation Values

	private static int[] finalX = new int[builderNum];
	private static int[] finalY = new int[builderNum];

	private static int[] previousBuilderX = new int[builderNum];
	private static int[] previousBuilderY = new int[builderNum];

	public static int[] vx = new int[builderNum]; // absolute value
	public static int[] vy = new int[builderNum];

	public static boolean[] timeToCheck = new boolean[builderNum];
	private static boolean[] targetXReached = new boolean[builderNum];
	private static boolean[] targetYReached = new boolean[builderNum];

	// These 3 must be reset before another operation!
	private static int[] reachedCounterX = new int[builderNum];
	private static int[] reachedCounterY = new int[builderNum];
	public static boolean[] doit = new boolean[builderNum];

	// Used only once!
	public static boolean startsignal = false;
	public static boolean startsignal2 = false;

	private static int generatorCounter = 0;

	private static boolean builderSelected = false;
	private static int builderSelectedNum;

	public static void setPos(int arrayNum, int x, int y) {
		previousBuilderX[arrayNum] = RoadBuilder.builderX[arrayNum];
		previousBuilderY[arrayNum] = RoadBuilder.builderY[arrayNum];
		finalX[arrayNum] = x;
		finalY[arrayNum] = y;
		System.out.println(
				"RoadBuilder Pos X #" + arrayNum + ": " + x + ", " + "RoadBuilder Pos Y #" + arrayNum + ": " + y);
	}

	@Override
	public void startBuilderWalk(Graphics g) {
		if (startsignal2) {
			for (int z = 0; z < builderNum; z++) {
				if (vx[z] == 0 && vy[z] == 0) {

					if (generatorCounter == builderNum / 2) {

						for (int y = 0; y < builderNum; y++) {
							timeToCheck[y] = true;
							reachedCounterX[y] = 0;
							reachedCounterY[y] = 0;
							doit[y] = true;
							vx[y] = 0;
							vy[y] = 0;
							setPos(y, posX()[y], posY()[y]);

						}

						generatorCounter = 0;
						break;
					}
					generatorCounter++;
				}
			}
			startsignal2 = false;
		}

		if (startsignal) {
			for (int i = 0; i < builderNum; i++) {
				if (finalX[i] == builderX[i] && reachedCounterX[i] == 0) {
					targetXReached[i] = true;
					timeToCheck[i] = true;
					reachedCounterX[i]++;
				}

				if (finalY[i] == builderY[i] && reachedCounterY[i] == 0) {
					targetYReached[i] = true;
					timeToCheck[i] = true;
					reachedCounterY[i]++;
				}

				if (timeToCheck[i]) {
					if (finalX[i] - previousBuilderX[i] > finalY[i] - previousBuilderY[i]) {
						// start with X

						if (doit[i]) {
							if (finalX[i] - previousBuilderX[i] < 0) {
								vx[i] = -1;
							} else {
								vx[i] = 1;
							}
							doit[i] = false;
						}

						if (targetXReached[i]) {
							if (finalY[i] - previousBuilderY[i] < 0) {
								vx[i] = 0;
								vy[i] = -1;
							} else {
								vx[i] = 0;
								vy[i] = 1;
							}
							targetXReached[i] = false;
						}

						if (targetYReached[i]) {
							vy[i] = 0;
							targetYReached[i] = false;
							startsignal2 = true;

						}

					} else if (finalY[i] - previousBuilderY[i] > finalX[i] - previousBuilderX[i]) {

						if (doit[i]) {
							if (finalY[i] - previousBuilderY[i] < 0) {
								vy[i] = -1;
							} else {
								vy[i] = 1;
							}
							doit[i] = false;
						}

						if (targetYReached[i]) {
							if (finalX[i] - previousBuilderX[i] < 0) {
								vy[i] = 0;
								vx[i] = -1;
							} else {
								vy[i] = 0;
								vx[i] = 1;
							}
							targetYReached[i] = false;
						}

						if (targetXReached[i]) {
							vx[i] = 0;
							targetXReached[i] = false;
							startsignal2 = true;

						}

					} else {

						if (doit[i]) {

							if (finalX[i] - previousBuilderX[i] < 0) {
								vx[i] = -1;
							} else {
								vx[i] = 1;
							}
							doit[i] = false;
						}

						if (targetXReached[i]) {
							if (finalY[i] - previousBuilderY[i] < 0) {
								vx[i] = 0;
								vy[i] = -1;
							} else {
								vx[i] = 0;
								vy[i] = 1;
							}
							targetXReached[i] = false;
						}

						if (targetYReached[i]) {
							vy[i] = 0;
							targetYReached[i] = false;
							startsignal2 = true;

						}

					}
					timeToCheck[i] = false;
				}

				/*
				 * if (wandererSelected) { g.setColor(Color.blue);
				 * g.drawRect(wandererX[wandererSelectedNum] - 5, wandererY[wandererSelectedNum]
				 * - 5, 45, 45); }
				 */

				// System.out.println("vx #" + i + ": " + vx[i] + " vy #" + i + ": " + vy[i]);
				g.drawImage(builderLeftRendered[i], builderX[i] += vx[i], builderY[i] += vy[i], null);

			}
		}
	}

	@Override
	public void setBuilderMood(int mood) {
		// TODO Auto-generated method stub

	}

}
