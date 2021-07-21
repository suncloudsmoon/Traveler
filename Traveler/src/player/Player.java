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

package player;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mainmenu.MainMenu;

public class Player {

	// Random player positions are generated each time the game loads up...
	public static final int playerPosX = (int) (Math.random() * 855) + 10;
	public static final int playerPosY = (int) (Math.random() * 730) + 10;

	private static int playerSpeed = 5;

	private BufferedImage[] playerImage = playerImageRender();

	public BufferedImage[] getPlayerImage() {
		return playerImage;
	}

	private BufferedImage[] playerImageRender() {
		BufferedImage[] player = new BufferedImage[2];
		try {
			player[0] = ImageIO.read(getClass().getClassLoader().getResource("Left Player Position.png"));
			player[1] = ImageIO.read(getClass().getClassLoader().getResource("Right Player Position.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MainMenu.log.info(e.getMessage());
		}

		return player;
	}

	public static int getPlayerSpeed() {
		return playerSpeed;
	}

	public static void setPlayerSpeed(int playerSpeed) {
		Player.playerSpeed = playerSpeed;
	}
}
