package com.upgrad.movieapp.moviemicroservice.utils;

import com.upgrad.movieapp.moviemicroservice.dto.MovieDTO;
import com.upgrad.movieapp.moviemicroservice.dto.TheatreDTO;
import com.upgrad.movieapp.moviemicroservice.dto.UserDTO;
import com.upgrad.movieapp.moviemicroservice.entities.Movie;
import com.upgrad.movieapp.moviemicroservice.entities.Theatre;
import com.upgrad.movieapp.moviemicroservice.entities.User;


public class POJOConvertor {

    public static MovieDTO covertMovieEntityToDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setMovieId(movie.getMovieId());
        movieDTO.setDuration(movie.getDuration());
        movieDTO.setTrailerUrl(movie.getTrailerUrl());
        movieDTO.setCoverPhotoUrl(movie.getCoverPhotoUrl());
        movieDTO.setReleaseDate(movie.getReleaseDate());
        movieDTO.setMovieName(movie.getMovieName());
        movieDTO.setMovieDescription(movie.getMovieDescription());

        return movieDTO;
    }

    public static Movie covertMovieDTOToEntity(MovieDTO movieDTO) {
        Movie movie = new Movie();

        movie.setMovieId(movieDTO.getMovieId());
        movie.setDuration(movieDTO.getDuration());
        movie.setTrailerUrl(movieDTO.getTrailerUrl());
        movie.setCoverPhotoUrl(movieDTO.getCoverPhotoUrl());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setMovieName(movieDTO.getMovieName());
        movie.setMovieDescription(movieDTO.getMovieDescription());

        return movie;
    }

    public static UserDTO covertUserEntityToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUserName());
        userDTO.setUserId(user.getUserId());
        userDTO.setPassword(user.getPassword());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setPhoneNumbers(user.getPhoneNumbers());

        return userDTO;
    }

    public static User covertUserDTOToEntity(UserDTO userDTO) {
        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserName(userDTO.getUsername());
        user.setUserId(userDTO.getUserId());
        user.setPassword(userDTO.getPassword());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setPhoneNumbers(userDTO.getPhoneNumbers());

        return user;
    }

    public static TheatreDTO covertTheatreEntityToDTO(Theatre theatre) {
        TheatreDTO theatreDTO = new TheatreDTO();

        theatreDTO.setTheatreId(theatre.getTheatreId());
        theatreDTO.setMovieId(theatre.getMovieId());

        return theatreDTO;
    }

    public static Theatre covertTheatreDTOToEntity(TheatreDTO theatreDTO) {
        Theatre theatre = new Theatre();

        theatre.setTheatreId(theatreDTO.getTheatreId());
        theatre.setMovieId(theatreDTO.getMovieId());

        return theatre;
    }



}

