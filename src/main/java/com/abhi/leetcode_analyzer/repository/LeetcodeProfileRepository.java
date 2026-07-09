package com.abhi.leetcode_analyzer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.leetcode_analyzer.model.LeetcodeProfile;

public interface LeetcodeProfileRepository
        extends JpaRepository<LeetcodeProfile, Long> {

    Optional<LeetcodeProfile> findByUsername(String username);

}