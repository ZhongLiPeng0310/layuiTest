package com.lbxtest.layui.controller;


import com.lbxtest.layui.Service.ProductService;
import com.lbxtest.layui.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductContoller {

    @Autowired
    private ProductService productService;

    @RequestMapping("/list")
    public DataVO list(Integer page,Integer limit){
        return productService.findData(page,limit);
    }
}
