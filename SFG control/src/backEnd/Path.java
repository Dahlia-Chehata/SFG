package backEnd;
import java.util.LinkedList;

import IbackEnd.*;

public class Path implements IPath {
   
	private LinkedList<INode> path;
	private double pathGain ;
	private int encodedCombinations;
	
	public Path(LinkedList<INode> path,double pathGain){
		this.path=path;
		this.pathGain=pathGain;
		encodedCombinations=bitMasking();
	}

	@Override
	public double getPathGain() {
		return pathGain;
	}

	@Override
	public void setPathGain(double pathGain) {
		this.pathGain=pathGain;
	}

	@Override
	public LinkedList<INode> getPath() {
		return path;
	}
	public int bitMasking() {
		int Mask=0;
		for (int i=0;i<path.size();i++)
			Mask|=(1<<path.get(i).getnum());
		return Mask;
	}

	@Override
	public void setMaskingCode(int bitMask) {
		encodedCombinations=bitMask;
	}

	@Override
	public int getMaskingCode() {
		return encodedCombinations;
	}
}
