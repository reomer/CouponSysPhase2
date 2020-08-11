package com.omer.demo.dbdao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.omer.demo.beans.Coupon;
import com.omer.demo.repo.CouponRepo;
import com.omer.demo.repo.CustomerRepo;

@Repository
public class CouponDBDAO {
	
	
	@Autowired
	private CouponRepo couponRepo;
	
	public void addCoupon(Coupon coupon) {
		couponRepo.save(coupon);
	}
	
	
	public void updateCoupon(Coupon coupon) {
		couponRepo.saveAndFlush(coupon);

	}
	
	
	public void deleteCoupon(int couponId) {
		couponRepo.deleteById(couponId);

	}
	
	// Arraylist is a must?? can i change to List??
	public List<Coupon> getAllCoupons() {
		
		return  couponRepo.findAll();
	}
	
	public Coupon getOneCoupon(int couponId) { 
	
	return couponRepo.findById(couponId);
	}
	
	public void addCouponPurchased(int couponId,Coupon coupon ) {
	}
	
	public void deleteCouponPurchased(int couponId,Coupon coupon ) {
	}

}
