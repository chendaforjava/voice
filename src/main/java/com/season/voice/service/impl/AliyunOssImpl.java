package com.season.voice.service.impl;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.StringUtils;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.season.voice.config.OssProperties;
import com.season.voice.service.AliyunOssService;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class AliyunOssImpl implements AliyunOssService {

    private final OssProperties ossProperties;

    /**
     * 获取oss客户端
     *
     * @return OSSClient oss客户端
     */
    private OSS getOSSClient() {
        if (StringUtils.isNullOrEmpty(ossProperties.getEndpoint())
                || StringUtils.isNullOrEmpty(ossProperties.getAccessKeyId())
                || StringUtils.isNullOrEmpty(ossProperties.getAccessKeySecret())
                || StringUtils.isNullOrEmpty(ossProperties.getDownloadFileUrl())
                || StringUtils.isNullOrEmpty(ossProperties.getSystemName())) {
        /*    log.error("连接参数为空！"+Endpoint+";"+AccessKeyId+";"+AccessKeySecret+";"+
                    downloadFileUrl+";"+systemName);*/
            throw new NullPointerException("连接参数为空！");
        }
        OSS ossClient = new OSSClientBuilder().build(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
        return ossClient;
    }

    /**
     * 获取下载路径
     *
     * @param objectName 文件名称
     * @return: 路径
     */
    @Override
    public String getDownloadFileUrlOss(String objectName) {
        return getDownloadFileUrl(objectName);
    }

    /**
     * 获取下载路径
     *
     * @param objectName 文件名称
     * @return: 路径
     */
    public String getDownloadFileUrl(String objectName) {
        String url = "%s/%s/%s";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date now = new Date();
        String date = sdf.format(now);
        return String.format(url, ossProperties.getDownloadFileUrl(), ossProperties.getSystemName(), objectName);
    }

    /**
     * 获取上传路径
     *
     * @param objectName 文件名称
     * @return: 路径
     */
    private String GetUploadFileUrl(String objectName) {
        String url = "%s/%s";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date now = new Date();
        String date = sdf.format(now);
        return String.format(url, ossProperties.getSystemName(), objectName);
    }

    /**
     * 通过网络流上传文件
     *
     * @param url        URL
     * @param bucketName bucket名称
     * @param objectName 上传文件目录和（包括文件名）例如“test/index.html”
     * @return String         返回类型
     */
    @Override
    public String uploadByNetworkStream(URL url, String bucketName, String objectName) {
        OSS ossClient = null;
        try {
            ossClient = getOSSClient();
            String uploadObjectName = GetUploadFileUrl(objectName);
            InputStream inputStream = url.openStream();
            ossClient.putObject(bucketName, uploadObjectName, inputStream);
            ossClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return getDownloadFileUrl(objectName);
    }

    /**
     * 通过输入流上传文件
     *
     * @param inputStream 输入流
     * @param bucketName  bucket名称
     * @param objectName  上传文件目录和（包括文件名） 例如“test/a.jpg”
     * @return String         返回类型
     */
    @Override
    public String uploadByInputStream(InputStream inputStream, String bucketName,
                                      String objectName) {
        OSS ossClient = null;
        try {
            ossClient = getOSSClient();
            String uploadObjectName = GetUploadFileUrl(objectName);
            ossClient.putObject(bucketName, uploadObjectName, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return getDownloadFileUrl(objectName);
    }

    /**
     * 通过file上传文件
     *
     * @param file       上传的文件
     * @param bucketName bucket名称
     * @param objectName 上传文件目录和（包括文件名） 例如“test/a.jpg”
     * @return String         返回类型
     */
    @Override
    public String uploadByFile(File file, String bucketName, String objectName) {
        OSS ossClient = null;
        try {
            ossClient = getOSSClient();
            String uploadObjectName = GetUploadFileUrl(objectName);
            ossClient.putObject(bucketName, uploadObjectName, file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return getDownloadFileUrl(objectName);
    }


    /**
     * 根据key删除oss服务器上的文件
     *
     * @param bucketName bucket名称
     * @param key        文件路径/名称，例如“test/a.txt”
     */
    @Override
    public void deleteFile(String bucketName, String key) {
        OSS ossClient = null;
        try {
            ossClient = getOSSClient();
            String uploadObjectName = GetUploadFileUrl(key);
            ossClient.deleteObject(bucketName, uploadObjectName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    /**
     * 根据key获取服务器上的文件的输入流
     *
     * @param bucketName bucket名称
     * @param key        文件路径和名称
     * @return InputStream     文件输入流
     */
    @Override
    public InputStream getInputStreamByOSS(String bucketName, String key) {
        OSS ossClient = null;
        InputStream content = null;
        try {
            ossClient = getOSSClient();
            String uploadObjectName = GetUploadFileUrl(key);
            OSSObject ossObj = ossClient.getObject(bucketName, uploadObjectName);
            content = ossObj.getObjectContent();
        } catch (Exception e) {
          e.printStackTrace();
        }
        return content;
    }

    /**
     * 根据key获取服务器上的文件的输入流
     *
     * @param bucketName bucket名称
     * @param url        文件下载地址
     * @return InputStream    文件输入流
     */
    @Override
    public InputStream getInputStreamByUrl(String bucketName, String url) {
        OSS ossClient = null;
        InputStream content = null;
        try {
            ossClient = getOSSClient();
            GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, url, HttpMethod.GET);
            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
            // 设置过期时间。
            request.setExpiration(expiration);
            // 生成签名URL（HTTP GET请求）。
            URL signedUrl = ossClient.generatePresignedUrl(request);
            // 使用签名URL发送请求。
            OSSObject ossObject = ossClient.getObject(signedUrl, new HashMap<>());
            if (ossObject != null) {
                content = ossObject.getObjectContent();
            }
        } catch (Exception e) {
          e.printStackTrace();
        }
        return content;
    }

    /**
     * 查询某个bucket里面的所有文件
     *
     * @param bucketName bucket名称
     * @return List   文件路径和大小集合
     */
    @Override
    public List<String> queryAllObject(String bucketName) {
        OSS ossClient = null;
        List<String> results = new ArrayList<String>();
        try {
            ossClient = getOSSClient();
            // ossClient.listObjects返回ObjectListing实例，包含此次listObject请求的返回结果。
            ObjectListing objectListing = ossClient.listObjects(bucketName);
            // objectListing.getObjectSummaries获取所有文件的描述信息。
            for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                results.add(" - " + objectSummary.getKey() + "  " + "(size = " + objectSummary.getSize() + ")");
            }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return results;
    }

    @Override
    public void checkBucket(String bucketName) {
        OSS ossClient = getOSSClient();
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
        }
    }
}
