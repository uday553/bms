package com.bms.booking.requests;

import java.util.List;

public class ReserveSeatPojo {
	private int showid;
	private List<Integer> seatnumbers;
	private long mobileNumber;
	
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public int getShowid() {
		return showid;
	}
	public void setShowid(int showid) {
		this.showid = showid;
	}
	public List<Integer> getSeatnumbers() {
		return seatnumbers;
	}
	public void setSeatnumbers(List<Integer> seatnumbers) {
		this.seatnumbers = seatnumbers;
	}
	
}
