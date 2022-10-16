package com.donate.service;

import com.donate.dto.reqdto.BulletinReqDTO;
import com.donate.entity.DtBulletinEntity;

import java.util.Map;

public interface IBulletinService {
  /**
   * 获取通知公告列表
   * @param reqDTO
   * @return
   */
  public Map<String,Object> getBulletInList(BulletinReqDTO reqDTO);

  public Map<String,Object> addBulletInList(DtBulletinEntity bulletinEntity);

  public Map<String,Object> delBulletIn(int id);

  public Map<String,Object> changeBulletIn(int id,int status);
}
