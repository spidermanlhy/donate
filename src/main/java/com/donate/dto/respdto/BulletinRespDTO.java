package com.donate.dto.respdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BulletinRespDTO {
  /**
   *
   **/
  private Integer id;
  /**
   *
   **/
  private Integer createdUserId;
  /**
   *
   **/
  private Integer modifiedUserId;
  /**
   *
   **/
  private String title;
  /**
   *
   **/
  private String content;
  /**
   *
   **/
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date gmtCreated;
  /**
   *
   **/
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date gmtModified;
  /**
   * 0：展示在首页；1：不展示在首页
   **/
  private Integer isShow;
  /**
   *
   **/
  private Integer isDeleted;
}
