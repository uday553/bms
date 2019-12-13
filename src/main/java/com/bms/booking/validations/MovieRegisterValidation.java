package com.bms.booking.validations;

import org.springframework.stereotype.Component;

import com.bms.booking.requests.MovieRegisterPojo;

@Component
public class MovieRegisterValidation {
	
	public boolean movieRegisterValidation(MovieRegisterPojo movieRegisterPojo)
	{
		if(movieRegisterPojo!=null && movieRegisterPojo.getName()!=null && movieRegisterPojo.getName().trim().length()>0 )
			return true;
		return false;
	}

}
