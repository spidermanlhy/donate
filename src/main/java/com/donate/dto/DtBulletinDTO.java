package com.donate.dto;

import lombok.Data;
import java.util.Date;

@Data
public class DtBulletinDTO {
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
  private Date gmtCreated;
  /**
   *
   **/
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
