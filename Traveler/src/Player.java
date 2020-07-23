import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {

	// Random player positions are generated each time the game loads up...
	public static final int playerPosX = (int) (Math.random() * 855) + 10;
	public static final int playerPosY = (int) (Math.random() * 730) + 10;
	
	public static int playerSpeed = 1;

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
			Main.log.info(e.getMessage());
		}

		return player;
	}
}
