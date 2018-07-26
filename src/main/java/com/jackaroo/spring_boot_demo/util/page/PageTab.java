package com.jackaroo.spring_boot_demo.util.page;

/**
 * @author JackarooZhang
 * @date 2018/6/11 8:42
 * 页签类
 */
public class PageTab {

    /*页码*/
    private Long pageNumber;
    /*页签链接*/
    private String url;
    /*是否激活该页签*/
    private Boolean activated;

    public PageTab(Long pageNumber) {
        if (pageNumber < 1) throw new IllegalArgumentException("页码不能小于1!");
        this.pageNumber = pageNumber;
    }


    public void render(PageConfig pageConfig) {
        if (pageConfig == null) throw new IllegalArgumentException("PageConfig 不能为空");
        this.url = pageConfig.getBaseUrl() + "&page=" + pageNumber + "&pageSize=" + pageConfig.getPageSize();
    }

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }
}
