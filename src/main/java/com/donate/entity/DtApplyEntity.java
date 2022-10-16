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
@TableName("dt_apply")
public class DtApplyEntity {
  /**
   * 申请表Id
   **/
  @TableId(type = IdType.AUTO, value = "id")
  private Integer id;
  /**
   * 申请人Id
   **/
  @TableField(value = "user_id")
  private Integer userId;
  /**
   * 用户名
   **/
  @TableField(value = "user_name")
  private String userName;
  /**
   * 审核人id
   **/
  @TableField(value = "verify_user_id")
  private Integer verifyUserId;
  /**
   * 标题
   **/
  @TableField(value = "title")
  private String title;
  /**
   * 内容
   **/
  @TableField(value = "content")
  private String content;
  /**
   * 类型：0公益；1个人
   **/
  @TableField(value = "apply_type")
  private Integer applyType;
  /**
   * 状态：0，审核中；1，通过；2拒绝
   **/
  @TableField(value = "apply_state")
  private Integer applyState;
  /**
   * 目标
   **/
  @TableField(value = "goal")
  private Float goal;
  /**
   * 完成：0已经，1进行，2下线
   **/
  @TableField(value = "completed")
  private Integer completed;
  /**
   * 创建时间
   **/
  @TableField(value = "gmt_created")
  private Date gmtCreated;
  /**
   * 修改时间
   **/
  @TableField(value = "gmt_modified")
  private Date gmtModified;
  /**
   * 照片链接
   **/
  @TableField(value = "file")
  private String file;
  /**
   * 是否删除
   **/
  @TableField(value = "is_deleted")
  private Integer isDeleted;

  /**
   * 儿童ID
   **/
  @TableField(value = "child_id")
  private Integer childId;

  /**
   * 城市编码
   **/
  @TableField(value = "city_code")
  private String cityCode;
}
