package com.gantao.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class AdminAuthInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        System.out.println("控制层拦截去");
        invocation.invoke();
    }
}
