package tree;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public interface Tree {
	public Image getTreeFallingAnimation();
	public Image[] getTreeImg();
	public int[] getTreeEstimatedPrice();
	
}
