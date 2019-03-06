package com.gantao.jfinal.interceptor;

import com.gantao.jfinal.model.Bank;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

        public class HelloValidator extends Validator {

    @Override
    protected void validate(Controller controller) {
        validateRequiredString("bank.bId","idMsg","请输入id");
        validateRequiredString("bank.bankName","nameMsg","请输入名字");
    }

    @Override
    protected void handleError(Controller controller) {
        controller.keepModel(Bank.class);
//        controller.keepPara("bId");
        String actionKey = getActionKey();
        System.out.println("actionKey = " + actionKey);
        if(actionKey.equals("/hello/home")){
            controller.render("/template/login.html");
        }
    }
}
