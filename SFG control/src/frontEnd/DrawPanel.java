package frontEnd;
import backEnd.*;
import IbackEnd.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel {

	private ArrayList<DrawNode> nodes;
	private DrawNode newNode;
	private DrawNode source;
	private DrawNode sink;
	private Color color;
	private Color selectedColor;
	
	public DrawPanel() {
		nodes = new ArrayList<>();
		color = Color.red;
		selectedColor = Color.pink;
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	private boolean yRanged(int y, DrawNode node) {
		return (node.getyPosition() <= y && node.getyPosition() + node.getHeight() >= y);
	}

	private boolean xRanged(int x, DrawNode node) {
		return (node.getxPosition() <= x && node.getxPosition() + node.getWidth() >= x);
	}
    public void clearNodes(){
    	nodes.clear();
    }
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < nodes.size(); i++) 
			nodes.get(i).Draw(g);
		if (newNode != null)
			newNode.Draw(g);
	}

	public void createNode() {
		newNode = new DrawNode();
		newNode.setColor(selectedColor);
	}
	public void setNode(int x, int y) {
		newNode.setxPosition(x);
		newNode.setyPosition(y);
	}
	public void addNode() {
		if (newNode != null) {
			newNode.setColor(color);
			newNode.setxPosition(newNode.getxPosition() + newNode.getWidth()
					- (newNode.getxPosition() % newNode.getWidth()));
			newNode.setyPosition(newNode.getyPosition() + newNode.getHeight()
					- (newNode.getyPosition() % newNode.getHeight()));
			nodes.add(newNode);
			newNode = null;
			repaint();
		}
	}
	public Edge addEdge(double value) {
		DrawEdge edge = (DrawEdge) source.getEdges().getLast();
		((DrawEdge) edge).setGainValue(value);
		edge.setEndNode(sink);
		source.setColor(color);
		sink.setColor(color);
		source = null;
		sink = null;
		return edge;
	}

	public void cancelNodeAddition() {
		newNode = null;
		repaint();
	}
	
	public INode select(int x, int y) {
		for (int i = 0; i < nodes.size(); i++) {
			if (xRanged(x, nodes.get(i)) && yRanged(y, nodes.get(i))) {
				nodes.get(i).setColor(selectedColor);
				if (source == null)
					return source = nodes.get(i);
				return sink = nodes.get(i);
			}
		}
		return null;
	}

	
	public boolean isThereSelectedNode() {
		return source != null;
	}
	public boolean isThereNewNode() {
		return newNode != null;
	}

	public void setSelectedNodeToNull() {
		if (source != null) {
			source.setColor(color);
			source = null;
		}

		if (sink != null) {
			sink.setColor(color);
			sink = null;
		}
	}
	public Color getDefaultColor() {
		return color;
	}
	public ArrayList<DrawNode> getNodes() {
		return nodes;
	}

	public INode getfirstSelectedNode() {
		return source;
	}

	public INode getSecondSelectedNode() {
		return sink;
	}
}

