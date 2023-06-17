package main.models.entityModels;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "im_users")
public class UserDetails {
	private String user_name;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	private String user_password;
	private String otp;
	private LocalDateTime otp_expiry_time;
	private String user_type;
	private String status;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getOtp_expiry_time() {
		return otp_expiry_time;
	}

	public void setOtp_expiry_time(LocalDateTime otp_expiry_time) {
		this.otp_expiry_time = otp_expiry_time;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserDetails [user_name=" + user_name + ", user_id=" + user_id + ", user_password=" + user_password
				+ ", otp=" + otp + ", otp_expiry_time=" + otp_expiry_time + ", user_type=" + user_type + ", status="
				+ status + "]";
	}

}
