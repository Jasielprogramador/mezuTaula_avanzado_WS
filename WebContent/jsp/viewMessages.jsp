<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*,helper.info.*" %>
<% ArrayList<MessageInfo> messageList = (ArrayList) request.getAttribute("messageList"); %>
   
<html>
	<head>
		<title>View Messages</title>
	</head>
	<body>
		<table>
			<tr>
   				<th>Username</th>
   				<th>Message</th>
   			</tr>
			<% for(int i = 0; i < messageList.size(); i++) {
				MessageInfo messageInfo = messageList.get(i); %>
	   			<tr>
	   				<td><%= messageInfo.getUsername() %></td>
	   				<td><%= messageInfo.getMessage() %></td>
	   			</tr>
	   		<% } %>
		</table>

	</body>
</html>