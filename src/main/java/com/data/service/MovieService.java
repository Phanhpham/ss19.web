package com.data.service;

import com.data.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovie();
    public List<Movie> getMoviesByPage(int pageNo, int pageSize);
    public long countTotalMovie();
    void save(Movie movie);
}
