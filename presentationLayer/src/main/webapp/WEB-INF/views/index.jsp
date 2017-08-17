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
	
	<script>
		(function($){
			$(window).on("load",function(){
				
				$("#mapScroll").mCustomScrollbar({
					theme:"minimal"
				});
				
			});
		})(jQuery);
	</script>


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
      .gm-style img { max-width: none; }
       .gm-style label { width: auto; display: inline; }	
       .gmnoprint img {
    	max-width: none; 
	}

	.gmnoprint.gm-bundled-control.gm-bundled-control-on-bottom{
		bottom: 207px !important;
	}
    </style>
  </head>
  <body id="boilerHealthPage">
    <div id="map"></div>
    <script>

      
    </script>
     <script src="resources/js/mapscript.js"></script> 
    <script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js">
    </script>
 <!--   <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC-_xIjxfnkVw6vxtMw7p8urWT91hxsou4&callback=initMap">
    </script>  -->
      <script 
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC-_xIjxfnkVw6vxtMw7p8urWT91hxsou4">
    </script> 
    
	
	<!--<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC-_xIjxfnkVw6vxtMw7p8urWT91hxsou4"></script>    -->
		
		<!--  waiting task  -->
<div id="wait"
		style="display: none; width: 100%; height: 100%; top: 100px; left: 0px; position: fixed; z-index: 10000; text-align: center;">
		<img src="resources/images/ajax-loader.gif" width="45" height="45"
			alt="Loading..." style="position: fixed; top: 50%; left: 50%;" />
	</div>
		
		<nav class="navbar navbar-default navbar-fixed-top top_nav">
  <div class="container-fluid ">
    <div class="navbar-header"> <a class="navbar-brand" href="#" title="Blockchain">&nbsp;</a> 
      <!-- <button type="button" class="navbar-toggle collapsed searchIcon" data-toggle="collapse" data-target="#searchbar" aria-expanded="false" aria-controls="searchbar">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
              </button> --> 
    </div>
    <div class="navbar-collapse collapse" id="searchbar">
     <!--  <div class="input-group">
        <input type="text" class="form-control" placeholder="Search for location...">
        <span class="input-group-btn">
        <button class="btn btn-default" type="button"></button>
        </span> </div> -->
        
         <div class="input-group displayPlantSelection" >
           <!-- <select class="plantNamee">
                <option>Plantname 1</option>
                <option>Plantname 2</option>
            </select>
            
            <select class="slectBoiler">
                <option>Boiler 1</option>
                <option>Boiler 2</option>
            </select> 
            <em class="globe"></em>  -->
</div>
        
        <button  class="dataAnalytic gotoDataAnalysis">Data Analysis</button>
        
        
      <!-- /input-group --> 
    </div>
    <div class="nav_menu navbar-right">
      <ul class="nav navbar-nav ">
        
        <!-- Search Icon  -->
        <li class="dropdown searchMenu circleIcon"> 
          <!-- Menu toggle button --> 
          <a href="#" class="dropdown-toggle searchClick"> <i class="icon-magnifier icons topNotification"></i> </a> </li>
        
        <!-- Notifications Menu -->
        <li class="dropdown circleIcon"> <a id="button2" href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false"> 
          <!--<i class="icon-bell icons topNotification"></i>--> 
          <em class="icon alertsIcon allAlertCount"></em> <span class="label"></span> </a>
           <div id="menu1" class="dropdown-menu list-unstyled msg_list col-md-12 col-xs-12 alertInfo" role="menu">
           <div class="col-lg-12">
              <div class = "alertCount">  <h3>Alerts <span></span></h3> </div>
              <ul class="nav nav-tabs alertTab">
                <li classname = "all" class="active"><a data-toggle="tab"  textDiv="all" href="#all">Critical</a></li>
                <!-- <li classname = "critical" ><a data-toggle="tab" textDiv="critical" href="#critical">Critical</a></li> -->
                <!-- <li classname = "major" ><a data-toggle="tab"  textDiv="major" href="#major">Major</a></li> -->
              </ul>
              <div class="tab-content">
                <div id="all" class="tab-pane fade in all AlertCategory active">
                <!--   <div class="filterBy">
                    <label><img src="resources/images/calendar.png" width="16" height="16" alt=""/> Filter By</label>
                    <select>
                      <option>Losses due to moisture in the air </option>
                    </select>
                  </div>
                  <div class="boilerHealthAlertMain">
                    <h4 class="darkRed"><img src="resources/images/critical.png" width="16"   alt=""/> Critical</h4>
                  <div class="categoryList">
                    <dl>
                      <dt>PLANT NAME</dt>
                      <dd>ADDRESS</dd>
                    </dl>
                    <ul>
                    	<li>
                        	<div class="boilerName">Boiler 1</div>
                        	<span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul>
                    <ul>
                    	<li>
                        	<div class="boilerName">Boiler 1</div>
                        	<span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul>
                   </div>
                   <div class="categoryList">
                    <dl>
                      <dt>PLANT NAME</dt>
                      <dd>ADDRESS</dd>
                    </dl>
                    <ul>
                    	<li>
                        	<div class="boilerName">Boiler 1</div>
                        	<span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul>
                    <ul>
                    	<li>
                        	<div class="boilerName">Boiler 1</div>
                        	<span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul>
                   </div> 
                   
                    <h4 class="yellow"><img src="resources/images/major.png" width="16"   alt=""/> Major</h4>
                   <div class="categoryList">
                    <dl>
                      <dt>PLANT NAME</dt>
                      <dd>ADDRESS</dd>
                    </dl>
                    <ul>
                    	<li>
                        	<div class="boilerName">Boiler 1</div>
                        	<span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul>
                    <ul>
                    	<li>
                        	<div class="boilerName">Boiler 1</div>
                        	<span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul>
                   </div>
                  </div> -->
                  
                  
                </div>
                <div id="critical" class="tab-pane fade critical AlertCategory">
                <!--   <div class="boilerHealthAlertMain">
                    <h4 class="darkRed"><img src="resources/images/critical.png" width="16"   alt=""/> Critical</h4>
                  <div class="categoryList">
                    <dl>
                      <dt>PLANT NAME</dt>
                      <dd>ADDRESS</dd>
                    </dl>
                    <ul>
                    	<li>
                        	<div class="boilerName">Boiler 1</div>
                        	<span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul>
                    <ul>
                    	<li>
                        	<div class="boilerName">Boiler 1</div>
                        	<span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul>
                   </div>
                   <div class="categoryList">
                    <dl>
                      <dt>PLANT NAME</dt>
                      <dd>ADDRESS</dd>
                    </dl>
                    <ul>
                    	<li>
                        	<div class="boilerName">Boiler 1</div>
                        	<span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul>
                    <ul>
                    	<li>
                        	<div class="boilerName">Boiler 1</div>
                        	<span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul>
                   </div> 
                  </div>-->
                </div>
                <div id="major" class="tab-pane fade major AlertCategory ">
                  <!--  <div class="boilerHealthAlertMain"> 
                    <h4 class="yellow"><img src="resources/images/major.png" width="16"   alt=""/> Major</h4>
                  <div class="categoryList">
                    <dl>
                      <dt>PLANT NAME</dt>
                      <dd>ADDRESS</dd>
                    </dl>
                    <ul>
                    	<li>
                        	<div class="boilerName">Boiler 1</div>
                        	<span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul>
                    <ul>
                    	<li>
                        	<div class="boilerName">Boiler 1</div>
                        	<span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul>
                   </div> 
                  </div>-->
                </div>
              </div>
            </div>
          </div>  
        </li>
        <li class=""> <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> <img src="resources/images/user.jpg" alt=""> <span class="nameTitle">John Doe <em>Plant Technician</em></span> <i class="icon-arrow-down icons"></i> </a>
          <ul class="dropdown-menu dropdown-usermenu pull-right">
            <!--<li><a href="#"><i class="icon-user icons"></i> My Profile</a></li>-->
            <li><a href="#" class="navLogout"><i class="icon-power icons"></i> Log Out</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
<div class="col-lg-12 categories hidden-sm hidden-md hidden-xs">
  <h3>Categories</h3>
  <ul>
    <li class ="crticalCount"> <span class="catStatus">Critical</span> <span class="catStatusNum "></span> <img src="resources/images/graphLine.jpg" alt="" /> </li>
    <li class = "normalCount"> <span class="catStatus">Normal</span> <span class="catStatusNum "></span> <img src="resources/images/graphLine.jpg" alt="" /> </li>
   	 <li class="majorCount"> <span class="catStatus">Not Integrated</span> <span class="catStatusNum "></span> <img src="resources/images/graphLine.jpg" alt="" /> </li></ul> 
</div>


<!--- Popup
   <div id="" class="plantDetail">
            <div class="col-lg-12">
              <h3>Plant Name <span>x</span><span>8</span></h3>
               
                  
                  <div class="plantContent">
                    <h4 class="darkRed"><img src="resources/images/critical.png" width="16"   alt=""/> Critical</h4>
                   
                     
                    <ul>
                                <li>
                                <div class="boilerName">Boiler 1</div>
                                <span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul> 
                     
                   <ul>
                                <li>
                                <div class="boilerName">Boiler 1</div>
                                <span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul>
                    <ul>
                                <li>
                                <div class="boilerName">Boiler 1</div>
                                <span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul> 
                   
                    
                   
                   <h4 class="yellow"><img src="resources/images/major.png" width="16"   alt=""/> Major</h4>
                   
                    
                    <ul>
                                <li>
                                <div class="boilerName">Boiler 1</div>
                                <span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul>
                    <ul>
                                <li>
                                <div class="boilerName">Boiler 1</div>
                                <span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul> 
                    
                    <h4 class="green"><span><img src="resources/images/normal.png" width="16"   alt=""/> </span>Normal</h4>
                    <ul>
                                <li>
                                <div class="boilerName">Boiler 1</div>
                                <span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul> 
                    <ul>
                                <li>
                                <div class="boilerName">Boiler 1</div>
                                <span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>
                        <li>08/15/2013</li>
                        <li><input type="button" value="View"></li>
                    </ul>  
                  </div>
                  
                  
                </div>
                
                
              </div>  --> 
              
              <div class="singlePlantDetails">    </div>
<script src="resources/js/index.js"></script>
<!-- <script src="resources/js/common.js"></script> -->
  </body>
</html>