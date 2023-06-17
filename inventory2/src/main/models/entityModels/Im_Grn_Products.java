package main.models.entityModels;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "im_GRN_Products")
public class Im_Grn_Products implements Serializable {

	@Id
	@Column(name = "GRN_ID")
	private int grnId;
	@Id
	@Column(name = "Product_ID")
	private int productID;

	@Column(name = "Batch_no")
	private int batchNo;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "Price")
	private double price;

	@Column(name = "hsn_code")
	private int hsnCode;

	@Column(name = "Bonus")
	private int bonus;

	@ManyToOne
	@JoinColumn(name = "GRN_Id", referencedColumnName = "GRN_Id", insertable = false, updatable = false)
	Im_GRN gil;

	public Im_Grn_Products() {

	}

	public Im_Grn_Products(int grnId, int productID, int batchNo, int quantity, double price, int hsnCode, int bonus) {
		super();
		this.grnId = grnId;
		this.productID = productID;
		this.batchNo = batchNo;
		this.quantity = quantity;
		this.price = price;
		this.hsnCode = hsnCode;
		this.bonus = bonus;
	}

	public int getGrnId() {
		return grnId;
	}

	public void setGrnId(int grnId) {
		this.grnId = grnId;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(int hsnCode) {
		this.hsnCode = hsnCode;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

}
