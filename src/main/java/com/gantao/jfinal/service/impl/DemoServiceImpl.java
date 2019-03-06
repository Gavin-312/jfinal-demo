package com.gantao.jfinal.service.impl;

import com.gantao.jfinal.interceptor.InterceptorDemo;
import com.gantao.jfinal.model.Bank;
import com.gantao.jfinal.model.Deposite;
import com.gantao.jfinal.service.DemoService;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;

import java.util.List;

@Before(InterceptorDemo.class)
public class DemoServiceImpl implements DemoService {
    private Bank dao = new Bank().dao();
    @Override
    public List<Bank> find() {
        String sql = "select * from bank";
        return dao.find(sql);
    }

    @Override
    public String getIdByBankName(int i) {
        String sql = "select ba.bank_name from bank as ba,deposite as de where ba.b_id=de.b_id and de.d_id=?";
        Bank bank = Bank.dao.findFirst(sql, i);
        String name = bank.getStr("bank_name");
        return name;
    }

    @Override
    public List<Deposite> getById(String s) {
        String sql = "select de.b_id from deposite as de,customer as cr where de.c_id=cr.c_id and cr.c_id=?";
        List<Deposite> deposite = Deposite.dao.find(sql, s);
        return deposite;
    }
}
