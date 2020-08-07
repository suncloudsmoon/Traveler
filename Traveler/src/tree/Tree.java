package tree;

import java.awt.Image;

public interface Tree {
	public Image[] getTreeImg();

	public Image getTreeFallingAnimation();

	public Image getTreeIcon();

	public void changeTreeForCutting();

	public int[] getTreeEstimatedPrice();

}
