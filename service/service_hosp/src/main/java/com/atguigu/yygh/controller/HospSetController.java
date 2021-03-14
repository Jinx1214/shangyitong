package com.atguigu.yygh.controller;

import com.atguigu.yygh.mapper.HospMapper;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.service.HospService;
import com.atguigu.yygh.common.config.MD5;
import com.atguigu.yygh.vo.hosp.HospitalSetQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.crypto.interfaces.PBEKey;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Api(tags = "医院设置接口")
@RestController
@RequestMapping("/hospset")
public class HospSetController {

    @Autowired
    private HospService service;

    @ApiOperation("获取所有医院设置")
    @RequestMapping("/list")
    public Result getList(){
        List<HospitalSet> list = service.list();
        return Result.ok(list);
    }

    @ApiOperation("添加医院设置接口")
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet){
        //1可用 0不可用
        hospitalSet.setStatus(1);
        hospitalSet.setSignKey(MD5.encrypt(UUID.randomUUID().toString()+Long.toString(new Date().getTime())));
        boolean save = service.save(hospitalSet);
        return save?Result.ok():Result.fail();
    }

    @ApiOperation("删除医院设置接口")
    @DeleteMapping("deleteHospitalSet/{id}")
    public Result deleteHospitalSet(@PathVariable long id){
        boolean b = service.removeById(id);
        return b?Result.ok():Result.fail();
    }

    @ApiOperation("根据id查询医院设置接口")
    @GetMapping("getHospitalSet/{id}")
    public Result getHospital(@PathVariable long id){
        HospitalSet byId = service.getById(id);
        return Result.ok(byId);
    }

    @ApiOperation("更改医院设置接口")
    @PostMapping("updateHospitalSet")
    public Result updateHospital(@RequestBody HospitalSet hospitalSet){
        boolean b = service.updateById(hospitalSet);
        return b?Result.ok():Result.fail();
    }
    @ApiOperation("分页查询医院设置接口")
    @PostMapping("page/{current}/{limit}")
    public Result pageHospitalSet(@PathVariable("current")long current,
                                  @PathVariable("limit")long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo){
        //创建page对象，传递当前页，每页记录数
        Page<HospitalSet> page = new Page<>(current,limit);
        //构建条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        String hosname = hospitalSetQueryVo.getHosname();//医院名称
        String hoscode = hospitalSetQueryVo.getHoscode();//医院编号
        if(!StringUtils.isEmpty(hosname)) {
            wrapper.like("hosname",hospitalSetQueryVo.getHosname());
        }
        if(!StringUtils.isEmpty(hoscode)) {
            wrapper.eq("hoscode",hospitalSetQueryVo.getHoscode());
        }
        //调用方法实现分页查询
        Page<HospitalSet> pageHospitalSet = service.page(page, wrapper);
        //返回结果
        return Result.ok(pageHospitalSet);

    }

}
