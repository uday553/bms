package com.bms.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookingApplication {

	//CREATE SCHEMA `bms` DEFAULT CHARACTER SET utf8 ;

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}

}
