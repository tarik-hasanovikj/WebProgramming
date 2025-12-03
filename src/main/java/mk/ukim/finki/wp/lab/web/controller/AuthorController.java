package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.enums.Gender;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public String getAuthorsPage(
            @RequestParam(required = false) String error,
            Model model
    ) {
        if (error != null) {
            model.addAttribute("error", error);
        }

        List<Author> authors = authorService.findAll();

        model.addAttribute("authors", authors);
        return "listAuthors";
    }

    @GetMapping("/add-form")
    public String addAuthorPage(Model model) {
        model.addAttribute("genders", List.of(Gender.MALE, Gender.FEMALE));
        return "author-form";
    }

    @PostMapping
    public String saveAuthor(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String country,
            @RequestParam String biography,
            @RequestParam Gender gender

    ) {
        authorService.save(name, surname, country, biography, gender);
        return "redirect:/authors";
    }

    @GetMapping("/edit-form/{id}")
    public String editAuthorPage(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        model.addAttribute("genders", List.of(Gender.MALE, Gender.FEMALE));
        return "author-form";
    }

    @PostMapping("/{id}")
    public String updateAuthor(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String country,
            @RequestParam String biography,
            @RequestParam Gender gender
    ) {
        authorService.edit(id, name, surname, country, biography, gender);
        return "redirect:/authors";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return "redirect:/authors";
    }

}
