package frontEnd;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.QuadCurve2D;

import IbackEnd.INode;
import backEnd.Edge;

public class DrawEdge extends Edge{

	private DrawPoint SourcePt;
	
	public DrawEdge(INode srcNode, int num) {
		super(srcNode, num);
		this.SourcePt = new DrawPoint(((DrawNode) this.srcNode).getxPosition(),
				((DrawNode) this.srcNode).getyPosition());
	}
	
	public void Draw(Graphics g) {
		
		if (srcNode != null && endNode != null) {
			Graphics2D graphics = (Graphics2D) g;
			graphics.setFont(new Font("Traditional Arabic", Font.BOLD, 18));
			if (srcNode==endNode){
				g.setColor(Color.cyan);
				graphics.drawArc(
		     ((DrawNode) srcNode).getxPosition() 
		    , ((DrawNode) srcNode).getyPosition()-20 , 40,40,0,360);	
				
				Path2D.Double path = new Path2D.Double();
				path.moveTo(((DrawNode) srcNode).getxPosition() + 12, ((DrawNode) srcNode).getyPosition() - 10);
				path.lineTo(((DrawNode) srcNode).getxPosition() , ((DrawNode) srcNode).getyPosition() + 12);
				path.lineTo(((DrawNode) srcNode).getxPosition()  - 12, ((DrawNode) srcNode).getyPosition() - 10);
				graphics.fill(path);
				
				} else {
					g.setColor(Color.white);
			graphics.draw(new QuadCurve2D.Double(((DrawNode) srcNode).getxPosition()
					+ ((DrawNode) srcNode).getWidth() / 2, ((DrawNode) srcNode).getyPosition()
					+ ((DrawNode) srcNode).getHeight() / 2, SourcePt.getX(),
					SourcePt.getY(), ((DrawNode) endNode).getxPosition()
							+ ((DrawNode) endNode).getWidth() / 2, ((DrawNode) endNode).getyPosition()
							+ ((DrawNode) endNode).getHeight() / 2));
			}
			g.drawString("Edge" + (num+1) + "," + gain, SourcePt.getX(),
					SourcePt.getY() + 10);

		}
	}

	public int getStartX() {
		return ((DrawNode) srcNode).getxPosition() + (((DrawNode) srcNode).getWidth() / 2);
	}

	public int getStartY() {
		return ((DrawNode) srcNode).getyPosition() + (((DrawNode) srcNode).getHeight() / 2);
	}

	public DrawPoint getSourcePt() {
		return this.SourcePt;
	}

	public void setSourcePtPosition(int x, int y) {
		this.SourcePt.setX(x);
		this.SourcePt.setY(y);
	}

}
