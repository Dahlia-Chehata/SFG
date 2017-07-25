package IbackEnd;

import java.util.LinkedList;

import frontEnd.DrawEdge;

public interface INode {

	public String getName();
	public void setName(String name);
	public int getnum();
	public void setnum(int num);
	public boolean isVisited();
	public void setVisited(boolean visited);
	public INode getParent();
	public void setParent(INode parent);
	public LinkedList<DrawEdge> getEdges();
	public void addEdges(DrawEdge edge);
}
