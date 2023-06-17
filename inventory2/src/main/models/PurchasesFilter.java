package main.models;

public class PurchasesFilter {
	private int vendor_id;

	private String purchase_order_expected_date = null;

	public int getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}

	public PurchasesFilter(int vendor_id, String purchase_order_expected_date) {
		super();
		this.vendor_id = vendor_id;
		if (purchase_order_expected_date != "")
			this.purchase_order_expected_date = purchase_order_expected_date;
	}

	@Override
	public String toString() {
		return "PurchasesFilter [vendor_id=" + vendor_id + ", purchase_order_expected_date="
				+ purchase_order_expected_date + "]";
	}

	public String getPurchase_order_expected_date() {
		return purchase_order_expected_date;
	}

	public void setPurchase_order_expected_date(String purchase_order_expected_date) {
		if (purchase_order_expected_date != "")
			this.purchase_order_expected_date = purchase_order_expected_date;
	}

	public PurchasesFilter() {
		super();
	}

}
