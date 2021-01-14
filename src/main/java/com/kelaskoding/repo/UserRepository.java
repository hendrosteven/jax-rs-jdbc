/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kelaskoding.repo;

import com.kelaskoding.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jarvis
 */
public class UserRepository {
    
    private Connection conn;
    
    public UserRepository(Connection conn){
        this.conn = conn;
    }
    
    public int create(User user){
        String sql = "insert into tbl_user(name, email) values(?,?)";
        PreparedStatement pst = null;
        try{
            pst = this.conn.prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            return pst.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
            return 0;
        }finally{
            if(pst!=null){
                try{
                    pst.close();
                    this.conn.close();
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        String sql = "select id, name, email from tbl_user";
        PreparedStatement pst = null;
        try{
            pst = this.conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
            return users;
        }catch(Exception ex){
            ex.printStackTrace();
            return users;
        }finally{
            if(pst!=null){
                try{
                    pst.close();
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public User findOne(Long id){
        User user = null;
        String sql = "select id, name, email from tbl_user where id=?";
        PreparedStatement pst = null;
        try{
            pst = this.conn.prepareStatement(sql);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
            }
            return user;
        }catch(Exception ex){
            ex.printStackTrace();
            return user;
        }finally{
            if(pst!=null){
                try{
                    pst.close();
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}
