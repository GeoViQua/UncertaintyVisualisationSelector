/**
 *Copyright 2011 52°North Initiative for Geospatial Open Source Software GmbH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package org.n52.geostatistics.uvs.method;

import org.n52.geostatistics.uvs.format.DataFormat;
import org.n52.geostatistics.uvs.type.DataType;
import org.n52.geostatistics.uvs.type.UncertaintyType;

/**
 * 
 * @author v_sena01
 *
 */

public class Animation extends DynamicVisualizationMethod {

	private static String name = "Animation";
	private static String description = "Different realisations of the uncertain attribute are animated together in 2D to emphasise the uncertainty. <a href ='http://www.sciencedirect.com/science/article/pii/S0098300497000058'>(Ehlschlaeger et al. 1997) </a>.";
	
	/**
	 * 
	 * @param uT
	 * @param dF
	 * @param dT
	 */
	public Animation(UncertaintyType uT, DataFormat dF, DataType dT) {
		super(uT, dF, dT, name, description);
	}
}
