package com.bms.booking.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bms.booking.models.BMSTheater;
 
@Repository
public interface TheaterRepository extends JpaRepository<BMSTheater, Long> {
	
	public List<BMSTheater> findById(int id);
	
	@Modifying(clearAutomatically = true)
	@Query("update BMSTheater theater set theater.deleted =1 where theater.id =:id")
	public void delete(@Param("id") int id);
	
	
}
