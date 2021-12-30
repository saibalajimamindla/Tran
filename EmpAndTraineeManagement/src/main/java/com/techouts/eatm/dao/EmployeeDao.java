package com.techouts.eatm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.techouts.eatm.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {
	
	
	@Query(value = "select * from employees where emp_name =?1" ,nativeQuery = true)
	@Transactional
	public Employee getByName(String name);
	
	@Query(value = "select * from employees where emp_id =?1" ,nativeQuery = true)
	@Transactional
	public Employee getById(Long id);
	
	@Query(value = "select * from employees where track_id =?1" ,nativeQuery = true)
	@Transactional
	public List<Employee> getByTrack(long l);
}
