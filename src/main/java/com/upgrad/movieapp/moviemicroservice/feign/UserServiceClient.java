package com.upgrad.movieapp.moviemicroservice.feign;

import com.upgrad.movieapp.moviemicroservice.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserServiceClient {

    @GetMapping(value = "/user_app/v1/users/{userId}")
    public User getUser(@PathVariable(name = "userId") int userId);

}
