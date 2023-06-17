package main.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import main.models.JoinClass2;
import main.models.PurchaseId;
import main.models.PurchaseJoinClass;
import main.models.PurchaseReturnId;
import main.models.PurchasesFilter;
import main.models.PurchasesReturnFilter;
import main.models.joinclass;
import main.models.entityModels.Im_Purchase_Order;
import main.models.entityModels.Im_Purchase_Order_Products;
import main.models.entityModels.UserDetails;
import main.models.entityModels.im_products;
import main.models.entityModels.im_products_stock;
import main.models.loginModels.MailDetails;
import main.models.loginModels.credentials2;
import main.models.loginModels.password;
import main.models.warehouseModels.ProductName;
import main.models.warehouseModels.TotalStock;
import main.models.warehouseModels.productquant;

@Component
public class InventDAO {
	// Custom query methods, if needed
	@PersistenceContext
	EntityManager em;
	@Autowired
	private ApplicationContext applicationContext;
	productquant t1;
	ProductName r1;

	public ProductName getr1(@Autowired ProductName p) {
		return p;
	}

	public productquant gett1(@Autowired productquant p) {
		return p;
	}

	im_products r;
	im_products_stock t;
	Im_Purchase_Order f1;
	Im_Purchase_Order_Products u1;

	@Transactional
	public boolean persist(main.models.entityModels.UserDetails stud) {
		try {
			em.persist(stud);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Transactional
	public List<PurchaseId> getPurchaseId(PurchasesFilter p) {
		if (p.getVendor_id() == 0 && p.getPurchase_order_expected_date() == null) {
			List<PurchaseId> s = em
					.createQuery("SELECT new main.models.PurchaseId(s.purchase_order_id) from Im_Purchase_Order s",
							PurchaseId.class)
					.getResultList();
			for (PurchaseId x : s) {
				System.out.println(s.toString());
			}
			return s;
		}
		else if (p.getVendor_id() != 0 && p.getPurchase_order_expected_date() == null) {
			List<PurchaseId> s = em.createQuery(
					"SELECT new main.models.PurchaseId(s.purchase_order_id) from Im_Purchase_Order s where s.vendor_id=:v",
					PurchaseId.class).setParameter("v", p.getVendor_id()).getResultList();
			for (PurchaseId x : s) {
				System.out.println(s.toString());
			}
			return s;
	 }
			 else if (p.getVendor_id() == 0 && p.getPurchase_order_expected_date()!= null) {
					List<PurchaseId> s = em.createQuery(
							"SELECT new main.models.PurchaseId(s.purchase_order_id) from Im_Purchase_Order s where s.purchase_order_expected_date=:dat",
							PurchaseId.class).setParameter("dat", Date.valueOf(p.getPurchase_order_expected_date())).getResultList();
					for (PurchaseId x : s) {
						System.out.println(s.toString());
					}
			return s;
			 }
		 else {
			List<PurchaseId> s = em.createQuery(
					"SELECT new main.models.PurchaseId(s.purchase_order_id) from Im_Purchase_Order s where s.vendor_id=:v and s.purchase_order_expected_date=:dat",
					PurchaseId.class).setParameter("v", p.getVendor_id())
					.setParameter("dat", Date.valueOf(p.getPurchase_order_expected_date())).getResultList();
			for (PurchaseId x : s) {
				System.out.println(s.toString());
			}
			return s;
		}

	}		

	
	
	@Transactional
	public List<PurchaseReturnId> getPurchaseReturnsList(PurchasesReturnFilter p) {
		if (p.getVendor_id() == 0 && p.getPurchase_return_date() == null && p.getGrn_value()==0) {
			List<PurchaseReturnId> s = em
					.createQuery("SELECT new main.models.PurchaseReturnId(s.purchase_return_id) from ImPurchaseReturn s",
							PurchaseReturnId.class)
					.getResultList();
			for (PurchaseReturnId x : s) {
				System.out.println(s.toString());
			}
			return s;
		} else if (p.getVendor_id() != 0 && p.getPurchase_return_date() == null&& p.getGrn_value()==0) {
			List<PurchaseReturnId> s = em.createQuery(
					"SELECT new main.models.PurchaseReturnId(s.purchase_return_id) from ImPurchaseReturn s where s.vendor_id=:v",
					PurchaseReturnId.class).setParameter("v", p.getVendor_id()).getResultList();
			for (PurchaseReturnId x : s) {
				System.out.println(s.toString());
			}
			return s;
		} 
		else if (p.getVendor_id() != 0 && p.getPurchase_return_date() != null&& p.getGrn_value()==0) {
			List<PurchaseReturnId> s = em.createQuery(
					"SELECT new main.models.PurchaseReturnId(s.purchase_return_id) from ImPurchaseReturn s where s.vendor_id=:v and s.purchase_return_date=:v1",
					PurchaseReturnId.class).setParameter("v", p.getVendor_id()).setParameter("v1", Date.valueOf(p.getPurchase_return_date())).getResultList();
			for (PurchaseReturnId x : s) {
				System.out.println(s.toString());
			}
			return s;
		}
		else if (p.getVendor_id() != 0 && p.getPurchase_return_date() == null&& p.getGrn_value()!=0)
		{
			List<PurchaseReturnId> s = em.createQuery(
					"SELECT new main.models.PurchaseReturnId(s.purchase_return_id) from ImPurchaseReturn s where s.vendor_id=:v and s.grn_cost=:v1",
					PurchaseReturnId.class).setParameter("v", p.getVendor_id()).setParameter("v1",BigDecimal.valueOf( p.getGrn_value())).getResultList();
			return s;
		}
		else if (p.getVendor_id() == 0 && p.getPurchase_return_date() != null&& p.getGrn_value()==0)
		{
			List<PurchaseReturnId> s = em.createQuery(
					"SELECT new main.models.PurchaseReturnId(s.purchase_return_id) from ImPurchaseReturn s where s.purchase_return_date=:v1",
					PurchaseReturnId.class).setParameter("v1",Date.valueOf(p.getPurchase_return_date())).getResultList();
			return s;
		}
		else if (p.getVendor_id() == 0 && p.getPurchase_return_date() == null&& p.getGrn_value()!=0)
		{
			List<PurchaseReturnId> s = em.createQuery(
					"SELECT new main.models.PurchaseReturnId(s.purchase_return_id) from ImPurchaseReturn s where s.grn_cost=:v1",
					PurchaseReturnId.class).setParameter("v1",BigDecimal.valueOf( p.getGrn_value())).getResultList();
			return s;
		}
		else if (p.getVendor_id() == 0 && p.getPurchase_return_date() !=null&& p.getGrn_value()!=0)
		{
			List<PurchaseReturnId> s = em.createQuery(
					"SELECT new main.models.PurchaseReturnId(s.purchase_return_id) from ImPurchaseReturn s where s.grn_cost=:v1 and s.purchase_return_date=:v2",
					PurchaseReturnId.class).setParameter("v1",BigDecimal.valueOf( p.getGrn_value())).setParameter("v2",Date.valueOf(p.getPurchase_return_date())).getResultList();
			return s;
		}
		
		else {
			System.out.println(p.getPurchase_return_date());
			List<PurchaseReturnId> s = em.createQuery(
					
					"SELECT new main.models.PurchaseReturnId(s.purchase_return_id) from ImPurchaseReturn s where s.vendor_id=:v and s.purchase_return_date=:v1 and s.grn_cost=:v2",
					PurchaseReturnId.class).setParameter("v", p.getVendor_id()).setParameter("v1", Date.valueOf(p.getPurchase_return_date())).setParameter("v2", BigDecimal.valueOf(p.getGrn_value())).getResultList();
			for (PurchaseReturnId x : s) {
				System.out.println(s.toString());
			}
			return s;
		}

	}

	// @Transactional
	// public List<PurchaseId> getPurchaseReturnsList(PurchaseReturnId s) {
	//
	//
	// }

	@Transactional
	public List<PurchaseJoinClass> getPurchaseProducts(PurchaseId x) {
		List<Object> s = em.createQuery("SELECT s from Im_Purchase_Order s where s.purchase_order_id=:x")
				.setParameter("x", x.getPurchase_order_id()).getResultList();

		List<PurchaseJoinClass> l = new ArrayList<>();
		ArrayList<Im_Purchase_Order_Products> m = new ArrayList<>();

		for (Object q : s) {

			List<Im_Purchase_Order_Products> j = ((Im_Purchase_Order) q).getChildren();

			System.out.println(q);

			for (int i = 0; i < j.size(); i++)

			{
				System.out.println(((Im_Purchase_Order) q).toString());
				l.add(new PurchaseJoinClass((Im_Purchase_Order) q, j.get(i)));
			}
			break;

		}
		return l;
	}

	@Transactional
	public Im_Purchase_Order persistpurchase(Im_Purchase_Order stud) {
		try {
			em.persist(stud);
			List<Im_Purchase_Order_Products> p = stud.getChildren();
			for (Im_Purchase_Order_Products x : p) {
				System.out.println("hello");
				System.out.println(stud.getPurchase_order_id());
				x.setPurchase_order_id(stud.getPurchase_order_id());
				System.out.println(x.toString());
				em.persist(x);
			}
			return stud;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return stud;

	}

	@Transactional
	public List<joinclass> getAllData() {
		ArrayList<Object[]> s = (ArrayList<Object[]>) em
				.createQuery("SELECT s, p FROM im_products s JOIN im_products_stock p ON s.product_id = p.product_id")
				.getResultList();
		List<joinclass> l = new ArrayList<joinclass>();
		for (Object[] x : s) {
			for (int i = 0; i < x.length; i++) {
				if (i == 0) {
					r = (im_products) x[i];
					System.out.println(r.toString());
					System.out.println("a ra babu");
				} else {
					t = (im_products_stock) x[i];
					System.out.println(t.toString());
				}
			}
			l.add(new joinclass(t, r));
		}

		return l;

	}

	@Transactional
	public List<JoinClass2> getProductsCount() {
		ArrayList<Object[]> s = (ArrayList<Object[]>) em
				.createQuery("SELECT s, p FROM im_products s JOIN im_products_stock p ON s.product_id = p.product_id")
				.getResultList();

		List<JoinClass2> l = new ArrayList<JoinClass2>();
		for (Object[] x : s) {
			System.out.println(x.length);

			for (int i = 0; i < x.length; i++) {
				if (i == 0) {
					r = (im_products) x[i];
					r1 = applicationContext.getBean(ProductName.class);
					r1.setProduct_name(r.getProduct_name());
					System.out.println(r1.toString());
					System.out.println("a ra babu");
				} else {
					t = (im_products_stock) x[i];
					t1 = applicationContext.getBean(productquant.class);
					t1.setProduct_stock(t.getProduct_stock());

					System.out.println(t1.toString());
				}
			}
			l.add(new JoinClass2(t1, r1));
		}

		return l;

	}

	@Transactional
	public ArrayList<TotalStock> getTotalStock() {
		System.out.println("hello");
		ArrayList<TotalStock> ts = (ArrayList<TotalStock>) em
				.createQuery("SELECT s FROM TotalStock s ", TotalStock.class).getResultList();
		return ts;
	}

	@Transactional
	public int getCategoriesCount() {
		Long val = (Long) em.createQuery("select count(*) from  ProductCategories s").getSingleResult();
		return val.intValue();

	}

	@Transactional
	public double getWarehouseValue() {
		Long val = (Long) em.createQuery("select sum(s.product_cost) from im_products_stock  s").getSingleResult();
		return val.doubleValue();

	}

	@Transactional
	public int getVendorsCount() {
		Long val = (Long) em.createQuery("select count(*) from Im_vendor  s").getSingleResult();
		return val.intValue();

	}

	@Transactional
	public UserDetails check(MailDetails m) {
		Query q = em.createQuery("select s from UserDetails s where s.user_name=:email", UserDetails.class)
				.setParameter("email", m.getMail());
		System.out.println(m.getMail());
		UserDetails s = (UserDetails) q.getSingleResult();

		System.out.println(s.toString());
		return s;
	}

	@Transactional
	public void getData(MailDetails m, String num) {
		Query q = em.createQuery("select s from UserDetails s where s.user_name=:email", UserDetails.class)
				.setParameter("email", m.getMail());
		UserDetails s = (UserDetails) q.getSingleResult();
		s.setOtp(num);
		s.setOtp_expiry_time((LocalDateTime.now().plusMinutes(2)));
		System.out.println(s.toString());

	}

	@Transactional
	public UserDetails getRow(password p) {
		Query q = em.createQuery("select s from UserDetails s where s.user_name=:email", UserDetails.class)
				.setParameter("email", p.getMail());
		UserDetails s = (UserDetails) q.getSingleResult();

		System.out.println(s.toString());
		return s;

	}

	@Transactional
	public void getRow2(password p) {
		Query q = em.createQuery("select s from UserDetails s where s.user_name=:email", UserDetails.class)
				.setParameter("email", p.getMail());
		UserDetails s = (UserDetails) q.getSingleResult();
		s.setUser_password(p.getPass());
		System.out.println(p.getPass());
		System.out.println(s.toString());

	}

	@Transactional
	public UserDetails getAuthent(credentials2 s) {
		Query q = em.createQuery(
				"select s from UserDetails s where s.user_name=:email and s.user_password=:password and s.user_type=:usertype",
				UserDetails.class).setParameter("email", s.getUsername()).setParameter("password", s.getPassword())
				.setParameter("usertype", s.getUser_type());
		UserDetails ud = (UserDetails) q.getSingleResult();
		System.out.println(ud.toString());
		return ud;

	}

}
