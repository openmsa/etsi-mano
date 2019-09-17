<?php
use PHPUnit\Framework\TestCase;
use Ubiqube\EtsiMano\VnfPkgSol005;

final class VnfPkgSol005Test extends TestCase
{

	private $vnfPkg;

	public function __construct()
	{
		$this->vnfPkg = new VnfPkgSol005('http://localhost:8380/ubi-etsi-mano-api/');
	}

	/**
	 *
	 * @test
	 */
	public function testCrud()
	{
		$body = file_get_contents(__DIR__ . '/stubs/vnf-pkg.json');
		$res = $this->vnfPkg->vnfPackagesPost($body);
		$id = $res['VnfPkgInfo']['id'];
		$this->assertNotNull($id);
		
		$this->vnfPkg->vnfPackagesVnfPkgIdDelete($id);
	}
}



