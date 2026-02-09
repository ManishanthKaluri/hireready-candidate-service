package com.hireready.candidate.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hireready.candidate.domain.SkillProfile;

public interface SkillProfileRepository extends JpaRepository<SkillProfile, Long> {

} 
