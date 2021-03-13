package com.atguigu.yygh.service.Impl;

import com.atguigu.yygh.mapper.HospMapper;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.atguigu.yygh.service.HospService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class HospServiceImpl extends ServiceImpl<HospMapper,HospitalSet> implements HospService {
}
