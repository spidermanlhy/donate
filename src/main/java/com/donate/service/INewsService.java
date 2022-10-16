package com.donate.service;

import com.donate.dto.reqdto.NewsReqDTO;
import com.donate.entity.NewsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

public interface INewsService {

  Map<String,Object> getNewsList(NewsReqDTO reqDTO);

  Map<String,Object> addNews(NewsEntity newsEntity);

  Map<String,Object> delNews(int id);
}
