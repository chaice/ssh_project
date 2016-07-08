<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="main-sidebar">
    <section class="sidebar">
        <ul class="sidebar-menu">
            <shiro:hasAnyRoles name="经理,员工">
            <li class="header"></li>
            <li class="active"><a href="#"><i class="fa fa-link"></i> <span>首页</span></a></li>
            <li><a href="#"><i class="fa fa-link"></i> <span>公告</span></a></li>
            <li><a href="#"><i class="fa fa-link"></i> <span>项目管理</span></a></li>
            <li><a href="#"><i class="fa fa-link"></i> <span>客户管理</span></a></li>
            <li><a href="#"><i class="fa fa-link"></i> <span>待办事项</span></a></li>
            <li><a href="#"><i class="fa fa-link"></i> <span>文档管理</span></a></li>
            </shiro:hasAnyRoles>
            <shiro:hasRole name="管理员">
            <li class="treeview">
                <a href="#"><i class="fa fa-link"></i><span>系统管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"><!--TODO--></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="#">员工管理</a></li>
                    <li><a href="#">系统设置</a></li>
                </ul>
            </li>
            </shiro:hasRole>
        </ul>
    </section>
</aside>
