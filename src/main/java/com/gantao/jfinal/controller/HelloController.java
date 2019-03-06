package com.gantao.jfinal.controller;

import com.gantao.jfinal.interceptor.AdminAuthInterceptor;
import com.gantao.jfinal.interceptor.HelloValidator;
import com.gantao.jfinal.model.Bank;
import com.gantao.jfinal.model.Deposite;
import com.gantao.jfinal.service.DemoService;
import com.gantao.jfinal.service.impl.DemoServiceImpl;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;

import java.util.Arrays;
import java.util.List;

@Before(AdminAuthInterceptor.class)
public class HelloController extends Controller {

    @Inject(DemoServiceImpl.class)
    private DemoService demoService;


    @Before(GET.class)
    public void index() {
        //全部操作
        List<Bank> banks = demoService.find();
        //表关联操作
        String bankName = demoService.getIdByBankName(2);
        //表关联操作List
        List<Deposite> deposites = demoService.getById("101001");
        setAttr("bank",banks).setAttr("bId",deposites);
        render("/template/login.html");
    }

//    @ActionKey("/login") //不写访问hello+method
    @Before(HelloValidator.class)
    public void home(){
        Bank user = this.getBean(Bank.class);
        System.out.println("user = " + user);
        setAttr("user",user);
        render("/template/home.html");
    }

    @Before(GET.class)
    public void getParas(){
        String id = getPara("title"); //传值 - 号分割，下标0开始
        System.out.println("id = " + id);
        setAttr("id",id).render("/template/home.html");
    }

    @Before(POST.class)
    public void postParas(){
        String name = getPara("username"); //传值 - 号分割，下标0开始
        String pwd = getPara("password"); //传值 - 号分割，下标0开始
        System.out.println("name = " + name);
        System.out.println("pwd = " + pwd);
        renderFreeMarker("/template/home.html");
    }
}
