package main.models.loginModels;

public class credentials {

	@Override
	public String toString() {
		return "credentials [username=" + username + ", Password=" + Password + "]";
	}

	private String username;
	private String Password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public credentials() {
		super();
	}

}
