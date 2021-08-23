package com.upgrad.movieapp.moviemicroservice.feign;


import com.upgrad.movieapp.moviemicroservice.entities.Theatre;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "theatre-service", url = "http://localhost:8082")
public interface TheatreServiceClient {

    @GetMapping(value = "/theatre_app/v1/theatres/{theatreId}/movie/{movieId}")
    public Theatre getTheatre(@PathVariable(name = "theatreId") int theatreId,
                              @PathVariable(name = "movieId") int movieId);

}
