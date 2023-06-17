package main.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.JpaService;
import main.models.PurchaseId;
import main.models.PurchaseJoinClass;
import main.models.PurchasesFilter;
import main.models.entityModels.Im_Purchase_Order;

@Controller
public class PurchasesController {
	JpaService x;

	@Autowired
	public PurchasesController(JpaService cla) {
		x = cla;
	}

	@RequestMapping(value = "/getPurchaseId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getPurchaseId(@ModelAttribute PurchasesFilter p, Model m)
			throws MessagingException, JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
		System.out.println(p.getVendor_id());
		System.out.println(p.getPurchase_order_expected_date());

		List<PurchaseId> s = x.getPurchaseId(p);
		ObjectMapper ob = new ObjectMapper();
		System.out.println(ob.writeValueAsString(s));
		return ob.writeValueAsString(s);

	}

	@RequestMapping(value = "/getPurchaseProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getPurchaseProducts(PurchaseId p) throws JsonProcessingException {
		List<PurchaseJoinClass> s = x.getPurchaseProducts(p);
		ObjectMapper ob = new ObjectMapper();
		System.out.println(ob.writeValueAsString(s));
		return ob.writeValueAsString(s);

	}

//	@RequestMapping(value = "/makePurchseOrder", method = RequestMethod.GET)
//	@ResponseBody
//	
//		public void makePurchseOrder(@RequestBody String json, Model m)
//				throws MessagingException, JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
//			String json2 = json.toString();
//			String escapedJson = JSONObject.quote(json);
//
//			ObjectMapper objectMapper = new ObjectMapper();
//			// String json1 =
//			// "{\"purchase_order_date\":\"2023-06-13\",\"purchase_order_amount\":3000.0,\"vendor_id\":1,\"purchase_order_expected_date\":\"2023-06-20\",\"purchase_order_status\":\"Active\",\"user_id\":2,\"last_updated_user\":\"John
//			// Doe\",\"last_updated_date\":\"2023-06-13\",\"children\":[{\"product_id\":1,\"purchase_order_quantity\":10,\"negotiation_price\":100,\"quantity_received\":5},{\"product_id\":2,\"purchase_order_quantity\":5,\"negotiation_price\":50,\"quantity_received\":0}]}";
//			// System.out.println(json1.equals(escapedJson));
//			System.out.println("hii" + json);
//			Im_Purchase_Order purchaseOrder = objectMapper.readValue(json, Im_Purchase_Order.class);
//
//			System.out.println(purchaseOrder.toString());
//			x.persistpurchase(purchaseOrder);
//
//			// Process the parent and child data as needed
//			// ...
//
//			// Print the received data
//
//		}


	

	@RequestMapping(value = "/makePurchseOrder", method = RequestMethod.POST)

	public void makePurchseOrder(@RequestBody String json, Model m)
			throws MessagingException, JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
		String json2 = json.toString();
		String escapedJson = JSONObject.quote(json);

		ObjectMapper objectMapper = new ObjectMapper();
		// String json1 =
		// "{\"purchase_order_date\":\"2023-06-13\",\"purchase_order_amount\":3000.0,\"vendor_id\":1,\"purchase_order_expected_date\":\"2023-06-20\",\"purchase_order_status\":\"Active\",\"user_id\":2,\"last_updated_user\":\"John
		// Doe\",\"last_updated_date\":\"2023-06-13\",\"children\":[{\"product_id\":1,\"purchase_order_quantity\":10,\"negotiation_price\":100,\"quantity_received\":5},{\"product_id\":2,\"purchase_order_quantity\":5,\"negotiation_price\":50,\"quantity_received\":0}]}";
		// System.out.println(json1.equals(escapedJson));
		System.out.println("hii" + json);
		Im_Purchase_Order purchaseOrder = objectMapper.readValue(json, Im_Purchase_Order.class);

		System.out.println(purchaseOrder.toString());
		x.persistpurchase(purchaseOrder);

		// Process the parent and child data as needed
		// ...

		// Print the received data

	}

}