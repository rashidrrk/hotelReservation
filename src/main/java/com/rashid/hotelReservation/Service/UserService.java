package com.rashid.hotelReservation.Service;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.rashid.hotelReservation.POJO.User_dtl;
import com.rashid.hotelReservation.POJO.booking_dtls;
import com.rashid.hotelReservation.POJO.room_master;

public interface UserService {
	
	int authorizedUser(String user,String password);
	String getAuthority(String user);
	int register_user(User_dtl user);
	List<room_master> getSerach_value(String check_in, String check_out, int guest);
	int insert_booking_dtl(booking_dtls bd);

}
