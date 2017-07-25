package frontEnd;
import java.awt.Color;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class View extends JFrame {

	static View frame;
	private JPanel Pane;
	private Control controller;
	private JTextField textField;
	private DefaultListModel<String> history;
	private DefaultTableModel edges;
	private DefaultTableModel loops;
	private DefaultTableModel loopsCombinations;
	private DefaultTableModel forwardData;
	 
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public View() throws IOException {
		setTitle("SIGNAL FLOW GRAPH");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 710);
        setLocationRelativeTo(null);
        
		DrawPanel panel = new DrawPanel();
		
		controller = Control.getInstance(panel, this);
		panel.addMouseListener(controller);
		panel.addMouseMotionListener(controller);
		panel.setBackground(Color.black);

		Pane = new JPanel();
		Pane.setBorder(new EmptyBorder(0, 0, 5, 5));
		setContentPane(Pane);

		JScrollPane scrollPane1 = new JScrollPane();
		JScrollPane scrollPane2 = new JScrollPane();
		JScrollPane scrollPane3 = new JScrollPane();
		JScrollPane scrollPane4 = new JScrollPane();
		JScrollPane scrollPane5 = new JScrollPane();

		scrollPane1.setViewportView(panel);
		
		JPanel pnl1 = new JPanel();
		pnl1.setBorder(new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, null));

		history = new DefaultListModel<String>();

		
		JLabel lblGain = new JLabel("   Transfer function =");
		lblGain.setFont(new Font("Times New Roman", Font.BOLD, 18));
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setColumns(27);
		
		JList<String> list = new JList<>(history);
		list.setVisible(true);
		list.setBackground(Color.yellow);
		list.addMouseListener(controller);

		GroupLayout gl_Pane1 = new GroupLayout(Pane);
		
		gl_Pane1.setHorizontalGroup(gl_Pane1.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_Pane1.createSequentialGroup().addGap(15)
		.addGroup(gl_Pane1.createParallelGroup(Alignment.TRAILING)
		.addComponent(pnl1, GroupLayout.PREFERRED_SIZE, 169,Short.MAX_VALUE)
		.addComponent(lblGain, GroupLayout.PREFERRED_SIZE, 169,Short.MAX_VALUE)
		.addComponent(textField,GroupLayout.PREFERRED_SIZE, 169, Short.MAX_VALUE)
		.addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 169,Short.MAX_VALUE))
		.addGap(15).addGroup(gl_Pane1.createParallelGroup(Alignment.LEADING)
		.addComponent( scrollPane4 ,GroupLayout.PREFERRED_SIZE, 250, Short.MAX_VALUE)
		.addComponent(scrollPane5,GroupLayout.PREFERRED_SIZE, 250, Short.MAX_VALUE)
		.addComponent(scrollPane3, GroupLayout.PREFERRED_SIZE, 250, Short.MAX_VALUE))
		.addGap(20).addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
		.addComponent(list,GroupLayout.PREFERRED_SIZE,180, GroupLayout.PREFERRED_SIZE))
		);
		
		
		
		
		gl_Pane1.setVerticalGroup(gl_Pane1.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_Pane1.createSequentialGroup().addContainerGap()
		.addGroup(gl_Pane1.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_Pane1.createSequentialGroup()
		.addComponent(pnl1, GroupLayout.PREFERRED_SIZE, 250,GroupLayout.PREFERRED_SIZE)
		.addComponent(lblGain, GroupLayout.PREFERRED_SIZE, 50,GroupLayout.PREFERRED_SIZE)
		.addComponent(textField, GroupLayout.PREFERRED_SIZE, 50,GroupLayout.PREFERRED_SIZE)
		.addGap(10).addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
		.addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 319,Short.MAX_VALUE)
		.addComponent(list,GroupLayout.PREFERRED_SIZE,300,  Short.MAX_VALUE)
		.addGroup(gl_Pane1.createSequentialGroup().addComponent(scrollPane3, 
		 GroupLayout.PREFERRED_SIZE, 200,Short.MAX_VALUE).addGap(10)
		.addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 400,Short.MAX_VALUE)					
		.addGap(10).addGroup(gl_Pane1.createParallelGroup(Alignment.LEADING)
		.addComponent(scrollPane5, GroupLayout.DEFAULT_SIZE, 400,Short.MAX_VALUE)
		.addGroup(gl_Pane1.createSequentialGroup().addContainerGap()
		.addGroup(gl_Pane1.createParallelGroup(Alignment.LEADING)
		)))))));
					
        /**
         * edge table
         */
		edges = new DefaultTableModel(new String[][] {}, new String[] { "Edge", "Gain" });
		JTable table = new JTable(edges);
		table.setName("edgeTable");
		table.addMouseListener(controller);
		scrollPane2.setViewportView(table);
        /**
         * forward path table
         */
		forwardData = new DefaultTableModel(new String[][] {}, new String[] 
				{ "Forward Paths", "Gain" ,"Δ"});
		JTable forwardTable = new JTable(forwardData);
		forwardTable.setName("forwardTable");
		forwardTable.addMouseListener(controller);
		scrollPane3.setViewportView(forwardTable);
        /**
         * loops table
         */
		loops = new DefaultTableModel(new String[][] {}, new String[] { "Loops", "Gain" });
		JTable loopTable = new JTable(loops);
		loopTable.setName("loopTable");
		loopTable.addMouseListener(controller);
		scrollPane4.setViewportView(loopTable);
		/**
		 * combinations table
		 */
		loopsCombinations=new DefaultTableModel(new String[][] {}, new String[] 
				{ "Combination","Loop", "Gain" });
		JTable CombinationsTable = new JTable(loopsCombinations);
		CombinationsTable.setName("CombinationsTable");
		CombinationsTable.addMouseListener(controller);
		scrollPane5.setViewportView(CombinationsTable);

		
		JButton button = new JButton("ADD NODE");
		button.setBounds(0, 0, 25, 35);
		button.addActionListener(controller);

		JButton btn1 = new JButton("ADD EDGE");
		btn1.addActionListener(controller);

		JButton btn2 = new JButton("COMPUTE GAIN");
		btn2.addActionListener(controller);
         
		JButton btn3 = new JButton("CLEAR");
		btn3.addActionListener(controller);
		
		GroupLayout gl_pnl1 = new GroupLayout(pnl1);
		
		gl_pnl1.setHorizontalGroup(gl_pnl1.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_pnl1.createSequentialGroup().addGap(18)
		.addGroup(gl_pnl1.createParallelGroup(Alignment.LEADING)
		 .addComponent(btn3, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
		.addComponent(btn2, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
		.addComponent(btn1, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
		.addComponent(button, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
		.addGap(21))));
		gl_pnl1.setVerticalGroup(gl_pnl1.createParallelGroup(Alignment.TRAILING)
		.addGroup(Alignment.LEADING, gl_pnl1.createSequentialGroup().addGap(35)
		.addComponent(button, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE).addGap(18)
		.addComponent(btn1, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
		.addGap(18)
		.addComponent(btn2, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
		.addGap(18)
		.addComponent(btn3, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
		.addGap(18)
		.addGap(57)));
		
		pnl1.setLayout(gl_pnl1);
		Pane.setLayout(gl_Pane1);
	}
   /**
    * history handling
    * @param s
    */
	
	public void addhistory(String s) {
		if (history.size() > 20)
			history.remove(0);
		history.addElement(s);

	}
	public void clearHistory(){
			history.clear();
	}
    /**
     * loops combinations handling in tables
     */
	public void clearloopsCombinations() {
			loopsCombinations.setRowCount(0);
	}

	public void addLoopsCombination(String[] rowData) {
		loopsCombinations.addRow(rowData);
	}

	public void deleteLoopsCombination(int row) {
		loopsCombinations.removeRow(row);
	}
	/**
     * loops handling in tables
     */
	public void clearLoops() {
		loops.setRowCount(0);;
	}

	public void addLoop(String[] rowData) {
		loops.addRow(rowData);
	}

	public void deleteLoop(int row) {
		loops.removeRow(row);
	}
	/**
	 * forward paths handling in tables
	 */
	public void clearForwardData() {
		forwardData.setRowCount(0);
		
	}

	public void addForwardPaths(String[] rowData) {
		forwardData.addRow(rowData);
	}
	public void deleteForwardPaths(int row) {
		forwardData.removeRow(row);
	}
	
	/**
	 * edges handling in tables
	 */
	public void clearEdges(){
		if (edges.getRowCount() > 0) {
		    for (int i = edges.getRowCount() - 1; i > -1; i--) {
		        edges.removeRow(i);
		    }
		}
	}
	public void addEdge(String[] rowData) {
		edges.addRow(rowData);
	}

	public void removeEdge(int row) {
		edges.removeRow(row);
	}

	public void setGainText(String s) {
		textField.setText(s);
	}

	public void setEdgeValue(int row, int col, double val) {
		edges.setValueAt(val, row, col);
	}
	public void clearTF(){
		textField.setText(null);
	}
}

