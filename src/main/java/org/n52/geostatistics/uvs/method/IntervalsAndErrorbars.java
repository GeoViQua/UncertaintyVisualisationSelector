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

public class IntervalsAndErrorbars extends InteractiveVisualizationMethod {
	
	private static String name = "Intervals and Errorbars";
	private static String description = "The length of the represents the low and high uncertainties, where wider bars represent higher uncertainties and narrower bars represent lower uncertainties. Similarly, wider intervals indicate higher uncertainties and narrower intervals indicate lower uncerrtainties <a href='http://www.sci.utah.edu/~kpotter/library/papers/olston:2002:VDBU/index.html'>(Olston & Mackinlay 2002)</a> <div id='scriptiny'><ul> <li onclick='TINY.box.show({iframe:'http://www.youtube.com/watch?v=Yr5gxMxCDGo',boxid:'frameless',width:550,height:450,fixed:false,maskid:'bluemask',maskopacity:40,closejs:function (){closeJS()}})'><em>Click image for a demo</em><img src='images/errorbars with PDF.png' width = '40' height = '30'/> <em> For more on the webclient click <a href = 'http://www.uncertweb.org/uploads/deliverables/06061f73486e9c6d33e601a74295c391b28ad6a4.pdf'>here</em></a></li></ul></div>"; 
	
	
	/**
	 * 
	 * @param uT
	 * @param dF
	 * @param dT
	 */
	public IntervalsAndErrorbars(UncertaintyType uT, DataFormat dF, DataType dT) {
		super(uT, dF, dT, name, description);
	}

}
