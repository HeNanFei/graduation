package com.zjt.graduation.serverfile.controller;

import com.zjt.graduation.common.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags = "SellGoodApi")
@RequestMapping("/sellgood/good")
public interface SellGoodApi {

    @ApiOperation(value = "importGoods")
    @PostMapping("/importGoods")
    CommonResult importGoods(@RequestParam MultipartFile file) throws IOException;

    @ApiOperation(value = "exportGoods")
    @GetMapping("/exportGoods")
    void exportGoods(HttpServletResponse response) throws IOException;

    @ApiOperation(value = "createGoods")
    @GetMapping("/createGoods")
    CommonResult createGoods(HttpServletResponse response) throws IOException;


    @ApiOperation(value = "purchaseGoods")
    @GetMapping("/purchaseGoods")
    CommonResult purchaseGoods(HttpServletResponse response,Long id) throws IOException;


}
