import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hut {

	private static Image hut;
	private static int[] x = new int[3]; // Access this by x[1] etc
	private static int[] y = new int[3];

	public Image hutImage() {
		try {
			hut = ImageIO.read(getClass().getClassLoader().getResource("Hut.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hut;
	}

	// Make sure the huts don't interfere with each other!
	public static int[] posX() {
		for (int i = 0; i < 3; i++) {
			x[i] = (int) (Math.random() * 750);
		}
		return x;
	}

	public static int[] posY() {
		for (int i = 0; i < 3; i++) {
			y[i] = (int) (Math.random() * 730);
		}
		return y;
	}

}
