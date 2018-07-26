package com.jackaroo.spring_boot_demo.service.impl;

import com.jackaroo.spring_boot_demo.mapper.MedicalCategoryMapper;
import com.jackaroo.spring_boot_demo.pojo.MedicalCategory;
import com.jackaroo.spring_boot_demo.service.IMedicalCategoryService;
import com.jackaroo.spring_boot_demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JackarooZhang
 * @date 2018/6/10 21:24
 */
@Service
public class MedicalCategoryServiceImpl implements IMedicalCategoryService {

    @Autowired
    private MedicalCategoryMapper medicalCategoryMapper;

    @Override
    public List<MedicalCategory> getMedicalCategoryByStatus(Constant.CommonStatus status) {

        List<MedicalCategory> medicalCategoryList = medicalCategoryMapper.getMedicalCategoriesByStatus(status.getId());
        return medicalCategoryList;
    }
}
