package com.stonedt.intelligence.controller;

import com.stonedt.intelligence.service.YuQingService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/yuqing")
public class YuQingController {
    @Autowired
    private YuQingService yuQingService;

    @GetMapping(value = "/findInfo")
    @ResponseBody
    public List<Document> find(){
        return yuQingService.find();
    }
}
