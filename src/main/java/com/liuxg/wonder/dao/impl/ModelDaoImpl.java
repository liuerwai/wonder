package com.liuxg.wonder.dao.impl;

import com.liuxg.wonder.dao.IModelDao;
import com.liuxg.wonder.po.Model;
import com.liuxg.wonder.util.SerializerUtills;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

@Component
public class ModelDaoImpl implements IModelDao {

    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock READ_LOCK = readWriteLock.readLock();
    private static final ReentrantReadWriteLock.WriteLock WRITE_LOCK = readWriteLock.writeLock();

    @Override
    public List<Model> query() {

        Map<String, Model> map = SerializerUtills.getModel();
        return map.values().stream().collect(Collectors.toList());
    }

    @Override
    public Model queryOne(String id) {

        Map<String, Model> map = SerializerUtills.getModel();
        return map.get(id);
    }

    @Override
    public int add(Model model) throws Exception {

        if (StringUtils.isEmpty(model.getId())) {
            throw new Exception("id不能为空！");
        }
        WRITE_LOCK.lock();
        try {
            Map map = SerializerUtills.getModel();
            map.put(model.getId(), model);
            SerializerUtills.saveModel(map);
        } catch (Exception e) {
            return 0;
        } finally {
            WRITE_LOCK.unlock();
        }
        return 1;
    }

    @Override
    public int delete(Model model) throws Exception {

        if (StringUtils.isEmpty(model.getId())) {
            throw new Exception("id不能为空！");
        }
        WRITE_LOCK.lock();
        try {
            Map map = SerializerUtills.getModel();
            map.remove(model.getId());
            SerializerUtills.saveModel(map);
        } catch (Exception e) {
            return 0;
        } finally {
            WRITE_LOCK.unlock();
        }
        return 1;
    }
}
