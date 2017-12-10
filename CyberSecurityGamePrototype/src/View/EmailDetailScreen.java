package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EmailDetailScreen extends JPanel {

	private JPanel myEmailScreen;
	
	public EmailDetailScreen(final JPanel thePanel) {
		
		super();
		
		myEmailScreen = thePanel;
		
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
		
		setLayout(new BorderLayout());
		
		final JPanel north_panel = new JPanel();
		final JPanel south_panel = new JPanel();
		
		final JButton back_button = new JButton("Back");
		// This will fire some event so that the main email know and go back to main screen 
		
		south_panel.add(back_button);
		
		add(south_panel, BorderLayout.SOUTH);
		
		south_panel.setOpaque(false);
		
		setBackground(Color.CYAN);
		setVisible(true);
	}
}




