package com.abhi.leetcode_analyzer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abhi.leetcode_analyzer.model.LeetcodeProfile;
import com.abhi.leetcode_analyzer.service.LeetcodeService;

import org.springframework.ui.Model;

@Controller
public class Homecontroller {

	private final LeetcodeService leetcodeservices;
	
	public Homecontroller(LeetcodeService leetcodeservices) {
		this.leetcodeservices = leetcodeservices;
	}
    @GetMapping("/")
    public String home() {
        return "index";
    }

    
    // handles form submission
    @PostMapping("/analyze")
    					//reads username from form input
    public String analyze(@RequestParam("username") String username, Model model) {
    	try {
    	//model passes data to Html page
//        model.addAttribute("username", username);
    		//here the getprofile doesnt care where the data comes from and which api is calling 
    		//this is called separation of concerns
    	LeetcodeProfile profile = leetcodeservices.getProfile(username);
    	model.addAttribute("profile",profile);
        return "result";
    	}catch(Exception e) {
    		model.addAttribute("error","User not found!");
    		return "index";
    	}
    } 
}