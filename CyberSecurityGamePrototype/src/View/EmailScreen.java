package View;

import Driver.GUI;
import Model.PlayerInfo;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 *
 * @author Dat Ly
 * @author Raymond Schooley
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
	
	private JPanel myTopPanel;
	
	public EmailScreen(final GUI theGUI, final PlayerInfo thePlayer) {
		
		super();
		myGUI = theGUI;
		myEmail = new ArrayList<>();
		myReportButton = new ArrayList<>();
		myEmailList = new ArrayList<>();
		myPlayer = thePlayer;
		myTopPanel = new JPanel();
		
		startEmailScreen();
	}

	private void startEmailScreen() {
		
		setLayout(new BorderLayout());
		myTopPanel.setLayout(new GridLayout(15, 2));
		
		final JPanel first_panel = new JPanel();
		
		final EmailDetailScreen password_email = new EmailDetailScreen(this, "0", myPlayer, 
									"email_storage/default_password_email.txt");
		myEmail.add(password_email);
		
		myTopPanel.setOpaque(false);
		
		final JPanel bottom_panel = new JPanel();
		bottom_panel.setOpaque(false);
		
		final JButton back_button = new JButton("Back");
		back_button.addMouseListener(new Email2MainListener());
		back_button.setIcon(GUI.BACK_ICON);
		
		bottom_panel.add(back_button);
		
		myEmailList.add(new JButton("Password Email"));
		myEmailList.get(0).addMouseListener(new Email2DetailListener());
		
		// Need to update the myEmailIndex accordingly 
		myReportButton.add(new JButton("Report!"));
		
		first_panel.add(myEmailList.get(0));
		first_panel.add(myReportButton.get(0));
		first_panel.setOpaque(false);
		
		myTopPanel.add(first_panel);
		
		add(myTopPanel, BorderLayout.NORTH);
		add(bottom_panel, BorderLayout.SOUTH);
		
		myEmail.get(0).addPropertyChangeListener(this);
	}
	
	public int getTotalEmail() {
		return myEmail.size();
	}
	
	public void generateMoreEmail() {
		
		final JPanel second_panel = new JPanel();
		
		final EmailDetailScreen network_email = new EmailDetailScreen(this, "1", myPlayer, 
				"email_storage/macaddress_email.txt");
		myEmail.add(network_email);
		
		myEmailList.add(new JButton("MAC Address Email"));
		myEmailList.get(1).addMouseListener(new Email2DetailListener());
		
		// Need to update the myEmailIndex accordingly 
		myReportButton.add(new JButton("Report!"));
		
		second_panel.add(myEmailList.get(1));
		second_panel.add(myReportButton.get(1));
		second_panel.setOpaque(false);
		
		myTopPanel.add(second_panel);
		
		myEmail.get(1).addPropertyChangeListener(this);
		
		spamEmail();
	}

	private void spamEmail() {
		
		for (int i = 2; i < 15; i++) {
			final JPanel second_panel = new JPanel();
		
			final EmailDetailScreen network_email = new EmailDetailScreen(this, "1", myPlayer, 
				"email_storage/phishing_email_1.txt");
			myEmail.add(network_email);
		
			myEmailList.add(new JButton("Spam Email"));
			myEmailList.get(i).addMouseListener(new Email2DetailListener());
		
			// Need to update the myEmailIndex accordingly 
			myReportButton.add(new JButton("Report!"));
		
			second_panel.add(myEmailList.get(i));
			second_panel.add(myReportButton.get(i));
			second_panel.setOpaque(false);
		
			myTopPanel.add(second_panel);
		
			myEmail.get(i).addPropertyChangeListener(this);
		}
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
		int index = -1;
		if("0".equals(theEvent.getPropertyName())) {
			index = 0;
		} else if ("1".equals(theEvent.getPropertyName())) {
			index = 1;
		} 
		if (index > -1) {
			myGUI.remove( myEmail.get(index) );
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
			Object o = theEvent.getSource();
			JButton b = (JButton) o;
			int index = 0; 
			
			if (b.getText().equals("MAC Address Email")) {
				index = 1;
			}
			
			if (b.getText().equals("Spam Email")) {
				index = 2;
			}
			
			myGUI.remove(EmailScreen.this);
			myGUI.setContentPane( myEmail.get(index) );
			myGUI.invalidate();
			myGUI.validate();
		}
	}
}


