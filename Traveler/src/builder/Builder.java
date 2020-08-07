package builder;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public interface Builder {

	public BufferedImage[] getBuilderImg();

	public void startBuilderWalk(Graphics g);

	public void setBuilderMood(int mood);
}
