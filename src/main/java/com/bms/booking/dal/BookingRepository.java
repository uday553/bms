package com.bms.booking.dal;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bms.booking.models.BMSBooking;
import com.bms.booking.models.BMSMovie;
import com.bms.booking.models.BookingStatus;

public interface BookingRepository  extends JpaRepository<BMSBooking, Long> {
	public List<BMSBooking> findById(int id);
	
	public List<BMSBooking> findByShowIdAndDeleted(int showId,int deleted);
	
	public List<BMSBooking> findByBookingStatusNotInAndSeatIdInAndShowId(List<BookingStatus> status, List<Integer> seats,int showId);
	
	public List<BMSBooking> findByIdInAndDeletedAndContactNumber(List<Integer> ids,int deleted,long contactNumber);
	
	public List<BMSBooking> findByUpdatedAtLessThanAndBookingStatus(Date date,BookingStatus bookingStatus);
	
	public List<BMSBooking> findByBookingKey(String key);
	
	@Modifying(clearAutomatically = true)
	@Query("update BMSBooking booking set booking.bookingStatus = 1 where booking.bookingKey =:key")
	public void bookTicket(@Param("key") String key);
	
	@Modifying(clearAutomatically = true)
	@Query("update BMSBooking booking set booking.deleted =:id where booking.id =:id")
	public void delete(@Param("id") int id);
	
	@Modifying(clearAutomatically = true)
	@Query("update BMSBooking booking set booking.deleted =1 where booking.id =:id")
	public void reserveSeat(@Param("id") int id);
	
	@Modifying(clearAutomatically = true)
	@Query("update BMSBooking booking set booking.deleted =1 where booking.id =:id")
	public void bookSeat(@Param("id") int id);
}
