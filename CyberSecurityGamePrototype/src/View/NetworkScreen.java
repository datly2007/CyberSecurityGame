package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NetworkScreen extends JPanel implements ActionListener {

/**Serial ID*/
	private static final long serialVersionUID = -800376005947915793L;
	
	//	private PlayerInfo myPlayer;
	private JTextField myPassField;
	private JTextField myMacField;
	
	private String myPassString = "PasswordField";
	private String myMacString = "MacField";
	private GUI myGui;
	
	public NetworkScreen(final GUI theGUI) {
		super(new BorderLayout());
		myGui = theGUI;
		setPreferredSize(new Dimension(GUI.SCREEN_SIZE, GUI.SCREEN_SIZE));
//		myPlayer = new PlayerInfo();
		
		JLabel screenTitle = new JLabel("NetworkScreen");
		//mainPanel contains all the components of network screen
		JPanel mainPanel = new JPanel(new GridLayout(5,1));
		
		final JPanel passwordPanel = createPassField();
		final JPanel passEncryptPanel = createPassEncrypt();
		final JPanel netEncryptPanel = createNetEncrypt();
		final JPanel enableMacFilter = createMacFilterCheck();
		final JPanel macField = createMacField();
		
		add(screenTitle, BorderLayout.NORTH);
		
		
		mainPanel.add(passwordPanel);
		mainPanel.add(passEncryptPanel);
		mainPanel.add(netEncryptPanel);
		mainPanel.add(enableMacFilter);
		mainPanel.add(macField);
		
		add(mainPanel, BorderLayout.CENTER);
	}
	
	private void createPanelBorder(final String theString, final JPanel thePanel) {
		thePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(theString),
                BorderFactory.createEmptyBorder(5,5,5,5)));
	}
	
	private JPanel createPassField() {
		final JPanel panel = new JPanel();
        //Create password field
		myPassField = new JTextField(10);
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
		myMacField = new JTextField(20);
		myMacField.setActionCommand(myMacString);
		//label for field
//		JLabel label = new JLabel(myPassString + ": ");
//		label.setLabelFor(myPassField);
		
		myMacField.addActionListener(this);
	    panel.add(myMacField);
	    createPanelBorder("Enter New Mac address", panel);
		
		return panel;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		String text = "";
		if(cmd.equals(myPassString)) {
		    text = myPassField.getText();
		    System.out.println(text);
		  //myPlayer.setPassword(text);			
			myPassField.selectAll();
		} else if(cmd.equals(myMacString)) {
			text = myMacField.getText();
			System.out.println(text);
//			if(myPlayer.isValidAdress(text)) {
//				myPlayer.addMacAdress(text):
//			}
			myMacField.selectAll();
		}
		
		

	}
	
	class PassEncryptListener implements ChangeListener {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			final JCheckBox cb = (JCheckBox) e.getSource();
			//myPlayer.passEncrypt(cb.isSelected());
//			System.out.println(cb.isSelected());
		}
		
	}
	
class NetEncryptListener implements ChangeListener {
		
		@Override
		public void stateChanged(ChangeEvent e) {
			final JCheckBox cb = (JCheckBox) e.getSource();
			//myPlayer.netEncrypt(cb.isSelected());
//			System.out.println(cb.isSelected());
		}
		
	}

class MacFilterListener implements ChangeListener {
	
	@Override
	public void stateChanged(ChangeEvent e) {
		final JCheckBox cb = (JCheckBox) e.getSource();
		//myPlayer.enableAdressFiltering(cb.isSelected());
//		System.out.println(cb.isSelected());
	}
	
}

}
