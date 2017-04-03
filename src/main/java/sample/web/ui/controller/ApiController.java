package sample.web.ui.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.web.ui.domain.Configuration;

/**
 * api controller die alle requests naar /api/** afhandeld
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {

	@RequestMapping("/config")
	public Configuration getConfiguration(){
		return Configuration.getConfiguration();
	}







}
