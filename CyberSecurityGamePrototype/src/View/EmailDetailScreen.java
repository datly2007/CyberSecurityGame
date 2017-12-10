package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Driver.GUI;
import Model.PlayerInfo;

/**
*
* @author Dat Ly
* @author Raymond Schooley
* @author Trung Thai 
* @author Wes Stahl
* @version 1.0
*/

public class EmailDetailScreen extends JPanel {

	private JPanel myEmailScreen;
	
	private String myPosition;
	
	private JTextArea myEmailBody;
	
	private PlayerInfo myPlayer;
	
	public EmailDetailScreen(final JPanel thePanel, final String thePos, final PlayerInfo thePlayer) {
		
		super();
		
		myEmailScreen = thePanel;
		myPosition = thePos;
		myEmailBody = new JTextArea(48, 100);
		myPlayer = thePlayer;
		
		openEmail();
	}
	
//	@Override 
//	public void paintComponent(final Graphics theGraphics) {
//		this.setOpaque(false);
//		super.paintComponent(theGraphics);
//		ImageIcon back_ground = new ImageIcon(EmailScreen.BACKGROUND_DIRECTORY);
//		theGraphics.drawImage(back_ground.getImage(), EmailScreen.TOP_LEFT_X, EmailScreen.TOP_LEFT_Y, 
//										this.getWidth(), this.getHeight(), null);
//		this.setOpaque(true);
//	}
	
	private void openEmail() {
		
		try {
			readFile();
		} catch (final IOException e) {
    			JOptionPane.showMessageDialog(null, "Could not find file" 
    				+ e.getMessage(), "I/O Error", JOptionPane.ERROR_MESSAGE);
		}
		
		setLayout(new BorderLayout());
		
		final JPanel north_panel = new JPanel();
		final JPanel south_panel = new JPanel();
		
		final JButton back_button = new JButton("Back");
		back_button.addMouseListener(new BacktoEmailScreen());
		back_button.setIcon(GUI.BACK_ICON);
		
		north_panel.add(myEmailBody);
		south_panel.add(back_button);
		
		add(north_panel, BorderLayout.NORTH);
		add(south_panel, BorderLayout.SOUTH);
		
		north_panel.setOpaque(false);
		south_panel.setOpaque(false);
		
		setBackground(Color.DARK_GRAY);
		setVisible(true);
	}
	
	private void readFile() throws IOException {
		
		String line; 
		File file_name = new File("email_storage/default_password_email.txt");
		
		FileReader file_reader = new FileReader(file_name);
		BufferedReader buffer = new BufferedReader(file_reader);
		
		while( (line = buffer.readLine()) != null) {
			myEmailBody.append("\n" + line);
		}
		
		myEmailBody.append(" " + myPlayer.getPass());
		
		buffer.close();
	}
	
	class BacktoEmailScreen extends MouseAdapter {
		
		@Override
		public void mouseClicked(final MouseEvent e) {
			EmailDetailScreen.this.firePropertyChange(myPosition, null, null);
		}
	}
}




