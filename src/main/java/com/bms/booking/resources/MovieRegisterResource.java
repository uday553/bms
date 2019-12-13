package com.bms.booking.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.booking.handlers.MovieHandler;
import com.bms.booking.requests.MovieRegisterPojo;

@RestController
@RequestMapping("/movie")
public class MovieRegisterResource {

	@Autowired
	MovieHandler movieHandler;

	// API for new movie registration
	@RequestMapping(value = "/register/v1", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> registerMovie(@RequestBody MovieRegisterPojo movieRegisterRequest)
	{
		return movieHandler.registerMovie(movieRegisterRequest);
	}

}
