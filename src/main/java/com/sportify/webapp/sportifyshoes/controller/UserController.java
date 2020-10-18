package com.sportify.webapp.sportifyshoes.controller;

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

import com.sportify.webapp.sportifyshoes.entity.users;
import com.sportify.webapp.sportifyshoes.exception.EmptyFieldException;
import com.sportify.webapp.sportifyshoes.exception.PassworWrongException;
import com.sportify.webapp.sportifyshoes.exception.UserAlreadyExistsException;
import com.sportify.webapp.sportifyshoes.exception.UserNotFound;
import com.sportify.webapp.sportifyshoes.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/userops")
public class UserController {
	
	
	// inject dependency
		@Autowired
       private UserRepository UserRepository;
		
		// GET all users
//		@GetMapping"/admin/{password}/users")
//		public List<users> getallUsers(@PathVariable(value = "password") String adminPassword) {
//			users Admin =  this.UserRepository.findById((long) 1);	
//			if(Admin.getPassword().equals(adminPassword))
//				return this.UserRepository.findAll();
//		}
		
		// register as user or admin
		@PostMapping("/user")
		public users adduser(@RequestBody users user) {
			List<users> allUsers = this.UserRepository.findAll();
			String tempUSeremail = user.getEmail();
			short userfound=0;
			short adminExists=0;
			
			if(user.getType().equals("admin")) {
			for (int i = 0; i < allUsers.size(); i++) {
		           users tempUser=allUsers.get(i);
		           if( tempUser.getType().equals("admin")){
		        	   adminExists=1;
		        	   break;
		           } 
		       }
			}
			
			for (int i = 0; i < allUsers.size(); i++) {
	           users tempUser=allUsers.get(i);
	           if( tempUser.getEmail().equals(tempUSeremail)){
	        	   userfound=1;
	        	   break;
	           } 
	        }

			if((userfound==0)&&(adminExists==0)) {
				if(user.getEmail()==null||user.getName()==null||user.getPassword()==null||user.getType()==null)
					throw new EmptyFieldException("Empty field(s)");
				else
					return this.UserRepository.save(user);
			}
			else if(adminExists==1)
				throw new UserAlreadyExistsException("An Admin is already registered"); 
			else 
				throw new UserAlreadyExistsException("User already exists"); 
		}
		
		//admin deleteing user by id 
		@DeleteMapping("/{email}/{password}/{userid}")
		public void deleteUser (@PathVariable(value = "userid") long userId,@PathVariable(value = "email") String email,@PathVariable(value = "password") String password) {
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
					users fetchedUser = this.UserRepository.findById(userId)
							.orElseThrow(() -> new UserNotFound("User Not found with id " + userId));
					 this.UserRepository.delete(fetchedUser);
				}
				else
					throw new PassworWrongException("Wrong password");
			}
			else
				throw new UserNotFound("Admin not found with email" + email);
		}
		
		//admin see all users
		@GetMapping("/{email}/{password}/users")
		public List<users> getAllusers(@PathVariable(value = "password") String password,@PathVariable(value = "email") String email){
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
				if(admin.getPassword().equals(password)) 
					return allusers;
				else
					throw new PassworWrongException("Wrong password");
			}
			else
				throw new UserNotFound("Admin not found with email" + email);
		}
		
		// update a product
		@PutMapping("/{userid}/{oldpassword}")
		public users updatePassword(@RequestBody users user, @PathVariable(value = "userid") long userid, @PathVariable(value = "oldpassword") String oldpassword) {
			// 1. find a record / product
			users fetcheduser = this.UserRepository.findById(userid)
					.orElseThrow(() -> new UserNotFound("User Not found with id " + userid));
			
			if(fetcheduser.getPassword().equals(oldpassword)) {
			// 2 . set new values
				if(user.getEmail()!=null) {
					List<users> allusers = this.UserRepository.findAll();
					short userExists=0;
					for(int i=0;i<allusers.size();i++) {
						if(allusers.get(i).getEmail().equals(user.getEmail())) {
							userExists=1;
							break;
						}
					}
					if(userExists==1)
						throw new UserAlreadyExistsException("User already exists in email Id "+user.getEmail()); 
					fetcheduser.setEmail(user.getEmail());
				}
				if(user.getName()!=null)
					fetcheduser.setName(user.getName());
				if(user.getPassword()!=null)
					fetcheduser.setPassword(user.getPassword());
    	// 3.save a user
			return this.UserRepository.save(fetcheduser);
			}
			else
				throw new PassworWrongException("Wrong password");
		}                                                        
		
		//admin get user by id
				@GetMapping("/{email}/{password}/{userid}")
				public users getAllusers(@PathVariable(value = "password") String password,@PathVariable(value = "email") String email,@PathVariable(value = "userid") long userId){
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
							return this.UserRepository.findById(userId)
							.orElseThrow(() -> new UserNotFound("User Not found with id " + userId));
						}
						else
							throw new PassworWrongException("Wrong password");
					}
					else
						throw new UserNotFound("Admin not found with email" + email);
				}
		
}
