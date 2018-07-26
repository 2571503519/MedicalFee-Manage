package com.jackaroo.spring_boot_demo.util.page;

/**
 * @author JackarooZhang
 * @date 2018/6/11 8:43
 * 分页配置类
 */
public class PageConfig {

    private Long totalRows;

    private Integer pageSize;

    private Long totalPages;
    /*页签需要的基础URL*/
    private String baseUrl;
    /*显示页签的数量*/
    private Integer tabListSize;

    private PageConfig(Long totalRows, Integer pageSize, String baseUrl, Integer tabListSize) {
        this.totalRows = totalRows;
        this.pageSize = pageSize;
        this.baseUrl = baseUrl;

        // 计算总页数
        this.totalPages = totalRows % pageSize == 0 ?
                totalRows/pageSize : totalRows/pageSize + 1;
        if (totalPages < tabListSize)
            this.tabListSize = Math.toIntExact(totalPages);
        else
            this.tabListSize = tabListSize;
    }

    public static PageConfig create(Long totalRows, Integer pageSize, String baseUrl, Integer tabListSize) {
        return new PageConfig(totalRows, pageSize, baseUrl, tabListSize);
    }

    public static PageConfig create(Long totalRows, String baseUrl) {
        return new PageConfig(totalRows, 10, baseUrl, 5);
    }

    public Integer getTabListSize() {
        return tabListSize;
    }


    public String getBaseUrl() {
        return baseUrl;
    }

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalPages() {
        return totalPages;
    }
}
