/**
 * @auther Rakesh
 * @time Nov 12, 2016
 */

package com.rkumbhare.restapi.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rkumbhare.restapi.vo.User;

@RestController
public class UserController {
	private List<User> userList;

	@PostConstruct
	public void init() {
		this.userList = new ArrayList<User>();
		this.userList.add(new User(1001, "rakesh", "kumbhare", 27, "male"));
		this.userList.add(new User(1002, "manisha", "kumbhare", 24, "female"));
		this.userList.add(new User(1003, "deepali", "kumbhare", 25, "female"));
		this.userList.add(new User(1004, "sachin", "watare", 31, "male"));
		this.userList.add(new User(1005, "pravin", "ninave", 28, "male"));
		this.userList.add(new User(1006, "deepak", "gedam", 29, "male"));
		this.userList.add(new User(1007, "manoj", "wankhede", 30, "male"));
		this.userList.add(new User(1008, "chandu", "jangde", 26, "male"));
		this.userList.add(new User(1009, "pritam", "warade", 27, "male"));
		this.userList.add(new User(1010, "pramod", "gachhiwale", 27, "male"));
		this.userList.add(new User(1011, "sushil", "padhye", 35, "male"));
		this.userList.add(new User(1012, "rajesh", "suyal", 34, "male"));
		this.userList.add(new User(1013, "nilesh", "kumbhare", 22, "male"));
		this.userList.add(new User(1014, "vinod", "parate", 30, "male"));
		this.userList.add(new User(1015, "varsha", "barapatre", 40, "female"));
		this.userList.add(new User(1016, "ravi", "kanoje", 32, "male"));
		this.userList.add(new User(1017, "anand", "sharma", 37, "male"));
		this.userList.add(new User(1018, "nikhil", "atewar", 30, "male"));
		this.userList.add(new User(1019, "nikita", "atewar", 27, "female"));
		this.userList.add(new User(1020, "anu", "katar", 25, "female"));
		this.userList.add(new User(1021, "nishi", "pawar", 30, "female"));
		this.userList.add(new User(1022, "manali", "goyal", 28, "female"));
		this.userList.add(new User(1023, "neha", "sharma", 35, "female"));
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = {"application/json"})
	@ResponseBody
	public Map<String, Object> getUser(@RequestParam("startIndex") String startIndex, @RequestParam("pageSize") String pageSize,
			@RequestParam(value = "sortColumn", required = false) String sortColumn,
			@RequestParam(value = "sortOrder", required = false) String sortOrder,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "fname", required = false) String fname,
			@RequestParam(value = "gender", required = false) String gender) {

		if (sortColumn != null && sortOrder != null) {
			switch (sortColumn) {
				case "id" :
					Collections.sort(userList, new Comparator<User>() {
						@Override
						public int compare(User user1, User user2) {
							if (user1.getId() == user2.getId())
								return 0;
							if(sortOrder.equals("1")){
								if (user1.getId() > user2.getId())
									return 1;
								else
									return -1;
							}else{
								if (user2.getId() > user1.getId())
									return 1;
								else
									return -1;
							}
						}
					});
					break;
					
				case "fname" :
					Collections.sort(userList, new Comparator<User>() {
						@Override
						public int compare(User user1, User user2) {
							if( sortOrder.equals("1")){
								return user1.getFname().compareTo(user2.getFname());
							}else{
								return user2.getFname().compareTo(user1.getFname());
							}
						}
					});
					break;
					
				case "lname" :
					Collections.sort(userList, new Comparator<User>() {
						@Override
						public int compare(User user1, User user2) {
							if( sortOrder.equals("1")){
								return user1.getLname().compareTo(user2.getLname());
							}else{
								return user2.getLname().compareTo(user1.getLname());
							}
						}
					});
					break;
					
				case "age" :
					Collections.sort(userList, new Comparator<User>() {
						@Override
						public int compare(User user1, User user2) {
							if (user1.getAge() == user2.getAge())
								return 0;
							if(sortOrder.equals("1")){
								if (user1.getAge() > user2.getAge())
									return 1;
								else
									return -1;
							}else{
								if (user2.getAge() > user1.getAge())
									return 1;
								else
									return -1;
							}
						}
					});
					break;
					
				case "gender" :
					Collections.sort(userList, new Comparator<User>() {
						@Override
						public int compare(User user1, User user2) {
							if( sortOrder.equals("1")){
								return user1.getGender().compareTo(user2.getGender());
							}else{
								return user2.getGender().compareTo(user1.getGender());
							}
						}
					});
					break;	
			}

		}
		
		// search criteria
		List<User> filterList = userList;
		if(id != null){
			filterList = filterList.stream().filter(user -> user.getId()==Integer.parseInt(id))
									.collect(Collectors.toList());
		}
		if(fname != null){
			filterList = filterList.stream().filter(user -> user.getFname().toLowerCase().contains(fname.toLowerCase()))
									.collect(Collectors.toList());
		}
		if(gender != null){
			filterList = filterList.stream().filter(user -> user.getGender().equalsIgnoreCase(gender))
					.collect(Collectors.toList());
		}

		List<User> sublist = filterList.subList(Math.min(Integer.parseInt(startIndex), filterList.size() - 1),
				Math.min(filterList.size(), (Integer.parseInt(startIndex) + Integer.parseInt(pageSize))));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("users", sublist);
		map.put("totalRecords", filterList.size());
		return map;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<com.rkumbhare.restapi.vo.Error> exceptionHandler(Exception e){
		com.rkumbhare.restapi.vo.Error error = new com.rkumbhare.restapi.vo.Error(e.getMessage(), e.toString(), HttpStatus.BAD_REQUEST);
		ResponseEntity<com.rkumbhare.restapi.vo.Error> responseEntity = new ResponseEntity<com.rkumbhare.restapi.vo.Error>(error, HttpStatus.BAD_REQUEST);
		return responseEntity;
	}
	
}
