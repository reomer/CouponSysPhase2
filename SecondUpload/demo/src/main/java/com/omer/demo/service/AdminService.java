package com.omer.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omer.demo.beans.Company;
import com.omer.demo.beans.Coupon;
import com.omer.demo.beans.Customer;
import com.omer.demo.dbdao.CompanyDBDAO;
import com.omer.demo.dbdao.CouponDBDAO;
import com.omer.demo.dbdao.CustomerDBDAO;
import com.omer.demo.exceptions.NotAllowedException;

import lombok.Data;

@Service
public class AdminService extends ClientService {
	
	public AdminService() {super();}

	@Override
	public boolean login(String email, String password) {
		String adminMail = "admin@admin.com";
		String adminPass ="admin";
		if (email.equals(adminMail)&&password.equals(adminPass)) {
			return true;
		}
		System.out.println("Access not aloowed - You are not the admin");
		return false;
	} 
	
	public void addCompany(Company company) {
		
		this.companyDBDAO.addCompany(company);
	}
	
	
	public void updateCompany(Company company) throws NotAllowedException {
		if(this.companyDBDAO.getOneCompany(company.getId()).equals(company.getId())&&(this.companyDBDAO.getOneCompany(company.getId()).getName()).equals(company.getName()))
		{
			this.companyDBDAO.updateCompany(company);

		}
		else
			 throw new NotAllowedException("Updating Name or ID for an existing company is not Allowed");
		
	}
	
	
	public void deleteCompany(int companyId) {
		List<Coupon> allCoupons= this.couponDBDAO.getAllCouponsByCompany(companyId);
		for (Coupon coupon : allCoupons) {
			
			this.couponDBDAO.deleteCouponPurchasedByCouponID(coupon.getId());
			
			this.couponDBDAO.deleteCoupon(coupon.getId());
		}
		
		this.companyDBDAO.deleteCompany(companyId);
	}
	

	public List<Company> getAllCompanies() {
		
		return  this.companyDBDAO.getAllCompanies();
	}
	public Company getOneCompany(int customerId) {
		return this.companyDBDAO.getOneCompany(customerId);
	}
	
	public void addCustomer(Customer customer) {
		
		
		this.customerDBDAO.addCustomer(customer);;
	}
	
	public void updateCustomer(Customer customer) {
		this.customerDBDAO.updateCustomer(customer);
	}
	
	public void deleteCustomer(int customerId) {
		
		
		this.customerDBDAO.deleteCustomer(customerId);
	}
	

	public List<Customer> getAllCustomers() {
		
		return  this.customerDBDAO.getAllCustomers();
	}
	public Customer getOneCustomer(int customerId) {
		return this.customerDBDAO.getOneCustomer(customerId);
		
	}
}
