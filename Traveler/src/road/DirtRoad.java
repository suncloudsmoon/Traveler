/*
* Copyright (C) 2020 Ganesha Ajjampura
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

package road;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DirtRoad implements Road {

	public static final int randRoads = (int) (Math.random() * 4) + 1;
	private static final int verticalOrHorizontal = (int) (Math.random() * 2) + 1;

	public static final int[] dirtRoadX = posX();
	public static final int[] dirtRoadY = posY();

	private static BufferedImage dirtRoad;

	@Override
	public BufferedImage getRoadImg() {
		try {
			dirtRoad = ImageIO.read(getClass().getClassLoader().getResource("Dirt Road.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dirtRoad;
	}

	private static final int[] posX() {
		int[] x = new int[randRoads];
		x[0] = (int) (Math.random() * 862);
		if (verticalOrHorizontal == 1) {
			for (int i = 1; i < randRoads; i++) {
				x[i] += x[0] + 35;
			}

		} else {
			for (int i = 0; i < randRoads; i++) {
				x[i] = x[0];
			}
		}
		return x;

	}

	private static final int[] posY() {
		int[] y = new int[randRoads];
		y[0] = (int) (Math.random() * 760);
		if (verticalOrHorizontal == 2) {
			for (int i = 1; i < randRoads; i++) {
				y[i] += y[0] + 20;
			}
		} else {
			for (int i = 1; i < randRoads; i++) {
				y[i] = y[0];
			}
		}
		
		return y;
	}

	@Override
	public void getEstimatedRoadPrice() {
		// TODO Auto-generated method stub

	}

}
