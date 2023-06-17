package main.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import main.bll.TotalStockbll;
import main.models.JoinClass2;
import main.models.PurchaseId;
import main.models.PurchaseJoinClass;
import main.models.PurchaseReturnId;
import main.models.PurchasesFilter;
import main.models.PurchasesReturnFilter;
import main.models.joinclass;
import main.models.entityModels.Im_Purchase_Order;
import main.models.entityModels.UserDetails;
import main.models.loginModels.MailDetails;
import main.models.loginModels.credentials2;
import main.models.loginModels.password;
import main.models.warehouseModels.ProductCategoryCount;
import main.models.warehouseModels.TotalOverallStock;
import main.models.warehouseModels.TotalStock;
import main.models.warehouseModels.TotalWarehouseVal;
import main.models.warehouseModels.VendorCount;

@Component
public class JpaService {
	InventDAO sd;
	@Autowired
	VendorCount vc;
	@Autowired
	TotalStockbll tb;
	@Autowired
	ProductCategoryCount pcc;
	@Autowired
	TotalWarehouseVal twv;
	@Autowired
	TotalOverallStock tsc;

	@Autowired
	public JpaService(@Qualifier("InventDAO") InventDAO sd) {
		super();
		this.sd = sd;
	}

	public void persistpurchase(Im_Purchase_Order stud) {
		Im_Purchase_Order l = sd.persistpurchase(stud);
		System.out.println(stud.getPurchase_order_id());

	}

	public List<PurchaseJoinClass> getPurchaseProducts(PurchaseId x) {
		List<PurchaseJoinClass> l = sd.getPurchaseProducts(x);

		return l;

	}
	public List<PurchaseReturnId> getPurchaseReturnsList(PurchasesReturnFilter p) {
		
		return sd.getPurchaseReturnsList(p);
	}

	public List<PurchaseId> getPurchaseId(PurchasesFilter p) {
		List<PurchaseId> s = sd.getPurchaseId(p);
		for (PurchaseId x : s) {
			System.out.println(s.toString());
		}
		return s;

	}

	public TotalOverallStock getTotalStock() {
		ArrayList<TotalStock> ts = sd.getTotalStock();

		tsc.setTotal_product_stock(tb.getTotalStockQuantity(ts));
		TotalOverallStock tsc2 = tsc;
		return tsc2;

	}

	public List<JoinClass2> getProductsCount() {

		return sd.getProductsCount();

	}

	public TotalWarehouseVal getWarehouseValue() {
		double val = sd.getWarehouseValue();
		twv.setTotal_warehouse_value(val);
		return twv;

	}

	public ProductCategoryCount getCategoriesCount() {
		pcc.setTotal_product_category_count(sd.getCategoriesCount());
		System.out.println(pcc.toString());
		return pcc;

	}

	public VendorCount getVendorsCount() {

		vc.setVendorcount(sd.getVendorsCount());
		return vc;

	}

	public boolean add(UserDetails s) {
		return sd.persist(s);
	}

	public List<joinclass> getAllData() {
		return sd.getAllData();
	}

	public boolean check(MailDetails m) {
		try {
			UserDetails s = sd.check(m);
		} catch (NoResultException e) {
			// No entity found, return false
			return false;
		}

		return true;

	}

	public String getAuthent(credentials2 c) {
		try {
			UserDetails s = sd.getAuthent(c);
		} catch (NoResultException e) {
			// No entity found, return false
			return "login failed";
		}

		return "login success";
	}

	public void getDat(MailDetails m, String num) {
		System.out.println("hello");
		sd.getData(m, num);
		System.out.println("hello");
	}

	public UserDetails getRow(password m) {
		return sd.getRow(m);
	}

	public void getRow2(password p) {
		sd.getRow2(p);

	}

	// public List<PurchaseId> getPurchaseReturnsList() {
	//
	// }

}