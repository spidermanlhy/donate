package com.donate.controller;

import com.donate.dto.reqdto.ApplyReqDTO;
import com.donate.entity.DtApplyEntity;
import com.donate.service.IApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("/api-a/appeal/apply-anon")
@RestController
public class ApplyController {
  @Autowired
  IApplyService applyService;

  @RequestMapping("/pageDetail/{page}")
  public Map<String,Object> pageDetail(Integer page) {
    if(page==null||page<=0){
      page=1;
    }
    ApplyReqDTO applyReqDTO=new ApplyReqDTO();
    applyReqDTO.setPage(page);
    applyReqDTO.setSize(6);
    applyReqDTO.setApplyState(1);
    applyReqDTO.setApplyType(0);
    applyReqDTO.setCompleted(1);
    return applyService.getApplyLunbo(applyReqDTO);
  }

  @RequestMapping("/person")
  public Map<String,Object> person(ApplyReqDTO dto) {
    dto.setApplyState(1);
    dto.setApplyType(1);
    dto.setCompleted(1);
    return applyService.getApplyDetail(dto);
  }

  @RequestMapping("/public")
  public Map<String,Object> publicList(ApplyReqDTO dto) {
    dto.setApplyState(1);
    dto.setApplyType(0);
    dto.setCompleted(1);
    return applyService.getApplyDetail(dto);
  }

  @RequestMapping("/list")
  public Map<String,Object> list(@RequestBody ApplyReqDTO dto) {
    return applyService.getApplyDetail(dto);
  }

  @RequestMapping("/mylist")
  public Map<String,Object> mylist(@RequestBody ApplyReqDTO dto, HttpServletRequest request) {
    dto.setUserId((Integer) request.getSession().getAttribute("user_id"));
    return applyService.getApplyDetail(dto);
  }

  @RequestMapping("/addApply")
  public Map<String,Object> addApply(@RequestBody DtApplyEntity dtApplyEntity, HttpServletRequest request){
    dtApplyEntity.setUserId((Integer) request.getSession().getAttribute("user_id"));
    return applyService.addApply(dtApplyEntity);
  }

  @RequestMapping("/updateApply")
  public Map<String,Object> updateApply(@RequestBody DtApplyEntity dtApplyEntity, HttpServletRequest request){
    dtApplyEntity.setUserId((Integer) request.getSession().getAttribute("user_id"));
    return applyService.updateApply(dtApplyEntity);
  }

  @RequestMapping("/getApplyById")
  public Map<String,Object> getApplyById(int id){
    return applyService.getApplyById(id);
  }

  @RequestMapping("/delApply")
  public Map<String,Object> delApply(int id){
    return applyService.delApply(id);
  }

  @RequestMapping("/approveApply")
  public Map<String,Object> approveApply(int id,int status){
    return applyService.approveApply(id,status);
  }

  @RequestMapping("/getApplyInfo")
  public Map<String,Object> getApplyInfo(int id,HttpServletRequest request){
    Integer userId=0;
    if(request.getSession().getAttribute("user_id")!=null){
      userId= (Integer) request.getSession().getAttribute("user_id");
    }
    return applyService.getApplyInfo(id,userId);
  }

  @RequestMapping("/componentApply")
  public Map<String,Object> componentApply(int id,int status){
    return applyService.componentApply(id,status);
  }


}
