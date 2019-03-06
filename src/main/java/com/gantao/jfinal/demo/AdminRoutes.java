package com.gantao.jfinal.demo;

import com.gantao.jfinal.controller.HelloController;
import com.gantao.jfinal.controller.SqlController;
import com.jfinal.config.Routes;
// 后端路由
public class AdminRoutes extends Routes {
    public void config() {
        setBaseViewPath("/template");
        add("/hello", HelloController.class);
        add("/sql", SqlController.class);
    }
}
