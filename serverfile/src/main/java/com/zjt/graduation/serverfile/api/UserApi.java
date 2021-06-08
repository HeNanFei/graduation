package com.zjt.graduation.serverfile.api;

import com.zjt.graduation.common.response.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags = "UserFileApi")
@RequestMapping("/fileservice/user")
public interface UserApi {
    @ApiOperation(value = "导出用户")
    @GetMapping("/exportUser")
    void exportAll(HttpServletResponse response) throws IOException;


    @ApiOperation(value = "导入用户")
    @PostMapping("/importUser")
    void importUser(@RequestParam MultipartFile file) throws IOException;

    @ApiOperation(value = "导出商品")
    @GetMapping("/exportGoods")
    void exportGoods(HttpServletResponse response) throws IOException;

    @ApiOperation(value = "导入商品")
    @PostMapping("/importGoods")
    public CommonResult importTGood(MultipartFile file) throws IOException, InterruptedException;

    @ApiOperation(value = "下载")
    @GetMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response,@RequestParam  String objectName) throws IOException, InterruptedException;

    @ApiOperation(value = "上传")
    @GetMapping("/uploadFile")
    public void uploadFile(String directory,String fileName,String path) throws IOException, InterruptedException;


    @ApiOperation(value = "上传文件")
    @PostMapping("/uploadFileInter")
    public String uploadFile(String grade,String description,@RequestParam String directory,@RequestParam  MultipartFile file);


    @ApiOperation(value = "打包下载文件")
    @GetMapping("/zipDownLoadFiles")
    public void zipDownLoadFile(HttpServletResponse response,String bucketName) throws Exception;


    @ApiOperation(value = "ListFiles")
    @GetMapping("/listAllFiles")
    @ResponseBody
    public CommonResult listAllFiles(HttpServletResponse response,@RequestParam String bucketName) throws Exception;

    @ApiOperation(value = "deleteFile")
    @GetMapping("/deleteFile")
    public CommonResult deleteFile(String bucketName,String fileDirectory) throws Exception;


    @ApiOperation(value = "payMethod")
    @GetMapping("/payMethod")
    public CommonResult payMethod(String bucketName,String fileDirectory) throws Exception;


    @ApiOperation(value = "importClass")
    @PostMapping("/importClass")
    @ResponseBody
    public CommonResult importClass(@RequestParam MultipartFile file) throws Exception;


    @ApiOperation(value = "importStudent")
    @PostMapping("/importStudent")
    @ResponseBody
    public CommonResult importStudent(@RequestParam MultipartFile file) throws Exception;

    @ApiOperation(value = "exportClass")
    @GetMapping("/exportClass")
    public void exportClass(HttpServletResponse response) throws Exception;


    @ApiOperation(value = "downloaModel")
    @GetMapping("/downloaModel")
    public void downloaModel(HttpServletResponse response,@RequestParam  String type) throws Exception;


    @ApiOperation(value = "importTeacher")
    @PostMapping("/importTeacher")
    @ResponseBody
    public CommonResult importTeacher(@RequestParam MultipartFile file) throws Exception;


}
