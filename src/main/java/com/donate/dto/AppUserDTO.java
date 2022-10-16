package com.donate.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AppUserDTO {
  /**
   * 自增id
   **/
  private Integer id;
  /**
   * 用户名
   **/
  private String username;
  /**
   * 密码
   **/
  private String password;
  /**
   * 昵称
   **/
  private String nickname;
  /**
   * 头像url
   **/
  private String headImgUrl;
  /**
   * 手机号
   **/
  private String phone;
  /**
   * 邮箱
   **/
  private String mail;
  /**
   * 性别
   **/
  private Integer sex;
  /**
   * 状态（1有效,0无效）
   **/
  private Integer enabled;
  /**
   * 类型（暂未用）
   **/
  private String type;
  /**
   * 创建时间
   **/
  private Date createTime;
  /**
   * 修改时间
   **/
  private Date updateTime;
}
