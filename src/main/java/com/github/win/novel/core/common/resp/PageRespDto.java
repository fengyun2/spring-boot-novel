package com.github.win.novel.core.common.resp;

import java.util.List;
import lombok.Getter;

/**
 * 分页响应数据格式封装
 */
@Getter
public class PageRespDto<T> {

  /**
   * 页码
   */
  private final long pageNum;

  /**
   * 每页显示条数
   */
  private final long pageSize;

  /**
   * 总记录数
   */
  private final long total;

  /**
   * 分页数据集
   */
  private final List<? extends T> list;

  /**
   * 该构造函数用于通用分页查询的场景，接收普通分页数据和普通集合
   */
  public PageRespDto(long pageNum, long pageSize, long total, List<? extends T> list) {
    this.pageNum = pageNum;
    this.pageSize = pageSize;
    this.total = total;
    this.list = list;
  }

  public static <T> PageRespDto<T> of(long pageNum, long pageSize, long total, List<? extends T> list) {
    return new PageRespDto<>(pageNum, pageSize, total, list);
  }

  /**
   * 获取分页数
   */
  public long getPages() {
    if (this.pageSize == 0L) {
      return 0L;
    } else {
      long pages = this.total / this.pageSize;
      if (this.total % this.pageSize != 0L) {
        ++pages;
      }
      return pages;
    }
  }
}
