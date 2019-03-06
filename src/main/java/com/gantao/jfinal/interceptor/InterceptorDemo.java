package com.gantao.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class InterceptorDemo implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        System.out.println("业务层拦截");
        invocation.invoke();
    }
}
