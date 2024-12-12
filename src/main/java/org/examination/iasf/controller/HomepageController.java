package org.examination.iasf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/12/12
 */

@Controller
@RequestMapping("/")
public class HomepageController {

    @GetMapping
    public String homepage() {
        return "homepage";
    }

}
