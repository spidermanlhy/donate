package com.donate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

;
;
;
;
;
;

@Data
@TableName("sys_role_user")
public class SysRoleUserEntity {
  /**
   * 用户id
   **/
  @TableField(value = "user_id")
  private Integer userId;
  /**
   * 角色id
   **/
  @TableField(value = "role_id")
  private Integer roleId;
}
