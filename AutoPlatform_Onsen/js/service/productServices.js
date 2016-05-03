var productServices = angular.module('productServices', ['ngFileUpload'])

productServices.service('productService', ['$http', '$q', 'Upload', function ($http, $q, Upload) {
   this.createProduct = function(product) {
      var promises = [];

      return $http.post("http://10.0.0.121:8081/products/create", product)
      // return $http.post("http://www.carpapapa.com:8081/products/create", product)
      .then(
         function success(response) {
            console.log('yes1')

            angular.forEach(product.images, function(image) {
               console.log('yes2');
               
               var deferred = $q.defer();

               // image.upload = Upload.upload({
               promises.push(Upload.upload({
                  url: "http://10.0.0.121:8081/images/products/" + response.data.id + "/upload",
                  // url: "http://www.carpapapa.com:8081/images/products/" + response.data.id + "/upload",
                  data: {file: image},
               }).then(function (response) {
                  console.log('yes3');
                  console.log(response.data);
                  // deferred.resolve("yead");
               }));

               // promises.push(deferred.promise);

               // image.upload.then(function (response) {
               //    $timeout(function () {
               //       image.result = response.data;
               //       console.log(response.data);
               //    });
               // }, function (response) {
               //    if (response.status > 0)
               //    $scope.errorMsg = response.status + ': ' + response.data;
               // }, function (evt) {
               //    // Math.min is to fix IE which reports 200% sometimes
               //    image.progress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
               // });
            });

            return $q.all(promises).then(function () {
               return 'yeah';
            });

            // deferred.resolve("yeah");
            
         }, function error(response) {
         }
      );

      // promises.push(deferred.promise);
      // console.log(promises);

      // return $q.all(promises).then(
      //    function success(data){
      //       console.log("done");
      //       return "data";
      //     }, function failure(err){
      //       console.log("failed");
      //     }
      // );
   }

   this.updateProduct = function(product) {
      // return $http.post("http://wwww.carpapapa.com/products/" + product.id + "/update", product)
      return $http.post("http://10.0.0.121:8081/products/" + product.id + "/update", product)
      .then(
         function success(response) {
            return response.data
         }, function error(response) {
            return $q.all([]);
         }
      );
   }

   this.getProducts = function(limit, offset, all) {
      // return $http.get("http://www.carpapapa.com:8081/products?limit=" + (limit ? limit : 10) + "&offset=" + (offset ? offset : 0) + "&all=" + (all ? all : false))
      return $http.get("http://10.0.0.121:8081/products?limit=" + (limit ? limit : 10) + "&offset=" + (offset ? offset : 0) + "&all=" + (all ? all : false))
      .then(function success(response) {
         return response.data;
      }, function error(response) {
         return $q.all([]);
      });
   };

   this.getProduct = function(id) {
      // return $http.get("http://www.carpapapa.com:8081/products/" + id)
      return $http.get("http://10.0.0.121:8081/products/" + id)
      .then(function success(response) {
         return response.data;
      }, function error(response) {
         return $q.all([]);
      });
   };

   this.getProductCount = function() {
      // return $http.get("http://www.carpapapa.com:8081/products/count")
      return $http.get("http://10.0.0.121:8081/products/count")
      .then(function success(response) {
         return response.data;
      }, function error(response) {
         return $q.all([]);
      });
   }
}])