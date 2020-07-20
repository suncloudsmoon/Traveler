import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {

	private static BufferedImage[] player = new BufferedImage[2];

	// Random player positions are generated each time the game loads up...
	public static final int playerPosX = (int) (Math.random() * 855) + 10;
	public static final int playerPosY = (int) (Math.random() * 730) + 10;

	private static boolean playerImageRender = true;

	public static Image[] playerImage() {

		if (playerImageRender == true) {
			try {
				player[0] = ImageIO.read(new File("Left Player Position.png"));
				player[1] = ImageIO.read(new File("Right Player Position.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			playerImageRender = false;
		}
		return player;
	}
}
