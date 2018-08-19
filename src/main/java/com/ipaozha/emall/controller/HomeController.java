package com.ipaozha.emall.controller;

import com.ipaozha.emall.model.AbcModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
    @RequestMapping("/abc")
    public String hehe(HttpServletRequest request,Model model) {
        AbcModel abcModel = new AbcModel();
        abcModel.setA("大家好");
        model.addAttribute("abc",abcModel);
        return "abc";
    }

//    restful风格请求
    @RequestMapping(value = "/abc2/{abc}", method = RequestMethod.GET)
    public String hehe2(HttpServletRequest request, @PathVariable("abc") String path, Model model) {

        AbcModel abcModel = new AbcModel();
        abcModel.setA(path);
        model.addAttribute("abc",abcModel);
        return "abc";
    }
}
