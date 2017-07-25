package backEnd;

import java.util.ArrayList;
import java.util.LinkedList;

import IbackEnd.*;

public class MasonMethod implements IMason {

	private INode[] vertices;
	private boolean[] visited;
	private LinkedList<IPath> forwardPaths;
	private LinkedList<IPath> loops;
	private ArrayList<Double> forwardPathsDeltas;
	private ArrayList<int[]> ALlLoopsCombinations;
	private ArrayList<ArrayList<int[]>> NonTouchingLoopsCombinations;
	private LinkedList<INode> series; //temporary variable designating list of nodes in one path 

	public MasonMethod(INode[] vertices) {
		this.vertices = vertices;
	}
	@Override
	public LinkedList<IPath> getForwardPaths() {
		return forwardPaths;
	}
	@Override
	public LinkedList<IPath> getForwardPaths(INode src, INode sink) {
		if (forwardPaths == null) {
			visited = new boolean[vertices.length];
			forwardPaths = new LinkedList<IPath>();
			DFS(src, src, sink, forwardPaths, vertices, 1);
		}
		return forwardPaths;
	}
	@Override
	public ArrayList<ArrayList<int[]>> getNonTouchingLoopsCombinations(){
		return  NonTouchingLoopsCombinations;
	}
	private void DFS(INode node, INode srcNode, INode endNode, LinkedList<IPath> paths, 
			INode[] nodes, double gain) {
		visited[node.getnum()] = true;
		for (int i = 0; i < nodes[node.getnum()].getEdges().size(); i++) {
			if (nodes[node.getnum()].getEdges().get(i).getEndNode().getnum() == endNode.getnum()) {
				nodes[node.getnum()].getEdges().get(i).getEndNode().setParent(node);
				series = new LinkedList<>();
				series.add(getPath(srcNode, node));
				series.add(endNode);
				Path path = new Path(series, gain * nodes[node.getnum()].getEdges().get(i).getGainValue());
				paths.add(path);
			} else if (!visited[nodes[node.getnum()].getEdges().get(i).getEndNode().getnum()]) {
				nodes[node.getnum()].getEdges().get(i).getEndNode().setParent(node);
				gain *= nodes[node.getnum()].getEdges().get(i).getGainValue();
				DFS(nodes[node.getnum()].getEdges().get(i).getEndNode(), srcNode, endNode, paths, nodes, gain);
				gain /= nodes[node.getnum()].getEdges().get(i).getGainValue(); // backtracking
			}
		}
		visited[node.getnum()] = false;// backtracking
	}
	private INode getPath(INode start, INode end) {
		if (start.getnum() == end.getnum())
			return start;
		series.add(getPath(start, end.getParent()));
		return end;
	}
	@Override
	public LinkedList<IPath> getIndividualLoops() {
		if (loops == null) {
			loops = new LinkedList<>();
			for (int i = 0; i < vertices.length; i++) {
				visited = new boolean[vertices.length];
				for (int j = 0; j < i; j++)
					visited[j] = true;
				DFS(vertices[i], vertices[i], vertices[i], loops, vertices, 1);
			}
		}
		System.out.println(loops.size());
		return loops;
	}
	@Override
	public ArrayList<Double> getForwardPathsDeltas() {
		return forwardPathsDeltas;
	}
	/**
	 * check if 2 loops are touching or not
	 * @param loopsMaskedCombinations
	 * @return
	 */
	private boolean isNonTouchingLoops(int[] loopsMaskedCombinations) {
			for (int i = 0; i < loopsMaskedCombinations.length; i++) {
				for (int j = i+1; j < loopsMaskedCombinations.length; j++) {
					if((loops.get(loopsMaskedCombinations[i]).getMaskingCode()
					& loops.get(loopsMaskedCombinations[j]).getMaskingCode()) != 0)
						return false;
				}
			}	
			return true;
		}
	/**
	 * check if the path is touching with a loop
	 * @param forwardPath
	 * @param loop
	 * @return boolean
	 */
	private boolean isPathTouchingWithLoop(IPath forwardPath, IPath loop) {
		return ((forwardPath.getMaskingCode() & loop.getMaskingCode()) != 0);
	}
	/**
	 * @param numberOfCombinations
	 * @return CombinedLoops
	 * CombinedLoops contains all non touching loops for the i-th combination
	 * NonTouchingLoopsCombinations contains all possible combinations of CombinedLoops 
	 */
	 private ArrayList<int[]> getCombinedLoops(int numberOfCombinations) {
			ALlLoopsCombinations = new ArrayList<>();
			int[] Loopslist = new int[loops.size()];
			for (int i = 0; i < Loopslist.length; i++)
				Loopslist[i] = i;
			int[] solution = new int[numberOfCombinations];
			combination(Loopslist, solution, 0, 0);
			ArrayList<int[]> CombinedLoops = new ArrayList<>();
			for (int i = 0; i < ALlLoopsCombinations.size(); i++) {
				if (isNonTouchingLoops(ALlLoopsCombinations.get(i))) 
					CombinedLoops.add(ALlLoopsCombinations.get(i));
			}
			if (CombinedLoops.size() == 0)
				return null;
			NonTouchingLoopsCombinations.add(CombinedLoops);
			return CombinedLoops;
		}	
		/**
		 * fill ALlLoopsCombinations only with touching and non touching loops
		 * @param Loops
		 * @param Check
		 * @param cnt1
		 * @param cnt2
		 */
	 private void combination(int[] Loops, int[] Check, int cnt1, int cnt2) {
		if (cnt2 == Check.length) {
			ALlLoopsCombinations.add(Check.clone());
		} else {
			if (Check.length - cnt2 > Loops.length - cnt1)
				return;
			Check[cnt2] = Loops[cnt1];
			combination(Loops, Check, cnt1 + 1, cnt2 + 1);
			combination(Loops, Check, cnt1 + 1, cnt2);
		}
	}
	 /**
	  * calculate the denominator of Mason's gain
	  * @return
	  */
	private double calculateBigDelta() {
		double result = 1;
		int numOfCombinations = 0;
		while (true) {
			numOfCombinations++;
			ArrayList<int[]> CombinedLoops = getCombinedLoops(numOfCombinations);
			if (CombinedLoops == null) break; //no more possible combinations 	
			double sumOfProducts = 0;
			for (int i = 0; i < CombinedLoops.size(); i++) {
				double Product = 1;
				for (int j = 0; j < CombinedLoops.get(i).length; j++) {
					Product *= loops.get(CombinedLoops.get(i)[j]).getPathGain();
				}
				sumOfProducts += Product;
			}
			if (numOfCombinations % 2 == 0)
				result += sumOfProducts;
			else
				result -= sumOfProducts;
		}
		return result;
	}
	private double calculateForwardPathDelta(IPath forwardPath) {
		double result = 1;
		for (int CombinationIndex = 0; CombinationIndex < NonTouchingLoopsCombinations.size();
				CombinationIndex++) {
			ArrayList<int[]> CombinedLoops = NonTouchingLoopsCombinations.get(CombinationIndex);
			double sumOfProducts = 0;
			for (int i = 0; i < CombinedLoops.size(); i++) {
				double Product = 1;
				for (int j = 0; j < CombinedLoops.get(i).length; j++) {
					if (!isPathTouchingWithLoop(forwardPath,loops.get(CombinedLoops.get(i)[j]))) {
						Product *= loops.get(CombinedLoops.get(i)[j]).getPathGain();
					} else {
						Product=0;
						break;
					}	
				}
				sumOfProducts += Product;
			}
			if (CombinationIndex % 2 == 0)result -= sumOfProducts;
			else result += sumOfProducts;	
		}
		forwardPathsDeltas.add(result);
		return result;
	}
	@ Override
  public double OverallTransferFunction(INode source,INode sink){
	  getForwardPaths(source,sink);
	  getIndividualLoops();
	  NonTouchingLoopsCombinations = new ArrayList<>();
		forwardPathsDeltas = new ArrayList<>();
		double denominator=calculateBigDelta();
	    double numenator=0;
	    for (int i=0;i<forwardPaths.size();i++)
	    	numenator+=forwardPaths.get(i).getPathGain()
	    	*calculateForwardPathDelta(forwardPaths.get(i));
	   return numenator/denominator;
	  
  }
}
