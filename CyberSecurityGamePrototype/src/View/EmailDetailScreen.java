package View;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class EmailDetailScreen extends JPanel {

	
	public EmailDetailScreen() {
		
		super();
		openEmail();
	}
	
//	@Override 
//	public void paintComponent(final Graphics theGraphics) {
//		this.setOpaque(false);
//		super.paintComponent(theGraphics);
//		ImageIcon back_ground = new ImageIcon(EmailScreen.BACKGROUND_DIRECTORY);
//		theGraphics.drawImage(back_ground.getImage(), EmailScreen.TOP_LEFT_X, EmailScreen.TOP_LEFT_Y, 
//										this.getWidth(), this.getHeight(), null);
//		this.setOpaque(true);
//	}
	
	private void openEmail() {
		
		JButton back_button = new JButton("Back");
		
		this.add(back_button);
		
		this.setBackground(Color.BLUE);
		this.setVisible(true);
	}
}
