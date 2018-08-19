<html>
<head>
    <title>${title!"default"}</title>
</head>
<body>
学生列表：
<table border="1">
    <#list students as student>
        <#if student_index % 2 == 0>
            <tr style="color: red">
        <#else>
            <tr>
        </#if>
            <td>${student_index}</td>
            <td>${student.id}</td>
            <td>${student.name}</td>
        </tr>
    </#list>
</table>
当前日期：${curDate?date}<br>
${curDate?time}<br>
${curDate?datetime}<br>
${curDate?string("yyyy/MM/dd HH:mm:ss")}<br>
</body>
</html>