package com.omer.demo.rest;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omer.demo.beans.Category;
import com.omer.demo.beans.Company;
import com.omer.demo.beans.Coupon;
import com.omer.demo.beans.LoginResult;
import com.omer.demo.exceptions.LoginException;
import com.omer.demo.exceptions.NotAllowedException;
import com.omer.demo.security.ClientsType;
import com.omer.demo.service.AdminService;

import lombok.Setter;

@RestController
@RequestMapping("company")
@Setter
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CompanyController extends ClientController{

	
	public CompanyController() {
		super();
	}
	
	@Override
	@PostMapping("login")
	public ResponseEntity<?> login(String email, String password)  {
		org.springframework.http.HttpHeaders responseHeaders = new org.springframework.http.HttpHeaders();
 try {
	 LoginResult loginResult =  loginManager.login2(email, password, ClientsType.Company);
		
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
	
	
	@PostMapping("add-coupon")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon,@RequestHeader(name ="Coupon-System-Headers") String token) throws NotAllowedException {
		try {
	          //  tokenManeger.isTokenExist(token);
			companyService.addCoupon(coupon);
			return new ResponseEntity<String>("coupon was added :)", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
//		catch (TokenNotExistException  e) {
//      return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//  }
	}
	@PostMapping("update-coupon")
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon, @RequestHeader(name ="Coupon-System-Headers") String token){
		try {
	          //  tokenManeger.isTokenExist(token);
			companyService.updateCoupon(coupon);
			return new ResponseEntity<String>("coupon was updated :)", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
//		catch (TokenNotExistException  e) {
//      return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//  }
	}
	@DeleteMapping("delete")
	public ResponseEntity<?> deleteCoupon(@RequestBody Coupon coupon, @RequestHeader(name ="Coupon-System-Headers") String token) {
		try {
			          //  tokenManeger.isTokenExist(token);
			companyService.deleteCoupon(coupon);;
			return new ResponseEntity<String>("coupon was deleted :)", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
//		catch (TokenNotExistException  e) {
//      return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//  }
	}
	@GetMapping("get-company-coupons")
	public ResponseEntity<?> getCompanyCoupons(@RequestHeader(name ="Coupon-System-Headers") String token) {
		try {
	          tokenManeger.isTokenExist(token);
			return new ResponseEntity<List<Coupon>>(companyService.getCompanyCoupons(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
//		catch (TokenNotExistException  e) {
//      return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//  }
	}
	@GetMapping("get-company-coupons-by-category")
	public ResponseEntity<?> getCompanyCoupons(@RequestBody Category category, @RequestHeader(name ="Coupon-System-Headers") String token) {
		try {
	          //  tokenManeger.isTokenExist(token);
			companyService.getCompanyCoupons(category);
			return new ResponseEntity<List<Coupon>>(companyService.getCompanyCoupons(category), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
//		catch (TokenNotExistException  e) {
//      return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//  }
	}
	@GetMapping("get-company-coupons-by-maxPrice")
	public ResponseEntity<?> getCompanyCoupons(@RequestParam Double maxPrice, @RequestHeader(name ="Coupon-System-Headers") String token) {
		try {
	          //  tokenManeger.isTokenExist(token);
			return new ResponseEntity<List<Coupon>>(companyService.getCompanyCoupons(maxPrice), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
//		catch (TokenNotExistException  e) {
//      return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//  }
	
	}
	@GetMapping("get-company-details")
	public ResponseEntity<?> getCompanyDetails(@RequestHeader(name ="Coupon-System-Headers") String token) {
		
		try {
	          //  tokenManeger.isTokenExist(token);

			return new ResponseEntity<Company>(	companyService.getCompanyDetails(), HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
//		catch (TokenNotExistException  e) {
//      return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//  }
	}


}
