<?php
namespace Ubiqube\EtsiMano;

class NsLcmSol005 extends BaseApi
{

	const BASE_URL = 'sol005/nslcm/v1/ns_instances';

	/**
	 * Create NS instance resource.
	 *
	 * @param string $body
	 *
	 * @return unknown
	 */
	public function nsLcmCreateInstance($_body)
	{
		$url_frag = self::BASE_URL;
		return json_decode($this->doPost($url_frag, $_body), 1);
	}

	/**
	 * Instantiate NS.
	 *
	 * @param string $nsInstanceId
	 * @param string $_body
	 *
	 * @return unknown
	 */
	public function nsLcmInstantiateNs($nsInstanceId, $_body = '{}')
	{
		$url_frag = self::BASE_URL . '/' . urlencode($nsInstanceId) . '/instantiate';
		return json_decode($this->doPostReturnLocation($url_frag, $_body), 1);
	}

	/**
         * Terminate NS instance.
         *
         * @param string $_body
         *
         * @return unknown
         */
        public function nsLcmTerminateNs($nsInstanceId, $_body = '{}')
        {
                $url_frag = self::BASE_URL . '/' . urlencode($nsInstanceId) . '/terminate';
                return json_decode($this->doPostReturnLocation($url_frag, $_body), 1);
        }

	/**
         * Delete NS Instance.
         *
         * @return no content
         */
        public function nsLcmDeleteInstanceOfNs($nsInstanceId)
        {
                $url_frag = self::BASE_URL . '/' . urlencode($nsInstanceId);
                return json_decode($this->doDelete($url_frag), 1);
        }	
}

