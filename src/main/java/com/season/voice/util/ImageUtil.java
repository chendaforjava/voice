package com.season.voice.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageUtil {

    public static InputStream getImageStream(String imagePath){
        URL url;
        HttpURLConnection conn;
        InputStream inputStream;
        try {
            url = new URL(imagePath);
            //创建http链接
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            //设置超时间为3秒
            conn.setConnectTimeout(3*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            inputStream = conn.getInputStream();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return inputStream ;
    }


    public static byte[] getImgOriginalBytes(String imageUrl) {
        byte[] data = null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            //获取大小
            inputStream = getImageStream(imageUrl);
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            // 循环写入数据到字节数组
            while ((length = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            byteArrayOutputStream.flush();
            data = byteArrayOutputStream.toByteArray();

            return data;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     *
     * @param imageUrl
     * @param desWidth
     * @param desSize
     * @return
     */
    public static byte[] getImgInfo(String imageUrl, int desWidth,long desSize)  {
        BufferedImage image;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            //获取大小
            byte[] data = getImgOriginalBytes(imageUrl);
            long size  = data.length;

            image = ImageIO.read(new ByteArrayInputStream(data));
            int width = image.getWidth();
            double factor =0;
            if(width > desWidth ){
                factor = (double) desWidth/width;
            }
            inputStream = new ByteArrayInputStream(data);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
            if(factor !=0){
                Thumbnails.of(inputStream).scale(factor).toOutputStream(outputStream);
                data = outputStream.toByteArray();
            }
            if(size > desSize){
                data = compressPictureForScale(data,desSize);
            }
            return  data;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(byteArrayOutputStream!=null){
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static byte[] getImgInfo(byte[] data, int desWidth, long desSize) {
        BufferedImage image;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            //获取大小
            long size = data.length;
            image = ImageIO.read(new ByteArrayInputStream(data));
            int width = image.getWidth();
            double factor = 0;
            if (width > desWidth) {
                factor = (double) desWidth / width;
            }
            inputStream = new ByteArrayInputStream(data);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
            if (factor != 0) {
                Thumbnails.of(inputStream).scale(factor).toOutputStream(outputStream);
                data = outputStream.toByteArray();
            }
            if (size > desSize) {
                data = compressPictureForScale(data, desSize);
            }
            return data;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static byte[] getImageInfoByFile(MultipartFile file, int desWidth, long desSize) {
        BufferedImage image;
        ByteArrayInputStream byteArrayInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            image = ImageIO.read(file.getInputStream());

            //获取大小
            int width = image.getWidth();
            byte[] data = file.getBytes();
            long size  = data.length;

            // 把字节输入转换成为字节输入流
            byteArrayInputStream = new ByteArrayInputStream(data);

            double factor =0;
            if (width > desWidth) {
                factor = (double) desWidth/width;
            }
            byteArrayOutputStream = new ByteArrayOutputStream(data.length);
            if (factor != 0) {
                Thumbnails.of(byteArrayInputStream).scale(factor).toOutputStream(byteArrayOutputStream);
                data = byteArrayOutputStream.toByteArray();
            }
            if (size > desSize) {
                data = compressPictureForScale(data,desSize);
            }
            return  data;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static byte[] compressPictureForScale(byte[] imageBytes, long desFileSize) {
        if (imageBytes == null || imageBytes.length == 0 || imageBytes.length < desFileSize) {
            return imageBytes;
        }
        long srcSize = imageBytes.length;
        double accuracy = getAccuracy(srcSize);
        try {
            while (imageBytes.length > desFileSize ) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);
                Thumbnails.of(inputStream).scale(1).outputQuality(accuracy).toOutputStream(outputStream);
                imageBytes = outputStream.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageBytes;
    }

    private static double getAccuracy(long size) {
        double accuracy;
        if (size < 900) {
            accuracy = 0.85;
        } else if (size < 2047) {
            accuracy = 0.6;
        } else if (size < 3275) {
            accuracy = 0.44;
        } else {
            accuracy = 0.4;
        }
        return accuracy;
    }
}
