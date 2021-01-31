package com.omer.demo.rest;

import java.net.http.HttpHeaders;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.omer.demo.beans.Company;
import com.omer.demo.beans.Coupon;
import com.omer.demo.beans.Customer;
import com.omer.demo.beans.LoginResult;
import com.omer.demo.exceptions.LoginException;
import com.omer.demo.exceptions.NotAllowedException;
import com.omer.demo.exceptions.TokenNotExistException;
import com.omer.demo.security.ClientsType;
import com.omer.demo.security.LoginManeger;
import com.omer.demo.service.AdminService;

import lombok.Setter;

@RestController
@RequestMapping("admin")
@Setter
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AdminController extends ClientController {
	@Autowired
	protected AdminService adminService;

	public AdminController() {
		super();
	}
	@PostMapping("login")
	@Override
	public ResponseEntity<?> login(@RequestParam String email,@RequestParam String password)   {
		org.springframework.http.HttpHeaders responseHeaders = new org.springframework.http.HttpHeaders();
		
		try {
			LoginResult loginResult =  loginManager.login2(email, password, ClientsType.Admin);
			
			String token =loginResult.getToken();
			responseHeaders.set("Coupon-System-Headers", token);
			return ResponseEntity.ok().headers(responseHeaders).body(loginResult);}
			
			catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
	
	}

	@DeleteMapping("logout")
	public ResponseEntity<?> logout(@RequestHeader(name ="Coupon-System-Headers") String token)   {
		tokenManeger.removeToken(token);
		return new ResponseEntity("Thank and goodbye", HttpStatus.NO_CONTENT);
	}

//		@PostMapping("addCompany")
//	public ResponseEntity<?> addCompany(@RequestBody Company company) {
//		try {
//	        //    tokenManeger.isTokenExist(token);
//			adminService.addCompany(company);
//			return ResponseEntity.ok().build();
//		} catch (Exception e) {
//			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//	}
	@PostMapping("addCompany")
	 public ResponseEntity<?> addCompany(@RequestBody Company company, @RequestHeader(name = "Coupon-System-Header") String token){
	        try {
	            tokenManeger.isTokenExist(token);
	            adminService.addCompany(company);
	            return new ResponseEntity<>("company was added :)",HttpStatus.CREATED);
	        } 
	        catch (NotAllowedException e){
	            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	        }
	        catch (TokenNotExistException  e) {
	            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
	        }
	    }

	@PostMapping("update-company")
	public ResponseEntity<?> updateCompany(@RequestBody Company company,@RequestHeader(name = "Coupon-System-Headers", required = false) String token) {
		try {
		     //       tokenManeger.isTokenExist(token);

			adminService.updateCompany(company);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("company was updated :)",  HttpStatus.OK);
	}
	//catch (TokenNotExistException  e) {
//        return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//    }

	@DeleteMapping("delete-company")
	public ResponseEntity<?> deleteCompany(@RequestParam int companyId, @RequestHeader(name = "Coupon-System-Headers", required = false) String token) {
		try {
		     //       tokenManeger.isTokenExist(token);
			adminService.deleteCompany(companyId);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("company was deleted :)",  HttpStatus.OK);
	
//	catch (TokenNotExistException  e) {
//        return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//    }
	}
	
	@GetMapping("get-all-companies")
	public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Coupon-System-Headers", required = false) String token) {

		try {
			System.out.println(token);
            tokenManeger.isTokenExist(token);
			return new ResponseEntity<List<Company>>(adminService.getAllCompanies(), HttpStatus.OK);
		}
		 catch (Exception  e) {
	            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
	        }
//		catch (Exception   e) {
//            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//        }

	}

	@GetMapping("get-one-company")
	public ResponseEntity<?> getOneCompany(@RequestHeader(name ="Coupon-System-Headers") String token,@RequestParam int id) {

		try {
		  //          tokenManeger.isTokenExist(token);
			return new ResponseEntity<Company>(adminService.getOneCompany(id), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
//		catch (TokenNotExistException  e) {
//      return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//  }
		
	}

	@PostMapping("add-customer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer,@RequestHeader(name = "Coupon-System-Headers", required = false) String token) {
		   try {
			     //       tokenManeger.isTokenExist(token);
			            adminService.addCustomer(customer);
			            return new ResponseEntity<>("customer was added :)",HttpStatus.CREATED);
			        } 
			        catch (NotAllowedException e){
			            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			        }
//			        catch (TokenNotExistException  e) {
//			            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//			        }
			    }

	@PostMapping("update-customer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer,@RequestHeader(name = "Coupon-System-Headers", required = false) String token) {
		try {
		     //       tokenManeger.isTokenExist(token);
			adminService.updateCustomer(customer);
			return new ResponseEntity<String>("Customer is updated :)", HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
//		catch (TokenNotExistException  e) {
//      return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//  }
	}

	@DeleteMapping("delete-customer")
	public ResponseEntity<?> deleteCustomer(@RequestParam int customerId, @RequestHeader(name ="Coupon-System-Headers") String token) {
		try {
		     //       tokenManeger.isTokenExist(token);
			adminService.deleteCustomer(customerId);
			return new ResponseEntity<String>("Customer is deleted :)", HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
//		catch (TokenNotExistException  e) {
//      return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//  }
	}

	@GetMapping("get-all-customers")
	public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Coupon-System-Headers", required = false) String token) {
		try {
            tokenManeger.isTokenExist(token);
			return new ResponseEntity<List<Customer>>(adminService.getAllCustomers(), HttpStatus.OK);


		} catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
		}
  }	
	
		

	@GetMapping("get-one-customer")
	public ResponseEntity<?> getOneCustomer(@RequestParam int customerId, @RequestHeader(name ="Coupon-System-Headers") String token) {
		try {
		            tokenManeger.isTokenExist(token);
			return new ResponseEntity<Customer>(adminService.getOneCustomer(customerId), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
//	catch (TokenNotExistException  e) {
//  return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
//}
}
