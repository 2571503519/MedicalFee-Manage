<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>医疗费报销列表</title>
    <link rel="stylesheet" type="text/css" href="../../static/admin/layui/css/layui.css" />
    <link rel="stylesheet" type="text/css" href="../../static/admin/css/admin.css" />
</head>

<body>
<div class="wrap-container clearfix">
    <div class="column-content-detail">
        <form class="layui-form" action="/costList.html" method="post">
            <div class="layui-form-item">
                <div class="layui-inline tool-btn">
                    <button class="layui-btn layui-btn-small layui-btn-normal addBtn" data-url="costAdd.html"><i class="layui-icon">&#xe654;</i></button>
                    <button class="layui-btn layui-btn-small layui-btn-danger delBtn"  data-url="#"><i class="layui-icon">&#xe640;</i></button>
                </div>
                <div class="layui-inline">
                    <input type="text" name="keywords" value="${keywords!""}" placeholder="请输入职工姓名或者工号" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-inline">
                    <select name="medical_cate_id" lay-filter="status">
                        <option value="">请选择医疗费类别</option>
                        <option value="">请选择医疗费类别</option>
                        <#list medicalCateList as medicalCate>
                            <option value="${medicalCate.medicalCateId}" <#if ((medicalCateId!-1) == medicalCate.medicalCateId)>selected</#if> >${medicalCate.cateName}</option>
                        </#list>
                    </select>
                </div>
                <div class="layui-inline">
                    <select name="status" lay-filter="status">
                        <option value="">请选择一个状态</option>
                        <option value="">请选择一个状态</option>
                        <#list statusList as statusItem>
                            <option value="${statusItem.id}" <#if ((status!-1) == statusItem.id)>selected</#if> >${statusItem.status}</option>
                        </#list>
                    </select>
                </div>
                <button class="layui-btn layui-btn-normal" lay-submit="search">搜索</button>
            </div>
        </form>
        <div class="layui-form" id="table-list">
            <table class="layui-table" lay-even lay-skin="nob">
                <colgroup>
                    <col width="50">
                    <col class="hidden-xs" width="50">
                    <col class="hidden-xs" width="100">
                    <col>
                    <col>
                    <col>
                    <col>
                    <col class="hidden-xs" width="150">
                    <col class="hidden-xs" width="150">
                    <col width="80">
                    <col width="150">
                </colgroup>
                <thead>
                <tr>
                    <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
                    <th class="hidden-xs">ID</th>
                    <th>单号</th>
                    <th>描述</th>
                    <th>报销额度</th>
                    <th>报销类别</th>
                    <th>姓名</th>
                    <th>工号</th>
                    <th class="hidden-xs">报销时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <#list costList as cost>
                <tr>
                    <td><input type="checkbox" name="" lay-skin="primary" data-id="${cost.medicalId}"></td>
                    <td class="hidden-xs">${cost_index+1 + (pagination.currentPageNumber-1) * pagination.pageConfig.pageSize}</td>
                    <td>${cost.medicalNumber}</td>
                    <td>${cost.desc}</td>
                    <td>${cost.amount}</td>
                    <td>${cost.medicalCategory.cateName}</td>
                    <td>${cost.employee.name}</td>
                    <td>${cost.employee.empNumber}</td>
                    <td class="hidden-xs">${cost.updateTime?date}</td>
                    <td>
                        <#if (cost.status=="正常")>
                            <button class="layui-btn layui-btn-mini layui-btn-normal">正常</button>
                        </#if>
                        <#if (cost.status=="删除")>
                            <button class="layui-btn layui-btn-danger layui-btn-mini layui-btn-normal">删除</button>
                        </#if>
                    </td>
                    <td>
                        <div class="layui-inline">
                            <button class="layui-btn layui-btn-small layui-btn-normal editBtn" data-id="1" data-url="costAdd.html"><i class="layui-icon">&#xe642;</i></button>
                            <button class="layui-btn layui-btn-small layui-btn-danger del-btn" data-id="1" data-url="article-detail.html"><i class="layui-icon">&#xe640;</i></button>
                        </div>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
            <div class="page-wrap">
                <ul class="pagination">
                    <#if pagination.isFirstPage>
                    <li class="disabled"><span>«</span></li>
                    <#else >
                    <li><span><a href="${pagination.prevTab.url}">«</a></span></li>
                    </#if>
                    <#list pagination.tabList as tab>
                    <#if tab.activated>
                    <li class="active">
                        <span>${tab.pageNumber}</span>
                    </li>
                    <#else >
                     <li>
                         <a href="${tab.url}">${tab.pageNumber}</a>
                     </li>
                    </#if>
                    </#list>
                    <#if pagination.isLastPage>
                    <li class="disabled"><span>»</span></li>
                    <#else >
                    <li><span><a href="${pagination.nexTab.url}">»</a></span></li>
                    </#if>
                </ul>
            </div>
        </div>
    </div>
</div>
<script src="../../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../../static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
<script>
    layui.use(['jquery'], function() {
        var $=layui.jquery;
        //获取当前iframe的name值
        var iframeObj = $(window.frameElement).attr('name');
        $('.editBtn').click(function () {
            var url=$(this).attr('data-url');
            var dataId = $(this).attr('data-id');
            //将iframeObj传递给父级窗口
            parent.page("报销医疗费", url, iframeObj, w = "700px", h = "620px");
            return false;
        })
        /*$('.editBtn').on('click', '.editBtn', function() {
            alert(22);
            var url=$(this).attr('data-url');
            //将iframeObj传递给父级窗口
            parent.page("修改职工信息", url, iframeObj, w = "700px", h = "620px");
            return false;
        })*/
    });
</script>
</body>

</html>