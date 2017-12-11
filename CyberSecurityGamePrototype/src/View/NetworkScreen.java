package View;


/**
 *
 * @author Dat Ly
 * @author Raymond Schooley
 * @version 1.0
 */

import Model.PlayerInfo;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Driver.GUI;

public class NetworkScreen extends JPanel implements ActionListener {
	
	public static final String NETWORK_BACKGROUND = "support_files/wallpapers/NetworkScreen.png";
	
	private static final int FIELD_LENGTH = 20; 

	private GUI myGUI;
	private PlayerInfo myPlayer;
	private JTextField myPassField;
	private JTextField myMacField;
	private JPasswordField myPassField1; 
	private JPasswordField myPassField2;

	private String myPassString = "PasswordField";
	private String myMacString = "MacField";
	
	private String myVerifyPassword = "VerifyPassword";
	
	private String myNewPassA;
	private String myNewPassB;
	private String myOldPass;
	
	public NetworkScreen(final GUI theGUI, final PlayerInfo thePlayer) {
		
		myGUI = theGUI;
		myPlayer = thePlayer;
		myPassField1 = new JPasswordField(FIELD_LENGTH);
		myPassField2 = new JPasswordField(FIELD_LENGTH);
		myNewPassA = "";
		myNewPassB = "";
		myOldPass = "Garbage String";
		
		startNetworkScreen();
	}
	
	private void startNetworkScreen() {
		
		setLayout(new BorderLayout());
		
		final JLabel screenTitle = new JLabel("NetworkScreen");
		
		//mainPanel contains all the components of network screen
		final JPanel mainPanel = new JPanel(new GridLayout(5,1));
		final JPanel bottom_panel = new JPanel();
		
		final JPanel passwordPanel = createPassField();
		final JPanel passEncryptPanel = createPassEncrypt();
		final JPanel netEncryptPanel = createNetEncrypt();
		final JPanel enableMacFilter = createMacFilterCheck();
		final JPanel macField = createMacField();
		
		final JButton back_button = new JButton("Back");
		back_button.addMouseListener(new Network2MainListener());
		back_button.setIcon(GUI.BACK_ICON);
		
		bottom_panel.add(back_button);
		
		add(screenTitle, BorderLayout.NORTH);
		
		mainPanel.add(passwordPanel);
		mainPanel.add(passEncryptPanel);
		mainPanel.add(netEncryptPanel);
		mainPanel.add(enableMacFilter);
		mainPanel.add(macField);
		
		mainPanel.setOpaque(false);
		
		passwordPanel.setOpaque(false);
		passEncryptPanel.setOpaque(false);
		netEncryptPanel.setOpaque(false);
		enableMacFilter.setOpaque(false);
		macField.setOpaque(false);
		
		bottom_panel.setOpaque(false);
		
		add(mainPanel, BorderLayout.CENTER);
		add(bottom_panel, BorderLayout.SOUTH);
	}
	
	private void createPanelBorder(final String theString, final JPanel thePanel) {
		thePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(theString),
                BorderFactory.createEmptyBorder(5,5,5,5)));
	}
	
	private JPanel createPassField() {
		final JPanel panel = new JPanel();
        //Create password field
		myPassField = new JTextField(FIELD_LENGTH);
		myPassField.setActionCommand(myPassString);
		//label for field
//		JLabel label = new JLabel(myPassString + ": ");
//		label.setLabelFor(myPassField);
		
		myPassField.addActionListener(this);
	    panel.add(myPassField);
		createPanelBorder("Change Password", panel);
		
		return panel;
	}
	
	private JPanel createPassEncrypt() {
		final JPanel panel = new JPanel();
		JCheckBox cb = new JCheckBox("Password Encryption", false);
		cb.addChangeListener(new PassEncryptListener());
		panel.add(cb);
		createPanelBorder("Encypt Password", panel);
		
		return panel;
	}
	
	private JPanel createNetEncrypt() {
		final JPanel panel = new JPanel();
		JCheckBox cb = new JCheckBox("Network Encryption", false);
		cb.addChangeListener(new NetEncryptListener());
		panel.add(cb);
		createPanelBorder("Encrypt Network Traffic", panel);
		
		return panel;
	}
	
	private JPanel createMacFilterCheck() {
		final JPanel panel = new JPanel();
		JCheckBox cb = new JCheckBox("Enable Mac Address Filtering", false);
		cb.addChangeListener(new MacFilterListener());
		panel.add(cb);
		createPanelBorder("Enable Mac Address Filtering", panel);
		
		return panel;
	}
	
	private JPanel createMacField() {
		final JPanel panel = new JPanel();
        //Create password field
		myMacField = new JTextField(FIELD_LENGTH);
		myMacField.setActionCommand(myMacString);
		//label for field
//		JLabel label = new JLabel(myPassString + ": ");
//		label.setLabelFor(myPassField);
		
		myMacField.addActionListener(this);
	    panel.add(myMacField);
	    createPanelBorder("Enter New Mac address", panel);
		
		return panel;
	}

	private void verifyPassword(final String theString) {
		final JFrame frame = new JFrame();
		frame.setSize(400, 400);
		
		myPassField1.setActionCommand(myVerifyPassword);
		myPassField1.addActionListener(this);
		
		myPassField2.setActionCommand(myVerifyPassword + "1");
		myPassField2.addActionListener(this);
		
		final JPanel panel = new JPanel(new GridLayout(2,1));
		final JPanel panel1 = new JPanel();
		final JPanel panel2 = new JPanel();
		
		createPanelBorder("Re-enter New Password", panel1);
		createPanelBorder("Verify Old Password", panel2);
		
		panel1.add(myPassField1);
		panel2.add(myPassField2);
		
		panel.add(panel1);
		panel.add(panel2);
		
		frame.add(panel);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		String text = "";
		if(cmd.equals(myPassString)) {
		    text = myPassField.getText();
		    verifyPassword(text);
		    myNewPassA = text;
			myPassField.selectAll();
		} else if(cmd.equals(myMacString)) {
			text = myMacField.getText();
			System.out.println(text);
//			if(myPlayer.isValidAdress(text)) {
//				myPlayer.addMacAdress(text):
//			}
			myMacField.selectAll();
		} else if(cmd.equals(myVerifyPassword)) {
			  //myNewPassB = myPassField1.getText();
		} else if(cmd.equals(myVerifyPassword + "1")) {	
			if (myOldPass.length() > 1) {
				myNewPassB = myPassField1.getText();
				myOldPass = myPassField2.getText();
			
			passwordVerification();
			
			myPassField.setText(null);
			myPassField1.setText(null);
			myPassField2.setText(null);
			}
		}
	}
	
	private void passwordVerification() {
		
		final boolean passChange = myPlayer.changePassword(myOldPass, myNewPassA
				, myNewPassB);
		if (passChange) {
		    JOptionPane.showMessageDialog(myGUI, "Success. Password has been changed.");
		} else {
			JOptionPane.showMessageDialog(myGUI, "Old password doesn't match. Password not changed");
		}
	}
	
	@Override 
	public void paintComponent(final Graphics theGraphics) {
		
		this.setOpaque(false);
		super.paintComponent(theGraphics);
		ImageIcon back_ground = new ImageIcon(NETWORK_BACKGROUND);
		theGraphics.drawImage(back_ground.getImage(), EmailScreen.TOP_LEFT_X, EmailScreen.TOP_LEFT_Y, 
										this.getWidth(), this.getHeight(), null);
		this.setOpaque(true);
	}
	
	class Network2MainListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			myGUI.remove(NetworkScreen.this);
			myGUI.setContentPane( myGUI.getMainScreen() );
			myGUI.invalidate();
			myGUI.validate();
		}
	}
	
	class PassEncryptListener implements ChangeListener {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			final JCheckBox cb = (JCheckBox) e.getSource();
			myPlayer.changePassEncryption(cb.isSelected());
//			System.out.println(cb.isSelected());
		}
		
	}
	
	class NetEncryptListener implements ChangeListener {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			final JCheckBox cb = (JCheckBox) e.getSource();
			myPlayer.changePassEncryption(cb.isSelected());
//			System.out.println(cb.isSelected());
		}
		
	}

	class MacFilterListener implements ChangeListener {
	
		@Override
		public void stateChanged(ChangeEvent e) {
			final JCheckBox cb = (JCheckBox) e.getSource();
			//myPlayer.enableAdressFiltering(cb.isSelected());
//			System.out.println(cb.isSelected());
		}
		
	}
	
}


