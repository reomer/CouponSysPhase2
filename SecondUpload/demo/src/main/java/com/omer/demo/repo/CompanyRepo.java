package com.omer.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omer.demo.beans.Company;


public interface CompanyRepo extends JpaRepository<Company, Integer>  {
	
	public boolean findByEmailAndPassword(String email,String password);
	public Company getOneByEmailAndPassword(String email,String password);


}
