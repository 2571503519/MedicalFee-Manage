<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>添加职工</title>
    <link rel="stylesheet" type="text/css" href="../../static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="../../static/admin/css/admin.css"/>
</head>
<body>
<div class="layui-tab page-content-wrap">
    <ul class="layui-tab-title">
        <li class="layui-this">添加职工</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <form id="employeeAddForm" class="layui-form"  style="width: 90%;padding-top: 20px;">
                <input type="hidden" name="empId" value="${(employeeInfo.empId)!""}">
                <div class="layui-form-item">
                    <label class="layui-form-label">工号：</label>
                    <div class="layui-input-block">
                        <input type="text" name="empNumber" required  lay-verify="required" autocomplete="off" placeholder="请输入职工工号" class="layui-input" value="${(employeeInfo.empNumber)!""}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">姓名：</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" required  lay-verify="required"  autocomplete="off" placeholder="请输入职工姓名" class="layui-input" value="${(employeeInfo.name)!""}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">单选框</label>
                    <div class="layui-input-block">
                        <input type="radio" name="gender" value="男" title="男" <#if (((employeeInfo.gender)!"男") == "男") >checked</#if>>
                        <input type="radio" name="gender" value="女" title="女" <#if (((employeeInfo.gender)!"") == "女") >checked</#if>>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">联系方式：</label>
                    <div class="layui-input-block">
                        <input type="text" name="phone" required  lay-verify="required" placeholder="请输入常用手机号" autocomplete="off" class="layui-input" value="${(employeeInfo.phone)!""}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">报销额度：</label>
                    <div class="layui-input-block">
                        <input type="text" name="balance" required  lay-verify="required" placeholder="请输入报销额度" autocomplete="off" class="layui-input" value="${(employeeInfo.balance)!""}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">所属部门：</label>
                    <div class="layui-inline">
                        <select name="department.deptId" lay-filter="status">
                            <option value="">请选择部门</option>
                            <option value="">请选择部门</option>
                        <#list departmentList as department>
                            <option value="${department.deptId}" <#if (((employeeInfo.department.deptId)!-1) == department.deptId)>selected</#if> >${department.deptName}</option>
                        </#list>
                        </select>
                    </div>
                </div>

            </form>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button id="submitBtn" class="layui-btn layui-btn-normal">立即提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script>
    //Demo
    layui.use(['form', 'element', 'jquery'], function(){
        var form = layui.form();
        var element = layui.element();
        var $ = layui.jquery;

        form.render();
        //监听信息提交
        $("#submitBtn").click(function () {
            var objArray = $("#employeeAddForm").serializeArray();
            var employeeInfo = {};
            for (var i = 0; i < objArray.length; i++) {
                var name = objArray[i].name;
                var value = objArray[i].value;
                employeeInfo[name] = value;
            }
            if (employeeInfo.empId == "") {
                // 新增
                $.post("/addEmployee.do", employeeInfo, function (res) {
                    if (res.status == 1001) {
                        layer.msg(res.msg);
                        // 清空表单内容
                        $("#employeeAddForm :input").val("");
                    } else {
                        layer.msg(res.msg);
                    }
                }, "JSON");
            } else {
                // 修改
                $.post("/updateEmployeeInfo.do", employeeInfo, function (res) {
                    if (res.status == 1001) {
                        layer.msg(res.msg);
                    } else {
                        layer.msg(res.msg);
                    }
                }, "JSON");
            }
        });
    });
</script>
</body>
</html>