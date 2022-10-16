package com.donate.service;

import com.donate.dto.reqdto.RecodeReqDTO;
import com.donate.entity.DtRecordEntity;

import java.util.Map;

public interface IDonateService {
  /**
   * 获取捐献记录
   * @param reqDTO
   * @return
   */
  public Map<String,Object> getRecordList(RecodeReqDTO reqDTO);

  public Map<String,Object> donate(DtRecordEntity recordEntity);
}
