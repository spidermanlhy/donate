package com.donate.controller;

import com.donate.dto.reqdto.BulletinReqDTO;
import com.donate.entity.DtBulletinEntity;
import com.donate.service.IBulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api-a/bulletin/bulletin-anon")
public class DtBulletinController {

  @Autowired
  IBulletinService bulletinService;

  @RequestMapping("/home")
  public Map<String,Object> getHomeBullet( BulletinReqDTO bulletinReqDTO){
    bulletinReqDTO.setIsShow(0);
    return bulletinService.getBulletInList(bulletinReqDTO);
  }

  @RequestMapping("/list")
  public Map<String,Object> list(@RequestBody BulletinReqDTO bulletinReqDTO){
    return bulletinService.getBulletInList(bulletinReqDTO);
  }

  @RequestMapping("/addBulletInList")
  public Map<String,Object> addBulletInList(@RequestBody DtBulletinEntity bulletinEntity, HttpServletRequest request){
    Integer userId=0;
    if(request.getSession().getAttribute("user_id")!=null){
      userId= (Integer) request.getSession().getAttribute("user_id");
    }
    bulletinEntity.setCreatedUserId(userId);
    return bulletinService.addBulletInList(bulletinEntity);
  }

  @RequestMapping("/delBulletIn")
  public Map<String,Object> delBulletIn(int id){
    return bulletinService.delBulletIn(id);
  }

  @RequestMapping("/changeBulletIn")
  public Map<String,Object> changeBulletIn(int id,int status){
    return bulletinService.changeBulletIn(id,status);
  }
}
