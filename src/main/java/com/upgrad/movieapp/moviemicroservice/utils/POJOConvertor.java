package com.upgrad.movieapp.moviemicroservice.utils;

import com.upgrad.movieapp.moviemicroservice.dto.MovieDTO;
import com.upgrad.movieapp.moviemicroservice.entities.Movie;


public class POJOConvertor {

    public static MovieDTO covertMovieEntityToDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();

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

        movie.setDuration(movieDTO.getDuration());
        movie.setTrailerUrl(movieDTO.getTrailerUrl());
        movie.setCoverPhotoUrl(movieDTO.getCoverPhotoUrl());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setMovieName(movieDTO.getMovieName());
        movie.setMovieDescription(movieDTO.getMovieDescription());

        return movie;
    }
}

