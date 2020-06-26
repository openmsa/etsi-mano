<?php
namespace Ubiqube\EtsiMano;

class NsLcmOpOccsSol005 extends BaseApi
{

	const BASE_URL = 'sol005/nslcm/v1/ns_lcm_op_occs';

	/**
	 * Read the NS LCM operation occurrence (Get Operation Status).
	 *
	 * @param string $nsLcmOpOccId
	 *		Identifier of a NS lifecycle management operation occurrence.
	 * @return mixte
	 */
	public function nsLcmOpOccsOperationStatusGet($nsLcmOpOccId)
	{
		$url_frag = self::BASE_URL . '/' . urlencode($nsLcmOpOccId);
		return json_decode($this->doGet($url_frag), 1);
	}

	/**
	 * Poll NS LCM Operation occurrence.
	 *
	 * @param string $nsLcmOpOccId
	 *		Identifier of a NS lifecycle management operation occurrence.
	 * @return mixte
	 */
	public function nsLcmOpOccsCompletionWait($nsLcmOpOccId, $timeout = 60)
	{
		$nsLcmOpOccs = $this->nsLcmOpOccsOperationStatusGet($nsLcmOpOccId);
		$state = $nsLcmOpOccs['operationState'];
		while($timeout > 0) {
			$nsLcmOpOccs = $this->nsLcmOpOccsOperationStatusGet($nsLcmOpOccId);	
			$state = $nsLcmOpOccs['operationState'];
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
		return json_encode($nsLcmOpOccs);
	}
}

