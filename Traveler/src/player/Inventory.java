package player;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Inventory {

	private BufferedImage textBox = null;
	private static boolean textBoxImgLoaded = false;

	private BufferedImage tradeTextBox = null;
	private static boolean tradeTextBoxLoaded = false;

	public static int[] slotX = { -55 };
	public static int[] slotY = { -80 };

	public static int[] item = { 100 }; // item[0] is for wood!
	
	public static int selectedItem = 0;
	
	public static int money = 0;

	public BufferedImage getTextBoxImg() {
		if (textBoxImgLoaded == false) {

			try {
				textBox = ImageIO.read(getClass().getClassLoader().getResource("Inventory Text Box.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			textBoxImgLoaded = true;
		}
		return textBox;

	}

	public BufferedImage getUserTradeTextBoxImg() {
		if (tradeTextBoxLoaded == false) {
			try {
				tradeTextBox = ImageIO.read(getClass().getClassLoader().getResource("User Trade Text Box.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tradeTextBoxLoaded = true;
		}

		return tradeTextBox;

	}
}
