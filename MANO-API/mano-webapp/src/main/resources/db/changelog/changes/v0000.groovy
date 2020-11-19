databaseChangeLog {
  changeSet(id: '''1591462712136-1''', author: '''olivier (generated)''') {
    createTable(tableName: '''subscription_subscription_filter''') {
      column(name: '''subscription_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''subscription_filter_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
    }
  }

  changeSet(id: '''1591462712136-2''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_instantiation_levels''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instantiation_levels_pkey''')
      }
      column(name: '''level_name''', type: '''VARCHAR(255)''')
      column(name: '''scale_info_level''', type: '''INTEGER''') {
        constraints(nullable: false)
      }
      column(name: '''scale_info_name''', type: '''VARCHAR(255)''')
      column(name: '''vnf_package_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-3''', author: '''olivier (generated)''') {
    createTable(tableName: '''ns_sap_security_groups''') {
      column(name: '''ns_sap_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ns_sap_security_groups_pkey''')
      }
      column(name: '''security_groups_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ns_sap_security_groups_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-4''', author: '''olivier (generated)''') {
    createTable(tableName: '''nsd_package_vnf_package''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''nsd_package_vnf_package_pkey''')
      }
      column(name: '''tosca_id''', type: '''VARCHAR(255)''')
      column(name: '''tosca_name''', type: '''VARCHAR(255)''')
      column(name: '''nsd_package_id''', type: '''UUID''')
      column(name: '''vnf_package_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-5''', author: '''olivier (generated)''') {
    createTable(tableName: '''vim_connection_information_extra''') {
      column(name: '''vim_connection_information_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vim_connection_information_extra_pkey''')
      }
      column(name: '''extra''', type: '''VARCHAR(255)''')
      column(name: '''extra_key''', type: '''VARCHAR(255)''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vim_connection_information_extra_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-6''', author: '''olivier (generated)''') {
    createTable(tableName: '''nsd_package_nsd_package''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''nsd_package_nsd_package_pkey''')
      }
      column(name: '''tosca_id''', type: '''VARCHAR(255)''')
      column(name: '''tosca_name''', type: '''VARCHAR(255)''')
      column(name: '''child_id''', type: '''UUID''')
      column(name: '''parent_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-7''', author: '''olivier (generated)''') {
    createTable(tableName: '''vdu_instantiation_level''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vdu_instantiation_level_pkey''')
      }
      column(name: '''level_name''', type: '''VARCHAR(255)''')
      column(name: '''number_of_instances''', type: '''INTEGER''') {
        constraints(nullable: false)
      }
      column(name: '''vnf_compute_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-8''', author: '''olivier (generated)''') {
    createTable(tableName: '''ip_over_ethernet_address_data_ip_addresses_entity_fixed_address''') {
      column(name: '''ip_over_ethernet_address_data_ip_addresses_entity_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''fixed_addresses''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-9''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_instantiated_base_metadata''') {
      column(name: '''vnf_instantiated_base_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instantiated_base_metadata_pkey''')
      }
      column(name: '''metadata''', type: '''VARCHAR(255)''')
      column(name: '''metadata_key''', type: '''VARCHAR(255)''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instantiated_base_metadata_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-10''', author: '''olivier (generated)''') {
    createTable(tableName: '''grants_request_temp_resources''') {
      column(name: '''grants_request_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grants_request_temp_resources_pkey''')
      }
      column(name: '''temp_resources_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grants_request_temp_resources_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-11''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_instance_vnf_configurable_properties''') {
      column(name: '''vnf_instance_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instance_vnf_configurable_properties_pkey''')
      }
      column(name: '''vnf_configurable_properties''', type: '''VARCHAR(255)''')
      column(name: '''vnf_configurable_properties_key''', type: '''VARCHAR(255)''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instance_vnf_configurable_properties_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-12''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_compute_storages''') {
      column(name: '''vnf_compute_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''storages''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-13''', author: '''olivier (generated)''') {
    createTable(tableName: '''grant_response_vim_connections''') {
      column(name: '''grant_response_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grant_response_vim_connections_pkey''')
      }
      column(name: '''vim_connections_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grant_response_vim_connections_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-14''', author: '''olivier (generated)''') {
    createTable(tableName: '''ip_over_ethernet_address_data_ip_addresses_entity''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ip_over_ethernet_address_data_ip_addresses_entity_pkey''')
      }
      column(name: '''max_address''', type: '''VARCHAR(255)''')
      column(name: '''min_address''', type: '''VARCHAR(255)''')
      column(name: '''num_dynamic_addresses''', type: '''INTEGER''')
      column(name: '''subnet_id''', type: '''VARCHAR(255)''')
      column(name: '''type''', type: '''VARCHAR(255)''')
      column(name: '''ip_over_ethernet_address_data_entity_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-15''', author: '''olivier (generated)''') {
    createTable(tableName: '''grants_request_remove_resources''') {
      column(name: '''grants_request_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grants_request_remove_resources_pkey''')
      }
      column(name: '''remove_resources_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grants_request_remove_resources_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-16''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_instance''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instance_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''flavour_id''', type: '''VARCHAR(255)''')
      column(name: '''instantiation_level_id''', type: '''VARCHAR(255)''')
      column(name: '''localization_language''', type: '''VARCHAR(255)''')
      column(name: '''vnf_state''', type: '''VARCHAR(255)''')
      column(name: '''instantiation_state''', type: '''VARCHAR(255)''')
      column(name: '''vnf_instance_description''', type: '''VARCHAR(255)''')
      column(name: '''vnf_instance_name''', type: '''VARCHAR(255)''')
      column(name: '''vnf_product_name''', type: '''VARCHAR(255)''')
      column(name: '''vnf_provider''', type: '''VARCHAR(255)''')
      column(name: '''vnf_software_version''', type: '''VARCHAR(255)''')
      column(name: '''vnfd_id''', type: '''VARCHAR(255)''')
      column(name: '''vnfd_version''', type: '''VARCHAR(255)''')
      column(name: '''ns_instance_id''', type: '''UUID''')
      column(name: '''vnf_pkg_id''', type: '''UUID''')
      column(name: '''vnf_instance_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-17''', author: '''olivier (generated)''') {
    createTable(tableName: '''ns_virtual_link''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ns_virtual_link_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''ns_vl_profile_id''', type: '''UUID''')
      column(name: '''nsd_package_id''', type: '''UUID''')
      column(name: '''vl_connectivity_type_id''', type: '''UUID''')
      column(name: '''ns_virtual_links_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-18''', author: '''olivier (generated)''') {
    createTable(tableName: '''ip_over_ethernet_address_data_entity''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ip_over_ethernet_address_data_entity_pkey''')
      }
      column(name: '''mac_address''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-19''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_instantiated_base_vnfc_cp_info''') {
      column(name: '''vnf_instantiated_compute_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instantiated_base_vnfc_cp_info_pkey''')
      }
      column(name: '''vnfc_cp_info_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instantiated_base_vnfc_cp_info_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-20''', author: '''olivier (generated)''') {
    createTable(tableName: '''software_image''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''software_image_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''algorithm''', type: '''VARCHAR(255)''')
      column(name: '''hash''', type: '''VARCHAR(255)''')
      column(name: '''container_format''', type: '''VARCHAR(255)''')
      column(name: '''disk_format''', type: '''VARCHAR(255)''')
      column(name: '''image_path''', type: '''VARCHAR(255)''')
      column(name: '''min_disk''', type: '''BIGINT''') {
        constraints(nullable: false)
      }
      column(name: '''min_ram''', type: '''BIGINT''') {
        constraints(nullable: false)
      }
      column(name: '''name''', type: '''VARCHAR(255)''')
      column(name: '''provider''', type: '''VARCHAR(255)''')
      column(name: '''size''', type: '''BIGINT''') {
        constraints(nullable: false)
      }
      column(name: '''version''', type: '''VARCHAR(255)''')
      column(name: '''vim_id''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-21''', author: '''olivier (generated)''') {
    createTable(tableName: '''ns_cp_protocol_data''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ns_cp_protocol_data_pkey''')
      }
      column(name: '''associated_layer_protocol''', type: '''VARCHAR(255)''')
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''protocol_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-22''', author: '''olivier (generated)''') {
    createTable(tableName: '''grant_response_update_resources''') {
      column(name: '''grant_response_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grant_response_update_resources_pkey''')
      }
      column(name: '''update_resources_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grant_response_update_resources_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-23''', author: '''olivier (generated)''') {
    createTable(tableName: '''ext_managed_virtual_link_data_entity''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ext_managed_virtual_link_data_entity_pkey''')
      }
      column(name: '''resource_id''', type: '''VARCHAR(255)''')
      column(name: '''resource_provider_id''', type: '''VARCHAR(255)''')
      column(name: '''vim_connection_id''', type: '''VARCHAR(255)''')
      column(name: '''vnf_virtual_link_desc_id''', type: '''VARCHAR(255)''')
      column(name: '''grants_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-24''', author: '''olivier (generated)''') {
    createTable(tableName: '''grant_response_remove_resources''') {
      column(name: '''grant_response_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grant_response_remove_resources_pkey''')
      }
      column(name: '''remove_resources_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grant_response_remove_resources_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-25''', author: '''olivier (generated)''') {
    createTable(tableName: '''task''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''task_pkey''')
      }
      column(name: '''object_id''', type: '''VARCHAR(255)''')
      column(name: '''status''', type: '''INTEGER''')
    }
  }

  changeSet(id: '''1591462712136-26''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_storage''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_storage_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''size''', type: '''BIGINT''') {
        constraints(nullable: false)
      }
      column(name: '''state''', type: '''VARCHAR(255)''')
      column(name: '''tosca_id''', type: '''VARCHAR(255)''')
      column(name: '''tosca_name''', type: '''VARCHAR(255)''')
      column(name: '''type''', type: '''VARCHAR(255)''')
      column(name: '''software_image_id''', type: '''UUID''')
      column(name: '''vnf_package_id''', type: '''UUID''')
      column(name: '''vnf_storage_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-27''', author: '''olivier (generated)''') {
    createTable(tableName: '''ns_instantiated_base''') {
      column(name: '''dtype''', type: '''VARCHAR(31)''') {
        constraints(nullable: false)
      }
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ns_instantiated_base_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''change_result''', type: '''VARCHAR(255)''')
      column(name: '''change_type''', type: '''VARCHAR(255)''')
      column(name: '''end_time''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''instantiation_level''', type: '''VARCHAR(255)''')
      column(name: '''resource_id''', type: '''VARCHAR(255)''')
      column(name: '''start_time''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''ns_instance_id''', type: '''VARCHAR(255)''')
      column(name: '''pnf_id''', type: '''VARCHAR(255)''')
      column(name: '''pnf_name''', type: '''VARCHAR(255)''')
      column(name: '''pnf_profile_id''', type: '''VARCHAR(255)''')
      column(name: '''pnfd_id''', type: '''VARCHAR(255)''')
      column(name: '''sap_instance_id''', type: '''VARCHAR(255)''')
      column(name: '''sap_name''', type: '''VARCHAR(255)''')
      column(name: '''ns_virtual_link_instance_id''', type: '''VARCHAR(255)''')
      column(name: '''vl_profile_id''', type: '''UUID''')
      column(name: '''instance_description''', type: '''VARCHAR(255)''')
      column(name: '''vnf_profile_id''', type: '''VARCHAR(255)''')
      column(name: '''vnffg_instance_id''', type: '''VARCHAR(255)''')
      column(name: '''vnffgd_id''', type: '''VARCHAR(255)''')
      column(name: '''ns_lcm_op_occs_id''', type: '''UUID''')
      column(name: '''nsd_package_id''', type: '''UUID''')
      column(name: '''sapd_id''', type: '''UUID''')
      column(name: '''ns_virtual_link_desc_id''', type: '''UUID''')
      column(name: '''nsd_package_vnf_package_id''', type: '''UUID''')
      column(name: '''vnf_instance_id''', type: '''UUID''')
      column(name: '''affected_vnfs_id''', type: '''UUID''')
      column(name: '''affected_vnffgs_id''', type: '''UUID''')
      column(name: '''affected_vls_id''', type: '''UUID''')
      column(name: '''affected_saps_id''', type: '''UUID''')
      column(name: '''affected_pnfs_id''', type: '''UUID''')
      column(name: '''affected_nss_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-28''', author: '''olivier (generated)''') {
    createTable(tableName: '''ns_lcm_op_occs''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ns_lcm_op_occs_pkey''')
      }
      column(name: '''cancel_mode''', type: '''VARCHAR(255)''')
      column(name: '''detail''', type: '''VARCHAR(500)''')
      column(name: '''instance''', type: '''VARCHAR(255)''')
      column(name: '''status''', type: '''BIGINT''')
      column(name: '''title''', type: '''VARCHAR(255)''')
      column(name: '''type''', type: '''VARCHAR(255)''')
      column(name: '''external_process_id''', type: '''VARCHAR(255)''')
      column(name: '''is_automatic_invocation''', type: '''BOOLEAN''')
      column(name: '''is_cancel_pending''', type: '''BOOLEAN''')
      column(name: '''lcm_operation_type''', type: '''VARCHAR(255)''')
      column(name: '''operation_params''', type: '''VARCHAR(255)''')
      column(name: '''operation_state''', type: '''VARCHAR(255)''')
      column(name: '''start_time''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''state_entered_time''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''ns_instance_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-29''', author: '''olivier (generated)''') {
    createTable(tableName: '''temporary_download''') {
      column(name: '''id''', type: '''VARCHAR(255)''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''temporary_download_pkey''')
      }
      column(name: '''creation_date''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''expiration_date''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''object_id''', type: '''UUID''')
      column(name: '''object_type''', type: '''INTEGER''')
    }
  }

  changeSet(id: '''1591462712136-30''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_package_additional_artifacts''') {
      column(name: '''vnf_package_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''artifact_path''', type: '''VARCHAR(255)''')
      column(name: '''algorithm''', type: '''VARCHAR(255)''')
      column(name: '''hash''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-31''', author: '''olivier (generated)''') {
    createTable(tableName: '''ext_cp_info''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ext_cp_info_pkey''')
      }
      column(name: '''associated_vnf_virtual_link_id''', type: '''VARCHAR(255)''')
      column(name: '''associated_vnfc_cp_id''', type: '''VARCHAR(255)''')
      column(name: '''cpd_id''', type: '''VARCHAR(255)''')
      column(name: '''ext_link_port_id_id''', type: '''UUID''')
      column(name: '''ext_cp_info_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-32''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_package_ns_instance''') {
      column(name: '''vnf_package_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_package_ns_instance_pkey''')
      }
      column(name: '''ns_instance_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_package_ns_instance_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-33''', author: '''olivier (generated)''') {
    createTable(tableName: '''zone_info_entity''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''zone_info_entity_pkey''')
      }
      column(name: '''resource_provider_id''', type: '''VARCHAR(255)''')
      column(name: '''vim_connection_id''', type: '''VARCHAR(255)''')
      column(name: '''zone_id''', type: '''VARCHAR(255)''')
      column(name: '''grants_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-34''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_instantiated_compute_affected_vnfc_cp_ids''') {
      column(name: '''vnf_instantiated_compute_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''affected_vnfc_cp_ids''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-35''', author: '''olivier (generated)''') {
    createTable(tableName: '''pnf_descriptor''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''pnf_descriptor_pkey''')
      }
      column(name: '''detail''', type: '''VARCHAR(500)''')
      column(name: '''instance''', type: '''VARCHAR(255)''')
      column(name: '''status''', type: '''BIGINT''')
      column(name: '''title''', type: '''VARCHAR(255)''')
      column(name: '''type''', type: '''VARCHAR(255)''')
      column(name: '''pnfd_id''', type: '''VARCHAR(255)''')
      column(name: '''pnfd_invariant_id''', type: '''VARCHAR(255)''')
      column(name: '''pnfd_name''', type: '''VARCHAR(255)''')
      column(name: '''pnfd_onboarding_state''', type: '''VARCHAR(255)''')
      column(name: '''pnfd_provider''', type: '''VARCHAR(255)''')
      column(name: '''pnfd_usage_state''', type: '''VARCHAR(255)''')
      column(name: '''pnfdersion''', type: '''VARCHAR(255)''')
      column(name: '''user_defined_data''', type: '''VARCHAR(255)''')
      column(name: '''pnfd_info_ids_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-36''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_compute_monitoring_parameters''') {
      column(name: '''vnf_compute_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_compute_monitoring_parameters_pkey''')
      }
      column(name: '''monitoring_parameters_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_compute_monitoring_parameters_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-37''', author: '''olivier (generated)''') {
    createTable(tableName: '''nsd_package_user_defined_data''') {
      column(name: '''nsd_package_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''nsd_package_user_defined_data_pkey''')
      }
      column(name: '''user_defined_data''', type: '''VARCHAR(255)''')
      column(name: '''user_defined_data_key''', type: '''VARCHAR(255)''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''nsd_package_user_defined_data_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-38''', author: '''olivier (generated)''') {
    createTable(tableName: '''grant_response_additional_params''') {
      column(name: '''grant_response_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grant_response_additional_params_pkey''')
      }
      column(name: '''additional_params''', type: '''VARCHAR(255)''')
      column(name: '''additional_params_key''', type: '''VARCHAR(255)''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grant_response_additional_params_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-39''', author: '''olivier (generated)''') {
    createTable(tableName: '''grants_request_compute_resource_flavours''') {
      column(name: '''grants_request_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''resource_provider_id''', type: '''VARCHAR(255)''')
      column(name: '''vim_connection_id''', type: '''VARCHAR(255)''')
      column(name: '''vim_flavour_id''', type: '''VARCHAR(255)''')
      column(name: '''vnfd_virtual_compute_desc_id''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-40''', author: '''olivier (generated)''') {
    createTable(tableName: '''link_port_info''') {
      column(name: '''id''', type: '''VARCHAR(255)''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''link_port_info_pkey''')
      }
      column(name: '''cp_instance_id''', type: '''VARCHAR(255)''')
      column(name: '''cp_instance_type''', type: '''INTEGER''')
      column(name: '''resource_id''', type: '''VARCHAR(255)''')
      column(name: '''resource_provider_id''', type: '''VARCHAR(255)''')
      column(name: '''vim_connection_id''', type: '''VARCHAR(255)''')
      column(name: '''vim_level_resource_type''', type: '''VARCHAR(255)''')
      column(name: '''vnf_link_ports_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-41''', author: '''olivier (generated)''') {
    createTable(tableName: '''networks''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''networks_pkey''')
      }
      column(name: '''cidr''', type: '''VARCHAR(255)''')
      column(name: '''end_range''', type: '''VARCHAR(255)''')
      column(name: '''start_range''', type: '''VARCHAR(255)''')
      column(name: '''vim_resource''', type: '''VARCHAR(255)''')
      column(name: '''vim_connection_information_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-42''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_instance_metadata''') {
      column(name: '''vnf_instance_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instance_metadata_pkey''')
      }
      column(name: '''metadata''', type: '''VARCHAR(255)''')
      column(name: '''metadata_key''', type: '''VARCHAR(255)''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instance_metadata_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-43''', author: '''olivier (generated)''') {
    createTable(tableName: '''grant_response_software_images''') {
      column(name: '''grant_response_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''resource_provider_id''', type: '''VARCHAR(255)''')
      column(name: '''vim_connection_id''', type: '''VARCHAR(255)''')
      column(name: '''vim_software_image_id''', type: '''VARCHAR(255)''')
      column(name: '''vnfd_software_image_id''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-44''', author: '''olivier (generated)''') {
    createTable(tableName: '''ns_vl_connectivity_type''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ns_vl_connectivity_type_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''flow_pattern''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-45''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_compute_aspect_delta''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_compute_aspect_delta_pkey''')
      }
      column(name: '''aspect_name''', type: '''VARCHAR(255)''')
      column(name: '''delta_name''', type: '''VARCHAR(255)''')
      column(name: '''level''', type: '''INTEGER''') {
        constraints(nullable: false)
      }
      column(name: '''max_scale_level''', type: '''INTEGER''') {
        constraints(nullable: false)
      }
      column(name: '''number_of_instances''', type: '''INTEGER''')
      column(name: '''vnf_compute_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-46''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_instantiated_compute_removed_storage_resource_ids''') {
      column(name: '''vnf_instantiated_compute_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''removed_storage_resource_ids''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-47''', author: '''olivier (generated)''') {
    createTable(tableName: '''scale_info''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''scale_info_pkey''')
      }
      column(name: '''aspect_id''', type: '''VARCHAR(255)''')
      column(name: '''scale_level''', type: '''INTEGER''')
      column(name: '''scale_status_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-48''', author: '''olivier (generated)''') {
    createTable(tableName: '''vim_connection_information_interface_info''') {
      column(name: '''vim_connection_information_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vim_connection_information_interface_info_pkey''')
      }
      column(name: '''interface_info''', type: '''VARCHAR(255)''')
      column(name: '''interface_info_key''', type: '''VARCHAR(255)''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vim_connection_information_interface_info_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-49''', author: '''olivier (generated)''') {
    createTable(tableName: '''grants_request_vim_connections''') {
      column(name: '''grants_request_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grants_request_vim_connections_pkey''')
      }
      column(name: '''vim_connections_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grants_request_vim_connections_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-50''', author: '''olivier (generated)''') {
    createTable(tableName: '''grant_response_temp_resources''') {
      column(name: '''grant_response_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grant_response_temp_resources_pkey''')
      }
      column(name: '''temp_resources_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grant_response_temp_resources_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-51''', author: '''olivier (generated)''') {
    createTable(tableName: '''nested_ns_instance_data''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''nested_ns_instance_data_pkey''')
      }
      column(name: '''nested_ns_instance_id''', type: '''VARCHAR(255)''')
      column(name: '''ns_profile_id''', type: '''VARCHAR(255)''')
      column(name: '''nested_ns_instance_data_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-52''', author: '''olivier (generated)''') {
    createTable(tableName: '''grants_request_add_resources''') {
      column(name: '''grants_request_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grants_request_add_resources_pkey''')
      }
      column(name: '''add_resources_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grants_request_add_resources_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-53''', author: '''olivier (generated)''') {
    createTable(tableName: '''instanciated_resource''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''instanciated_resource_pkey''')
      }
      column(name: '''end_time''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''start_time''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''status''', type: '''VARCHAR(255)''')
      column(name: '''vim_resource_id''', type: '''VARCHAR(255)''')
      column(name: '''vim_resource_type''', type: '''VARCHAR(255)''')
      column(name: '''instance_id''', type: '''UUID''')
      column(name: '''vim_connection_information_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-54''', author: '''olivier (generated)''') {
    createTable(tableName: '''ext_virtual_link_data_entity''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ext_virtual_link_data_entity_pkey''')
      }
      column(name: '''resource_id''', type: '''VARCHAR(255)''')
      column(name: '''resource_provider_id''', type: '''VARCHAR(255)''')
      column(name: '''vim_connection_id''', type: '''VARCHAR(255)''')
      column(name: '''vnf_instance_id''', type: '''UUID''')
      column(name: '''ext_virtual_link_info_id''', type: '''UUID''')
      column(name: '''ext_virtual_links_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-55''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_vl''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_vl_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''description''', type: '''VARCHAR(255)''')
      column(name: '''initial_br_leaf''', type: '''INTEGER''') {
        constraints(nullable: false)
      }
      column(name: '''initial_br_root''', type: '''INTEGER''') {
        constraints(nullable: false)
      }
      column(name: '''state''', type: '''VARCHAR(255)''')
      column(name: '''tosca_id''', type: '''VARCHAR(255)''')
      column(name: '''tosca_name''', type: '''VARCHAR(255)''')
      column(name: '''vl_profile_entity_id''', type: '''UUID''')
      column(name: '''vnf_vl_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-56''', author: '''olivier (generated)''') {
    createTable(tableName: '''ns_instantiated_pnf_cp_instance_id''') {
      column(name: '''ns_instantiated_pnf_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''cp_instance_id''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-57''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_instance_data''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instance_data_pkey''')
      }
      column(name: '''vnf_instance_id''', type: '''VARCHAR(255)''')
      column(name: '''vnf_profile_id''', type: '''VARCHAR(255)''')
      column(name: '''vnf_instance_data_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-58''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_compute''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_compute_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''boot_data''', type: '''VARCHAR(255)''')
      column(name: '''cpu_architecture''', type: '''VARCHAR(255)''')
      column(name: '''description''', type: '''VARCHAR(255)''')
      column(name: '''initial_number_of_instance''', type: '''INTEGER''')
      column(name: '''name''', type: '''VARCHAR(255)''')
      column(name: '''num_vcpu''', type: '''BIGINT''') {
        constraints(nullable: false)
      }
      column(name: '''state''', type: '''VARCHAR(255)''')
      column(name: '''tosca_id''', type: '''VARCHAR(255)''')
      column(name: '''tosca_name''', type: '''VARCHAR(255)''')
      column(name: '''max_number_of_instances''', type: '''INTEGER''') {
        constraints(nullable: false)
      }
      column(name: '''min_number_of_instances''', type: '''INTEGER''') {
        constraints(nullable: false)
      }
      column(name: '''virtual_memory_size''', type: '''BIGINT''') {
        constraints(nullable: false)
      }
      column(name: '''software_image_id''', type: '''UUID''')
      column(name: '''vnf_compute_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-59''', author: '''olivier (generated)''') {
    createTable(tableName: '''grants_request_additional_params''') {
      column(name: '''grants_request_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grants_request_additional_params_pkey''')
      }
      column(name: '''additional_params''', type: '''VARCHAR(255)''')
      column(name: '''additional_params_key''', type: '''VARCHAR(255)''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grants_request_additional_params_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-60''', author: '''olivier (generated)''') {
    createTable(tableName: '''ns_sap''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ns_sap_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''description''', type: '''VARCHAR(255)''')
      column(name: '''external_virtual_link''', type: '''VARCHAR(255)''')
      column(name: '''internal_virtual_link''', type: '''VARCHAR(255)''')
      column(name: '''role''', type: '''VARCHAR(255)''')
      column(name: '''state''', type: '''VARCHAR(255)''')
      column(name: '''tosca_id''', type: '''VARCHAR(255)''')
      column(name: '''tosca_name''', type: '''VARCHAR(255)''')
      column(name: '''trunk_mode''', type: '''BOOLEAN''') {
        constraints(nullable: false)
      }
      column(name: '''nsd_package_id''', type: '''UUID''')
      column(name: '''ns_saps_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-61''', author: '''olivier (generated)''') {
    createTable(tableName: '''virtual_nic_req''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''virtual_nic_req_pkey''')
      }
      column(name: '''description''', type: '''VARCHAR(255)''')
      column(name: '''name''', type: '''VARCHAR(255)''')
      column(name: '''support_mandatory''', type: '''BOOLEAN''') {
        constraints(nullable: false)
      }
      column(name: '''virtual_network_interface_requirements_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-62''', author: '''olivier (generated)''') {
    createTable(tableName: '''nsd_instance_nested_ns_instance''') {
      column(name: '''nsd_instance_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''nested_ns_instance_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
    }
  }

  changeSet(id: '''1591462712136-63''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_instance_monitoring_parameters''') {
      column(name: '''vnf_instance_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instance_monitoring_parameters_pkey''')
      }
      column(name: '''monitoring_parameters_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instance_monitoring_parameters_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-64''', author: '''olivier (generated)''') {
    createTable(tableName: '''ext_cp_info_cp_protocol_info''') {
      column(name: '''ext_cp_info_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ext_cp_info_cp_protocol_info_pkey''')
      }
      column(name: '''cp_protocol_info_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ext_cp_info_cp_protocol_info_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-65''', author: '''olivier (generated)''') {
    createTable(tableName: '''ns_vl_connectivity_type_layer_protocols''') {
      column(name: '''ns_vl_connectivity_type_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''layer_protocols''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-66''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_instance_extensions''') {
      column(name: '''vnf_instance_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instance_extensions_pkey''')
      }
      column(name: '''extensions''', type: '''VARCHAR(255)''')
      column(name: '''extensions_key''', type: '''VARCHAR(255)''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instance_extensions_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-67''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_ext_cp_configuration''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_ext_cp_configuration_pkey''')
      }
      column(name: '''cp_instance_id''', type: '''VARCHAR(255)''')
      column(name: '''link_port_id''', type: '''VARCHAR(255)''')
      column(name: '''vnf_ext_cp_data_entity_cpd_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-68''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_lcm_op_occs_monitoring_parameters''') {
      column(name: '''vnf_lcm_op_occs_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_lcm_op_occs_monitoring_parameters_pkey''')
      }
      column(name: '''monitoring_parameters_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_lcm_op_occs_monitoring_parameters_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-69''', author: '''olivier (generated)''') {
    createTable(tableName: '''grant_response''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grant_response_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''available''', type: '''BOOLEAN''')
      column(name: '''compute_reservation_id''', type: '''VARCHAR(255)''')
      column(name: '''flavour_id''', type: '''VARCHAR(255)''')
      column(name: '''instantiation_level_id''', type: '''VARCHAR(255)''')
      column(name: '''is_automatic_invocation''', type: '''BOOLEAN''') {
        constraints(nullable: false)
      }
      column(name: '''network_reservation_id''', type: '''VARCHAR(255)''')
      column(name: '''operation''', type: '''VARCHAR(255)''')
      column(name: '''storage_reservation_id''', type: '''VARCHAR(255)''')
      column(name: '''vnf_instance_id''', type: '''VARCHAR(255)''')
      column(name: '''vnf_lcm_op_occ_id''', type: '''VARCHAR(255)''')
      column(name: '''vnfd_id''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-70''', author: '''olivier (generated)''') {
    createTable(tableName: '''vl_profile_entity''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vl_profile_entity_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''link_bitrate_leaf''', type: '''INTEGER''')
      column(name: '''link_bitrate_root''', type: '''INTEGER''')
      column(name: '''max_bitrate_requirements_leaf''', type: '''INTEGER''')
      column(name: '''max_bitrate_requirements_root''', type: '''INTEGER''')
      column(name: '''latency''', type: '''BIGINT''') {
        constraints(nullable: false)
      }
      column(name: '''packet_delay_variation''', type: '''BIGINT''') {
        constraints(nullable: false)
      }
      column(name: '''packet_loss_ratio''', type: '''BIGINT''') {
        constraints(nullable: false)
      }
    }
  }

  changeSet(id: '''1591462712136-71''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_ext_cp_data_entity''') {
      column(name: '''cpd_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_ext_cp_data_entity_pkey''')
      }
      column(name: '''ext_virtual_link_data_entity_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-72''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_package''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_package_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''algorithm''', type: '''VARCHAR(255)''')
      column(name: '''hash''', type: '''VARCHAR(255)''')
      column(name: '''default_instantiation_level''', type: '''VARCHAR(255)''')
      column(name: '''descriptor_id''', type: '''VARCHAR(255)''')
      column(name: '''flavor_id''', type: '''VARCHAR(255)''')
      column(name: '''onboarding_state''', type: '''VARCHAR(255)''')
      column(name: '''operational_state''', type: '''VARCHAR(255)''')
      column(name: '''usage_state''', type: '''VARCHAR(255)''')
      column(name: '''vnf_product_name''', type: '''VARCHAR(255)''')
      column(name: '''vnf_provider''', type: '''VARCHAR(255)''')
      column(name: '''vnf_software_version''', type: '''VARCHAR(255)''')
      column(name: '''vnfd_id''', type: '''VARCHAR(255)''')
      column(name: '''vnfd_version''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-73''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_instance_vnfc_resource_info''') {
      column(name: '''vnf_instance_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instance_vnfc_resource_info_pkey''')
      }
      column(name: '''vnfc_resource_info_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instance_vnfc_resource_info_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-74''', author: '''olivier (generated)''') {
    createTable(tableName: '''vl_protocol_data''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vl_protocol_data_pkey''')
      }
      column(name: '''associated_layer_protocol''', type: '''VARCHAR(255)''')
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''mtu''', type: '''INTEGER''')
      column(name: '''name''', type: '''VARCHAR(255)''')
      column(name: '''network_type''', type: '''VARCHAR(255)''')
      column(name: '''vlan_transparent''', type: '''BOOLEAN''')
      column(name: '''cidr''', type: '''VARCHAR(255)''')
      column(name: '''dhcp_enabled''', type: '''BOOLEAN''') {
        constraints(nullable: false)
      }
      column(name: '''gateway_ip''', type: '''VARCHAR(255)''')
      column(name: '''ip_version''', type: '''VARCHAR(255)''')
      column(name: '''ipv6address_mode''', type: '''VARCHAR(255)''')
      column(name: '''l3name''', type: '''VARCHAR(255)''')
      column(name: '''virtual_link_protocol_data_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-75''', author: '''olivier (generated)''') {
    createTable(tableName: '''ns_sap_layer_protocols''') {
      column(name: '''ns_sap_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''layer_protocols''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-76''', author: '''olivier (generated)''') {
    createTable(tableName: '''ext_cp_info_metadata''') {
      column(name: '''ext_cp_info_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ext_cp_info_metadata_pkey''')
      }
      column(name: '''metadata''', type: '''VARCHAR(255)''')
      column(name: '''metadata_key''', type: '''VARCHAR(255)''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ext_cp_info_metadata_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-77''', author: '''olivier (generated)''') {
    createTable(tableName: '''filter_attributes''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''filter_attributes_pkey''')
      }
      column(name: '''attribute''', type: '''VARCHAR(255)''')
      column(name: '''value''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-78''', author: '''olivier (generated)''') {
    createTable(tableName: '''ip_pool''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ip_pool_pkey''')
      }
      column(name: '''end_ip_address''', type: '''VARCHAR(255)''')
      column(name: '''start_ip_address''', type: '''VARCHAR(255)''')
      column(name: '''ip_allocation_pools_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-79''', author: '''olivier (generated)''') {
    createTable(tableName: '''vim_connection_information''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vim_connection_information_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''vim_id''', type: '''VARCHAR(255)''')
      column(name: '''vim_type''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-80''', author: '''olivier (generated)''') {
    createTable(tableName: '''edge''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''edge_pkey''')
      }
      column(name: '''source''', type: '''VARCHAR(255)''')
      column(name: '''target''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-81''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_lcm_op_occs_vnfc_resource_info''') {
      column(name: '''vnf_lcm_op_occs_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_lcm_op_occs_vnfc_resource_info_pkey''')
      }
      column(name: '''vnfc_resource_info_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_lcm_op_occs_vnfc_resource_info_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-82''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_compute_networks''') {
      column(name: '''vnf_compute_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''networks''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-83''', author: '''olivier (generated)''') {
    createTable(tableName: '''grant_response_compute_resource_flavours''') {
      column(name: '''grant_response_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''resource_provider_id''', type: '''VARCHAR(255)''')
      column(name: '''vim_connection_id''', type: '''VARCHAR(255)''')
      column(name: '''vim_flavour_id''', type: '''VARCHAR(255)''')
      column(name: '''vnfd_virtual_compute_desc_id''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-84''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_instantiated_compute_added_storage_resource_ids''') {
      column(name: '''vnf_instantiated_compute_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''added_storage_resource_ids''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-85''', author: '''olivier (generated)''') {
    createTable(tableName: '''grant_response_add_resources''') {
      column(name: '''grant_response_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grant_response_add_resources_pkey''')
      }
      column(name: '''add_resources_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grant_response_add_resources_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-86''', author: '''olivier (generated)''') {
    createTable(tableName: '''ns_cp_protocol_data_address_data''') {
      column(name: '''ns_cp_protocol_data_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''address_data''', type: '''BYTEA''')
    }
  }

  changeSet(id: '''1591462712136-87''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_package_user_defined_data''') {
      column(name: '''vnf_package_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_package_user_defined_data_pkey''')
      }
      column(name: '''user_defined_data''', type: '''VARCHAR(255)''')
      column(name: '''user_defined_data_key''', type: '''VARCHAR(255)''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_package_user_defined_data_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-88''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_ext_cp''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_ext_cp_pkey''')
      }
      column(name: '''external_virtual_link''', type: '''VARCHAR(255)''')
      column(name: '''internal_virtual_link''', type: '''VARCHAR(255)''')
      column(name: '''state''', type: '''VARCHAR(255)''')
      column(name: '''tosca_id''', type: '''VARCHAR(255)''')
      column(name: '''tosca_name''', type: '''VARCHAR(255)''')
      column(name: '''vnf_ext_cp_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-89''', author: '''olivier (generated)''') {
    createTable(tableName: '''vim_connection_information_access_info''') {
      column(name: '''vim_connection_information_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vim_connection_information_access_info_pkey''')
      }
      column(name: '''access_info''', type: '''VARCHAR(255)''')
      column(name: '''access_info_key''', type: '''VARCHAR(255)''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vim_connection_information_access_info_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-90''', author: '''olivier (generated)''') {
    createTable(tableName: '''scaling_aspect_step_deltas''') {
      column(name: '''scaling_aspect_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''step_deltas''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-91''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_live_instance''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_live_instance_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''instantiation_level''', type: '''VARCHAR(255)''')
      column(name: '''resource_id''', type: '''UUID''')
      column(name: '''vnf_instance_id''', type: '''UUID''')
      column(name: '''vnf_instantiated_base_id''', type: '''UUID''')
      column(name: '''vnf_lcm_op_occs_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-92''', author: '''olivier (generated)''') {
    createTable(tableName: '''grants_request_update_resources''') {
      column(name: '''grants_request_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grants_request_update_resources_pkey''')
      }
      column(name: '''update_resources_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grants_request_update_resources_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-93''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_instance_vim_connection_info''') {
      column(name: '''vnf_instance_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instance_vim_connection_info_pkey''')
      }
      column(name: '''vim_connection_info_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instance_vim_connection_info_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-94''', author: '''olivier (generated)''') {
    createTable(tableName: '''cp_protocol_data_entity''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''cp_protocol_data_entity_pkey''')
      }
      column(name: '''layer_protocol''', type: '''VARCHAR(255)''')
      column(name: '''ip_over_ethernet_id''', type: '''UUID''')
      column(name: '''vnf_ext_cp_configuration_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-95''', author: '''olivier (generated)''') {
    createTable(tableName: '''grants_request_software_images''') {
      column(name: '''grants_request_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''resource_provider_id''', type: '''VARCHAR(255)''')
      column(name: '''vim_connection_id''', type: '''VARCHAR(255)''')
      column(name: '''vim_software_image_id''', type: '''VARCHAR(255)''')
      column(name: '''vnfd_software_image_id''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-96''', author: '''olivier (generated)''') {
    createTable(tableName: '''grants_request_zone_groups''') {
      column(name: '''grants_request_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''zone_groups_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
    }
  }

  changeSet(id: '''1591462712136-97''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_lcm_op_occs''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_lcm_op_occs_pkey''')
      }
      column(name: '''number_of_steps''', type: '''INTEGER''')
      column(name: '''scale_type''', type: '''INTEGER''')
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''cancel_mode''', type: '''VARCHAR(255)''')
      column(name: '''detail''', type: '''VARCHAR(500)''')
      column(name: '''instance''', type: '''VARCHAR(255)''')
      column(name: '''status''', type: '''BIGINT''')
      column(name: '''title''', type: '''VARCHAR(255)''')
      column(name: '''type''', type: '''VARCHAR(255)''')
      column(name: '''external_process_id''', type: '''VARCHAR(255)''')
      column(name: '''grant_id''', type: '''VARCHAR(255)''')
      column(name: '''is_automatic_invocation''', type: '''BOOLEAN''')
      column(name: '''is_cancel_pending''', type: '''BOOLEAN''')
      column(name: '''operation''', type: '''VARCHAR(255)''')
      column(name: '''operation_state''', type: '''VARCHAR(255)''')
      column(name: '''start_time''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''state_entered_time''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''flavour_id''', type: '''VARCHAR(255)''')
      column(name: '''instantiation_level_id''', type: '''VARCHAR(255)''')
      column(name: '''localization_language''', type: '''VARCHAR(255)''')
      column(name: '''vnf_state''', type: '''VARCHAR(255)''')
      column(name: '''vnf_instance_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-98''', author: '''olivier (generated)''') {
    createTable(tableName: '''zone_group_information''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''zone_group_information_pkey''')
      }
      column(name: '''zone_groups_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-99''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_link_port''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_link_port_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''state''', type: '''VARCHAR(255)''')
      column(name: '''tosca_id''', type: '''VARCHAR(255)''')
      column(name: '''tosca_name''', type: '''VARCHAR(255)''')
      column(name: '''virtual_binding''', type: '''VARCHAR(255)''')
      column(name: '''virtual_link''', type: '''VARCHAR(255)''')
      column(name: '''vnf_link_port_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-100''', author: '''olivier (generated)''') {
    createTable(tableName: '''ns_live_instance''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ns_live_instance_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''instantiation_level''', type: '''VARCHAR(255)''')
      column(name: '''resource_id''', type: '''VARCHAR(255)''')
      column(name: '''ns_instance_id''', type: '''UUID''')
      column(name: '''ns_instantiated_base_id''', type: '''UUID''')
      column(name: '''ns_lcm_op_occs_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-101''', author: '''olivier (generated)''') {
    createTable(tableName: '''grants_request''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grants_request_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''available''', type: '''BOOLEAN''')
      column(name: '''compute_reservation_id''', type: '''VARCHAR(255)''')
      column(name: '''flavour_id''', type: '''VARCHAR(255)''')
      column(name: '''instantiation_level_id''', type: '''VARCHAR(255)''')
      column(name: '''is_automatic_invocation''', type: '''BOOLEAN''') {
        constraints(nullable: false)
      }
      column(name: '''network_reservation_id''', type: '''VARCHAR(255)''')
      column(name: '''operation''', type: '''VARCHAR(255)''')
      column(name: '''storage_reservation_id''', type: '''VARCHAR(255)''')
      column(name: '''vnfd_id''', type: '''VARCHAR(255)''')
      column(name: '''vnf_lcm_op_occs_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-102''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_package_vnf_instantiation_levels''') {
      column(name: '''vnf_package_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_package_vnf_instantiation_levels_pkey''')
      }
      column(name: '''vnf_instantiation_levels_id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_package_vnf_instantiation_levels_pkey''')
      }
    }
  }

  changeSet(id: '''1591462712136-103''', author: '''olivier (generated)''') {
    createTable(tableName: '''zone_group_information_zone_id''') {
      column(name: '''zone_group_information_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''zone_id''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-104''', author: '''olivier (generated)''') {
    createTable(tableName: '''ns_vl_profile''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''ns_vl_profile_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''link_bitrate_leaf''', type: '''INTEGER''')
      column(name: '''link_bitrate_root''', type: '''INTEGER''')
      column(name: '''max_bitrate_requirements_leaf''', type: '''INTEGER''')
      column(name: '''max_bitrate_requirements_root''', type: '''INTEGER''')
      column(name: '''latency''', type: '''BIGINT''') {
        constraints(nullable: false)
      }
      column(name: '''packet_delay_variation''', type: '''BIGINT''') {
        constraints(nullable: false)
      }
      column(name: '''packet_loss_ratio''', type: '''BIGINT''') {
        constraints(nullable: false)
      }
      column(name: '''service_availability''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-105''', author: '''olivier (generated)''') {
    createTable(tableName: '''nsd_package''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''nsd_package_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''flavor_id''', type: '''VARCHAR(255)''')
      column(name: '''instantiation_level''', type: '''VARCHAR(255)''')
      column(name: '''max_number_of_instance''', type: '''INTEGER''') {
        constraints(nullable: false)
      }
      column(name: '''min_number_of_instance''', type: '''INTEGER''') {
        constraints(nullable: false)
      }
      column(name: '''nsd_designer''', type: '''VARCHAR(255)''')
      column(name: '''nsd_id''', type: '''VARCHAR(255)''')
      column(name: '''nsd_invariant_id''', type: '''VARCHAR(255)''')
      column(name: '''nsd_name''', type: '''VARCHAR(255)''')
      column(name: '''nsd_onboarding_state''', type: '''VARCHAR(255)''')
      column(name: '''nsd_operational_state''', type: '''VARCHAR(255)''')
      column(name: '''nsd_usage_state''', type: '''VARCHAR(255)''')
      column(name: '''nsd_version''', type: '''VARCHAR(255)''')
      column(name: '''detail''', type: '''VARCHAR(500)''')
      column(name: '''instance''', type: '''VARCHAR(255)''')
      column(name: '''status''', type: '''BIGINT''')
      column(name: '''title''', type: '''VARCHAR(255)''')
      column(name: '''type''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-106''', author: '''olivier (generated)''') {
    createTable(tableName: '''grant_information''') {
      column(name: '''dtype''', type: '''VARCHAR(31)''') {
        constraints(nullable: false)
      }
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''grant_information_pkey''')
      }
      column(name: '''reservation_id''', type: '''VARCHAR(255)''')
      column(name: '''resource_definition_id''', type: '''VARCHAR(255)''')
      column(name: '''resource_group_id''', type: '''VARCHAR(255)''')
      column(name: '''resource_provider_id''', type: '''VARCHAR(255)''')
      column(name: '''type''', type: '''VARCHAR(255)''')
      column(name: '''vdu_id''', type: '''UUID''')
      column(name: '''vim_connection_id''', type: '''VARCHAR(255)''')
      column(name: '''zone_id''', type: '''VARCHAR(255)''')
      column(name: '''external_id''', type: '''VARCHAR(255)''')
      column(name: '''instantiation_level_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-107''', author: '''olivier (generated)''') {
    createTable(tableName: '''nsd_instance''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''nsd_instance_pkey''')
      }
      column(name: '''flavour_id''', type: '''VARCHAR(255)''')
      column(name: '''ns_instance_description''', type: '''VARCHAR(255)''')
      column(name: '''ns_instance_name''', type: '''VARCHAR(255)''')
      column(name: '''ns_instantiation_level_id''', type: '''VARCHAR(255)''')
      column(name: '''ns_state''', type: '''VARCHAR(255)''')
      column(name: '''nsd_info_id''', type: '''UUID''')
      column(name: '''ns_instance_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-108''', author: '''olivier (generated)''') {
    createTable(tableName: '''security_group''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''security_group_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''description''', type: '''VARCHAR(255)''')
      column(name: '''direction''', type: '''VARCHAR(255)''')
      column(name: '''ether_type''', type: '''VARCHAR(255)''')
      column(name: '''port_range_max''', type: '''INTEGER''') {
        constraints(nullable: false)
      }
      column(name: '''port_range_min''', type: '''INTEGER''') {
        constraints(nullable: false)
      }
      column(name: '''protocol''', type: '''VARCHAR(255)''')
      column(name: '''state''', type: '''VARCHAR(255)''')
      column(name: '''tosca_id''', type: '''VARCHAR(255)''')
      column(name: '''tosca_name''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-109''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_instantiated_compute_storage_resource_ids''') {
      column(name: '''vnf_instantiated_compute_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''storage_resource_ids''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-110''', author: '''olivier (generated)''') {
    createTable(tableName: '''scaling_aspect''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''scaling_aspect_pkey''')
      }
      column(name: '''description''', type: '''VARCHAR(255)''')
      column(name: '''max_scale_level''', type: '''INTEGER''') {
        constraints(nullable: false)
      }
      column(name: '''name''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-111''', author: '''olivier (generated)''') {
    createTable(tableName: '''vnf_instantiated_base''') {
      column(name: '''dtype''', type: '''VARCHAR(31)''') {
        constraints(nullable: false)
      }
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''vnf_instantiated_base_pkey''')
      }
      column(name: '''created_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''updated_on''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''change_type''', type: '''VARCHAR(255)''')
      column(name: '''end_time''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''removed_instantiated''', type: '''UUID''')
      column(name: '''reservation_id''', type: '''VARCHAR(255)''')
      column(name: '''resource_group_id''', type: '''VARCHAR(255)''')
      column(name: '''resource_id''', type: '''VARCHAR(255)''')
      column(name: '''resource_provider_id''', type: '''VARCHAR(255)''')
      column(name: '''start_time''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''status''', type: '''VARCHAR(255)''')
      column(name: '''vdu_id''', type: '''UUID''')
      column(name: '''vim_level_resource_type''', type: '''VARCHAR(255)''')
      column(name: '''zone_id''', type: '''VARCHAR(255)''')
      column(name: '''collection_period''', type: '''BIGINT''')
      column(name: '''name''', type: '''VARCHAR(255)''')
      column(name: '''performance_metric''', type: '''VARCHAR(255)''')
      column(name: '''timestamp''', type: '''TIMESTAMP WITHOUT TIME ZONE''')
      column(name: '''value''', type: '''VARCHAR(255)''')
      column(name: '''vnf_virtual_link_desc_id''', type: '''UUID''')
      column(name: '''alias_name''', type: '''VARCHAR(255)''')
      column(name: '''flavor_id''', type: '''VARCHAR(255)''')
      column(name: '''image_id''', type: '''VARCHAR(255)''')
      column(name: '''instantiation_level_id''', type: '''UUID''')
      column(name: '''vim_connection_information_id''', type: '''UUID''')
      column(name: '''vnf_compute_id''', type: '''UUID''')
      column(name: '''vnf_lcm_op_occs_id''', type: '''UUID''')
      column(name: '''ext_virtual_link_data_entity_id''', type: '''UUID''')
      column(name: '''vnf_ext_cp_id''', type: '''UUID''')
      column(name: '''vnf_instantiated_compute_id''', type: '''UUID''')
      column(name: '''vnf_virtual_storage_id''', type: '''UUID''')
      column(name: '''vnf_virtual_link_id''', type: '''UUID''')
      column(name: '''virtual_storage_resource_info_id''', type: '''UUID''')
      column(name: '''virtual_link_resource_info_id''', type: '''UUID''')
      column(name: '''ext_managed_virtual_link_info_id''', type: '''UUID''')
      column(name: '''instance_monitors_id''', type: '''UUID''')
      column(name: '''affected_vnfcs_id''', type: '''UUID''')
      column(name: '''affected_virtual_storages_id''', type: '''UUID''')
      column(name: '''affected_virtual_links_id''', type: '''UUID''')
      column(name: '''affected_ext_cp_id''', type: '''UUID''')
      column(name: '''ext_managed_virtual_links_id''', type: '''UUID''')
    }
  }

  changeSet(id: '''1591462712136-112''', author: '''olivier (generated)''') {
    createTable(tableName: '''subscription''') {
      column(name: '''id''', type: '''UUID''') {
        constraints(nullable: false, primaryKey: true, primaryKeyName: '''subscription_pkey''')
      }
      column(name: '''api''', type: '''VARCHAR(255)''')
      column(name: '''password''', type: '''VARCHAR(255)''')
      column(name: '''user_name''', type: '''VARCHAR(255)''')
      column(name: '''client_id''', type: '''VARCHAR(255)''')
      column(name: '''client_password''', type: '''VARCHAR(255)''')
      column(name: '''token_endpoint''', type: '''VARCHAR(255)''')
      column(name: '''auth_type''', type: '''VARCHAR(255)''')
      column(name: '''callback_uri''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591462712136-113''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''subscription_filter_id''', constraintName: '''uk_e3x2aivplsxo25yi7ww471nai''', tableName: '''subscription_subscription_filter''')
  }

  changeSet(id: '''1591462712136-114''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''security_groups_id''', constraintName: '''uk_l85pn4c30831pntghodofpgao''', tableName: '''ns_sap_security_groups''')
  }

  changeSet(id: '''1591462712136-115''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''temp_resources_id''', constraintName: '''uk_1d2sbrcgactm1imcdfufwhoqo''', tableName: '''grants_request_temp_resources''')
  }

  changeSet(id: '''1591462712136-116''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''remove_resources_id''', constraintName: '''uk_api5iibs19frdmco6l175fgy5''', tableName: '''grants_request_remove_resources''')
  }

  changeSet(id: '''1591462712136-117''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''vnfc_cp_info_id''', constraintName: '''uk_o96jnad7q9e5rrgegopyb8hq0''', tableName: '''vnf_instantiated_base_vnfc_cp_info''')
  }

  changeSet(id: '''1591462712136-118''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''update_resources_id''', constraintName: '''uk_8p552uohcc8bgjolrq1hpvcqk''', tableName: '''grant_response_update_resources''')
  }

  changeSet(id: '''1591462712136-119''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''remove_resources_id''', constraintName: '''uk_hgny480h9nhxf7x9tw7pmwba5''', tableName: '''grant_response_remove_resources''')
  }

  changeSet(id: '''1591462712136-120''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''software_image_id''', baseTableName: '''vnf_storage''', constraintName: '''fk2e2rg9bw18u6yl3cqe6p1h8aa''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''software_image''', validate: true)
  }

  changeSet(id: '''1591462712136-121''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_virtual_link_desc_id''', baseTableName: '''ns_instantiated_base''', constraintName: '''fkauls1fep25tlkdo0jdlptg5wu''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_virtual_link''', validate: true)
  }

  changeSet(id: '''1591462712136-122''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''nsd_package_vnf_package_id''', baseTableName: '''ns_instantiated_base''', constraintName: '''fkj6rv2e4k8b9rm34a709sgg2g''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_package_vnf_package''', validate: true)
  }

  changeSet(id: '''1591462712136-123''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instance_id''', baseTableName: '''ns_instantiated_base''', constraintName: '''fkkdplonh4w8kumorypw37iu0t6''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-124''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ext_cp_info_id''', baseTableName: '''ext_cp_info_metadata''', constraintName: '''fk5jedwancs8w08yayejspo43o7''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ext_cp_info''', validate: true)
  }

  changeSet(id: '''1591462712136-125''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ext_cp_info_id''', baseTableName: '''ext_cp_info_cp_protocol_info''', constraintName: '''fkpsabw0nw00vg96d2hu5b32fjh''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ext_cp_info''', validate: true)
  }

  changeSet(id: '''1591462712136-126''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''monitoring_parameters_id''', constraintName: '''uk_h3fah7n7lkus2o4ij4ai2fupg''', tableName: '''vnf_compute_monitoring_parameters''')
  }

  changeSet(id: '''1591462712136-127''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instance_id''', baseTableName: '''vnf_instance_metadata''', constraintName: '''fktex7k5v9rjj350i2n9qeusrd1''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-128''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''temp_resources_id''', constraintName: '''uk_org9vnupbn0rarvsq46budq73''', tableName: '''grant_response_temp_resources''')
  }

  changeSet(id: '''1591462712136-129''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''add_resources_id''', constraintName: '''uk_hiyp08fkefh4chh410ckj5p8x''', tableName: '''grants_request_add_resources''')
  }

  changeSet(id: '''1591462712136-130''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''instance_id''', baseTableName: '''instanciated_resource''', constraintName: '''fk6dqcw21luttuxghvqmekt6utd''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-131''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ext_virtual_link_data_entity_id''', baseTableName: '''vnf_ext_cp_data_entity''', constraintName: '''fk9peyk1psglld8fh0crb5xevch''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ext_virtual_link_data_entity''', validate: true)
  }

  changeSet(id: '''1591462712136-132''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ext_virtual_link_data_entity_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fkb76ddscrnaj6tcx78gdupkqyf''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ext_virtual_link_data_entity''', validate: true)
  }

  changeSet(id: '''1591462712136-133''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instance_id''', baseTableName: '''ext_virtual_link_data_entity''', constraintName: '''fkfj69lt0ga8l98b5nsf7w2r50p''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-134''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_instantiated_pnf_id''', baseTableName: '''ns_instantiated_pnf_cp_instance_id''', constraintName: '''fkdke082l6vmr5y45o780forg3e''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_instantiated_base''', validate: true)
  }

  changeSet(id: '''1591462712136-135''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''software_image_id''', baseTableName: '''vnf_compute''', constraintName: '''fkc1nst3v662xhtt1xtg8vkntb1''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''software_image''', validate: true)
  }

  changeSet(id: '''1591462712136-136''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''nested_ns_instance_id''', constraintName: '''uk_6kwgtaw2yntj4p7es949vskqj''', tableName: '''nsd_instance_nested_ns_instance''')
  }

  changeSet(id: '''1591462712136-137''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instance_id''', baseTableName: '''vnf_instance_monitoring_parameters''', constraintName: '''fkqxqnxbkjsv7uspaao92vbl7qc''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-138''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''monitoring_parameters_id''', constraintName: '''uk_etwn7srj97o8omjm8o4dnmkp7''', tableName: '''vnf_instance_monitoring_parameters''')
  }

  changeSet(id: '''1591462712136-139''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''cp_protocol_info_id''', constraintName: '''uk_g5u7l047rm1lshq2i4u3cys22''', tableName: '''ext_cp_info_cp_protocol_info''')
  }

  changeSet(id: '''1591462712136-140''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_vl_connectivity_type_id''', baseTableName: '''ns_vl_connectivity_type_layer_protocols''', constraintName: '''fkckva3xhr7un89fivhwb8redek''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_vl_connectivity_type''', validate: true)
  }

  changeSet(id: '''1591462712136-141''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instance_id''', baseTableName: '''vnf_instance_extensions''', constraintName: '''fk3afk7vxtl0skgc090c3oa9tjq''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-142''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''monitoring_parameters_id''', constraintName: '''uk_4dat69wjgslqcl1q7n8qm3qs9''', tableName: '''vnf_lcm_op_occs_monitoring_parameters''')
  }

  changeSet(id: '''1591462712136-143''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instance_id''', baseTableName: '''vnf_instance_vnfc_resource_info''', constraintName: '''fkhljc7l4h7affhdpjgneq34lcu''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-144''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''vnfc_resource_info_id''', constraintName: '''uk_axglfg6015hjeqmdsh2jomm1s''', tableName: '''vnf_instance_vnfc_resource_info''')
  }

  changeSet(id: '''1591462712136-145''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''virtual_link_protocol_data_id''', baseTableName: '''vl_protocol_data''', constraintName: '''fk2nvqna8iy14mhrtueyll77a52''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vl_profile_entity''', validate: true)
  }

  changeSet(id: '''1591462712136-146''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_sap_id''', baseTableName: '''ns_sap_layer_protocols''', constraintName: '''fkdsi0a27vhn4n3pm12r4vgux36''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_sap''', validate: true)
  }

  changeSet(id: '''1591462712136-147''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''subscription_filter_id''', baseTableName: '''subscription_subscription_filter''', constraintName: '''fk4b3hb5b26hjinlpp1ar49o9rp''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''filter_attributes''', validate: true)
  }

  changeSet(id: '''1591462712136-148''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ip_allocation_pools_id''', baseTableName: '''ip_pool''', constraintName: '''fk93y8rfjrsxytu8l8ecunlc3hy''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vl_protocol_data''', validate: true)
  }

  changeSet(id: '''1591462712136-149''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''vnfc_resource_info_id''', constraintName: '''uk_tb9y1hn7tabvoy0xoccomjoe0''', tableName: '''vnf_lcm_op_occs_vnfc_resource_info''')
  }

  changeSet(id: '''1591462712136-150''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_compute_id''', baseTableName: '''vnf_compute_networks''', constraintName: '''fk2sum8cy2g47xa8y8igou6wqud''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_compute''', validate: true)
  }

  changeSet(id: '''1591462712136-151''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grant_response_id''', baseTableName: '''grant_response_compute_resource_flavours''', constraintName: '''fkabodk9p73m9bxxm614r9p0jjl''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_response''', validate: true)
  }

  changeSet(id: '''1591462712136-152''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grant_response_id''', baseTableName: '''grant_response_add_resources''', constraintName: '''fk7b0nfhapx985a8kim9crtoa''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_response''', validate: true)
  }

  changeSet(id: '''1591462712136-153''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''add_resources_id''', constraintName: '''uk_q2boykx7cldfv5hka0vijy74n''', tableName: '''grant_response_add_resources''')
  }

  changeSet(id: '''1591462712136-154''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_cp_protocol_data_id''', baseTableName: '''ns_cp_protocol_data_address_data''', constraintName: '''fk4kfuyce1wow6cxbt1qqelp90r''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_cp_protocol_data''', validate: true)
  }

  changeSet(id: '''1591462712136-155''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_package_id''', baseTableName: '''vnf_package_user_defined_data''', constraintName: '''fk30cb41pkavpt0fc5pamh1ah0g''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_package''', validate: true)
  }

  changeSet(id: '''1591462712136-156''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_ext_cp_id''', baseTableName: '''vnf_ext_cp''', constraintName: '''fk2thl4ki46e2st56u1frcn5eq4''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_package''', validate: true)
  }

  changeSet(id: '''1591462712136-157''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vim_connection_information_id''', baseTableName: '''vim_connection_information_access_info''', constraintName: '''fk22en7hrpd97dwqxolivksjpgh''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vim_connection_information''', validate: true)
  }

  changeSet(id: '''1591462712136-158''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instance_id''', baseTableName: '''vnf_live_instance''', constraintName: '''fkmo0wmqamx8v3o4trdcihr9n6h''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-159''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''update_resources_id''', constraintName: '''uk_g9kugfiojgjwxgyuwg2x1lpm5''', tableName: '''grants_request_update_resources''')
  }

  changeSet(id: '''1591462712136-160''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instance_id''', baseTableName: '''vnf_instance_vim_connection_info''', constraintName: '''fk9ohco6pfu4cp3kmp0aqqkdh1v''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-161''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vim_connection_info_id''', baseTableName: '''vnf_instance_vim_connection_info''', constraintName: '''fkeeao4qjtvpyl9h9dygeqqopfa''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vim_connection_information''', validate: true)
  }

  changeSet(id: '''1591462712136-162''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''vim_connection_info_id''', constraintName: '''uk_5imcneqbh1ho7nfqg6m9yoefa''', tableName: '''vnf_instance_vim_connection_info''')
  }

  changeSet(id: '''1591462712136-163''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ip_over_ethernet_id''', baseTableName: '''cp_protocol_data_entity''', constraintName: '''fk6lvl3d1x8so0f4464okkfxmt9''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ip_over_ethernet_address_data_entity''', validate: true)
  }

  changeSet(id: '''1591462712136-164''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnfc_cp_info_id''', baseTableName: '''vnf_instantiated_base_vnfc_cp_info''', constraintName: '''fkef8gaphea1i3xifi2y5n3stah''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''cp_protocol_data_entity''', validate: true)
  }

  changeSet(id: '''1591462712136-165''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ext_link_port_id_id''', baseTableName: '''ext_cp_info''', constraintName: '''fkpx5sxgmxiv5damv3d6c27w83j''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''cp_protocol_data_entity''', validate: true)
  }

  changeSet(id: '''1591462712136-166''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''cp_protocol_info_id''', baseTableName: '''ext_cp_info_cp_protocol_info''', constraintName: '''fkrvu22j5uxgnvle07mux0ga6gv''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''cp_protocol_data_entity''', validate: true)
  }

  changeSet(id: '''1591462712136-167''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_ext_cp_configuration_id''', baseTableName: '''cp_protocol_data_entity''', constraintName: '''fkspemnoi5mlr54wttydr2tek7b''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_ext_cp_configuration''', validate: true)
  }

  changeSet(id: '''1591462712136-168''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''zone_groups_id''', constraintName: '''uk_a7j4gn5khor06lxgvbmpa2o47''', tableName: '''grants_request_zone_groups''')
  }

  changeSet(id: '''1591462712136-169''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instance_id''', baseTableName: '''vnf_lcm_op_occs''', constraintName: '''fkd89xdqokr5hmdbtwltfhvheao''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-170''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''zone_groups_id''', baseTableName: '''zone_group_information''', constraintName: '''fklrn2nqjky1gk8anu219qx5wde''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_response''', validate: true)
  }

  changeSet(id: '''1591462712136-171''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_link_port_id''', baseTableName: '''vnf_link_port''', constraintName: '''fkdfq8bpht76h6skale88v4p4hn''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_package''', validate: true)
  }

  changeSet(id: '''1591462712136-172''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_instantiated_base_id''', baseTableName: '''ns_live_instance''', constraintName: '''fkbvxgsafqbf011fhgti2venr6m''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_instantiated_base''', validate: true)
  }

  changeSet(id: '''1591462712136-173''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_lcm_op_occs_id''', baseTableName: '''ns_live_instance''', constraintName: '''fkfjavi49sy6lrbvgq4m9u7jmx6''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-174''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_lcm_op_occs_id''', baseTableName: '''grants_request''', constraintName: '''fkawe2t33y29i84eywb9tyifyro''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-175''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instantiation_levels_id''', baseTableName: '''vnf_package_vnf_instantiation_levels''', constraintName: '''fkehnick441oj126sn0yytawtg6''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instantiation_levels''', validate: true)
  }

  changeSet(id: '''1591462712136-176''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_package_id''', baseTableName: '''vnf_package_vnf_instantiation_levels''', constraintName: '''fkfw67q1gli6lyfraaeb0gnkb87''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_package''', validate: true)
  }

  changeSet(id: '''1591462712136-177''', author: '''olivier (generated)''') {
    addUniqueConstraint(columnNames: '''vnf_instantiation_levels_id''', constraintName: '''uk_tp3dbd90j4d7b8g54vrbjwuot''', tableName: '''vnf_package_vnf_instantiation_levels''')
  }

  changeSet(id: '''1591462712136-178''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''zone_group_information_id''', baseTableName: '''zone_group_information_zone_id''', constraintName: '''fkeho5d9j3m9y4vulliw8bmb1nm''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''zone_group_information''', validate: true)
  }

  changeSet(id: '''1591462712136-179''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''instantiation_level_id''', baseTableName: '''grant_information''', constraintName: '''fk3dvfkufc0wx78xb6r0hk907cu''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vdu_instantiation_level''', validate: true)
  }

  changeSet(id: '''1591462712136-180''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_instance_id''', baseTableName: '''nsd_instance''', constraintName: '''fk33sw08mc9rn2x1i5hnqfqk1gf''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_package''', validate: true)
  }

  changeSet(id: '''1591462712136-181''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''nsd_info_id''', baseTableName: '''nsd_instance''', constraintName: '''fkpnjtvj2j41jk64h59axkecklg''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_package''', validate: true)
  }

  changeSet(id: '''1591462712136-182''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ext_managed_virtual_link_info_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fk77ah4eaj1j2rb51kk8hjg0abo''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-183''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_compute_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fk8gjnv6wu40c7yvnetjoiwygk1''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_compute''', validate: true)
  }

  changeSet(id: '''1591462712136-184''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''virtual_link_resource_info_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fk8xmu28mm0wpat70kuq0qivyd3''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-185''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_virtual_link_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fkbextdb7hp6kraqrpppniqrw98''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_vl''', validate: true)
  }

  changeSet(id: '''1591462712136-186''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''instance_monitors_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fkfnfb70pnipmhed579p25fmxvq''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-187''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vim_connection_information_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fkgjbvbte6ydgwthtlhhwy9mcw4''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vim_connection_information''', validate: true)
  }

  changeSet(id: '''1591462712136-188''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''affected_virtual_links_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fkimh41ci8nd0shc8tbsi3a8vnn''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-189''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_virtual_storage_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fklv6ddc5mg0x0meumqq2d9aer6''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_storage''', validate: true)
  }

  changeSet(id: '''1591462712136-190''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''instantiation_level_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fkntle4ilo3tbtkmfnsn9fyt2p''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vdu_instantiation_level''', validate: true)
  }

  changeSet(id: '''1591462712136-191''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_lcm_op_occs_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fkps8n96gjql2526wl2x8k8y38a''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-192''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''affected_vnfcs_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fkpunlv6orclai4kf85bi9bifht''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-193''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''affected_ext_cp_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fkrrear5tc5xrc5w6ofy06a1aa9''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-194''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''virtual_storage_resource_info_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fks8k75cypd3npfsbpfu8482k29''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-195''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''affected_virtual_storages_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fks9fk0ro3hm3vrcrera09yqnv''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-196''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ext_managed_virtual_links_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fksqljr3bd177jwahbdmejareka''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-197''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_ext_cp_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fkt6opj1797b3yb9p55dy0fma4r''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_ext_cp''', validate: true)
  }

  changeSet(id: '''1591462712136-198''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''subscription_id''', baseTableName: '''subscription_subscription_filter''', constraintName: '''fki6w3jg30xleio64bhd7f60a0a''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''subscription''', validate: true)
  }

  changeSet(id: '''1591462712136-199''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_package_id''', baseTableName: '''vnf_instantiation_levels''', constraintName: '''fklwyqroytfy98r72esg9q1exg3''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_package''', validate: true)
  }

  changeSet(id: '''1591462712136-200''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''security_groups_id''', baseTableName: '''ns_sap_security_groups''', constraintName: '''fkbh8ie2modas8p3snsn59lo5o6''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''security_group''', validate: true)
  }

  changeSet(id: '''1591462712136-201''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_sap_id''', baseTableName: '''ns_sap_security_groups''', constraintName: '''fkp31s59ost6r3ik0ya5xedfutc''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_sap''', validate: true)
  }

  changeSet(id: '''1591462712136-202''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_package_id''', baseTableName: '''nsd_package_vnf_package''', constraintName: '''fk664dkom15wkx4nu993rb3vrrc''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_package''', validate: true)
  }

  changeSet(id: '''1591462712136-203''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''id''', baseTableName: '''nsd_package_vnf_package''', constraintName: '''fk9qcyrbgj46ckrtunr1jx84ky1''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_package_vnf_package''', validate: true)
  }

  changeSet(id: '''1591462712136-204''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''nsd_package_id''', baseTableName: '''nsd_package_vnf_package''', constraintName: '''fkeiunqlenbugqq05frnj79gscg''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_package''', validate: true)
  }

  changeSet(id: '''1591462712136-205''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vim_connection_information_id''', baseTableName: '''vim_connection_information_extra''', constraintName: '''fkdd7m879cpujwoebo0xa0r7rww''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vim_connection_information''', validate: true)
  }

  changeSet(id: '''1591462712136-206''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''child_id''', baseTableName: '''nsd_package_nsd_package''', constraintName: '''fk7v3l9vwe45qwh85o9vmkqcjjj''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_package''', validate: true)
  }

  changeSet(id: '''1591462712136-207''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''parent_id''', baseTableName: '''nsd_package_nsd_package''', constraintName: '''fkn95hinideo8s5p8rmnoj2qs8j''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_package''', validate: true)
  }

  changeSet(id: '''1591462712136-208''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_compute_id''', baseTableName: '''vdu_instantiation_level''', constraintName: '''fktbso1bctmvdimnwrpmqb9bg3m''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_compute''', validate: true)
  }

  changeSet(id: '''1591462712136-209''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ip_over_ethernet_address_data_ip_addresses_entity_id''', baseTableName: '''ip_over_ethernet_address_data_ip_addresses_entity_fixed_address''', constraintName: '''fknsscdxjfxa1oqr5lyht1mjwee''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ip_over_ethernet_address_data_ip_addresses_entity''', validate: true)
  }

  changeSet(id: '''1591462712136-210''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instantiated_base_id''', baseTableName: '''vnf_instantiated_base_metadata''', constraintName: '''fkrui3vjus6650madaq2dp6mqjh''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instantiated_base''', validate: true)
  }

  changeSet(id: '''1591462712136-211''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grants_request_id''', baseTableName: '''grants_request_temp_resources''', constraintName: '''fkdj67gnffpk0219jamedp8gnbe''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grants_request''', validate: true)
  }

  changeSet(id: '''1591462712136-212''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''temp_resources_id''', baseTableName: '''grants_request_temp_resources''', constraintName: '''fkkyhrbuynhgu5cvcm5x4whhjvn''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_information''', validate: true)
  }

  changeSet(id: '''1591462712136-213''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instance_id''', baseTableName: '''vnf_instance_vnf_configurable_properties''', constraintName: '''fk5upk7b5wp7cs7dcwyte7pooi8''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-214''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_compute_id''', baseTableName: '''vnf_compute_storages''', constraintName: '''fkd1dbe30pm9rnmllili6st6d8i''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_compute''', validate: true)
  }

  changeSet(id: '''1591462712136-215''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grant_response_id''', baseTableName: '''grant_response_vim_connections''', constraintName: '''fkfs5yqs14oeu1vfgiy3t7o2y97''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_response''', validate: true)
  }

  changeSet(id: '''1591462712136-216''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vim_connections_id''', baseTableName: '''grant_response_vim_connections''', constraintName: '''fkkf5w4w9wlna58u9uobui4acjp''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vim_connection_information''', validate: true)
  }

  changeSet(id: '''1591462712136-217''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ip_over_ethernet_address_data_entity_id''', baseTableName: '''ip_over_ethernet_address_data_ip_addresses_entity''', constraintName: '''fkr9vj1lfpouf4lub6d786xn0au''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ip_over_ethernet_address_data_entity''', validate: true)
  }

  changeSet(id: '''1591462712136-218''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grants_request_id''', baseTableName: '''grants_request_remove_resources''', constraintName: '''fkdqbruh4bkbgyt6lejouvq1eyi''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grants_request''', validate: true)
  }

  changeSet(id: '''1591462712136-219''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''remove_resources_id''', baseTableName: '''grants_request_remove_resources''', constraintName: '''fkp7bqfsm3sai8f835qetvsr4bf''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_information''', validate: true)
  }

  changeSet(id: '''1591462712136-220''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_instance_id''', baseTableName: '''vnf_instance''', constraintName: '''fkdtkgv0r1et8c8d4mg5kxns5g6''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-221''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instance_id''', baseTableName: '''vnf_instance''', constraintName: '''fke67c0wdhfrrafmsfts2g9rxj9''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-222''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_pkg_id''', baseTableName: '''vnf_instance''', constraintName: '''fkrlj5i250i8v8x35q5oosqp367''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_package''', validate: true)
  }

  changeSet(id: '''1591462712136-223''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''nsd_package_id''', baseTableName: '''ns_virtual_link''', constraintName: '''fk44ake7h2gv0pos6v9hbpalui2''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_package''', validate: true)
  }

  changeSet(id: '''1591462712136-224''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_virtual_links_id''', baseTableName: '''ns_virtual_link''', constraintName: '''fk4siktfpy6qmt3yik1oesdph6j''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_package''', validate: true)
  }

  changeSet(id: '''1591462712136-225''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_vl_profile_id''', baseTableName: '''ns_virtual_link''', constraintName: '''fkp1xiv8ph6nhq94wrk7m8kv9pk''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_vl_profile''', validate: true)
  }

  changeSet(id: '''1591462712136-226''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vl_connectivity_type_id''', baseTableName: '''ns_virtual_link''', constraintName: '''fkskoau63uhyevc4vhi6lotxl9k''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_vl_connectivity_type''', validate: true)
  }

  changeSet(id: '''1591462712136-227''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instantiated_compute_id''', baseTableName: '''vnf_instantiated_base_vnfc_cp_info''', constraintName: '''fkkul6olm8e5gxwxo692sr4mvd1''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instantiated_base''', validate: true)
  }

  changeSet(id: '''1591462712136-228''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''protocol_id''', baseTableName: '''ns_cp_protocol_data''', constraintName: '''fknqfoc6p9we01dn97gs3f3085c''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_sap''', validate: true)
  }

  changeSet(id: '''1591462712136-229''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grant_response_id''', baseTableName: '''grant_response_update_resources''', constraintName: '''fk2eted7rwj0lfmvf9shcn6sfkf''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_response''', validate: true)
  }

  changeSet(id: '''1591462712136-230''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''update_resources_id''', baseTableName: '''grant_response_update_resources''', constraintName: '''fkhyqi9t39e8mphevo9fodmgx20''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_information''', validate: true)
  }

  changeSet(id: '''1591462712136-231''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grants_id''', baseTableName: '''ext_managed_virtual_link_data_entity''', constraintName: '''fk7ullaekw203yqbv9egbxg2lf''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_response''', validate: true)
  }

  changeSet(id: '''1591462712136-232''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grant_response_id''', baseTableName: '''grant_response_remove_resources''', constraintName: '''fk2bi7jy1ewnxg119fuwoca59ih''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_response''', validate: true)
  }

  changeSet(id: '''1591462712136-233''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''remove_resources_id''', baseTableName: '''grant_response_remove_resources''', constraintName: '''fk81jlo45pn219nqr31vbilyk1''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_information''', validate: true)
  }

  changeSet(id: '''1591462712136-234''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_storage_id''', baseTableName: '''vnf_storage''', constraintName: '''fk7iyfhnd7qw8e4e21wg6nolurf''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_package''', validate: true)
  }

  changeSet(id: '''1591462712136-235''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_package_id''', baseTableName: '''vnf_storage''', constraintName: '''fkfxbps2wdh1ce9rcolewwbonyr''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_package''', validate: true)
  }

  changeSet(id: '''1591462712136-236''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''affected_pnfs_id''', baseTableName: '''ns_instantiated_base''', constraintName: '''fk5e19gomuoriv0nk2co4nbg9vx''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-237''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_lcm_op_occs_id''', baseTableName: '''ns_instantiated_base''', constraintName: '''fk62maj1iafqvkollqrhogy1nty''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-238''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''affected_nss_id''', baseTableName: '''ns_instantiated_base''', constraintName: '''fk6nbo2b8a0nnepipdwp49rkpqy''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-239''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''affected_vnffgs_id''', baseTableName: '''ns_instantiated_base''', constraintName: '''fk6o3se4at316lbs5t96pyohc7d''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-240''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''sapd_id''', baseTableName: '''ns_instantiated_base''', constraintName: '''fk8ga70js6ikr35gylaasupbrd3''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_sap''', validate: true)
  }

  changeSet(id: '''1591462712136-241''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''affected_vls_id''', baseTableName: '''ns_instantiated_base''', constraintName: '''fk9uc0wp1bijot8kqmi0nsqtwv7''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-242''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''affected_saps_id''', baseTableName: '''ns_instantiated_base''', constraintName: '''fkbrlqnwqx1lwrdlemdansf4s1t''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-243''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''nsd_package_id''', baseTableName: '''ns_instantiated_base''', constraintName: '''fkokf0r9o16xdnfw9drv33c7629''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_package''', validate: true)
  }

  changeSet(id: '''1591462712136-244''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''affected_vnfs_id''', baseTableName: '''ns_instantiated_base''', constraintName: '''fkr55eg8emb2diufjci7uh7ebly''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''ns_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-245''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_instance_id''', baseTableName: '''ns_lcm_op_occs''', constraintName: '''fk1d159wjqjdh3hcbeycn7i9oo0''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-246''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_package_id''', baseTableName: '''vnf_package_additional_artifacts''', constraintName: '''fk1o6jg5plq27tc3ckjet4nooth''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_package''', validate: true)
  }

  changeSet(id: '''1591462712136-247''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ext_cp_info_id''', baseTableName: '''ext_cp_info''', constraintName: '''fka14r64bxrh5g98d90gvv93d5m''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-248''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_package_id''', baseTableName: '''vnf_package_ns_instance''', constraintName: '''fkj1d9vnvnoah1hv7ja1pp25pxu''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_package''', validate: true)
  }

  changeSet(id: '''1591462712136-249''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_instance_id''', baseTableName: '''vnf_package_ns_instance''', constraintName: '''fkpdaxudqe24s0g1rfjapgpmdy0''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-250''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grants_id''', baseTableName: '''zone_info_entity''', constraintName: '''fkjw26tdd5rb1m9bkc5x252elu3''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_response''', validate: true)
  }

  changeSet(id: '''1591462712136-251''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instantiated_compute_id''', baseTableName: '''vnf_instantiated_compute_affected_vnfc_cp_ids''', constraintName: '''fkrfo4ia2gwlmp10cbylvy8hdkj''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instantiated_base''', validate: true)
  }

  changeSet(id: '''1591462712136-252''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''pnfd_info_ids_id''', baseTableName: '''pnf_descriptor''', constraintName: '''fk1651kdb84mr9blnla16k22kxa''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_package''', validate: true)
  }

  changeSet(id: '''1591462712136-253''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_compute_id''', baseTableName: '''vnf_compute_monitoring_parameters''', constraintName: '''fk11pyk48pcnacyh978m966ca7x''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_compute''', validate: true)
  }

  changeSet(id: '''1591462712136-254''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''monitoring_parameters_id''', baseTableName: '''vnf_compute_monitoring_parameters''', constraintName: '''fkgb21g19ijddmwpks365lc80jq''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instantiated_base''', validate: true)
  }

  changeSet(id: '''1591462712136-255''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''nsd_package_id''', baseTableName: '''nsd_package_user_defined_data''', constraintName: '''fkidg9mpqwbwd55g1keqnvlknu''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_package''', validate: true)
  }

  changeSet(id: '''1591462712136-256''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grant_response_id''', baseTableName: '''grant_response_additional_params''', constraintName: '''fkmj6b5gqdsgjf2ggov771vqlca''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_response''', validate: true)
  }

  changeSet(id: '''1591462712136-257''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grants_request_id''', baseTableName: '''grants_request_compute_resource_flavours''', constraintName: '''fk4rjru3d6je16kxgniij6drkxn''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grants_request''', validate: true)
  }

  changeSet(id: '''1591462712136-258''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_link_ports_id''', baseTableName: '''link_port_info''', constraintName: '''fkim0xuiw1m8hgpsu6jkaaugo2v''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instantiated_base''', validate: true)
  }

  changeSet(id: '''1591462712136-259''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vim_connection_information_id''', baseTableName: '''networks''', constraintName: '''fkruiasji57ptkyhx0dng60euc5''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vim_connection_information''', validate: true)
  }

  changeSet(id: '''1591462712136-260''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grant_response_id''', baseTableName: '''grant_response_software_images''', constraintName: '''fk2pw6g48cpjga81mm70ps4lm5c''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_response''', validate: true)
  }

  changeSet(id: '''1591462712136-261''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_compute_id''', baseTableName: '''vnf_compute_aspect_delta''', constraintName: '''fkg81cr1mufn1ubtbx8eijp4u8t''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_compute''', validate: true)
  }

  changeSet(id: '''1591462712136-262''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instantiated_compute_id''', baseTableName: '''vnf_instantiated_compute_removed_storage_resource_ids''', constraintName: '''fklvyt4l7wk13abutq0f522bx6x''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instantiated_base''', validate: true)
  }

  changeSet(id: '''1591462712136-263''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''scale_status_id''', baseTableName: '''scale_info''', constraintName: '''fkr0aqh7yyrac2tlw60qxw3w6ac''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-264''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vim_connection_information_id''', baseTableName: '''vim_connection_information_interface_info''', constraintName: '''fk8b3e2w9advo9df917gve8rbym''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vim_connection_information''', validate: true)
  }

  changeSet(id: '''1591462712136-265''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vim_connections_id''', baseTableName: '''grants_request_vim_connections''', constraintName: '''fk6o3yfd7auc374d66q7rycwisx''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vim_connection_information''', validate: true)
  }

  changeSet(id: '''1591462712136-266''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grants_request_id''', baseTableName: '''grants_request_vim_connections''', constraintName: '''fket5t6y9rux7gmmhdbt1cdy0g''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grants_request''', validate: true)
  }

  changeSet(id: '''1591462712136-267''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''temp_resources_id''', baseTableName: '''grant_response_temp_resources''', constraintName: '''fk8046mx38d2tav6154tl7e2riv''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_information''', validate: true)
  }

  changeSet(id: '''1591462712136-268''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grant_response_id''', baseTableName: '''grant_response_temp_resources''', constraintName: '''fkipl3t9pihkoy2hhwmys18d81a''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_response''', validate: true)
  }

  changeSet(id: '''1591462712136-269''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''nested_ns_instance_data_id''', baseTableName: '''nested_ns_instance_data''', constraintName: '''fkbiqbcwg58783n59qvmjblutju''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-270''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''add_resources_id''', baseTableName: '''grants_request_add_resources''', constraintName: '''fk42004uxu64ooop4qv453l1jk1''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_information''', validate: true)
  }

  changeSet(id: '''1591462712136-271''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grants_request_id''', baseTableName: '''grants_request_add_resources''', constraintName: '''fkh24nfmtmyx2bxkqc0ho90pm3n''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grants_request''', validate: true)
  }

  changeSet(id: '''1591462712136-272''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vim_connection_information_id''', baseTableName: '''instanciated_resource''', constraintName: '''fktmxfdki5mfq08bm2h5wvbagr0''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vim_connection_information''', validate: true)
  }

  changeSet(id: '''1591462712136-273''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ext_virtual_link_info_id''', baseTableName: '''ext_virtual_link_data_entity''', constraintName: '''fk3iyt01il08gnbcpeuoyyq46gu''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-274''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ext_virtual_links_id''', baseTableName: '''ext_virtual_link_data_entity''', constraintName: '''fkq8ltad8jp81d0r4ldhtljf73a''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grants_request''', validate: true)
  }

  changeSet(id: '''1591462712136-275''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vl_profile_entity_id''', baseTableName: '''vnf_vl''', constraintName: '''fkkcj2iva11l24i01qyiwfd0wsc''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vl_profile_entity''', validate: true)
  }

  changeSet(id: '''1591462712136-276''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_vl_id''', baseTableName: '''vnf_vl''', constraintName: '''fkpcpw10crt6dpfl8jp2dk23oo''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_package''', validate: true)
  }

  changeSet(id: '''1591462712136-277''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instance_data_id''', baseTableName: '''vnf_instance_data''', constraintName: '''fkqo85gr5wn6lc7l31yi5q9g28m''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-278''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_compute_id''', baseTableName: '''vnf_compute''', constraintName: '''fk44misqc4abk9lu2cvs6rs22l4''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_package''', validate: true)
  }

  changeSet(id: '''1591462712136-279''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grants_request_id''', baseTableName: '''grants_request_additional_params''', constraintName: '''fkmawi87cr1u2kbidha6cwfl5sn''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grants_request''', validate: true)
  }

  changeSet(id: '''1591462712136-280''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_saps_id''', baseTableName: '''ns_sap''', constraintName: '''fkd59485mlkhhjhx86so2mc255e''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_package''', validate: true)
  }

  changeSet(id: '''1591462712136-281''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''nsd_package_id''', baseTableName: '''ns_sap''', constraintName: '''fkgrfrriwjsdo2w9bpfd4ltni4e''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_package''', validate: true)
  }

  changeSet(id: '''1591462712136-282''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''virtual_network_interface_requirements_id''', baseTableName: '''virtual_nic_req''', constraintName: '''fk5saeu078vdbx2couwoves7lb6''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_ext_cp''', validate: true)
  }

  changeSet(id: '''1591462712136-283''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''nested_ns_instance_id''', baseTableName: '''nsd_instance_nested_ns_instance''', constraintName: '''fk7djxbg0qxkdvrhx5oaftc9t7t''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-284''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''nsd_instance_id''', baseTableName: '''nsd_instance_nested_ns_instance''', constraintName: '''fk8yhbi5octdr5gchft38sjfvam''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-285''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''monitoring_parameters_id''', baseTableName: '''vnf_instance_monitoring_parameters''', constraintName: '''fkio6x263qpt31c0gvsaw69irsq''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instantiated_base''', validate: true)
  }

  changeSet(id: '''1591462712136-286''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_ext_cp_data_entity_cpd_id''', baseTableName: '''vnf_ext_cp_configuration''', constraintName: '''fko5a9d11gxe7i7ak42h7433jx7''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''cpd_id''', referencedTableName: '''vnf_ext_cp_data_entity''', validate: true)
  }

  changeSet(id: '''1591462712136-287''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''monitoring_parameters_id''', baseTableName: '''vnf_lcm_op_occs_monitoring_parameters''', constraintName: '''fkbdwtk7bugghqi3lqfinf7tk5j''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instantiated_base''', validate: true)
  }

  changeSet(id: '''1591462712136-288''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_lcm_op_occs_id''', baseTableName: '''vnf_lcm_op_occs_monitoring_parameters''', constraintName: '''fkbn8wmhvcr3c4ft6l3c14ndj3w''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-289''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnfc_resource_info_id''', baseTableName: '''vnf_instance_vnfc_resource_info''', constraintName: '''fk8261jtivj5q8wm8f6dhxdi5rf''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instantiated_base''', validate: true)
  }

  changeSet(id: '''1591462712136-290''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_lcm_op_occs_id''', baseTableName: '''vnf_lcm_op_occs_vnfc_resource_info''', constraintName: '''fk5d746rdxx7vs7ygeeaxbgjr5y''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-291''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnfc_resource_info_id''', baseTableName: '''vnf_lcm_op_occs_vnfc_resource_info''', constraintName: '''fklo9qkaliy6m80ba6yhf6u7rco''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instantiated_base''', validate: true)
  }

  changeSet(id: '''1591462712136-292''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instantiated_compute_id''', baseTableName: '''vnf_instantiated_compute_added_storage_resource_ids''', constraintName: '''fk9j3e4w4s8cl59bd39h88vj3g5''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instantiated_base''', validate: true)
  }

  changeSet(id: '''1591462712136-293''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''add_resources_id''', baseTableName: '''grant_response_add_resources''', constraintName: '''fkb3ek62bxxy9rg3v4i33jo5nk1''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_information''', validate: true)
  }

  changeSet(id: '''1591462712136-294''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''scaling_aspect_id''', baseTableName: '''scaling_aspect_step_deltas''', constraintName: '''fkni47u9ofhmk2ak1kweupw2qxo''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''scaling_aspect''', validate: true)
  }

  changeSet(id: '''1591462712136-295''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_lcm_op_occs_id''', baseTableName: '''vnf_live_instance''', constraintName: '''fkr2ymgxr79m3x0uo1yn92n2s1v''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_lcm_op_occs''', validate: true)
  }

  changeSet(id: '''1591462712136-296''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instantiated_base_id''', baseTableName: '''vnf_live_instance''', constraintName: '''fkrm8c9k2jxpxn3vex9t0uw84yd''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instantiated_base''', validate: true)
  }

  changeSet(id: '''1591462712136-297''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grants_request_id''', baseTableName: '''grants_request_update_resources''', constraintName: '''fkapor5hf7gjiq9flefmlpekhv8''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grants_request''', validate: true)
  }

  changeSet(id: '''1591462712136-298''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''update_resources_id''', baseTableName: '''grants_request_update_resources''', constraintName: '''fkbqg2guru2426yo0rxw0rnr810''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grant_information''', validate: true)
  }

  changeSet(id: '''1591462712136-299''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grants_request_id''', baseTableName: '''grants_request_software_images''', constraintName: '''fkd6hc4bmw92riradjhnnwu022m''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grants_request''', validate: true)
  }

  changeSet(id: '''1591462712136-300''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''zone_groups_id''', baseTableName: '''grants_request_zone_groups''', constraintName: '''fk4g1sxhqgi53bcrcvm0ofpukwq''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''zone_group_information''', validate: true)
  }

  changeSet(id: '''1591462712136-301''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''grants_request_id''', baseTableName: '''grants_request_zone_groups''', constraintName: '''fkhicflykr2tgit7g5qaexm4x7g''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''grants_request''', validate: true)
  }

  changeSet(id: '''1591462712136-302''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ns_instance_id''', baseTableName: '''ns_live_instance''', constraintName: '''fkece7taxbww9bqoj9wkqr4nd4s''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''nsd_instance''', validate: true)
  }

  changeSet(id: '''1591462712136-303''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instantiated_compute_id''', baseTableName: '''vnf_instantiated_compute_storage_resource_ids''', constraintName: '''fkhiq4jfl5j42ypvq49n1jdowno''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instantiated_base''', validate: true)
  }

  changeSet(id: '''1591462712136-304''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''vnf_instantiated_compute_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''fk4terfil0pn9w0oep1tbgm6o6s''', deferrable: false, initiallyDeferred: false, onDelete: '''NO ACTION''', onUpdate: '''NO ACTION''', referencedColumnNames: '''id''', referencedTableName: '''vnf_instantiated_base''', validate: true)
  }

  changeSet(id: '''1591462712136-305''', author: '''olivier (generated)''') {
    createSequence(cacheSize: 1, cycle: false, dataType: '''bigint''', incrementBy: 1, maxValue: 9223372036854775807, minValue: 1, sequenceName: '''hibernate_sequence''', startValue: 1)
  }

}
