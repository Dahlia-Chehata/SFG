package backEnd;

import IbackEnd.*;

public class Edge implements IEdge{
    protected INode srcNode;
    protected INode endNode;
    protected int num;
    protected double gain;
    
    public Edge(INode srcNode, int num) {
		this.srcNode = srcNode;
		this.num = num;
	}
	@Override
	public INode getEndNode() {
		return endNode;
	}

	@Override
	public void setEndNode(INode endNode) {
		this.endNode=endNode;
	}

	@Override
	public INode getsrcNode() {
		return srcNode;
	}

	@Override
	public double getGainValue() {
		return gain;
	}

	@Override
	public void setGainValue(double gain) {
		this.gain=gain;
	}

}
