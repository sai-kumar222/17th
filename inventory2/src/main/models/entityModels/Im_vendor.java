package main.models.entityModels;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "im_vendor")
public class Im_vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vendor_id;

	private String vendor_name;

	private BigInteger vendor_phonenumber;

	private String status;

	private String last_updated_user;

	private Date last_updated_date;

	public int getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}

	public Im_vendor(int vendor_id, String vendor_name, BigInteger vendor_phonenumber, String status,
			String last_updated_user, Date last_updated_date) {
		super();
		this.vendor_id = vendor_id;
		this.vendor_name = vendor_name;
		this.vendor_phonenumber = vendor_phonenumber;
		this.status = status;
		this.last_updated_user = last_updated_user;
		this.last_updated_date = last_updated_date;
	}

	public String getVendor_name() {
		return vendor_name;
	}

	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}

	public BigInteger getVendor_phonenumber() {
		return vendor_phonenumber;
	}

	public void setVendor_phonenumber(BigInteger vendor_phonenumber) {
		this.vendor_phonenumber = vendor_phonenumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLast_updated_user() {
		return last_updated_user;
	}

	public void setLast_updated_user(String last_updated_user) {
		this.last_updated_user = last_updated_user;
	}

	public Date getLast_updated_date() {
		return last_updated_date;
	}

	@Override
	public String toString() {
		return "Im_vendor [vendor_id=" + vendor_id + ", vendor_name=" + vendor_name + ", vendor_phonenumber="
				+ vendor_phonenumber + ", status=" + status + ", last_updated_user=" + last_updated_user
				+ ", last_updated_date=" + last_updated_date + "]";
	}

	public Im_vendor() {
		super();
	}

	public void setLast_updated_date(Date last_updated_date) {
		this.last_updated_date = last_updated_date;
	}

}