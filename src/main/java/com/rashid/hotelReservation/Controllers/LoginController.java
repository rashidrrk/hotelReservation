package com.rashid.hotelReservation.Controllers;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.rashid.hotelReservation.Dao.UserDao;
import com.rashid.hotelReservation.POJO.User_dtl;
import com.rashid.hotelReservation.Service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	UserService userService;
	
	@ModelAttribute
	public void name(Model m)
	{
		m.addAttribute("HRS","Hotel Reservation System");
	}
	
	@RequestMapping(value="/login")
	public ModelAndView login_page(Model m)
	{
		return new ModelAndView("common/login_page");
	}
	@RequestMapping(value="/checkuserId")
	@ResponseBody public int check_userid(Model m,@RequestParam("userid") String userid)
	{
		return userDao.check_user(userid);
		
	}
	@RequestMapping(value="/checkMobileNo")
	@ResponseBody public int check_mobileNo(Model m,@RequestParam("mno") String mno)
	{
		return userDao.check_Mno(mno);
		
	}
	@RequestMapping(value="/register", method = RequestMethod.POST)
	@ResponseBody public int check_mobileNo(Model m,@ModelAttribute("user") User_dtl user)
	{
		
		return userService.register_user(user);
		
	}
	@RequestMapping(value="/home")
	public ModelAndView home(Model m)
	{
		Object userid=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=userDao.getusername(userid);
		m.addAttribute("username",username);
		return new ModelAndView("common/home");
		
	}
	@RequestMapping(value = "/logout")
	   public ModelAndView logout(HttpSession session, Model model,RedirectAttributes redir ) throws Exception {

			session.invalidate();
			
		    redir.addFlashAttribute("success", "Logout Successfully");
		    return new ModelAndView(new RedirectView("/hotelReservation/login")); 
		  }




	
}
