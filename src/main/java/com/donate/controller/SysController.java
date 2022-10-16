package com.donate.controller;

import com.donate.dto.reqdto.LoginReqDTO;
import com.donate.service.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/sys")
public class SysController {

  @Autowired
  IUserinfoService userinfoService;

  @RequestMapping("/login")
  public Map<String,Object> login(@Valid LoginReqDTO loginReqDTO, HttpServletRequest request, HttpServletResponse response){
    Map<String,Object> result=userinfoService.login(loginReqDTO);
    if(result.get("code").toString().equals("401")){
      response.setStatus(401);
    }else{
      request.getSession().setAttribute("user_id",result.get("user_id"));
    }
    return result;
  }
}
