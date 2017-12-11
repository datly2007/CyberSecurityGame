package View;

import Driver.GUI;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 *
 * @author Dat Ly
 * @author Raymond Schooley
 * @version 1.0
 */

public class MarketScreen extends JPanel {
	
	public static final String MARKET_BACKGROUND = "support_files/wallpapers/MarketPlace.jpg";
	
	private GUI myGUI;
	
	//private PlayerInfo myPlayer;

	public MarketScreen(final GUI theGUI) {
		
		myGUI = theGUI;
		
		startMarketScreen();
	}
	
	private void startMarketScreen() {
		
		this.setLayout(new BorderLayout());
		
		final JPanel bottom_panel = new JPanel();
		bottom_panel.setOpaque(false);
		
		final JButton back_button = new JButton("Back");
		back_button.addMouseListener(new Market2MainListener());
		back_button.setIcon(GUI.BACK_ICON);
		
		bottom_panel.add(back_button);
		
		add(bottom_panel, BorderLayout.SOUTH);
	}
	
	public JMenuBar getMenuBar() {
		
		JMenuBar jmb = new JMenuBar();
		
		final JMenu upgrades = new JMenu("Upgrades");
		final JMenu newTools = new JMenu("New Tools");
		jmb.add(upgrades);
		jmb.add(newTools);
		
		return jmb;
	}
	
	@Override 
	public void paintComponent(final Graphics theGraphics) {
		
		this.setOpaque(false);
		super.paintComponent(theGraphics);
		ImageIcon back_ground = new ImageIcon(MARKET_BACKGROUND);
		theGraphics.drawImage(back_ground.getImage(), EmailScreen.TOP_LEFT_X, EmailScreen.TOP_LEFT_Y, 
										this.getWidth(), this.getHeight(), null);
		this.setOpaque(true);
	}
	
	class Market2MainListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			myGUI.remove(MarketScreen.this);
			myGUI.setJMenuBar(null);
			myGUI.setContentPane( myGUI.getMainScreen() );
			myGUI.invalidate();
			myGUI.validate();
			
		}
	}
}
