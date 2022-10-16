package com.donate.dto;

import lombok.Data;
import java.util.Date;

@Data
public class SysRolePermissionDTO {
  /**
   * 角色id
   **/
  private Integer roleId;
  /**
   * 权限id
   **/
  private Integer permissionId;
}
