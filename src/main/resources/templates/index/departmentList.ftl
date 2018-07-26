<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>部门列表</title>
    <link rel="stylesheet" type="text/css" href="../../static/admin/layui/css/layui.css" />
    <link rel="stylesheet" type="text/css" href="../../static/admin/css/admin.css" />
</head>

<body>
<div class="wrap-container clearfix">
    <div class="column-content-detail">
        <div class="layui-form-item">
            <div class="layui-inline tool-btn">
                <button class="layui-btn layui-btn-small layui-btn-normal addBtn" data-url="medicalCateAdd.html"><i class="layui-icon">&#xe654;</i></button>
            </div>
        </div>
        <div class="layui-form" id="table-list">
            <table class="layui-table" lay-even lay-skin="nob">
                <colgroup>
                    <col class="hidden-xs" width="50">
                    <col class="hidden-xs" width="100">
                    <col>
                    <col>
                    <col>
                </colgroup>
                <thead>
                <tr>
                    <th class="hidden-xs">ID</th>
                    <th>名称</th>
                    <th>描述</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="hidden-xs">1</td>
                    <td>校内门诊</td>
                    <td>在学校医院内的消费</td>
                    <td><button class="layui-btn layui-btn-mini layui-btn-normal">正常</button></td>
                    <td>
                        <div class="layui-inline">
                            <button class="layui-btn layui-btn-small layui-btn-normal editBtn" data-id="1" data-url="medicalCateAdd.html"><i class="layui-icon">&#xe642;</i></button>
                            <button class="layui-btn layui-btn-small layui-btn-danger del-btn" data-id="1" data-url="article-detail.html"><i class="layui-icon">&#xe640;</i></button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="page-wrap">
                <ul class="pagination">
                    <li class="disabled"><span>«</span></li>
                    <li class="active"><span>1</span></li>
                    <li>
                        <a href="#">2</a>
                    </li>
                    <li>
                        <a href="#">»</a>
                    </li>
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
            parent.page("修改医疗费类别", url, iframeObj, w = "700px", h = "620px");
            return false;
        })

    });
</script>
</body>

</html>