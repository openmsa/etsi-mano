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
package tosca.policies.nfv;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.parser.tosca.PolicyDefinition;
import com.ubiqube.parser.tosca.api.ToscaInernalBase;

public class AffinityRule extends ToscaInernalBase {
	/**
	 * scope of the rule is an NFVI_node, an NFVI_PoP, etc.
	 */
	@JsonProperty("scope")
	private String scope;

	private List<String> targets;
	private Map<String, PolicyDefinition> triggers;

	public Map<String, PolicyDefinition> getTriggers() {
		return this.triggers;
	}

	public void setTriggers(final Map<String, PolicyDefinition> triggers) {
		this.triggers = triggers;
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(final String scope) {
		this.scope = scope;
	}

	public List<String> getTargets() {
		return this.targets;
	}

	public void setTargets(final List<String> targets) {
		this.targets = targets;
	}

}
