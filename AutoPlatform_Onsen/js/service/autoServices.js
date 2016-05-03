var autoServices = angular.module('autoServices', ['productServices'])

autoServices.service('autoService', ['productService',
   function (productService) {
      this.getProducts = function(limit, offset) {
         return productService.getProducts(limit, offset);
      }

      this.getProduct = function(id) {
         return productService.getProduct(id);
      }
   }
])