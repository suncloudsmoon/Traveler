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

package wanderer;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JSlider;

import player.Inventory;

public class Trade {

	public static int timeEra = (int) (Math.random() * 1800);

	public static JSlider tradeAmount = new JSlider(Inventory.item[0] / 10, Inventory.item[0], Inventory.item[0] / 10);

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
