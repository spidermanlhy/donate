package com.donate.dto.reqdto;

import lombok.Data;

import java.util.List;

@Data
public class ChildCreateReqDTO {
  /**
   *
   **/
  private Integer id;
  /**
   *
   **/
  private Integer userId;
  /**
   *
   **/
  private String childName;
  /**
   *
   **/
  private String childCode;
  /**
   *
   **/
  private Integer childAge;
  /**
   *
   **/
  private String childSex;
  /**
   *
   **/
  private String address;
  /**
   *
   **/
  private List<String> city;
  /**
   *
   **/
  private String school;
  /**
   *
   **/
  private String score;
  /**
   *
   **/
  private String healthy;
  /**
   *
   **/
  private String baseMessage;
  /**
   *
   **/
  private String masterName;
  /**
   *
   **/
  private String faceDifficulty;
  /**
   *
   **/
  private Integer delFlag;

  private String filePath;

  private Integer status;

  private List<String> cityText;
}
