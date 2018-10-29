package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/index", "/index/loaded"})
    public String homePage(Model model) {
        return "index";
    }
}
