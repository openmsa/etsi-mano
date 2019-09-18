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
		$this->assertEquals('DISABLED', $res['VnfPkgInfo']['operationalState']);

		// PATCH the entity
		$res = $this->vnfPkg->vnfPackagesVnfPkgIdPatch($id, '{ "operationalState": "ENABLED" }');
		$this->assertEquals('ENABLED', $res['VnfPkgInfo']['operationalState']);

		// We can't delete an ENABLED one.
		$res = $this->vnfPkg->vnfPackagesVnfPkgIdPatch($id, '{ "operationalState": "DISABLED" }');
		$this->assertEquals('DISABLED', $res['VnfPkgInfo']['operationalState']);

		$this->vnfPkg->vnfPackagesVnfPkgIdDelete($id);
	}
}



