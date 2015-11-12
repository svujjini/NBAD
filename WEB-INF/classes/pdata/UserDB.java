/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.UserBean;

/**
 *
 * @author Sneha
 */
public class UserDB {

    public static void addUser(String firstName, String lastName, String email, String address1, String address2, String city, String state, String postCode, String country, String password){
        UserBean user = new UserBean();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAddress1(address1);
        user.setAddress2(address2);
        user.setCity(city);
        user.setState(state);
        user.setPostCode(postCode);
        user.setCountry(country);
        user.setPassword(password);
        addUser(user);
    }
    public static int addUser(UserBean user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO User (firstName,lastName,email,address1,address2,city,state,ostCode,country,password) "
                + "VALUES (?, ?, ?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getAddress1());
            ps.setString(6, user.getAddress2());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getState());
            ps.setString(9, user.getPostCode());
            ps.setString(10, user.getCountry());
            ps.setString(11, user.getPassword());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    public static ArrayList<UserBean> getAllUsers(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User ";
        try {
            ps = connection.prepareStatement(query);
            //ps.setInt(1, userID);
            rs = ps.executeQuery();
            ArrayList<UserBean> userList = new ArrayList<>();
            if (rs.next()) {
                UserBean user = new UserBean();
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setAddress1(rs.getString("address1"));
                user.setAddress2(rs.getString("address2"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setPostCode(rs.getString("postCode"));
                user.setCountry(rs.getString("country"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
            }
            return userList;
        }catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        
    }
    }
    /**
     *
     * @param userID
     * @return
     */
    public static UserBean getUser(int userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User "
                + "WHERE userID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            UserBean user = null;
            if (rs.next()) {
                user = new UserBean();
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setAddress1(rs.getString("address1"));
                user.setAddress2(rs.getString("address2"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));
                user.setPostCode(rs.getString("postCode"));
                user.setCountry(rs.getString("country"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        }catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }    
    public static int delete(UserBean user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM User "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(3, user.getEmail());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
public static int update(UserBean user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE User SET "
                + "firstName = ?, "
                + "lastName = ? "
                + "email = ? "
                + "address1 = ? "
                + "address2 = ? "
                + "city = ? "
                + "state = ? "
                + "postCode = ? "
                + "country = ? "
                + "password = ? "
                + "WHERE userID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getAddress1());
            ps.setString(6, user.getAddress2());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getState());
            ps.setString(9, user.getPostCode());
            ps.setString(10, user.getCountry());
            ps.setString(11, user.getPassword());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
