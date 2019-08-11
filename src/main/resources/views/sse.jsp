<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSE</title>
<script type="text/javascript" src="webjars/jquery/jquery.js"></script>
<script type="text/javascript">
if(!!window.EventSource){
	var source = new EventSource('push');
	s = '';
	source.addEventListener('message', function(e){
		s += e.data + "<br >";
		$('#msgFromPush').html(s);
	});
	
	source.addEventListener('open', function(e){
		console.log("链接打开");
	}, false);
	
	source.addEventListener('error', function(e){
		if(e.readyState == EventSource.CLOSED){
			console.log("链接关闭");
		}else {
			console.log(e.readyState);
		}
	}, false);
}else {
	console.log("你的浏览器不支持SSE");
}
</script>
</head>
<body>
<div id="msgFromPush"></div>
</body>
</html>