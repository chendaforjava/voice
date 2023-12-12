package com.season.voice.service;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 *  阿里云OSS服务调用
 *  @author caofei
 *
 */
public interface AliyunOssService {

    /**
     *通过网络流上传文件
     * @param url 			URL
     * @param bucketName 	bucket名称
     * @param objectName 	上传文件目录和（包括文件名）例如“test/index.html”
     * @return String 		返回类型
     */
    public String uploadByNetworkStream(URL url, String bucketName, String objectName);

    /**
     * 通过输入流上传文件
     * @param inputStream 	输入流
     * @param bucketName 	bucket名称
     * @param objectName 	上传文件目录和（包括文件名） 例如“test/a.jpg”
     * @return String 		返回类型
     */
    public String uploadByInputStream(InputStream inputStream, String bucketName, String objectName);

    /**
     * 通过file上传文件
     * @param file 			上传的文件
     * @param bucketName 	bucket名称
     * @param objectName 	上传文件目录和（包括文件名） 例如“test/a.jpg”
     * @return String 		返回类型
     */
    public String uploadByFile(File file, String bucketName, String objectName);

    /**
     * 根据key删除oss服务器上的文件
     * @param bucketName		bucket名称
     * @param key    		文件路径/名称，例如“test/a.txt”
     */
    public void deleteFile(String bucketName, String key);

    /**
     * 根据key获取服务器上的文件的输入流
     * @param bucketName 	bucket名称
     * @param key 			文件路径和名称
     * @return InputStream 	文件输入流
     */
    public InputStream getInputStreamByOSS(String bucketName, String key);

    /**
     * 根据key获取服务器上的文件的输入流
     * @param bucketName 	bucket名称
     * @param url 			文件下载地址
     * @return InputStream 	文件输入流
     */
    InputStream getInputStreamByUrl(String bucketName, String url);

    /**
     * 查询某个bucket里面的所有文件
     * @param bucketName		bucket名称
     * @return List 文件路径和大小集合
     */
    public List<String> queryAllObject(String bucketName);

    /**
     * 获取下载路径
     * @param objectName
     * @return
     */
    public String getDownloadFileUrlOss(String objectName);

    void checkBucket(String bucketName);
}
