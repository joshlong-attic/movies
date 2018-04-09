package com.example.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@SpringBootApplication
public class MoviesApplication {

		public static void main(String[] args) {
				SpringApplication.run(MoviesApplication.class, args);
		}
}

@Controller
@RequestMapping("/movies")
class MovieController {

		private MovieService movieService;

		MovieController(MovieService movieService) {
				this.movieService = movieService;
		}

		@GetMapping("/all.html")
		String getAllMovies(Model model) {
				model.addAttribute("movies", this.movieService.getAllMovies());
				return "movies";
		}
}

@Service
class MovieService {

		private JdbcTemplate jdbcTemplate;

		private RowMapper<Movie> movieRowMapper = new RowMapper<Movie>() {

				@Override
				public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
						return new Movie(resultSet.getString("id"), resultSet.getString("director"),
							resultSet.getString("title"), resultSet.getInt("year"));
				}
		};

		MovieService(JdbcTemplate jdbcTemplate) {
				this.jdbcTemplate = jdbcTemplate;
		}

		public Collection<Movie> getAllMovies() {
				return this.jdbcTemplate.query("select * from movies", this.movieRowMapper);
		}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Movie {
		private String id, director, title;
		private int year;
}