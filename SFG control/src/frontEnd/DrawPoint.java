package frontEnd;
import java.awt.Color;
import java.awt.Graphics;

public class DrawPoint {
	
	private int x;
	private int y;
	private int height ;
	private int width;

	public DrawPoint(int x, int y) {
		this.x = x;
		this.y = y;
		this.height=15;
		this.width=15;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void Draw(Graphics g) {
		g.setColor(Color.PINK);
		g.fillOval(x + width / 2, y - height / 2, width, height);
	}
}

