package wanderer;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JSlider;

import player.Inventory;

public class Trade {

	public static int timeEra = (int) (Math.random() * 1800);

	public static JSlider tradeAmount = new JSlider(Inventory.item[0]/10, Inventory.item[0], Inventory.item[0]/10);

	public static boolean userTradeBoxOpen = false;
	public static boolean wandererTradeBoxOpen = false;
	public static boolean tradeAmountInitialization = true;

	public static int[] numX = { -40, 60 };
	public static int[] numY = { -70, -70 };

	private WInventory wInventory = new WInventory();

	public BufferedImage getTradeBox() {
		if (tradeAmountInitialization) {
			// tradeAmount.setMajorTickSpacing(Inventory.item[0]/10);
			tradeAmount.setSnapToTicks(true);
			tradeAmount.setPaintTicks(true);

			tradeAmount.setEnabled(false);
			tradeAmountInitialization = false;
		}

		return wInventory.tradeBox();

	}

	protected class WInventory {

		private BufferedImage tradeBox = null;
		private boolean tradeBoxLoaded = false;

		protected BufferedImage tradeBox() {
			if (tradeBoxLoaded == false) {
				try {
					tradeBox = ImageIO.read(getClass().getClassLoader().getResource("Wanderer Trading Interface.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tradeBoxLoaded = true;
			}
			return tradeBox;

		}
	}

}
