package com.bms.booking.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class BookingNativeDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public boolean updateBookingStatus(String key) {
		Boolean status = false;
		String query = "update bms_bookings set booking_status=1 where booking_key=\""+key.trim()+"\" and booking_status!=1";
		try {
			Query q = entityManager.createNativeQuery(query);
			if(q.executeUpdate()>0)
				status=true;
		}catch(Exception exp) {
			System.out.println(exp.getMessage());
		}
		return status;
	}
	
//	@SuppressWarnings("unchecked")
//	public List<Integer> getAvailableSeats(int id) {
//		Boolean status = false;
//		String query = "update bms_bookings set booking_status=1 where booking_key=\""+key.trim()+"\" and booking_status!=1";
//		try {
//			Query q = entityManager.createNativeQuery(query);
//			if(q.executeUpdate()>0)
//				status=true;
//		}catch(Exception exp) {
//			System.out.println(exp.getMessage());
//		}
//		return status;
//	}
}
