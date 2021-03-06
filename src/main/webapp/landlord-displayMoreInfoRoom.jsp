<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 1/1/2022
  Time: 2:48 AM
  To change this template use File | Settings | File Templates.
--%>

<!--BOOTSTRAP JS-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<a href="https://icons8.com/icon/549/washing-machine"></a>
<a href="https://icons8.com/icon/NhCGK7IM0kV0/bedroom"></a>
<a href="https://icons8.com/icon/X8cu52egiLH0/air-conditioner"></a>
<a href="https://icons8.com/icon/scdenDaW3DCS/bath"></a>

<style><%@include file="landlord-displayMoreInfo.css"%></style>
<head>
    <title>House More Info Page</title>
</head>
<body>

<%@include file="landlord-navbar.html"%>

<sql:setDataSource var="ic" driver="oracle.jdbc.driver.OracleDriver" url="jdbc:oracle:thin:@localhost:1521:XE" user="NRS" password="system"/>

<sql:query dataSource="${ic}" var="oc">
    <%
        int jhouseid = Integer.parseInt(request.getParameter("hid"));
    %>
    <c:set var="jhouseid" value="<%=jhouseid%>"/>
    SELECT HOUSEPUBLISHDATE,HOUSENAME,HOUSEMONTHLYPRICE,HOUSEADDRESS,HOUSELOCATION,HOUSEAVAILIBILITY,HOUSENOTOILET,HOUSENOAC,HOUSEWIFI,HOUSEFURNITURE,HOUSEWM,HOUSEDESCRIPTION,HOUSEPICNAME,HOUSEID,LANDLORDID
    FROM HOUSEDETAILS
    WHERE HOUSEID=?
    <sql:param value="${jhouseid}" />
</sql:query>

<c:forEach var="result" items="${oc.rows}">
    <div class="showgrid">
        <div class="topic">${result.housename}</div>


        <form action="" method="post" id="theForm">
            <div>
                <input type="number" id="hid" name="hid" value="${result.houseid}" hidden/>
                <input type="number" id="landid" name="landid" value="${result.landlordid}" hidden/>
                <input type="hidden" name="action" value="updRoom"/>
            </div>
            <div class="mybtn">
                <button formaction="landlord-displayBookingList.jsp" type="submit">Booking</button>
                <button formaction="landlord-updateRoomDetails.jsp" type="submit">Edit</button>
                <button type="submit" formaction="LAdeleteHouseDetails" onclick="return confirm('Are you sure you wish to delete? Your action cannot be undone!');">Delete</button>
            </div>
        </form>


        <div class="tintedbg2">
            <div class="k">
                <div class="colk">
                    <div class="imagex">
                            <%-- display image by house-id and image array --%>
                        <div class="w3-content w3-display-container">
                                <%-- kene ada foreach klau nk display bnyk image nnti --%>
                            <img class="mySlides" src="images/${result.housepicname}" style="width:100%">

                            <button class="w3-button w3-black w3-display-left" onclick="plusDivs(-1)">&#10094;</button>
                            <button class="w3-button w3-black w3-display-right" onclick="plusDivs(1)">&#10095;</button>
                        </div>
                    </div>
                </div>

                <div class="coll">
                    <div class="overflow-auto">
                        <div class="mytable">
                            <table>
                                <tr>
                                    <td colspan="2">Publish Date </td>
                                    <td colspan="3">${result.housepublishdate}</td>
                                </tr>
                                <tr>
                                    <td colspan="2">Address</td>
                                    <td colspan="3">${result.houseaddress}</td>
                                </tr>
                                <tr>
                                    <td colspan="2">Location</td>
                                    <td colspan="3">${result.houselocation}</td>
                                </tr>
                                <tr>
                                    <td colspan="2">Price (per month) RM</td>
                                    <td colspan="3">${result.HOUSEMONTHLYPRICE}</td>
                                </tr>
                                <tr>
                                    <td colspan="2">House Availability</td>
                                    <td colspan="3">${result.HOUSEAVAILIBILITY}</td>
                                </tr>
                                <tr>
                                    <td colspan="2">Wifi Availability</td>
                                    <c:set var = "wifiAv" scope = "session" value = "${result.HOUSEWIFI}"/>
                                    <c:if test = "${wifiAv == 'Available'}">
                                        <td colspan="3">Available</td>
                                    </c:if>
                                    <c:if test = "${wifiAv == 'Not Available'}">
                                        <td colspan="3">Not Available</td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td style="text-align: center"><img src="https://img.icons8.com/ios-glyphs/40/000000/shower-and-tub.png"/></td>
                                    <td style="text-align: center"><img src="https://img.icons8.com/fluency-systems-filled/40/000000/air-conditioner.png"/></td>
                                    <td style="text-align: center"><img src="https://img.icons8.com/ios-glyphs/40/000000/washing-machine.png"/></td>
                                    <td style="text-align: center"><img src="https://img.icons8.com/ios-filled/40/000000/furniture.png"/></td>
                                </tr>
                                <tr>
                                    <td text-align="center">Bathroom</td>
                                    <td text-align="center">Air Conditioner</td>
                                    <td text-align="center">Washing Machine</td>
                                    <td text-align="center">Furniture</td>
                                </tr>
                                <tr>
                                    <td>${result.HOUSENOTOILET}</td>
                                    <td>${result.HOUSENOAC}</td>
                                    <td>${result.HOUSEWM}</td>
                                    <td>${result.HOUSEFURNITURE}</td>
                                </tr>
                                <tr>
                                    <td colspan="2">House Description</td>
                                    <td colspan="3">${result.HOUSEDESCRIPTION}</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br>
    </div>
</c:forEach>

<script>


    function booking(){
        location.href = "landlord-displayBookingList.jsp";
    }


    //for slideimage show
    var slideIndex = 1;
    showDivs(slideIndex);

    function plusDivs(n) {
        showDivs(slideIndex += n);
    }

    function showDivs(n) {
        var i;
        var x = document.getElementsByClassName("mySlides");
        if (n > x.length) {
            slideIndex = 1
        }
        if (n < 1) {
            slideIndex = x.length
        }
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        x[slideIndex - 1].style.display = "block";
    }
</script>

</body>
</html>