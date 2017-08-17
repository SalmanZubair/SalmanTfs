<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false" %>


<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Sonoco</title>
 
<!------ CSS ------>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!--   <link rel="stylesheet" href="/resources/demos/style.css"> -->
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
  

<!-- <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script> -->

<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/bootstrap-toggle.min.css" rel="stylesheet">
<!-- custom css -->
<link href="resources/css/style.css" rel="stylesheet">
<link href="resources/css/responsive.css" rel="stylesheet">

<!-- Font awesome css -->
<link rel="stylesheet" href="resources/css/font-awesome.css">

<!-- Simple Line icon css -->
<link rel="stylesheet" href="resources/css/simple-line-icons.css">

<!--  Slider -->
<link href="resources/css/slider.css" rel="stylesheet">

<!-- Font -->
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">


</head>

<body id="boilerHealthPage"> 
<!--  waiting task  -->
<div id="wait"
		style="display: none; width: 100%; height: 100%; top: 100px; left: 0px; position: fixed; z-index: 10000; text-align: center;">
		<img src="resources/images/ajax-loader.gif" width="45" height="45"
			alt="Loading..." style="position: fixed; top: 50%; left: 50%;" />
	</div>
<input type="hidden" id="boilerId" value="${boilerId}" />
<div class="col-lg-12">
  <div class="col-lg-8">
  
  <header>
      <nav class="navbar navbar-default top_nav">
  <div class="container-fluid "> 
  <div class="navbar-header"> <a class="navbar-brand" href="#" title="Blockchain">&nbsp;</a> </div>   
  <div><a href="" class="col-lg-12 bckBtn location goToMapPage"><span>&#8592;</span> Map</a></div>
  <div class="navbar-collapse collapse" id="searchbar">
  
  <div class="input-group displayPlantSelection" >
           <!--  <select class="plantNamee">
                <option>Plantname 1</option>
                <option>Plantname 2</option>
            </select>
            
            <select class="slectBoiler">
                <option>Boiler 1</option>
                <option>Boiler 2</option>
            </select> 
            <em class="globe"></em> -->
</div>
  
  
      <!-- <div class="input-group">
        <input type="text" class="form-control" id="listOfPlants" onselect ="getListOfBoilersInfoByPlantId()" onchange="getListOfBoilersInfoByPlantId()" placeholder="Search for Plant Name ...">
        <span class="input-group-addon listOfBoilerIds">
        
        </span>
        </div> -->
      <!-- /input-group --> 
</div>
  
  
    <div class="nav_menu navbar-right">
      <ul class="nav navbar-nav ">
        
        <!-- Search Icon  -->
        <!--<li class="dropdown searchMenu circleIcon"> 
         
          <a href="#" class="dropdown-toggle searchClick"> <i class="icon-magnifier icons topNotification"></i> </a> 
          </li>-->
        
        <!-- Alert Icon -->
        <!--<li class="dropdown circleIcon hidden-lg"> <a id="button2" href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">  
          <em class="icon alertsIcon"></em> <span class="label">10</span> </a>
        </li>-->
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
    </header>
  
  
    <!-- <header>
      <div class="logo"><img src="resources/images/logo.jpg" alt=""/></div>
      <a href="" class="profile"> <img src="resources/images/user.jpg" width="84" height="88" alt=""/>
        <dl>
          <dt>Edmond Halley</dt>
          <dd>Plant Engineer</dd>
        </dl>
      </a>
    </header> -->
     
    	
     
    
    <div class="col-lg-12 breadcrumContainer">
		<div class="col-lg-10 paddingLeftNone">
    		<ol class="col-lg-12 breadcrumb locationOl paddingLeftNone">
    		</ol>
    	</div>	
    	<div class="col-lg-2">
    		<div class="toggle toggleButton"><input type="checkbox" checked data-toggle="toggle" data-style="ios"></div>
    	</div>
    </div>
    
    <div>
      <div class="right efficiencyDate"></div>
      <ul class="nav nav-tabs realTimeData">
        <li classname = "boilerHealth" class="active"><a data-toggle="tab" >Boiler Health</a></li>
     <!--    <li><a data-toggle="tab" href="#dataAnalytics">Data Analytics</a></li> -->
      </ul>
    </div>
    <div class="tab-content">
      <div id="boilerHealth" class="tab-pane fade in  active boilerHealth ">
      <div class = "efficiencyCategory"></div>
        <!-- <ul>
          <li>
            <dl>
              <dt>Boiler Efficiency</dt>
              <dd>76% <span class="arrowheight">&#8595;</span> <em>Boiler Efficiency</em> </dd>
            </dl>
          </li>
          <li>
            <dl>
              <dt class="darkRed"><img src="resources/images/critical.png" width="16" height="16" alt=""/> Critical</dt>
              <dd>19% <span class="green arrowheight">&#8595;</span> <em>Dry Gas losses</em> </dd>
            </dl>
          </li>
          <li>
            <dl>
              <dt class="yellow"><img src="resources/images/major.png" width="16" height="15" alt=""/> Major</dt>
              <dd>5% <span class="darkRed arrowheight">&#8593;</span> <em>Losses due to moistue in fuel</em> </dd>
            </dl>
          </li>
        </ul> -->
        <div class="parameterDetails">
        	<h2>Parameter Details<!-- <span>SENSOR GENERATED</span> --></h2>
            <ul class = "parameterDetailsCategory">
             <!-- <li>
                <div class="progress">
                  <div class="progress-bar progress-bar-success" role="progressbar" style="width:50%">
                    Normal
                  </div>
                  <div class="progress-bar progress-bar-danger" role="progressbar" style="width:50%">
                    Warning
                  </div>
				</div>
                </li>
                
                
              
                <li>
                <div class="progress">
                <input id="ex1" data-slider-id='ex1Slider' type="text" data-slider-min="0" data-slider-max="20" data-slider-step="1" data-slider-value="14"/>
                  <div class="progress-bar progress-bar-success" role="progressbar" style="width:50%">
                    Normal
                  </div>
                  <div class="progress-bar progress-bar-danger" role="progressbar" style="width:50%">
                    Warning
                  </div>
				</div>
                </li>
                <li>L DG [ % ] = 0.001044 X ( TStack - 70 ) <span class="output">16.7%</span></li>
            --> </ul>
            <div class="staticValue manualValue1"></div>
            <div class="staticValue manualValue2">
            	<!-- <table >
            		<tr>
            			<th>Parameter</th>
            			<th>Value</th>
            		</tr>
            		<tr>
            			<td>H20</td>
            			<td>-</td>
            		</tr>
            		<tr>
            			<td>H20</td>
            			<td>-</td>
            		</tr>
            		<tr>
            			<td>H20</td>
            			<td>-</td>
            		</tr>
            		<tr>
            			<td>H20</td>
            			<td>-</td>
            		</tr>
            	</table> -->
            </div>
            
            <div class="formula showFormula">Show Formula</div>
            <!-- <div>L DG [ % ] = 0.001044 X ( TStack - 70 )</div> -->
        </div>    
        <div class="boilerHealthAnalysis">
          <h2>BOILER HEALTH ANALYSIS<span></span></h2>
          <ul class="nav nav-tabs timeCategory">
            <li className = "today" class="active"><a data-toggle="tab" href="">Today</a></li>
            <li className = "weekly"><a data-toggle="tab" href="">Weekly</a></li>
            <li className = "month"><a data-toggle="tab" href="">Month</a></li>
          </ul>
          <div class="tab-content">
            <div id="today" class="tab-pane fade in active ">
           	<!--  <img src="resources/images/graph.gif" alt=""/>  -->
           	<div id="chart_div" style="clear:both"></div> 
            </div>
            <!-- <div id="weekly" class="tab-pane fade">
              <h2>weekly</h2>
            </div>
            <div id="month" class="tab-pane fade">
              <h2>month</h2>
            </div> -->
          </div>
        </div>
      </div>
     <!--  <div id="dataAnalytics"  class="tab-pane fade dataAnalytics">
        <h2>Data Analytics</h2>
      </div> -->
    </div>
  </div>
  <div class="col-lg-4 greyBg alertsDiv">
  <div class = "alertCount">  <h3>Alerts <span></span></h3> </div>
    <ul class="nav nav-tabs alertTab">
      <li classname = "all" class="active"><a data-toggle="tab" textDiv="all" href="#all">All</a></li>
      <li classname="critical"><a data-toggle="tab" textDiv="critical" href="#critical">Critical</a></li>
      <li classname="major"><a data-toggle="tab" textDiv="major" href="#major">Major</a></li>
    </ul>
   <div class="tab-content">
      <div id="all" class="tab-pane fade in  all AlertCategory active">
       <!-- 	<div class="filterBy">
        	<label><img src="resources/images/calendar.png" width="16" height="16" alt=""/> Filter By</label>
            <select>
           	  <option>Losses due to moisture in the air </option>
            </select>
        </div>
        <div class="boilerHealthAlert">
          <h4>20<sup>th</sup> Jan 2017</h4>
          <dl>
            <dt class="darkRed"><img src="resources/images/critical.png" width="16" height="16" alt=""/>CRITICAL<span>4:45pm</span></dt>
            <dd>Losses due to moisture in the air</dd>
            <dt></dt>
            <dd>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</dd>
            <dt class="blue"><img src="resources/images/suggestion.png" width="16" height="17" alt=""/>SUGGESTION</dt>
            <dd>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</dd>
          </dl>
          <h4>20<sup>th</sup> Jan 2017</h4>
          <dl>
            <dt class="red"><img src="resources/images/major.png" width="16" height="15" alt=""/>Major <span>4:45pm</span></dt>
            <dd>Losses due to moisture in the air</dd>
            <dt></dt>
            <dd>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</dd>
            <dt class="blue"><img src="resources/images/suggestion.png" width="16" height="17" alt=""/>SUGGESTION</dt>
            <dd>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</dd>
          </dl>
          <h4>20<sup>th</sup> Jan 2017</h4>
          <dl>
            <dt class="darkRed"><img src="resources/images/critical.png" width="16" height="16" alt=""/>CRITICAL<span>4:45pm</span></dt>
            <dd>Losses due to moisture in the air</dd>
            <dt></dt>
            <dd>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</dd>
            <dt class="blue"><img src="resources/images/suggestion.png" width="16" height="17" alt=""/>SUGGESTION</dt>
            <dd>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</dd>
          </dl>
          <h4>20<sup>th</sup> Jan 2017</h4>
          <dl>
            <dt class="red"><img src="resources/images/major.png" width="16" height="15" alt=""/>Major <span>4:45pm</span></dt>
            <dd>Losses due to moisture in the air</dd>
            <dt></dt>
            <dd>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</dd>
            <dt class="blue"><img src="resources/images/suggestion.png" width="16" height="17" alt=""/>SUGGESTION</dt>
            <dd>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</dd>
          </dl>
          <h4>20<sup>th</sup> Jan 2017</h4>
          <dl>
            <dt class="darkRed"><img src="resources/images/critical.png" width="16" height="16" alt=""/>CRITICAL<span>4:45pm</span></dt>
            <dd>Losses due to moisture in the air</dd>
            <dt></dt>
            <dd>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</dd>
            <dt class="blue"><img src="resources/images/suggestion.png" width="16" height="17" alt=""/>SUGGESTION</dt>
            <dd>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</dd>
          </dl>
          <h4>20<sup>th</sup> Jan 2017</h4>
          <dl>
            <dt class="red"><img src="resources/images/major.png" width="16" height="15" alt=""/>Major <span>4:45pm</span></dt>
            <dd>Losses due to moisture in the air</dd>
            <dt></dt>
            <dd>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</dd>
            <dt class="blue"><img src="resources/images/suggestion.png" width="16" height="17" alt=""/>SUGGESTION</dt>
            <dd>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</dd>
          </dl>
        </div>-->
      </div>
      <div id="critical" class="tab-pane fade critical  AlertCategory">
        <h2>Critical</h2>
      </div>
      <div id="major" class="tab-pane fade major AlertCategory">
        <h2>Major</h2>
        
        <input name="surgery_id" id="surgeryId"
													type="text" placeholder="Please enter surgery id">
        
      </div>
    </div> 
  </div>
</div>
<!-- Scripts -->
<!-- Line graph -->
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
 

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- <script src="resources/js/jquery.min.js"></script> -->

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/bootstrap-toggle.min.js"></script>


<script src="resources/js/boiler-health.js"></script>
<script src="resources/js/common.js"></script>


</body>
</html>