package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/setmeal")
@Api(tags = "套餐管理接口")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealService setmealService;


//    public Result update(@RequestBody SetmealDTO setmealDTO){
//        log.info("编辑套餐信息：{}",setmealDTO);
//        setmealService.update(setmealDTO);
//        return Result.success();
//    }
    @PostMapping
    @ApiOperation("新增套餐")
    public Result saveWithDish(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐信息：{}",setmealDTO);
        setmealService.saveWithDish(setmealDTO);
        return Result.success();
    }
    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("分页查询套餐信息：{}",setmealPageQueryDTO);
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }



    }


