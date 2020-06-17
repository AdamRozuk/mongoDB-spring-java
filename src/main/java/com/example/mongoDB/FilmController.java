package com.example.mongoDB;

import com.example.mongoDB.entities.Film;
import com.example.mongoDB.entities.User;
import com.example.mongoDB.repositiory.FilmRepository;
import com.example.mongoDB.repositiory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class FilmController {
    private final FilmRepository repository;
    private final UserRepository userRepository;
    private String userId;


    @Autowired
    public FilmController(FilmRepository repository,
                          UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
//        this.userId = userId;
    }

    @RequestMapping(value = "/home")
    public String index(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "category", required = false) String category
    ) {
        if (title != null && year != null && category != null) {
            repository.insert(new Film(title, year, category));
        }
        return "addFilm";
    }
    @RequestMapping(value = "/login")
    public String login() {
        return "index";
    }

    @RequestMapping(value = "/home/{email}")
    public String AddUser(
            @PathVariable(value = "email", required = false) String email, Model model
    ) {
        User user = new User(email);
        userRepository.insert(user);
        userId= user.getId();
        return "addFilm";
    }

    @RequestMapping("/findFilms")
    public String showFindByCategory(
            Model model) {
        List<Film> findFilms = repository.findAll();
        model.addAttribute("findFilms", findFilms);
        return "findFilms";
    }

    @RequestMapping("/deleteFilm/{id}")
    public RedirectView deleteElement(
            @PathVariable(value = "id") String id,
            Model model) {
        Optional<Film> filmToDelete = repository.findById(id);
        filmToDelete.ifPresent(repository::delete);
        List<Film> findFilms = repository.findAll();
        model.addAttribute("findFilms", findFilms);
        return new RedirectView("/findFilms");
    }

    @RequestMapping("/findFilms/category")
    public String findCategory(
            @RequestParam(value = "category", required = false) String category,
            Model model) {
        List<Film> findFilms = repository.findByCategory(category);
        model.addAttribute("findFilms", findFilms);
        return "findFilms";

    }
    @RequestMapping("/findFilms/title")
    public String findTitle(
            @RequestParam(value = "title", required = false) String title,
            Model model) {
        List<Film> findFilms = repository.findByTitle(title);
        model.addAttribute("findFilms", findFilms);
        return "findFilms";
    }

    @RequestMapping("/updateFilm/{id}")
    public String updateFilm( @PathVariable(value = "id") String id,
                              @RequestParam(value = "title", required = false) String title,
                              @RequestParam(value = "year", required = false) Integer year,
                              @RequestParam(value = "category", required = false) String category,
                              Model model) {

        model.addAttribute("filmId",id);
        Optional<Film> filmToUpdate = repository.findById(id);
        filmToUpdate.ifPresent(film -> model.addAttribute("film", film));
        if (title != null || year != null || category != null) {
            if (filmToUpdate.isPresent()) {
                filmToUpdate.get().setTitle(title);
                filmToUpdate.get().setYear(year);
                filmToUpdate.get().setCategory(category);
            }
            filmToUpdate.ifPresent(repository::save);
        }
        return "updateFilm";
    }

    @RequestMapping("/likeFilm/{id}")
    public RedirectView likeFilm(@PathVariable(value = "id") String id, Model model) {
        Optional<Film> filmToUpdate = repository.findById(id);
        if(userId != null) {
            Optional<User> currentUser = userRepository.findById(userId);
            int a = 0;
            if (currentUser.isPresent()) {
                for (String val : currentUser.get().getLikedFilmsId()
                ) {
                    if (val.equals(id)) {
                        a = 1;

                    }
                }
            }
            if (a == 0) {
                currentUser.ifPresent(user -> {
                    user.getLikedFilmsId().add(id);
                    currentUser.ifPresent(userRepository::save);
                    filmToUpdate.ifPresent(film -> film.setLikes(film.getLikes() + 1));
                    filmToUpdate.ifPresent(repository::save);
                });
            }
        }

        return new RedirectView("/findFilms");
    }
}
