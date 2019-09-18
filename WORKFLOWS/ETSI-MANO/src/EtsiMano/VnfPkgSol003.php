<?php
namespace Ubiqube\EtsiMano;

class VnfPkgSol003 extends BaseApi
{

	const BASE_URL = 'sol003/vnfpkgm/v1/vnf_packages';

	/**
	 * Get a VNF Package by ID.
	 *
	 * @param string $vnf_pkg_id
	 *        	Vnf Package ID.
	 * @return mixed
	 */
	public function vnfPackagesVnfPkgIdGet($vnf_pkg_id)
	{
		$url_frag = self::BASE_URL . '/' . urlencode($vnf_pkg_id);
		return json_decode($this->doGet($url_frag), 1);
	}

	/**
	 * Get A list of all VNF package in the NFVO.
	 *
	 * @return mixed
	 */
	public function vnfPackagesGet()
	{
		$url_frag = self::BASE_URL;
		return json_decode($this->doGet($url_frag), 1);
	}

	/**
	 * Get a Single artifact from a given VNF Package.
	 *
	 * @param string $vnfPkgId
	 *        	The package ID.
	 * @param string $artifactPath
	 *        	The file name.
	 * @return mixed A single file or a ZIP
	 */
	public function vnfPackagesVnfPkgIdArtifactsArtifactPathGet($vnfPkgId, $artifactPath)
	{
		$url_frag = self::BASE_URL . '/' . urlencode($vnfPkgId) . '/artifacts/' . urlencode($artifactPath);
		return $this->doGet($url_frag);
	}
}

