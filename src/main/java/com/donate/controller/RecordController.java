package com.donate.controller;

import com.donate.dto.reqdto.RecodeReqDTO;
import com.donate.entity.DtApplyUsedEntity;
import com.donate.entity.DtRecordEntity;
import com.donate.service.ApplyUsedService;
import com.donate.service.IDonateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api-p/record/apply-anon")
public class RecordController {

  @Autowired
  IDonateService donateService;

  @Autowired
  ApplyUsedService applyUsedService;

  @RequestMapping("/latestDontate")
  public Map<String,Object> latestDontate(RecodeReqDTO reqDTO){
    return donateService.getRecordList(reqDTO);
  }

  @RequestMapping("/donate")
  public Map<String, Object> donate(@RequestBody DtRecordEntity recordEntity, HttpServletRequest request){
    recordEntity.setCreatedUserId((Integer) request.getSession().getAttribute("user_id"));
    return donateService.donate(recordEntity);
  }

  @RequestMapping("/addUsedRecord")
  public Map<String, Object> addUsedRecoed(@RequestBody DtApplyUsedEntity applyUsedEntity, HttpServletRequest request){
    applyUsedEntity.setAddUserId((Integer) request.getSession().getAttribute("user_id"));
    return applyUsedService.addUsedRecoed(applyUsedEntity);
  }
}
