package com.donate.controller;

import com.donate.dto.reqdto.NewsReqDTO;
import com.donate.entity.NewsEntity;
import com.donate.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/news")
public class NewsController {

  @Autowired
  INewsService newsService;

  @RequestMapping("/getNewsList")
  Map<String,Object> getNewsList(@RequestBody NewsReqDTO reqDTO){
    return newsService.getNewsList(reqDTO);
  }

  @RequestMapping("/addNews")
  Map<String,Object> addNews(@RequestBody NewsEntity newsEntity, HttpServletRequest request){
    Integer userId=0;
    if(request.getSession().getAttribute("user_id")!=null){
      userId= (Integer) request.getSession().getAttribute("user_id");
    }
    newsEntity.setAddUserId(userId);
    return newsService.addNews(newsEntity);
  }

  @RequestMapping("/delNews")
  Map<String,Object> delNews(int id){
    return newsService.delNews(id);
  }
}
