package com.example.product.Service;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.example.product.Entity.Product;
import com.example.product.Repository.ProductRepo;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productrepo;

	public List<Product> getAllProducts(){
		return productrepo.findAll();
	}
	
	public List<Product> getProductsGreaterthan(int price) {
		return productrepo.findByPriceGreaterThan(price);
		
	}

	public List<Product> createProduct(List<Product> p) {
		// TODO Auto-generated method stub
//		Product product1=new Product();
//		//product1.setPid(product1.getPid());
//		product1.setName(p.getName());
//		product1.setPrice(p.getPrice());
//		return productrepo.save(product1);

		return productrepo.saveAll(p);
	
	}

	

	public Product getProductById(int id) {
		 Optional<Product> productbyId = productrepo.findById(id);
		Product product = productbyId.get();
		return product;
		
		
	}


	public List<Product> updateProduct(List<Product> product) {
		for(Product p: product){

			Optional<Product> existingproductopt = productrepo.findById(p.getPid());

			if (existingproductopt != null) {
				Product existingproduct = existingproductopt.get();
				existingproduct.setName(p.getName());
				existingproduct.setPrice(p.getPrice());
				productrepo.save(existingproduct);
			}else {
				throw new RuntimeException("Product Not Found With given Id");
			}
		}

		return productrepo.findAll();  //find updated products
	}

	public Product updateProduct(int id, Product product) {
		
		Optional<Product> productById = productrepo.findById(id);
		
		Product producttobesaved=productById.get();
		producttobesaved.setName(product.getName());
		producttobesaved.setPrice(product.getPrice());
		
		Product updatedproduct = productrepo.save(producttobesaved);
		
		
		return updatedproduct;
	}
	//for Options http method
	public void getalloweduserOperations(HttpServletResponse responce) {
		responce.setHeader("Allow", "GET, HEAD, PUT, DELETE");
		responce.setStatus(HttpServletResponse.SC_OK);
		
	}

//HEAD method
	public void wheatherUserExists(int id, HttpServletResponse responce) {
		if(id==1) {
			
			responce.setStatus(HttpServletResponse.SC_OK);
			responce.setHeader("Content-Type", "application/json");
		}
		else
			responce.setStatus(HttpServletResponse.SC_NOT_FOUND);
		
	}


	public Product createOneProduct(Product product) {
		return productrepo.save(product);
	}
}
