package com.techouts.eatm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techouts.eatm.entity.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

}
