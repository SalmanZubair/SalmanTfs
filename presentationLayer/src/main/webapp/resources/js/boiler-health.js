$(document).ready(function(){ 
	
	$(document).ajaxStart(function() {
		$.fn.blockUI;
		$('#wait').show();
	});
	$(document).ajaxStop(function() {
		$('#wait').hide();
		$.fn.unblockUI
	});
	$(document).ajaxError(function() {
		$('#wait').hide();
	});
	
	/************Global variables************/
	var alertData ;
	var plantOfListNames = [];
	var plantOfListIds = [];
	var criticalData = [];
	var majorData = [];
	var timeStamp ;
	var formula ='';
	
	
	/************************Goto Map Page*******************/
	localStorage.setItem("boilerHealth", true)
	$('.goToMapPage').click(function(){
		window.history.back();
		/*var jspcall="index.jsp" 
			window.location.href=jspcall */
			
/*//			$.ajax({url:"/Sonoco/index", success: function(result){
//
//		    }});
*/		//window.open("index","_self");
	});
	/***********List of all plants*************************/
	
	getListOfAllPlantsInfo = function(){
    	$.ajax({
            url: '/Sonoco/getAllPlants',                         
            type: 'get',
            success: function(res){
            	populateAllPlantList(res);
                },
            error:function(error){
            //alert("Internal Error");
            }
        });
	} 
	
	populateAllPlantList = function(res){
		plantOfListNames = [];
		plantOfListIds = [];
		for(var i =0; i<res.length; i++){
			plantOfListNames.push(res[i].plantName);
			plantOfListIds.push(res[i].plantId)
		}
		var displayPlantSelectionDiv = $('.displayPlantSelection');
		var innerTag = '';
		innerTag += '<select class="plantNamee" onchange="getListOfBoilersInfoByPlantId(this.value)">';
		innerTag += '<option>Select Plant</option>';
		for(var i = 0; i<plantOfListNames.length; i++){
			innerTag += '<option value='+plantOfListIds[i]+'>'+plantOfListNames[i]+'</option>';
		}
		innerTag += ' </select>';
		
		displayPlantSelectionDiv.append(innerTag);
    
		/*$('#listOfPlants').autocomplete({
			source : plantOfListNames,
			select: function(event, ui) {
				var selectedPlant = ui.item.value;
				var index = plantOfListNames.indexOf(selectedPlant)
				if (index >= 0){
		        	  getListOfBoilersInfoByPlantId(plantOfListIds[index],'call');
		          }
	        }
		});*/
	}
	
	getListOfBoilersInfoByPlantId = function(plantId){
		//if(call === 'call'){
		$.ajax({
            url: '/Sonoco/getBoiler?plantId='+plantId,                         
            type: 'get',
            success: function(res){
            	setBoilersList(res);
                },
            error:function(error){
            //alert("Internal Error");
            }
        });
	//}
	}
	
	setBoilersList = function(data){
		var displayPlantSelectionDiv = $('.displayPlantSelection');
		var createInnerTag = '';
		//listOfBoilerIds.html('');
		//createInnerTag += '<span class="input-group-addon">';
		
		//changes for deleteing the select to LI
		/*createInnerTag += '<select class="slectBoiler" onchange="getBoilerDetail(this.value)">';
		createInnerTag += '<option>Select</option>';
		for(var i=0;i<data.length; i++){
			createInnerTag += '<option  value='+data[i].boiler_id+'>'+data[i].boiler_name+'-'+data[i].status+'</option>';
		}
		 += '</select>  ';*/
		var boilerSelect = $('.boilerSelect');
		if(boilerSelect.html() != undefined){
			boilerSelect.html('');
		}
		createInnerTag += '<span class="input-group-addon boilerSelect">';
		createInnerTag += '<input placeholder="Select Boiler" name="" type="text" class="slectBoiler" readonly>'
		createInnerTag += '<ul class="dropBoiler">';
		for(var i=0;i<data.length; i++){
			var bgColor = 'green_background';
			if(data[i].status === 'CRITICAL'){
				bgColor = 'red_background';
			}
			createInnerTag += '<li><a  class=" boiler '+bgColor+'" textDiv = "'+data[i].boiler_id+'">'+data[i].boiler_name+'</a></li>';
		}
		//createInnerTag += '<li><a href="#" class="red_background">Boiler 2</a></li>';
		createInnerTag += '</ul>   </span>'
		createInnerTag += '<em class="globe"></em>';
		displayPlantSelectionDiv.append(createInnerTag);
		  $(".slectBoiler").click(function() {
			$(".dropBoiler").addClass("open");
		});
		$(".dropBoiler a").click(function() {
			$(".dropBoiler").removeClass("open");
			$(".boiler").removeClass("highlight");
			var boilerId = this.getAttribute('textDiv');
			$('.slectBoiler').val(this.text);
			getBoilerDetail(boilerId);
			$(this).addClass('highlight')
		});
		
		/*$(".boiler").click(function() {
			$(".dropBoiler").removeClass("open");
		});
*/
	       
	}
	
	
	getBoilerDetail = function(boilerId){
		$("#boilerId").val(boilerId);
		//var clickedTab = $('.alertTab li.active a')[0].getAttribute('textDiv');
		var clickedTab = 'all'
		getAlerts(clickedTab,'INDIRECT');
		getBoilerHealth('INDIRECT');
		getBoilerInfo();
		 $('.timeCategory li').removeClass('active');
		 $('.timeCategory li')[0].classList.add("active")
		//var timeCategory = $('.timeCategory li.active')[0].getAttribute('className');
		var timeCategory = 'today'
		getValuesForLineGraph(timeCategory,'INDIRECT');
		//getParameter('INDIRECT');
	}
	/******************End of List of all plants and All boilers*******************/
	
	/***********************boiler info starts************************/
	getBoilerInfo = function(){
		var boilerId = $('#boilerId').val();
    	$.ajax({
            url: '/Sonoco/getBoilerInfo?boilerId='+boilerId,                         
            type: 'get',
            success: function(res){
            	setBoilerInfo(res,boilerId);
                },
            error:function(error){
            //alert("Internal Error");
            }
        });
	} 
	setBoilerInfo = function(data){
		var boilerInfo = $('.locationOl');
		boilerInfo.html('');
		//innerTag = '<li class="breadcrumb-item">You are here:</li>';
		var innerTag = '<li class="breadcrumb-item">'+data.plantInfo.location.state.country.countryName+'</li>';
		innerTag += ' <li class="breadcrumb-item">'+data.plantInfo.location.locationName+'</li>';
		innerTag += '<li class="breadcrumb-item active">'+data.plantInfo.plantName+'</li>';
		innerTag += '<li class="breadcrumb-item active">'+data.boilerName+'</li>';
		/*innerTag += '<select>'
		for(var i = 0; i<data.listOfPlants.length; i++){
			innerTag += '<option value='+data.listOfPlants[i]+'>'+data.listOfPlants[i]+'</option>';
			//innerTag += '<option>Plant name 2</option>';
		}
		
		innerTag +=	'</select>'
		innerTag += '<li class="breadcrumb-item">';
		innerTag += '<select>'
		for(var y = 0; y<data.boilerName.length; y++){
			innerTag += '<option value='+data.boilerName[y]+'>'+data.boilerName[y]+'</option>';

		}
		innerTag +=	'</select>';
		innerTag += '</li>';*/
		
	/*	 <li class="breadcrumb-item active">
	      	<select>
	        	<option>Plant name 1</option>
	            <option>Plant name 2</option>
	        </select>
	      </li>
	      <li class="breadcrumb-item">
	      	<select>
	        	<option>Boiler 1</option>
	            <option>Boiler 2</option>
	        </select>
	      </li>*/
		
		
		boilerInfo.append(innerTag);
	}
	
	/****************On click of Toggle*******************/
	
	$(".toggleButton").click(function(){
		//var status = $(".toggleButton .toggle.btn")[0].classList.contains('off');
		var method = '';
		// true means indirect method is called
		if($(".toggleButton .toggle.btn")[0].classList.contains('off')){
			method = 'INDIRECT';
		}
		else{
			method = 'DIRECT';
		}
		
		//var clickedTab = $('.alertTab li.active a')[0].getAttribute('textDiv');
		var clickedTab = 'all';
		getAlerts(clickedTab,method);
		getBoilerHealth(method);
		getBoilerInfo();
	//	var timeCategory = $('.timeCategory li.active')[0].getAttribute('className');
		var  timeCategory = 'today';
		getValuesForLineGraph(timeCategory,method);
		var selectedlink = $('.showFormula')
		selectedlink.html('Show Formula');
		//getParameter(method);
	});
	
	//****************Alert data is populated************************
	$(".alertTab li").click(function(){
		var clickedTab = this.getAttribute("className");
		var method = 'INDIRECT';
		if($(".toggleButton .toggle.btn")[0].classList.contains('off')){
			method = 'DIRECT';
		}
		getAlerts(clickedTab,method);
		$(".alertTab li").removeClass('active');
		this.classList.add("active")
	});
	
	getAlerts = function(clickedTab,method){
		var boilerId = $('#boilerId').val();
		if(clickedTab === 'all'){ 
			$.ajax({
	            url: '/Sonoco/getAlerts?type='+clickedTab+'&boilerId='+boilerId+'&method='+method,                         
	            type: 'get',
	            success: function(res){
	            	criticalData = [];
	            	majorData =[];
	            	setAlertDataOnLoad(res, clickedTab);
	                },
	            error:function(error){
	            //alert("Internal Error");
	            }
	        });
			
		}
		else{
			
			if(clickedTab ==='critical'){
				setAlertDataOnLoad(criticalData,clickedTab);
			}
			else{
				setAlertDataOnLoad(majorData,clickedTab);
			}
		}
    	
	}
	
	setAlertDataOnLoad = function(data,clickedTab){
		$('.AlertCategory').removeClass('active');
		$('.AlertCategory').addClass('fade');
		//var clickedTab = $('.AlertCategory li.active a')[0].getAttribute('textDiv');
		var selectedDiv = $('.' + clickedTab + '.AlertCategory');
		selectedDiv.addClass('active');
		selectedDiv.removeClass('fade');
		var alertsDiv = $('.alertCount' );
		alertsDiv.html('');
		selectedDiv.html('');
		alertsDiv.append('<h3>Alerts <span>'+data.length+'</span></h3>');
		var innerTag = createInnerTagForAlerts(data,clickedTab);
		selectedDiv.append(innerTag);       
		if(data.length > 0){
			addScroll();
		}
		
		//selectedDiv.addClass('active');
	}
	
	createInnerTagForAlerts = function(data,clickedTab){
		var lossArray = [];
		var innerTag ='';
		if(data.length>0){
			alertData = data;
			var lossNameArray = [];
			innerTag = ' <div class="filterBy">'
			innerTag +=' <label><img src="resources/images/calendar.png" width="16" height="16" alt=""/> Filter By</label>'
			innerTag +=' <select onchange="fillterAlert(this.value)">'
			for(var i = 0; i<=data.length; i++){
				if(i === 0){
					innerTag +='  <option value = "all">'+'All'+' </option>'
				}
				else{
					var lossName = data[i-1].alertDesc;
					if(lossNameArray.indexOf(lossName)==-1){
						lossNameArray.push(lossName);
						innerTag +='  <option value = "'+lossName +'">'+lossName+' </option>'
					}
					
				}
				
			}
			
			innerTag +=' </select>'
			innerTag +=' </div>'
			innerTag += '  <div class="boilerHealthAlert">';
			innerTag += alertLoop(data,clickedTab);
			innerTag += ' </div>';
		}
		else{
			innerTag += '<div>No record found </div>'
		}
		return innerTag;
		
		
	}
	
	alertLoop = function(data,lossSelected){
		var innerTag = '';
		for(var i = 0; i<data.length; i++){
			var date = new Date (data[i].insertDate);
			var month = date.toLocaleString("en", { month: "long"  });
			var time = date.toLocaleString("en", { Time: "long"  });
			var str = time.split(",");
			month = month.substring(0, 3);
			innerTag += ' <h4>'+date.getDate()+'<sup>th</sup>'+' '+month+' ' +date.toLocaleString("en", { year: "numeric"})+'</h4>';
			innerTag += ' <dl>';
			var alert = '';
			var imageName = '';
			if(data[i].severity == 0 || data[i].severity == -1){
				if(lossSelected === 'all'){
					criticalData.push(data[i]);
				}
				alert = 'CRITICAL';
				imageName = 'critical';
			}
			else if(data[i].severity == 1){
				if(lossSelected === 'all'){
					majorData.push(data[i]); 
				}
				alert = 'Major';
				imageName = 'major';
			}
			innerTag += ' <dt class="darkRed"><img src="resources/images/'+imageName+'.png" width="16" height="16" alt=""/>'+alert+'<span>'+str[1]+'</span></dt>';
			innerTag += ' <dd>'+data[i].alertDesc +'</dd>';
			innerTag += ' <dt></dt>';
		//	innerTag += ' <dd>'+data[i].alertDesc+'</dd>';
			innerTag += ' <dt class="blue"><img src="resources/images/suggestion.png" width="16" height="17" alt=""/>SUGGESTION</dt>';
			innerTag += ' <dd>'+data[i].suggestion +'</dd>';
			innerTag += ' </dl>';
		}
		if(data.length ==0){
		
		}
		
		return innerTag;
	}
	fillterAlert = function(selectedAlertName){
		var selectedDiv = $('.boilerHealthAlert');
		selectedDiv.html('');
		
		var alertsDiv = $('.alertCount' );
		
		
		var innerTag = '';
		if(selectedAlertName === 'all'){
			innerTag = alertLoop(alertData);
			alertsDiv.html('');
			alertsDiv.append('<h3>Alerts <span>'+alertData.length+'</span></h3>');
		}
		else{
			var selectedLossAlertData = [];
			for(var i=0; i<alertData.length; i++){
				if(alertData[i].alertDesc === selectedAlertName){
					selectedLossAlertData.push(alertData[i]);
				}
				
			}
			//var filterAlert = new Array(selectedLossAlertData);
			alertsDiv.html('');
			alertsDiv.append('<h3>Alerts <span>'+selectedLossAlertData.length+'</span></h3>');
			innerTag = alertLoop(selectedLossAlertData,'lossSelected');
		}
		 
		selectedDiv.append(innerTag);
	}
	
	addScroll = function(){
		var scrollTop = $(window).scrollTop(), 
		elementOffset = $('.boilerHealthAlert, #map').offset().top, 
		distance      = (elementOffset - scrollTop);
		$(window).resize(function() { 
		var w = window, 
		    d = document, 
		    e = d.documentElement, 
		    g = d.getElementsByTagName('body')[0], 
		    x = w.innerWidth|| e.clientWidth|| g.clientWidth, 
		    y = w.innerHeight|| e.clientHeight|| g.clientHeight; 
		    $('.boilerHealthAlert, #map').height(y - distance); 
		}); 
		
		if ($(window).width() < 740)
		{
			$(window).resize(function() {
		var w = window,
		    d = document,
		    e = d.documentElement,
		    g = d.getElementsByTagName('body')[0],
		    x = w.innerWidth || e.clientWidth || g.clientWidth,
		    y = w.innerHeight|| e.clientHeight|| g.clientHeight;
			$('.boilerHealthAlert').height(y - 0)
		});
		//alert("mobile");
		}
		else
		{	
			//alert("Desktop");
		}

		$(window).trigger('resize');  
	}
	//***************Alert data is populated ends***************************
	
	//********************BoilerHealth Dynamic data is populated***********************
	$(".realTimeData li").click(function(){
		
		var clickedTab = this.getAttribute("className");
		if(clickedTab === 'boilerHealth'){
			getBoilerHealth();
		}
	});
	
	getBoilerHealth = function(method){
		var boilerId = $('#boilerId').val();
    	$.ajax({
            url: '/Sonoco/calculateEfficiency?boilerId='+boilerId+'&method='+method,                         
            type: 'get',
            success: function(res){
            	if(res.boiler != undefined){
            		timeStamp = res.boiler.timestamp;
            		getParameter(method,timeStamp);
            		if(method === 'DIRECT'){
            			getManualParams(timeStamp);
            		}
            		else{
            			var manualValue2 = $('.manualValue2');
            			manualValue2.html('');
            		}
            	}
            	
            	setBoilerHealth(res);
                },
            error:function(error){
            //alert("Internal Error");
            }	
        });
	}
	
	setBoilerHealth = function(data){
		var selectedDiv = $('.' + 'efficiencyCategory');
		
		var date = new Date (data.boiler.timestamp);
		var month = date.toLocaleString("en", { month: "long"  });
		var time = date.toLocaleString("en", { Time: "long"  });
		var str = time.split(",");
		month = month.substring(0, 3);
		var effDate = ' <h4>'+date.getDate()+'<sup>th</sup>'+' '+month+' ' +date.toLocaleString("en", { year: "numeric"})+' '+str[1]+'</h4>';
		var efficiencyDate = $('.efficiencyDate');
		efficiencyDate.html('');
		efficiencyDate.append(effDate);
		selectedDiv.html('');
		var innerTag = createInnerTagForBoilerHeath(data);
		selectedDiv.append(innerTag)
		$(".lossesBox").click(function() {
			var lossesId = this
			.getAttribute("lossesId");	
			var lossesName = this
			.getAttribute("lossesName");
			var method = 'INDIRECT';
			if($(".toggleButton .toggle.btn")[0].classList.contains('off')){
				method = 'DIRECT';
			}
			getParameter(method,timeStamp,lossesId);
			if(method === 'DIRECT'){
    			getManualParams(timeStamp);
    		}
			$('.lossesBox').removeClass('highlight');
			this.classList.add("highlight");
			var timeCategory = $('.timeCategory li.active')[0].getAttribute('className');
			getValuesForLineGraph(timeCategory,method,lossesId,lossesName)
			var selectedlink = $('.showFormula')
			selectedlink.html('Show Formula');
			});
		
		$(".efficiency").click(function() {
			var method = 'INDIRECT';
			if($(".toggleButton .toggle.btn")[0].classList.contains('off')){
				method = 'DIRECT';
			}
			$('.lossesBox').removeClass('highlight');
			getParameter(method,timeStamp);
			if(method === 'DIRECT'){
    			getManualParams(timeStamp);
    		}
			var timeCategory = $('.timeCategory li.active')[0].getAttribute('className');
			getValuesForLineGraph(timeCategory,method);
			var selectedlink = $('.showFormula')
			selectedlink.html('Show Formula');
			});
		
		//selectedDiv.addClass('active');
	}
	
	createInnerTagForBoilerHeath = function(data){
		var innerTag = '';
		innerTag += '<ul class="boilerTabHealth">  <li class = "efficiency">   <dl>';
		innerTag += '<dt>Boiler Efficiency</dt>';
		innerTag += '<dd>'
		if(data.boiler == undefined){
			return '';
		}
		innerTag +=	data.boiler.efficiency.toFixed(2);
		var arrowSign  ='';
		if(	data.boiler.arrow == -1){
			arrowSign = '<span class="arrowheight">&#8593;</span>';
			//arrowSign = '&#8595';
		}
		else if(data.boiler.arrow == 1){
			arrowSign = '<span class="arrowheight">&#8593;</span>';
			//arrowSign = '&#8211';
		}
		
		innerTag +=	arrowSign+' <em>Boiler Efficiency</em> '
		innerTag +=  '</dd>  </dl> </li>  '
		for(var i=0; i<data.losses.length; i++){
			var lossInfo = data.losses[i];
			var alert = '';
			var imageName = '<img src="resources/images/';
			var arrowColor = 'darkRed';
			if(lossInfo.severity == 0){
				alert = 'CRITICAL';
				imageName += 'critical.png" width="16" height="16" alt=""/> ';
				arrowColor = 'darkRed';
			}
			else if(lossInfo.severity == 1){
				alert = 'Major';
				imageName += 'major.png" width="16" height="16" alt=""/> '
				arrowColor = 'yellow';
			}
			else if(lossInfo.severity == 2){
				alert = 'Normal';
				imageName = '';
				arrowColor = 'green';
			}
			var arrow = '';
			if(lossInfo.arrow == -1){
				arrow = ' <span class="'+arrowColor+' arrowheight">&#8595;</span>'
				//arrow = '&#8595';
			}
			else if(lossInfo.arrow == 1){
				arrow = ' <span class="'+arrowColor+' arrowheight">&#8593;</span>'
				//arrow = '&#8593';
			}
			else if(lossInfo.arrow == 0){
				//arrow = '&#8211';
			}
			
			
			innerTag +='<li class = "lossesBox" lossesId = "'+lossInfo.lossId+'" lossesName = "'+ lossInfo.description+'"> <dl>';
			innerTag +='<dt class="'+arrowColor+'">'+imageName+' '+alert+'</dt>';
			innerTag +=	'<dd>'+lossInfo.loss.toFixed(2) +arrow+' <em>'+lossInfo.description+'</em> </dd>';
			innerTag +='</dl>  </li>';
		}
		innerTag += '</ul> ';
		return innerTag;
          
	}
	
	/***********************Parameter Detail Data **************************************/
	
	getParameter = function(method,timeStamp,loseId){
		var boilerId = $('#boilerId').val();
		if(loseId !== undefined){
			boilerId = 'boilerId='+boilerId+'&lossId='+loseId;
		}
		else{
			boilerId ='boilerId='+boilerId;
		}
		$.ajax({
            url: '/Sonoco/getparameterdetails?'+boilerId+'&method='+method+'&timeStamp='+timeStamp,                         
            type: 'get',
            context: this,
            success: function(res){
            	drawSlider(res,method);
                },
            error:function(error){
          //  alert("Internal Error");
            },
            complete: function() {
                $(this).data('requestRunning', false);
            }
        });
	}
	
	
	drawSlider = function(res,method){
		var ul = $('.parameterDetailsCategory');
		
		ul.html('');
		var fullDom = "";
		var responsedata = res.params;
		for(var i=0; i<responsedata.length; i++){
			var data = responsedata[i];
			var minT = data.min;
			var maxT = data.max;
			var minThreshT = data.minThresh;
			var maxThreshT = data.threshold;
			var currentT = data.current;
			fullDom += "<li>";
			fullDom += "<label>"+data.name+"</label>"
			fullDom += 	"<div class = 'hahaha'>";
			var preRedWidth = (minThreshT-minT)*100/(maxT-minT);
			var greenWidth = (maxThreshT-minThreshT)*100/(maxT-minT);
			var postRedWidth = 100-(preRedWidth + greenWidth);
			
			var currentLeft = (currentT-minT)*100/(maxT-minT);
			
			fullDom += "<div class='barPartDiv' style='width:"
				+ preRedWidth + "%; background-color:#fecbca; border-radius:5px 0 0 5px;'></div>";
			fullDom += "<div class='barPartDiv' style='width:"
				+ greenWidth + "%; background-color:#cdf7c1;'></div>";
			fullDom += "<div class='barPartDiv' style='width:"
				+ postRedWidth + "%; background-color:#fecbca; border-radius:0 5px 5px 0;'></div>";
			
			fullDom += "<div class='marker minMark'></div>";
			fullDom += "<div class='marker maxMark'></div>";
			fullDom += "<div class='marker' style='left: calc(" 
				+ (preRedWidth)+ "% - 9px);'></div>";
			fullDom += "<div class='marker' style='left: calc(" 
				+ (preRedWidth + greenWidth)+ "% - 9px);'></div>";
			fullDom += "<div class='rectMarker' style='left: calc(" 
				+ (currentLeft)+ "% - 1px);'></div>";
				
			fullDom += "<div class='countDivPresent' style='left: calc(" 
				+ (currentLeft)+ "% - 25px);'>"+ currentT +"</div>";
			
			fullDom += "<div class='countDiv' style='left:0px;'>" + minT + "</div>";
			fullDom += "<div class='countDiv' style='right:2px;'>" + maxT + "</div>";
			fullDom += "<div class='countDiv' style='left:calc("
				+ (preRedWidth)+ "% - 7px);'>"+ minThreshT +"</div>";
			fullDom += "<div class='countDiv' style='left:calc("
				+ (preRedWidth + greenWidth)+ "% - 7px);'>"+ maxThreshT +"</div>";
			
			fullDom += "</div></li>";
			
		}
		formula = res.formula;
		//fullDom += '<li>'+res.formula+'</li>';
		
		ul.append(fullDom);
		
		var manualValue1 = $('.manualValue1');
		manualValue1.html('');
		
		var innerTag = '';
		var manualParamsCurrentValue = res.current;
		if(manualParamsCurrentValue.length>0){
			innerTag += '<table width="100%"> <tr>';
			innerTag += '<th width="150">Parameter</th>';
			innerTag += '<th>Value</th>';
			innerTag += '</tr>';
		}
		
		
		 for (var i=0; i < manualParamsCurrentValue.length; i++){
			innerTag += '<tr>';
			innerTag += '<td width="150">'+manualParamsCurrentValue[i].name+'</td>';
			innerTag += '<td>'+manualParamsCurrentValue[i].current+'</td>';
			innerTag += '</tr>';
		}
		
	/*	innerTag += '<tr>';
		innerTag += '</tr>';
		innerTag += '<tr>';
		innerTag += '<td>Feed Water Enthalpy</td>';
		innerTag += '<td>20</td>';
		innerTag += '</tr>';
		innerTag += '<tr>';
		innerTag += '<td>Gross Calorific Value</td>';
		innerTag += '<td>20</td>';*/
		innerTag += '</tr>	</table>';
		//innerTag += '';
		manualValue1.append(innerTag);
		
		
	}
	
	
	
	/*********************get Static Values of parameters***********************/
	
	getManualParams= function(timeStamp){
		var boilerId = $('#boilerId').val();
		$.ajax({
            url: '/Sonoco/getManualParams?boilerId='+boilerId+'&timeStamp='+timeStamp,                         
            type: 'get',
            success: function(res){
            	setManualParamsOnUI(res);
                },
            error:function(error){
            //alert("Internal Error");
            }	
        });
		
	}
	
	setManualParamsOnUI = function(data){
		
		var manualValue2 = $('.manualValue2');
		manualValue2.html('');
		var innerTag = '';
			innerTag += '<table width="100%"> ';
			
			//innerTag += 'tr';
			//innerTag += '<th>Parameter</th>';
			//innerTag += '<th>Value</th>';
			//innerTag += '</tr>';
			//var allKeys = Object.keys(data);
			 for (var key in data){
				innerTag += '<tr>';
				innerTag += '<td width="150">'+key+'</td>'; 
				innerTag += '<td>'+Math.round(data[key] * 100) / 100+'</td>';
				innerTag += '</tr>';
			}
			
		/*	innerTag += '<tr>';
			innerTag += '</tr>';
			innerTag += '<tr>';
			innerTag += '<td>Feed Water Enthalpy</td>';
			innerTag += '<td>20</td>';
			innerTag += '</tr>';
			innerTag += '<tr>';
			innerTag += '<td>Gross Calorific Value</td>';
			innerTag += '<td>20</td>';*/
			innerTag += '</tr>	</table>';
			//innerTag += '';
			manualValue2.append(innerTag);
	}
	
	/*********************End of get Static Values of parameters***********************/
	
	/***********************Onclick on losses data**************************************/
	$(".timeCategory li").click(function(){
		var timeCategory = this.getAttribute("className")
		var method = 'INDIRECT';
		if($(".toggleButton .toggle.btn")[0].classList.contains('off')){
			method = 'DIRECT';
		}
		var clickedTab = $('.boilerTabHealth li.highlight')[0]
		if(clickedTab == undefined){
			getValuesForLineGraph(timeCategory,method);
		}
		else{
			var lossId = clickedTab.getAttribute("lossesId")
			var lossName = clickedTab.getAttribute("lossesName")
			getValuesForLineGraph(timeCategory,method,lossId,lossName)
		}
		
		 
	});
	
	getValuesForLineGraph = function(timeCategory,method,loseId,lossesName){
		var boilerId = $('#boilerId').val();
		if(loseId !== undefined){
			boilerId = 'boilerId='+boilerId+'&method='+method+'&lossId='+loseId;
		}
		else{
			boilerId ='boilerId='+boilerId+'&method='+method
		}
		$.ajax({
            url: '/Sonoco/getHistoricalData?'+boilerId+'&type='+timeCategory,                         
            type: 'get',
            success: function(res){
            	setLineChart(res,timeCategory,loseId,lossesName);
                },
            error:function(error){
            //alert("Internal Error");
            }	
        });
	}
	
	setLineChart = function(res,timeCategory,loseId,lossesName){
		 google.charts.load('current', {'packages':['corechart']});
	      google.charts.setOnLoadCallback(function(){ drawLineChart(res,timeCategory,loseId,lossesName) });
	}

	function drawLineChart(res,timeCategory,loseId,lossesName) {
        var data = new google.visualization.DataTable();
        data.addColumn('datetime', 'Time of Day');
        var title = '';
        var minValue = 10;
        if(loseId === undefined){
        	data.addColumn('number', 'Efficiency');
        	title = 'Efficiency';
        	minValue = 75;
        }
        else{
        	data.addColumn('number', lossesName);
        	title = lossesName;
        }
        

        /*data.addRows([
          [new Date('2017-02-28 00:20:00.0'), 5], [new Date('2017-03-10 20:00:00.0'), 2], [new Date('2017-03-30 00:50:00.0'), 9]
        ]);*/
        for(var i=0; i<res.length; i++){
        	 data.addRows([[new Date(res[i].time),res[i].value]]);
        	
        }
        var format = '';
        var start = new Date();
        start.setHours(0,0,0,0);

        var end = new Date();
        end.setHours(23,59,59,999);
        if(res.length ==0 && timeCategory === 'today'){
        	data.addRows([[start,0]]);
        	data.addRows([[end,0]]);
        }
        var count = '';
        if(timeCategory === 'today'){
        	format = 'hh:mm';
        	count = 10;
        }
        else if(timeCategory === 'weekly'){
        	format = 'MMM dd ';
        	count = 10;
        }
        else if(timeCategory === 'month'){
        	format = 'MMM dd';
        	count = 10;
        }
        
        
        var options = {
          hAxis: {
            format: format,
            gridlines: {count: count},
            title: 'Time'
          },
          vAxis: {
            gridlines: {color: 'none'},
            minValue: minValue,
            title: title
          }
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));

        chart.draw(data, options);

      }

	     /* var data = new google.visualization.DataTable();
	      data.addColumn('number', 'X');
	      data.addColumn('number', 'Losses');

	      data.addRows([
	        [0, 0],   [1, 10],  [2, 23],  [3, 17],  [4, 18],  [5, 9],
	        [6, 11],  [7, 27],  [8, 33],  [9, 40],  [10, 32], [11, 35],
	        [12, 30], [13, 40], [14, 42], [15, 47], [16, 44], [17, 48],
	        [18, 52], [19, 54], [20, 42], [21, 55], [22, 56], [23, 57],
	        [24, 60], [25, 50], [26, 52], [27, 51], [28, 49], [29, 53],
	        [30, 55], [31, 60], [32, 61], [33, 59], [34, 62], [35, 65],
	        [36, 62], [37, 58], [38, 55], [39, 61], [40, 64], [41, 65],
	        [42, 63], [43, 66], [44, 67], [45, 69], [46, 69], [47, 70],
	        [48, 72], [49, 68], [50, 66], [51, 65], [52, 67], [53, 70],
	        [54, 71], [55, 72], [56, 73], [57, 75], [58, 70], [59, 68],
	        [60, 64], [61, 60], [62, 65], [63, 67], [64, 68], [65, 69],
	        [66, 70], [67, 72], [68, 75], [69, 80]
	      ]);

	      var options = {
	        hAxis: {
	          title: 'Time'
	        },
	        vAxis: {
	          title: 'Loss Value'
	        }
	      };
	      var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
	      chart.draw(data, options);
	     
	    }*/
	
	$('.showFormula').click(function(){
		var selectedlink = $('.showFormula')
		selectedlink.html('Show Formula');
		var innerTag = '<div class="formulaValue">'+formula+'</div>'
		selectedlink.append(innerTag);
		
		
	});
	
	
	
	//*****************Onload of page starts*********************  
	var clickedTab = $('.alertTab li.active a')[0].getAttribute('textDiv');
	getAlerts(clickedTab,'INDIRECT');
	getBoilerHealth('INDIRECT');
	getBoilerInfo();
	var timeCategory = $('.timeCategory li.active')[0].getAttribute('className');
	getValuesForLineGraph(timeCategory,'INDIRECT');
	//getParameter('INDIRECT');
	getListOfAllPlantsInfo();
	
	
		
	
	
})
