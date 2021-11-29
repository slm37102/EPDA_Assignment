<%-- 
    Document   : newjsp
    Created on : Nov 21, 2021, 9:05:53 PM
    Author     : SLM
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
 
<%
    HttpSession s = request.getSession();
    Map<String, Integer> account = (Map<String, Integer>)s.getAttribute("account");
    Map<String, Integer> gender = (Map<String, Integer>)s.getAttribute("gender");
    Map<Integer, Integer> dose = (Map<Integer, Integer>)s.getAttribute("dose");
    Map<String, Integer> vacPerDay = (Map<String, Integer>)s.getAttribute("vacPerDay");
    Map<String, Integer> vacStatus = (Map<String, Integer>)s.getAttribute("vacStatus");
%>
 
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
window.onload = function() { 
    
    var options1 = {
      chart: {
        type: 'bar'
      },
      series: [{
        name: 'accounts',
        data: [
            <c:forEach items="${account.values()}" var="i" varStatus="status">  
                ${i}<c:if test="${!status.last}">,</c:if>   
            </c:forEach>
        ]
      }],
      title: {
        text: "Bar chart of the Distribution of Accounts",
      },
      xaxis: {
        categories: [
            <c:forEach items="${account.keySet()}" var="i" varStatus="status">  
                '${i}'<c:if test="${!status.last}">,</c:if>   
            </c:forEach>
        ],
         title: {
          text: "Types of Account"
        }
      },
      yaxis: {
        title: {
          text: "Number of Accounts"
        }
      }
    };

    var options2 = {
      chart: {
        type: 'bar'
      },
      series: [{
        name: 'Number of Public User',
        data: [
            <c:forEach items="${gender.values()}" var="i" varStatus="status">  
                ${i}<c:if test="${!status.last}">,</c:if>   
            </c:forEach>
        ]
      }],
      labels: [
            <c:forEach items="${gender.keySet()}" var="i" varStatus="status">  
                '${i}'<c:if test="${!status.last}">,</c:if>   
            </c:forEach>
        ],
      title: {
        text: "Bar chart of Distribution of Public User's gender",
      },
      xaxis: {
         title: {
          text: "Gender"
        }
      },
      yaxis: {
        title: {
          text: "Number of Public User"
        }
       }
    };
    
    var options3 = {
      chart: {
        type: 'bar'
      },
      series: [{
        name: 'sales',
        data: [
            <c:forEach items="${dose.values()}" var="i" varStatus="status">  
                ${i}<c:if test="${!status.last}">,</c:if>   
            </c:forEach>
        ]
      }],
      title: {
        text: "Bar chart of distribution of each dose",
      },
      xaxis: {
        categories: [
            <c:forEach items="${dose.keySet()}" var="i" varStatus="status">  
                '${i}'<c:if test="${!status.last}">,</c:if>   
            </c:forEach>
        ],
        title: {
          text: "Dose"
        }
      },
      yaxis: {
        title: {
          text: "Number of people taken dose"
        }
       }
      
    };
    var options4 = {
      chart: {
        type: 'line'
      },
      series: [{
        name: 'People Vaccined',
        data: [
            <c:forEach items="${vacPerDay.keySet()}" var="i" varStatus="status">
                { x: '${i}', y:${vacPerDay.get(i)} }<c:if test="${!status.last}">,</c:if>   
            </c:forEach>
        ]
      }],
      title: {
        text: "Number of dose taken per day",
      },
        xaxis: {
          type: "datetime",
          title: {
            text: "Date"
          }
        },
      yaxis: {
        title: {
          text: "Number of dose taken"
        }
       }
    };
    var options5 = {
      chart: {
        type: 'bar'
      },
      series: [{
        name: 'person',
        data: [
            <c:forEach items="${vacStatus.values()}" var="i" varStatus="status">  
                ${i}<c:if test="${!status.last}">,</c:if>   
            </c:forEach>
        ]
      }],
      title: {
        text: "Bar chart of Vaccine Status",
      },
      xaxis: {
        categories: [
            <c:forEach items="${vacStatus.keySet()}" var="i" varStatus="status">  
                '${i}'<c:if test="${!status.last}">,</c:if>   
            </c:forEach>
        ],
        title: {
          text: "Vaccine Status"
        }
      },
      yaxis: {
        title: {
          text: "Number of people"
        }
       }
    };
    
    var chart1 = new ApexCharts(document.querySelector("#chart1"), options1);
    chart1.render();
    var chart2 = new ApexCharts(document.querySelector("#chart2"), options2);
    chart2.render();
    var chart3 = new ApexCharts(document.querySelector("#chart3"), options3);
    chart3.render();
    var chart4 = new ApexCharts(document.querySelector("#chart4"), options4);
    chart4.render();
    var chart5 = new ApexCharts(document.querySelector("#chart5"), options5);
    chart5.render();

};
</script>
</head>
<body>
    <div id="chart1" style="height: 370px; width: 70%; margin:0 auto;" ></div>
    <div id="chart2" style="height: 370px; width: 70%; margin:0 auto;"></div>
    <div id="chart3" style="height: 370px; width: 70%; margin:0 auto;"></div>
    <div id="chart4" style="height: 370px; width: 70%; margin:0 auto;"></div>
    <div id="chart5" style="height: 370px; width: 70%; margin:0 auto;"></div>
    <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
</body>
</html>           