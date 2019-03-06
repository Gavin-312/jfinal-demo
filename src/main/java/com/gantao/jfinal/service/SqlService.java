package com.gantao.jfinal.service;

import com.gantao.jfinal.model.Deposite;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

public interface SqlService {
    List<Record> relation(int i, int i1);
}
