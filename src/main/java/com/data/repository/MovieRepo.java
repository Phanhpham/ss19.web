package com.data.repository;

import com.data.model.Movie;
import com.data.model.User;

import java.util.List;

public interface MovieRepo {
    List<Movie> getAllMovie();
    public List<Movie> getMoviesByPage(int pageNo, int pageSize);
    public long countTotalMovie();
    void save(Movie movie);
}
