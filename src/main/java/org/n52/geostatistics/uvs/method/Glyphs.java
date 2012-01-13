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

public class Glyphs extends StaticVisualizationMethod {

	private static String name = "Glyphs";
	private static String description = "The uncertainty and the data are represented in a bivariate depiction through pictorial symbols, known as glyphs. <a href = 'ftp://128.114.49.249/pub/reinas/papers/geo.pdf'> (Pang 2001) </a> <div id='scriptiny'><ul> <li onclick='TINY.box.show({iframe:'images/symbols.png/',boxid:'frameless',width:550,height:450,fixed:false,maskid:'bluemask',maskopacity:40,closejs:function (){closeJS()}})'><img src='images/symbols.png' width = '40' height = '30'/></li></ul> </div> ";

	/**
	 * 
	 * @param uT
	 * @param dF
	 * @param dT
	 */
	public Glyphs(UncertaintyType uT, DataFormat dF, DataType dT) {
		super(uT, dF, dT, name, description);
	}
}
