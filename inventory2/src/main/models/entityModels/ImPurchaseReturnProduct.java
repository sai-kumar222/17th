package main.models.entityModels;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "im_purchase_return_products")

public class ImPurchaseReturnProduct implements Serializable {

	@Id
	@Column(name = "purchase_return_id")
	private int purchaseReturnId;

	@Id
	@Column(name = "product_id")
	private int productId;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "last_updated_user")
	private String lastUpdatedUser;

	@Column(name = "last_updated_date")
	private LocalDate lastUpdatedDate;

	@ManyToOne
	@JoinColumn(name = "purchase_return_id", referencedColumnName = "purchase_return_id", insertable = false, updatable = false)
	private ImPurchaseReturn purchaseReturn;

	// Constructors, getters, and setters

	public ImPurchaseReturnProduct() {
	}

	public ImPurchaseReturnProduct(int purchaseReturnId, int productId, int quantity, String lastUpdatedUser,
			LocalDate lastUpdatedDate) {
		this.purchaseReturnId = purchaseReturnId;
		this.productId = productId;
		this.quantity = quantity;
		this.lastUpdatedUser = lastUpdatedUser;
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public int getPurchaseReturnId() {
		return purchaseReturnId;
	}

	public void setPurchaseReturnId(int purchaseReturnId) {
		this.purchaseReturnId = purchaseReturnId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	public LocalDate getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public ImPurchaseReturn getPurchaseReturn() {
		return purchaseReturn;
	}

	public void setPurchaseReturn(ImPurchaseReturn purchaseReturn) {
		this.purchaseReturn = purchaseReturn;
	}
}
