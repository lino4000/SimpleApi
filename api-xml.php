<?php
Class ApiServer{

	protected $con, $table;

	public function __construct($con) {
		$this->con = new mysqli($con['host'], $con['user'], $con['pass'], $con['db']);
		if (mysqli_connect_error()) {
			die('Connect Error (' . mysqli_connect_errno() . ') '. mysqli_connect_error());
		}
		$this->table = $con['table'];
	}

	public function show($params) {
		$result = $this->con->query($this->formatQuery($params));
		if ($this->con->error){
			return $this->con->error;
		}

		return $this->formatXml($result);
	}

	function formatQuery($params){
		$query = "SELECT * FROM ".$this->table;
		if (!empty($params)){
			$where = $params->where;
			$ordby = $params->ordby;
			$limit = $params->limit;
			$query .= ($where != '') ? ' WHERE '.$where : '';
			$query .= ($ordby != '') ? ' ORDER BY '.$ordby : '';
			$query .= ($limit != '') ? ' LIMIT '.$limit : '';
		};
		return $query;
	}

	protected function formatXml($result){
		$resp = simplexml_load_string('<return />');
		while($row = $result->fetch_assoc()){
			$prog = $resp->addChild('row');
			foreach($row as $key => $value){
				$prog->addChild($key,$value);
			}
		};
		return $resp->asXML();
	}
}

/*
<?xml version="1.0" encoding="UTF-8"?>
<query>
	<table>test</table>
	<where></where>
	<ordby></ordby>
	<limit></limit>
</query>
*/

$token_aceito = 'hFXyCmQf3HQlWNhc0dqHy40oHqYzAj';
if( $token_aceito == $_POST['token']){
	$params = (!empty($_POST['params'])) ? simplexml_load_string($_POST['params']) : '';
	$api = new ApiServer(array(
		'host' => 'localhost', // localhost | Se errado: "Connect Error (2002) php_network_getaddresses: getaddrinfo failed: Este host não é conhecido."
		'user' => 'simpleapi', // usuário | Se errado: "Connect Error (1045) Access denied for user 'user'@'localhost' (using password: YES)"
		'pass' => 'api123', // senha | Se errado: "Connect Error (1045) Access denied for user 'user'@'localhost' (using password: YES)"
		'db' => 'simpleapi',   // nome do banco de dados | Se errado: "Connect Error (1044) Access denied for user 'user'@'localhost' to database 'database_name'"
		'table' => 'test' // nome da tabela | Se errado: Table 'banco_de_dados.tabela' doesn't exist
	));
	echo $api->show($params);
}else{
	// Talvez seja interessante fazer algum log por motivos de segurança
	// para bloquear ip's de quem tenta acessar indevidamente.

	// Retornando 404 para tentar esconder a existência do arquivo.
	// Se quiser estilizar sua página 404, coloque a saída do HTML abaixo do header().
	header('HTTP/1.0 404 Not Found');
}