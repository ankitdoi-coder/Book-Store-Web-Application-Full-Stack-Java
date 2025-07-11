package com.bookstore.bookstore.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // returns login.html
    }
}
