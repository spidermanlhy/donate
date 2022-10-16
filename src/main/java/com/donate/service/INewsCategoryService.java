package com.donate.service;

import com.donate.dto.reqdto.NewsReqDTO;
import com.donate.entity.NewsCategoryEntity;

import java.util.Map;

public interface INewsCategoryService {
  Map<String,Object> getNewsCategoryList(NewsReqDTO reqDTO);

  Map<String,Object> addNewsCategoty(NewsCategoryEntity newsCategoryEntity);

  Map<String,Object> updateNewsCategory(NewsCategoryEntity newsCategoryEntity);

  Map<String,Object> delNewCategory(int id);
}
