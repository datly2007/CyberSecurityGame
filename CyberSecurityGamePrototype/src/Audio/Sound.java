/*
 * TCSS 360 - Autumn 2017
 * Cyber-Security Game
 */

package Audio;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This class gets the song file and plays it in the Cyber-Security Game application.
 * @author Trung Thai
 * @author Credit to Ntiamoah Emmanuel on Youtube for the code to play music.
 * @version 1.0
 */
public class Sound extends JFrame {
    
    /** The Serialization ID. */
    private static final long serialVersionUID = -7821521218574097200L;

    /** Button for user to press to play music. */
    private JButton myPlay = new JButton("Play");
    
    /** Button for user to press to loop music. */
    private JButton myLoop = new JButton("Loop");
    
    /** Button for user to press to stop music. */
    private JButton myStop = new JButton("Stop");
    
    /** Gets the URL of the wav clip used to play game song. */
    private URL myUrl = Sound.class.getResource("Game-of-Thrones (1).wav");
    
    /** Audio clip used to get url to play song. */
    private AudioClip myClip = Applet.newAudioClip(myUrl);
    
    
    /** Constructor that initializes the Sound class. */
    public Sound() {
        super("Game-of-Thrones");
        setLayout(new FlowLayout());
        
        myPlay.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myClip.play();
            }
        });
        myLoop.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myClip.loop();
            }
        });
        myStop.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myClip.stop();
            }
        });
        add(myPlay);
        add(myLoop);
        add(myStop);
    }

}
