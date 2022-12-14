package com.donate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("news_category")
public class NewsCategoryEntity {
  @TableId(type = IdType.AUTO)
  private Integer id;

  @TableField("name")
  private String name;
}
