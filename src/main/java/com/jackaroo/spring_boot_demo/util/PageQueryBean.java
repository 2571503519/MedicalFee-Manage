package com.jackaroo.spring_boot_demo.util;

/**
 * @author JackarooZhang
 * @date 2018/6/8 10:26
 */
public class PageQueryBean {

    private Integer prev;

    private Integer next;

    private Integer first;

    private Integer last;

    private Integer pageNumber;

    private Integer pageSize;

    private Long total;

    public PageQueryBean() {
        pageNumber = 1;
        pageSize = 5;
    }

    public PageQueryBean(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Integer getPrev() {
        return prev;
    }

    public void setPrev(Integer prev) {
        this.prev = prev;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
        first = 1;
        if ( (total % pageSize) > 0) {
            last = Math.toIntExact(total / pageSize + 1);
        } else {
            last = Math.toIntExact(total / pageSize);
        }
        prev = (pageNumber - 1) < first ? first : pageNumber - 1;
        next = (pageNumber + 1) > last ? last : pageNumber + 1;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
