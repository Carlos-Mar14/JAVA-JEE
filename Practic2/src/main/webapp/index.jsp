<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400..700&display=swap" rel="stylesheet">
    <style>
        /* Use a unique and descriptive class name */
        .dancing-script-custom {
            font-family: "Dancing Script", cursive;
            font-optical-sizing: auto;
            font-weight: 400; /* Use a value from 400 to 700 */
            font-style: normal;
        }
    </style>
</head>
<body >
    <p class="dancing-script-custom">
        Tu navegador es <%= request.getHeader("user-agent") %>
    </p>
    <h1>
    	Lorem40
    </h1>
    <h2>
    	Lorem50
    </h2>
    <h3>
    	lorem39
    </h3>
</body>
</html>
