package com.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên phim không được để trống")
    private String title;

    @NotBlank(message = "Đạo diễn không được để trống")
    private String director;

    @NotNull(message = "Năm phát hành không được để trống")
    @Min(value = 1888, message = "Năm phát hành phải hợp lệ")
    private Integer releaseYear;

    @NotNull(message = "The loai phim không được để trống")
    private String genre;

    @NotNull(message = "Thời lượng phim không được để trống")
    @Min(value = 1, message = "Thời lượng phim phải lớn hơn 0 phút")
    private Integer duration;
    @NotNull(message = "Ngon ngu phim không được để trống")
    private String language;

    @URL(message = "URL ảnh áp phích không hợp lệ")
    private String poster;

    private boolean status;

}
