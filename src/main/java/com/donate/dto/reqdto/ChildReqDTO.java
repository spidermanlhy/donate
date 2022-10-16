package com.donate.dto.reqdto;

import lombok.Data;

import java.util.List;

@Data
public class ChildReqDTO {
  private Integer userId;

  private String childName;

  private String childCode;

  private Integer status;

  private Integer page;

  private Integer size;

  private List<String> city;
}
