package main.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.JpaService;
import main.models.PurchaseId;
import main.models.PurchaseReturnId;
import main.models.PurchasesReturnFilter;

@Controller
public class PurchaseReturnController {
	JpaService x;

	@Autowired
	public PurchaseReturnController(JpaService cla) {
		x = cla;
	}

	@RequestMapping(value = "/getPurchaseReturnsList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getPurchaseReturnsList(@ModelAttribute PurchasesReturnFilter p, Model m)
			throws MessagingException, JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
		System.out.println(p.getGrn_value());
		System.out.println(p.getPurchase_return_date());
		System.out.println(p.getVendor_id());
		List<PurchaseReturnId> s=x.getPurchaseReturnsList(p);
		ObjectMapper ob = new ObjectMapper();
		System.out.println(ob.writeValueAsString(s));
		return ob.writeValueAsString(s);

	}

	@RequestMapping(value = "/getPurchaseReturnProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getPurchaseReturnProducts(PurchaseReturnId s) throws JsonProcessingException {
		String json = "[{\"grnId\": 1, \"productId\": 101, \"batchNo\": \"ABC123\", \"quantity\": 10, \"cost\": 50.0, \"reason\": \"Expired\"}, {\"grnId\": 2, \"productId\": 102, \"batchNo\": \"DEF456\", \"quantity\": 5, \"cost\": 20.0, \"reason\": \"Damaged\"}, {\"grnId\": 3, \"productId\": 101, \"batchNo\": \"GHI789\", \"quantity\": 8, \"cost\": 45.0, \"reason\": \"Incorrect item\"}]";
		return json;

	}

	@RequestMapping(value = "/makePurchaseReturn", method = RequestMethod.GET)
	@ResponseBody
	public String makePurchseOrder() throws JsonProcessingException {
		return null;
	}

	// @RequestMapping(value = "/formdata", method = RequestMethod.POST)
	//
	// public void formdata(@RequestBody String json, Model m)
	// throws MessagingException, JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
	// String json2 = json.toString();
	// String escapedJson = JSONObject.quote(json);
	//
	// ObjectMapper objectMapper = new ObjectMapper();
	// // String json1 =
	// //
	// "{\"purchase_order_date\":\"2023-06-13\",\"purchase_order_amount\":3000.0,\"vendor_id\":1,\"purchase_order_expected_date\":\"2023-06-20\",\"purchase_order_status\":\"Active\",\"user_id\":2,\"last_updated_user\":\"John
	// //
	// Doe\",\"last_updated_date\":\"2023-06-13\",\"children\":[{\"product_id\":1,\"purchase_order_quantity\":10,\"negotiation_price\":100,\"quantity_received\":5},{\"product_id\":2,\"purchase_order_quantity\":5,\"negotiation_price\":50,\"quantity_received\":0}]}";
	// // System.out.println(json1.equals(escapedJson));
	// System.out.println("hii" + json);
	// Im_Purchase_Order purchaseOrder = objectMapper.readValue(json, Im_Purchase_Order.class);
	//
	// System.out.println(purchaseOrder.toString());
	// x.persistpurchase(purchaseOrder);
	//
	// // Process the parent and child data as needed
	// // ...
	//
	// // Print the received data
	//
	// }

}