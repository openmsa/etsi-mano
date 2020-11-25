databaseChangeLog {
  changeSet(id: '''1591465616143-4''', author: '''olivier (generated)''') {
    createTable(tableName: '''ip_over_ethernet_address_data_ip_addresses_entity_fixed_addresses''') {
      column(name: '''ip_over_ethernet_address_data_ip_addresses_entity_id''', type: '''UUID''') {
        constraints(nullable: false)
      }
      column(name: '''fixed_addresses''', type: '''VARCHAR(255)''')
    }
  }

  changeSet(id: '''1591465616143-5''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ext_managed_virtual_link_info_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''FK18vxnolswycj6xc9fnhqctpny''', deferrable: false, initiallyDeferred: false, referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591465616143-6''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ext_cp_info_id''', baseTableName: '''ext_cp_info''', constraintName: '''FK43uwob835p07s7oy8q4pkl289''', deferrable: false, initiallyDeferred: false, referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591465616143-7''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''virtual_storage_resource_info_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''FKiiyrkoqnvx30t7abgdw4ooec9''', deferrable: false, initiallyDeferred: false, referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591465616143-8''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''virtual_link_resource_info_id''', baseTableName: '''vnf_instantiated_base''', constraintName: '''FKma7flrvtci1sdga3w29strcwj''', deferrable: false, initiallyDeferred: false, referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591465616143-9''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''scale_status_id''', baseTableName: '''scale_info''', constraintName: '''FKr3w2rqxxvmmbq473y43qjmrf2''', deferrable: false, initiallyDeferred: false, referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591465616143-10''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ext_virtual_link_info_id''', baseTableName: '''ext_virtual_link_data_entity''', constraintName: '''FKrcm03ec0oaldj9q6ksnx859i4''', deferrable: false, initiallyDeferred: false, referencedColumnNames: '''id''', referencedTableName: '''vnf_instance''', validate: true)
  }

  changeSet(id: '''1591465616143-11''', author: '''olivier (generated)''') {
    dropForeignKeyConstraint(baseTableName: '''ext_virtual_link_data_entity''', constraintName: '''fk3iyt01il08gnbcpeuoyyq46gu''')
  }

  changeSet(id: '''1591465616143-12''', author: '''olivier (generated)''') {
    dropForeignKeyConstraint(baseTableName: '''vnf_instantiated_base''', constraintName: '''fk77ah4eaj1j2rb51kk8hjg0abo''')
  }

  changeSet(id: '''1591465616143-13''', author: '''olivier (generated)''') {
    dropForeignKeyConstraint(baseTableName: '''vnf_instantiated_base''', constraintName: '''fk8xmu28mm0wpat70kuq0qivyd3''')
  }

  changeSet(id: '''1591465616143-14''', author: '''olivier (generated)''') {
    dropForeignKeyConstraint(baseTableName: '''ext_cp_info''', constraintName: '''fka14r64bxrh5g98d90gvv93d5m''')
  }

  changeSet(id: '''1591465616143-15''', author: '''olivier (generated)''') {
    dropForeignKeyConstraint(baseTableName: '''scale_info''', constraintName: '''fkr0aqh7yyrac2tlw60qxw3w6ac''')
  }

  changeSet(id: '''1591465616143-16''', author: '''olivier (generated)''') {
    dropForeignKeyConstraint(baseTableName: '''vnf_instantiated_base''', constraintName: '''fks8k75cypd3npfsbpfu8482k29''')
  }

  changeSet(id: '''1591465616143-17''', author: '''olivier (generated)''') {
    dropTable(tableName: '''ip_over_ethernet_address_data_ip_addresses_entity_fixed_address''')
  }

  changeSet(id: '''1591465616143-1''', author: '''olivier (generated)''') {
    dropForeignKeyConstraint(baseTableName: '''ip_over_ethernet_address_data_ip_addresses_entity_fixed_addresses''', constraintName: '''FKnsscdxjfxa1oqr5lyht1mjwee''')
  }

  changeSet(id: '''1591465616143-2''', author: '''olivier (generated)''') {
    addForeignKeyConstraint(baseColumnNames: '''ip_over_ethernet_address_data_ip_addresses_entity_id''', baseTableName: '''ip_over_ethernet_address_data_ip_addresses_entity_fixed_addresses''', constraintName: '''FKnsscdxjfxa1oqr5lyht1mjwee''', referencedColumnNames: '''id''', referencedTableName: '''ip_over_ethernet_address_data_ip_addresses_entity''')
  }

  changeSet(id: '''1591465616143-3''', author: '''olivier (generated)''') {
    alterSequence(sequenceName: '''hibernate_sequence''')
  }

}
