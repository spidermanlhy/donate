package com.donate.service;

import com.donate.dto.reqdto.ChildCreateReqDTO;
import com.donate.dto.reqdto.ChildReqDTO;

import java.util.Map;

public interface IChildService {
  /**
   * 查询儿童列表
   * @param reqDTO
   * @return
   */
  public Map<String,Object> getChildList(ChildReqDTO reqDTO,Integer userId);

  /**
   * 创建儿童档案
   * @param reqDTO
   * @return
   */
  public Map<String,Object> createChild(ChildCreateReqDTO reqDTO);

  /**
   * 根据ID获取儿童信息
   * @param id
   * @return
   */
  public Map<String,Object> getChildById(int id);

  public Map<String,Object> updateChild(ChildCreateReqDTO reqDTO);

  public Map<String,Object> delChild(int id);

  public Map<String,Object> approve(int id,int status);
}
