package View;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EmailScreen extends JPanel implements ActionListener{
	
	public static final String BACKGROUND_DIRECTORY = "support_files/wallpapers/EmailScreen.jpg";
	
	public static final int TOP_LEFT_X = 0; 
	
	public static final int TOP_LEFT_Y = 0;
	
	private JButton myReportButton [];
	
	private JButton myEmailList[];
	
	private EmailDetailScreen []myEmail;
	
	private JFrame myFrame;
	
	public EmailScreen() {
		
		super();
		myFrame = new JFrame();
		myEmail = new EmailDetailScreen[4];
		myEmail[0] = new EmailDetailScreen();
		StartEmailScreen();
	}
	
	@Override 
	public void paintComponent(final Graphics theGraphics) {
		this.setOpaque(false);
		super.paintComponent(theGraphics);
		ImageIcon back_ground = new ImageIcon(BACKGROUND_DIRECTORY);
		theGraphics.drawImage(back_ground.getImage(), TOP_LEFT_X, TOP_LEFT_Y, 
										this.getWidth(), this.getHeight(), null);
		this.setOpaque(true);
	}
	
	
	private void StartEmailScreen() {
		JButton test = new JButton("Email");
		JButton report_button = new JButton("Report!");
		
		this.add(test);
		this.add(report_button);
		
		test.addActionListener(this);
		
		this.setVisible(true);
		myFrame.add(this);
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Toolkit kit = Toolkit.getDefaultToolkit();
        myFrame.setSize(
                    (int) (kit.getScreenSize().getWidth()),
                    (int) (kit.getScreenSize().getHeight()));
        myFrame.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		myFrame.remove(this);
		myFrame.setContentPane(myEmail[0]);
		myFrame.invalidate();
		myFrame.validate();
	}
}
