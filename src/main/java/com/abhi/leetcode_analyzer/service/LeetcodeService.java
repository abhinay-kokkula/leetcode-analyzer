package com.abhi.leetcode_analyzer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.abhi.leetcode_analyzer.dto.GraphQLRequest;
import com.abhi.leetcode_analyzer.model.LeetcodeProfile;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import com.abhi.leetcode_analyzer.repository.LeetcodeProfileRepository;


@Service
public class LeetcodeService {
	@Autowired
	private LeetcodeProfileRepository repository;
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

	    // Step 1 : Check Database
	    Optional<LeetcodeProfile> existingProfile =
	            repository.findByUsername(username);

	    if (existingProfile.isPresent()) {
	        System.out.println("Profile loaded from MySQL");
	        return existingProfile.get();
	    }

	    try {

	        // Step 2 : Fetch from LeetCode
	        String response = fetchfromLeetcode(username);

	        ObjectMapper mapper = new ObjectMapper();
	        JsonNode root = mapper.readTree(response);

	        JsonNode stats = root.path("data")
	                .path("matchedUser")
	                .path("submitStats")
	                .path("acSubmissionNum");

	        LeetcodeProfile profile = new LeetcodeProfile();

	        profile.setUsername(username);

	        profile.setTotalSolved(stats.get(0).path("count").asInt());
	        profile.setEasySolved(stats.get(1).path("count").asInt());
	        profile.setMediumSolved(stats.get(2).path("count").asInt());
	        profile.setHardSolved(stats.get(3).path("count").asInt());

	        profile.setContestRating(
	                (int) root.path("data")
	                        .path("userContestRanking")
	                        .path("rating")
	                        .asDouble()
	        );

	        profile.setGlobalRanking(
	                root.path("data")
	                        .path("userContestRanking")
	                        .path("globalRanking")
	                        .asInt()
	        );

	        // Example Performance Score
	        int score = profile.getEasySolved()
	                + (profile.getMediumSolved() * 2)
	                + (profile.getHardSolved() * 3);

	        profile.setPerformanceScore(score);

	        // Step 3 : Save into Database
	        repository.save(profile);

	        System.out.println("Profile saved into DataBase");

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
