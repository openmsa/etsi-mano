<?php
namespace Ubiqube\EtsiMano;

class NsdSol005 extends BaseApi
{

	/**
	 * TODO should not use SOL005/ in URL.²
	 *
	 * @var string
	 */
	const BASE_URL = 'sol005/nsd/v1/ns_descriptors';

	/**
	 * Return a list of NFVO ns descriptor.
	 *
	 * @return mixed List<NsDescriptorsNsdInfoIdGetResponse>
	 */
	public function nsDescriptorsGet()
	{
		$url_frag = self::BASE_URL;
		return json_decode($this->doGet($url_frag), 1);
	}

	/**
	 * Return an individual NSD.
	 *
	 * @param string $_nsdInfoId
	 *        	The NSD Id
	 * @return mixed NsDescriptorsNsdInfo
	 */
	public function nsDescriptorsNsdInfoIdGet($_nsdInfoId)
	{
		$url_frag = self::BASE_URL . '/' . $_nsdInfoId;
		return json_decode($this->doGet($url_frag), 1);
	}

	/**
	 *
	 * @param string $_nsdInfoId
	 *        	The NSD Id.
	 * @return mixed Zip if NSD have multi file, plain file otherwise.
	 */
	public function nsDescriptorsNsdInfoIdNsdContentGet($_nsdInfoId)
	{
		$url_frag = self::BASE_URL . '/' . $_nsdInfoId . '/nsd_content';
		return $this->doGet($url_frag);
	}

	/**
	 *
	 * @param string $_body
	 * @return void
	 */
	public function nsDescriptorsPost($_body)
	{
		$url_frag = self::BASE_URL;
		return $this->doPost($url_frag, $_body);
	}

	/**
	 *
	 * @param string $_nsdInfoId
	 * @param string $_body
	 * @return unknown
	 */
	public function nsDescriptorsNsdInfoIdPatch($_nsdInfoId, $_body)
	{
		$url_frag = self::BASE_URL . '/' . $_nsdInfoId;
		return $this->doPost($url_frag, $_body);
	}

	public function nsDescriptorsNsdInfoIdDelete($_nsdInfoId)
	{
		$url_frag = self::BASE_URL . '/' . $_nsdInfoId;
		return $this->doDelete($url_frag);
	}
}

