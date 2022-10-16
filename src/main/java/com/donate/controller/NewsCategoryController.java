package com.donate.controller;


import com.donate.dto.reqdto.NewsReqDTO;
import com.donate.entity.NewsCategoryEntity;
import com.donate.service.INewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/newscategory")
@RestController
public class NewsCategoryController {
  @Autowired
  INewsCategoryService newsCategoryService;

  @RequestMapping("/getNewsCategoryList")
  Map<String,Object> getNewsCategoryList(NewsReqDTO reqDTO){
    return newsCategoryService.getNewsCategoryList(reqDTO);
  }

  @RequestMapping("/addNewsCategoty")
  Map<String,Object> addNewsCategoty(NewsCategoryEntity newsCategoryEntity){
    return newsCategoryService.addNewsCategoty(newsCategoryEntity);
  }

  @RequestMapping("/updateNewsCategory")
  Map<String,Object> updateNewsCategory(NewsCategoryEntity newsCategoryEntity){
    return newsCategoryService.updateNewsCategory(newsCategoryEntity);
  }

  @RequestMapping("/delNewCategory")
  Map<String,Object> delNewCategory(int id){
    return newsCategoryService.delNewCategory(id);
  }
}
