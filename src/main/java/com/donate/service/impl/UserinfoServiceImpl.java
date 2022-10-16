package com.donate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.donate.dto.reqdto.AppUserReqDTO;
import com.donate.dto.reqdto.LoginReqDTO;
import com.donate.dto.respdto.BulletinRespDTO;
import com.donate.entity.*;
import com.donate.mapper.*;
import com.donate.service.IUserinfoService;
import com.donate.util.MD5Util;
import com.donate.util.MapUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class UserinfoServiceImpl implements IUserinfoService {
  @Autowired
  AppUserMapper appUserMapper;

  @Autowired
  SysRoleMapper sysRoleMapper;

  @Autowired
  SysRoleUserMapper sysRoleUserMapper;

  @Autowired
  SysRolePermissionMapper sysRolePermissionMapper;

  @Autowired
  SysPermissionMapper sysPermissionMapper;


  @Override
  public Map<String, Object> login(LoginReqDTO loginReqDTO) {
    Map<String,Object> result=new HashMap<>();
    //查询用户是否存在
    QueryWrapper<AppUserEntity> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("username",loginReqDTO.getUsername());
    AppUserEntity appUserEntity=appUserMapper.selectOne(queryWrapper);
    if(appUserEntity==null){
      result.put("code",401);
      result.put("error","unauthorized");
      result.put("error_description","用户不存在");
      return result;
    }
    if(!MD5Util.getMD5String(loginReqDTO.getPassword()).equals(appUserEntity.getPassword())){
      result.put("code",401);
      result.put("error","unauthorized");
      result.put("error_description","密码错误");
      return result;
    }
    result.put("code","ACK");
    Map<String,Object> data=new HashMap<>();
    data.put("access_token", UUID.randomUUID());
    data.put("expires_in",150000);
    data.put("jti",UUID.randomUUID());
    data.put("refresh_token",UUID.randomUUID());
    data.put("scope","app");
    data.put("token_type","bearer");
    result.put("data",data);
    result.put("message","登陆成功");
    result.put("nonBizError",false);
    result.put("user_id",appUserEntity.getId());
    return result;
  }

  /**
   * 根据用户ID获取用户详细信息
   *
   * @param userId
   * @return
   */
  @Override
  public Map<String, Object> getUserinfoByUserid(Integer userId) throws IllegalAccessException {
    Map<String,Object> result=new HashMap<>();
    //查询用户基本信息
    AppUserEntity appUserEntity=appUserMapper.selectById(userId);
    //查询用户角色
    QueryWrapper<SysRoleUserEntity> roleUserEntityQueryWrapper=new QueryWrapper<>();
    roleUserEntityQueryWrapper.eq("user_id",userId);
    List<SysRoleUserEntity> roleUserEntityList=sysRoleUserMapper.selectList(roleUserEntityQueryWrapper);
    //查询角色信息
    List<Integer> roleids=new ArrayList<>();
    roleUserEntityList.forEach(item->{
      roleids.add(item.getRoleId());
    });
    List<SysRoleEntity> roleEntityList=new ArrayList<>();
    if(roleids.size()>0){
      roleEntityList=sysRoleMapper.selectBatchIds(roleids);
    }

    //查询角色权限
    List<SysRolePermissionEntity> sysRolePermissionEntities=new ArrayList<>();
    if(roleids.size()>0){
      QueryWrapper<SysRolePermissionEntity> rolePermissionEntityQueryWrapper=new QueryWrapper<>();
      rolePermissionEntityQueryWrapper.in("roleId",roleids);
      sysRolePermissionEntities=sysRolePermissionMapper.selectList(rolePermissionEntityQueryWrapper);
    }
    //查询权限信息
    List<Integer> permissionIds=new ArrayList<>();
    sysRolePermissionEntities.forEach(item->{
      permissionIds.add(item.getPermissionId());
    });
    List<SysPermissionEntity> sysPermissionEntityList=new ArrayList<>();
    if(permissionIds.size()>0){
      sysPermissionEntityList=sysPermissionMapper.selectBatchIds(permissionIds);
    }
    //组装返回数据
    result.put("code","ACK");
    Map<String,Object> data= MapUtil.objectToMap(appUserEntity);
    data.put("accountNonExpired",true);
    data.put("accountNonLocked",true);
    data.put("credentialsNonExpired",true);
    data.put("passed",1);

    List<String> permission=new ArrayList<>();
    sysPermissionEntityList.forEach(item->{
      permission.add(item.getPermission());
    });
    data.put("permissions",permission);
    data.put("sysRoles",roleEntityList);
    result.put("data",data);
    result.put("code","ACK");
    result.put("message","成功");
    result.put("nonBizError","false");
    return result;
  }

  /**
   * 注册
   *
   * @param appUserEntity
   * @return
   */
  @Override
  public Map<String, Object> register(AppUserEntity appUserEntity) {
    Map<String,Object> result=new HashMap<>();
    Map<String,Object> data=new HashMap<>();
    //验证用户名是否存在
    QueryWrapper<AppUserEntity> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("username",appUserEntity.getUsername());
    if(appUserMapper.selectOne(queryWrapper)!=null){
      result.put("code","ACK");
      data.put("message","用户名已存在");
      data.put("success",false);
      result.put("data",data);
      result.put("nonBizError","false");
      return result;
    }else{
      appUserEntity.setEnabled(true);
      appUserEntity.setCreateTime(new Date());
      appUserEntity.setUpdateTime(new Date());
      appUserEntity.setPassword(MD5Util.getMD5String(appUserEntity.getPassword()));
      appUserMapper.insert(appUserEntity);
      result.put("code","ACK");
      data.put("message","注册成功");
      data.put("success",true);
      result.put("data",data);
      result.put("nonBizError","false");
      return result;
    }
  }

  @Override
  public Map<String, Object> getUserList(AppUserReqDTO reqDTO) {
    Map<String,Object> result=new HashMap<>();
    QueryWrapper<AppUserEntity> queryWrapper=new QueryWrapper<>();
    if(!ObjectUtils.isEmpty(reqDTO.getNickname())){
      queryWrapper.like("username",reqDTO.getNickname());
    }

    if(!ObjectUtils.isEmpty(reqDTO.getType())){
      queryWrapper.eq("type",reqDTO.getType());
    }
    List<AppUserEntity> list=new ArrayList<>();
    if(reqDTO.getPage()!=null&&reqDTO.getPage()>0){
      IPage page=new Page(reqDTO.getPage(), reqDTO.getSize());
      list=appUserMapper.selectPage(page,queryWrapper).getRecords();
    }else{
      list=appUserMapper.selectList(queryWrapper);
    }

    /*List<BulletinRespDTO> data=new ArrayList<>();
    list.forEach(item->{
      BulletinRespDTO bulletinRespDTO=new BulletinRespDTO();
      BeanUtils.copyProperties(item,bulletinRespDTO);
      data.add(bulletinRespDTO);
    });*/
    Map<String,Object> dataMap=new HashMap<>();
    dataMap.put("list",list);
    dataMap.put("current",reqDTO.getPage());
    dataMap.put("size",reqDTO.getSize());
    dataMap.put("total",appUserMapper.selectCount(queryWrapper));
    result.put("data",dataMap);
    result.put("code","ACK");
    result.put("message","查询成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> changeUserState(int id, int status) {
    Map<String,Object> result=new HashMap<>();
    AppUserEntity appUserEntity=appUserMapper.selectById(id);
    appUserEntity.setEnabled(status==1);
    appUserMapper.updateById(appUserEntity);
    result.put("code","ACK");
    result.put("message","修改成功");
    result.put("nonBizError",false);
    return result;
  }

  @Override
  public Map<String, Object> changePassword(int id, String password) {
    Map<String,Object> result=new HashMap<>();
    AppUserEntity appUserEntity=appUserMapper.selectById(id);
    appUserEntity.setPassword(MD5Util.getMD5String(password));
    appUserMapper.updateById(appUserEntity);
    result.put("code","ACK");
    result.put("message","修改成功");
    result.put("nonBizError",false);
    return result;
  }
}
