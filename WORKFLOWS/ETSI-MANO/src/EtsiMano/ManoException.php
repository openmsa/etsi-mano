<?php
namespace Ubiqube\EtsiMano;

class ManoException extends \Exception
{

	private $exception;

	private $object;

	public function __construct($message, $except = null)
	{
		$code = 0;
		$messageFinal = '';
		if (isset($message) && null != $message) {
			$this->object = json_decode($message);
			$code = $this->object->status;
			$messageFinal = $this->object->detail;
		}
		if (isset($except) && ($except instanceof Exception)) {
			$this->exception = $except;
		}
		parent::__construct($messageFinal, $code);
	}
}