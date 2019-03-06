package com.gantao.jfinal.service;

import com.gantao.jfinal.model.Bank;
import com.gantao.jfinal.model.Deposite;

import java.util.List;

public interface DemoService {
    List<Bank> find();

    String getIdByBankName(int i);

    List<Deposite> getById(String s);
}
