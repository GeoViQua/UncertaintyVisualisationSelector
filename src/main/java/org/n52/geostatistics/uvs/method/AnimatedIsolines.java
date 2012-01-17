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
 * 
 * @author v_sena01
 * 
 */
public class AnimatedIsolines extends DynamicVisualizationMethod {

    private static String name = "Animated Isolines";

    private static String description =
            "The concept of contouring or isolines can also be used in an animated environment. <a href = 'http://www.geovista.psu.edu/sites/icavis/icavis/febm.html'>(Fauerbach et al. 1996) </a>. <div id='scriptiny'><ul> <li onclick='TINY.box.show({iframe:'http://www.geovista.psu.edu/sites/icavis/icavis/febm/sdhbivar.html',boxid:'frameless',width:550,height:450,fixed:false,maskid:'bluemask',maskopacity:40,closejs:function (){closeJS()}})'> <em>Click image for a demo</em> <img src='images/Animated_Isoline.png' width = '50' height = '40'/></li></ul> </div> ";

    /**
     * 
     * @param uT
     * @param dF
     * @param dT
     */
    public AnimatedIsolines(UncertaintyType uT, DataFormat dF, DataType dT) {
        super(uT, dF, dT, name, description);
    }
}
