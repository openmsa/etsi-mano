<?php
use PHPUnit\Framework\TestCase;
use Ubiqube\EtsiMano\NsdSol005;
use Ubiqube\EtsiMano\ManoException;

final class NsdSol005Test extends TestCase
{

	private $nsdApi;

	public function __construct()
	{
		$this->nsdApi = new NsdSol005('http://localhost:8380/ubi-etsi-mano/');
	}

	public function testCrud()
	{
		$payload = array(
			'CreateNsdInfoRequest' => array(
				'userDefinedData' => array(
					'name' => 'MyName',
					'customerId' => 'TMA12',
					'vimId' => 'TMA213',
					'vnfPkgIds' => array()
				)
			)
		);
		$payJson = json_encode($payload);
		$response = $this->nsdApi->nsDescriptorsPost($payJson);
		$id = $response['id'];
		$this->assertNotNull($id);
		$this->assertEquals('DISABLED', $response['nsdOperationalState']);
		$this->assertEquals('CREATED', $response['nsdOnboardingState']);
		
		$this->nsdApi->nsDescriptorsNsdInfoIdDelete($id);
	}
}