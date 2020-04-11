package com.rashid.hotelReservation.ServiceImpl;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rashid.hotelReservation.Dao.UserDao;
import com.rashid.hotelReservation.POJO.User_dtl;
import com.rashid.hotelReservation.POJO.booking_dtls;
import com.rashid.hotelReservation.POJO.room_master;
import com.rashid.hotelReservation.Service.UserService;


public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	BCryptPasswordEncoder bcryptEncoder;
	
	@Override
	public int authorizedUser(String user, String raw_pass) {
		// TODO Auto-generated method stub
		try {
			
			
		logger.info("USER_ID="+user+"====Password="+raw_pass);

			int if_count =userDao.get_User_exist(user);
			if(if_count==1)
			{			
                    String pass=userDao.get_user_password(user);
                    if(bcryptEncoder.matches(raw_pass,pass)){return 1;}
                    else return 2;

			}
			else return 3;
	}
	catch(Exception e)
	{
		return 4;
	}
	}

	@Override
	public String getAuthority(String user) {
		// TODO Auto-generated method stub
		return userDao.getUSER_Authority(user);
	}

	@Override
	public int register_user(User_dtl user) {
		// TODO Auto-generated method stub
		String pass=bcryptEncoder.encode(user.getNew_password());
		user.setNew_password(pass);
		return userDao.register_user(user);
		
	}

	@Override
	public List<room_master> getSerach_value(String check_in, String check_out, int guest) {
		// TODO Auto-generated method stub
		
		
		List<room_master> rm=userDao.getRoom_dtl(check_in,check_out,guest);
		return rm;
	}

	@Override
	public int insert_booking_dtl(booking_dtls bd) {
		// TODO Auto-generated method stub
		return userDao.insert_booking_dtl(bd);
	}
}
