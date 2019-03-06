package com.gantao.jfinal.demo;

import com.gantao.jfinal.controller.HelloController;
import com.jfinal.config.Routes;
// 前端路由
public class FrontRoutes extends Routes {
    @Override
    public void config() {
        setBaseViewPath("/template");
        add("/admin/user", HelloController.class);
    }
}
