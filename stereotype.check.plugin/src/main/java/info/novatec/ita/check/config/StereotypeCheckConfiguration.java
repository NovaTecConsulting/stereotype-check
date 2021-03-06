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

import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * This class holds all configuration informations from the stereotype check
 * configuration file.
 * 
 * @author Volker Koch (volker.koch@novatec-gmbh.de)
 *
 */
public class StereotypeCheckConfiguration {

	/**
	 * The package names checked by the stereotype check. Classes outside this
	 * packages and its subpackages are ignored
	 */
	private final Set<String> applicationPackageNames;

	/**
	 * The set of regular expressions of classes that are excluded from the
	 * check.
	 */
	private final Set<Pattern> excludedClasses;

	/**
	 * The map of all allowed dependencies. Maps from a stereotype-name to list
	 * of allowed stereotypes. All strings in the map must match a key of the
	 * {@link #stereotypeConfig}.
	 */
	private final Map<StereotypeIdentifier, DependencyConfiguration> dependencies;

	/**
	 * The stereotypes. The key is the name of the stereotype.
	 */
	private final Map<StereotypeIdentifier, StereotypeConfiguration> stereotypeConfig;

	/**
	 * Constructor
	 * 
	 * @param applicationPackageNames
	 *            he package names checked by the stereotype check.
	 * @param dependencies
	 *            The map of all dependencies.
	 * @param stereotypeConfig
	 *            The stereotypes.
	 */
	StereotypeCheckConfiguration(Set<String> applicationPackageNames, Set<Pattern> excludedClasses,
			Map<StereotypeIdentifier, DependencyConfiguration> dependencies,
			Map<StereotypeIdentifier, StereotypeConfiguration> stereotypeConfig) {
		this.applicationPackageNames = applicationPackageNames;
		this.excludedClasses = excludedClasses;
		this.dependencies = dependencies;
		this.stereotypeConfig = stereotypeConfig;
	}

	/**
	 * Checks if the given full qualified classname starts with one of the
	 * {@link #applicationPackageNames}.
	 * 
	 * @param classNameWithPackage
	 *            the full qualified classname
	 * @return true if the classname starts with one of the
	 *         {@link #applicationPackageNames}.
	 */
	public boolean isInApplicationPackage(String classNameWithPackage) {
		if (classNameWithPackage == null) {
			return false;
		}
		if (applicationPackageNames.size() == 0) {
			return true;
		}
		for (String packageName : applicationPackageNames) {
			if (classNameWithPackage.startsWith(packageName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Is the classname an annotation or interface or baseclass referenced in a
	 * stereotype.
	 * 
	 * @param classNameWithPackage
	 *            the full qualified classname
	 * @return true if the classname an annotation or interface or baseclass
	 *         referenced in a stereotype.
	 */
	public boolean isPartOfAnyStereotype(String classNameWithPackage) {
		for (StereotypeConfiguration config : stereotypeConfig.values()) {
			if (config.isPartOfStereotype(classNameWithPackage)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return The map of all allowed dependencies. Maps from a stereotype-name
	 *         to list of allowed stereotypes. All strings in the map must match
	 *         a key of the {@link #stereotypeConfig}.
	 */
	public Map<StereotypeIdentifier, DependencyConfiguration> getDependencies() {
		return dependencies;
	}

	/**
	 * @return The stereotypes. The key is the name of the stereotype.
	 */
	public Map<StereotypeIdentifier, StereotypeConfiguration> getStereotypeConfig() {
		return stereotypeConfig;
	}

	/**
	 * @return the excludedClasses
	 */
	public Set<Pattern> getExcludedClasses() {
		return excludedClasses;
	}

}
