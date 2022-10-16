package com.donate.controller;

import com.donate.dto.reqdto.AppUserReqDTO;
import com.donate.entity.AppUserEntity;
import com.donate.service.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api-u/users")
@RestController
public class UserinfoController {
  @Autowired
  IUserinfoService userinfoService;

  @RequestMapping("/current")
  public Map<String,Object> getCurrUser(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException {
    if(request.getSession().getAttribute("user_id")==null){
      response.setStatus(401);
      Map<String,Object> result=new HashMap<>();
      result.put("code",401);
      result.put("error","unauthorized");
      result.put("error_description","未登录");
      return result;
    }else{
      return userinfoService.getUserinfoByUserid((Integer) request.getSession().getAttribute("user_id"));
    }
  }

  @RequestMapping("/register")
  public Map<String, Object> register(@RequestBody AppUserEntity appUserEntity){
    return userinfoService.register(appUserEntity);
  }

  @RequestMapping("/find")
  public Map<String, Object> getUserList(@RequestBody AppUserReqDTO reqDTO){
    return userinfoService.getUserList(reqDTO);
  }

  @RequestMapping("/changeUserState")
  public Map<String, Object> changeUserState(int id, int status){
    return userinfoService.changeUserState(id,status);
  }

  @RequestMapping("/changePassword")
  public Map<String,Object> changePassword(String password,HttpServletRequest request){
    Integer userId=0;
    if(request.getSession().getAttribute("user_id")!=null){
      userId= (Integer) request.getSession().getAttribute("user_id");
    }
    return userinfoService.changePassword(userId,password);
  }
}
