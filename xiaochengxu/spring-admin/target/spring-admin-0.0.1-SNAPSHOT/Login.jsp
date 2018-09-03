<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="user" method="post">
		name<input type="text" name="name"/>
		word<input type="text" name="word"/>
		<input type="submit" value="注册"/>
	</form>
	
	<form action="common/springUpload" enctype="multipart/form-data" method="post">
        <table>
            <tr>
                <td>请上传头像:</td>
                <td><input type="file" name="file"></td>
            </tr>
            <tr>
                <td><input type="submit" value="提交"></td>
            </tr>
        </table>
    </form>
</body>
</html>