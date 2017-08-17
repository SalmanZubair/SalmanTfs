<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Sonoco</title>

<!------ CSS ------>

<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<!-- custom css -->
<link href="resources/css/style.css" rel="stylesheet">
<link href="resources/css/responsive.css" rel="stylesheet">

<!-- Font awesome css -->
<link rel="stylesheet" href="resources/css/font-awesome.css">

<!-- Simple Line icon css -->
<link rel="stylesheet" href="resources/css/simple-line-icons.css">

<!-- custom scrollbar stylesheet -->
<link rel="stylesheet" href="resources/css/jquery.mCustomScrollbar.css">

<script type="text/javascript" src="resources/js/jquery.min.js"></script>


<!-- custom scrollbar plugin -->
	<script src="resources/js/jquery.mCustomScrollbar.concat.min.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="resources/js/bootstrap.min.js"></script>
<!-- Scripts -->


    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 100%;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
  </head>
  <body style="padding-top:72px;">
   
  <nav class="navbar navbar-default navbar-fixed-top top_nav">
  <div class="container-fluid ">
    <div class="navbar-header"> <a class="navbar-brand" href="#" title="Blockchain">&nbsp;</a> 
      <!-- <button type="button" class="navbar-toggle collapsed searchIcon" data-toggle="collapse" data-target="#searchbar" aria-expanded="false" aria-controls="searchbar">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
              </button> --> 
    </div>
    <div><a href="" class="col-lg-12 bckBtn location goToMapPage"><span>&#8592;</span> Map</a></div>
    
    <div class="nav_menu navbar-right">
      <ul class="nav navbar-nav ">
        
        <!-- Search Icon  -->
        <li class="dropdown searchMenu circleIcon"> 
          <!-- Menu toggle button --> 
          <a href="#" class="dropdown-toggle searchClick"> <i class="icon-magnifier icons topNotification"></i> </a> </li>
        
        <!-- Alert Icon -->
        <li class="dropdown circleIcon hidden-lg"> <a id="button2" href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">  
          <em class="icon alertsIcon"></em> <span class="label">10</span> </a>
        </li>
        <li class=""> <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> <img src="resources/images/user.jpg" alt=""> <span class="nameTitle">John Doe <em>Plant Technician</em></span> <i class="icon-arrow-down icons"></i> </a>
          <ul class="dropdown-menu dropdown-usermenu pull-right">
            <!--<li><a href="#"><i class="icon-user icons"></i> My Profile</a></li>-->
            <li><a href="#" class="navLogout"><i class="icon-power icons"></i> Log Out</a></li>
          </ul>
        </li>
      </ul>
    </div>
<!--     <div class="navbar-collapse collapse" id="searchbar">
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search for location...">
        <span class="input-group-btn">
        <button class="btn btn-default" type="button"></button>
        </span> </div>       
      /input-group 
    </div> -->
  </div>
</nav>

<div>
<iframe width="100%" height="590px" src="https://app.powerbi.com/view?r=eyJrIjoiNzQxZWYxZDMtN2NlOC00ZDU1LWI3N2ItMGQyYTQ0MDVkNjQ1IiwidCI6IjE4OWRlNzM3LWM5M2EtNGY1YS04YjY4LTZmNGNhOTk0MTkxMiIsImMiOjEwfQ%3D%3D" frameborder="0" allowFullScreen="true"></iframe>
</div>
<!-- <script src="resources/js/index.js"></script> -->
<script src="resources/js/dataAnalysis.js"></script>

<!-- <script src="resources/js/common.js"></script> -->
  </body>
</html>