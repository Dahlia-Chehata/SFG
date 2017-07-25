package frontEnd;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Interface {
	
	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Interface() {
		frame = new JFrame();
		frame.setBounds(40, 40, 400, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		/**
		 * title
		 */
		JLabel title = new JLabel("   ---SIGNAL FLOW GRAPH---");
		title.setFont(new Font("Segoe Script", Font.BOLD, 15));
		title.setForeground(Color.BLUE);
		title.setBounds(40, 18, 262, 90);
		frame.getContentPane().add(title);
		/**
		 * start button
		 */
		JButton btnStart = new JButton("START");
		btnStart.setFont(new Font("Segoe Script", Font.PLAIN, 20));
		btnStart.setForeground(Color.BLACK);
		btnStart.setBounds(20, 95, 110, 34);
		btnStart.setBackground(Color.black);
		btnStart.setForeground(Color.white);
		frame.getContentPane().add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.main();
				frame.setVisible(false);
			}
		});
		/**
		 * exit button
		 */
		JButton ExitBtn = new JButton("EXIT");
		ExitBtn.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		ExitBtn.setBounds(140,95, 100, 34);
		ExitBtn.setBackground(Color.ORANGE);
		ExitBtn.setForeground(Color.white);
		frame.getContentPane().add(ExitBtn);
		ExitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		/**
		 * help button
		 */
		JButton GuideBtn = new JButton("Help");
		GuideBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserManual help = new UserManual();
				help.frame.setVisible(true);
				help.frame.setTitle("User Guide");
				
			}
		});
		GuideBtn.setFont(new Font("Segoe Script", Font.PLAIN, 20));
		GuideBtn.setBounds(250, 95, 100, 34);
		GuideBtn.setForeground(Color.white);
		GuideBtn.setBackground(Color.red);
		frame.getContentPane().add(GuideBtn);
	}
}
