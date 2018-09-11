package com.thg.bootdemo.controller.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/demo")
public class DemoController {
	@RequestMapping(value="/getname", method=RequestMethod.GET)
	public String getName(String userName) {
		return "Hello, " + userName + "..";
	}
}
