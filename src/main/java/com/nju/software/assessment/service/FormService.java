package com.nju.software.assessment.service;

import com.nju.software.assessment.bean.Form;
import com.nju.software.assessment.dao.FormDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormService {

    @Autowired
    FormDao formDao;

    public void save(Form form){
        formDao.save(form);
    }

    public Form findFormById(Integer id){
        return formDao.findFormById(id);
//        return formDao.findOne(id);
    }

    public void deleteFormById(Integer id){
        formDao.deleteFormById(id);
    }
}
