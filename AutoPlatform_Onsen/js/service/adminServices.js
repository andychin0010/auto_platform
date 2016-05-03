var adminServices = angular.module('adminServices', ['productServices'])

adminServices.service('adminService', ['productService', 
	function (productService) {
		this.createProduct = function(product) {
			return productService.createProduct(product);
		}

		this.updateProduct = function(product) {
			return productService.updateProduct(product);
		}

		this.getProducts = function(limit, offset) {
			return productService.getProducts(limit, offset);
		}

		this.getProduct = function(id) {
			return productService.getProduct(id);
		}

		this.getProductCount = function() {
			return productService.getProductCount();
		}
   }
])