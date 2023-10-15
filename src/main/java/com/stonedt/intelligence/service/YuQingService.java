package com.stonedt.intelligence.service;

import com.stonedt.intelligence.dao.YuQingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YuQingService {
    @Autowired
    private YuQingDao yuQingDao;

    public List<Document> find(){
        return yuQingDao.find();
    }
}
