package com.rashid.hotelReservation.POJO;

public class booking_dtls {

	private int id;
	private String guest;
	private String check_in_date;
	private String check_out_date;
	private int no_room;
	private String customer_name;
	private String room_type;
	private String hotel_name;
	
	
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public int getNo_room() {
		return no_room;
	}
	public void setNo_room(int no_room) {
		this.no_room = no_room;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}
	public String getCheck_in_date() {
		return check_in_date;
	}
	public void setCheck_in_date(String check_in_date) {
		this.check_in_date = check_in_date;
	}
	public String getCheck_out_date() {
		return check_out_date;
	}
	public void setCheck_out_date(String check_out_date) {
		this.check_out_date = check_out_date;
	}
	
	
	
}
