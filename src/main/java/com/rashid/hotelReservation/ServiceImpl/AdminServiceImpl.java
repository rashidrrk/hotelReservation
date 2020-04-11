package com.rashid.hotelReservation.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rashid.hotelReservation.Dao.AdminDao;
import com.rashid.hotelReservation.POJO.booking_dtls;
import com.rashid.hotelReservation.POJO.room_master;
import com.rashid.hotelReservation.Service.AdminService;

public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;
	
	@Override
	public List<room_master> getRoomList() {
		// TODO Auto-generated method stub
		return adminDao.getRoomList();
	}

	@Override
	public int add_data(room_master rm) {
		// TODO Auto-generated method stub

		String status=rm.getStatus();
		System.out.println(""+status);
		if(status.equals("on")) {rm.setStatus("A");} else rm.setStatus("X");
		if(rm.getId()==0)
		{
		return adminDao.add_data(rm);
		}
		else return adminDao.update_data(rm);
	}

	@Override
	public room_master get_room_data(int id) {
		// TODO Auto-generated method stub
		return adminDao.getroom_data(id);
	}

	@Override
	public List<booking_dtls> getBooking_Dtl() {
		// TODO Auto-generated method stub
		return adminDao.getBooking_dtl();
	}

}
