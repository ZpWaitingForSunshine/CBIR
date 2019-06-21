package com.njust.cbir.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/page")
@CrossOrigin
public class PageController {
    /**
     * 404 page
     */
    @RequestMapping("/404")
    public String error404() {
        return "404";
    }

    /**
     * 401 page
     */
    @RequestMapping("/401")
    public String error401() {
        return "401";
    }

    /**
     * 500 page
     */
    @RequestMapping("/500")
    public String error500() {
        return "500";
    }
}
