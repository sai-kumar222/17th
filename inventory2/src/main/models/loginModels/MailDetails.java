package main.models.loginModels;

public class MailDetails {

	private String Mail;

	public String getMail() {
		return Mail;
	}

	public void setMail(String mail) {
		Mail = mail;
	}

	@Override
	public String toString() {
		return "MailDetails [Mail=" + Mail + "]";
	}

	public MailDetails(String mail) {
		super();
		Mail = mail;
	}

	public MailDetails() {
		super();
	}

}
