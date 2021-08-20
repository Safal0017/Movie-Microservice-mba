package com.upgrad.movieapp.moviemicroservice.dao;

import com.upgrad.movieapp.moviemicroservice.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This layer will be used to talk to databases
 */
public interface MovieDao extends JpaRepository<Movie, Integer> {
}
