package main.bll;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import main.models.warehouseModels.TotalStock;

@Component
public class TotalStockbll {

	public int getTotalStockQuantity(ArrayList<TotalStock> s) {
		int sum = 0;
		for (TotalStock x : s) {
			sum += x.getProduct_stock();

		}
		return sum;
	}

}
