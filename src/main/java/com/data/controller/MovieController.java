package com.data.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.data.model.Movie;
import com.data.model.User;
import com.data.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/pageMovie")
    public String getAllMovie(
            Model model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {

        List<Movie> movies = movieService.getMoviesByPage(page, size);
        long totalMovies = movieService.countTotalMovie();
        int totalPages = (int) Math.ceil((double) totalMovies / size);

        model.addAttribute("movie", movies);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "movie";
    }

    @GetMapping("/addMovie")
    public String showAddForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "addMovie";
    }

     //Xử lý form thêm phim
    @PostMapping("/add")
    public String addMovie(@Valid @ModelAttribute("movie") Movie movie,
                           BindingResult result,
                           @RequestParam("image") MultipartFile file,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        if (result.hasErrors()) {
            return "addMovie";
        }

        try {
            if (!file.isEmpty()) {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                String imageUrl = (String) uploadResult.get("secure_url");
                movie.setPoster(imageUrl);
            }
            movieService.save(movie);
            redirectAttributes.addFlashAttribute("success", "Thêm phim thành công!");
            return "redirect:/pageMovie";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi upload ảnh hoặc lưu phim.");
            return "addMovie";
        }
    }
}