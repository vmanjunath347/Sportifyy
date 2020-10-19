package com.sportify.webapp.sportifyshoes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public  Product addProduct(@RequestBody Product product, @PathVariable(value = "adminEmail") String email, @PathVariable(value = "adminPassword") String password) {
		
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
	
	
	//search product
	@GetMapping("/{size}/{gender}")
	public List<Product> searchProducts(@PathVariable(value = "size") int size,@PathVariable(value = "gender") String gender){
		
		List<Product> allProducts= this.ProductRepository.findAll();
		
		if(allProducts.isEmpty())
			throw new UserNotFound("Store is empty");
		List<Product> matchingProducts= new ArrayList<>();
		for(int i=0; i<allProducts.size();i++) {
			Product tempProduct=allProducts.get(i);
			if((tempProduct.getGender().equals(gender))&&(tempProduct.getSize()==size)) 
				matchingProducts.add(tempProduct);
		}
		if(matchingProducts.isEmpty())
			throw new UserNotFound("No Results for this search");
		
		return matchingProducts;
	}
	
	//admin get  all product
		@GetMapping("/{adminEmail}/{adminPassword}/all")
		public List<Product> getAllProduct(@PathVariable(value = "adminEmail") String email, @PathVariable(value = "adminPassword") String password) {
			
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
					List<Product> allProducts= this.ProductRepository.findAll();
					if(allProducts.isEmpty())
						throw new UserNotFound("Store is empty");
					return this.ProductRepository.findAll();
				}
			else 
				throw new PassworWrongException("Wrong password");
		}
		else
			throw new UserNotFound("Admin not found with email" + email);
}
		
		//admin get  all product
				@GetMapping("/{adminEmail}/{adminPassword}/{id}")
				public Product getProductByID(@PathVariable(value = "id") long id,@PathVariable(value = "adminEmail") String email, @PathVariable(value = "adminPassword") String password) {
					
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
							List<Product> allProducts= this.ProductRepository.findAll();
							if(allProducts.isEmpty())
								throw new UserNotFound("Store is empty");
							return this.ProductRepository.findById(id)
									.orElseThrow(() -> new UserNotFound("Product Not found with id " + id));
						}
					else 
						throw new PassworWrongException("Wrong password");
				}
				else
					throw new UserNotFound("Admin not found with email" + email);
		}
				
				//admin deleteing user by id 
				@DeleteMapping("/{email}/{password}/delete/{productId}")
				public void deleteProduct (@PathVariable(value = "productId") long productId,@PathVariable(value = "email") String email,@PathVariable(value = "password") String password) {
					List<users> allusers = UserRepository.findAll();
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
							Product fetchedProduct = this.ProductRepository.findById(productId)
									.orElseThrow(() -> new UserNotFound("Product Not found with id " + productId));
							 this.ProductRepository.delete(fetchedProduct);
						}
						else
							throw new PassworWrongException("Wrong password");
					}
					else
						throw new UserNotFound("Admin not found with email" + email);
				}
				
				//update product
				@PutMapping("/{email}/{password}/edit/{productId}")
				public Product EditProduct (@RequestBody Product product,@PathVariable(value = "productId") long productId,@PathVariable(value = "email") String email,@PathVariable(value = "password") String password) 
				{
				List<users> allusers = UserRepository.findAll();
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
						if(product.getQuantity()>0) {
							Product fetchedProduct=this.ProductRepository.findById(productId).orElseThrow(() -> new UserNotFound("Product Not found with id " + productId));
							if(product.getQuantity()<1)
								this.ProductRepository.delete(fetchedProduct);
							else
								fetchedProduct.setQuantity(product.getQuantity());
							return fetchedProduct;
						}
						else
							throw new SpecifyQuantityException("Qauntity cannot be less than 0");
					}
					else
						throw new PassworWrongException("Wrong password");
				}
				else
					throw new UserNotFound("Admin not found with email" + email);
			}
					
}
