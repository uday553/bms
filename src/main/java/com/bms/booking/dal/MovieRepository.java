package com.bms.booking.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bms.booking.models.BMSMovie;

public interface MovieRepository  extends JpaRepository<BMSMovie, Long> {
	public List<BMSMovie> findById(int id);
	public List<BMSMovie> findByIdAndDeleted(int id,int deleted);
	@Modifying(clearAutomatically = true)
	@Query("update BMSMovie movie set movie.deleted =1 where movie.id =:id")
	public void delete(@Param("id") int id);
	
}
