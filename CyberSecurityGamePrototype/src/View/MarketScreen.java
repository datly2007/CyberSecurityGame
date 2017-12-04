package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MarketScreen extends JPanel {
	
	private GUI myGUI;
	private JPanel myMainScreen;
	
	//private PlayerInfo myPlayer;

	public MarketScreen(final GUI theGUI, final JPanel theMain /*, thePlayer*/) {
		super(new BorderLayout());
		myGUI = theGUI;
		myMainScreen = theMain;
		setPreferredSize(new Dimension(GUI.SCREEN_SIZE, GUI.SCREEN_SIZE));
		
		final JPanel panel = new JPanel();
		
		
		final JPanel panel1 = new JPanel(new GridLayout(3, 1));
		final JButton button = new JButton("Back to main");
		button.addMouseListener(new Back2MainListener());
		
		panel1.add(button);
		panel.add(panel1, BorderLayout.CENTER);
		add(panel, BorderLayout.CENTER);
		
		
		GUI.createPanelBorder("Market Screen", this);
	}
	
	public void loadMenuBar(final boolean toLoad) {
		
		JMenuBar jmb = new JMenuBar();
		
		if(toLoad) {
			final JMenu upgrades = new JMenu("Upgrades");
			final JMenu newTools = new JMenu("New Tools");
			jmb.add(upgrades);
			jmb.add(newTools);
			myGUI.setJMenuBar(jmb);
			
		} else {
			myGUI.setMenuBar(null);
		}
	}
	
	class Back2MainListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			myGUI.switchPanel(myMainScreen);
			myGUI.setJMenuBar(null);
		}
	}
}
