<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 31/12/2021
  Time: 8:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style><%@include file="landlord-displayHouseList.css"%></style>
<head>
    <title>House List Page</title>
</head>
<body>
<div class="mymenu"><%@include file="landlord-navbar.html"%></div>
<div class="TT">
     <p>YOUR HOUSE LIST</p>
</div>
<br>


<sql:setDataSource var="ic" driver="oracle.jdbc.driver.OracleDriver" url="jdbc:oracle:thin:@localhost:1521:XE" user="NRS" password="system"/>

<sql:query dataSource="${ic}" var="oc">
    SELECT HOUSEID,HOUSEPICNAME,HOUSENAME,RENTYPE
    FROM HOUSEDETAILS
    WHERE LANDLORDID=1
</sql:query>

<div class="overflow-auto">
    <c:forEach var="result" items="${oc.rows}">
        <c:set var="houseid" scope="application" value="${result.houseid}"/>
        <div class="Hcont">
            <form method="post">
                <input type="number" id="hid" name="hid" value="${result.houseid}" hidden/>
                <div class="housepic">
                    <img src="images/${result.housepicname}"/>
                </div>  <%--Nnti letak data sql using scrplet --%>
                <div class="houseName">
                    <p><c:out value="${result.housename}"/></p>  <%--Nnti letak data sql using scrplet --%>
                </div>
                <c:set var="hr" scope="application" value="${result.RENTYPE}"/>
                <c:if test="${hr=='House'}">
                    <div class="myLink">
                        <button type="submit" formaction="landlord-displayMoreInfoHouse.jsp">View More</button>
                    </div>
                </c:if>
                <c:if test="${hr=='Room'}">
                    <div class="myLink">
                        <button type="submit" formaction="landlord-displayMoreInfoRoom.jsp">View More</button>
                    </div>
                </c:if>
            </form>
        </div>
    </c:forEach>
</div>


    <div class="C" id="Chouse">
        <button onclick="z()">
	 <span class="button__icon">
	 <ion-icon name="add"></ion-icon>
	 </span>
            <br>
            <span class="button__text">Create</span>
        </button>
    </div>

</div>
<br>
<br>
<script src="https://unpkg.com/ionicons@4.5.10-0/dist/ionicons.js"></script>





<script type="text/javascript">
    <%--kene ada sql untuk specifickan id rumah mana nak display dalam page ni--%>
    function x() {
        location.href = "landlord-displayMoreInfoHouse.jsp";
    }

    function z() {
        location.href = "landlord-RoomHouse.jsp";
    }
</script>

</body>
</html>
