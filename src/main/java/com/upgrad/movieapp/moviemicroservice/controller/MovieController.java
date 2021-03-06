package com.upgrad.movieapp.moviemicroservice.controller;


import com.upgrad.movieapp.moviemicroservice.dto.MovieBookingDTO;
import com.upgrad.movieapp.moviemicroservice.dto.MovieDTO;
import com.upgrad.movieapp.moviemicroservice.entities.Movie;
import com.upgrad.movieapp.moviemicroservice.entities.Theatre;
import com.upgrad.movieapp.moviemicroservice.entities.User;
import com.upgrad.movieapp.moviemicroservice.services.MovieService;
import com.upgrad.movieapp.moviemicroservice.utils.POJOConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/movie_app/v1")
public class MovieController {

    @Autowired
    private MovieService movieService ;

    /**
     * Method for creating movies
     * 127.0.0.1:8080/movie_app/v1/movies
     */

    /**
     * POSTCALL ENDPOINT: localhost:8585/movie_app/v1/movies
     * {
     * 	"movieId":1,
     * 	"coverPhotoUrl": "COVER PHOTO URL",
     * 	"duration": 120,
     * 	"movieDescription":"The Final installment of most awaited movie",
     * 	"movieName":"Avengers: The END GAME",
     * 	"releaseDate": "2019-04-26T05:30:00",
     * 	"trailerUrl": "TRAILER URL"
     * }
     * movieId can be avoided while passing post request body
     * @param movieDTO
     * @return
     */

    @PostMapping(value="/movies", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createMovie(@RequestBody MovieDTO movieDTO){

        //convert movieDTO to MovieEntity

        Movie newMovie = POJOConvertor.covertMovieDTOToEntity(movieDTO);
        Movie savedMovie = movieService.acceptMovieDetails(newMovie);

        MovieDTO savedMovieDto = POJOConvertor.covertMovieEntityToDTO(savedMovie);

        return new ResponseEntity(savedMovieDto, HttpStatus.CREATED);

    }

    /**
     * GETCALL ENDPOINT: localhost:8585/movie_app/v1/movies
     * @return
     */

    @GetMapping(value = "/movies" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllMovies(){
        List<Movie> movieList = movieService.getAllMovies();
        List<MovieDTO> movieDTOList = new ArrayList<>();

        for(Movie movie : movieList){
            movieDTOList.add(POJOConvertor.covertMovieEntityToDTO(movie));
        }
        return new ResponseEntity(movieDTOList, HttpStatus.OK);
    }

    /**
     * GETCALL ENDPOINT: localhost:8585/movie_app/v1/movies/id
     * @param id
     * @return
     */

    @GetMapping(value="/movies/{id}")
    public ResponseEntity getMovieBasedOnId(@PathVariable(name="id") int id){
        Movie responseMovie = movieService.getMovieDetails(id);

        MovieDTO responseMovieDTO = POJOConvertor.covertMovieEntityToDTO(responseMovie);

        return new ResponseEntity(responseMovieDTO, HttpStatus.OK);
    }

    /**
     * PUTCALL ENDPOINT: localhost:8585/movie_app/v1/movies/id
     * @param id
     * @param movieDTO
     * @return
     */

    @PutMapping( value= "movies/{id}" ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateMovieDetails(@PathVariable(name ="id") int id,
                                             @RequestBody MovieDTO movieDTO){

        Movie newMovie = POJOConvertor.covertMovieDTOToEntity(movieDTO);
        Movie udpatedMovie = movieService.updateMovieDetails(id, newMovie);

        MovieDTO updatedMovieDTO = POJOConvertor.covertMovieEntityToDTO(udpatedMovie);

        return new ResponseEntity(updatedMovieDTO, HttpStatus.OK) ;
    }


    /**
     * ENDPOINT: http://localhost:8585/movie_app/v1/bookings/movie
     * {
     *     "movie":{
     *         "movieId":1
     *     },
     *     "user":{
     *         "userId":1,
     *         "firstName":"Jigar"
     *     },
     *     "theatre":{
     *         "theatreId":1,
     *         "movieId":1
     *     }
     * }
     * @param movieBookingDTO
     * @return
     */

    @PostMapping(value = "/bookings/movie", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity bookMovieDetails(@RequestBody MovieBookingDTO movieBookingDTO) {

        Movie requestedMovie = POJOConvertor.covertMovieDTOToEntity(movieBookingDTO.getMovie());
        User fromUser = POJOConvertor.covertUserDTOToEntity(movieBookingDTO.getUser());
        Theatre requestedTheatre = POJOConvertor.covertTheatreDTOToEntity(movieBookingDTO.getTheatre());

        boolean isValidBooking = movieService.bookMovie(fromUser, requestedMovie, requestedTheatre);

        if(!isValidBooking)
            return new ResponseEntity("Mr/Mrs. "+fromUser.getFirstName()+
                    " Your Ticket for movieId "+requestedMovie.getMovieId()+
                    " in theatreId "+requestedTheatre.getTheatreId()+
                    " Not Booked !! Please Try Again!!", HttpStatus.OK);

        return new ResponseEntity("Mr/Mrs. "+fromUser.getFirstName()+
                " Your Ticket for movieId "+requestedMovie.getMovieId()+
                " in theatreId "+requestedTheatre.getTheatreId()+
                " Booked Successfully !!", HttpStatus.OK);
    }


}
