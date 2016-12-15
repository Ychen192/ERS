package beans;

public class Users {
	private int userID;
	private String userName;
	private String passWord;
	private String firstName;
	private String lastName;
	private String email;
	private int roleID;

	public Users(int userID, String userName, String passWord, String firstName, String lastName, String email,
			int roleID) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roleID = roleID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	
	@Override
	public String toString() {
		return "Users( userID:" + userID +
						" userName:" + userName +
						" passWord:" + passWord +
						" firstName:" + firstName +
						" lastName:" + lastName +
						" email:" + email +
						" roleID:" + roleID;
	}
}
