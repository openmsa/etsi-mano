<?php
namespace Ubiqube\EtsiMano;

use Exception;

class BaseApi
{

	private $baseUrl = 'http://localhost:8380/';

	function __construct($_baseUrl)
	{
		$this->baseUrl = $_baseUrl;
	}

	protected function doGet($_url)
	{
		$ch = curl_init();
		curl_setopt($ch, CURLOPT_URL, $this->baseUrl . $_url);
		curl_setopt($ch, CURLOPT_USERPWD, 'ncroot:ubiqube');
		curl_setopt($ch, CURLOPT_HTTPHEADER, array(
			'Content-Type: application/json'
		));
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
		$response = curl_exec($ch);
		$httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
		curl_close($ch);
		if ($httpCode < 200 || $httpCode > 299) {
			throw new Exception('Error ' . $_url . ' Code: ' . $httpCode);
		}
		return $response;
	}
}