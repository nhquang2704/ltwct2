package vn.iostar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.iostar.configs.DBConnectMySQL;
import vn.iostar.configs.DBConnectSQL;
import vn.iostar.dao.IUserDao;
import vn.iostar.models.UserModel;

public class UserDaoImpl extends DBConnectMySQL implements IUserDao {

    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    public List<UserModel> findAll() {
        List<UserModel> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserModel user = new UserModel(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getNString("password"),
                    rs.getNString("images"),
                    rs.getString("fullname"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    Integer.parseInt(rs.getString("roleid")),
                    rs.getDate("createDate")
                );
                userList.add(user);
            }
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public UserModel findbyId(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));
                user.setPassword(rs.getNString("password"));
                user.setImages(rs.getNString("images"));
                user.setRoleid(Integer.parseInt(rs.getString("roleid")));
                user.setPhone(rs.getString("phone"));
                user.setCreateDate(rs.getDate("createDate"));
                // Set other properties similarly
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(UserModel user) {
        String sql = "INSERT INTO user(id,username, password, images, fullname, emal, phone, roleid, createDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try {
        	//conn = new 
        	ps = conn.prepareStatement(sql); //nem cau sql vao cho thuc thi
        	
        	ps.setInt(1, user.getId());
        	ps.setString(2, user.getUsername());
        	ps.setString(3, user.getPassword());
        	ps.setString(4, user.getImages());
        	ps.setString(5, user.getFullname());
        	ps.setString(6, user.getEmail());
        	ps.setString(7,user.getPhone());
        	ps.setInt(8,user.getRoleid());
        	ps.setDate(9, user.getCreateDate());
        	
        	ps.executeUpdate();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	try {
    		IUserDao userDao = new UserDaoImpl();
    		System.out.println(userDao.findbyId(1));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        
    }

    @Override
    public UserModel findByUserName(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));
                user.setPassword(rs.getNString("password"));
                user.setImages(rs.getNString("images"));
                user.setRoleid(Integer.parseInt(rs.getString("roleid")));
                user.setPhone(rs.getString("phone"));
                user.setCreateDate(rs.getDate("createDate"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean checkExistEmail(String email) {
    boolean duplicate = false;
    String query = "select * from [user] where email = ?";
    try {
	    conn = new DBConnectSQL().getConnection();
	    ps = conn.prepareStatement(query);
	    ps.setString(1, email);
	    rs = ps.executeQuery();
	    if (rs.next()) {
	    duplicate = true;
	    }
	    ps.close();
	    conn.close();
    } catch (Exception ex) {}
    return duplicate;
    }
    
    @Override
    public boolean checkExistUsername(String username) {
    boolean duplicate = false;
    String query = "select * from [User] where username = ?";
    try {
    conn = new DBConnectSQL().getConnection();
    ps = conn.prepareStatement(query);
    ps.setString(1, username);
    rs = ps.executeQuery();
    if (rs.next()) {
    duplicate = true;
    }
    ps.close();
    conn.close();
    } catch (Exception ex) {}
    return duplicate;
    }


    @Override
    public boolean checkExistPhone(String phone) {
        boolean duplicate = false;
        String sql = "SELECT * FROM users WHERE phone = ?";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if (rs.next()) {
                duplicate = true; // Nếu có kết quả thì số điện thoại đã tồn tại
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(); // Đảm bảo đóng tài nguyên
        }
        return duplicate;
    }


    private void closeResources() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
}
