package Driver;

import View.MarketScreen;
import View.NetworkScreen;
import View.EmailScreen;

import javax.swing.*;

import Audio.Sound;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Provide a frame in which to simulate a cyber-security videogame.
 *
 * @author Raymond Schooley
 * @author Dat Ly 
 * @author Trung Thai 
 * @author Wes Stahl
 * @version 1.0
 */
public class GUI extends JFrame {
	
	public static final String MAIN_BACKGROUND = "support_files/wallpapers/MainScreen.jpg";
	
	public static final int OFF_SET = 40;
	
	private static final int DEFAULT_MUSIC_WIDTH = 300; 
	
	private static final int DEFAULT_MUSIC_HEIGHT = 100; 
	
	private JPanel myStartScreen;
	
	private JPanel myMainScreen;
	
	private MarketScreen myMarketScreen;
	
	private NetworkScreen myNetworkScreen;
	
	private EmailScreen myEmailScreen;

    /**
     * Create a JFrame that will be able to load different panels representing the 
     * different screens of the game.
     */
    public GUI(final String theName) {
    	
        super(theName);
       
        myStartScreen = new JPanel();
        myMainScreen = new MainScreen();
        myEmailScreen = new EmailScreen(this);
        myNetworkScreen = new NetworkScreen(this);
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
    		
    		final JButton exit_button = new JButton("Exit Game");
    		exit_button.addMouseListener(new ExitGameAdapter());
    		
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
    		
    		myStartScreen.setBackground(Color.CYAN);
    		
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
    			
    			JButton market_button = new JButton("Market Place");
    			market_button.addMouseListener(new GotoMarketAdapter());
    			
    			JButton network_button = new JButton("Network Configuration");
    			network_button.addMouseListener(new GotoNetworkAdapter());
    			
    			JButton email_button = new JButton("Email");
    			email_button.addMouseListener(new GotoEmailAdapter());
    			
    			add(market_button);
    			add(network_button);
    			add(email_button);
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
    			GUI.this.invalidate();
    			GUI.this.validate();
    			GUI.this.setJMenuBar(myMarketScreen.getMenuBar());
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
    			System.exit(0);
    		}
    }
}
   
   

