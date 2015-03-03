package group17.weatherviewer;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
	private Image image;
	
	public Background(String image) {
		this(new ImageIcon(image).getImage());
	}
	public Background(Image image) {
		this.image = image;
		Dimension size = new Dimension(image.getWidth(null),
				image.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}	
