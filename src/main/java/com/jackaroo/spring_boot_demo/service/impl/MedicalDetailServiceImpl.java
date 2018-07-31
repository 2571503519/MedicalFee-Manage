package com.jackaroo.spring_boot_demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jackaroo.spring_boot_demo.mapper.MedicalDetailMapper;
import com.jackaroo.spring_boot_demo.pojo.MedicalDetail;
import com.jackaroo.spring_boot_demo.service.IMedicalDetailService;
import com.jackaroo.spring_boot_demo.util.Constant;
import com.jackaroo.spring_boot_demo.util.MedicalDetailQueryBean;
import com.jackaroo.spring_boot_demo.util.PageQueryBean;
import com.jackaroo.spring_boot_demo.util.page.PageConfig;
import com.jackaroo.spring_boot_demo.util.page.Pagination;
import com.jackaroo.spring_boot_demo.vo.EmployeeVo;
import com.jackaroo.spring_boot_demo.vo.MedicalDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JackarooZhang
 * @date 2018/6/10 20:52
 */
@Service
public class MedicalDetailServiceImpl implements IMedicalDetailService {

    @Autowired
    private MedicalDetailMapper medicalDetailMapper;

    @Override
    public Pagination<MedicalDetailVo> getMedicalDetailConditionally(MedicalDetailQueryBean queryBean, PageQueryBean pageQueryBean) {
        PageHelper.startPage(pageQueryBean.getPageNumber(), pageQueryBean.getPageSize());
        List<MedicalDetail> employeeList = medicalDetailMapper.getAllMedicalDetail(queryBean);
        List<MedicalDetailVo> voList = assembleMedicalDetailVo(employeeList);

        String keywords = "";
        String medicalCateId = "";
        String status = "";

        if (queryBean.getKeywords() != null) {
            keywords = queryBean.getKeywords();
        }
        if (queryBean.getMedicalCateId() != null) {
            medicalCateId = Long.toString(queryBean.getMedicalCateId());
        }
        if (queryBean.getStatus() != null) {
            status = Integer.toString(queryBean.getStatus().getId());
        }

        String baseUrl = "/costList.html?keywords=" + keywords
                + "&medical_cate_id=" + medicalCateId + "&status=" + status;
        PageConfig pageConfig = PageConfig.create(medicalDetailMapper.getRowsCountConditionally(queryBean)
                , 5, baseUrl, 5);
        Pagination<MedicalDetailVo> pagination = Pagination.create(pageConfig);
        pagination.setCurrentPageNumber(pageQueryBean.getPageNumber());
        pagination.setPageData(voList);

        return pagination;
    }

    private List<MedicalDetailVo> assembleMedicalDetailVo(List<MedicalDetail> employeeList) {
        List<MedicalDetailVo> voList = new ArrayList<>();
        MedicalDetailVo vo = null;
        for (MedicalDetail medicalDetail : employeeList) {
            vo = new MedicalDetailVo();
            vo.setMedicalId(medicalDetail.getMedicalId());
            vo.setDesc(medicalDetail.getDesc());
            vo.setAmount(medicalDetail.getAmount());
            vo.setEmployee(medicalDetail.getEmployee());
            vo.setMedicalCategory(medicalDetail.getMedicalCategory());
            vo.setMedicalNumber(medicalDetail.getMedicalNumber());
            vo.setCreateTime(new Date(medicalDetail.getCreateTime()));
            vo.setUpdateTime(new Date(medicalDetail.getUpdateTime()));
            vo.setStatus(medicalDetail.getStatus().getStatus());
            voList.add(vo);
        }
        return voList;
    }



}
