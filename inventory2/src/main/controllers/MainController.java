package main.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.bll.RandomNumber;
import main.dao.JpaService;
import main.models.entityModels.UserDetails;
import main.models.loginModels.MailDetails;
import main.models.loginModels.ModelData;
import main.models.loginModels.credentials;
import main.models.loginModels.credentials2;
import main.models.loginModels.password;

@Controller
public class MainController {
	JpaService x;
	@Autowired
	RandomNumber rn;
	int i = 0;
	HashMap<String, ModelData> modelattr = new HashMap<String, ModelData>();

	@GetMapping("/HomeProcurement")
	public String getIndex() {
		return "procurementViews/HomeProcurement";
	}

	@GetMapping("/grnData")
	public String getGrn() {
		return "procurementViews/grnlist";
	}

	@GetMapping("/createGRN")
	public String createGRN() {
		return "procurementViews/create-grn";
	}

	@GetMapping("/purchaseOrderData")
	public String purchaseOrderData() {
		return "procurementViews/purchase order data";
	}

	@GetMapping("/createPurchaseOrder")
	public String createPurchaseOrder() {
		return "procurementViews/purchase orders";
	}

	@GetMapping("/createNewPurchaseOrders")
	public String createNewPurchaseOrders() {
		return "procurementViews/purchase orders from scratch";
	}

	@GetMapping("/indents")
	public String indents() {
		return "procurementViews/indents";
	}

	@GetMapping("/prnData")
	public String prnData() {
		return "procurementViews/prnlist";
	}

	@GetMapping("/createPRN")
	public String createPRN() {
		return "procurementViews/createprn";
	}

	@GetMapping("/addHSN")
	public String addHSN() {
		return "procurementViews/addHSN";
	}

	@GetMapping("/addProductCategory")
	public String addProductCategory() {
		return "procurementViews/addProductCategory";
	}

	@GetMapping("/addProduct")
	public String addProduct() {
		return "procurementViews/addProduct";
	}

	@GetMapping("/warehousestock")
	public String warehouse() {
		return "procurementViews/Warehouse";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "loginViews/Home";
	}

	@Autowired
	public MainController(JpaService cla) {
		x = cla;
	}

	@RequestMapping(value = "/forgotrequest", method = RequestMethod.GET)
	public String request() {
		return "loginViews/OtpReq";
	}

	// @RequestMapping(value = "/multiple", method = RequestMethod.POST)
	// public String requestmultiple(MailDetails2 m) {
	// System.out.println("hello");
	// System.out.println(m);
	// List<String> ss = m.getMail();
	// for (String x : ss) {
	// System.out.println(x);
	// }
	// System.out.println(m.toString());
	// return "OtpReq";
	// }
	@RequestMapping(value = "/multiple", method = RequestMethod.POST)
	public String requestmultiple(@RequestBody String json) {
		System.out.println("hello");
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<credentials> mailDetailsList = mapper.readValue(json, new TypeReference<List<credentials>>() {
			});
			for (credentials x : mailDetailsList) {
				System.out.println("inside try");
				System.out.println(x.toString());
			}
			// Process the received mailDetailsList
			// Rest of the code...
		} catch (IOException e) {
			// Handle exception
			System.out.println(e);
		}

		return "loginViews/OtpReq";
	}

	@RequestMapping(value = "/changepass", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String changepass(password p) {
		UserDetails s = x.getRow(p);
		System.out.println(s.toString());
		if (!s.getOtp_expiry_time().isAfter(LocalDateTime.now())) {
			System.out.println(s.getOtp_expiry_time());
			return "failed";
		} else if (s.getOtp().equals(p.getOtp())) {
			x.getRow2(p);
			return "success";
		} else {
			return "failed";
		}
	}

	@RequestMapping(value = "/sendotp", method = RequestMethod.GET)
	public String sendmail(MailDetails md, Model m) throws MessagingException {
		i++;
		System.out.println(i);
		Properties prop = new Properties();
		m.addAttribute("mail", md.getMail());
		prop.put("mail.smtp.host", "smtp-mail.outlook.com");
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		prop.put("mail.smtp.ssl.ciphersuites", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");

		Session sess = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("devarmadd5@hotmail.com", "my mother43");
			}

		});
		prop.put("mail.smtp.ssl.trust", "*");
		sess.setDebug(true);
		int number = rn.getNumber();

		Message msg = new MimeMessage(sess);
		msg.setFrom(new InternetAddress("devarmadd5@hotmail.com"));

		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(md.getMail()));
		msg.setSubject("password reset");
		msg.setText("your otp is :" + number);
		Transport.send(msg);
		x.getDat(md, String.valueOf(number));

		return "loginViews/ChangePassword";

	}

	public MainController() {

	}

	@RequestMapping(value = "/check", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String check(MailDetails md, Model m) throws MessagingException {
		System.out.println("hello");
		System.out.println("mail" + md.getMail());
		return String.valueOf(x.check(md));

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String login(credentials2 md, Model m) throws MessagingException {
		System.out.println("hello");
		System.out.println(x.getAuthent(md));
		return x.getAuthent(md);

	}

	// @RequestMapping(value = "/register", method = RequestMethod.POST)
	// @ResponseBody
	// public String register(UserDetails md, HttpSession s) throws MessagingException {
	//
	// boolean b=x.add(md);
	// if(b)
	// {
	//
	// return "redirect:/sendotp";
	// }
	//
	//
	// }
	@Scheduled(fixedDelay = 100)
	public void removeExpiredAttributes() {
		LocalDateTime currentTime = LocalDateTime.now();

		for (String attributeName : modelattr.keySet()) {
			ModelData attributeData = modelattr.get(attributeName);
			if (currentTime.isAfter(attributeData.getExpire())) {
				modelattr.remove(attributeName);
				System.out.println(attributeName + "deleted");
			}
		}
	}
}