package com.techouts.eatm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techouts.eatm.model.Technology;

@Repository
public interface TechnologyDao extends JpaRepository<Technology, Long> {
	
	
	@Query(value = "select * from technologies where technology_name=?1" ,nativeQuery = true)
	@Transactional
	public Technology findByName(String name);
	
	
	@Query(value = "DELETE FROM technologies t WHERE t.technology_name =?1" ,nativeQuery = true)
	@Transactional
	public void removeByName(String name);
	
	
	@Query(value = "select * from technologies where technology_type=?1" ,nativeQuery = true)
	@Transactional
	public List<Technology> findBytype(String name);

}
