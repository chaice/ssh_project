<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="/static/css/bootstrap.min.css" rel="stylesheet">
<link href="/static/css/navbar.css" rel="stylesheet">
<link rel="stylesheet" href="/static/css/font-awesome.min.css">
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">CC医疗</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="${param.menu == 'home'?'active':''}"><a href="/"><i class="fa fa-home"></i> 主页</a></li>
                <li class="${param.menu == 'patient'?'active':''}"><a href="/patient"><i class="fa fa-user-md"></i> 病人档案</a></li>
                <li class="${param.menu == 'record'?'active':''}"><a href="/record"><i class="fa fa-stethoscope"></i> 就诊记录</a></li>
                <li><a href="#"><i class="fa fa-bell"></i> 复诊提醒</a></li>
                <li><a href="#"><i class="fa fa-bar-chart"></i> 数据统计</a></li>
                <li class="dropdown ${param.menu == 'setting'?'active':''}">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-cogs"></i> 系统设置 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/office"><i class="fa fa-h-square"></i> 科室设置</a></li>
                        <li><a href="/ill"><i class="fa fa-plus-square"></i> 病种设置</a></li>
                        <li><a href="/insurance"><i class="fa fa-navicon"></i> 医保类型设置</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/user"><i class="fa fa-edit"></i> 账号设置</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href=""><i class="fa fa-cog"></i> 个人设置</a></li>
            </ul>
        </div>
    </div>
</nav>
