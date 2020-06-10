package com.example.mongoDB;

import com.example.mongoDB.entities.Film;
import com.example.mongoDB.repositiory.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class FilmController {
    private final FilmRepository repository;

    @Autowired
    public FilmController(FilmRepository repository) {
        this.repository = repository;
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
        return "index";
    }


    @RequestMapping("/showFindByCategory")
    public String showFindByCategory(
            Model model) {
        List<Film> showFindByCategory = repository.findByCategory("Drama");
        model.addAttribute("showFindByCategory", showFindByCategory);
        return "showFindByCategory";
    }

    @RequestMapping("/showFindByCategory/{id}")
    public String deleteElement(
            @PathVariable(value = "id") String id,
            Model model) {
        Optional<Film> filmToDelete = repository.findById(id);
        filmToDelete.ifPresent(repository::delete);
        List<Film> showFindByCategory = repository.findByCategory("Drama");
        model.addAttribute("showFindByCategory", showFindByCategory);
        return "showFindByCategory";
    }

}
