package com.donate.dto;

import lombok.Data;
import java.util.Date;

@Data
public class SysPermissionDTO {
  /**
   * 自增id
   **/
  private Integer id;
  /**
   * 权限标识
   **/
  private String permission;
  /**
   * 名称
   **/
  private String name;
  /**
   * 创建时间
   **/
  private Date createTime;
  /**
   * 修改时间
   **/
  private Date updateTime;
  /**
   * 是否删除，0删除，1未删除
   **/
  private Integer isDeleted;
}
