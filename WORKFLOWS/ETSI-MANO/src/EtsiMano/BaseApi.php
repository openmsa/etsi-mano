<?php
namespace Ubiqube\EtsiMano;

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
		$this->setParameters($ch);
		$response = curl_exec($ch);
		$this->checkError($ch, $_url, $response);
		curl_close($ch);
		return $response;
	}

	protected function doPost($_url, $_body)
	{
		$ch = curl_init();
		curl_setopt($ch, CURLOPT_URL, $this->baseUrl . $_url);
		$this->setParameters($ch);
		curl_setopt($ch, CURLOPT_POST, 1);
		curl_setopt($ch, CURLOPT_POSTFIELDS, $_body);
		$response = curl_exec($ch);
		$this->checkError($ch, $_url, $response);
		curl_close($ch);
		return $response;
	}

	protected function doPatch($_url, $_body)
	{
		$ch = curl_init();
		curl_setopt($ch, CURLOPT_URL, $this->baseUrl . $_url);
		$this->setParameters($ch);
		curl_setopt($ch, CURLOPT_POST, 1);
		curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'PATCH');
		curl_setopt($ch, CURLOPT_POSTFIELDS, $_body);
		$response = curl_exec($ch);
		$this->checkError($ch, $_url, $response);
		curl_close($ch);
		return $response;
	}

	protected function doDelete($_url)
	{
		$ch = curl_init();
		curl_setopt($ch, CURLOPT_URL, $this->baseUrl . $_url);
		$this->setParameters($ch);
		curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'DELETE');
		$response = curl_exec($ch);
		$this->checkError($ch, $_url, $response);
		curl_close($ch);
		return $response;
	}

	private function setParameters($_ch)
	{
		curl_setopt($_ch, CURLOPT_USERPWD, 'ncroot:ubiqube');
		curl_setopt($_ch, CURLOPT_HTTPHEADER, array(
			'Content-Type: application/json'
		));
		curl_setopt($_ch, CURLOPT_RETURNTRANSFER, 1);
	}

	private function checkError($_ch, $_url, $_response)
	{
		$httpCode = curl_getinfo($_ch, CURLINFO_HTTP_CODE);
		$error = curl_error($_ch);
		if ($httpCode < 200 || $httpCode > 299) {
			if ($httpCode == 0) {
				curl_close($_ch);
				throw new \Exception('Error ' . $this->baseUrl . $_url . ' Code: ' . $httpCode . ' Error: ' . $error);
			} else {
				curl_close($_ch);
				throw new ManoException($_response);
			}
		}
	}
}
