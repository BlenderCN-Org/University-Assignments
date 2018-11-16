package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberOrderController {

    @RequestMapping(value = {"/order", "/order/loaded"})
    public String checkoutPage(Model model) {
        return "orders";
    }

}
