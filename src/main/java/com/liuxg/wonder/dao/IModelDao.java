package com.liuxg.wonder.dao;

import com.liuxg.wonder.po.Model;

import java.util.List;

public interface IModelDao {

    List<Model> query();

    Model queryOne(String id);

    int add(Model model) throws Exception;

    int delete(Model model) throws Exception;
}
