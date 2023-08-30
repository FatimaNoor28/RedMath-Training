(function () {
    'use strict';

    angular.module("news-fe", ['ngResource', 'ng']); //registerng my application with name news-fe and dependency angular resource, default angular dependency

    function NewsService($resource) {       //we define our service here
        return $resource('api/v1/news/:id');

    }
    angular.module('news-fe').factory('newsService', ['$resource', NewsService]);  //we register the service "NewsService"

    //next we need to add controller jo view i.e. html ko backend resources i.e. model k sath bind kr de ga
    function NewsController(NewsService) {
            var self = this;

            self.service = NewsService;
            self.news = [];
            self.title = '';
            self.display = false;

            self.init = function () {
                self.search();
            }

            self.search = function () {
                self.display = false;
                var parameters = {};
                if (self.title) {
                    parameters.title = '%' + self.title + '%';
                }
                else{
                    parameters.search = '%';
                }

                self.service.get(parameters).$promise.then(function (response) {
                    self.display = true;
                    self.news = response.content;
                });
            }

            self.init();
        }
        angular.module("news-fe").controller('newsController', ['newsService', NewsController]);  //is controlller ko web page se access kr skte hain
}());
