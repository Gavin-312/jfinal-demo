package com.gantao.jfinal.demo;

import com.gantao.jfinal.controller.HelloController;
import com.gantao.jfinal.controller.SqlController;
import com.gantao.jfinal.interceptor.AdminAuthInterceptor;
import com.gantao.jfinal.interceptor.InterceptorDemo;
import com.gantao.jfinal.model._MappingKit;
import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;

public class    DemoConfig extends JFinalConfig {

    public static void main(String[] args) {
        UndertowServer.start(DemoConfig.class, 8080, true);
    }
    @Override
    public void configConstant(Constants me) {
        me.setDevMode(true);
        //开启依赖注入
        me.setInjectDependency(true);
        //声明模板类型
//        me.setViewType(ViewType.FREE_MARKER);
    }

    @Override
    public void configRoute(Routes me) {
        me.add("/hello", HelloController.class);
        me.add("/sql", SqlController.class);

    }

    @Override
    public void configEngine(Engine me) {
        // devMode 配置为 true，将支持模板实时热加载
        me.setDevMode(true);

    }

    @Override
    public void configPlugin(Plugins me) {

        loadPropertyFile("config.txt");
        DruidPlugin dp = new DruidPlugin(getProperty("jdbcUrl"),getProperty("user"),getProperty("password"));
        me.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.getEngine().setSourceFactory(new ClassPathSourceFactory());
        arp.addSqlTemplate("sql/all.sql");
        _MappingKit.mapping(arp);
        me.add(arp);
    }

    /**
     * 全局拦截器
     * @param me
     */
    @Override
    public void configInterceptor(Interceptors me) {
        me.addGlobalActionInterceptor(new AdminAuthInterceptor());
        me.addGlobalServiceInterceptor(new InterceptorDemo());
    }
    @Override
    public void configHandler(Handlers me) {
    }

}
