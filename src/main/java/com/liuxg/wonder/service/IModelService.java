package com.liuxg.wonder.service;

import com.liuxg.wonder.po.Model;

import java.util.List;

public interface IModelService {

    List<Model> query();

    Model queryOne(String id);

    int add(Model model) throws Exception;

    int delete(Model model) throws Exception;
}
