package com.jackaroo.spring_boot_demo.service;

import com.github.pagehelper.PageInfo;
import com.jackaroo.spring_boot_demo.pojo.MedicalDetail;
import com.jackaroo.spring_boot_demo.util.MedicalDetailQueryBean;
import com.jackaroo.spring_boot_demo.util.PageQueryBean;
import com.jackaroo.spring_boot_demo.util.page.Pagination;
import com.jackaroo.spring_boot_demo.vo.MedicalDetailVo;

/**
 * @author JackarooZhang
 * @date 2018/6/10 20:50
 */
public interface IMedicalDetailService {

    Pagination<MedicalDetailVo> getMedicalDetailConditionally(MedicalDetailQueryBean queryBean, PageQueryBean pageQueryBean);

}
