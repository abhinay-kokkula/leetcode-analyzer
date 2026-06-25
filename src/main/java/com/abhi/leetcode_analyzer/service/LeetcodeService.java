package com.abhi.leetcode_analyzer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.abhi.leetcode_analyzer.dto.GraphQLRequest;
import com.abhi.leetcode_analyzer.model.LeetcodeProfile;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LeetcodeService {
	private final RestClient restClient;
	public LeetcodeService(RestClient restClient) {
		this.restClient = restClient;
	}
	public String fetchfromLeetcode(String username) {
		
		String query = """
				query getUserProfile($username: String!) {

				  matchedUser(username: $username) {
				    username

				    submitStats {
				      acSubmissionNum {
				        difficulty
				        count
				      }
				    }

				    profile {
				      ranking
				      reputation
				    }
				  }

				  userContestRanking(username: $username) {
				    rating
				    globalRanking
				  }
				}
				""";
		
		Map<String,String> variables = new HashMap<>();
		variables.put("username", username);
		GraphQLRequest request = new GraphQLRequest();
		request.setQuery(query);
		request.setVariables(variables);
//		System.out.println("before api");
		try {
			
		String response = restClient.post().uri("https://leetcode.com/graphql").body(request).retrieve().body(String.class);
//		System.out.println(response);
		return response;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public LeetcodeProfile getProfile(String username) {

	    try {
	        String response = fetchfromLeetcode(username);
	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode root = mapper.readTree(response);
	        
	        JsonNode matchedUser = root.path("data")
                    .path("matchedUser");
	        
	        //here we handling the exception of user not found
			if (matchedUser.isMissingNode() || matchedUser.isNull()) {
			throw new RuntimeException("Leetcode User not found");
			}
	        JsonNode stats = root
	                .path("data")
	                .path("matchedUser")
	                .path("submitStats")
	                .path("acSubmissionNum");
	        LeetcodeProfile profile = new LeetcodeProfile();
	        profile.setUsername(username);

	        profile.setTotalSolved(stats.get(0).path("count").asInt());
	        profile.setEasySolved(stats.get(1).path("count").asInt());
	        profile.setMediumSolved(stats.get(2).path("count").asInt());
	        profile.setHardSolved(stats.get(3).path("count").asInt());
	        
	        int score = 
	        		profile.getEasySolved()*1+
	        		profile.getMediumSolved()*3+
	        		profile.getHardSolved()*5;
	        profile.setPerformanceScore(score);
	        if(score < 200) {
	            profile.setProfileLevel("Beginner");
	        }
	        else if(score < 500) {
	            profile.setProfileLevel("Intermediate");
	        }
	        else if(score < 1000) {
	            profile.setProfileLevel("Advanced");
	        }
	        else {
	            profile.setProfileLevel("Expert");
	        }
	        JsonNode contest = root.path("data")
                    .path("userContestRanking");

	        	
	        
				if (!contest.isMissingNode()) {
				 profile.setContestRating(
				 (int) contest.path("rating").asDouble());
				 profile.setGlobalRanking(
				 contest.path("globalRanking").asInt());
				}

	        return profile;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
//	public LeetcodeProfile getProfile(String username) {
//		String response = fetchfromLeetcode(username);
//		System.out.print(response);
//		LeetcodeProfile profile = new LeetcodeProfile();
//		profile.setUsername(username);
//		profile.setTotalSolved(500);
//		return profile;
//	}
//	public String testApi() {
//		String response = restClient.get().uri("https://api.github.com").retrieve().body(String.class);
//		return response;
//	}
//	private LeetcodeProfile dummyProfile(String username) {
//		LeetcodeProfile profile = new LeetcodeProfile();
//		profile.setUsername(username);
//        profile.setTotalSolved(500);
//        profile.setEasySolved(200);
//        profile.setMediumSolved(250);
//        profile.setHardSolved(50);
//
//        profile.setAcceptanceRate(68.5);
//
//        profile.setContestRating(1750);
//        profile.setGlobalRanking(25000);
//
//        profile.setFollowers(120);
//        profile.setFollowing(45);
//
//        return profile;
//	}
}
