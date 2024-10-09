	//criação do modulo principal da aplicação
	var appCliente = angular.module("appCliente",["ngRoute"]);
	
	appCliente.config(function ($routeProvider, $locationProvider){
		
	$routeProvider
	.when("/clientes", {
		
		templateUrl: 'static/view/cliente.html', 
	
		controller: 'clienteController'
	}).when("/cidades", {
		templateUrl: 'static/view/cidade.html', 
		
		controller: 'cidadeController'
	}).when("/clientes/:clienteId", {
		templateUrl: 'static/view/cliente-detalhe.html', 
		
		controller: 'clienteDetalheController'
	}).when("/login", {
		templateUrl: 'static/view/login.html', 
		
		controller: 'loginController'
	}).when("/estados", {
		templateUrl: "static/view/estado.html",
		controller: "estadoController"
	}).otherwise({
		redirectTo:'/'
	});
	
	
		 $locationProvider.html5Mode(true);
		
	});
	