package com.github.win.novel.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.win.novel.core.common.resp.RestResp;
import com.github.win.novel.dao.entity.SysUser;
import com.github.win.novel.service.SysUserService;

/**
 * <p>
 * 系统用户 控制器
 * </p>
 *
 * @author win
 * @date 2024-03-03 22:37:47
 */
@RestController
@RequestMapping("/sys_user")
public class SysUserController {

  @Autowired
  private SysUserService sysUserService;

  @GetMapping("")
  public RestResp<List<SysUser>> list() {
    try {
      List<SysUser> sysUserList = sysUserService.list();
      System.out.println(sysUserList + "用户信息");
      return RestResp.ok(sysUserList);
    } catch (Exception e) {
      // TODO: handle exception
      System.out.println(e.getMessage() + "报错信息");
      return null;
    }
  }
}
