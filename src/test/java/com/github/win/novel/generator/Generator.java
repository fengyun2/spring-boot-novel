package com.github.win.novel.generator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 代码生成器
 */
public class Generator {

  // private static final String USERNAME = System.getenv().get("USER");
  private static final String USERNAME = "win";

  /**
   * 项目信息
   */
  private static final String PROJECT_PATH = System.getProperty("user.dir");
  private static final String JAVA_PATH = "/src/main/java";
  private static final String RESOURCE_PATH = "/src/main/resources";
  private static final String BASE_PACKAGE = "com.github.win.novel";

  /**
   * 数据库信息
   */
  private static final String DATABASE_IP = "127.0.0.1";
  private static final String DATABASE_PORT = "3306";
  private static final String DATABASE_NAME = "novel_test";
  private static final String DATABASE_USERNAME = "root";
  private static final String DATABASE_PASSWORD = "root123";
  private static final String DATABASE_URL = String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai", DATABASE_IP, DATABASE_PORT, DATABASE_NAME);

  public static void main(String[] args) {
    // 传入需要生成的表名，多个用英文逗号分割，所有用 all 表示
    genCode("sys_user");
  }

  /**
   * 代码生成
   * @param {String} tables
   */
  private static void genCode(String tables) {
    System.out.println("开始生成代码..." + DATABASE_URL);

    // 全局配置
    FastAutoGenerator.create(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)
      .globalConfig(builder -> {
        builder.author(USERNAME) // 设置作者
          // .enableSwagger() // 开启swagger 模式
          .disableOpenDir() // 禁止打开输出目录
          .dateType(DateType.TIME_PACK)
          .commentDate("yyyy-MM-dd HH:mm:ss")
          .outputDir(PROJECT_PATH + JAVA_PATH); // 指定输出目录
      })
      // 包配置
      .packageConfig(builder -> {
        builder.parent(BASE_PACKAGE) // 设置父包名
          .entity("dao.entity") // 设置实体类包名，默认为 entity
          .service("service") // 设置 service 包名
          .serviceImpl("service.impl")
          .mapper("dao.mapper")
          .controller("controller.front")
          .pathInfo(Collections.singletonMap(OutputFile.xml, PROJECT_PATH + RESOURCE_PATH + "/mapper"));
      })
      // 模板配置
      .templateConfig(builder -> {
        // builder.disable(TemplateType.SERVICE)
        //   .disable(TemplateType.SERVICE_IMPL)
        //   .disable(TemplateType.CONTROLLER);
          builder.disable(TemplateType.ENTITY)
              .entity("/templates/entity.java.vm")
              .service("/templates/service.java.vm")
              .serviceImpl("/templates/serviceImpl.java.vm")
              .mapper("/templates/mapper.java.vm")
              .xml("/templates/mapper.xml.vm")
              .controller("/templates/controller.java.vm")
              .build();
      })
      // 策略配置
      .strategyConfig(builder -> {
        builder.addInclude(getTables(tables)) // 设置需要生成的表名
        .entityBuilder()
            .enableFileOverride()
            // .enableLombok()
            .enableChainModel()
            .enableTableFieldAnnotation()
            .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
            .columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的命名策略
            .idType(IdType.AUTO)
            .build();
        builder.controllerBuilder()
            .enableFileOverride()
            .enableHyphenStyle() // 开启驼峰转连字符
            .enableRestStyle() // 开启生成 @RestController 控制器
            .build();
        builder.serviceBuilder()
            .enableFileOverride()
            .formatServiceFileName("%sService")
            .build();
        builder.mapperBuilder()
            .enableFileOverride()
            .mapperAnnotation(Mapper.class)
            .enableBaseResultMap()
            .enableBaseColumnList()
            .build();
      })
      .execute();
  }

  /**
   * 处理 all 和多表情况
   */
  protected static List<String> getTables(String tables) {
    return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
  }
}
