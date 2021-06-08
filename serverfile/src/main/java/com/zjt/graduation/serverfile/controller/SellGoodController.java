package com.zjt.graduation.serverfile.controller;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjt.graduation.common.controller.AbstractController;
import com.zjt.graduation.common.entity.SellGood;
import com.zjt.graduation.common.entity.SysUserInfo;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.common.service.SellGoodService;
import com.zjt.graduation.common.service.SysUserInfoService;
import com.zjt.graduation.serverfile.entity.ErrorInfo;
import com.zjt.graduation.serverfile.entity.SellGoodExcel;
import com.zjt.graduation.serverfile.listener.SellGoodsListenner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SellGoodController extends AbstractController implements SellGoodApi{

    @Autowired
    private SellGoodService sellGoodService;

    @Autowired
    private SysUserInfoService sysUserInfoService;

    @Override
    public CommonResult importGoods(MultipartFile file) throws IOException {
        List<SellGood> sellGoodList = new ArrayList<>();
        List<ErrorInfo> errorInfos = new ArrayList<>();
        EasyExcel.read(file.getInputStream(), SellGoodExcel.class,new SellGoodsListenner(sellGoodList,errorInfos,new ArrayList<SellGoodExcel>(),SellGood.class)).sheet(0).headRowNumber(1).doRead();

        return CommonResult.failed(JSONUtil.toJsonStr(errorInfos));
    }

    @Override
    public void exportGoods(HttpServletResponse response) throws IOException {
        List<SellGood> list = sellGoodService.list();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("商品列表", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), SellGoodExcel.class).sheet().doWrite(list);
    }

    @Override
    public CommonResult createGoods(HttpServletResponse response) throws IOException {
        return null;
    }

    @Override
    public CommonResult purchaseGoods(HttpServletResponse response,Long id) throws IOException {
        //String userName = getUserName();
        SysUserInfo sysuser = sysUserInfoService.getOne(new QueryWrapper<SysUserInfo>().eq("username", "Messi"));
        String purchase = sellGoodService.purchase(1370360023993053185L, 1, sysuser.getId());
        return CommonResult.success(purchase);
    }
}
