package com.liuxg.wonder.service.impl;

import com.liuxg.wonder.dao.IModelDao;
import com.liuxg.wonder.po.Model;
import com.liuxg.wonder.service.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModelServiceImpl implements IModelService {

    @Autowired
    IModelDao modelDao;

    @Override
    public List<Model> query() {
        return modelDao.query();
    }

    @Override
    public Model queryOne(String id) {
        return modelDao.queryOne(id);
    }

    @Override
    public int add(Model model) throws Exception {
        return modelDao.add(model);
    }

    @Override
    public int delete(Model model) throws Exception {
        return modelDao.delete(model);
    }
}
