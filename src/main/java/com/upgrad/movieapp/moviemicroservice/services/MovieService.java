package com.upgrad.movieapp.moviemicroservice.services;

import com.upgrad.movieapp.moviemicroservice.entities.Movie;
import com.upgrad.movieapp.moviemicroservice.entities.Theatre;
import com.upgrad.movieapp.moviemicroservice.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {

    public Movie acceptMovieDetails(Movie movie);

    public List<Movie> acceptMultipleMovieDetails(List<Movie> movies);

    public Movie getMovieDetails(int id);

    public Movie updateMovieDetails(int id , Movie movie);

    public boolean deleteMovie(int id);

    public List<Movie> getAllMovies();

    public Page<Movie> getPaginatedMovieDeatails(Pageable pageable);

    public boolean bookMovie(User fromUser, Movie requestedMovie, Theatre requestedTheatre);

}
