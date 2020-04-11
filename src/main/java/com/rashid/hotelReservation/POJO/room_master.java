package com.rashid.hotelReservation.POJO;

public class room_master {

	private int id;
	private String hotel_name;
	private String room_type;
	private int capacity;
	private String status;
	private int total_room;
	
	
	public int getTotal_room() {
		return total_room;
	}
	public void setTotal_room(int total_room) {
		this.total_room = total_room;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHotel_name() {
		return hotel_name;
	}
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
