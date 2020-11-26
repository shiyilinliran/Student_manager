package com.syl.sm.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;

import java.io.File;
import java.util.UUID;

/**
 * @ClassName AlioSSUtil
 * @Description TODO
 * @Author admin
 * @Date 2020/11/23
 **/
public class AlioSSUtil {
    public static String ossUpload(File file) {
        String bucketDomain = "https://ranrandecangku.oss-cn-shanghai.aliyuncs.com/";
        String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
        String accessKeyId = "LTAI4G7KAVVorGbUqimsan3c";
        String accessKeySecret = "XZ46GWo6jfaU3XuaMJSAlxYrUQaYFh";
        String bucketName="ranrandecangku";
        String fileDir="test/image/";
        String fileName=file.getName();
        String fileKey= UUID.randomUUID().toString()+fileName.substring(fileName.indexOf("."));
        OSS ossClient=new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        ossClient.putObject(bucketName,fileDir+fileKey,file);
        ossClient.shutdown();
        return bucketDomain+fileDir+fileKey;
    }

    public static void main(String[] args) {
        File file=new File("D:/DownLoad/Gugo_Downloads/st_jisuanji1.jpg");
        String url=ossUpload(file);
        System.out.println(url);
    }
}
