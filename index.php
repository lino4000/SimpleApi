<?php
	Class ApiClient{

		protected $token, $url;

		public function __construct($token,$url){
			$this->token = $token;
			$this->url = $url;
		}

		public function getInfo($query = null){
			if (!empty($query)){
				$params = $this->formatXml($query);
				return $this->_getInfo($params);
			};
			return $this->_getInfo();
		}

		protected function formatXml($query){
			$params = simplexml_load_string('<query />');
			foreach($query as $key => $value){
				$params->addChild($key,$value);
			}
			return $params;
		}

		protected function _getInfo($params = null){
			$curl_fields = array('token' => $this->token);
			if (!empty($params))
				$curl_fields['params'] = $params->asXML();

			$curl_opt = array(
				CURLOPT_URL => $this->url,
				CURLOPT_POSTFIELDS => http_build_query($curl_fields),
				CURLOPT_RETURNTRANSFER => true
			);

			$curl = curl_init();
			curl_setopt_array($curl, $curl_opt);
			$info = curl_exec($curl);
			curl_close($curl);
			return simplexml_load_string($info);
		}
	};

	$api = new ApiClient('hFXyCmQf3HQlWNhc0dqHy40oHqYzAj', 'http://localhost/lino4000/SimpleApi/api-xml.php');
//	error_reporting(E_ERROR);
	$retorno = $api->getInfo(array(
		'table' => "test",
		'where' => "",
		'ordby' => "",
		'limit' => ""
	));
	echo $retorno->asXML();