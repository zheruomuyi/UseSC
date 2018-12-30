package water.ustc.action;

import java.io.Serializable;

public class UserInform implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4605315546391805865L;
	private String userEmail;
	private String userAddress;
	private int userAge;
	
	public String toString(){
		return "” œ‰£∫"+userEmail+"\n"+"µÿ÷∑£∫"+userAddress+"\n"+"ƒÍ¡‰£∫"+userAge;
	}
	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
