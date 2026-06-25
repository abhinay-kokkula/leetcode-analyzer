package com.abhi.leetcode_analyzer.model;

import lombok.Data;

@Data
public class LeetcodeProfile {
	    private int totalSolved;
	    private int easySolved;
	    private int mediumSolved;
	    private int hardSolved;

	    private double acceptanceRate;

	    private int contestRating;
	    private int globalRanking;

	    private int performanceScore;	    
	    private String username;
	    private String profileLevel;
	    
	    public String getProfileLevel() {
			return profileLevel;
		}

		public void setProfileLevel(String profileLevel) {
			this.profileLevel = profileLevel;
		}
	    
	    public int getPerformanceScore() {
	        return performanceScore;
	    }

	    public void setPerformanceScore(int performanceScore) {
	        this.performanceScore = performanceScore;
	    }
	    public void setUsername(String username) {
	    	this.username = username;
	    }
	    public String getUsername() {
	    	return username;
	    }
	    public int getTotalSolved() {
	        return totalSolved;
	    }

	    public void setTotalSolved(int totalSolved) {
	        this.totalSolved = totalSolved;
	    }

	    public int getEasySolved() {
	        return easySolved;
	    }

	    public void setEasySolved(int easySolved) {
	        this.easySolved = easySolved;
	    }

	    public int getMediumSolved() {
	        return mediumSolved;
	    }

	    public void setMediumSolved(int mediumSolved) {
	        this.mediumSolved = mediumSolved;
	    }

	    public int getHardSolved() {
	        return hardSolved;
	    }

	    public void setHardSolved(int hardSolved) {
	        this.hardSolved = hardSolved;
	    }

	    public double getAcceptanceRate() {
	        return acceptanceRate;
	    }

	    public void setAcceptanceRate(double acceptanceRate) {
	        this.acceptanceRate = acceptanceRate;
	    }

	    public int getContestRating() {
	        return contestRating;
	    }

	    public void setContestRating(int contestRating) {
	        this.contestRating = contestRating;
	    }

	    public int getGlobalRanking() {
	        return globalRanking;
	    }

	    public void setGlobalRanking(int globalRanking) {
	        this.globalRanking = globalRanking;
	    }

//	    public int getFollowers() {
//	        return followers;
//	    }
//
//	    public void setFollowers(int followers) {
//	        this.followers = followers;
//	    }
//
//	    public int getFollowing() {
//	        return following;
//	    }
//
//	    public void setFollowing(int following) {
//	        this.following = following;
//	    }
	}
