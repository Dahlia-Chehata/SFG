package frontEnd;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class UserManual {

  JFrame frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManual window = new UserManual();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public UserManual() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 26));
		frame.setBounds(100, 100, 800, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		/**
		 * help
		 */
		JLabel help = new JLabel("Ask for help ??");
		help.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
		help.setForeground(Color.BLUE);
		help.setBounds(322, 35, 238, 36);

		frame.getContentPane().add(help);
		/**
		 * SFG definition
		 */
		JLabel lbl1 = new JLabel("Signal Flow Graph  :-");
		lbl1.setForeground(Color.BLUE);
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl1.setBounds(10, 60, 567, 58);
		frame.getContentPane().add(lbl1);
		
		JLabel lbl2 = new JLabel("Signal flow graph of control system is "
				+ "simplification of block diagram using Mason's Method to calculate "
				+ "the overall gain of the system."
				);
		lbl2.setBounds(10, 100, 1164, 34);
		frame.getContentPane().add(lbl2);
		
		JLabel lbl3 = new JLabel("This is done by means of branches and nodes that form"
				+ " forward paths and loops..... ");
		lbl3.setBounds(10, 120, 473, 34);
		frame.getContentPane().add(lbl3);
		/**
		 * additional features
		 */
		JLabel lbl11 = new JLabel("Additional Features:-");
		lbl11.setBounds(10, 150, 473, 34);
		lbl11.setForeground(Color.BLUE);
		lbl11.setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.getContentPane().add(lbl11);
		
		JLabel lbl12 = new JLabel("A yellow panel appears next to the drawing panel.."
				+ "All log operations from insertion or deletion of nodes and edges ");
		lbl12.setBounds(10, 175, 1000, 34);
		frame.getContentPane().add(lbl12);
		
		JLabel lbl13 = new JLabel(
				 "are shown as well as the errors during any operations.");
		lbl13.setBounds(10, 195, 1000, 34);
		frame.getContentPane().add(lbl13);
		
		/**
		 * user's guide
		 */
		JLabel lbl4 = new JLabel("User's Guide  :-");
		lbl4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl4.setForeground(Color.blue);
		lbl4.setBounds(10, 227, 567, 36);
		frame.getContentPane().add(lbl4);
		
		JLabel lbl5 = new JLabel(" 1)  To draw a Node , press the button <ADD Node>... "
				+ "Drag the node to the required position and then release it by al left click.");
		lbl5.setBounds(10, 262, 1018, 28);
		frame.getContentPane().add(lbl5);
		
		JLabel lbl6 = new JLabel(" 2)  To draw an edge, press <ADD EDGE> ... "
				+ "Choose the 2 nodes to be connected with a click and enter the path gain in the dialog box .");
		lbl6.setBounds(10, 282, 888, 28);
		frame.getContentPane().add(lbl6);
		
		JLabel lbl7 = new JLabel(" 3) To draw a self loop , press <ADD EDGE>... "
				+ " and click twice on the required node then enter the gain in the dialog box .");
		lbl7.setBounds(10, 302, 918, 28);
		frame.getContentPane().add(lbl7);
		
		JLabel lbl8 = new JLabel(" 4) To calculate the gain, press <COMPUTE GAIN>... "
				+ " then select Source node and sink node by click .");
		lbl8.setBounds(10, 332, 803, 14);
		frame.getContentPane().add(lbl8);
		
		JLabel lbl9 = new JLabel(" 5) To clear data and restart a new SFG operation,"
				+ " press <CLEAR>. ");
		lbl9.setBounds(10, 352, 803, 14);
		frame.getContentPane().add(lbl9);
		JLabel lbl10 = new JLabel(" 6) To modify the value on any edge ,you can click on "
				+ "this value in the shown table and enter the new one in the dialog box");
		lbl10.setBounds(10, 372, 803, 14);
		frame.getContentPane().add(lbl10);
		JLabel lbl14 = new JLabel(" 7) Individuals loops and all possible combinations "
				+ "of non touching loops are shown in the correspondant table");
		lbl14.setBounds(10, 392, 803, 14);
		frame.getContentPane().add(lbl14);
		JLabel lbl15 = new JLabel(" the cell combination refers to the number "
				+ "of non touchingloops"
				+ ",the loop cell refers to the possible combinations of this specific number ");
		lbl15.setBounds(10, 412, 803, 14);
		frame.getContentPane().add(lbl15);
		JLabel lbl16 = new JLabel(" 8)Selecting any row in the tables (forward paths,loops,combinations)"
				+ " will change the color of the nodes along the selected path to green ");
		lbl16.setBounds(10, 432, 803, 14);
		frame.getContentPane().add(lbl16);
	}
}
