package com.example.product.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

@Entity

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Pid;
	@NotNull
	@Size(min =1 ,max =100 ,message = "Name should be given between 50 to 100 character ")
	private String name;
	@NotNull
	private int price;
	
	
	
	
	public Product() {
		super();
	}
	
	public Product(int pid, String name, int price) {
		super();
		Pid = pid;
		this.name = name;
		this.price = price;
	}




	public int getPid() {
		return Pid;
	}




	public void setPid(int pid) {
		Pid = pid;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public int getPrice() {
		return price;
	}




	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [Pid=" + Pid + ", name=" + name + ", price=" + price + "]";
	}

	




	
	
}
