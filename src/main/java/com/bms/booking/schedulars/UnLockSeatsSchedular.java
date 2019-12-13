package com.bms.booking.schedulars;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.bms.booking.dal.BookingRepository;
import com.bms.booking.models.BMSBooking;
import com.bms.booking.models.BookingStatus;

@Configuration
@EnableScheduling
public class UnLockSeatsSchedular {

	@Autowired
	BookingRepository bookingRepository; 
	
	@Scheduled(fixedRate = 3600000)
	public void unlockSeasts() {
		Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.MINUTE, -2);
		List<BMSBooking> reservedSeatsToUnlock = bookingRepository.findByUpdatedAtLessThanAndBookingStatus(calendar.getTime(), BookingStatus.RESERVED);
		for(int i=0;i<reservedSeatsToUnlock.size();i++)
		{
			BMSBooking seat = reservedSeatsToUnlock.get(i);
			seat.setBookingStatus(BookingStatus.AVAILABLE);
			seat.setUpdatedAt(Calendar.getInstance().getTime());
			seat.setContactNumber(0);
			seat.setBookingKey(null);
		}
		bookingRepository.saveAll(reservedSeatsToUnlock);
	}
}




