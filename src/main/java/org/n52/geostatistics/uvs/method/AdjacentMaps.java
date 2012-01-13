/**
 * Copyright 2011 52°North Initiative for Geospatial Open Source Software GmbH
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
 * @author v_sena01
 * 
 */

public class AdjacentMaps extends StaticVisualizationMethod {

	private static String name = "Adjacent Maps";

	private static String description = "This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended. <a href='http://www.envplan.com/abstract.cgi?id=a301547'>(MacEachren et al. 1998)</a> <div id='scriptiny'><ul> <li onclick='TINY.box.show({iframe:'images/Adjacent Maps.gif/',boxid:'frameless',width:550,height:450,fixed:false,maskid:'bluemask',maskopacity:40,closejs:function (){closeJS()}})'><img src='images/Adjacent Maps.gif' width = '40' height = '30'/></li> </ul></div> ";

	/**
	 * class constructor creating objects of class AdjacentMaps
	 * 
	 * @param uT
	 * @param dF
	 * @param dT
	 */
	public AdjacentMaps(UncertaintyType uT, DataFormat dF, DataType dT) {
		super(uT, dF, dT, name, description);
	}

}
