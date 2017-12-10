package Driver;

import View.MarketScreen;
import View.NetworkScreen;
import View.EmailScreen;

import Model.PlayerInfo;

import javax.swing.*;

import Audio.Sound;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Provide a frame in which to simulate a Cyber-security videogame.
 *
 * @author Dat Ly
 * @author Raymond Schooley
 * @author Trung Thai 
 * @author Wes Stahl
 * @version 1.0
 */

public class GUI extends JFrame {
	
	public static final String MAIN_BACKGROUND = "support_files/wallpapers/MainScreen.jpg";
	
	public static final ImageIcon EMAIL_ICON = new ImageIcon("support_files/icons/Email-icon.png");
	
	public static final ImageIcon EXIT_ICON = new ImageIcon("support_files/icons/Close-icon.png");
	
	public static final ImageIcon MARKET_ICON = new ImageIcon("support_files/icons/Market-icon.png");
	
	public static final ImageIcon BACK_ICON = new ImageIcon("support_files/icons/Back-icon.png");
	
	public static final ImageIcon START_ICON = new ImageIcon("support_files/icons/Start-icon.png");
	
	public static final ImageIcon NETWORK_ICON = new ImageIcon("support_files/icons/Network-icon.png");
	
	public static final ImageIcon SUBMIT_ICON = new ImageIcon("support_files/icons/Lock-icon.png");
	
	public static final int OFF_SET = 40;
	
	private static final int DEFAULT_MUSIC_WIDTH = 300; 
	
	private static final int DEFAULT_MUSIC_HEIGHT = 100; 
	
	private JPanel myStartScreen;
	
	private JPanel myMainScreen;
	
	private MarketScreen myMarketScreen;
	
	private NetworkScreen myNetworkScreen;
	
	private EmailScreen myEmailScreen;
	
	private PlayerInfo myPlayer;

    /**
     * Create a JFrame that will be able to load different panels representing the 
     * different screens of the game.
     */
    public GUI(final String theName) {
    	
        super(theName);
       
        myPlayer = new PlayerInfo();
        myStartScreen = new JPanel();
        myMainScreen = new MainScreen();
        myEmailScreen = new EmailScreen(this, myPlayer);
        myNetworkScreen = new NetworkScreen(this, myPlayer);
        myMarketScreen = new MarketScreen(this);
       
        
        try {
        		setupMusic();
        } catch (final IOException e) {
        		JOptionPane.showMessageDialog(null, "Could not find file" 
        							+ e.getMessage(), "I/O Error", JOptionPane.ERROR_MESSAGE);
        }
        
        startGame();
    }
    
    private void startGame() {
    		
    		myStartScreen.setLayout(new BorderLayout());
    		
    		final JPanel top_panel = new JPanel();
    		final JPanel bottom_panel = new JPanel();
    		
    		final JButton start_button = new JButton("Start Game");
    		start_button.addMouseListener(new StartGameAdapter());
    		start_button.setIcon(START_ICON);
    		
    		final JButton exit_button = new JButton("Exit Game");
    		exit_button.addMouseListener(new ExitGameAdapter());
    		exit_button.setIcon(EXIT_ICON);
    		
    		ImageIcon im = new ImageIcon("support_files/wallpapers/gameLabel.jpg");
    		final JLabel label = new JLabel();
    		label.setIcon(im);
    		
    		top_panel.add(start_button);
    		top_panel.add(exit_button);
    		top_panel.setOpaque(false);
    		
    		bottom_panel.add(label);
    		bottom_panel.setOpaque(false);
    		
    		// Add all necessary components into main screen
    		myStartScreen.add(top_panel, BorderLayout.NORTH);
    		myStartScreen.add(bottom_panel, BorderLayout.SOUTH);
    		
    		myStartScreen.setBackground(Color.DARK_GRAY);
    		
    		add(myStartScreen);
    		setSize(610, 655);
    		final Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation(
                (int) ((kit.getScreenSize().getWidth() - this.getWidth()) / 2),
                (int) ((kit.getScreenSize().getHeight() - this.getHeight()) / 2));
        
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /** 
     * Add a separate JFrame for background music.
     * 
     * @throws IOException Will look for sound file, throw exception if not there.
     */
    public final void setupMusic() throws IOException {
        
        final JFrame frame = new Sound();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setSize(DEFAULT_MUSIC_WIDTH, DEFAULT_MUSIC_HEIGHT);
        frame.setVisible(true);
    }
    
	public JPanel getMainScreen() {
		
		return myMainScreen;
	}

    public static void main(final String[] theArgs) {
    	
    		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI("Cyber Security Game");
            }
        });
    }
    
    class MainScreen extends JPanel {
    	
    		MainScreen() {
    			
    			startMainScreen();
    		}
    		
    		private void startMainScreen() {
    			
    			setLayout(new BorderLayout());
    			
    			final JPanel north_panel = new JPanel();
    			final JPanel south_panel = new JPanel();
    			
    			final JPanel inner_south_panel = new JPanel();
    			
    			south_panel.setLayout(new BorderLayout());
    			
    			final JButton market_button = new JButton("Market Place");
    			market_button.addMouseListener(new GotoMarketAdapter());
    			market_button.setIcon(MARKET_ICON);
    			
    			final JButton network_button = new JButton("Network Configuration");
    			network_button.addMouseListener(new GotoNetworkAdapter());
    			network_button.setIcon(NETWORK_ICON);
    			
    			final JButton email_button = new JButton("Email");
    			email_button.addMouseListener(new GotoEmailAdapter());
    			email_button.setIcon(EMAIL_ICON);
    			
    			final JButton submit_button = new JButton("Submit");
    			submit_button.addMouseListener(new SubmitActionAdapter()); 
    			submit_button.setIcon(SUBMIT_ICON);
    			
    			final JButton exit_button = new JButton("Exit");
    			exit_button.addMouseListener(new ExitGameAdapter());
    			exit_button.setIcon(EXIT_ICON);
    			
    			north_panel.add(market_button);
    			north_panel.add(network_button);
    			north_panel.add(email_button);
    			
    			inner_south_panel.add(submit_button);
    			
    			south_panel.add(inner_south_panel, BorderLayout.NORTH);
    			south_panel.add(exit_button, BorderLayout.WEST);
    			
    			north_panel.setOpaque(false);
    			south_panel.setOpaque(false);
    			inner_south_panel.setOpaque(false);
    			
    			add(north_panel, BorderLayout.NORTH);
    			add(south_panel, BorderLayout.SOUTH);
    		}
    		
    		@Override 
    		public void paintComponent(final Graphics theGraphics) {
    			
    			setOpaque(false);
    			super.paintComponent(theGraphics);
    			ImageIcon back_ground = new ImageIcon(MAIN_BACKGROUND);
    			theGraphics.drawImage(back_ground.getImage(), EmailScreen.TOP_LEFT_X, EmailScreen.TOP_LEFT_Y, 
										this.getWidth(), this.getHeight(), null);
    			setOpaque(true);
		}
    }
    
    class GotoMarketAdapter extends MouseAdapter {
    	
    		@Override
    		public void mouseClicked(final MouseEvent e) {
    			
    			GUI.this.remove(myMainScreen);
    			GUI.this.setContentPane(myMarketScreen);
    			GUI.this.setJMenuBar(myMarketScreen.getMenuBar());
    			GUI.this.invalidate();
    			GUI.this.validate();
    			
    		}
    }
    
    class GotoNetworkAdapter extends MouseAdapter {
    	
    		@Override
    		public void mouseClicked(final MouseEvent e) {
    			GUI.this.remove(myMainScreen);
    			GUI.this.setContentPane(myNetworkScreen);
    			GUI.this.invalidate();
    			GUI.this.validate();
    		}
    }
    
    class GotoEmailAdapter extends MouseAdapter {
    		
    		@Override
    		public void mouseClicked(final MouseEvent e) {
    			GUI.this.remove(myMainScreen);
    			GUI.this.setContentPane(myEmailScreen);
    			GUI.this.invalidate();
    			GUI.this.validate();
    		}
    }
    
    class StartGameAdapter extends MouseAdapter {
    		
    		@Override
    		public void mouseClicked(final MouseEvent e) {
    			GUI.this.remove(myStartScreen);
    			GUI.this.setContentPane(myMainScreen);
    			GUI.this.invalidate();
    			GUI.this.validate();
    			
    			final Toolkit kit = Toolkit.getDefaultToolkit();
    			GUI.this.setSize(
    	                (int) (kit.getScreenSize().getWidth()),
    	                (int) (kit.getScreenSize().getHeight() - OFF_SET));
    			GUI.this.setLocation(EmailScreen.TOP_LEFT_X, EmailScreen.TOP_LEFT_Y);
    		}
    }
    
    class ExitGameAdapter extends MouseAdapter {
    		
    		@Override
    		public void mouseClicked(final MouseEvent e) {
    			GUI.this.dispatchEvent(new WindowEvent(GUI.this, 
                        WindowEvent.WINDOW_CLOSING));
    		}
    }
    // Needed to signal the requirement class to do the checking for requirement 
    class SubmitActionAdapter extends MouseAdapter {
    	
    		@Override 
    		public void mouseClicked(final MouseEvent e) {
    			
    		}
    }
}
   
   

