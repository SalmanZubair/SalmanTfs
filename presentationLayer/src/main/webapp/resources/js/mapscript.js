
	/*function initMap() {
		var bounds = new google.maps.LatLngBounds();
		var mapOptions = { mapTypeId: google.maps.MapTypeId.ROADMAP ,
		 styles: [{"featureType": "all","elementType":"labels","stylers":[{"visibility": "off" }]},{"featureType": "administrative","elementType": "labels","stylers": [{"visibility": "on"}]},{"featureType": "administrative.country","elementType": "geometry.stroke","stylers": [{"lightness": "15"}]},{"featureType": "road","elementType": "all","stylers": [{"visibility": "off"}]},{"featureType": "transit","elementType": "all","stylers": [{"visibility": "off"}]},{"featureType": "water","elementType": "all","stylers": [{"color": "#effefd"}]}]
		};
	        var map = new google.maps.Map(document.getElementById('map'), mapOptions);

	        // Create an array of alphabetical characters used to label the markers.
	       // var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
	        var locations = ajaxCallForAllPlantsData();
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
   		  var infowindow = new google.maps.InfoWindow();
   		  map.fitBounds(bounds);
   		  google.maps.event.addListener(marker, 'click', (function(marker, i) {
   						                   return function() {
   										   debugger;
	   										 var text = createMarkerText();
   						                     infowindow.setContent(text);
   						                     infowindow.open(map, marker);
   						                   }  
   										   })(marker, i));
             return marker;
           });

	        // Add a marker clusterer to manage the markers.
	        var markerCluster = new MarkerClusterer(map, markers,
	            {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
	      }
		createMarkerText=function(){
			var innerText = '<div id="" class="plantDetail">';
			innerText += ' <div class="">';
			innerText += '<h3>Plant Name <span>8</span></h3>';
			innerText += '<div class="plantContent">';
			innerText += ' <h4 class="darkRed"><img src="resources/images/critical.png" width="16"   alt=""/> Critical</h4>';
			innerText += '<ul> <li>';
			innerText += '<div class="boilerName">Boiler 1</div>';
			innerText += '<span>Efficiency &gt;&gt; 78%</span> <span class="green">&#8595;</span></li>';
			innerText += '<li>08/15/2013</li>';
			innerText += ' <li><input type="button" value="View"></li>';
			innerText += ' </ul>';
			innerText += '</div> </div> </div>';
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
			innerText += '';
			innerText += '';
			
			return innerText;
			
		}
	
	      var locations = [
	        {lat: -31.563910, lng: 147.154312},
	        {lat: -33.718234, lng: 150.363181},
	        {lat: -33.727111, lng: 150.371124},
	        {lat: -33.848588, lng: 151.209834},
	        {lat: -33.851702, lng: 151.216968},
	        {lat: -34.671264, lng: 150.863657},
	        {lat: -35.304724, lng: 148.662905},
	        {lat: -36.817685, lng: 175.699196},
	        {lat: -36.828611, lng: 175.790222},
	        {lat: -37.750000, lng: 145.116667},
	        {lat: -37.759859, lng: 145.128708},
	        {lat: -37.765015, lng: 145.133858},
	        {lat: -37.770104, lng: 145.143299},
	        {lat: -37.773700, lng: 145.145187},
	        {lat: -37.774785, lng: 145.137978},
	        {lat: -37.819616, lng: 144.968119},
	        {lat: -38.330766, lng: 144.695692},
	        {lat: -39.927193, lng: 175.053218},
	        {lat: -41.330162, lng: 174.865694},
	        {lat: -42.734358, lng: 147.439506},
	        {lat: -42.734358, lng: 147.501315},
	        {lat: -42.735258, lng: 147.438000},
	        {lat: 37.0902, lng: -95.7129},
			{lat: 42.3601, lng: - 71.0589},
			{lat: 28.6139, lng: 77.2090},
			{lat: 28.6139, lng: 77.2093},
			{lat: 42.3601, lng: - 71.0599},
	      ]
	      ajaxCallForAllPlantsData = function(){
	    	  var locations = new Array();
	    	  $.ajax({
	    	            url: '/Sonoco/getAllPlantsInfoForMap',                         
	    	            type: 'get',
	    	            success: function(res){
	    	            	locations = populateAllDataofPlants(res);
	        },
	        error:function(error){
	              alert("Internal Error");
	              }
	          });
	    	  return locations;
	        }
	        
	        populateAllDataofPlants = function(data){
	      	  var locations = new Array();
	      	  for(var i = 0; i < data.length; i++){
	      		  var plantInfo = data[i];
	      		  var myLatlng = new google.maps.LatLng(plantInfo.lat,
	      				  plantInfo.long);
	      		  locations.push(myLatlng);
	      	  }
	      	  return locations;
	        }
	
*/