package com.taotao.service.impl;

import com.taotao.common.pojo.PictureResult;
import com.taotao.service.PictureService;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utils.FastDFSClient;

@Service
public class PictureServiceImpl implements PictureService {

    @Value("${IMAGE_SERVER_BASE_URL}")
    private String IMAGE_SERVER_BASE_URL;

    @Override
    public PictureResult uploadPic(MultipartFile picFile) {
        PictureResult pictureResult = new PictureResult();
        if(picFile.isEmpty())
        {
            pictureResult.setError(1);
            pictureResult.setMessage("图片为空！");
        }
        try {
            //取图片扩展名
            String originalFileName = picFile.getOriginalFilename();
            String extName = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
            System.out.println(extName);
            FastDFSClient client = new FastDFSClient("classpath:properties/client.conf");
            String[] strings = client.uploadFile(picFile.getBytes(), extName);
            String url = "";
            for (String string:strings) {
                url = url+string+"/";
            }
            url = url.substring(0,url.length()-1);
            url = IMAGE_SERVER_BASE_URL + url;
            System.out.println(url);
            //把url响应给客户端
            pictureResult.setError(0);
            pictureResult.setUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
            pictureResult.setError(1);
            pictureResult.setMessage("图片上传失败！");
        }

        return pictureResult;
    }
}
