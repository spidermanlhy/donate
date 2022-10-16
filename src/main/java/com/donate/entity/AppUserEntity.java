package com.donate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("app_user")
public class AppUserEntity {
  /**
   * 自增id
   **/
  @TableId(type = IdType.AUTO, value = "id")
  private Integer id;
  /**
   * 用户名
   **/
  @TableField(value = "username")
  private String username;
  /**
   * 密码
   **/
  @TableField(value = "password")
  private String password;
  /**
   * 昵称
   **/
  @TableField(value = "nickname")
  private String nickname;
  /**
   * 头像url
   **/
  @TableField(value = "headImgUrl")
  private String headImgUrl;
  /**
   * 手机号
   **/
  @TableField(value = "phone")
  private String phone;
  /**
   * 邮箱
   **/
  @TableField(value = "mail")
  private String mail;
  /**
   * 性别
   **/
  @TableField(value = "sex")
  private Integer sex;
  /**
   * 状态（1有效,0无效）
   **/
  @TableField(value = "enabled")
  private Boolean enabled;
  /**
   * 类型（暂未用）
   **/
  @TableField(value = "type")
  private String type;
  /**
   * 创建时间
   **/
  @TableField(value = "createTime")
  private Date createTime;
  /**
   * 修改时间
   **/
  @TableField(value = "updateTime")
  private Date updateTime;
}
