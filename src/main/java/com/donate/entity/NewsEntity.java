package com.donate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("news")
public class NewsEntity {

  @TableId(type = IdType.AUTO)
  private Integer id;

  @TableField("title")
  private String title;

  @TableField("content")
  private String content;

  @TableField("create_date")
  private Date createDate;

  @TableField("add_user_id")
  private Integer addUserId;

  @TableField("add_user_name")
  private String addUserName;

  @TableField("type")
  private Integer type;

  @TableField("status")
  private Integer status;
}
