package IbackEnd;

import java.util.ArrayList;
import java.util.LinkedList;

public interface IMason {
     /**
      * ForwardPaths getter
      * @param src
      * @param sink
      * @return
      */
	public LinkedList<IPath> getForwardPaths(INode src,INode sink);
	/**
	 * ForwardPaths getter 
	 * @return
	 */
	public LinkedList<IPath> getForwardPaths();
	/**
	 * Non Touching-Loops Combinations
	 * @return
	 */
	public ArrayList<ArrayList<int[]>> getNonTouchingLoopsCombinations();
	/**
	 * Individual Loops
	 * @return
	 */
	public LinkedList<IPath> getIndividualLoops();
	/**
	 * small deltas
	 * @return
	 */
	public ArrayList<Double> getForwardPathsDeltas();
	/**
	 * calculate transfer function
	 * @param source
	 * @param sink
	 * @return
	 */
	public double OverallTransferFunction(INode source,INode sink);
}
