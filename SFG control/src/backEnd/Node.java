package backEnd;
import java.util.LinkedList;

import IbackEnd.*;
import frontEnd.DrawEdge;

public class Node implements INode {
 
	private String name;
	private int num;
	private boolean visited;
	private INode parent;
	protected LinkedList<DrawEdge> edges;
	
	public Node() {
	}
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name=name;
	}
	@Override
	public int getnum() {
		return num;
	}
	@Override
	public void setnum(int num) {
		this.num=num;
	}

	@Override
	public boolean isVisited() {
		return visited;
	}

	@Override
	public void setVisited(boolean visited) {
		this.visited=visited;
	}

	@Override
	public INode getParent() {
		return parent;
	}

	@Override
	public void setParent(INode parent) {
		this.parent=parent;
	}
	@Override
	public LinkedList<DrawEdge> getEdges() {
		return edges;
	}

	@Override
	public void addEdges(DrawEdge edge) {
		edges.add(edge);
		
	}
}
