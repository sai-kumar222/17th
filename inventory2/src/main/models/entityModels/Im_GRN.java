package main.models.entityModels;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "im_GRN")
public class Im_GRN {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GRN_ID")
	private int grnId;

	@Column(name = "GRN_Date")
	private Date grnDate;

	@Column(name = "Purchase_Order_ID")
	private int purchaseOrderdD;

	@Column(name = "GRN_Amount")
	private double grnAmount;

	@Column(name = "GRN_Status")
	private String grnStatus;

	@OneToMany(mappedBy = "gil")
	List<Im_Grn_Products> productsList;

	public Im_GRN() {

	}

	public Im_GRN(int grnId, Date grnDate, int purchaseOrderdD, double grnAmount, String grnStatus) {
		super();
		this.grnId = grnId;
		this.grnDate = grnDate;
		this.purchaseOrderdD = purchaseOrderdD;
		this.grnAmount = grnAmount;
		this.grnStatus = grnStatus;
	}

	public int getGrnId() {
		return grnId;
	}

	public void setGrnId(int grnId) {
		this.grnId = grnId;
	}

	public Date getGrnDate() {
		return grnDate;
	}

	public void setGrnDate(Date grnDate) {
		this.grnDate = grnDate;
	}

	public int getPurchaseOrderdD() {
		return purchaseOrderdD;
	}

	public void setPurchaseOrderdD(int purchaseOrderdD) {
		this.purchaseOrderdD = purchaseOrderdD;
	}

	public double getGrnAmount() {
		return grnAmount;
	}

	public void setGrnAmount(double grnAmount) {
		this.grnAmount = grnAmount;
	}

	public String getGrnStatus() {
		return grnStatus;
	}

	public void setGrnStatus(String grnStatus) {
		this.grnStatus = grnStatus;
	}

}
