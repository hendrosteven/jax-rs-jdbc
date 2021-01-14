/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kelaskoding.rest;

import com.kelaskoding.model.User;
import com.kelaskoding.repo.UserRepository;
import com.kelaskoding.utilty.LoadDatasource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author jarvis
 */
@Path("/users")
@ApplicationScoped
public class UserController {
    
    private UserRepository userRepository;
    
    public UserController(){
        this.userRepository = new UserRepository(LoadDatasource.getConnection());
    }
    
    @GET
    public Response findAll(){
        List<User> users = userRepository.findAll();
        return Response.ok(users).build();
    }
    
    @POST
    public Response create(User user){
        int affectedRow = userRepository.create(user);
        Map response = new HashMap();
        response.put("affectedRow", affectedRow);
        return Response.ok(response).build();
    }
    
    @GET
    @Path("/{id}")
    public Response findOne(@PathParam("id") Long id){
        User user = userRepository.findOne(id);
        return Response.ok(user).build();
    }
}
