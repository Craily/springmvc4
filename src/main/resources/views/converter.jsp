<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HttpMessageConverter</title>
<script type="text/javascript" src="webjars/jquery/jquery.js"></script>
<script type="text/javascript">
$(function(){
	req();
});
function req(){
	$.ajax({
		url: 'convert',
		data: '1-test',
		type: 'POST',
		contentType: 'application/x-wisely',
		success: function(date){
			alert(date);
		}
	});
}
</script>
</head>
<body>

</body>
</html>