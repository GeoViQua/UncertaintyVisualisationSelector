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

public class StatisticalDimensionInAGIS extends InteractiveVisualizationMethod {

	private static String name = "Statistical Dimention in a GIS";
	private static String description = "The uncertainty of the data is represented by the cumulative probability functions for each pixel or vector object. Depending on the chosen quantile or threshold value, the map colour scale shows the associated value or probability. <a href ='http://www.tandfonline.com/doi/pdf/10.1080/13658810601064009'>(Pebesma et al. 2007) </a> <div id='scriptiny'><ul> <li onclick='TINY.box.show({iframe:'http://www.youtube.com/watch?v=3carYnbow54',boxid:'frameless',width:550,height:450,fixed:false,maskid:'bluemask',maskopacity:40,closejs:function (){closeJS()}})'><em>Click image for a demo</em><img src='images/Aguila.png' width = '40' height = '30'/></li> </ul></div> ";

	/**
	 * 
	 * @param uT
	 * @param dF
	 * @param dT
	 */
	public StatisticalDimensionInAGIS(UncertaintyType uT, DataFormat dF,
			DataType dT) {
		super(uT, dF, dT, name, description);
	}
}
