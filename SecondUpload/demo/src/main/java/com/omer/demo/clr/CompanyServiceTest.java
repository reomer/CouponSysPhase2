package com.omer.demo.clr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.omer.demo.dbdao.CompanyDBDAO;
import com.omer.demo.dbdao.CouponDBDAO;
import com.omer.demo.service.CompanyService;
import com.omer.demo.service.CustomerService;

@Component
@Order(3)
public class CompanyServiceTest implements CommandLineRunner {
	

		@Autowired
		private CompanyService companyService;

		@Autowired
		private CouponDBDAO couponDBDAO;

		@Override
		public void run(String... args) throws Exception {
			// TODO Auto-generated method stub
			
		}
}
