package com.jackaroo.spring_boot_demo.mapper;

import com.jackaroo.spring_boot_demo.pojo.Employee;
import com.jackaroo.spring_boot_demo.pojo.MedicalDetail;
import com.jackaroo.spring_boot_demo.util.MedicalDetailQueryBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JackarooZhang
 * @date 2018/6/7 20:06
 */
@Mapper
public interface MedicalDetailMapper {

    int saveMedicalDetail(MedicalDetail detail);

    int updateMedicalDetailByPrimaryKeySelective(MedicalDetail detail);

    int deleteMedicalDetailByPrimaryKey(Long medicalId);

    List<MedicalDetail> getAllMedicalDetail(MedicalDetailQueryBean queryBean);

    MedicalDetail getMedicalDetailByEmpId(Long medicalId);

    Long getRowsCount();

    Long getRowsCountConditionally(MedicalDetailQueryBean queryBean);

}
