package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {

    @RequestMapping(value = {"/cart", "/cart/loaded"})
    public String homePage(Model model) {
        return "cart";
    }
}
