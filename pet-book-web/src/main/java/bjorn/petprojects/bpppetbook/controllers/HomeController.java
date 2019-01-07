package bjorn.petprojects.bpppetbook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping({"","/","index","index.html"})
    public String getHome(){
        return "index";
    }
}
