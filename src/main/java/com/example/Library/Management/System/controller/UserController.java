package com.example.Library.Management.System.controller;

import com.example.Library.Management.System.entity.User;
import com.example.Library.Management.System.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/create_user")
    public String createUserForm(Model model, HttpSession session) {
        User loggedInuser = (User) session.getAttribute("loggedInUser");

        //if no user is login, send to login page
//        if (loggedInuser == null) {
//            return "redirect:/";
//        }

        User user = new User();
        model.addAttribute("user", user);
        return "create_user";
    }

//    @PostMapping("/create_user")
//    public String saveUser(@ModelAttribute("user") User user, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
//
//    }

    @GetMapping("/sign_in")
    public String signInForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "sign_in";
    }

    @PostMapping("/sign_in")
    public String getUser(@ModelAttribute("user") User user, Model model, HttpSession session) {
        User existingUser = userService.getUserByEmail(user.getEmail());

        if(existingUser == null) {
            System.out.println("user does not exist");
            model.addAttribute("errorMessage", "invalid email and password");
            return "sign_in";
        }

        String existingPassword = existingUser.getPassword();
        if (!user.getPassword().equalsIgnoreCase(existingPassword)) {
            System.out.println("Incorrect password");
            model.addAttribute("errorMessage", "invalid email and password");
            return "sign_in";
        }
        session.setAttribute("loggedInUser", existingUser);

        if (existingUser.getRole().equalsIgnoreCase("admin")) {
            return "redirect:/books";
        }
        return "redirect:/books";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/sign_in";
    }

    @GetMapping("/")
    public String home() {
        System.out.println("Home");
        return "/";
    }
}
