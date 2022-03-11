/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.nfvo.v261.services;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.CancelModeType;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.ExtVirtualLinkInfo;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.LcmOperationType;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.ProblemDetails;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.ChangeExtVnfConnectivityRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.ChangeVnfFlavourRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.HealVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.OperateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.ScaleVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.ScaleVnfToLevelRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.TerminateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfInfoModificationRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfInfoModifications;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfLcmOpOccLinks;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfLcmOpOccResourceChanges;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
public class VnfLcmOpOcc261Deserializer extends StdDeserializer<VnfLcmOpOcc> {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	public VnfLcmOpOcc261Deserializer() {
		super(VnfLcmOpOcc.class);
	}

	@Override
	public VnfLcmOpOcc deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
		final VnfLcmOpOcc ret = new VnfLcmOpOcc();
		while (JsonToken.END_OBJECT != p.nextToken()) {
			switch (p.currentName()) {
			case "id":
				ret.setId(p.nextTextValue());
				break;
			case "operationState":
				ret.setOperationState(LcmOperationStateType.fromValue(p.nextTextValue()));
				break;
			case "stateEnteredTime":
				p.nextToken();
				ret.setStateEnteredTime(p.getCodec().readValue(p, OffsetDateTime.class));
				break;
			case "startTime":
				ret.setStartTime(getNextObject(p, OffsetDateTime.class));
				break;
			case "vnfInstanceId":
				ret.setVnfInstanceId(p.nextTextValue());
				break;
			case "grantId":
				ret.setGrantId(p.nextTextValue());
				break;
			case "operation":
				ret.setOperation(LcmOperationType.fromValue(p.nextTextValue()));
				break;
			case "isAutomaticInvocation":
				ret.setIsAutomaticInvocation(p.nextBooleanValue());
				break;
			case "operationParams":
				ret.setOperationParams(mapOperation(ret.getOperation(), p));
				break;
			case "isCancelPending":
				ret.setIsCancelPending(p.nextBooleanValue());
				break;
			case "cancelMode":
				ret.setCancelMode(CancelModeType.fromValue(p.nextTextValue()));
				break;
			case "error":
				ret.setError(getNextObject(p, ProblemDetails.class));
				break;
			case "resourceChanges":
				ret.setResourceChanges(getNextObject(p, VnfLcmOpOccResourceChanges.class));
				break;
			case "changedInfo":
				ret.setChangedInfo(getNextObject(p, VnfInfoModifications.class));
				break;
			case "changedExtConnectivity":
				ret.setChangedExtConnectivity(getNextObject(p, new TypeReference<List<ExtVirtualLinkInfo>>() {
					// Nothing.
				}));
				break;
			case "_links":
				ret.setLinks(getNextObject(p, VnfLcmOpOccLinks.class));
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + p.currentName());
			}
		}
		return null;
	}

	private static Object mapOperation(final LcmOperationType op, final JsonParser p) throws IOException {
		p.nextToken();
		return switch (op) {
		case INSTANTIATE -> map(p, InstantiateVnfRequest.class);
		case SCALE -> map(p, ScaleVnfRequest.class);
		case SCALE_TO_LEVEL -> map(p, ScaleVnfToLevelRequest.class);
		case CHANGE_FLAVOUR -> map(p, ChangeVnfFlavourRequest.class);
		case TERMINATE -> map(p, TerminateVnfRequest.class);
		case HEAL -> map(p, HealVnfRequest.class);
		case OPERATE -> map(p, OperateVnfRequest.class);
		case CHANGE_EXT_CONN -> map(p, ChangeExtVnfConnectivityRequest.class);
		case MODIFY_INFO -> map(p, VnfInfoModificationRequest.class);
		default -> throw new IllegalArgumentException("Unexpected value: " + op);
		};
	}

	private static <U> U getNextObject(final JsonParser p, final Class<U> clazz) throws IOException {
		p.nextToken();
		return p.getCodec().readValue(p, clazz);
	}

	private static <U> U map(final JsonParser p, final Class<U> clazz) {
		try {
			return p.getCodec().readValue(p, clazz);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private static List<ExtVirtualLinkInfo> getNextObject(final JsonParser p, final TypeReference<List<ExtVirtualLinkInfo>> typeReference) throws IOException {
		p.nextToken();
		return p.getCodec().readValue(p, typeReference);
	}

}
