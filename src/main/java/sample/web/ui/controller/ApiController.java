package sample.web.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
