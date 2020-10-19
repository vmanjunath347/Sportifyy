package com.sportify.webapp.sportifyshoes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.webapp.sportifyshoes.entity.Product;
import com.sportify.webapp.sportifyshoes.entity.PurchaceDetails;
import com.sportify.webapp.sportifyshoes.entity.users;
import com.sportify.webapp.sportifyshoes.exception.PassworWrongException;
import com.sportify.webapp.sportifyshoes.exception.UserNotFound;
import com.sportify.webapp.sportifyshoes.repository.PurchaseDetailsRepository;
import com.sportify.webapp.sportifyshoes.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/purchasehistory")
public class PurchaseDetailsController {

	@Autowired
    private UserRepository UserRepository;
	
	@Autowired
    private PurchaseDetailsRepository PurchaseDetailsRepository;
	
	@GetMapping("/{adminEmail}/{password}")
	public List<PurchaceDetails> getAllHistory(@PathVariable(value = "adminEmail") String email,@PathVariable(value = "password") String password){
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
				return this.PurchaseDetailsRepository.findAll();
			}
			else
				throw new PassworWrongException("Wrong password");
		}
		else
			throw new UserNotFound("Admin not found with email" + email);
	}
	
	@GetMapping("/{adminEmail}/{password}/{userID}")
	public List<PurchaceDetails> getAllHistory(@PathVariable(value = "userID") long userID,@PathVariable(value = "adminEmail") String email,@PathVariable(value = "password") String password){
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
				List <PurchaceDetails> allPurchase= this.PurchaseDetailsRepository.findAll();
				if(allPurchase.isEmpty())
					throw new UserNotFound("No purchases have been done yet");
				 List <PurchaceDetails> newList =  new ArrayList<>();
				 for(int i=0; i<allPurchase.size();i++) {
					if(userID==allPurchase.get(i).getUserId())
						newList.add(allPurchase.get(i));
				}
				 if(newList.isEmpty())
					 throw new UserNotFound("User with id "+userID+" hasint bought anything");
				 return newList;
			}
			else
				throw new PassworWrongException("Wrong password");
		}
		else
			throw new UserNotFound("Admin not found with email" + email);
	}
}
