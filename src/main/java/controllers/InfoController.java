package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/about")
public class InfoController {

    @GetMapping
    public ModelAndView getInfoPage() {
        return new ModelAndView("aboutUs");
    }
}
