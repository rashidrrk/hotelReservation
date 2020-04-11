package com.rashid.hotelReservation.Dao;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.rashid.hotelReservation.POJO.User_dtl;
import com.rashid.hotelReservation.POJO.booking_dtls;
import com.rashid.hotelReservation.POJO.room_master;

public interface UserDao {

	int get_User_exist(String user);

	String get_user_password(String user);

	String getUSER_Authority(String user);

	int check_user(String userid);

	int check_Mno(String mno);

	int register_user(User_dtl user);

	List<room_master> getRoom_dtl(String check_in, String check_out, int guest);

	int insert_booking_dtl(booking_dtls bd);

	String getusername(Object userid);

}
