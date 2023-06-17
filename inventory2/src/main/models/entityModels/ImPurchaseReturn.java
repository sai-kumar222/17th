package main.models.entityModels;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "im_purchase_return")
public class ImPurchaseReturn {

	@Id
	@Column(name = "purchase_return_id")
	private int purchase_return_id;

	@Column(name = "purchase_return_date")
	private Timestamp purchase_return_date;

	@Column(name = "grn_no")
	private int grn_no;

	@Column(name = "grn_cost")
	private BigDecimal grn_cost;

	@Column(name = "vendor_id")
	private int vendor_id;

	@Column(name = "purchase_status")
	private String purchase_status;

	@Column(name = "purchase_return_description")
	private String purchase_return_description;

	@Column(name = "last_updated_user")
	private String last_updated_user;

	@Column(name = "last_updated_date")
	private LocalDate last_updated_date;

	@OneToMany(mappedBy = "purchaseReturn", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ImPurchaseReturnProduct> purchaseReturnProducts;

	// Constructors, getters, and setters

	public ImPurchaseReturn() {
	}

	public ImPurchaseReturn(int purchaseReturnId, Timestamp purchaseReturnDate, int grnNo, BigDecimal grnCost,
			int vendorId, String purchaseStatus, String purchaseReturnDescription, String lastUpdatedUser,
			LocalDate lastUpdatedDate) {
		this.purchase_return_id = purchaseReturnId;
		this.purchase_return_date = purchaseReturnDate;
		this.grn_no = grnNo;
		this.grn_cost = grnCost;
		this.vendor_id = vendorId;
		this.purchase_status = purchaseStatus;
		this.purchase_return_description = purchaseReturnDescription;
		this.last_updated_user = lastUpdatedUser;
		this.last_updated_date = lastUpdatedDate;
	}

	public int getPurchaseReturnId() {
		return purchase_return_id;
	}

	public void setPurchaseReturnId(int purchaseReturnId) {
		this.purchase_return_id = purchaseReturnId;
	}

	public Timestamp getPurchaseReturnDate() {
		return purchase_return_date;
	}

	public void setPurchaseReturnDate(Timestamp purchaseReturnDate) {
		this.purchase_return_date = purchaseReturnDate;
	}

	public int getGrnNo() {
		return grn_no;
	}

	public void setGrnNo(int grnNo) {
		this.grn_no = grnNo;
	}

	public BigDecimal getGrnCost() {
		return grn_cost;
	}

	public void setGrnCost(BigDecimal grnCost) {
		this.grn_cost = grnCost;
	}

	public int getVendorId() {
		return vendor_id;
	}

	public void setVendorId(int vendorId) {
		this.vendor_id = vendorId;
	}

	public String getPurchaseStatus() {
		return purchase_status;
	}

	public void setPurchaseStatus(String purchaseStatus) {
		this.purchase_status = purchaseStatus;
	}

	public String getPurchaseReturnDescription() {
		return purchase_return_description;
	}

	public void setPurchaseReturnDescription(String purchaseReturnDescription) {
		this.purchase_return_description = purchaseReturnDescription;
	}

	public String getLastUpdatedUser() {
		return last_updated_user;
	}

	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.last_updated_user = lastUpdatedUser;
	}

	public LocalDate getLastUpdatedDate() {
		return last_updated_date;
	}

	public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
		this.last_updated_date = lastUpdatedDate;
	}

	public List<ImPurchaseReturnProduct> getPurchaseReturnProducts() {
		return purchaseReturnProducts;
	}

	public void setPurchaseReturnProducts(List<ImPurchaseReturnProduct> purchaseReturnProducts) {
		this.purchaseReturnProducts = purchaseReturnProducts;
	}

	@Override
	public String toString() {
		return "ImPurchaseReturn [purchase_return_id=" + purchase_return_id + ", purchase_return_date="
				+ purchase_return_date + ", grn_no=" + grn_no + ", grn_cost=" + grn_cost + ", vendor_id=" + vendor_id
				+ ", purchase_status=" + purchase_status + ", purchase_return_description="
				+ purchase_return_description + ", last_updated_user=" + last_updated_user + ", last_updated_date="
				+ last_updated_date + ", purchaseReturnProducts=" + purchaseReturnProducts + "]";
	}
}
