package com.bms.booking.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.booking.handlers.TheaterHandler;
import com.bms.booking.requests.TheaterRegisterPojo;

@RestController
@RequestMapping("/theater")
public class TheaterServiceResource {

	@Autowired
	TheaterHandler bookingSeatHandler;

	// API for new theater registration
	@RequestMapping(value = "/register/v1", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> registerTheater(@RequestBody TheaterRegisterPojo theater)
	{
		return bookingSeatHandler.registerTheater(theater);
	}

	// API for close theater 
	@RequestMapping(value = "/close/v1/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> closeTheater(@PathVariable int id)
	{
		return bookingSeatHandler.closeTheater(id);
	}
}
