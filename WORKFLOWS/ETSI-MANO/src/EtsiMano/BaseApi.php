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

	protected function doPut($_url, $_body)
	{
		$ch = curl_init();
		curl_setopt($ch, CURLOPT_URL, $this->baseUrl . $_url);
		$this->setParameters($ch);
		curl_setopt($ch, CURLOPT_POST, 1);
		curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'PUT');
		curl_setopt($ch, CURLOPT_POSTFIELDS, $_body);
		$response = curl_exec($ch);
		$this->checkError($ch, $_url, $response);
		curl_close($ch);
		return $response;
	}

	protected function doPutMp($_url, $_content)
	{
		// Works in php 5.3.3 but not in 7.3.x
		$ch = curl_init();
		curl_setopt($ch, CURLOPT_URL, $this->baseUrl . $_url);
		curl_setopt($ch, CURLOPT_USERPWD, 'ncroot:ubiqube');
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
		curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'PUT');
		curl_setopt($ch, CURLOPT_POSTFIELDS, array(
			'file' => $_content
		));
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
		curl_setopt($_ch, CURLOPT_RETURNTRANSFER, 1);
		curl_setopt($_ch, CURLOPT_HTTPHEADER, array(
			'Content-Type: application/json'
		));
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
