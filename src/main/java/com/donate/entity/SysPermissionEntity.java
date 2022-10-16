package com.donate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

;
;
;
;
;
;

@Data
@TableName("sys_permission")
public class SysPermissionEntity {
  /**
   * 自增id
   **/
  @TableId(type = IdType.AUTO, value = "id")
  private Integer id;
  /**
   * 权限标识
   **/
  @TableField(value = "permission")
  private String permission;
  /**
   * 名称
   **/
  @TableField(value = "name")
  private String name;
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
  /**
   * 是否删除，0删除，1未删除
   **/
  @TableField(value = "is_deleted")
  private Integer isDeleted;
}
