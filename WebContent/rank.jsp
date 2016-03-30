<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Candidate Ranking</title>
</head>
<body>
<form action="saveRank">
Candidate id<input type="text" name="candidateId"><br>
<br>
Rank<input type="text" name="rank"><br>
<br>
<input type="submit" name="save" value="Save">

</form>
${msg}<br>
<br>
<br>
<br>
<a href="showDetail">See Students Rank</a>
</body>
</html>