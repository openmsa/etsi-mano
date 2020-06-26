<?php
namespace Ubiqube\EtsiMano;

class VnfLcmOpOccsSol003 extends BaseApi
{

	const BASE_URL = 'sol003/vnflcm/v1/vnf_lcm_op_occs';

	/**
	 * Read the VNF LCM operation occurrence (Get Operation Status).
	 *
	 * @param string $vnfLcmOpOccId
	 *		Identifier of a VNF lifecycle management operation occurrence.
	 * @return mixte
	 */
	public function vnfLcmOpOccsOperationStatusGet($vnfLcmOpOccId)
	{
		$url_frag = self::BASE_URL . '/' . urlencode($vnfLcmOpOccId);
		return json_decode($this->doGet($url_frag), 1);
	}

	/**
	 * Poll VNF LCM Operation occurrence.
	 *
	 * @param string $vnfLcmOpOccId
	 *		Identifier of a VNF lifecycle management operation occurrence.
	 * @return mixte
	 */
	public function vnfLcmOpOccsCompletionWait($vnfLcmOpOccId, $timeout = 60)
	{
		$vnfLcmOpOccs = $this->vnfLcmOpOccsOperationStatusGet($vnfLcmOpOccId);
		$state = $vnfLcmOpOccs['operationState'];
		while($timeout > 0) {
			$vnfLcmOpOccs = $this->vnfLcmOpOccsOperationStatusGet($vnfLcmOpOccId);	
			$state = $vnfLcmOpOccs['operationState'];
			$timeout--;
			sleep(1);
			
			switch ($state) {
				case 'STARTING':
				case 'PROCESSING':
				case 'COMPLETED':
					break 2;
				case 'FAILED_TEMP':
					break 2;
				case 'PARTIALLY_COMPLETED':
					break 2;
				case 'FAILED':
					break 2;
				case 'ROLLING_BACK':
				case 'ROLLED_BACK':
					break 2;
			}
		} 
		return json_encode($vnfLcmOpOccs);
	}
}

