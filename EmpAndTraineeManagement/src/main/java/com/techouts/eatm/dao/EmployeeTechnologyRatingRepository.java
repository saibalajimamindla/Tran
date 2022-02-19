package com.techouts.eatm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techouts.eatm.entity.EmployeeTechnologyRating;
import com.techouts.eatm.entity.TechnologyRatingKey;

@Repository
public interface EmployeeTechnologyRatingRepository extends JpaRepository<EmployeeTechnologyRating, TechnologyRatingKey> {
	
	@Query(value = "select * from employee_technology_rating where emp_id = :id" ,nativeQuery = true)
	public List<EmployeeTechnologyRating> getbyEmpid(Long id);
	
	@Modifying
	@Query(value ="DELETE from employee_technology_rating WHERE emp_id =:id" ,nativeQuery = true)
	@Transactional
	public void removeEmployeeRatings(Long id);

}
