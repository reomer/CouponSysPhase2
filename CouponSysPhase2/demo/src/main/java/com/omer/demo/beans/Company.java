package com.omer.demo.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {
	@Id
	private int id;
	private String name;
	private String email;
	private String password;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Coupon> coupons;

}
