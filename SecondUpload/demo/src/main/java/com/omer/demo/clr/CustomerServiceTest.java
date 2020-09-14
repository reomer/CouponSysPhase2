package com.omer.demo.clr;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.omer.demo.beans.Category;
import com.omer.demo.beans.Coupon;
import com.omer.demo.dbdao.CouponDBDAO;
import com.omer.demo.dbdao.CustomerDBDAO;
import com.omer.demo.exceptions.LoginException;
import com.omer.demo.service.CustomerService;
import com.omer.demo.utils.PrintStringUtils;
import com.omer.demo.utils.TablesAndLinesUtils;

@Component
@Order(2)
public class CustomerServiceTest implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CouponDBDAO couponDBDAO;

	@Override
	public void run(String... args) throws Exception {

		TablesAndLinesUtils.printLine();

		System.out.println("testing exceptions - login():");
		System.out.println();
		try {
			customerService.login("bigg@g,ail.com", "1234");
		} catch (LoginException e) {
			PrintStringUtils.printedException(e.getMessage(), "trying to login with wrong email");
		}
		try {
			customerService.login("biggi@g,ail.com", "12345");
		} catch (LoginException e) {
			PrintStringUtils.printedException(e.getMessage(), "trying to login with wrong password");
		}
		TablesAndLinesUtils.printLine();
		customerService.login("biggi@g,ail.com", "1234");

		customerService.setCustomerID(2);
		Coupon theCoupon = null;

		TablesAndLinesUtils.printCouponsTable(customerService.getCustomerCoupons(),
				"getting all customer's coupon purchases before testing");

		System.out.println();
		TablesAndLinesUtils.printLine();
		
		theCoupon = couponDBDAO.getOneCoupon(1);
		
		System.out.println(theCoupon);

		
//		customerService.purchaseCoupon(theCoupon);
		
		TablesAndLinesUtils.printCouponsTable(customerService.getCustomerCoupons(),
				"getting all customer's coupon purchases before testing");
		
		
		
		TablesAndLinesUtils.printCouponsTable(customerService.getCustomerCoupons(Category.Food), "testing getAllCouponsByCategory method (getting sport coupons) ");
	
//testing getAllCouponsByPriceLessThan()
		
		TablesAndLinesUtils.printCouponsTable(customerService.getCustomerCoupons(200.00), "testing getAllCouponsByPriceLessThan method (getting coupons that cost less than 200)");
		
////testing getCustomerDetails()
//		
		TablesAndLinesUtils.printCustomersTable(Arrays.asList(customerService.getCustomerDetails()), "testing getCustomerDetails method (getting Moshes details)");
	}

	}


