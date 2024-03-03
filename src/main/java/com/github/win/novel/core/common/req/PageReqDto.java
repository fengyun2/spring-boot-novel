package com.github.win.novel.core.common.req;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

/**
 * 分页请求数据格式封装，所有分页请求的Dto 类都应继承该类
 */
@Data
public class PageReqDto {

  /**
   * 当前页码，默认第 1 页
   */
  @Parameter(description = "当前页码，默认第 1 页")
  private int pageNum = 1;

  /**
   * 每页显示条数，默认 10 条
   */
  @Parameter(description = "每页显示条数，默认 10 条")
  private int pageSize = 10;

  /**
   * 是否查询所有，默认不查所有，为 true 时，pageNum 和 pageSize 无效
   */
  @Parameter(hidden = true)
  private boolean fetchAll = false;
}
