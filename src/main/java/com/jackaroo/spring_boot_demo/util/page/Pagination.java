package com.jackaroo.spring_boot_demo.util.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JackarooZhang
 * @date 2018/6/11 8:42
 * 分页类
 */
public class Pagination<T> {

    private PageTab firstTab;

    private PageTab lastTab;

    private PageTab prevTab;

    private PageTab nexTab;

    private List<PageTab> tabList;

    private List<T> pageData;

    private PageConfig pageConfig;

    private Long currentPageNumber;

    private boolean isFirstPage;

    private boolean isLastPage;

    private Pagination(PageConfig pageConfig, Long currentPageNumber) {
        this.pageConfig = pageConfig;
        // 初始化Pagination内部成员变量
        initPagination(currentPageNumber);
    }

    private void initPagination(Long currentPageNumber) {
        if (pageConfig == null) {
            return;
        }
        firstTab = new PageTab(1L);
        lastTab = new PageTab(1L);
        prevTab = new PageTab(1L);
        nexTab = new PageTab(1L);
        firstTab.setActivated(false);
        lastTab.setActivated(false);
        prevTab.setActivated(false);
        nexTab.setActivated(false);
        tabList = new ArrayList<>(pageConfig.getTabListSize());
        PageTab p = null;
        for (int i = 0; i < pageConfig.getTabListSize(); i++) {
            p = new PageTab((long) (i + 1));
            p.setActivated(false);
            p.render(pageConfig);
            tabList.add(p);
        }
        // 初始默认激活第一页
        setCurrentPageNumber(currentPageNumber);
    }

    public static Pagination create(PageConfig pageConfig) {
        if (pageConfig == null) throw new IllegalArgumentException("分页配置对象参数不能为null！");
        return new Pagination(pageConfig, 1L);
    }

    public void setCurrentPageNumber(long currentPageNumber) {
        long curPN = currentPageNumber;
        // 边界检测
        if (curPN < 1 || curPN > pageConfig.getTotalPages()) {
            throw new IllegalArgumentException("当前页码设置超出范围！");
        }
        this.currentPageNumber = currentPageNumber;

        // 需要显示的页签数目
        int tabListSize = pageConfig.getTabListSize();
        // 总页数
        long totalPages = pageConfig.getTotalPages();

        isFirstPage = currentPageNumber == 1;
        isLastPage = currentPageNumber == totalPages;

        // 确定需要激活的页签在页签列表中的位置
        int activatedLocation = 1;
        if (curPN - 1 < tabListSize/2 + 1) {
            activatedLocation = (int) curPN;
        } else if (pageConfig.getTotalPages() - curPN < tabListSize/2) {
            activatedLocation = (int) (tabListSize - (totalPages - curPN));
        } else {
            activatedLocation = (tabListSize % 2 == 0 ? tabListSize / 2 : tabListSize/2 + 1);
        }
        // 分页开始页码
        long start = curPN - activatedLocation + 1;
        // 构造页签列表
        for (int i = 0; i < tabListSize; i++) {
            PageTab pageTab = tabList.get(i);
            pageTab.setActivated(false);
            pageTab.setPageNumber(start + i);
            if (i == activatedLocation - 1) {
                pageTab.setActivated(true);
            }
            pageTab.render(pageConfig);
        }

        // 设置首尾页、上一页、下一页
        firstTab.setPageNumber(1L);
        lastTab.setPageNumber(totalPages);
        if (curPN - 1 > 1) prevTab.setPageNumber(curPN - 1);
        else prevTab.setPageNumber(1L);
        if (curPN + 1 < totalPages) nexTab.setPageNumber(curPN + 1);
        else nexTab.setPageNumber(totalPages);

        firstTab.render(pageConfig);
        lastTab.render(pageConfig);
        prevTab.render(pageConfig);
        nexTab.render(pageConfig);
    }

    private static Logger logger = LoggerFactory.getLogger(Pagination.class);

    public static void main(String[] args) {
        // 1. 构造分页配置对象
        PageConfig config = PageConfig.create(10001L, 11, "/index.html?p=123", 6);
        PageConfig config2 = PageConfig.create(10001L, 10, "/home.html?p=fff", 9);
        PageConfig config3 = PageConfig.create(999L, 10, "/user.html?p=fff", 3);
        // 2. 构造分页类
        Pagination pagination = Pagination.create(config);
        // 3. 设置当前页码(不设置，默认为第一页)
        pagination.setCurrentPageNumber(908);
        pagination.setPageConfig(config2);
        pagination.setPageConfig(config3);
//        pagination.setCurrentPageNumber(2);

        PageTab firstTab = pagination.getFirstTab();
        PageTab lastTab = pagination.getLastTab();
        PageTab prevTab = pagination.getPrevTab();
        PageTab nextTab = pagination.getNexTab();
        List<PageTab> tabList = pagination.getTabList();
        logger.info("首页页码：{} URL：{} 是否激活：{}", firstTab.getPageNumber(), firstTab.getUrl(), firstTab.getActivated());
        logger.info("尾页页码：{} URL：{} 是否激活：{}", lastTab.getPageNumber(), lastTab.getUrl(), lastTab.getActivated());
        logger.info("上一页页码：{} URL：{} 是否激活：{}", prevTab.getPageNumber(), prevTab.getUrl(), prevTab.getActivated());
        logger.info("下一页页码：{} URL：{} 是否激活：{}", nextTab.getPageNumber(), nextTab.getUrl(), nextTab.getActivated());
        tabList.stream().forEach(e -> {
            logger.info("显示页码：{} URL：{} 是否激活：{}", e.getPageNumber(), e.getUrl(), e.getActivated());
        });
        logger.info("总页数：{}, 总记录数：{}", config2.getTotalPages(), config2.getTotalRows());
    }

    public boolean getIsFirstPage() {
        return isFirstPage;
    }

    public boolean getIsLastPage() {
        return isLastPage;
    }

    public Long getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setPageConfig(PageConfig pageConfig) {
        this.pageConfig = pageConfig;
        for (PageTab tab : tabList) {
            if (tab.getActivated()) {
                if (tab.getPageNumber() < 1 || tab.getPageNumber() > pageConfig.getTotalPages())
                    initPagination(1L);
                else
                    initPagination(tab.getPageNumber());
            }
        }
    }

    public PageConfig getPageConfig() {
        return pageConfig;
    }

    public PageTab getFirstTab() {
        return firstTab;
    }


    public PageTab getLastTab() {
        return lastTab;
    }


    public PageTab getPrevTab() {
        return prevTab;
    }

    public PageTab getNexTab() {
        return nexTab;
    }

    public void setTabList(List<PageTab> tabList) {
        this.tabList = tabList;
    }

    public List<PageTab> getTabList() {
        return tabList;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }
}
