package com.bms.booking.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.booking.dal.TheaterSeatRepository;
import com.bms.booking.models.BMSTheaterSeat;

@Service
public class TheaterSeatService {

	@Autowired
	TheaterSeatRepository theaterSeatRepository;
	
	public void addSeatsToTheater(int theaterId, int capacity)
	{
		List<BMSTheaterSeat> seatList = new ArrayList<BMSTheaterSeat>();
		for (int i=1;i<=capacity;i++)
		{
			BMSTheaterSeat seat = new BMSTheaterSeat();
			seat.setSeatNumber(i);
			seat.setTheaterId(theaterId);
			seat.setCreatedAt(Calendar.getInstance().getTime());
			seat.setDeleted(0);
			seatList.add(seat);
		}
		theaterSeatRepository.saveAll(seatList);
	}
}
