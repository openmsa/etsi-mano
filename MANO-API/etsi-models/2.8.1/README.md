# b/MANO-API/src/SOL002/VNFLifecycleManagement/definitions/SOL002VNFLifecycleManagement_def.yaml
+          vnfcInfo:
+            description: >
+              Information about the VNFC instances.
+            type: array
+            items:
+              $ref: "#/definitions/VnfcInfo"


+      extensions:
+        description: >
+          If present, this attribute provides modifications to the values of the "extensions" attribute in
+          "VnfInstance", as defined in clause 5.5.2.2. Provisions for handling extensions during the operation,
+          are defined in clause 5.4.7.3.1.
+        $ref: "../../../definitions/SOL002SOL003_def.yaml#/definitions/KeyValuePairs"
+      vnfConfigurableProperties:
+        description: >
+          If present, this attribute provides modifications to the values of the vnfConfigurableProperties" attribute
+          in "VnfInstance", as defined in clause 5.5.2.2. Provisions for handling VNF configurable properties during
+          the operation, are defined in clause 5.4.7.3.1.
+        $ref: "../../../definitions/SOL002SOL003_def.yaml#/definitions/KeyValuePairs"


+      gracefulTerminationTimeout:
+        description: >
+          This attribute is only applicable in case of graceful termination.
+          It defines the time to wait for the VNF to be taken out of service
+          before shutting down the VNF and releasing the resources.
+          The unit is seconds.
+          If not given and the "terminationType" attribute is set to
+          "GRACEFUL", it is expected that the VNFM waits for the successful
+          taking out of service of the VNF, no matter how long it takes,
+          before shutting down the VNF and releasing the resources.
+        type: integer

+  VnfcResourceInfo:
+  VnfInfoModifications:

# b/MANO-API/src/SOL003/VNFPackageManagement/VNFPackageManagement.yaml
+        - name: exclude_all_mano_artifacts
+          description: >
+            Flag (i.e. parameter without value) that instructs the NFVO to exclude 
+            the set of additional MANO artifacts (i.e. those that are not images) 
+            from the response payload body. The NFVO shall support this parameter. 
+            The VNFM may supply this parameter.
+          in: query
+          required: false
+          type: string
+        - name: exclude_all_non_mano_artifacts
+          description: >
+            Flag (i.e. parameter without value) that instructs the NFVO to exclude 
+            the set of non-MANO artifacts from the response payload body. 
+            The NFVO shall support this parameter. The VNFM may supply this parameter.
+          in: query
+          required: false
+          type: string
+        - name: include_external_artifacts
+          description: >
+            Flag (i.e. parameter without value) that instructs the NFVO to include external 
+            artifacts in the response payload body. It shall not be treated as an error if 
+            this flag is provided but there is no external artifact to include in the result. 
+            If this parameter is missing, no external artifacts shall be included.
+            The NFVO shall support this parameter. The VNFM may supply this parameter.
+          in: query
+          required: false
+          type: string
+        - name: select_non_mano_artifact_sets
+          description: >
+            Comma-separated list of non-MANO artifact set identifiers for which the artifacts 
+            are to be included in the response body. The NFVO should support this parameter. 
+            If the NFVO does not support this parameter, it shall ignore it, i.e. provide a 
+            response as if no parameter was provided. The VNFM may supply this parameter.
+          in: query
+          required: false
+          type: string
+        - name: include_signatures
+          description: >
+            If this parameter is provided, the NFVO shall include in the ZIP archive the individual 
+            signatures and, if provided, related certificates for the included artifacts, in the 
+            format in which they are provided in the VNF package. If this parameter is not given, 
+            the NFVO shall only provide copies of the artifact files. This URI query parameter is 
+            a flag, i.e. it shall have no value. The NFVO shall support this parameter.
+          in: query
+          required: false
+          type: string

+      imageUri:
+        description: >
+          URI of the image artifact as defined in the VNF package manifest. 
+          Shall be present if the image artifact is external to the VNF package and 
+          shall be absent otherwise.
+          EXAMPLE:     https://example.com/m%40ster.vhd
+        $ref: "../../../definitions/SOL002SOL003_def.yaml#/definitions/Uri"

+      artifactURI:
+        description: >
+          URI of the artifact as defined in the VNF package manifest.
+          Shall be present if the artifact is external to the package
+          and shall be absent otherwise.
+
+          EXAMPLE: https://example.com/m%40ster.sh
+        $ref: "../../../definitions/SOL002SOL003_def.yaml#/definitions/Uri"

# b/MANO-API/src/SOL003/VirtualisedResourcesQuotaAvailableNotificationNotification/VirtualisedResourcesQuotaAvailableNotificationNotification.yaml


# b/MANO-API/src/definitions/SOL002SOL003VNFPerformanceManagement_def.yaml

+      pmJobId:
+        description: >
+          Identifier of the PM job for which performance information is available.
+        $ref: "SOL002SOL003_def.yaml#/definitions/Identifier"



