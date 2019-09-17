<?php
namespace Ubiqube\EtsiMano;

class VnfPkgSol005 extends BaseApi
{

	const BASE_URL = 'sol005/vnfpkgm/v1/vnf_packages';

	/**
	 *
	 * @param string $body
	 * @return unknown
	 */
	public function vnfPackagesPost($body)
	{
		$url_frag = self::BASE_URL;
		return json_decode($this->doPost($url_frag, $body), 1);
	}

	/**
	 *
	 * @param string $_vnfPkgId
	 * @param string $_body
	 * @return void
	 */
	public function vnfPackagesVnfPkgIdPatch($_vnfPkgId, $_body)
	{
		$url_frag = self::BASE_URL . '/' . urlencode($_vnfPkgId);
		return json_decode($this->doPatch($url_frag, $body));
	}
	
	public function vnfPackagesVnfPkgIdDelete($_vnfPkgId) {
		$url_frag = self::BASE_URL .'/' . $_vnfPkgId;
		return $this->doDelete($url_frag);
	}
}
