<?php
use PHPUnit\Framework\TestCase;
use Ubiqube\EtsiMano\VnfPkgSol005;
use Ubiqube\EtsiMano\ManoException;

final class VnfPkgSol005Test extends TestCase
{

	private $vnfPkg;

	public function __construct()
	{
		$this->vnfPkg = new VnfPkgSol005('http://localhost:8380/ubi-etsi-mano/');
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

	public function testException()
	{
		// $this->setExpectedException(ManoException::class);
		$this->vnfPkg->vnfPackagesVnfPkgIdDelete('12345');
	}

	public function testOnboarding()
	{
		$body = file_get_contents(__DIR__ . '/stubs/vnf-pkg.json');
		$res = $this->vnfPkg->vnfPackagesPost($body);
		$id = $res['VnfPkgInfo']['id'];

		$this->vnfPkg->vnfPackagesVnfPkgIdPackageContentPut($id, '{}');
	}

	public function testExpose()
	{
		$res = $this->vnfPkg->exposeDocument('VNFD', 'b0b7d593-9a4d-407d-90fa-dfd17599bf65');
		$url = 'http://localhost:8380/ubi-etsi-mano/download/' . $res['id'];
	}
}



