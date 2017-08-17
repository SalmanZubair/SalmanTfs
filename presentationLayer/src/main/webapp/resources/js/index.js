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
	
	
	
	/*********Global Variables ********************/
	var criticalData = [];
	var majorData = [];
	var allAlertCount =0;
	var plantsData;
	var plantOfListNames = [];
	var plantOfListIds = [];
	var allPlantData =[];
	
	
	
	$('.gotoDataAnalysis').click(function(){
		
			
			window.open("dataAnalysis","_self");
	});
	getAlertDataForMap = function(clickedTab){
		if(clickedTab === 'all'){ 
			$.ajax({
	            url: '/Sonoco/getAllertsForMap',                         
	            type: 'get',
	            success: function(res){
	            	criticalData = [];
	            	majorData =[];
	            	var alertsDiv = $('.allAlertCount' );
	            	var crticalCount = $('.crticalCount' );
	            	var majorCount = $('.majorCount' );
	            	var normalCount = $('.normalCount' );
	        		alertsDiv.html('');
	        		crticalCount.html('');
	        		majorCount.html('');
	        		normalCount.html('');
	        		alertsDiv.append('<span class="label">'+res.plant.length+'</span>');
	        		crticalCount.append('<span class="catStatus">Critical</span> <span class="catStatusNum ">'+res.critical+'</span> <img src="resources/images/graphLine.jpg" alt="" />');
	        		normalCount.append('<span class="catStatus">Normal</span> <span class="catStatusNum ">'+res.normal+'</span> <img src="resources/images/graphLine.jpg" alt="" /> ');
	        		majorCount.append('<span class="catStatus">Not Integrated</span> <span class="catStatusNum ">'+res.major+'</span> <img src="resources/images/graphLine.jpg" alt="" />');
	        		//normalCount.append('<span class="catStatus">Normal</span> <span class="catStatusNum ">'+res.normal+'</span> <img src="resources/images/graphLine.jpg" alt="" /> ');
	            	populateAllAlertData(res.plant,clickedTab);
	            	
	            	
	                },
	            error:function(error){
	            //alert("Internal Error");
	            }
	        });
		}
		else{
			
			if(clickedTab ==='critical'){
				populateAllAlertData(criticalData,clickedTab);
			}
			else{
				populateAllAlertData(majorData,clickedTab);
			}
		}
	}
	populateAllAlertData = function(data,clickedTab){
		debugger;
		
		$('.AlertCategory').removeClass('active');
		$('.AlertCategory').addClass('fade');
		
		var selectedDiv = $('.' + clickedTab + '.AlertCategory');
		selectedDiv.addClass('active');
		selectedDiv.removeClass('fade');
		selectedDiv.html('');
		
		var alertsDiv = $('.alertCount' );
		alertsDiv.html('');
		alertsDiv.append('<h3>Alerts <span>'+data.length+'</span></h3>');
		
		//var alerDataDiv = $('.alertInfo');
		//alerDataDiv.html('');
		var innerTag = createInnerTagForAllAlertData(data,clickedTab)
		selectedDiv.append(innerTag);
		
	}
	createInnerTagForAllAlertData = function(data,clickedTab){
		var createInnerTag = '';
		if(data.length>0){
			createInnerTag += '<div class="boilerHealthAlertMain">';
			for(var i= 0; i<data.length; i++){
				
				
				var alert = '';
				var imageName = '<img src="resources/images/';
				var arrowColor = 'darkRed';
				if(data[i].severity == 0){
					if(clickedTab === 'all'){
						criticalData.push(data[i]);
					}
					alert = 'CRITICAL';
					imageName += 'critical.png" width="16" height="16" alt=""/> ';
					arrowColor = 'darkRed';
				}
				else if(data[i].severity == 1){
					if(clickedTab === 'all'){
						majorData.push(data[i]); 
					}
					alert = 'Major';
					imageName += 'major.png" width="16" height="16" alt=""/> '
					arrowColor = 'yellow';
				}
				else if(data[i].severity == 2){
					alert = 'Normal';
					imageName = '';
					arrowColor = 'green';
				}
				
				createInnerTag += '<h4 class="'+arrowColor+'">'+imageName+alert+'</h4>';
				createInnerTag += '<div class="categoryList">';
				createInnerTag += '<dl>';
				createInnerTag += '<dt>'+data[i].plantName+'</dt>';
				createInnerTag += '<dd>'+data[i].address+'</dd>';
				createInnerTag += '</dl>';
				
				//for Loop For boilers
				for(var j=0;j<data[i].boilerlist.length;j++){
					createInnerTag += ' <ul>';
					var boilerData = data[i].boilerlist[j];
					var boilerArrowColor
					if(boilerData.severity == 0){
						boilerArrowColor = 'darkRed';
					}
					else if(boilerData.severity == 1){
						boilerArrowColor = 'yellow';
					}
					else if(boilerData.severity == 2){
						boilerArrowColor = 'green';
					}
					var arrow = '';
					if(boilerData.arrow == -1){
						arrow= '<span class="'+boilerArrowColor+'">&#8595;</span>';
						//arrow = '';
					}
					else if(boilerData.arrow == 1){
						arrow= '<span class="'+boilerArrowColor+'">&#8593;</span>';
						//arrow = '&#8593';
					}
					else if(boilerData.arrow == 0){
						//arrow = '&#8211';
					}
					createInnerTag += '<li>';
					createInnerTag += '<div class="boilerName">'+boilerData.boilerName+'</div>';
					createInnerTag += '<span>Efficiency &gt;&gt;'+boilerData.efficiency+'%</span>'+'</li>';
					createInnerTag += ' <li>'+boilerData.date+'</li>';
					createInnerTag += ' <li><input type="button" boilerId="'+boilerData.boilerId+'" value="View" onclick="getBoilerHealth(this)"></li>';
					createInnerTag += '</ul>';
				}
				//end of forLoop
				
				createInnerTag += '</div>';
			}
			createInnerTag += '</div>';
			createInnerTag += '</div>';
			createInnerTag += '</div>';
			createInnerTag += '</div>';
		}
		else{
			createInnerTag += '<div>No record found </div>'
		}
		//createInnerTag += '</div>';
		
		return createInnerTag;
			
		
	}
	
	
	
	$('.dropdown-menu a[data-toggle="tab"]').click(function (e) {
		e.stopPropagation(); 
		
		var clickedTab = this.getAttribute('textDiv');
		getAlertDataForMap(clickedTab);
		/*getAlerts(clickedTab,method);
		$(".alertTab li").removeClass('active');
		this.classList.add("active")*/
		$(this).tab('show');
	});
	getBoilerHealth=function(boilerObject){
		var boilerId = boilerObject.getAttribute('boilerId');
		
		window.open("boilerHealth?boilerId="+boilerId,"_self");
	}
	
	/******************Map*****************************/
	ajaxCallForAllPlantsData = function(){
  	  $.ajax({
	            url: '/Sonoco/getAllPlantsInfoForMap',                         
	            type: 'get',
	            success: function(res){
	            	plantOfListNames =[];
	            	plantOfListIds = [];
	            	populateAllDataofPlants(res.plantList);
	            	allPlantData = res.plantList;
	            	populateSelectOptionsForPlant(res.plantList);
    },
    error:function(error){
         // alert("Internal Error");
          }
      });
    }
    
    populateAllDataofPlants = function(data){
    	plantsData = data;
  	 var locations = new Array();
  	  for(var i = 0; i < data.length; i++){
  		plantOfListNames.push(data[i].plantName);
		plantOfListIds.push(data[i].plantName)
  		 var plantInfo = data[i];
  		  locations.push({lat: +plantInfo.lat, lng: +plantInfo.long});
  	  }

  	var bounds = new google.maps.LatLngBounds();
	var mapOptions = { mapTypeId: google.maps.MapTypeId.ROADMAP ,
			// zoomControl: true,
			//zoom:10,
			 panControl:true,
			 mapTypeControl:true,
			 streetViewControl:true,
	         overviewMapControl:true,
	         rotateControl:true,
			    scaleControl: true,
	 styles: [{"featureType": "all","elementType":"labels","stylers":[{"visibility": "on" }]},{"featureType": "administrative","elementType": "labels","stylers": [{"visibility": "on"}]},{"featureType": "administrative.country","elementType": "geometry.stroke","stylers": [{"lightness": "15"}]},{"featureType": "road","elementType": "all","stylers": [{"visibility": "off"}]},{"featureType": "transit","elementType": "all","stylers": [{"visibility": "off"}]},{"featureType": "water","elementType": "all","stylers": [{"color": "#effefd"}]}]
	};
        var map = new google.maps.Map(document.getElementById('map'), mapOptions);

        // Create an array of alphabetical characters used to label the markers.
       // var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

        // Add some markers to the map.
        // Note: The code uses the JavaScript Array.prototype.map() method to
        // create an array of markers based on a given "locations" array.
        // The map() method here has nothing to do with the Google Maps API.
        var markers = locations.map(function(location, i) {
		var loc =new google.maps.LatLng(location.lat, location.lng);
						            	   bounds.extend(loc);
	   var marker = new google.maps.Marker({
         position: location
        // label: labels[i % labels.length]
       });
	   if(plantsData[i].boilerInfo.critical.length >0){
	  		 marker.setIcon('http://maps.google.com/mapfiles/ms/icons/red-dot.png')	
	  	   }
	   else if(plantsData[i].boilerInfo.major.length >0){
		   marker.setIcon('http://maps.google.com/mapfiles/ms/icons/yellow-dot.png')
	   }
	   else if(plantsData[i].boilerInfo.normal.length >0){
		   marker.setIcon('http://maps.google.com/mapfiles/ms/icons/green-dot.png')
	   }else{
		   marker.setIcon('http://maps.google.com/mapfiles/ms/icons/blue-dot.png')
	   }
		  var infowindow = new google.maps.InfoWindow();
		  
		  
		  //set Zoom level
		  if(data.length==1){
		  google.maps.event.addListener(map, 'zoom_changed', function() {
			    zoomChangeBoundsListener = 
			        google.maps.event.addListener(map, 'bounds_changed', function(event) {
			            if (this.getZoom() > 15 && this.initialZoom == true) {
			                // Change max/min zoom here
			                this.setZoom(10);
			                this.initialZoom = false;
			            }
			        google.maps.event.removeListener(zoomChangeBoundsListener);
			    });
			});
			map.initialZoom = true;
		  }
		  
		  
		  map.fitBounds(bounds);
		  if(data.length>1){
		  google.maps.event.addListener(marker, 'click', (function(marker, i) {
						                   return function() {
										   debugger;
										   var plantInfo = plantsData[i];
   										 var text = createMarkerText(plantInfo);
						                     infowindow.setContent(text);
						                     infowindow.open(map, marker);
						                   }  
										   })(marker, i));
        }
       return marker;
     });
        
        var calc = function(markers, numStyles) {
        	  for (var i = 0; i < markers.length; i++) {
        	    if (markers[i].getIcon().indexOf("red-dot") > -1) {
        	      return {text: markers.length, index: 4};
        	    } else if (markers[i].getIcon().indexOf("yellow-dot") > -1) {
        	      return {text: markers.length, index: 3};
        	    }
        	    else if (markers[i].getIcon().indexOf("green-dot") > -1) {
          	      return {text: markers.length, index: 2};
          	    }
        	  }
        	  return {text: markers.length, index: 1};
        	}

        	var mcOptions = {gridSize: 50, maxZoom: 15, styles: [{
        	    height: 46,
        	    url: "resources/images/clusterBlue.png",
        	    width: 46
        	  },
        	  {
        	    height: 46,
        	    url: "resources/images/clusterGreen.png",
        	    width: 46
        	  },
        	  {
          	    height: 46,
          	    url: "resources/images/clusterYellow.png",
          	    width: 46
          	  },
          	 {
          	    height: 46,
          	    url: "resources/images/clusterRed.png",
          	    width: 46
          	  }
        	  ]
        	};
        
        	var markerCluster = new MarkerClusterer(map, markers, mcOptions);
        	markerCluster.setCalculator(calc);
        
        // Add a marker clusterer to manage the markers.
        /*var markerCluster = new MarkerClusterer(map, markers,
            {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});*/
  	  
    }
    
   
    createMarkerText=function(plantInfo){
    	var innerText  = '';
    	var boilerInfo = plantInfo.boilerInfo;
    	innerText += '<div id="" class="plantDetail">';
		innerText += ' <div class="">';
		innerText += '<h3>'+plantInfo.plantName+' <span>'+(boilerInfo.critical.length+boilerInfo.major.length+boilerInfo.normal.length)+'</span></h3>';
		innerText += '<div class="plantContent" id="mapScroll">';
		if(boilerInfo.critical.length>0){
			innerText += ' <h4 class="darkRed"><img src="resources/images/critical.png" width="16"   alt=""/> Critical</h4>';
    		
		}
    	for(var i=0; i <plantInfo.boilerInfo.critical.length; i++){
    		var criticalInfo = plantInfo.boilerInfo.critical[i];
    		innerText += '<ul>';
    		innerText +=' <li>';
    		innerText += '<div class="boilerName">'+criticalInfo.boilerName+'</div>';
    	
			var arrow = '';
			if(criticalInfo.arrow == -1){
				arrow = '<span class="green">&#8595;</span>'
				//arrow = '&#8595';
			}
			else if(criticalInfo.arrow == 1){
				arrow = '<span class="green">&#8593;</span>'
				arrow = '&#8593';
			}
			else if(criticalInfo.arrow == 0){
				//arrow = '&#8211';
			}
    		innerText += '<span>Efficiency &gt;&gt; '+criticalInfo.efficiency+'%</span> '+arrow+'</li>';
    		innerText += '<li>'+criticalInfo.date+'</li>';
    		innerText += ' <li><input type="button" boilerId="'+criticalInfo.boilerId+'" value="View" onclick="getBoilerHealth(this)"></li>';
    		innerText += ' </ul>';
    	}
    	if(boilerInfo.major.length>0){
			innerText += ' <h4 class="yellow"><img src="resources/images/major.png" width="16"   alt=""/> Major</h4>';
    		
		}
    	for(var i=0; i <plantInfo.boilerInfo.major.length; i++){
    		var majorInfo = plantInfo.boilerInfo.major[i];
    		innerText += '<ul>';
    		innerText +=' <li>';
    		innerText += '<div class="boilerName">'+majorInfo.boilerName+'</div>';
    		var arrow = '';
    		if(majorInfo.arrow == -1){
    			arrow =' <span class="green">&#8595;</span>'
    			//arrow = '&#8595';
    		}
    		else if(majorInfo.arrow == 1){
    			arrow =' <span class="green">&#8593;</span>'
    			//arrow = '&#8593';
    		}
    		else if(majorInfo.arrow == 0){
    			//arrow = '&#8211';
    		}
    		innerText += '<span>Efficiency &gt;&gt; '+majorInfo.efficiency+'%</span>'+arrow+'</li>';
    		innerText += '<li>'+majorInfo.date+'</li>';
    		innerText += ' <li><input type="button" boilerId="'+majorInfo.boilerId+'" value="View" onclick="getBoilerHealth(this)"></li>';
    		innerText += ' </ul>';
    	}
    	if(boilerInfo.normal.length>0){
			innerText += ' <h4 class="green"><img src="resources/images/normal.png" width="16"   alt=""/> Normal</h4>';
    		
		}
    	for(var i=0; i <plantInfo.boilerInfo.normal.length; i++){
    		var normalInfo = plantInfo.boilerInfo.normal[i];
    		innerText += '<ul>';
    		innerText +=' <li>';
    		innerText += '<div class="boilerName">'+normalInfo.boilerName+'</div>';
    		var arrow = '';
    		if(normalInfo.arrow == -1){
    			arrow =  '<span class="green">&#8595;</span>'
    			//arrow = '&#8595';
    		}
    		else if(normalInfo.arrow == 1){
    			arrow =  '<span class="green">&#8595;</span>'
    			//arrow = '&#8593';
    		}
    		else if(normalInfo.arrow == 0){
    			//arrow = '&#8211';
    		}
    		innerText += '<span>Efficiency &gt;&gt;'+normalInfo.efficiency+'%</span>'+arrow+'</li>';
    		innerText += '<li>'+normalInfo.date+'</li>';
    		innerText += ' <li><input type="button"boilerId="'+normalInfo.boilerId+'" value="View" onclick="getBoilerHealth(this)" ></li>';
    		innerText += ' </ul>';
    	}
    	if(boilerInfo.normal.length>0){
    		
    	}
		innerText += '</div> </div> </div>';
		/*innerText += '';
		innerText += '';
		innerText += '';
		innerText += '';
		innerText += '';
		innerText += '';
		innerText += '';
		innerText += '';
		innerText += '';
		innerText += '';
		innerText += '';
		innerText += '';*/
		
		return innerText;
		
	}
    
  /*  $('#listOfPlants').autocomplete({
		source : plantOfListNames,
		select: function(event, ui) {
			var selectedPlant = ui.item.value;
			var index = plantOfListNames.indexOf(selectedPlant)
			if (index >= 0){
	        	  getListOfBoilersInfoByPlantId(plantOfListIds[index],'call');
	          }
        }
	});*/
    $("#mapScroll").mCustomScrollbar({
		theme:"minimal"
	});
    populateSelectOptionsForPlant = function(data){
    	plantOfListNames = [];
		plantOfListIds = [];
		for(var i =0; i<data.length; i++){
			plantOfListNames.push(data[i].plantName);
			plantOfListIds.push(data[i].plantId)
		}
		var displayPlantSelectionDiv = $('.displayPlantSelection');
		var innerTag = '';
		innerTag += '<select class="plantNamee" onchange="showAllBoilerForPlantId(this.value)">';
		innerTag += '<option>Select Plant</option>';
		for(var i = 0; i<plantOfListNames.length; i++){
			innerTag += '<option value='+plantOfListIds[i]+'>'+plantOfListNames[i]+'</option>';
		}
		innerTag += ' </select>';
		
		displayPlantSelectionDiv.append(innerTag);
    	
    }
    showAllBoilerForPlantId = function(plantId){
    	var param = '?plantId='+plantId;
    	if(plantId ==='Select Plant'){
    		param = '';
    	}
		$.ajax({
			url: '/Sonoco/getAllertsForMap'+param,                        
            type: 'get',
            success: function(res){
            	
            	var alertsDiv = $('.allAlertCount' );
            	var crticalCount = $('.crticalCount' );
            	var majorCount = $('.majorCount' );
            	var normalCount = $('.normalCount' );
        		alertsDiv.html('');
        		crticalCount.html('');
        		majorCount.html('');
        		normalCount.html('');
        		alertsDiv.append('<span class="label">'+res.plant.length+'</span>');
        		crticalCount.append('<span class="catStatus">Critical</span> <span class="catStatusNum ">'+res.critical+'</span> <img src="resources/images/graphLine.jpg" alt="" />');
        		normalCount.append('<span class="catStatus">Normal</span> <span class="catStatusNum ">'+res.normal+'</span> <img src="resources/images/graphLine.jpg" alt="" /> ');
        		majorCount.append('<span class="catStatus">Not Integrated</span> <span class="catStatusNum ">'+res.major+'</span> <img src="resources/images/graphLine.jpg" alt="" />');
        		populateAllAlertData(res.plant,'all');
        		console.log(allPlantData);
        		if(plantId === 'Select Plant'){
        			populateAllDataofPlants(allPlantData)
        			var singlePalntInfo = $('.singlePlantDetails');
					singlePalntInfo.css("display","none");
        		}
        		else {
					for (var i = 0; i < allPlantData.length; i++) {

						if (plantId === allPlantData[i].plantId) {
							var aa = [];
							aa.push(allPlantData[i]);
							populateAllDataofPlants(aa);
							var singlePalntInfo = $('.singlePlantDetails');
							singlePalntInfo.css("display","block");
							singlePalntInfo.html('');
							var innerText = createMarkerText(allPlantData[i]);
							singlePalntInfo.append(innerText)
							break;
						}
					}
							}
            	//setBoilersList(res);
                },
            error:function(error){
            }
        });
	}
	
	var data = ['222','3333','444'];
	var clickedTab = 'all';
	getAlertDataForMap(clickedTab);
	ajaxCallForAllPlantsData();
})