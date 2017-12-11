package Model;

//import javax.swing.JLabel;

import java.util.Timer;
import java.util.TimerTask;

public class Simulator {
	
	private int myHour;
	private int myMinute;
	private int myMinute1;
	//private JLabel myLabel;
	private Timer time;
	private boolean isEnd;
	
	public Simulator(/*JLabel theLabel*/) {
		super();
		myHour = 8;
		myMinute = 0;
		myMinute1 = 0;
		//myLabel = theLabel;
		time = new Timer();
		isEnd = false;
	}
	
	public /*JLabel*/ void currentTime() {
		time.schedule(new TimerTask() {

			@Override
			public void run() {
				/*if (myMinute < 10) {
					myLabel.setText(myHour + ":0" + myMinute);
				} else {
					myLabel.setText(myHour + ":" + myMinute);
				}*/
				checkTime();
			}
			
		}, 1000, 1000);
		//return myLabel;
	}

	private void checkTime() {
		myMinute++;
		myMinute1++;
		if (myMinute1 % 50 == 0) {
			//send email
		}
		if (myMinute == 60) {
			myHour++;
			myMinute = 0;
		}
		if (isEndHour() && isEndMinute()) {
			time.cancel();
			isEnd = true;
		}
	}
	
	private boolean isEndMinute() {
		return myMinute == 1;
	}

	private boolean isEndHour() {
		return 9 == myHour;
	}
	
	public boolean getEnd() {
		return isEnd;
	}
	
}
