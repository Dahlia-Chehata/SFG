package IbackEnd;

public interface IEdge {
    
	/**
	 * get the end node
	 * @return
	 */
	public INode getEndNode();
	/**
	 * setter for the edge end node
	 * @param endNode
	 */
	public void setEndNode(INode endNode);
	/**
	 * getter for the start node of the edge
	 * @return
	 */
	public INode getsrcNode();
	/**
	 * get the gain value of the edge
	 * @return
	 */
	public double getGainValue();
	/**
	 * set the gain value of the edge
	 * @param gain
	 */
	public void setGainValue(double gain);
}
