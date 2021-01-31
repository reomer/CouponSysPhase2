package com.omer.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omer.demo.exceptions.LoginException;
import com.omer.demo.security.LoginManeger;
import com.omer.demo.security.TokenManeger;
import com.omer.demo.security.TokenService;
import com.omer.demo.service.AdminService;
import com.omer.demo.service.ClientService;
import com.omer.demo.service.CompanyService;
import com.omer.demo.service.CustomerService;

@RestController
@RequestMapping("CouponSys")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public abstract class ClientController {

//	@Autowired
//	public ClientService clientService;
//	
	@Autowired
	protected AdminService adminService;
	
	@Autowired
	protected CompanyService companyService;
	
	@Autowired
	protected CustomerService customerService;
	
	@Autowired
	protected TokenManeger tokenManeger;
	
	@Autowired
	protected TokenService  tokenService;
	
	@Autowired
	protected LoginManeger  loginManager;
	
	
	
	
	public abstract ResponseEntity<?> login(String email, String password) throws LoginException ;

}
