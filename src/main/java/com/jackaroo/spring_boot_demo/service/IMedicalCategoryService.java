package com.jackaroo.spring_boot_demo.service;

import com.jackaroo.spring_boot_demo.pojo.MedicalCategory;
import com.jackaroo.spring_boot_demo.util.Constant;

import java.util.List;

/**
 * @author JackarooZhang
 * @date 2018/6/10 21:22
 */
public interface IMedicalCategoryService {

    List<MedicalCategory> getMedicalCategoryByStatus(Constant.CommonStatus status);

}
