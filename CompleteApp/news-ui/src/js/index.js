(function () {
    'use strict';
    function NewsConfig($locationProvider, $routeProvider ){
        $locationProvider.hashPrefix('!');
        $routeProvider.when('/news',{
             templateUrl: 'news/news.html',
             controller: 'NewsController as $ctrl'
        }).when('/',{
              templateUrl: 'hello-world.html',

//              templateUrl: 'news/news.html',
//              controller: 'NewsController as $ctrl'
        }).otherwise('/');
    }
    angular.module('news-fe', ['ngRoute', 'ngResource', 'ng'])
    .config(['$locationProvider', '$routeProvider', 'NewsConfig'])


}());