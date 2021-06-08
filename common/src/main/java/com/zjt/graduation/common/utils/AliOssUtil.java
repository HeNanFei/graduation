package com.zjt.graduation.common.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ZipUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import com.zjt.graduation.common.config.oss.AliOssConfig;
import com.zjt.graduation.common.entity.FileRecord;
import com.zjt.graduation.common.service.FileRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@Component
public class AliOssUtil {

    @Autowired
    private OSS ossClient;

    @Autowired
    private AliOssConfig aliOssConfig;

    @Autowired
    private FileRecordService fileRecordService;

    public PutObjectResult singleUpload(String directory,String fileName,String path) {
        try{
            String fileNameResult = directory+"/"+fileName;
            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = new FileInputStream(path);
            // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
            PutObjectResult putObjectResult = ossClient.putObject(aliOssConfig.getBucketname(), fileNameResult, inputStream);
            // 关闭OSSClient。
            //ossClient.shutdown();
            return putObjectResult;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public String singleUpload(String grade,String description, String directory,MultipartFile file) {
        FileRecord fileRecord = new FileRecord();
        try{
            String fileNameResult = null;
            String fileTypeName = FileUtil.extName(file.getOriginalFilename());
            if("png".equals(fileTypeName)){
                fileNameResult = FileUtil.getName(file.getOriginalFilename())+"_"+ UUID.randomUUID();
            }else{
                fileNameResult = directory+"/"+file.getOriginalFilename();
            }
            PutObjectResult putObjectResult = ossClient.putObject(aliOssConfig.getBucketname(), fileNameResult,file.getInputStream());
            //ossClient.shutdown();
            URL url = ossClient.generatePresignedUrl("winterfell", directory+"/"+file.getOriginalFilename(), new Date(2082, 12, 21));

            //设置文件记录
            fileRecord.setContentType(file.getContentType());
            fileRecord.setIsImg(1);
            fileRecord.setName(file.getOriginalFilename());
            fileRecord.setPath("don't");
            fileRecord.setSize(file.getSize());
            fileRecord.setUrl(url.toString());
            fileRecord.setGrade(grade);
            fileRecord.setDescription(description);

            fileRecordService.save(fileRecord);

            return url.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public PutObjectResult createDirectory(String directoryName){
        String keySuffixWithSlash =  directoryName +"/";
        PutObjectResult putObjectResult = ossClient.putObject(aliOssConfig.getBucketname(), keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
        //ossClient.shutdown();
        return putObjectResult;
    }

    public ObjectListing listAllFile(String bucketName){
        ObjectListing objectListing = ossClient.listObjects(bucketName);
        //ossClient.shutdown();
        return objectListing;
    }


    public void downLoadFileWithStream(String key) throws IOException {


        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(aliOssConfig.getBucketname(), key);

        // 读取文件内容。
        System.out.println("Object content:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            System.out.println("\n" + line);
        }

        // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        reader.close();
    }


    public ListObjectsV2Result listDirectoryFiles(String bucketName,String directoryName){
        if(StringUtils.isEmpty(directoryName) || !StringUtils.endsWithIgnoreCase(directoryName,"/")){
            throw new RuntimeException("please check the direcotryName");
        }
        ListObjectsV2Result listObjectsV2Result = ossClient.listObjectsV2(bucketName, directoryName);
        //ossClient.shutdown();
        return listObjectsV2Result;
    }

    public void deleteFile(String bucketName,String fileDirectory){

        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, fileDirectory);
        //ossClient.shutdown();
    }

    public void downLoadFile(HttpServletResponse response, String objectName) throws IOException {
        //"2022/3/11/8.冒泡排序(Av415255592,P8).mp4"
        OSSObject ossObject = ossClient.getObject(aliOssConfig.getBucketname(),objectName);
        InputStream objectContent = ossObject.getObjectContent();
        byte[] bytes = FileCopyUtils.copyToByteArray(objectContent);
        response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(ossObject.getKey(),"UTF-8"));//文件名如果包含中文需要指定编码
        response.getOutputStream().write(bytes);
    }

    public void downLoadFile(String objectName) throws IOException {
        //"2022/3/11/8.冒泡排序(Av415255592,P8).mp4"
        OSSObject ossObject = ossClient.getObject(aliOssConfig.getBucketname(),objectName);
        InputStream objectContent = ossObject.getObjectContent();
        byte[] bytes = FileCopyUtils.copyToByteArray(objectContent);
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\winter\\Desktop\\test");
        fileOutputStream.write(bytes);
        //ossClient.shutdown();
    }

    public void zipDownLoad(HttpServletResponse response,String bucketName) throws Exception{
        ObjectListing allFiles = listAllFile(bucketName);
        List<OSSObjectSummary> objectSummaries = allFiles.getObjectSummaries();
        for (int i = 0; i < objectSummaries.size(); i++) {
            OSSObjectSummary ossObjectSummary = objectSummaries.get(i);
            try{
                String endpoint = aliOssConfig.getEndpoint();
                // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
                String accessKeyId = aliOssConfig.getBucket();
                String accessKeySecret = aliOssConfig.getSecret();

                // 创建OSSClient实例。
                OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
                OSSObject ossObject = ossClient.getObject(aliOssConfig.getBucketname(), ossObjectSummary.getKey());
                byte[] bytes = FileCopyUtils.copyToByteArray(ossObject.getObjectContent());
                String replace = ossObjectSummary.getKey().replace("/", "-");
                File file = new File("D:\\excel\\"+replace);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(bytes);
                fileOutputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        File file = new File("D:\\excel");
        File zip = ZipUtil.zip(file);
        byte[] bytes1 = FileCopyUtils.copyToByteArray(zip);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\home\\test.zip"));
        fileOutputStream.write(bytes1);
        fileOutputStream.close();
    }



}
