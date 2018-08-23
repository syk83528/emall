package com.ipaozha.emall.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by syk on 2018/8/24.
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @RequestMapping
    public String init() {
        return "/system/index";
    }

}
