package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import id.PieceColor;

public class StartScreen {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private PieceColor color=PieceColor.BLACK;
	private JSlider slider;
	private JCheckBox chckbxNewCheckBox;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public StartScreen(UIBoard b) {
		initialize(b);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(UIBoard b) {
		frame = new JFrame();
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 230, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		Image icon=null;
		try {
			icon = ImageIO.read(UIBoard.class.getResource("/IconImage.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		frame.setIconImage(icon);
		
		JLabel lblComputerAs = new JLabel("Computer AS");
		lblComputerAs.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		lblComputerAs.setForeground(new Color(139, 0, 0));
		lblComputerAs.setBounds(54, 41, 106, 25);
		frame.getContentPane().add(lblComputerAs);
		
		JRadioButton rdbtnWhite = new JRadioButton("WHITE");
		rdbtnWhite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				color=PieceColor.WHITE;
			}
		});
		buttonGroup.add(rdbtnWhite);
		rdbtnWhite.setBounds(20, 73, 86, 23);
		frame.getContentPane().add(rdbtnWhite);
		
		JRadioButton rdbtnBlack = new JRadioButton("BLACK");
		rdbtnBlack.setSelected(true);
		rdbtnBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color=PieceColor.BLACK;
			}
		});
		buttonGroup.add(rdbtnBlack);
		rdbtnBlack.setBounds(122, 73, 86, 23);
		frame.getContentPane().add(rdbtnBlack);
		
		JLabel lblStrength = new JLabel("Strength");
		lblStrength.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));
		lblStrength.setBounds(70, 126, 68, 25);
		frame.getContentPane().add(lblStrength);
		
		slider = new JSlider();
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setValue(1);
		slider.setPaintLabels(true);
		slider.setMinimum(1);
		slider.setMaximum(9);
		slider.setBounds(10, 151, 179, 26);
		frame.getContentPane().add(slider);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int t=0;
				b.newGame(new GameStateInfo(slider.getValue(), chckbxNewCheckBox.isSelected(), color,t));
				frame.dispose();
			}
		});
		btnNewButton.setForeground(new Color(70, 130, 180));
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 17));
		btnNewButton.setBounds(70, 327, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("JCERB");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(0, 0, 204, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblQuiscence = new JLabel("Quiescence");
		lblQuiscence.setFont(new Font("Sitka Text", Font.BOLD, 16));
		lblQuiscence.setForeground(new Color(119, 136, 153));
		lblQuiscence.setBounds(10, 284, 118, 32);
		frame.getContentPane().add(lblQuiscence);
		
		chckbxNewCheckBox = new JCheckBox("ALLOW");
		chckbxNewCheckBox.setBounds(144, 290, 70, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		chckbxNewCheckBox.setSelected(true);
		
	}
}
