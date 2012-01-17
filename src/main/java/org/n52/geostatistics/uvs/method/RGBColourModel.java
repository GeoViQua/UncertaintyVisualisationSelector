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
public class RGBColourModel extends StaticVisualizationMethod {

    private static String name = "Rgb Colour Model";

    private static String description =
            "In a scenario where the uncertainty has to be generated for three variables, the colours red, green and blue are used to represent these variables whereas the amount of uncertainty is represented through the intensity of these colours, i.e. higher intensity indicates lower uncertainty. In instances where more than three variables are involved, the technique of colour mixing is incorporated. <a href = 'http://www.itc.nl/library/Papers/HENGL-pixel.pdf'>(Hengl et al. 2006)</a> <div id='scriptiny'><ul> <li onclick='TINY.box.show({iframe:'images/RGB_colour_model.png/',boxid:'frameless',width:550,height:450,fixed:false,maskid:'bluemask',maskopacity:40,closejs:function (){closeJS()}})'><img src='images/RGB_colour_model.png' width = '50' height = '40'/> <em>Images taken from MacEachren et al. 2005 and Hengl & Toomanian 2006</em></li> </ul></div> ";

    /**
     * 
     * @param uT
     * @param dF
     * @param dT
     */
    public RGBColourModel(UncertaintyType uT, DataFormat dF, DataType dT) {
        super(uT, dF, dT, name, description);
    }

}
