var autoControllers = angular.module('autoControllers', ['autoServices']);

autoControllers.controller('autoController', ['$scope', '$q', '$http', 'autoService',
	function($scope, $q, $http, autoService) {
		$scope.test = "testing";
		// $scope.test1 = "testing1";
		$scope.autoDetail = function() {
			autoNavigator.pushPage('templates/autoDetail.html', { animation : 'slide', autoId : 1 } );	
		};

		// autoService.getProducts().then(
		// 	function() {
		// 		console.log("ues");
		// 	}
		// );

		$scope.autoDelegate = {
	      countItems: function() {
	        // Return number of items.
	        return 5;
	      },

	      calculateItemHeight: function(index) {
	        // Return the height of an item in pixels.
	        return 310;
	      },

	      configureItemScope: function(index, itemScope) {
	        // Initialize scope
	        // itemScope.item = 'Item #' + (index + 1);
	        if (!itemScope.item) {
	        	itemScope.canceler = $q.defer();
	        	// itemScope.item = {
	        	// 	title: 'Item #' + (index + 1),
	        	// 	label: '',
	        	// 	desc: '',
	        	// 	rand: Math.random()
	        	// };

	        	// $http.get('https://baconipsum.com/api/?type=meat-and-filler&sentences=1', {timeout: itemScope.canceler.promise})
	        	// .success(function(data) {
	        	// 	itemScope.item.desc = data[0];
	        	// 	itemScope.item.label = data[0].substr(0, data[0].indexOf(" ")) + 'bacon';
	        	// })
	        	// .error(function() {
	        	// 	itemScope.item.desc = 'No bacon lorem ipsum';
      				// itemScope.item.label = 'No bacon';
	        	// });

	        	autoService.getProducts(1, index + 1).then(function(value) {
	        		console.log(value);
	        		itemScope.item = value[0];
	        	});
	        }
	      },

	      destroyItemScope: function(index, itemScope) {
	        // Optional method that is called when an item is unloaded.
	        // console.log('Destroyed item with index: ' + index);
	        itemScope.canceler.resolve();
	      }
	    };	
	}
]);

autoControllers.controller('autoDetailController', function($scope, $window) {
	$scope.test = "testing";
	$scope.test1 = "testing2";

	var page = autoNavigator.getCurrentPage();
	console.log("yes");
	console.log(page.options.autoId);
	$scope.myStyle={"height": ($window.innerWidth / 800 * 450) + "px", "width": "100%"};

	$scope.prevPage = function() {
		console.log('back');
		autoNavigator.popPage();
	}

	$scope.prevCarousel = function() {
		autoCarousel.prev();
	};

	$scope.nextCarousel = function() {
		autoCarousel.next();
	};
});