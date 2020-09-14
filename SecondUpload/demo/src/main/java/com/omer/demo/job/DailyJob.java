package com.omer.demo.job;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.omer.demo.beans.Coupon;
import com.omer.demo.dbdao.CouponDBDAO;

//@Component
//public class DailyJob {
//	
//	@Autowired
//	private CouponDBDAO couponDBDAO;
//	
//	@Scheduled(fixedRate = 1000*60*60*24)
//	public void doTheJob() {
//		Date date= new Date();
//		List<Coupon> expiredCoupons= couponDBDAO.getExpieredCoupons(date);
//		if (expiredCoupons!=null||expiredCoupons.size()>0)
//		{
//		//Add print utils before eraesig
//			for (Coupon coupon : expiredCoupons) {
//				System.out.println("Daily Task is deleting Coupons");
//				couponDBDAO.deleteCoupon(coupon.getId());
//			//	couponDBDAO.deleteCouponPurchasedByCouponID(coupon.getId());
//				//Add print utils after eraesing
//
//			}
//			}
//		}
//		
//		//couponDBDAO.deleteExpiredCoupons();}
//	}


