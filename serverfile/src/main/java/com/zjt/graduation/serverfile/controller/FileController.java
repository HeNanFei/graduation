package com.zjt.graduation.serverfile.controller;

import com.alibaba.excel.EasyExcel;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjt.graduation.common.annota.AvoidSubmit;
import com.zjt.graduation.common.controller.AbstractController;
import com.zjt.graduation.common.entity.SysUserInfo;
import com.zjt.graduation.common.entity.SystemClass;
import com.zjt.graduation.common.entity.TGood;
import com.zjt.graduation.common.entity.TGoods;
import com.zjt.graduation.common.excelentity.SystemClassExcel;
import com.zjt.graduation.common.excelentity.SystemStudentExcel;
import com.zjt.graduation.common.excelentity.SystemTeacherExcel;
import com.zjt.graduation.common.mapper.TGoodsMapper;
import com.zjt.graduation.common.response.CommonResult;
import com.zjt.graduation.common.security.utils.JwtTokenUtil;
import com.zjt.graduation.common.service.SysUserInfoService;
import com.zjt.graduation.common.service.SystemClassService;
import com.zjt.graduation.common.service.TGoodService;
import com.zjt.graduation.common.utils.AliOssUtil;
import com.zjt.graduation.serverfile.api.UserApi;
import com.zjt.graduation.serverfile.excellisten.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

@Controller
public class FileController extends AbstractController implements UserApi {
    @Autowired
    private SysUserInfoService sysUserInfoService;

    @Autowired
    private TGoodService tGoodService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TGoodsMapper tGoodsMapper;

    @Autowired
    private ThreadPoolExecutor taskExecutor;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AliOssUtil aliOssUtil;

    @Autowired
    private OSSClient ossClient;


    @Autowired
    private SystemClassService systemClassService;

    @Override
    public void exportAll(HttpServletResponse response) throws IOException {

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        List<SysUserInfo> list =  sysUserInfoService.list();
        SysUserInfo source = list.get(0);
        for (int i = 0; i < 100000; i++) {
            SysUserInfo sysUserInfo = new SysUserInfo();
            BeanUtils.copyProperties(source,sysUserInfo);
            sysUserInfo.setId(null);
            sysUserInfo.setUsername(source.getUsername()+i);
            list.add(sysUserInfo);
        }
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), SysUserInfo.class).sheet().doWrite(list);
    }

    @Override
    public void importUser(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), SysUserInfo.class,new SysUserInfoListener()).sheet(0).headRowNumber(1).doRead();
    }

    @ResponseBody
    @Override
    public CommonResult importTGood(MultipartFile file) throws IOException, InterruptedException {
        String redisRecordKey = putFileDataToRedis(redisTemplate, getUserName());
        Map<String,List<String>> checkData = new HashMap<>();
        checkData.put("platform_goods_code",tGoodsMapper.getColumn(new QueryWrapper()));
        checkData.put("goods_code",tGoodsMapper.getGoodsCode(new QueryWrapper()));
        checkData.put("brand_code",tGoodsMapper.getBrandCode(new QueryWrapper()));
        checkData.put("category_code",tGoodsMapper.getCategoryCode(new QueryWrapper()));
        checkData.put("category_name",tGoodsMapper.getCategoryName(new QueryWrapper()));
        checkData.put("name",tGoodsMapper.getName(new QueryWrapper()));
        checkData.put("second_category_code",tGoodsMapper.getSecondCategoryCode(new QueryWrapper()));
        checkData.put("second_category_name",tGoodsMapper.getSecondCategoryName(new QueryWrapper()));
        checkData.put("three_category_code",tGoodsMapper.getThreeCategoryCode(new QueryWrapper()));
        checkData.put("three_category_name",tGoodsMapper.getThreeCategoryName(new QueryWrapper()));
        checkData.put("brand_name",tGoodsMapper.getBrandName(new QueryWrapper()));
        checkData.put("title",tGoodsMapper.getTitle(new QueryWrapper()));
        File file1 = new File("C:\\Users\\winter\\Desktop\\测试 (8).xlsx");
        //File file1 = new File("C:\\Users\\winter\\Desktop\\测试91.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file1);
        //EasyExcel.read(file.getInputStream(), TGood.class,new TGoodListener(tGoodService,taskExecutor,checkData,applicationContext,redisRecordKey)).sheet(0).headRowNumber(1).doRead();
        EasyExcel.read(fileInputStream, TGood.class,new TGoodListener(tGoodService,taskExecutor,checkData,applicationContext,redisRecordKey)).sheet(0).headRowNumber(1).doRead();
        return CommonResult.success("success");
    }

    @Override
    public void downloadFile(HttpServletResponse response,String objectName) throws IOException, InterruptedException {
        //"2022/03/11/8.冒泡排序(Av415255592,P8).mp4"
        aliOssUtil.downLoadFile(response,objectName);
        //aliOssUtil.downLoadFileWithStream(objectName);
    }

    @Override
    public void uploadFile(String directory,String fileName,String path) throws IOException, InterruptedException {
        aliOssUtil.singleUpload(directory,fileName,path);
    }

    @ResponseBody
    @Override
    public String uploadFile(String grade,String description, String directory,MultipartFile file) {
        return aliOssUtil.singleUpload( grade, description, directory, file);
    }

    @Override
    public void zipDownLoadFile(HttpServletResponse response,String bucketName) throws Exception {
      aliOssUtil.zipDownLoad(response,bucketName);
    }

    @Override
    public CommonResult listAllFiles(HttpServletResponse response, String bucketName) throws Exception {
        ObjectListing objectListing = aliOssUtil.listAllFile(bucketName);
        List<OSSObjectSummary> objectSummaries = objectListing.getObjectSummaries();
        Page page = new Page<>();
        page.setRecords(objectSummaries);
        page.setTotal(Optional.ofNullable(objectSummaries.size()).orElse(0));

        return CommonResult.success(objectSummaries);
    }

    @Override
    public CommonResult deleteFile(String bucketName, String fileDirectory) throws Exception {
        aliOssUtil.deleteFile(bucketName,fileDirectory);
        return CommonResult.success("delete success");
    }

    @Override
    public CommonResult payMethod(String bucketName, String fileDirectory) throws Exception {
        return null;
    }

    @Override
    public CommonResult importClass(MultipartFile file) throws Exception {
        EasyExcel.read(file.getInputStream(), SystemClassExcel.class,new SystemClassListner()).sheet(0).headRowNumber(1).doRead();
        return CommonResult.success("success");
    }

    @AvoidSubmit(seconds = 60)
    @Override
    public CommonResult importStudent(MultipartFile file) throws Exception {
        EasyExcel.read(file.getInputStream(), SystemStudentExcel.class,new SystemStudentListener()).sheet(0).headRowNumber(1).doRead();
        return CommonResult.success("success");
    }

    @Override
    public void exportClass(HttpServletResponse response) throws Exception {
        List<SystemClassExcel> systemClassExcels = new ArrayList<>();
        List<SystemClass> list = systemClassService.list();
        if(!CollectionUtils.isEmpty(list)){
            for (int i = 0; i < list.size(); i++) {
                SystemClassExcel systemClassExcel = new SystemClassExcel();
                BeanUtils.copyProperties(list.get(i),systemClassExcel);
                systemClassExcels.add(systemClassExcel);
            }
        }
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("Classname", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), SystemClassExcel.class).sheet().doWrite(systemClassExcels);
    }

    @Override
    public void downloaModel(HttpServletResponse response,String type) throws Exception {
        String excelName = null;
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        if("Class".equals(type)){
            excelName = "classInfor";
            String fileName = URLEncoder.encode(excelName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            List<SystemClassExcel> list = new ArrayList<>();
            list.add(new SystemClassExcel());
            EasyExcel.write(response.getOutputStream(), SystemClassExcel.class).sheet().doWrite(list);
        }
        if("Teacher".equals(type)){
            excelName = "teacherinfo";
            String fileName = URLEncoder.encode(excelName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            List<SystemTeacherExcel> list = new ArrayList<>();
            list.add(new SystemTeacherExcel());
            EasyExcel.write(response.getOutputStream(), SystemTeacherExcel.class).sheet().doWrite(list);
        }

        if("Student".equals(type)){
            excelName = "studentinfor";
            String fileName = URLEncoder.encode(excelName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            List<SystemStudentExcel> list = new ArrayList<>();
            list.add(new SystemStudentExcel());
            EasyExcel.write(response.getOutputStream(), SystemStudentExcel.class).sheet().doWrite(list);
        }
    }

    @Override
    public CommonResult importTeacher(MultipartFile file) throws Exception {
        EasyExcel.read(file.getInputStream(), SystemTeacherExcel.class,new SystemTeacherExcelLisner()).sheet(0).headRowNumber(1).doRead();
        return CommonResult.success("success");
    }

    //@PreAuthorize("hasAnyRole('ROLE_KEN')")
    @Override
    public void exportGoods(HttpServletResponse response) throws IOException {
        List<TGoods> tGoods = tGoodsMapper.selectList(new QueryWrapper<>());
        tGoods.parallelStream().forEach(n -> n.setId(null));
        List<TGoods> broadData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            broadData.addAll(tGoods);
        }
        List<TGood> tGoodsList = new ArrayList<>(50000);
        broadData.parallelStream().forEach(n -> {
            TGood tGood = new TGood();
            BeanUtils.copyProperties(n,tGood);
            tGoodsList.add(tGood);
        });

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), TGood.class).sheet().doWrite(tGoodsList);
    }


    public static String putFileDataToRedis(RedisTemplate redisTemplate,String userName){
        try{
            String keyAndId = userName+LocalDateTime.now();
            HashMap<String,String> redisMap = new HashMap<>();
            redisMap.put("id",keyAndId);
            redisMap.put("status","analysying---");
            redisMap.put("importTime",String.valueOf(LocalDateTime.now()));
            redisMap.put("user",userName);
            redisMap.put("costTime","analysying- not completed!");
            redisMap.put("message","process is running,be patient!");
            redisTemplate.opsForHash().put("importRecord",keyAndId,redisMap);
            return keyAndId;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
