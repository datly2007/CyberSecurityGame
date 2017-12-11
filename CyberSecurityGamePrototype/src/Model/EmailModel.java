package Model;

public class EmailModel {
	private String myHeader;
	private String myBody;
	private boolean myPhish;
	private String mySender;
	private String myRecipiant;
	
	public EmailModel(String theHeader, String theBody, boolean thePhish, String theSender, String theRecipiant) {
		myHeader = theHeader;
		myBody = theBody;
		myPhish = thePhish;
		mySender = theSender;
		myRecipiant = theRecipiant;
	}
	
	public String getHeader() {
		return myHeader;
	}
	
	public String getBody() {
		return myBody;
	}
	
	public boolean getPhish() {
		return myPhish;
	}
	
	public String getSender() {
		return mySender;
	}
	
	public String gertRecipiant() {
		return myRecipiant;
	}
}
