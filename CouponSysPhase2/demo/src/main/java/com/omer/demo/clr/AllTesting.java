package com.omer.demo.clr;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.omer.demo.beans.Category;
import com.omer.demo.beans.Company;
import com.omer.demo.beans.Coupon;
import com.omer.demo.dbdao.CompanyDBDAO;
import com.omer.demo.dbdao.CouponDBDAO;
import com.omer.demo.dbdao.CustomerDBDAO;
import com.omer.demo.repo.CouponRepo;

@Component
public class AllTesting  implements CommandLineRunner {

	
	@Autowired
	private CompanyDBDAO companyDBDAO;
	@Autowired
	private CustomerDBDAO customerDBDAO;
	@Autowired
	private CouponDBDAO couponDBDAO;
	


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	//	Coupon c2 = new Coupon(1,111,Category.Food,"BuyOneGetOne","Buy one family pizza get one for free ",Date);
		
		
	System.out.println("Starting teting All:");
	System.out.println("------------------------------------------------------------------");

	
	System.out.println("Company Tests: Upload 1 companies:");
	System.out.println("------------------------------------------------------------------");
	Company comp1 = new Company();
	comp1.setId(111);
	comp1.setName("BrooklynPizza");
	comp1.setEmail("shop@BrooklynPizza.com");
	comp1.setPassword("1234");
	
	companyDBDAO.addCompany(comp1);
	
	
	System.out.println("Coupon Tests: Upload 2 coupons:");
	System.out.println("------------------------------------------------------------------");
		Coupon c1 = new Coupon();
		c1.setId(1);
		c1.setCompanyID(111);
		c1.setCategory(Category.Food);
		c1.setTitle("BuyOne GetOne");
		c1.setDescription("Buy one family pizza get one for free");
		c1.setPrice(100.00);
		Date date = new Date(2020, 11, 10);
		c1.setEndDate(date);
		Date date1 = new Date(2020, 05, 10);
		c1.setEndDate(date1);
		c1.setAmount(10);
		c1.setImage("http://pizza.com");
		
		Coupon c2 = new Coupon();
		c2.setId(2);
		c2.setCompanyID(111);
		c2.setCategory(Category.Food);
		c2.setTitle("10th slice for free");
		c2.setDescription("Buy 9 slices get one for free");
		c2.setPrice(150.00);
		Date date4 = new Date(2020, 11, 10);
		c2.setEndDate(date4);
		Date date5 = new Date(2020, 05, 10);
		c2.setEndDate(date5);
		c2.setAmount(10);
		c2.setImage("http://pizza.com");

		System.out.println("Coupon Tests: Upload 2 coupons:");

		couponDBDAO.addCoupon(c1);
		couponDBDAO.addCoupon(c2);
		
		System.out.println("Coupon Tests: getAll -> 2 coupons:");
		
		System.out.println(couponDBDAO.getAllCoupons());


		
		
	}

}
