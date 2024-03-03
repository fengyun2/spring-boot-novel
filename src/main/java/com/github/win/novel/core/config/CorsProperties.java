package com.github.win.novel.core.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * 跨域配置属性
 */
@ConfigurationProperties(prefix = "novel.cors")
@Data
public class CorsProperties {

  /**
   * 允许跨域的域名
   */
  private List<String> allowOrigins;
}
