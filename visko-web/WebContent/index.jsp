<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"
	import="edu.utep.trustlab.visko.web.html.Template" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="visko-style.css" />
<title>A VisKo Server</title>
</head>
<body>
<div id="container">

<%= Template.getHeader() %>

<div id="content">

<h2>Visualization Query Submission</h2>
<form action="ViskoServletManager">
    <input type="hidden" name="requestType" value="execute-query" /> 
	<textarea style="width: 989px; height: 152px" id="queryText" name="query"></textarea>
	<br>
	<input type="submit" id="submitButton" style="width: 156px">
</form>

<p>Click <a href="query.jsp">here</a> for a list of query examples.</p>

<h2>VisKo server resources:</h2>
<ul>
    <li><a href="query.jsp">Query Submission</a>: allows users to generate visualizations by submitting queries.</li>
    <li><a href="knowledge-base.jsp">Knowledge Base:</a> a description of the capabilities of the knowledge base supporting this VisKo instance.</li> 
</ul>

<h2>This VisKo server knows about the following objects:</h2>
<iframe width="900" height="290" src="InstanceBarGraph.html" frameborder="0"></iframe>
</div>
</div>

<%= Template.getFooter() %>

</body>
</html>