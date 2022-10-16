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
@TableName("sys_role_permission")
public class SysRolePermissionEntity {
  /**
   * 角色id
   **/
  @TableField( value = "roleId")
  private Integer roleId;
  /**
   * 权限id
   **/
  @TableField(value = "permissionId")
  private Integer permissionId;
}
