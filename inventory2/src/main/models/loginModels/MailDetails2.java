package main.models.loginModels;

import java.util.List;

public class MailDetails2 {

	private List<MailDetails> Mail;

	public List<MailDetails> getMail() {
		return Mail;
	}

	public void setMail(List<MailDetails> mail) {
		Mail = mail;
	}

	public MailDetails2(List<MailDetails> mail) {
		super();
		Mail = mail;
	}

	public MailDetails2() {
		super();
	}

	@Override
	public String toString() {
		return "MailDetails2 [Mail=" + Mail + "]";
	}

}
