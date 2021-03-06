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

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.Test;

import info.novatec.ita.check.AbstractStereotypeCheckTest;
import info.novatec.ita.check.config.OverrideMode;
import info.novatec.ita.check.config.StereotypeCheckConfiguration;
import info.novatec.ita.check.config.StereotypeCheckReader;
import info.novatec.ita.check.config.StereotypeConfiguration;
import info.novatec.ita.check.config.StereotypeIdentifier;

/**
 * Tests to check the override behavior of interfaces.
 * 
 * @author Volker Koch (NovaTec Consulting GmbH)
 *
 */
public class InterfaceOverrideTest extends AbstractStereotypeCheckTest {

	/**
	 * Checks that a interface is overridden by {@link OverrideMode#replace}.
	 * 
	 * @throws Exception
	 *             in case of an unexpected test execution.
	 */
	@Test
	public void interfaceIsOverriddenByReplace() throws Exception {
		StereotypeCheckConfiguration config = StereotypeCheckReader
				.read(new File("src/test/resources/stereotype-allowoverride.xml"));
		StereotypeConfiguration businessservice = config.getStereotypeConfig()
				.get(StereotypeIdentifier.of("businessservice"));
		assertThat(businessservice).isNotNull();
		assertThat(businessservice.getInterfaceNames())
				.containsExactly("info.novatec.ita.check.testclasses.core.fwk.api.bl.bs.ApiBusinessService");
	}

	/**
	 * Checks that a interface is overridden by {@link OverrideMode#extend}.
	 * 
	 * @throws Exception
	 *             in case of an unexpected test execution.
	 */
	@Test
	public void interfaceIsOverriddenByExtend() throws Exception {
		StereotypeCheckConfiguration config = StereotypeCheckReader
				.read(new File("src/test/resources/stereotype-allowoverride-extend.xml"));
		StereotypeConfiguration businessservice = config.getStereotypeConfig()
				.get(StereotypeIdentifier.of("businessservice"));
		assertThat(businessservice).isNotNull();
		assertThat(businessservice.getInterfaceNames()).containsExactly(
				"info.novatec.ita.check.testclasses.core.fwk.api.bl.bs.ApiBusinessService",
				"info.novatec.ita.check.testclasses.core.fwk.api.bl.bs.BusinessService");
	}

	/**
	 * Checks that a interface name is overridden by {@link OverrideMode#replace}
	 * .
	 * 
	 * @throws Exception
	 *             in case of an unexpected test execution.
	 */
	@Test
	public void interfaceNameIsOverriddenByReplace() throws Exception {
		StereotypeCheckConfiguration config = StereotypeCheckReader
				.read(new File("src/test/resources/stereotype-allowoverride.xml"));
		StereotypeConfiguration businessservice2 = config.getStereotypeConfig()
				.get(StereotypeIdentifier.of("businessservice2"));
		assertThat(businessservice2).isNotNull();
		assertThat(businessservice2.getInterfaceNames())
				.containsExactly("info.novatec.ita.check.testclasses.core.fwk.api.bl.bs.ApiBusinessService2");
	}

	/**
	 * Checks that a interface name is overridden by {@link OverrideMode#extend}.
	 * 
	 * @throws Exception
	 *             in case of an unexpected test execution.
	 */
	@Test
	public void interfaceNameIsOverriddenByExtend() throws Exception {
		StereotypeCheckConfiguration config = StereotypeCheckReader
				.read(new File("src/test/resources/stereotype-allowoverride-extend.xml"));
		StereotypeConfiguration businessservice2 = config.getStereotypeConfig()
				.get(StereotypeIdentifier.of("businessservice2"));
		assertThat(businessservice2).isNotNull();
		assertThat(businessservice2.getInterfaceNames()).containsExactlyInAnyOrder(
				"info.novatec.ita.check.testclasses.core.fwk.api.bl.bs.ApiBusinessService2",
				"info.novatec.ita.check.testclasses.core.fwk.api.bl.bs.BusinessService2");
	}

	/**
	 * Checks that a interface that is overrideable is not overridden.
	 * 
	 * @throws Exception
	 *             in case of an unexpected test execution.
	 */
	@Test
	public void interfaceIsNotOverridden() throws Exception {
		StereotypeCheckConfiguration config = StereotypeCheckReader
				.read(new File("src/test/resources/stereotype-allowoverride.xml"));
		StereotypeConfiguration businessserviceNotOverridden = config.getStereotypeConfig()
				.get(StereotypeIdentifier.of("businessserviceNotOverridden"));
		assertThat(businessserviceNotOverridden).isNotNull();
		assertThat(businessserviceNotOverridden.getInterfaceNames())
				.containsExactly("info.novatec.ita.check.testclasses.core.fwk.api.bl.bs.BusinessService3");
	}
}