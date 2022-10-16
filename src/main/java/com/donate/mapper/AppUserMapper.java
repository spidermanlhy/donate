package com.donate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;;
import org.apache.ibatis.annotations.Mapper;;
import com.donate.entity.AppUserEntity;
@Mapper
public interface AppUserMapper extends BaseMapper<AppUserEntity> {
}