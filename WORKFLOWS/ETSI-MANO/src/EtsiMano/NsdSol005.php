<?php
namespace Ubiqube\EtsiMano;

class NsdSol005 extends BaseApi
{

	/**
	 * Return a list of NFVO ns descriptor.
	 *
	 * @return mixed List<NsDescriptorsNsdInfoIdGetResponse>
	 */
	public function nsDescriptorsGet()
	{
		$url_frag = 'sol005/nsd/v1/ns_descriptors';
		return json_decode($this->doGet($url_frag), 1);
	}

	/**
	 * Return an individual NSD.
	 *
	 * @param string $nsdInfoId
	 *        	The NSD Id
	 * @return mixed NsDescriptorsNsdInfo
	 */
	public function nsDescriptorsNsdInfoIdGet($nsdInfoId)
	{
		$url_frag = 'sol005/nsd/v1/ns_descriptors/' . $nsdInfoId;
		return json_decode($this->doGet($url_frag), 1);
	}

	/**
	 *
	 * @param string $nsdInfoId
	 *        	The NSD Id.
	 * @return mixed Zip if NSD have multi file, plain file otherwise.
	 */
	public function nsDescriptorsNsdInfoIdNsdContentGet($nsdInfoId)
	{
		$url_frag = 'sol005/nsd/v1/ns_descriptors/' . $nsdInfoId . '/nsd_content';
		return $this->doGet($url_frag);
	}
}

