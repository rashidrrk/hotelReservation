package com.rashid.hotelReservation.Dao;

import java.util.List;

import com.rashid.hotelReservation.POJO.booking_dtls;
import com.rashid.hotelReservation.POJO.room_master;

public interface AdminDao {

	List<room_master> getRoomList();

	int add_data(room_master rm);

	int update_data(room_master rm);

	room_master getroom_data(int id);

	List<booking_dtls> getBooking_dtl();

}
