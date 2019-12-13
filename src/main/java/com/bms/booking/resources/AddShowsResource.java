package com.bms.booking.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.booking.handlers.MovieHandler;
import com.bms.booking.handlers.ShowsHandler;
import com.bms.booking.requests.AddShowsPojo;
import com.bms.booking.requests.MovieRegisterPojo;

@RestController
@RequestMapping("/shows")
public class AddShowsResource {

	@Autowired
	ShowsHandler showHandler;

	// API for adding shows
	@RequestMapping(value = "/add/v1", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addShow(@RequestBody AddShowsPojo addShowsPojo)
	{
		return showHandler.addShow(addShowsPojo);
	}
	
}
