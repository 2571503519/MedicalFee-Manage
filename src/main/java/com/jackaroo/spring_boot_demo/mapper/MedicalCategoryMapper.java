package com.jackaroo.spring_boot_demo.mapper;

import com.jackaroo.spring_boot_demo.pojo.MedicalCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JackarooZhang
 * @date 2018/6/8 8:58
 */
@Mapper
public interface MedicalCategoryMapper {

    int saveMedicalCategory(MedicalCategory medicalCategory);

    int updateMedicalCategoryByPrimaryKeySelective(MedicalCategory medicalCategory);

    int deleteMedicalCategoryByPrimaryKey(Long medicalCateId);

    List<MedicalCategory> getAllMedicalCategory();

    MedicalCategory getMedicalCategoryByMedicalCateId(Long medicalCateId);

    List<MedicalCategory> getMedicalCategoriesByStatus(Integer status);

}
