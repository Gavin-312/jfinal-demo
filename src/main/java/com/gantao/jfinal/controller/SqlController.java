package com.gantao.jfinal.controller;

import com.gantao.jfinal.interceptor.AdminAuthInterceptor;
import com.gantao.jfinal.service.SqlService;
import com.gantao.jfinal.service.impl.SqlServiceImpl;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.plugin.activerecord.Record;

import java.util.Arrays;
import java.util.List;

@Before(AdminAuthInterceptor.class)
public class SqlController extends Controller {

    @Inject(SqlServiceImpl.class)
    private SqlService sqlService;

    @Before(GET.class)
    public void index() {
        List<Record> deposites = sqlService.relation(101001,101004);
        System.out.println("deposites = " + Arrays.toString(deposites.toArray()));
        setAttr("deposite",deposites);
        render("/template/login.html");
    }
}
