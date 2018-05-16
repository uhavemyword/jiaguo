package com.cp.jiaguo.webapi.controller;

import com.cp.jiaguo.webapi.model.DashboardModel;
import com.cp.jiaguo.webapi.service.ProductService;
import com.cp.jiaguo.webapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping("/demo")
    public DashboardModel getDemo() {
        DashboardModel model = new DashboardModel();
        model.setProductCount(productService.getCount());
        model.setUesrCount(userService.getCount());
        return model;
    }
}
