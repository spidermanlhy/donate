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
@TableName("dt_bulletin")
public class DtBulletinEntity {
  /**
   *
   **/
  @TableId(type = IdType.AUTO, value = "id")
  private Integer id;
  /**
   *
   **/
  @TableField(value = "created_user_id")
  private Integer createdUserId;
  /**
   *
   **/
  @TableField(value = "modified_user_id")
  private Integer modifiedUserId;
  /**
   *
   **/
  @TableField(value = "title")
  private String title;
  /**
   *
   **/
  @TableField(value = "content")
  private String content;
  /**
   *
   **/
  @TableField(value = "gmt_created")
  private Date gmtCreated;
  /**
   *
   **/
  @TableField(value = "gmt_modified")
  private Date gmtModified;
  /**
   * 0：展示在首页；1：不展示在首页
   **/
  @TableField(value = "is_show")
  private Integer isShow;
  /**
   *
   **/
  @TableField(value = "is_deleted")
  private Integer isDeleted;
}
