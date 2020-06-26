package com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type provides information regarding a VIM selection constraint. A set of
 * such constraints may be sent by the VNFM to the NFVO to influence the VIM
 * selection decisions made by the NFVO as part of the granting process.
 */
@ApiModel(description = "This type provides information regarding a VIM selection constraint. A set of such constraints may be sent by the VNFM to the NFVO to influence the VIM selection decisions made by the NFVO as part of the granting process. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-30T10:47:24.034+02:00")

public class VimConstraint {
	@JsonProperty("sameResourceGroup")
	private Boolean sameResourceGroup = null;

	@JsonProperty("resource")
	@Valid
	private List<ConstraintResourceRef> resource = new ArrayList<>();

	public VimConstraint sameResourceGroup(final Boolean sameResourceGroup) {
		this.sameResourceGroup = sameResourceGroup;
		return this;
	}

	/**
	 * If present and set to true, this signals that the constraint applies not only
	 * to the same VIM connection, but also to the same infrastructure resource
	 * group.
	 * 
	 * @return sameResourceGroup
	 **/
	@ApiModelProperty(value = "If present and set to true, this signals that the constraint applies not only to the same VIM connection, but also to the same infrastructure resource group. ")

	public Boolean isSameResourceGroup() {
		return sameResourceGroup;
	}

	public void setSameResourceGroup(final Boolean sameResourceGroup) {
		this.sameResourceGroup = sameResourceGroup;
	}

	public VimConstraint resource(final List<ConstraintResourceRef> resource) {
		this.resource = resource;
		return this;
	}

	public VimConstraint addResourceItem(final ConstraintResourceRef resourceItem) {
		this.resource.add(resourceItem);
		return this;
	}

	/**
	 * References to resources in the constraint rule. The NFVO shall ensure that
	 * all resources in this list are managed through the same VIM connection. If
	 * \"sameResourceGroup\" is set to true, the NFVO shall further ensure that all
	 * resources in this list are part of the same infrastructure resource group in
	 * that VIM connection.
	 * 
	 * @return resource
	 **/
	@ApiModelProperty(required = true, value = "References to resources in the constraint rule. The NFVO shall ensure that all resources in this list are managed through the same VIM connection. If \"sameResourceGroup\" is set to true, the NFVO shall further ensure that all resources in this list are part of the same infrastructure resource group in that VIM connection. ")
	@NotNull

	@Valid
	@Size(min = 2)
	public List<ConstraintResourceRef> getResource() {
		return resource;
	}

	public void setResource(final List<ConstraintResourceRef> resource) {
		this.resource = resource;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VimConstraint vimConstraint = (VimConstraint) o;
		return Objects.equals(this.sameResourceGroup, vimConstraint.sameResourceGroup) &&
				Objects.equals(this.resource, vimConstraint.resource);
	}

	@Override
	public int hashCode() {
		return Objects.hash(sameResourceGroup, resource);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VimConstraint {\n");

		sb.append("    sameResourceGroup: ").append(toIndentedString(sameResourceGroup)).append("\n");
		sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
