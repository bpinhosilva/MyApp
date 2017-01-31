var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider, $locationProvider){
    $locationProvider.html5Mode(true);

    $routeProvider
        .when("/", {
            template : "<h1>Main</h1><p>Click on the links to change this content</p>"
        })
        .when("/banana", {
             template : "<h1>Banana</h1><p>Bananas contain around 75% water.</p>"
         })
         .when("/tomato", {
             template : "<h1>Tomato</h1><p>Tomatoes contain around 95% water.</p>"
         })
        .when("/london", {
            templateUrl : "views/london.htm",
            controller  : "londonCtrl"
        })
        .when("/paris", {
            templateUrl : "views/paris.htm",
            controller  : "parisCtrl"
        })
        .otherwise({
            redirectTo : "/"
        });;
});

app.controller("londonCtrl", function ($scope) {
    $scope.msg = "I love London";
});
app.controller("parisCtrl", function ($scope) {
    $scope.msg = "I love Paris";
});