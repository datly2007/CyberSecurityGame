package Driver;

import View.MarketScreen;
import View.NetworkScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Provide a frame in which to simulate a cyber-security videogame.
 *
 * @author Raymond Schooley
 * @version 1.0
 */
public class GUI extends JFrame
{
	/**Serial ID*/
	private static final long serialVersionUID = -2095830580749127423L;
	
	public static final int SCREEN_SIZE = 800;
	
	private JPanel myEmailScreen;

	private JPanel myMainScreen;
	
	private MarketScreen myMarketScreen;
	
	private NetworkScreen myNetworkScreen;
	
	private JPanel myCurrentPanel;

    /**
     * Create a JFrame that will be able to load different panels representing the 
     * different screens of the game.
     */
    public GUI(String theName)
    {
        super(theName);
        setVisible(true);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myMainScreen = createMainScreen();
        myEmailScreen = new JPanel();
        myNetworkScreen = new NetworkScreen(this);
        myMarketScreen = new MarketScreen(this, myMainScreen);
        myCurrentPanel = myMainScreen;
        add(myMainScreen,BorderLayout.CENTER);
        pack();
         
    }
	
	private JPanel createMainScreen() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(SCREEN_SIZE, SCREEN_SIZE));
		
		JPanel panel1 = new JPanel(new GridLayout(3, 1));
		
		JButton marketButton = new JButton();
		marketButton.addMouseListener(new Main2MarketAdapter());
		panel1.add(marketButton);
		
		panel.add(new JLabel("MainScreen"), BorderLayout.NORTH);
		
		JButton netButton = new JButton();
		netButton.addMouseListener(new Main2NetworkAdapter());
		panel1.add(netButton);
		
		panel.add(panel1, BorderLayout.CENTER);
		
		return panel;
		
	}
	
//	private JPanel createMarketScreen() {
//		JPanel panel = new JPanel(new BorderLayout());
//		panel.setPreferredSize(new Dimension(SCREEN_SIZE, SCREEN_SIZE));
//		JButton button = new JButton();
//		button.addMouseListener(new Market2MainAdapter());
//		panel.add(button, BorderLayout.CENTER);
//		panel.add(new JLabel("MarketScreen"), BorderLayout.NORTH);
//		
//		return panel;
//	}
	
	public static void createPanelBorder(final String theString, final JPanel thePanel) {
		thePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(theString),
                BorderFactory.createEmptyBorder(5,5,5,5)));
	}
	
	public void switchPanel(JPanel thePanel) {
		remove(myCurrentPanel);
		add(thePanel);
		myCurrentPanel = thePanel;
		pack();
	}
    

    public static void main(String[] theArgs) {
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI("CyberSecurity");
            }
        });
    }
    
    
    class Main2MarketAdapter extends MouseAdapter {
    	
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		switchPanel(myMarketScreen);
    		myMarketScreen.loadMenuBar(true);
    	}
    }
    
    class Market2MainAdapter extends MouseAdapter {
    	
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		switchPanel(myMainScreen);
    	}
    }
    

    class Main2NetworkAdapter extends MouseAdapter {
    	
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		switchPanel(myNetworkScreen);
    	}
    }
}
   
   

