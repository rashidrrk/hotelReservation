package com.rashid.hotelReservation.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.rashid.hotelReservation.Dao.AdminDao;
import com.rashid.hotelReservation.POJO.booking_dtls;
import com.rashid.hotelReservation.POJO.room_master;

public class AdminDaoImpl implements AdminDao {
	@Autowired
	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {    
	    this.template = template; 
	  
	}
	
	private static final Logger logger = Logger.getLogger(AdminDaoImpl.class.getName());

	@Override
	public List<room_master> getRoomList() {
		// TODO Auto-generated method stub
		String sql="Select ID,ROOM_TYPE,CAPACITY,HOTEL_NAME,if(STATUS='A','Active','Inactive'),TOTAL_ROOM from rooms_master";
		logger.info(sql);
		return template.query(sql,new RowMapper<room_master>() {
           @Override
			public room_master mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
        	   room_master r=new room_master();
        	   r.setId(rs.getInt(1));
        	   r.setRoom_type(rs.getString(2));
        	   r.setCapacity(rs.getInt(3));
        	   r.setHotel_name(rs.getString(4));
        	   r.setStatus(rs.getString(5));
        	   r.setTotal_room(rs.getInt(6));
				return r;
			}
			
		});
	}

	@Override
	public int add_data(room_master rm) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO `rooms_master` (`ROOM_TYPE`,`CAPACITY`,`HOTEL_NAME`,STATUS,TOTAL_ROOM) VALUES (?,?,?,?,?)";
		logger.info("sql");
		return template.update(sql,new Object[] {rm.getRoom_type(),rm.getCapacity(),rm.getHotel_name(),rm.getStatus(),rm.getTotal_room()});
		
	}

	@Override
	public int update_data(room_master rm) {
		// TODO Auto-generated method stub
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		String sql="update `rooms_master` set `ROOM_TYPE`=?,`CAPACITY`=?,STATUS=?,TOTAL_ROOM=?,MODIFICATION_DATE=? where ID=?";
		logger.info("sql");
		return template.update(sql,new Object[] {rm.getRoom_type(),rm.getCapacity(),rm.getStatus(),rm.getTotal_room(),date,rm.getId()});
	}

	@Override
	public room_master getroom_data(int id) {
		// TODO Auto-generated method stub
		String sql="Select ID,ROOM_TYPE,CAPACITY,HOTEL_NAME,Status,TOTAL_ROOM from rooms_master where ID=?";
		logger.info(sql);
		return template.queryForObject(sql,new Object[] {id},new RowMapper<room_master>() {
           @Override
			public room_master mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
        	   room_master r=new room_master();
        	   r.setId(rs.getInt(1));
        	   r.setRoom_type(rs.getString(2));
        	   r.setCapacity(rs.getInt(3));
        	   r.setHotel_name(rs.getString(4));
        	   r.setStatus(rs.getString(5));
        	   r.setTotal_room(rs.getInt(6));
				return r;
			}
			
		});
	}

	@Override
	public List<booking_dtls> getBooking_dtl() {
		// TODO Auto-generated method stub
		String sql="SELECT NAme,No_OF_GUEST,DATE_FORMAT(CHECK_IN_DATE,'%d-%m-%y'),DATE_FORMAT(CHECK_OUT_DATE,'%d-%m-%y'),NO_OF_ROOM_BOOKED,ROOM_TYPE,HOTEL_NAME FROM `booking_dtl` b inner join user_dtl u on u.USER_ID=b.BOOKED_By inner join rooms_master r on r.ID=b.ROOM_ID";
		logger.info(sql);
		return template.query(sql,new RowMapper<booking_dtls>() {
	           @Override
				public booking_dtls mapRow(ResultSet rs, int rowNum) throws SQLException {
					// TODO Auto-generated method stub
	        	   booking_dtls r=new booking_dtls();
	        	   r.setCustomer_name(rs.getString(1));
	        	   r.setGuest(rs.getString(2));
	        	   r.setCheck_in_date(rs.getString(3));
	        	   r.setCheck_out_date(rs.getString(4));
	        	   r.setNo_room(rs.getInt(5));
	        	   r.setRoom_type(rs.getString(6));
	        	   r.setHotel_name(rs.getString(7));
					return r;
				}
				
			});
	}
}
