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

package wanderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Wanderer extends JPanel {

	public static int wandererNum = (int) (Math.random() * 8) + 2;

	private static BufferedImage[] wanderderLeft = new BufferedImage[wandererNum];
	private static BufferedImage[] wanderderRight = new BufferedImage[wandererNum];

	private BufferedImage[] wandererLeftRendered = wandererLeftImageRender();

	// NEW
	private static int[] wandererX = posX();
	private static int[] wandererY = posY();

	// Calculation Values

	public static int[] finalX = new int[wandererNum];
	public static int[] finalY = new int[wandererNum];

	public static int[] previousWandererX = new int[wandererNum];
	public static int[] previousWandererY = new int[wandererNum];

	public static int[] vx = new int[wandererNum]; // absolute value
	public static int[] vy = new int[wandererNum];

	public static boolean[] timeToCheck = new boolean[wandererNum];
	private static boolean[] targetXReached = new boolean[wandererNum];
	private static boolean[] targetYReached = new boolean[wandererNum];

	// These 3 must be reset before another operation!
	private static int[] reachedCounterX = new int[wandererNum];
	private static int[] reachedCounterY = new int[wandererNum];
	public static boolean[] doit = new boolean[wandererNum];

	// Used only once!
	public static boolean startsignal = false;
	public static boolean startsignal2 = false;

	private static int generatorCounter = 0;

	public static boolean wandererSelected = false;
	public static int wandererSelectedNum;

	public static void setPos(int arrayNum, int x, int y) {
		previousWandererX[arrayNum] = wandererX[arrayNum];
		previousWandererY[arrayNum] = wandererY[arrayNum];
		finalX[arrayNum] = x;
		finalY[arrayNum] = y;
		System.out.println("Pos X #" + arrayNum + ": " + x + ", " + "Pos Y #" + arrayNum + ": " + y);
	}

	public void startWandererWalk(Graphics g) {
		if (startsignal2) {
			for (int z = 0; z < wandererNum; z++) {
				if (vx[z] == 0 && vy[z] == 0) {

					if (generatorCounter == wandererNum / 2) {

						for (int y = 0; y < wandererNum; y++) {
							timeToCheck[y] = true;
							reachedCounterX[y] = 0;
							reachedCounterY[y] = 0;
							doit[y] = true;
							vx[y] = 0;
							vy[y] = 0;
							setPos(y, Wanderer.posX()[y], Wanderer.posY()[y]);

						}

						// System.out.println("Generating Random Positions for Wanderer!");

						generatorCounter = 0;
						break;
					}
					generatorCounter++;
				}
			}
			startsignal2 = false;
		}

		if (startsignal) {
			for (int i = 0; i < wandererNum; i++) {
				if (finalX[i] == wandererX[i] && reachedCounterX[i] == 0) {
					targetXReached[i] = true;
					timeToCheck[i] = true;
					reachedCounterX[i]++;
				}

				if (finalY[i] == wandererY[i] && reachedCounterY[i] == 0) {
					targetYReached[i] = true;
					timeToCheck[i] = true;
					reachedCounterY[i]++;
				}

				if (timeToCheck[i]) {
					if (finalX[i] - previousWandererX[i] > finalY[i] - previousWandererY[i]) {
						// start with X

						if (doit[i]) {
							if (finalX[i] - previousWandererX[i] < 0) {
								vx[i] = -1;
							} else {
								vx[i] = 1;
							}
							doit[i] = false;
						}

						if (targetXReached[i]) {
							if (finalY[i] - previousWandererY[i] < 0) {
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

					} else if (finalY[i] - previousWandererY[i] > finalX[i] - previousWandererX[i]) {

						if (doit[i]) {
							if (finalY[i] - previousWandererY[i] < 0) {
								vy[i] = -1;
							} else {
								vy[i] = 1;
							}
							doit[i] = false;
						}

						if (targetYReached[i]) {
							if (finalX[i] - previousWandererX[i] < 0) {
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

							if (finalX[i] - previousWandererX[i] < 0) {
								vx[i] = -1;
							} else {
								vx[i] = 1;
							}
							doit[i] = false;
						}

						if (targetXReached[i]) {
							if (finalY[i] - previousWandererY[i] < 0) {
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

				if (wandererSelected) {
					g.setColor(Color.blue);
					g.drawRect(wandererX[wandererSelectedNum] - 5, wandererY[wandererSelectedNum] - 5, 45, 45);
				}

				// System.out.println("vx #" + i + ": " + vx[i] + " vy #" + i + ": " + vy[i]);
				g.drawImage(Wanderer.wanderderLeft[i], wandererX[i] += vx[i], wandererY[i] += vy[i], null);

			}
		}
	}

	public static void stopWanderer(int id) {
		timeToCheck[id] = false;
		vx[id] = 0;
		vy[id] = 0;
	}

	public static void startWanderer(int id) {
		timeToCheck[id] = true;
	}

	private BufferedImage[] wandererLeftImageRender() {
		try {
			wanderderLeft[0] = ImageIO.read(getClass().getClassLoader().getResource("Left Wanderer.png"));
			for (int i = 1; i < wandererNum; i++) {
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
			for (int i = 1; i < wandererNum; i++) {
				wanderderRight[i] = wanderderRight[0];
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wanderderRight;

	}

	public static int[] posX() {
		int[] x = new int[wandererNum];
		for (int i = 0; i < wandererNum; i++) {
			x[i] = (int) (Math.random() * 862);
		}
		return x;

	}

	public static int[] posY() {
		int[] y = new int[wandererNum];
		for (int i = 0; i < wandererNum; i++) {
			y[i] = (int) (Math.random() * 760);
		}
		return y;
	}

	public BufferedImage[] getWandererLeftRendered() {
		return wandererLeftRendered;
	}

	public static int[] getWandererX() {
		return wandererX;
	}

	public static int[] getWandererY() {
		return wandererY;
	}

}
