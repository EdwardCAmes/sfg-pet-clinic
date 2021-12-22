package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/vets")
@Controller
public class VetController {
    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listVets() {
        return "vets/index";
    }
}
/*
@Controller
public class VetController {
    @RequestMapping({"/vets", "vets/index", "vets/index.html"})
    public String listVets() {
        return "vets/index";
    }
}
*/