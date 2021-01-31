package com.omer.demo.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omer.demo.beans.Category;
import com.omer.demo.beans.Company;
import com.omer.demo.beans.Coupon;
import com.omer.demo.beans.Customer;
import com.omer.demo.beans.LoginResult;
import com.omer.demo.exceptions.LoginException;
import com.omer.demo.exceptions.PurchaseCouponException;
import com.omer.demo.security.ClientsType;

import lombok.Setter;

@RestController
@RequestMapping("customer")
@Setter
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CustomerController extends ClientController {

	public CustomerController() {
		super();
	}
	
	@Override
	@PostMapping("login")
	public ResponseEntity<?> login(String email, String password)  {
		org.springframework.http.HttpHeaders responseHeaders = new org.springframework.http.HttpHeaders();
 try {
	 LoginResult loginResult =  loginManager.login2(email, password, ClientsType.Customer);
		
		String token =loginResult.getToken();
		responseHeaders.set("Coupon-System-Headers", token);
		return ResponseEntity.ok().headers(responseHeaders).body(loginResult);}
	
catch (LoginException e){
    return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
}
	}
	@DeleteMapping("logout")
	public ResponseEntity<?> logout(@RequestHeader(name ="Coupon-System-Headers") String token)   {
		tokenManeger.removeToken(token);
		return new ResponseEntity("Thank and goodbye", HttpStatus.NO_CONTENT);
	}
	

	@GetMapping("get-customer-coupons")
	public ResponseEntity<?> getCustomerCoupons() {
		try {
	          //  tokenManeger.isTokenExist(token);
			return new ResponseEntity<List<Coupon>>(customerService.getCustomerCoupons(), HttpStatus.NO_CONTENT);	
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
//		catch (TokenNotExistException  e) {
//      return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//  }
	}
	
	@PostMapping("purchase-coupon")
	public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon, @RequestHeader(name ="Coupon-System-Headers") String token)  {
		try {
	          //  tokenManeger.isTokenExist(token);
			customerService.purchaseCoupon(coupon);
			return new ResponseEntity<String>("company was added :)", HttpStatus.OK);	

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//			catch (TokenNotExistException  e) {
//	      return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
	//  }
		}

	}
	@GetMapping("customer-coupons-by-category")
	public ResponseEntity<?> getCustomerCoupons(Category category, @RequestHeader(name ="Coupon-System-Headers") String token) {
		try {
	          //  tokenManeger.isTokenExist(token);
			return new ResponseEntity<List<Coupon>>(customerService.getCustomerCoupons(category), HttpStatus.OK);	

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
//		catch (TokenNotExistException  e) {
//      return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//  }
	}
	
	@GetMapping("customer-coupons-by-maxPrice")
	public ResponseEntity<?> getCustomerCoupons(Double maxPrice, @RequestHeader(name ="Coupon-System-Headers") String token) {
		try {
	          //  tokenManeger.isTokenExist(token);
			return new ResponseEntity<List<Coupon>>(customerService.getCustomerCoupons(maxPrice), HttpStatus.OK);	

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
//		catch (TokenNotExistException  e) {
//      return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//  }
	}
	@GetMapping("customer-details")
	public ResponseEntity<?> getCustomerDetails(@RequestHeader(name ="Coupon-System-Headers") String token) {
		try {
	          //  tokenManeger.isTokenExist(token);
			return new ResponseEntity<Customer>(customerService.getCustomerDetails(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
//		catch (TokenNotExistException  e) {
//      return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//  }
	}
}
