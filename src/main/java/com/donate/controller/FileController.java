package com.donate.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/file")
@RestController
public class FileController {

  @Value("${savePath}")
  private String savePath;

  @RequestMapping("uploadPicture")
  @ResponseBody
  public Map<String,Object> uploadPicture(HttpServletRequest request,HttpServletResponse httpServletResponse) throws IOException {
    MultipartRequest multipartRequest=(MultipartRequest) request;
    MultipartFile file=multipartRequest.getFile("file");
    String fileName= UUID.randomUUID().toString().replace("-","")+".jpg";
    file.transferTo(new File(savePath+"/"+fileName));
    Map<String,Object> result=new HashMap<>();
    result.put("path","http://localhost:8081/file/getImage?path="+savePath+"/"+fileName);
    result.put("name",fileName.replace(".",""));
    result.put("code","ACK");
    result.put("data","111");


    httpServletResponse.setHeader("Access-Control-Allow-Headers",
      "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, Access-Control-Allow-Methods, Access-Control-Allow-Origin");
    return  result;
  }

  @RequestMapping(value = "/getImage",produces = MediaType.IMAGE_JPEG_VALUE)
  @ResponseBody
  public byte[] getImage(String path, HttpServletResponse response) throws IOException {
    File file = new File(path);
    FileInputStream inputStream = new FileInputStream(file);
    byte[] bytes = new byte[inputStream.available()];
    inputStream.read(bytes, 0, inputStream.available());
    return bytes;
  }
}
