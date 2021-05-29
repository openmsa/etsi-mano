# b/MANO-API/src/SOL002/VNFConfiguration/VNFConfiguration.yaml
      extCpConfig:
+      intCpConfig:
         description: >
-          Configuration parameters for the external CPs of the VNFC instance.
-        $ref: '#/definitions/CpConfiguration'
+          Configuration parameters for the internal CPs of the VNFC instance.
+        type: array
+        items:
+          $ref: '#/definitions/CpConfiguration'


# b/MANO-API/src/SOL002/VNFFaultManagement/definitions/SOL002VNFFaultManagement_def.yaml
+      alarmAcknowledgedTime:
+        description: >
+          Time stamp indicating when the alarm was
+          acknowledged. It shall be present if the alarm has
+          been acknowledged.
+        $ref: "../../../definitions/SOL002SOL003_def.yaml#/definitions/DateTime"

# b/MANO-API/src/SOL002/VNFLifecycleManagement/VNFLifecycleManagement.yaml
+      - name: cancelMode
+        description: >
+          The POST request to this resource shall include a CancelMode
+          structure in the payload body to choose between "graceful" and
+          "forceful" cancellation.
+        in: body
+        required: true
+        schema:
+          $ref: "../../definitions/SOL002SOL003VNFLifecycleManagement_def.yaml#/definitions/CancelMode"


+        - name: nextpage_opaque_marker
+          description: >
+            Marker to obtain the next page of a paged response.
+            Shall be supported by the VNFM if the VNFM supports
+            alternative 2 (paging) according to clause 5.4.2.1
+            of ETSI GS NFV-SOL 013 for this resource.
+          in: query
+          required: false
+          type: string

# b/MANO-API/src/SOL002/VNFLifecycleManagement/definitions/SOL002VNFLifecycleManagement_def.yaml
+          maxScaleLevels:
+            description: >
+              Maximum allowed scale levels of the VNF, one entry per aspect.
+              This attribute shall be present if the VNF supports scaling.
+            type: array
+            items:
+              $ref: "../../../definitions/SOL002SOL003VNFLifecycleManagement_def.yaml#/definitions/ScaleInfo"

+      vnfConfigurableProperties:
+        description: >
+          This parameter provides values for the VNF configurable
+          properties attribute in the "VnfInstance", as defined in
+          clause 5.5.2.2. If an entry with the same key exists in the
+          VnfInstance data structure, the VNFM shall replace its
+          value with the value passed in the InstantiateVnfRequest
+          data structure.
+        $ref: "../../../definitions/SOL002SOL003_def.yaml#/definitions/KeyValuePairs"

+    patch:
+      description: >
+        This method allows to modify an "individual PM job" resource.
+        This method shall follow the provisions specified in the tables 6.4.3.3.4-1 and 6.4.3.3.4-2 for URI query parameters,
+        request and response data structures, and response codes.
+      parameters:

+    patch:
       description: >
-        This method allows to delete a threshold.
-        As the result of successfully executing this method, the 
-        "Individual threshold" resource shall not exist any longer.
+        This method allows to modify an "Individual threshold" resource.
+        This method shall follow the provisions specified in the tables 6.4.6.3.4-1 and 6.4.6.3.4-2 for URI query parameters,
+        request and response data structures, and response codes.

# b/MANO-API/src/SOL003/VNFPackageManagementNotification/definitions/VNFPackageManagementNotification_def.yaml
+      vnfmInfo:
+        description: >
+          Specifies VNFMs compatible with the VNF.
+          This information is copied from the VNFD. See table 10.5.2.2-1.
+        type: array
+        items:
+          $ref: "../../../definitions/SOL002SOL003_def.yaml#/definitions/String"

# b/MANO-API/src/SOL003/VNFPerformanceManagement/VNFPerformanceManagement.yaml
+    patch:
+      description: >
+        This method allows to modify an "individual PM job" resource.
+        This method shall follow the provisions specified in the tables
+        6.4.3.3.4-1 and 6.4.3.3.4-2 for URI query parameters, request
+        and response data structures, and response codes.
+      parameters:

# b/MANO-API/src/definitions/SOL002SOL003VNFFaultManagement_def.yaml
+      vnfcInstanceIds:
+        description: >
+          Identifiers of the affected VNFC instances.
+        type: array
+        items:
+          $ref: "SOL002SOL003_def.yaml#/definitions/IdentifierInVnf"

+      alarmAcknowledgedTime:
+        description: >
+          Time stamp indicating when the alarm was acknowledged.
+          It shall be present if the alarm has been acknowledged.
+        $ref: "SOL002SOL003_def.yaml#/definitions/DateTime"

# b/MANO-API/src/definitions/SOL002SOL003VNFLifecycleManagement_def.yaml
+      vnfcInfoModifications:
+        description: >
+          If present, this attribute signals modifications of certain
+          entries in the "vnfcInfo" attribute array in the
+          "instantiatedVnfInfo" attribute of "VnfInstance", as defined
+          in clause 5.5.2.12
+        type: array
+        items:
+          $ref: "#/definitions/VnfcInfoModifications"
+      vnfcInfoModificationsDeleteIds:
+        description: >
+          If present, this attribute signals the deletion of certain
+          entries in the "vnfcInfo" attribute array in the
+          "instantiatedVnfInfo" attribute of "VnfInstance", as defined
+          in clause 5.5.2.12
+        type: array
+        items:
+          $ref: "SOL002SOL003_def.yaml#/definitions/Identifier"

+      zoneId:
+        description: >
+          The identifier of the resource zone, as managed by the
+          resource management layer (typically, the VIM), where
+          the referenced VirtualCompute resource is placed.
+          Shall be provided if this information is available from the VIM.
+        $ref: "SOL002SOL003_def.yaml#/definitions/Identifier"


