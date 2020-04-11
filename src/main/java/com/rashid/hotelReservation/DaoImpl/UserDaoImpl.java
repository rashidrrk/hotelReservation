package com.rashid.hotelReservation.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.rashid.hotelReservation.Dao.UserDao;
import com.rashid.hotelReservation.POJO.User_dtl;
import com.rashid.hotelReservation.POJO.booking_dtls;
import com.rashid.hotelReservation.POJO.room_master;
import com.rashid.hotelReservation.ServiceImpl.UserServiceImpl;

public class UserDaoImpl implements UserDao {

	@Autowired
	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {    
	    this.template = template; 
	  
	}
	
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());
	
	@Override
	public int get_User_exist(String user) {
		// TODO Auto-generated method stub
	String sql="Select count(ID) from user_dtl where USER_ID=?";
	logger.info(sql);
	return template.queryForObject(sql,new Object[] {user},int.class);
	}

	@Override
	public String get_user_password(String user) {
		// TODO Auto-generated method stub
		String sql="Select Password from user_dtl where USER_ID=?";
		logger.info(sql);
		return template.queryForObject(sql,new Object[] {user},String.class);
	}

	@Override
	public String getUSER_Authority(String user) {
		// TODO Auto-generated method stub
		String sql="SELECT AUTHORITY_TYPE FROM authority_data as a inner join user_dtl as u on a.ID=u.AUTHORITY Where u.USER_ID=? ";
		logger.info(sql);
		String access = template.queryForObject(sql,new Object[] {user},String.class);
		return access;
		
		
	}

	@Override
	public int check_user(String userid) {
		// TODO Auto-generated method stub
		String sql="SELECT count(USER_ID) FROM user_dtl  Where USER_ID=? ";
		logger.info(sql);
		int count = template.queryForObject(sql,new Object[] {userid},int.class);
		return count;
	}

	@Override
	public int check_Mno(String mno) {
		// TODO Auto-generated method stub
		String sql="SELECT count(USER_ID) FROM user_dtl  Where MOBILE_NUMBER=? ";
		logger.info(sql);
		int count = template.queryForObject(sql,new Object[] {mno},Integer.class);
		logger.info("-------------"+count);
		return count;
	}

	@Override
	public int register_user(User_dtl user) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO `user_dtl` (`USER_ID`, `NAME`, `MOBILE_NUMBER`, `EMAIL_ID`, `PASSWORD`) VALUES (?,?,?,?,?)";
		logger.info(sql);
		return template.update(sql,new Object[] {user.getUserId(),user.getName(),user.getMobile_number(),user.getEmail_id(),user.getNew_password()});
		
	}

	
	@Override
	public List<room_master> getRoom_dtl(String check_in, String check_out, int guest) {
		// TODO Auto-generated method stub
		String sql="SELECT r.ID,ROOM_TYPE,CAPACITY,HOTEL_NAME,(TOTAL_ROOM-ifnull(NO_OF_ROOM_BOOKED,'0')) as aval_room FROM rooms_master r left join `booking_dtl` b on (b.ROOM_ID=r.ID AND ((CHECK_IN_DATE BETWEEN ? AND ?) OR (CHECK_OUT_DATE BETWEEN ? AND ?)) AND BOOKING_STATUS='A' ) where STATUS='A' AND (TOTAL_ROOM-ifnull(NO_OF_ROOM_BOOKED,'0'))>=(?/CAPACITY)";
		logger.info(sql);
		return template.query(sql,new Object[] {check_in,check_out,check_in,check_out,guest},new RowMapper<room_master>() {
	           @Override
				public room_master mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
	        	   room_master r=new room_master();
	        	   r.setId(rs.getInt(1));
	        	   r.setRoom_type(rs.getString(2));
	        	   r.setCapacity(rs.getInt(3));
	        	   r.setHotel_name(rs.getString(4));
	        	   r.setTotal_room(rs.getInt(5));
					return r;
				}
				
			});
	}

	@Override
	public int insert_booking_dtl(booking_dtls bd) {
		// TODO Auto-generated method stub
		Object userid=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String sql="INSERT INTO `booking_dtl` (`BOOKED_BY`,`ROOM_ID`, `NO_OF_GUEST`, `CHECK_IN_DATE`, `CHECK_OUT_DATE`,`NO_OF_ROOM_BOOKED`) VALUES (?,?,?,?,?,?)";
		logger.info(sql);
		return template.update(sql,new Object[] {userid,bd.getId(),bd.getGuest(),bd.getCheck_in_date(),bd.getCheck_out_date(),bd.getNo_room()});
	}

	@Override
	public String getusername(Object userid) {
		// TODO Auto-generated method stub
		String sql="Select Name from user_dtl where USER_ID=?";
		logger.info(sql);
		try {return template.queryForObject(sql,new Object[] {userid},String.class);}
		catch(EmptyResultDataAccessException e)
		{
			return "";
		}
	}

}
