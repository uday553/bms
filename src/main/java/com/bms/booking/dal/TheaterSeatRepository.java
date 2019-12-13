package com.bms.booking.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bms.booking.models.BMSTheater;
import com.bms.booking.models.BMSTheaterSeat;

public interface TheaterSeatRepository  extends JpaRepository<BMSTheaterSeat, Long> {

	public List<BMSTheaterSeat> findById(int id);
	public List<BMSTheaterSeat> findByTheaterIdAndDeleted(int theaterId,int deleted);
	
	public List<BMSTheaterSeat> findByIdInAndDeleted(List<Integer> ids,int deleted);
	
	@Modifying(clearAutomatically = true)
	@Query("update BMSTheaterSeat seat set seat.deleted =1 where seat.id =:id")
	public void delete(@Param("id") int id);
}
