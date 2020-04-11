package com.rashid.hotelReservation.Service;

import java.util.List;

import com.rashid.hotelReservation.POJO.booking_dtls;
import com.rashid.hotelReservation.POJO.room_master;

public interface AdminService {

	List<room_master> getRoomList();

	int add_data(room_master rm);

	room_master get_room_data(int id);

	List<booking_dtls> getBooking_Dtl();

}
