<?php
namespace Ubiqube\EtsiMano;

class VnfLcmSol003 extends BaseApi
{

	const BASE_URL = 'sol003/vnflcm/v1/vnf_instances';

	/**
	 * Create VNF instance resource.
	 *
	 * @param string $body
	 *
	 * @return unknown
	 */
	public function vnfLcmCreateInstance($_body)
	{
		$url_frag = self::BASE_URL;
		return json_decode($this->doPost($url_frag, $_body), 1);
	}

	/**
         * Scale To Level VNF instance.
         *
         * @param string $body
         *
         * @return unknown
         */
        public function vnfLcmScaleToLevelInstanceVnf($vnfInstanceId, $_body)
        {
		$url_frag = self::BASE_URL . '/' . urlencode($vnfInstanceId) . '/scale_to_level';
                return json_decode($this->doPostReturnLocation($url_frag, $_body), 1);
        }

	/**
         * Scale VNF instance.
         *
         * @param string $body
         *
         * @return unknown
         */
        public function vnfLcmScaleInstanceVnf($vnfInstanceId, $_body)
        {
		$url_frag = self::BASE_URL . '/' . urlencode($vnfInstanceId) . '/scale';
                return json_decode($this->doPostReturnLocation($url_frag, $_body), 1);
        }

	/**
	 * Instantiate VNF.
	 *
	 * @param string $vnfInstanceId
	 * @param string $_body
	 *
	 * @return unknown
	 */
	public function vnfLcmInstantiateVnf($vnfInstanceId, $_body = '{}')
	{
		$url_frag = self::BASE_URL . '/' . urlencode($vnfInstanceId) . '/instantiate';
		return json_decode($this->doPostReturnLocation($url_frag, $_body), 1);
	}

	/**
         * Terminate VNF instance.
         *
         * @param string $_body
         *
         * @return unknown
         */
        public function vnfLcmTerminateVnf($vnfInstanceId, $_body = '{}')
        {
                $url_frag = self::BASE_URL . '/' . urlencode($vnfInstanceId) . '/terminate';
                return json_decode($this->doPostReturnLocation($url_frag, $_body), 1);
        }

	/**
         * Delete VNF Instance.
         *
         * @return no content
         */
        public function vnfLcmDeleteInstanceOfVnf($vnfInstanceId)
        {
                $url_frag = self::BASE_URL . '/' . urlencode($vnfInstanceId);
                return json_decode($this->doDelete($url_frag), 1);
        }	
}

