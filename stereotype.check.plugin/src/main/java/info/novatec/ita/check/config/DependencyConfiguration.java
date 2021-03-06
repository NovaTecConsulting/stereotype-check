/*******************************************************************************
 * Copyright 2016 NovaTec Consulting GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package info.novatec.ita.check.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The configuration of a stereotype that has outgoing dependencies to other
 * stereotypes. Each outgoing dependency stereotype can be marked as allowed or
 * disallowed. If a stereotype is not in the list of the outgoing stereotypes it
 * is implicitly disallowed.
 * 
 * @author Volker Koch (volker.koch@novatec-gmbh.de)
 *
 */
public class DependencyConfiguration {

	/** Stereotype-Id of the stereotype that has outgoing dependencies. */
	private final StereotypeIdentifier from;
	/**
	 * Map that hold all outgoing stereotypes that are allowed (value=true) and some
	 * stereotype that are explicitly disallowed by overriding. All other
	 * stereotypes are implicitly disallowed.
	 */
	private Map<StereotypeIdentifier, Boolean> outgoingDependencies = new HashMap<>();

	/**
	 * Creates a new {@link DependencyConfiguration}
	 * 
	 * @param from
	 *            Stereotype-Id of the source of the dependency
	 */
	public DependencyConfiguration(StereotypeIdentifier from) {
		this.from = from;
	}

	/**
	 * @return the {@link #from}
	 */
	public StereotypeIdentifier getFrom() {
		return from;
	}

	/**
	 * Add dependency {@link #from} to the given Stereotype.
	 * 
	 * @param to
	 *            the Stereotype
	 * @param isAllowed
	 *            if the dependency is not allowed provide the string "false". In all other cases it is true.
	 */
	public void addTo(StereotypeIdentifier to, String isAllowed) {
		boolean allowed = !"false".equalsIgnoreCase(isAllowed);
		outgoingDependencies.put(to, allowed);
	}

	/**
	 * Is a dependency from Stereotype {@link #from} to the given Stereotype
	 * allowed.
	 * 
	 * @param to
	 *            the Stereotype to check.
	 * @return true if explicitly allowed otherwise false.
	 */
	public boolean isAllowed(StereotypeIdentifier to) {
		Boolean allowed = outgoingDependencies.get(to);
		return allowed != null ? allowed : false;
	}

	/**
	 * @return all dependencies that are allowed
	 */
	public Set<StereotypeIdentifier> getAllowedToDependencies() {
		Set<StereotypeIdentifier> allowed = new HashSet<>();
		for (StereotypeIdentifier to : outgoingDependencies.keySet()) {
			if (isAllowed(to)) {
				allowed.add(to);
			}
		}
		return allowed;
	}

	/**
	 * Merges this {@link DependencyConfiguration} with the given.
	 * <p>
	 * If the given {@link DependencyConfiguration} has a value for a
	 * {@link StereotypeIdentifier} it overrides the value in this
	 * {@link DependencyConfiguration}.
	 * <p>
	 * If the given {@link DependencyConfiguration} has a to-stereotype that is
	 * not part of this {@link DependencyConfiguration} an error is thrown.
	 * 
	 * @param dependencyConfigurationAdditional
	 *            the {@link DependencyConfiguration} to merge.
	 */
	public void merge(DependencyConfiguration dependencyConfigurationAdditional) {
		if (!from.equals(dependencyConfigurationAdditional.getFrom())) {
			throw new IllegalArgumentException("The merge of the central Dependency-Configuration " + from
					+ " with the additional Dependency-Configuration " + dependencyConfigurationAdditional.getFrom()
					+ " is not possible");
		}
		for (StereotypeIdentifier stereotypeIdOutgoingDependencyOfAdditionalConfig : dependencyConfigurationAdditional.outgoingDependencies.keySet()) {
			if (!outgoingDependencies.containsKey(stereotypeIdOutgoingDependencyOfAdditionalConfig)) {
				throw new IllegalArgumentException(
						"The additional configuration for from dependency " + from + " contains a dependency to "
								+ stereotypeIdOutgoingDependencyOfAdditionalConfig + " that is not part of the central configuration.");
			}
		}
		outgoingDependencies.putAll(dependencyConfigurationAdditional.outgoingDependencies);
	}

	@Override
	public String toString() {
		return "DependencyConfiguration [from=" + from + ", toAllowed=" + outgoingDependencies + "]";
	}

}
