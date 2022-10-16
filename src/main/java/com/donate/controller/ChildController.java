package com.donate.controller;

import com.donate.dto.reqdto.ChildCreateReqDTO;
import com.donate.dto.reqdto.ChildReqDTO;
import com.donate.service.IChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api-a/child/child-anon")
public class ChildController {

  @Autowired
  IChildService childService;

  @RequestMapping("/myChild")
  public Map<String,Object> myChild(ChildReqDTO reqDTO, HttpServletRequest request){
    reqDTO.setUserId((Integer) request.getSession().getAttribute("user_id"));
    return childService.getChildList(reqDTO,(Integer) request.getSession().getAttribute("user_id"));
  }

  @RequestMapping("/childList")
  public Map<String,Object> childList(@RequestBody ChildReqDTO reqDTO, HttpServletRequest request){
    Integer userId=0;
    if(request.getSession().getAttribute("user_id")!=null){
      userId= (Integer) request.getSession().getAttribute("user_id");
    }
    return childService.getChildList(reqDTO,userId);
  }

  @RequestMapping("/createChild")
  public Map<String,Object> createChild(@RequestBody ChildCreateReqDTO reqDTO, HttpServletRequest request){
    reqDTO.setUserId((Integer) request.getSession().getAttribute("user_id"));
    return childService.createChild(reqDTO);
  }

  @RequestMapping("/getChildById")
  public Map<String, Object> getChildById( Integer id){
    return childService.getChildById(id);
  }

  @RequestMapping("/updateChild")
  public Map<String,Object> updateChild(@RequestBody ChildCreateReqDTO reqDTO, HttpServletRequest request){
    reqDTO.setUserId((Integer) request.getSession().getAttribute("user_id"));
    return childService.updateChild(reqDTO);
  }

  @RequestMapping("/delChild")
  public Map<String,Object> delChild(int id){
    return childService.delChild(id);
  }

  @RequestMapping("/getChildList")
  public Map<String,Object> getChildList(ChildReqDTO reqDTO, HttpServletRequest request){
    Integer userId=0;
    if(request.getSession().getAttribute("user_id")!=null){
      userId= (Integer) request.getSession().getAttribute("user_id");
    }
    return childService.getChildList(reqDTO,userId);
  }

  @RequestMapping("/approve")
  public Map<String,Object> approve(int id,int status){
    return childService.approve(id,status);
  }
}
