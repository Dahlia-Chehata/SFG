package IbackEnd;

import java.util.LinkedList;

public interface IPath {
  
	public double getPathGain();
	public void setPathGain(double PathGain);
	public LinkedList<INode> getPath();
	public void setMaskingCode(int bitMask);
	public int getMaskingCode();
}
