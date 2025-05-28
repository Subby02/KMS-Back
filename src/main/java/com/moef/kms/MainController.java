package com.moef.kms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/education/search")
    public String educationSearch() {
        return "educationSearch";
    }

    @GetMapping("/education/enrol")
    public String educationEnrol() {
        return "educationEnrol";
    }

}
