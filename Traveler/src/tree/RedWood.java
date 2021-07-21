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

package tree;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import main.Draw;
import main.SystemTimer;
import mainmenu.MainMenu;

public class RedWood implements Tree {

	public static Image[] redWood = new Image[3];

	private static int[] x = new int[4];
	private static int[] y = new int[4];

	private static final int[] treeX = posX();
	private static final int[] treeY = posY();

	private static ArrayList<Integer> stage = new ArrayList<>();
	private static int treeRenderOncecounter = 0;
	
	private static AudioInputStream wood;
	private static Clip woodPlay;

	private static Image treeFalling;
	private static boolean redWoodRender = true;
	private static boolean redWoodAnimationAgain = true;

	private static SystemTimer systemTimer1 = new SystemTimer();

	public static int getStage(int arrayNum) {
		return RedWood.stage.get(arrayNum);
	}

	public static void setStage(int arrayNum, int addNum) {
		stage.set(arrayNum, stage.get(arrayNum) + addNum);
	}

	public static void setRedWoodRender(boolean redWoodRender) {
		RedWood.redWoodRender = redWoodRender;
	}

	public static final int[] getTreeX() {
		return treeX;
	}

	public static final int[] getTreeY() {
		return treeY;
	}

	public RedWood() {
		for (int i = 0; i < 5; i++) {
			stage.add(1);
		}
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

					if (stage.get(Draw.getNeedToCutTreeNumber()) < 4) {
						redWood[Draw.getNeedToCutTreeNumber()] = ImageIO.read(getClass().getClassLoader()
								.getResource("Red Wood #" + stage.get(Draw.getNeedToCutTreeNumber()) + ".png"));
						setStage(Draw.getNeedToCutTreeNumber(), 1);
						if (stage.get(Draw.getNeedToCutTreeNumber()) == 4) {
							SystemTimer.setTimerFinished(false);
							systemTimer1.setTimer(900, 900);
						}
						playRedWoodMusic();

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

	protected void playRedWoodMusic() {
		try {
			wood = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource("Wood Cutting.wav"));
			woodPlay = AudioSystem.getClip();
			woodPlay.open(wood);
			woodPlay.start();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if (redWoodAnimationAgain) {
			treeFalling = Toolkit.getDefaultToolkit()
					.getImage(getClass().getClassLoader().getResource("Red Wood Animation.gif"));

			redWoodAnimationAgain = false;
		}
		return treeFalling;
	}

	@Override
	public void changeTreeForCutting() {
		System.out.println("Timer Finished! lol");
		try {
			redWood[Draw.getNeedToCutTreeNumber()] = ImageIO
					.read(getClass().getClassLoader().getResource("Red Wood #4.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setStage(Draw.getNeedToCutTreeNumber(), 1);
		Draw.setBackgroundRenderNeeded(true);
	}

	@Override
	public Image getTreeIcon() {
		Image redWoodLogs = null;
		try {
			redWoodLogs = ImageIO.read(getClass().getClassLoader().getResource("Log.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return redWoodLogs;
	}

}
