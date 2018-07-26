package com.jackaroo.spring_boot_demo.web.controller;

import com.github.pagehelper.PageInfo;
import com.jackaroo.spring_boot_demo.pojo.MedicalCategory;
import com.jackaroo.spring_boot_demo.service.IMedicalCategoryService;
import com.jackaroo.spring_boot_demo.service.IMedicalDetailService;
import com.jackaroo.spring_boot_demo.util.Constant;
import com.jackaroo.spring_boot_demo.util.MedicalDetailQueryBean;
import com.jackaroo.spring_boot_demo.util.PageQueryBean;
import com.jackaroo.spring_boot_demo.util.page.Pagination;
import com.jackaroo.spring_boot_demo.vo.MedicalDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author JackarooZhang
 * @date 2018/6/10 20:43
 */
@Controller
public class CostController {

    @Autowired
    private IMedicalDetailService iMedicalDetailService;

    @Autowired
    private IMedicalCategoryService iMedicalCategoryService;

    @RequestMapping("/costAdd.html")
    public ModelAndView costAdd(@RequestParam(value = "medical_id", required = false) Long medicalId) {
        ModelAndView mv = new ModelAndView("index/costAdd");

        if (medicalId != null) {
            // 修改
        } else {
            // 新增
        }
        List<MedicalCategory> medicalCategoryList = iMedicalCategoryService.getMedicalCategoryByStatus(Constant.CommonStatus.NORMAL);

        mv.addObject("medicalCateList", medicalCategoryList);
        mv.addObject("statusList", Constant.CommonStatus.values());
        return mv;
    }

    @RequestMapping("/costList.html")
    public ModelAndView costList(@RequestParam(value = "keywords", required = false) String keywords
            , @RequestParam(value = "medical_cate_id", required = false) Long medicalCateId
            , @RequestParam(value = "status", required = false) Integer status
            , @RequestParam(value = "page", defaultValue = "1") Integer pageNumber
            , @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {

        ModelAndView mv = new ModelAndView("index/costList");
        PageQueryBean pageQueryBean = new PageQueryBean(pageNumber, pageSize);
        MedicalDetailQueryBean queryBean = new MedicalDetailQueryBean(keywords, medicalCateId, status);

        Pagination<MedicalDetailVo> pagination = iMedicalDetailService.getMedicalDetailConditionally(queryBean, pageQueryBean);

        List<MedicalCategory> medicalCategoryList = iMedicalCategoryService.getMedicalCategoryByStatus(Constant.CommonStatus.NORMAL);


        mv.addObject("costList", pagination.getPageData());
        mv.addObject("pagination", pagination);
        mv.addObject("keywords", keywords);
        mv.addObject("medicalCateId", medicalCateId);
        mv.addObject("medicalCateList", medicalCategoryList);
        mv.addObject("statusList", Constant.CommonStatus.values());
        mv.addObject("status", status);
        return mv;
    }

}
