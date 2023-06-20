package main.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.dao.GrnDAO;
import main.model.inputgrnmodels.GrnInputList;
import main.models.entityModels.ImGrn;

@Controller
public class GrnController {

	@Autowired
	GrnDAO grndao;

	@PostMapping("/makeGrn")
	public void makeGrn(String jsonData) {
		System.out.println(jsonData);
		ObjectMapper o = new ObjectMapper();
		ModelMapper mapper = new ModelMapper();

		GrnInputList grnInputList = null;
		try {
			grnInputList = o.readValue(jsonData, GrnInputList.class);
			System.out.println("\n");
			System.out.println(grnInputList.toString());
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		ImGrn imGrn = mapper.map(grnInputList, ImGrn.class);
		System.out.println("\n");
		System.out.println(imGrn.toString());

		grndao.saveGrn(imGrn);

	}

}