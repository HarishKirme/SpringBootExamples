package com.springboot.rest.webservices.Demo.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.rest.webservices.Demo.Entity.User;

@Repository
@Transactional
public class UserService implements RowMapper<User>  {

	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User u = new User();
		
		u.setId(rs.getInt("id"));
		u.setName(rs.getString("name"));
		u.setSchool(rs.getString("school"));
		
		return u;
	}
	
	
	
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	 
	 
	 private SimpleJdbcInsert simpleJdbcInsert;
	 
	 @Autowired
	protected DataSource dataSource;
		
		
	  
	    public List<User> findAllUser() {
	        

	    	 String sql = "SELECT * FROM User";
	    	 
	    	 List<User> listUser = jdbcTemplate.query(sql,
	    	            BeanPropertyRowMapper.newInstance(User.class));
	    	 
	    	    return listUser;
	    	 
	    	 
	    }
	    
	    
	    public User findUserById(int id) {

	    	
	    	String sql = "SELECT * FROM User WHERE id = ? ";
	    	
	    	User userById = (User)jdbcTemplate.queryForObject(sql, new Object[] {id},new UserService());
	    	
				
	    	return userById;
	    	
	    }
	    	  
	    public int newUser(User user) {
	    		
		  SimpleJdbcInsert insertUser = new SimpleJdbcInsert(jdbcTemplate);
		  
		  insertUser.withTableName("User").usingColumns("name","school");
		  
		  BeanPropertySqlParameterSource param = new
		  BeanPropertySqlParameterSource(user);
		  
		 int k = insertUser.execute(param);
			
		 return k;
			
	    }
	  
	  
	  
	    public void delete(String name) {
	    	
	    	String sql = " Delete From User where name = ?";
	    	
	    	jdbcTemplate.update(sql,name);
	    	
	    }


		public void updateUser(int id,User user) {
		
			String sql = "UPDATE User SET name = ? , school = ? where id = ? ";
			
			jdbcTemplate.update(sql, user.getName(),user.getSchool(),id);
			
		}


		public void updateSchoolService(int id,String school) {
			
			String sql = "Update User SET school = ? where id = ? ";
			
			jdbcTemplate.update(sql, school,id);
		}
		
	
	
}
