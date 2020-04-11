package com.rashid.hotelReservation.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rashid.hotelReservation.Dao.AdminDao;
import com.rashid.hotelReservation.POJO.booking_dtls;
import com.rashid.hotelReservation.POJO.room_master;
import com.rashid.hotelReservation.Service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	AdminService adminService;

	@ModelAttribute
	public void name(Model m)
	{
		m.addAttribute("HRS","Hotel Reservation System");
	}
	
	@RequestMapping(value="/rooms-list")
	public ModelAndView room_list(Model m)
	{
		List<room_master> rm=adminService.getRoomList();
		m.addAttribute("rm",rm);
		return new ModelAndView("admin/room_list");
	}
	@RequestMapping(value="/connect-room-data")
	@ResponseBody public int connect_room_data(Model m,@ModelAttribute("rm") room_master rm)
	{
		return adminService.add_data(rm);
	}
	@RequestMapping(value="/get_room_data")
	@ResponseBody public room_master get_room_data(Model m,@RequestParam("id") int id)
	{
		return adminService.get_room_data(id);
	}
	@RequestMapping(value="/view-booking-details")
	public ModelAndView view_booking_details(Model m)
	{
		List<booking_dtls> rm=adminService.getBooking_Dtl();
		m.addAttribute("rm",rm);
		return new ModelAndView("admin/view_dtl");
	}
}
