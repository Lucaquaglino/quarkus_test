package org.luca.resources;

import com.fasterxml.jackson.databind.util.BeanUtil;
import io.quarkus.security.User;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.beanutils.BeanUtils;
import org.luca.models.House;
import org.luca.models.UserDTO;
import org.luca.models.Users;
import org.luca.repository.UserRepository;
import org.luca.services.UserService;

import java.beans.Transient;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Path("/user")
public class UserResource {
    @Inject
    UserService userService;



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response createUser(Users user) {
userService.saveUser(user);
return Response.ok().build();
    }



    @POST
    @Path("/house")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response createHouse(House house) {
        userService.saveHouse(house);
        return Response.ok().build();
    }

//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//
//    public Response createUser(UserDTO userDTO)  {
//
//        Users user = new Users();
//
//        try {
//            BeanUtils.copyProperties(user, userDTO);
//            userService.saveUser(user);
//        }catch (Exception e) {
//          return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
//        }
//
//        return Response.status(Response.Status.CREATED).entity(userDTO).build();
//    }



 @PUT
 @Path("/assegnaCasa")
 public Response assegnaCasa(@QueryParam("userId") Long userId, List<Long> houseIds) {
        try {
            Users userAggiornato = userService.assegnaCasa(userId, houseIds);
            return Response.ok().entity(userAggiornato).build();
        } catch ( Exception e ) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }



 }



    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Users> getUsers() {
        return userService.getAllUsers();
    }













    @GET
    @Path("/{houseId}/user")
    public Response getUserByHouseId(@PathParam("houseId") Long houseId) {
        try {
            Optional<Users> user = userService.findUserByHouseId(houseId);
            return Response.ok(user).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }


    @GET
    @Path("/findByHouseAddress")
    public Response findUserByHouseAddress(@QueryParam("houseAddress") String houseAddress) {
        try {
            Users user = userService.findUserByHouseAddress(houseAddress);
            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(user).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }


}
