package com.upgrad.movieapp.moviemicroservice.services;

import com.upgrad.movieapp.moviemicroservice.dao.MovieDao;
import com.upgrad.movieapp.moviemicroservice.entities.Movie;
import com.upgrad.movieapp.moviemicroservice.entities.Theatre;
import com.upgrad.movieapp.moviemicroservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService{

    @Value("${userApp.url}")
    private String userAppUrl;


    @Value("${theatreApp.url}")
    private String theatreAppUrl;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Movie acceptMovieDetails(Movie movie) {
        return movieDao.save(movie);
    }

    @Override
    public List<Movie> acceptMultipleMovieDetails(List<Movie> movies) {
        List<Movie> savedMovies = new ArrayList<>();
        for(Movie movie : movies) {
            savedMovies.add(acceptMovieDetails(movie));
        }
        return savedMovies;
    }

    @Override
    public Movie getMovieDetails(int id) {
        return movieDao.findById(id).get();
    }

    @Override
    public Movie updateMovieDetails(int id, Movie movie) {
        Movie savedMovie = getMovieDetails(id);
        savedMovie.setDuration(movie.getDuration());
        savedMovie.setTrailerUrl(movie.getTrailerUrl());
        savedMovie.setCoverPhotoUrl(movie.getCoverPhotoUrl());
        savedMovie.setReleaseDate(movie.getReleaseDate());
        savedMovie.setMovieName(movie.getMovieName());
        savedMovie.setMovieDescription(movie.getMovieDescription());

        movieDao.save(savedMovie);

        return savedMovie;
    }

    @Override
    public boolean deleteMovie(int id) {
        Movie savedMovie = getMovieDetails(id);
        if(savedMovie == null){
            return false ;
        }
        movieDao.delete(savedMovie);
        return true;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDao.findAll();
    }

    @Override
    public Page<Movie> getPaginatedMovieDeatails(Pageable pageable) {
        return movieDao.findAll(pageable);
    }

    @Override
    public boolean bookMovie(User user, Movie movie, Theatre theatre) {

        Optional<Movie> requestedMovie = movieDao.findById(movie.getMovieId());
        if(!requestedMovie.isPresent())
            return false;

        /**
         * Using RestTemplate + application.properties
         */

        //Check whether the user is valid or not
        Map<String, String> userUriMap = new HashMap<>();
        userUriMap.put("id", String.valueOf(user.getUserId()));
        //String userAppUrl = "http://localhost:8080/user_app/v1/users/{id}";
        User receivedUser = restTemplate.getForObject(userAppUrl, User.class, userUriMap);
        if(receivedUser == null)
            return false;

        //Check whether the theatre and movie combination is available
        Map<String, String> theatreUriMap = new HashMap<>();
        theatreUriMap.put("theatreId", String.valueOf(theatre.getTheatreId()));
        theatreUriMap.put("movieId", String.valueOf(theatre.getMovieId()));
        //String theatreAppUrl = "http://localhost:8082/theatre_app/v1/theatres/{theatreId}/movie/{movieId}";
        Theatre receivedTheatre = restTemplate.getForObject(theatreAppUrl, Theatre.class, theatreUriMap);
        if(receivedTheatre==null)
            return false;

        return true;
    }
}
