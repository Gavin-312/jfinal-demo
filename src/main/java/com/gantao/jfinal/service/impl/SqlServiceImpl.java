package com.gantao.jfinal.service.impl;

import com.gantao.jfinal.model.Deposite;
import com.gantao.jfinal.service.SqlService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

public class SqlServiceImpl implements SqlService {
    @Override
    public List<Record> relation(int i, int i1) {
        String sql = Db.getSql("find");
        List<Record> records = Db.find(sql,i,i1);
//        for (Record r:records) {
//            System.out.println("r = " + r);
//        }
        return records;
    }
}
