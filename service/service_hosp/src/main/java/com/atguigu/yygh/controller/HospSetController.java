package com.atguigu.yygh.controller;

import com.atguigu.yygh.mapper.HospMapper;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.atguigu.yygh.service.HospService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "医院设置接口")
@RestController
@RequestMapping("/hospset")
public class HospSetController {

    @Autowired
    private HospService service;

    @ApiOperation(value = "获取所有医院设置")
    @RequestMapping("/list")
    public List<HospitalSet> getList(){

        return service.list();
    }

}
