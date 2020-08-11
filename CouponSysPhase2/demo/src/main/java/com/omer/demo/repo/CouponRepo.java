package com.omer.demo.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omer.demo.beans.Category;
import com.omer.demo.beans.Coupon;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {

	Coupon findById(int couponId);
	
	Coupon findByTitle(String title);

	
	
	//Maybe for service??
	//---------------------------------
	//Look at couponsys1 at facade
//		List<Coupon> findByCompanyById(int companyId, int couponId);
	
//	findby company category
//		List<Coupon> findByCompanyByCategory(int companyId, Category category );

//	findby company maxprice 
//		List<Coupon> findByCompanyByPrice(int companyId, int price);

}
