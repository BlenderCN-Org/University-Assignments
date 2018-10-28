package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountCreationController {

    @RequestMapping("/account-creation")
    public String accountCreationPage(Model model) {
        return "account-creation";
    }

}


