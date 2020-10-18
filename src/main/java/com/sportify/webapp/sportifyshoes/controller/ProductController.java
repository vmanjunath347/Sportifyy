package com.sportify.webapp.sportifyshoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.webapp.sportifyshoes.entity.Product;
import com.sportify.webapp.sportifyshoes.entity.users;
import com.sportify.webapp.sportifyshoes.exception.PassworWrongException;
import com.sportify.webapp.sportifyshoes.exception.SpecifyQuantityException;
import com.sportify.webapp.sportifyshoes.exception.UserNotFound;
import com.sportify.webapp.sportifyshoes.repository.ProductRepository;
import com.sportify.webapp.sportifyshoes.repository.PurchaseDetailsRepository;
import com.sportify.webapp.sportifyshoes.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/productops")
public class ProductController {

	@Autowired
    private UserRepository UserRepository;
	
	@Autowired
    private ProductRepository ProductRepository;
	
	@Autowired
    private PurchaseDetailsRepository PurchaseDetailsRepository;
	
	
		//admin adding product
	@PostMapping("/{adminEmail}/{adminPassword}")
	public  Product adduser(@RequestBody Product product, @PathVariable(value = "adminEmail") String email, @PathVariable(value = "adminPassword") String password) {
		
		List<users> allusers = this.UserRepository.findAll();
		short adminVarified=0;
		users admin = new users();
		for(int i=0;i<allusers.size();i++) {
			users tempUser=allusers.get(i);
			if(email.equals(tempUser.getEmail())&& tempUser.getType().equals("admin")) {
				adminVarified=1;
				 admin=tempUser;
				break;
			}
		}
		if(adminVarified==1) {
			if(admin.getPassword().equals(password)) {
				long quantity =  product.getQuantity();
				if((quantity<1)) 
					throw new SpecifyQuantityException("Quantity Cannot be null");
				if(product.getBrand()==null)
						throw new SpecifyQuantityException("Brand name cannot be empty");
				if(product.getGender()==null)
					throw new SpecifyQuantityException("Gender Type Cannot Be Empty cannot be empty");
				
				List<Product> allProducts= this.ProductRepository.findAll();
				
				//searching
				if(!allProducts.isEmpty()) {
				for(int i=0;i<allProducts.size();i++) {
					Product tempProduct=allProducts.get(i);
					if(tempProduct.getBrand().equals(product.getBrand())&&(tempProduct.getSize()==product.getSize())&&(tempProduct.getGender().equals(product.getGender()))) {
						long newQuantity=product.getQuantity()+tempProduct.getQuantity();
						tempProduct.setQuantity(newQuantity);
						return tempProduct;
					}
				}
			}
				
				return this.ProductRepository.save(product);
			}
			else
				throw new PassworWrongException("Wrong password");
		}
		else
			throw new UserNotFound("Admin not found with email" + email);
	}
	

}
