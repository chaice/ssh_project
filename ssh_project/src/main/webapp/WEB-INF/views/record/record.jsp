<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增就诊记录</title>
    <link rel="stylesheet" href="/static/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/simditor/styles/simditor.css">
    <link rel="stylesheet" href="/static/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="/static/css/webuploader.css">
    <link rel="stylesheet" href="/static/css/easy-autocomplete.min.css">
    <link rel="stylesheet" href="/static/css/easy-autocomplete.themes.min.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <jsp:include page="../include/head.jsp">
        <jsp:param name="menu" value="record"/>
    </jsp:include>

    <div class="box box-primary container">
        <div class="box-header">
            <h4><i class="fa fa-user"></i> 患者信息</h4>
        </div>
        <div class="box-body">
            <table class="table">
                <tr>
                    <th style="width: 100px">姓名</th>
                    <td style="width: 100px"><input id="basics"/></td>
                    <th style="width: 100px">性别</th>
                    <td style="width: 100px" id="sex"></td>
                    <th style="width: 100px">年龄</th>
                    <td style="width: 100px" id="age"></td>
                </tr>
                <tr>
                    <th style="width: 100px">身份证号</th>
                    <td style="width: 100px" id="idnumber"></td>
                    <th style="width: 100px">联系电话</th>
                    <td style="width: 100px" id="telnum"></td>
                    <th style="width: 100px">医保类型</th>
                    <td style="width: 120px" id="insurance"></td>
                </tr>
                <tr>
                    <th style="width: 100px">地址</th>
                    <td colspan="5" id="address"></td>
                </tr>
                <tr>
                    <th colspan="6">过敏史</th>
                </tr>
                <tr>
                    <td colspan="6" id="history"></td>
                </tr>
                <tr>
                    <th colspan="6">备注</th>
                </tr>
                <tr>
                    <td colspan="6" id="note"></td>
                </tr>
            </table>
        </div>
    </div>

    <div class="box box-info container">
        <div class="box-header">
            <h4><i class="fa fa-medkit"></i> 病历信息</h4>
        </div>
        <div class="box-body">
            <form action="/record/add" method="post" id="record_add">
                <input type="hidden" name="patient.id" id="patientid">
                <input type="hidden" name="file.id" id="fileid">
                <div class="form-inline form-group">
                    <label class="form-group">科室</label>
                    <select name="office.id" class="form-control" style="width: 160px">
                        <option value="">请选择科室</option>
                        <c:forEach items="${officeList}" var="office">
                            <option value="${office.id}">${office.officename}</option>
                        </c:forEach>
                    </select>
                    &nbsp;
                    <label class="form-group">病种</label>
                    <select name="ill.id"  class="form-control" style="width: 160px">
                        <option value="">请选择病种</option>
                        <c:forEach items="${illList}" var="ill">
                            <option value="${ill.id}">${ill.illname}</option>
                        </c:forEach>
                    </select>
                </div>
                <label class="form-group">初步诊断</label>
                <input type="text" class="form-control" name="initlog">
                <label class="form-group">主要症状</label>
                <textarea class="form-control textarea" name="mainlog"></textarea>
                <label class="form-group">相关病史</label>
                <textarea class="form-control textarea" name="relatelog"></textarea>
                <label class="form-group">阳性体征</label>
                <textarea class="form-control textarea" name="positivesign"></textarea>
                <label class="form-group">检查结果</label>
                <textarea class="form-control textarea" name="checkresult"></textarea>
                <label class="form-group">治疗方案</label>
                <textarea class="form-control textarea" name="treatproject"></textarea>
                <br/>
                <div class="form-group form-inline">
                    <label class="form-group">管床医生</label>
                    <input type="text" class="form-control" name="dutydoctor">
                    <label class="form-group">下次复诊时间</label>
                    <input type="text" class="form-control" id="picker" name="nexttime">
                </div>
            </form>
            <div class="form-group">
                <label class="form-group">影像资料</label>
                <div id="filePicker">选择图片</div>
            </div>
            <div class="well well-sm">
                <button class="btn btn-success" id="save">保存</button>
            </div>
        </div>
    </div>
    <script src="/static/js/jquery-2.2.3.min.js"></script>
    <script src="/static/simditor/scripts/module.min.js"></script>
    <script src="/static/simditor/scripts/hotkeys.min.js"></script>
    <script src="/static/simditor/scripts/uploader.min.js"></script>
    <script src="/static/simditor/scripts/simditor.min.js"></script>
    <script src="/static/js/bootstrap-datetimepicker.min.js"></script>
    <script src="/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="/static/js/webuploader.min.js"></script>
    <script src="/static/js/jquery.easy-autocomplete.min.js"></script>
    <script src="/static/js/select2.min.js"></script>
    <script src="/static/js/zh-CN.js"></script>
    <script>
        $(function () {
            $('select').select2({
                language:"zh-CN"
            });
            var editor = new Simditor({
                toolbar:['title', 'bold', 'italic','underline','strikethrough','ol','ul','blockquote','table','link','hr','indent','outdent'],
                textarea: $('.textarea')
            });
            $("#basics").easyAutocomplete({
                url:"/record/patient.json",
                getValue:function (element) {
                    return element.patientname;
                },
                list:{
                    onSelectItemEvent:function () {
                        var id = $("#basics").getSelectedItemData().id;
                        var sex = $("#basics").getSelectedItemData().sex.sexname;
                        var idnumber = $("#basics").getSelectedItemData().idnumber;
                        var age = $("#basics").getSelectedItemData().age;
                        var telnum = $("#basics").getSelectedItemData().telnum;
                        var insurance = $("#basics").getSelectedItemData().insurance.insname;
                        var address = $("#basics").getSelectedItemData().address;
                        var history = $("#basics").getSelectedItemData().history;
                        var note = $("#basics").getSelectedItemData().note;
                        $("#patientid").val(id);
                        $("#sex").text(sex);
                        $("#idnumber").text(idnumber);
                        $("#age").text(age);
                        $("#telnum").text(telnum);
                        $("#insurance").text(insurance);
                        $("#address").text(address);
                        $("#history").text(history);
                        $("#note").text(note);
                    }
                }
            });
            $("#picker").datetimepicker({
                format:"yyyy-mm-dd",
                minView:"month",
                todayBtn:true,
                autoclose: true,
                language:"zh-CN"
            });
            var uploader = WebUploader.create({
                auto: true,
                swf: "/static/Uploader.swf",
                server: '/record/addfile',
                pick: '#filePicker',
                fileVal:"file",
                accept: {
                    title: 'Images',
                    extensions: 'gif,jpg,jpeg,bmp,png',
                    mimeTypes: 'image/*'
                }
            });
            uploader.on("uploadProgress",function(file){

            });
            uploader.on("uploadSuccess",function(file){

            });
            uploader.on("uploadError",function(file){
                alert("文件上传失败!")
            });
            uploader.on("uploadAccept",function(object,ret){
               $("#fileid").val(ret);
            })
            $("#save").click(function () {
                $("#record_add").submit();
            })

        })
    </script>
</body>
</html>
