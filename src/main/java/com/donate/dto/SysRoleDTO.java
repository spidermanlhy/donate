package com.donate.dto;

import lombok.Data;
import java.util.Date;

@Data
public class SysRoleDTO {
  /**
   * 自增id
   **/
  private Integer id;
  /**
   * 角色code
   **/
  private String code;
  /**
   * 角色名
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
   * 1未删除，0删除
   **/
  private Integer isDeleted;
}
