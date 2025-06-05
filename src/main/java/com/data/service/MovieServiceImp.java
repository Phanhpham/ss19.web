package com.data.service;

import com.data.model.Movie;
import com.data.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImp implements MovieService{
    @Autowired
    private MovieRepo movieRepo;

    @Override
    public List<Movie> getAllMovie() {
        return movieRepo.getAllMovie();
    }

    @Override
    public long countTotalMovie() {
        return movieRepo.countTotalMovie();
    }

    @Override
    public List<Movie> getMoviesByPage(int pageNo, int pageSize) {
        return movieRepo.getMoviesByPage(pageNo,pageSize);
    }

    @Override
    public void save(Movie movie) {
        movieRepo.save(movie);
    }
}
