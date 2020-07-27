package player;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Inventory {
	
	private BufferedImage textBox = null;
	private static boolean textBoxImgLoaded = false;
	
	public BufferedImage getTextBoxImg() {
		if (textBoxImgLoaded == false) {
			
			try {
				textBox = ImageIO.read(getClass().getClassLoader().getResource("Text Box.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			textBoxImgLoaded = true;
		}
		return textBox;
		
	}
}
