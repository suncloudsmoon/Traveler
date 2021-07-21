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
