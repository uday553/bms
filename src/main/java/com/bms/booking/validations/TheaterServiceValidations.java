package com.bms.booking.validations;

import org.springframework.stereotype.Component;

import com.bms.booking.requests.TheaterRegisterPojo;

@Component
public class TheaterServiceValidations {

	public boolean isValidTheaterRegisterRequest(TheaterRegisterPojo theaterPojo)
	{
		if(theaterPojo!=null && theaterPojo.getAddress()!=null && theaterPojo.getName()!=null && theaterPojo.getName().trim().length()>0 && theaterPojo.getAddress().trim().length()>0 && theaterPojo.getCapacity()>0)
			return true;
		return false;
	}
}
