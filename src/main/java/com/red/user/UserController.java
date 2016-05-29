package com.red.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping(value="/user/login",  method=RequestMethod.GET)
	public ResponseEntity<String> getUserInfo(
			@RequestHeader(defaultValue="") String userName,
			@RequestHeader(defaultValue="") String id,
			@RequestHeader(defaultValue="") String userId,
			@RequestHeader(defaultValue="") String authToken,
		HttpServletRequest request){
		
		User user = userRepo.findByUserName(userName);
		if(user != null){
			
			// validate sign in 
			if(!isValidUser(userName, authToken)){
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

			System.out.println("getUserInfo: Sigining In: " + user);
			
		} else {
			
			// verify if the user 
			user = new User();
			user.setAuthToken(authToken);
			user.setKey(id);
			user.setUserId(userId);
			user.setUserName(userName);
			
			userRepo.save(user);
			System.out.println("getUserInfo: New User: " + user);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping("/user/update-loc")
	public ResponseEntity<String> updateUserLoc(@RequestHeader String userName,
			@RequestHeader String authToken,
			@RequestHeader String loc){
		
		if(!isValidUser(userName, authToken)){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		System.out.println(userName + " Loc Update: " +  loc);
		User user = userRepo.findByUserName(userName);
		
		String[] locSplit = loc.split(",");
		user.setLoc(new double[]{Double.parseDouble(locSplit[0]), Double.parseDouble(locSplit[1])});
		
		userRepo.save(user);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private boolean isValidUser(String userName, String authToken){
		
		User user = userRepo.findByUserName(userName);
		
		if(!user.getAuthToken().equals(authToken)){
			return false;
		}
		
		return true;
	}
}
