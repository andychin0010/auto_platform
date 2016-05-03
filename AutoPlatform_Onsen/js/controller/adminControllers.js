var adminControllers = angular.module('adminControllers', ['adminServices']);

adminControllers.controller('adminController', ['$scope', 
	function($scope) {
		$scope.test = function() {
			// $scope.replacePage("adminLanding.");
			autoNavigator.replacePage('templates/adminLanding.html', { animation : 'lift' });	
		}
	}
])

adminControllers.controller('adminLandingController', ['$scope', '$window', '$q', '$location', '$anchorScroll', 'adminService',
	function($scope, $window, $q, $location, $anchorScroll, adminService) {
		init();

		// var PAGES = 5;
		var pageCount = 0;

		function init() {
			console.log("init");
			$scope.currentPage = 1;
			var page = new Page(0);
		
			return $q.all([adminService.getProducts(page.getLimit(), page.getOffset(), true), adminService.getProductCount()])
					.then(function(values) {
						$scope.products = values[0];

						pageCount = Math.ceil(values[1] / page.getLimit());

						if (pageCount > 1) {
							var pageNumbers = [];

							for (var i = 0; i < (pageCount > page.getLimit() ? page.getLimit() : pageCount); i++) {
								pageNumbers.push(i + 1);
							}

							$scope.pageNumbers = pageNumbers;
							if (pageCount > page.getLimit()) {
								$scope.hasNext = true;
							}
						}
					});
		};

		$scope.getPaginations = function(next) {
			var pageNumbers = [];
			var hasNext = true;
			var hasPrevious = true;

			var temp = $scope.pageNumbers[0] + 5 * (next ? 1 : -1);

			for (var i = 0; i < 5; i++) {
				if (temp + i == pageCount) {
					hasNext = false;
				} else if (temp + i > pageCount) {
					break;
				}
				pageNumbers.push(temp + i);
			}	

			if (temp <= 1) {
				hasPrevious = false;
			}		

			$scope.pageNumbers = pageNumbers;
			$scope.hasNext = hasNext;
			$scope.hasPrevious = hasPrevious;
		}

		$scope.getProducts = function(page) {
			if ($scope.currentPage != page) {
				$scope.currentPage = page;

				var page = new Page(page);
				adminService.getProducts(page.getLimit(), page.getOffset(), true).then(
					function(products) {
						$scope.products = products;
						$anchorScroll("top");
					}
				);
			}
		}

		$scope.adminCreate = function() {
			autoNavigator.pushPage('templates/adminCreate.html', { animation : 'slide' });	
		};

		$scope.adminDetail = function(autoId) {
			autoNavigator.pushPage('templates/adminDetail.html', { animation : 'slide', autoId : autoId });
		}

		$scope.load = function($done) {
			init().then(function() {
				$done();
			})
		}
	}
]);

adminControllers.controller('adminDetailController', ['$scope', '$window', 'adminService', 
	function($scope, $window, adminService) {
		var page = autoNavigator.getCurrentPage();
		
		$scope.myStyle={"height": ($window.innerWidth / 800 * 450) + "px", "width": "100%"};
		// $scope.myStyle={"height": "100%", "width": "100%"};

		adminService.getProduct(page.options.autoId).then(
			function(product) {
				$scope.product = product;
				$scope.images = product.images;
			}
		)

		$scope.prevPage = function() {
			autoNavigator.popPage();
		}

		$scope.prevCarousel = function() {
			autoCarousel.prev();
		};

		$scope.nextCarousel = function() {
			autoCarousel.next();
		};

		$scope.update = function(product) {
			adminService.updateProduct(product).then(
				function(products) {
					autoNavigator.popPage();
				}
			);
		}
	}
]);

adminControllers.controller('adminCreateController', ['$scope', '$window', 'adminService', 
	function($scope, $window, adminService) {

		$scope.prevPage = function() {
			console.log('back');
			autoNavigator.popPage();
		}

		$scope.submit = function(product) {
			adminService.createProduct(product).then(
				function(products) {
					// console.log(products);
					// $location.path("#/");
					autoNavigator.popPage();
		// 			autoNavigator.popPage({onTransitionEnd : function() {
  //    $scope.autoNavigator.replacePage('admin.html', { animation : 'none' } );
  // }});
  					// autoNavigator.resetToPage('admin.html');
  					// autoNavigator.popPage({onTransitionEnd : function() {
						// autoNavigator.replacePage('admin.html', { animation : 'none' } );
					// }})
				}
			);
		}
	}
]);