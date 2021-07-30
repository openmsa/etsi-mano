<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/Common/Library/msa_common.php';

/**
 * List all the parameters required by the task
 */
function list_args()
{
  create_var_def('cnf_descriptor_content', 'Link');
}

logToFile(debug_dump($context, "MSA CONTEXT:\n"));

$cnf_descriptor_content = '';
$cnf_descriptor_file = '';
$ret = "No CNF Descriptor defined for this service instance.";

if (isset($context['file'])) {
	$cnf_descriptor_file = $context['file'];
	$cnf_descriptor_content = 'Datafiles/' . $cnf_descriptor_file;
	$ret = "Query the link by clicking on this link: " . $cnf_descriptor_content;
	$context['cnf_descriptor_content'] = $cnf_descriptor_content;
	
	# create .meta file
	$metadata = '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
	<metadata>
		<map>
			<entry>
				<key>DATE_MODIFICATION</key>
				<value>1588580618765</value>
			</entry>
			<entry>
				<key>REPOSITORY</key>
				<value>CommandDefinition</value>
			</entry>
			<entry>
				<key>DATE_CREATION</key>
				<value>1588580618757</value>
			</entry>
			<entry>
				<key>MODEL</key>
				<value>134</value>
			</entry>
			<entry>
				<key>TAG</key>
			</entry>
			<entry>
				<key>FILE_TYPE</key>
				<value>text</value>
			</entry>
			<entry>
				<key>MANUFACTURER</key>
				<value>28</value>
			</entry>
			<entry>
				<key>TYPE</key>
				<value>FILE</value>
			</entry>
			<entry>
				<key>COMMENT</key>
			</entry>
		</map>
	</metadata>
	';
	$basename = basename($cnf_descriptor_file);
	$dirname = dirname($cnf_descriptor_file);
	file_put_contents('/opt/fmc_repository/Datafiles/' . $dirname . '/.meta_' . $basename, $metadata);
}

task_exit(ENDED, $ret);

?>
