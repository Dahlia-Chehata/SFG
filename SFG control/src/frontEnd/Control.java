package frontEnd;
import backEnd.*;
import IbackEnd.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class Control extends MouseAdapter implements ActionListener {

	//MVC
	private IMason model;
	private View view;
	private static Control controller ;
	private DrawPanel panel;
	private RowSelection Row;
	//boolean
	private boolean addEdge;
	private boolean transferFunction;
	private boolean updateEdge;
	//lists
	private LinkedList<IPath> forwardPaths;
	private ArrayList<Double>forwardPathsDeltas;
	private LinkedList<IPath> loops;
	private ArrayList<ArrayList<int[]>> Combinations;
	private ArrayList<IEdge> edges;
	private int edgeNum;
	private DrawEdge newEdge;
	private int [] loopsIndices;
	/**
	 * constructor
	 * @param panel
	 * @param view
	 * @return
	 */
	
	//singleton:ONE instance is created
	public static Control getInstance (DrawPanel panel, View view){
		if(controller!=null){
			return controller;
		}else{
			controller=new Control(panel,view);
			return controller;
		}
		
	}

	private Control(DrawPanel panel, View view) {
		this.panel = panel;
		this.view = view;
		edgeNum = 0;
		edges = new ArrayList<>();
	}
     
	private void ClearData(){
		cancelNodeAddition();
		CancelEdgeAddition();
		cancelSelection();
		view.clearTF();
		view.clearEdges();
		view.clearForwardData();
		view.clearLoops();
		view.clearloopsCombinations();
		view.clearHistory();
		panel.clearNodes();
		panel.repaint();
	}
	
	private void cancelNodeAddition() {
		panel.cancelNodeAddition();
	}
	private void CancelEdgeAddition() {
		if (addEdge) {
			view.addhistory("Adding Edge operation was interrupted!");
			addEdge = false;
			panel.setSelectedNodeToNull();
		}
	}

	private void cancelSelection() {
		if (Row != null) {
			if (Row.getTableName().equals("loopTable")) {
				Iterator<INode> iterator = loops.get(Row.getSelectedRow()).getPath().iterator();
				DrawNode node;
				while (iterator.hasNext()) {
					node = (DrawNode) iterator.next();
					node.setColor(panel.getDefaultColor());
				}
			} else if (Row.getTableName().equals("forwardTable")) {
		       Iterator<INode> iterator = forwardPaths.get(Row.getSelectedRow()).getPath().iterator();		
				INode node;
				while (iterator.hasNext()) {
					node = iterator.next();
					((DrawNode) node).setColor(panel.getDefaultColor());
				}
			
			}else if (Row.getTableName().equals("CombinationsTable")){
				 for (int i=0;i<loopsIndices.length;i++){
					 Iterator<INode>iterator=loops.get(loopsIndices[i]-1).getPath().iterator();
	                   INode node;
	                       while (iterator.hasNext()){
	                    	  node=iterator.next();
	                          ((DrawNode) node).setColor(panel.getDefaultColor());
	                            }
				 }	
			}
			Row = null;
			panel.repaint();
		}
	}

	
	private boolean isReady() {
		if (panel.isThereSelectedNode()) {
			JOptionPane.showMessageDialog(null,	"The previous Operation was interrupted!","Alert",
					JOptionPane.ERROR_MESSAGE);
			view.addhistory("Alert ! Uncompleted Operation.");
			return false;
		}
		return true;
	}

	private void addNode() {
		if (isReady()) {
			model = null;
			CancelEdgeAddition();
			panel.createNode();
			
		}
	}

	
	private void addEdge(int x, int y) {
		if (!panel.isThereSelectedNode()) {
			INode node = (Node) panel.select(x, y);
			if (node != null) {
				view.addhistory("Start of The Edge is Selected.");
				newEdge = new DrawEdge(node, edgeNum);
				node.addEdges(newEdge);
				panel.repaint();
			}

		} else if (panel.isThereSelectedNode()) {
			Node node = (Node) panel.select(x, y);
			if (node != null) {

				try {
					double value = Double.parseDouble(JOptionPane.showInputDialog(
							null, "Enter the Edge's gain :", "New Edge",
							JOptionPane.DEFAULT_OPTION));
					edges.add(panel.addEdge(value));
					view.addEdge(new String[] { "e" + (edgeNum+1), value + "" });
					edgeNum++;
					addEdge = false;
					updateEdge = true;
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"An Error has occured","Error",JOptionPane.ERROR_MESSAGE);
					CancelEdgeAddition();
				}

				view.addhistory("Edge is added .");
				panel.repaint();
			}

		}
	}

	private void transferFunction(int x, int y) {

		if (!panel.isThereSelectedNode()) {
			Node node = (Node) panel.select(x, y);
			if (node != null) {
				view.addhistory("R(s) is Selected");
				panel.repaint();
			}

		} else if (panel.isThereSelectedNode()) {
			Node node = (Node) panel.select(x, y);
			if (node != null) {
				double gain=model.OverallTransferFunction(panel.getfirstSelectedNode(),
						panel.getSecondSelectedNode());
				if (!Double.isNaN(gain)&&!Double.isInfinite(gain))
				view.setGainText(""+gain );
				else
	   JOptionPane.showMessageDialog(null,"An Error has occured","Error",JOptionPane.ERROR_MESSAGE);

				//forward paths
				view.clearForwardData();
				forwardPaths = model.getForwardPaths();
				forwardPathsDeltas=model.getForwardPathsDeltas();
				Iterator<IPath> iterator = forwardPaths.iterator();
				IPath path;
				int count = 1;
				int index=0;
				while (iterator.hasNext()) {
					path = iterator.next();
					view.addForwardPaths(new String[] { "Path " + (count++),
							path.getPathGain() + "" ,forwardPathsDeltas.get(index++)+""});
				}
              //loops
				view.clearLoops();
				loops = model.getIndividualLoops();
				iterator = loops.iterator();
				count = 1;
				while (iterator.hasNext()) {
					path = iterator.next();
					view.addLoop(new String[] { "Loop " + (count++),
							path.getPathGain() + "" });
				}
				 //combinations
				view.clearloopsCombinations();
				Combinations=model.getNonTouchingLoopsCombinations();
				for (int k=0;k<Combinations.size();k++){
					for (int i=0;i<Combinations.get(k).size();i++){
						double product=1;
						String str="";
						for (int j=0;j< (Combinations.get(k).get(i)).length;j++) {
						   product*=loops.get((Combinations.get(k).get(i))[j]).getPathGain();
						   int temp=(Combinations.get(k).get(i))[j]+1;
						   str= str+ temp+" ";
						}
						view.addLoopsCombination((new String[] { (k+1)+"" ,str,
								product + "" }));
					}
				}
				transferFunction = false;
				view.addhistory("C(s) has been Selected.");
				view.addhistory("All possible Combinations ");
				view.addhistory("of non touching loops were");
				view.addhistory("added if found");
				panel.setSelectedNodeToNull();
				panel.repaint();
			}

		}

	}

   /**
    * for Mason's constructor
    * @return
    */
	private Node[] getNodes() {
		ArrayList<DrawNode> list = panel.getNodes();
		Node[] nodes = new Node[list.size()];
		for (int i = 0; i < list.size(); i++) {
			nodes[i] = list.get(i);
			nodes[i].setnum(i);
		}
		return nodes;
	}
	/**
     * action performed response
     */
	@Override
	public void actionPerformed(ActionEvent arg) {
		cancelSelection();
		if (!updateEdge) {
			switch (arg.getActionCommand()) {
			case "CLEAR":
				ClearData();
				break;
			case "ADD NODE":
				addNode();
				break;
			case "ADD EDGE":
				addEdge = true;
				model = null;
				cancelNodeAddition();
				break;
			case "COMPUTE GAIN":
				transferFunction = true;
				model = new MasonMethod(getNodes());
				CancelEdgeAddition();
				break;
			
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		cancelSelection();
		
		if (e.getSource() instanceof JTable) {
			JTable tempTable = (JTable) e.getSource();
			int row	= tempTable.getSelectedRow();
			Row = new RowSelection(row, tempTable.getName());
			
			if (tempTable.getName().equals("loopTable")) {
				Iterator<INode> iterator = loops.get(row).getPath().iterator();
				INode node;
				while (iterator.hasNext()) {
					node = iterator.next();
					((DrawNode) node).setColor(Color.GREEN);
				}
			} else if (tempTable.getName().equals("forwardTable")) {
				Iterator<INode> iterator = forwardPaths.get(row).getPath().iterator();
				INode node;
				while (iterator.hasNext()) {
					node = iterator.next();
					((DrawNode) node).setColor(Color.GREEN);
				}

			} else if (tempTable.getName().equals("edgeTable")) {
				IEdge edge = edges.get(row);
				double val = 0;
				try {
					val = Double.parseDouble(JOptionPane.showInputDialog(null,
							"Enter The Value of The Edge :", "New Edge",
							JOptionPane.DEFAULT_OPTION));

					edge.setGainValue(val);
					view.setEdgeValue(row, tempTable.getSelectedColumn(), val);
				} catch (Exception ex) {
					edge.setGainValue(1);
					view.setEdgeValue(row, tempTable.getSelectedColumn(), 1);
				}
			} else if (tempTable.getName().equals("CombinationsTable")){  
				
				String content =tempTable.getModel().getValueAt(tempTable.getSelectedRow(), 1)
	            		   .toString();
				 String[] split = content.split("\\s+");
				 int[] results = new int[split.length];
				
				 for (int i = 0; i < split.length; i++) {
				     try {
				         results[i] = Integer.parseInt(split[i]);
				     } catch (NumberFormatException nfe) {
				     };
				 }
			     loopsIndices=results;
				 for (int i=0;i<results.length;i++){
					 Iterator<INode>iterator=loops.get(results[i]-1).getPath().iterator();
	                   INode node;
	                       while (iterator.hasNext()){
	                    	  node=iterator.next();
	                          ((DrawNode) node).setColor(Color.GREEN);
	                            }
				 }
			}
			panel.repaint();
		} else if (panel.isThereNewNode()) {
			panel.addNode();
			view.addhistory("New Node is Added");
		} else if (updateEdge) {
			updateEdge = false;
			newEdge = null;
		} else if (addEdge) {
			addEdge(e.getX(), e.getY());
		} else if (transferFunction) {
			transferFunction(e.getX(), e.getY());
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (updateEdge) {
			((DrawEdge) newEdge).setSourcePtPosition(e.getX(),e.getY());
		} else if (panel.isThereNewNode()) {
			panel.setNode(e.getX(), e.getY());
		}
		panel.repaint();
	}

}

