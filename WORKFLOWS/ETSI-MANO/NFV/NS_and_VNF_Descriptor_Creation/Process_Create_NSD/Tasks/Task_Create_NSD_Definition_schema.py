import json
import uuid
import os
import errno
import sys
from msa_sdk import constants
from msa_sdk.order import Order
from msa_sdk.variables import Variables
from msa_sdk.msa_api import MSA_API

dev_var = Variables()
dev_var.add('nsd_name', var_type='String')
context = Variables.task_call(dev_var)
#filename is created based-on the device external reference
uuid_gen = str(uuid.uuid4())
if 'uuid_gen' in context:
    # if the uuid_gen exists in the context do not create a new one. This allow to update the existing NSD.
    name = context.get('nsd_name')
    if name:
        nsd_name = name + '_' + uuid_gen
#store in the context for the next process tasks usage.
context.update(uuid_gen=uuid_gen)

nsd_name = uuid_gen
if 'nsd_name' in context:
    nsd_name = context.get('nsd_name') + '_' + uuid_gen

nsd_sol0001_schema = 'etsi_nfv_sol001_nsd_types.yaml'
filename = '/opt/fmc_repository/Datafiles/NFV/NSD/' + nsd_name + '/Definition/' + nsd_sol0001_schema

#nsd_sol0001_schema content

etsi_nfv_sol001_nsd_types = """
tosca_definitions_version: tosca_simple_yaml_1_2
description: ETSI NFV SOL 001 nsd types definitions version 2.6.1
metadata:
  template_name: etsi_nfv_sol001_nsd_types
  template_author: ETSI_NFV
  template_version: 2.6.1

imports:
  - https://forge.etsi.org/rep/nfv/SOL001/raw/v2.6.1/etsi_nfv_sol001_common_types.yaml

data_types:
  tosca.datatypes.nfv.ServiceAvailability:
    derived_from: tosca.datatypes.Root
    description: service availability
    properties:
      level:
        type: string
        description: service availability levels
        required: true
        constraints:
          - valid_values: [ level1, level2, level3 ]

  tosca.datatypes.nfv.NsVlProfile:
    derived_from: tosca.datatypes.Root
    description: Describes additional instantiation data for a given NsVirtualLink used in a specific NS deployment flavour.
    properties:
      max_bitrate_requirements:
        type: tosca.datatypes.nfv.LinkBitrateRequirements
        description: Specifies the maximum bitrate requirements for a VL instantiated according to this profile.
        required: true
      min_bitrate_requirements:
        type: tosca.datatypes.nfv.LinkBitrateRequirements
        description: Specifies the minimum bitrate requirements for a VL instantiated according to this profile.
        required: true
      qos:
        type: tosca.datatypes.nfv.NsVirtualLinkQos
        description: Specifies the QoS requirements of a VL instantiated according to this profile.
        required: false
      service_availability:
        type: tosca.datatypes.nfv.ServiceAvailability
        description: Network service virtual link service availability levels, as described in ETSI GS NFV-REL 001
        required: false

  tosca.datatypes.nfv.NsVirtualLinkQos:
    derived_from: tosca.datatypes.nfv.Qos
    description: describes QoS data for a given VL used in a VNF deployment flavour
    properties:
      priority:
        type: integer
        constraints:
          - greater_or_equal: 0
        description: Specifies the priority level in case of congestion on the underlying physical links
        required: false

  tosca.datatypes.nfv.NsProfile:
    derived_from: tosca.datatypes.Root
    description: describes a profile for instantiating NSs of a particular NS DF according to a specific NSD and NS DF.
    properties:
      ns_instantiation_level:
        type: string
        description: Identifier of the instantiation level of the NS DF to be used for instantiation. If not present, the default instantiation level as declared in the NSD shall be used.
        required: false
      min_number_of_instances:
        type: integer
        description: Minimum number of instances of the NS based on this NSD that is permitted to exist for this NsProfile.
        required: true
        constraints:
          - greater_or_equal: 0
      max_number_of_instances:
        type: integer
        description: Maximum number of instances of the NS based on this NSD that is permitted to exist for this NsProfile.
        required: true
        constraints:
          - greater_or_equal: 0
      flavour_id:
        type: string
        description: Identifies the applicable network service DF within the scope of the NSD.
        required: true

interface_types:
  tosca.interfaces.nfv.Nslcm:
    derived_from: tosca.interfaces.Root
    description: This interface encompasses a set of TOSCA operations corresponding to NS LCM operations defined in ETSI GS NFV-IFA 013. as well as to preamble and postamble procedures to the execution of the NS LCM operations.
    instantiate_start:
      description: Preamble to execution of the instantiate operation
    instantiate:
      description: Base procedure for instantiating an NS, corresponding to the Instantiate NS operation defined in GS NFV-IFA 013.
    instantiate_end:
      description: Postamble to the execution of the instantiate operation
    terminate_start:
      description: Preamble to execution of the terminate operation
    terminate:
      description: Base procedure for terminating an NS, corresponding to the Terminate NS operation defined in GS NFV-IFA 013.
    terminate_end:
      description: Postamble to the execution of the terminate operation
    update_start:
      description: Preamble to execution of the update operation
    update:
      description: Base procedure for updating an NS, corresponding to the Update NS operation defined in GS NFV-IFA 013.
    update_end:
      description: Postamble to the execution of the update operation
    scale_start:
      description: Preamble to execution of the scale operation
    scale:
      description: Base procedure for scaling an NS, corresponding to the Scale NS operation defined in GS NFV-IFA 013.
    scale_end:
      description: Postamble to the execution of the scale operation
    heal_start:
      description: Preamble to execution of the heal operation
    heal:
      description: Base procedure for healing an NS, corresponding to the Heal NS operation defined in GS NFV-IFA 013.
    heal_end:
      description: Postamble to the execution of the heal operation

group_types:
  tosca.groups.nfv.PlacementGroup:
    derived_from: tosca.groups.Root
    description: PlacementGroup is used for describing the affinity or anti-affinity relationship applicable between VNF instances created using different VNFDs, the Virtual Link instances created using different VLDs or the nested NS instances created using different NSDs
    properties:
      description:
        type: string
        description: Human readable description of the group
        required: true
    members: [ tosca.nodes.nfv.VNF, tosca.nodes.nfv.NsVirtualLink, tosca.nodes.nfv.NS ]

node_types:
  tosca.nodes.nfv.NS:
    derived_from: tosca.nodes.Root
    properties:
      descriptor_id:
        type: string # GUID
        description: Identifier of this NS descriptor
        required: true
      designer:
        type: string
        description: Identifies the designer of the NSD.
        required: true
      version:
        type: string
        description: Identifies the version of the NSD.
        required: true
      name:
        type: string
        description: Provides the human readable name of the NSD.
        required: true
      invariant_id:
        type: string
        description: Identifies an NSD in a version independent manner. This attribute is invariant across versions of NSD.
        required: true
      flavour_id:
        type: string
        description: Identifier of the NS Deployment Flavour within the NSD
        required: true
      ns_profile:
        type: tosca.datatypes.nfv.NsProfile
        description: Specifies a profile of a NS, when this NS is used as nested NS within another NS.
        required: false
    requirements:
      - virtual_link:
          capability: tosca.capabilities.nfv.VirtualLinkable
          relationship: tosca.relationships.nfv.VirtualLinksTo
          node: tosca.nodes.nfv.NsVirtualLink
          occurrences: [ 0, 1 ]
    interfaces:
      Nslcm:
        type: tosca.interfaces.nfv.Nslcm

  tosca.nodes.nfv.Sap:
    derived_from: tosca.nodes.nfv.Cp
    description: node definition of SAP.
    requirements:
      - external_virtual_link:
          capability: tosca.capabilities.nfv.VirtualLinkable
          relationship: tosca.relationships.nfv.VirtualLinksTo
      - internal_virtual_link:
          capability: tosca.capabilities.nfv.VirtualLinkable
          relationship: tosca.relationships.nfv.VirtualLinksTo

  tosca.nodes.nfv.NsVirtualLink:
    derived_from: tosca.nodes.Root
    description: node definition of Virtual Links
    properties:
      vl_profile:
        type: tosca.datatypes.nfv.NsVlProfile # only covers min/max bitrate requirements
        description: Specifies instantiation parameters for a virtual link of a particular NS deployment flavour.
        required: true
      connectivity_type:
        type: tosca.datatypes.nfv.ConnectivityType
        required: true
      test_access:
        type: list
        description: Test access facilities available on the VL
        required: false
        entry_schema:
          type: string
          constraints:
            - valid_values: [ passive_monitoring, active_loopback ]
      description:
        type: string
        required: false
        description: Human readable information on the purpose of the virtual link (e.g. VL for control plane traffic).
    capabilities:
      virtual_linkable:
        type: tosca.capabilities.nfv.VirtualLinkable

policy_types:
  tosca.policies.nfv.AffinityRule:
    derived_from: tosca.policies.Placement
    description: The AffinityRule describes the affinity rules applicable for the defined targets
    properties:
      scope:
        type: string
        description: scope of the rule is an NFVI_node, an NFVI_PoP, etc.
        required: true
        constraints:
          - valid_values: [ nfvi_node, zone, zone_group, nfvi_pop ]
    targets: [ tosca.nodes.nfv.VNF, tosca.nodes.nfv.NsVirtualLink, tosca.nodes.nfv.NS, tosca.groups.nfv.PlacementGroup ]

  tosca.policies.nfv.AntiAffinityRule:
    derived_from: tosca.policies.Placement
    description: The AntiAffinityRule describes the anti-affinity rules applicable for the defined targets
    properties:
      scope:
        type: string
        description: scope of the rule is an NFVI_node, an NFVI_PoP, etc.
        required: true
        constraints:
          - valid_values: [ nfvi_node, zone, zone_group, nfvi_pop ]
    targets: [ tosca.nodes.nfv.VNF, tosca.nodes.nfv.NsVirtualLink, tosca.nodes.nfv.NS, tosca.groups.nfv.PlacementGroup ]

  tosca.policies.nfv.SecurityGroupRule:
    derived_from: tosca.policies.Root
    description: The SecurityGroupRule type is a policy type specified the matching criteria for the ingress and/or egress traffic to/from visited connection points as defined in ETSI GS NFV-IFA 011 [1].
    properties:
      description:
        type: string
        description: Human readable description of the security group rule.
        required: false
      direction:
        type: string
        description: The direction in which the security group rule is applied. The direction of 'ingress' or 'egress' is specified against the associated CP. I.e., 'ingress' means the packets entering a CP, while 'egress' means the packets sent out of a CP.
        required: false
        constraints:
          - valid_values: [ ingress, egress ]
        default: ingress
      ether_type:
        type: string
        description: Indicates the protocol carried over the Ethernet layer.
        required: false
        constraints:
          - valid_values: [ ipv4, ipv6 ]
        default: ipv4
      protocol:
        type: string
        description: Indicates the protocol carried over the IP layer. Permitted values include any protocol defined in the IANA protocol registry, e.g. TCP, UDP, ICMP, etc.
        required: false
        constraints:
          - valid_values: [ hopopt, icmp, igmp, ggp, ipv4, st, tcp, cbt, egp, igp, bbn_rcc_mon, nvp_ii, pup, argus, emcon, xnet, chaos, udp, mux, dcn_meas, hmp, prm, xns_idp, trunk_1, trunk_2, leaf_1, leaf_2, rdp, irtp, iso_tp4, netblt, mfe_nsp, merit_inp, dccp, 3pc, idpr, xtp, ddp, idpr_cmtp, tp++, il, ipv6, sdrp, ipv6_route, ipv6_frag, idrp, rsvp, gre, dsr, bna, esp, ah, i_nlsp, swipe, narp, mobile, tlsp, skip, ipv6_icmp, ipv6_no_nxt, ipv6_opts, cftp, sat_expak, kryptolan, rvd, ippc, sat_mon, visa, ipcv, cpnx, cphb, wsn, pvp, br_sat_mon, sun_nd, wb_mon, wb_expak, iso_ip, vmtp, secure_vmtp, vines, ttp, iptm, nsfnet_igp, dgp, tcf, eigrp, ospfigp, sprite_rpc, larp, mtp, ax.25, ipip, micp, scc_sp, etherip, encap, gmtp, ifmp, pnni, pim, aris, scps, qnx, a/n, ip_comp, snp, compaq_peer, ipx_in_ip, vrrp, pgm, l2tp, ddx, iatp, stp, srp, uti, smp, sm, ptp, isis, fire, crtp, crudp, sscopmce, iplt, sps, pipe, sctp, fc, rsvp_e2e_ignore, mobility, udp_lite, mpls_in_ip, manet, hip, shim6, wesp, rohc ]
        default: tcp
      port_range_min:
        type: integer
        description: Indicates minimum port number in the range that is matched by the security group rule. If a value is provided at design-time, this value may be overridden at run-time based on other deployment requirements or constraints.
        required: false
        constraints:
          - greater_or_equal: 0
          - less_or_equal: 65535
        default: 0
      port_range_max:
        type: integer
        description: Indicates maximum port number in the range that is matched by the security group rule. If a value is provided at design-time, this value may be overridden at run-time based on other deployment requirements or constraints.
        required: false
        constraints:
          - greater_or_equal: 0
          - less_or_equal: 65535
        default: 65535
    targets: [ tosca.nodes.nfv.Sap ]
"""

#create file in http server directory.
if not os.path.exists(os.path.dirname(filename)):
    try:
        os.makedirs(os.path.dirname(filename))
    except OSError as exc: # Guard against race condition
        if exc.errno != errno.EEXIST:
            raise
    with open(filename, "w") as file:
        file.write(etsi_nfv_sol001_nsd_types)
        file.close()


MSA_API.task_success('NSD TOSCA Sol001 schema was created successfully.', context, True)