<#setting date_format="yyyy-MM-dd"/>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>职工列表</title>
    <link rel="stylesheet" type="text/css" href="../../static/admin/layui/css/layui.css" />
    <link rel="stylesheet" type="text/css" href="../../static/admin/css/admin.css" />
</head>

<body>
<div class="wrap-container clearfix">
    <div class="column-content-detail">
        <form class="layui-form" action="/employeeList.html" method="post">
            <div class="layui-form-item">
                <div class="layui-inline tool-btn">
                    <button class="layui-btn layui-btn-small layui-btn-normal addBtn" data-url="employeeAdd.html"><i class="layui-icon">&#xe654;</i></button>
                    <button class="layui-btn layui-btn-small layui-btn-danger delBtn"  data-url="#"><i class="layui-icon">&#xe640;</i></button>
                </div>
                <div class="layui-inline">
                    <input type="text" name="keywords" value="${keywords!""}" placeholder="请输入职工姓名或者工号" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-inline">
                    <select name="dept_id" lay-filter="status">
                        <option value="">请选择部门</option>
                        <option value="">请选择部门</option>
                        <#list departmentList as department>
                            <option value="${department.deptId}" <#if ((deptId!-1) == department.deptId)>selected</#if> >${department.deptName}</option>
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
                    <col class="hidden-xs" width="100">
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
                    <th class="hidden-xs">序号</th>
                    <th>姓名</th>
                    <th class="hidden-xs">工号</th>
                    <th>所属部门</th>
                    <th>报销额度</th>
                    <th class="hidden-xs">创建时间</th>
                    <th class="hidden-xs">修改时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <#list employeeVoList as employeeVo>
                <tr>
                    <td><input type="checkbox" name="" lay-skin="primary" data-id="${employeeVo.empId}"></td>
                    <td class="hidden-xs">${employeeVo_index+1 + (pageInfo.pageNumber-1) * pageInfo.pageSize}</td>
                    <td>${employeeVo.name}</td>
                    <td>${employeeVo.empNumber}</td>
                    <td>${employeeVo.department.deptName}</td>
                    <td>${employeeVo.balance}</td>
                    <td class="hidden-xs">${employeeVo.createTime?date}</td>
                    <td class="hidden-xs">${employeeVo.updateTime?date}</td>
                    <td>
                        <#if (employeeVo.status=="正常")>
                            <button class="layui-btn layui-btn-mini layui-btn-normal">正常</button>
                        </#if>
                        <#if (employeeVo.status=="禁止")>
                            <button class="layui-btn layui-btn-warm layui-btn-mini layui-btn-normal">禁止</button>
                        </#if>
                        <#if (employeeVo.status=="删除")>
                            <button class="layui-btn layui-btn-danger layui-btn-mini layui-btn-normal">删除</button>
                        </#if>
                    </td>
                    <td>
                        <div class="layui-inline">
                            <button class="layui-btn layui-btn-small layui-btn-normal editBtn" data-id="${employeeVo.empId}" data-url="employeeAdd.html"><i class="layui-icon">&#xe642;</i></button>
                            <#if (employeeVo.status=="删除")>
                            <button class="layui-btn layui-btn-disabled layui-btn-small layui-btn-danger" data-id="${employeeVo.empId}" data-url="delEmployee.do"><i class="layui-icon">&#xe640;</i></button>
                            <#else>
                            <button class="layui-btn layui-btn-small layui-btn-danger del-Btn" data-id="${employeeVo.empId}" data-url="delEmployee.do"><i class="layui-icon">&#xe640;</i></button>
                            </#if>
                        </div>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
            <div class="page-wrap">
                <ul class="pagination">
                    <#if (pageInfo.pageNumber == pageInfo.first)>
                    <li class="disabled"><span>«</span></li>
                    <#else>
                    <li><span><a href="/employeeList.html?page=${pageInfo.prev}">«</a></span></li>
                    </#if>
                    <li class="active"><span>${pageInfo.pageNumber}</span></li>
                    <#if (pageInfo.pageNumber+1 <= pageInfo.last)>
                        <li>
                            <a href="/employeeList.html?page=${pageInfo.pageNumber+1}">${pageInfo.pageNumber+1}</a>
                        </li>
                    </#if>
                    <#if (pageInfo.pageNumber+2 <= pageInfo.last)>
                        <li>
                            <a href="/employeeList.html?page=${pageInfo.pageNumber+2}">${pageInfo.pageNumber+2}</a>
                        </li>
                    </#if>
                    <#if (pageInfo.pageNumber == pageInfo.last)>
                    <li class="disabled"><span>»</span></li>
                    <#else>
                    <li><span><a href="/employeeList.html?page=${pageInfo.next}">»</a></span></li>
                    </#if>
                </ul>
            </div>
        </div>
    </div>
</div>
<script src="../../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../../static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
<script>
    layui.use(['jquery', 'dialog'], function() {
        var $=layui.jquery;
        var dialog = layui.dialog;
        //获取当前iframe的name值
        var iframeObj = $(window.frameElement).attr('name');
        $('.editBtn').click(function () {
            var url=$(this).attr('data-url');
            var dataId = $(this).attr('data-id');
            url += "?emp_id=" + dataId;
            //将iframeObj传递给父级窗口
            parent.page("修改职工信息", url, iframeObj, w = "700px", h = "620px");
            return false;
        });
        $('.del-Btn').click(function() {
            var url=$(this).attr('data-url');
            var dataId = $(this).attr('data-id');
            var page = $(".active span")[0].innerHTML;
            dialog.confirm({
                message:'您确定要删除选中项',
                success:function(){
                    $.post(url, {empIds: dataId}, function (data) {
                        if (data.status == 1001) {
                            // 删除成功
                            layer.msg(data.msg, function () {
                                location.href = "/employeeList.html?page=" + page;
                            });
                        } else {
                            layer.msg(data.msg);
                        }
                    });
                    return;
                },
                cancel:function(){
                    layer.msg('取消了')
                }
            })
            return false;
        });
    });
</script>
</body>

</html>