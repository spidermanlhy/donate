package com.donate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("child_list")
public class ChildListEntity {
  /**
   *
   **/
  @TableId(type = IdType.AUTO, value = "id")
  private Integer id;
  /**
   *
   **/
  @TableField(value = "user_id")
  private Integer userId;
  /**
   *
   **/
  @TableField(value = "child_name")
  private String childName;
  /**
   *
   **/
  @TableField(value = "child_code")
  private String childCode;
  /**
   *
   **/
  @TableField(value = "child_age")
  private Integer childAge;
  /**
   *
   **/
  @TableField(value = "child_sex")
  private String childSex;
  /**
   *
   **/
  @TableField(value = "address")
  private String address;
  /**
   *
   **/
  @TableField(value = "city")
  private String city;
  /**
   *
   **/
  @TableField(value = "school")
  private String school;
  /**
   *
   **/
  @TableField(value = "score")
  private String score;
  /**
   *
   **/
  @TableField(value = "healthy")
  private String healthy;
  /**
   *
   **/
  @TableField(value = "base_message")
  private String baseMessage;
  /**
   *
   **/
  @TableField(value = "master_name")
  private String masterName;
  /**
   *
   **/
  @TableField(value = "face_difficulty")
  private String faceDifficulty;
  /**
   *
   **/
  @TableField(value = "del_flag")
  private Integer delFlag;

  @TableField(value = "file_path")
  private String filePath;

  @TableField(value = "status")
  private Integer status;

  @TableField(value = "city_text")
  private String cityText;
}
