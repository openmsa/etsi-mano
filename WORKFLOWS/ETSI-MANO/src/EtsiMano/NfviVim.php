<?php
namespace Ubiqube\EtsiMano;

class NfviVim extends BaseApi
{

	const BASE_URL = 'admin/vim';

	/**
	 * Register VIM.
	 *
	 * @param string $body
	 *		Vim access details.
	 * @return mixte
	 */
	public function nfviVimRegister($_body)
	{
		$url_frag = self::BASE_URL . '/register';
		return json_decode($this->doPost($url_frag, $_body), 1);
	}

	/**
         * Delete VIM.
         *
         * @param string $vimId
         *              Vim identifier.
         * @return mixte
         */
        public function nfviVimDelete($vimId)
        {
                $url_frag = self::BASE_URL . '/' . urlencode($vimId);
                return json_decode($this->doDelete($url_frag), 1);
        }

	/**
         * Get VIM list.
         *
         * @return mixte
         */
        public function nfviVimListGet()
        {
                $url_frag = self::BASE_URL;
                return json_decode($this->doPost($url_frag), 1);
        }
}

