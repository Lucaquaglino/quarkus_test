package org.luca.resources;

import io.quarkus.security.User;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.luca.models.Users;
import org.luca.repository.UserRepository;
import org.luca.services.UserService;

import java.beans.Transient;
import java.util.Iterator;
import java.util.List;

@Path("/user")
public class UserResource {
    @Inject
    UserService userService;
    private final UserRepository userRepository;
    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response createUser(Users user) {
userService.saveUser(user);
return Response.ok().build();
    }


    @GET
    @Path("/list")

    public Iterable<Users> FindALl() {
       return userRepository.findAll();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response creaUser(Users user) {
        userRepository.save(user);
        return Response.ok().build();
    }


//    @GET
//    @Path("/list")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Users> getUsers() {
//        return userService.getAllUsers();
//    }
}
