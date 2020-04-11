package com.rashid.hotelReservation.Controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rashid.hotelReservation.POJO.booking_dtls;
import com.rashid.hotelReservation.POJO.room_master;
import com.rashid.hotelReservation.Service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@ModelAttribute
	public void name(Model m)
	{
		m.addAttribute("HRS","Hotel Reservation System");
	}
	
	@RequestMapping(value="/new-search")
	public ModelAndView new_search(Model m)
	{
		return new ModelAndView("user/new_search");
	}
	
	@RequestMapping(value="/connect-search_data")
	@ResponseBody public ModelAndView search_data(Model m,@RequestParam("check_in") String check_in ,@RequestParam("check_out") String check_out,@RequestParam("guest") int guest) throws ParseException
	{
		List<room_master> rm=userService.getSerach_value(check_in,check_out,guest); 
		
		m.addAttribute("guest", guest);
		m.addAttribute("rm",rm);
		m.addAttribute("check_in",check_in);
		m.addAttribute("check_out",check_out);
		return new ModelAndView("user/search_result");
		
	}
	@RequestMapping(value="/save-booking")
	@ResponseBody public int save_booking(Model m,@ModelAttribute("bd") booking_dtls bd )
	{
		return userService.insert_booking_dtl(bd);
	}
}
