package com.donate.service;

import com.donate.dto.reqdto.ApplyReqDTO;
import com.donate.entity.DtAppealEntity;
import com.donate.entity.DtApplyEntity;

import java.util.Map;

public interface IApplyService {
  /**
   *
   * @param reqDTO
   * @return
   */
  public Map<String,Object> getApplyDetail(ApplyReqDTO reqDTO);

  /**
   * 获取轮播图数据
   * @param reqDTO
   * @return
   */
  public Map<String, Object> getApplyLunbo(ApplyReqDTO reqDTO);

  public Map<String,Object> addApply(DtApplyEntity dtApplyEntity);

  public Map<String,Object> updateApply(DtApplyEntity dtApplyEntity);

  public Map<String,Object> getApplyById(int id);

  public Map<String,Object> delApply(int id);

  public Map<String,Object> approveApply(int id,int status);

  public Map<String,Object> componentApply(int id,int status);


  public Map<String,Object> getApplyInfo(int id,Integer userId);

}
