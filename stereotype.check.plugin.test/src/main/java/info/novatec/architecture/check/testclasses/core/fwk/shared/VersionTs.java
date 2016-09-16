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
package info.novatec.architecture.check.testclasses.core.fwk.shared;

import info.novatec.architecture.check.testclasses.core.fwk.api.ts.TechnicalService;

import javax.ejb.Remote;

/**
 * Valid interface for its stereotype.
 * 
 * @author Volker Koch (NovaTec Consulting GmbH)
 *
 */
@Remote
public interface VersionTs extends TechnicalService {

}
