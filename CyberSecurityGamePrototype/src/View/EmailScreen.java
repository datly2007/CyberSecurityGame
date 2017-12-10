package View;

import Driver.GUI;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class EmailScreen extends JPanel {
	
	public static final String EMAIL_BACKGROUND = "support_files/wallpapers/EmailScreen.jpg";
	
	public static final int TOP_LEFT_X = 0; 
	
	public static final int TOP_LEFT_Y = 0;
	
	private GUI myGUI;
	
	private List<JButton> myReportButton;
	
	private List<JButton> myEmailList;
	
	private List<EmailDetailScreen> myEmail;
	
	private int myEmailIndex; 
	
	public EmailScreen(final GUI theGUI) {
		
		super();
		myGUI = theGUI;
		myEmail = new ArrayList<>();
		myReportButton = new ArrayList<>();
		myEmailList = new ArrayList<>();
		myEmailIndex = 0; 
		
		startEmailScreen();
	}

	private void startEmailScreen() {
		
		setLayout(new BorderLayout());
		final EmailDetailScreen test = new EmailDetailScreen(this);
		myEmail.add(test);
		
		final JPanel top_panel = new JPanel();
		top_panel.setOpaque(false);
		
		final JPanel bottom_panel = new JPanel();
		bottom_panel.setOpaque(false);
		
		final JButton back_button = new JButton("Back");
		back_button.addMouseListener(new Email2MainListener());
		
		bottom_panel.add(back_button);
		
		myEmailList.add(new JButton("Email"));
		myEmailList.get(0).addMouseListener(new Email2DetailListener());
		// Need to update the myEmailIndex accordingly 
		myReportButton.add(new JButton("Report!"));
		
		top_panel.add(myEmailList.get(0));
		top_panel.add(myReportButton.get(0));
		
		add(top_panel, BorderLayout.CENTER);
		add(bottom_panel, BorderLayout.SOUTH);
	}

	@Override 
	public void paintComponent(final Graphics theGraphics) {
		
		this.setOpaque(false);
		super.paintComponent(theGraphics);
		ImageIcon back_ground = new ImageIcon(EMAIL_BACKGROUND);
		theGraphics.drawImage(back_ground.getImage(), TOP_LEFT_X, TOP_LEFT_Y, 
										this.getWidth(), this.getHeight(), null);
		this.setOpaque(true);
	}
	
	class Email2MainListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			myGUI.remove(EmailScreen.this);
			myGUI.setContentPane( myGUI.getMainScreen() );
			myGUI.invalidate();
			myGUI.validate();
		}
	}
	
	class Email2DetailListener extends MouseAdapter {
		@Override 
		public void mouseClicked(final MouseEvent e) {
			myGUI.remove(EmailScreen.this);
			myGUI.setContentPane( myEmail.get(0) );
			myGUI.invalidate();
			myGUI.validate();
		}
	}
}
