package com.donate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.donate.entity.NewsEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper extends BaseMapper<NewsEntity> {
}
