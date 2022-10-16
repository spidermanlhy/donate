package com.donate.service;

import com.donate.dto.reqdto.AppUserReqDTO;
import com.donate.dto.reqdto.LoginReqDTO;
import com.donate.entity.AppUserEntity;

import java.util.Map;

public interface IUserinfoService {
  /**
   * 登录接口
   * @param loginReqDTO
   * @return
   */
  public Map<String,Object> login(LoginReqDTO loginReqDTO);

  /**
   * 根据用户ID获取用户详细信息
   * @param userId
   * @return
   */
  public Map<String,Object> getUserinfoByUserid(Integer userId) throws IllegalAccessException;

  /**
   * 注册
   * @param appUserEntity
   * @return
   */
  public Map<String,Object> register(AppUserEntity appUserEntity);

  public Map<String,Object> getUserList(AppUserReqDTO reqDTO);

  public Map<String,Object> changeUserState(int id,int status);

  public Map<String,Object> changePassword(int id,String password);
}
