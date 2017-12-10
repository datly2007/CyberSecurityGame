package View;

import Driver.GUI;
import Model.PlayerInfo;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
*
* @author Dat Ly
* @author Raymond Schooley
* @author Trung Thai 
* @author Wes Stahl
* @version 1.0
*/

public class EmailScreen extends JPanel implements PropertyChangeListener {
	
	public static final String EMAIL_BACKGROUND = "support_files/wallpapers/EmailScreen.jpg";
	
	public static final int TOP_LEFT_X = 0; 
	
	public static final int TOP_LEFT_Y = 0;
	
	private GUI myGUI;
	
	private List<JButton> myReportButton;
	
	private List<JButton> myEmailList;
	
	private List<EmailDetailScreen> myEmail; 
	
	private PlayerInfo myPlayer;
	
	public EmailScreen(final GUI theGUI, final PlayerInfo thePlayer) {
		
		super();
		myGUI = theGUI;
		myEmail = new ArrayList<>();
		myReportButton = new ArrayList<>();
		myEmailList = new ArrayList<>();
		myPlayer = thePlayer;
		
		startEmailScreen();
	}

	private void startEmailScreen() {
		
		setLayout(new BorderLayout());
		final EmailDetailScreen test = new EmailDetailScreen(this, "0", myPlayer);
		myEmail.add(test);
		
		final JPanel top_panel = new JPanel();
		top_panel.setOpaque(false);
		
		final JPanel bottom_panel = new JPanel();
		bottom_panel.setOpaque(false);
		
		final JButton back_button = new JButton("Back");
		back_button.addMouseListener(new Email2MainListener());
		back_button.setIcon(GUI.BACK_ICON);
		
		bottom_panel.add(back_button);
		
		myEmailList.add(new JButton("Email"));
		myEmailList.get(0).addMouseListener(new Email2DetailListener());
		
		// Need to update the myEmailIndex accordingly 
		myReportButton.add(new JButton("Report!"));
		
		top_panel.add(myEmailList.get(0));
		top_panel.add(myReportButton.get(0));
		
		add(top_panel, BorderLayout.CENTER);
		add(bottom_panel, BorderLayout.SOUTH);
		
		myEmail.get(0).addPropertyChangeListener(this);
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
	
	@Override
	public void propertyChange(final PropertyChangeEvent theEvent) {
		if("0".equals(theEvent.getPropertyName())) {
			myGUI.remove( myEmail.get(0) );
			myGUI.setContentPane(this);
			myGUI.invalidate();
			myGUI.validate();
		}
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
		public void mouseClicked(final MouseEvent theEvent) {
			myGUI.remove(EmailScreen.this);
			myGUI.setContentPane( myEmail.get(0) );
			myGUI.invalidate();
			myGUI.validate();
		}
	}
}


