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
public class SymbolFocus extends StaticVisualizationMethod {

    private static String name = "Symol Focus";

    private static String description =
            "The focus metaphor is based on the human perception of focussed and non-focused (blurred) views. Uncertain data is depicted out of focus, making it less precisely visible, e.g. foggy. More certain data is depicted in focus, e.g. crisp boundaries. <a href = 'http://www.nws.noaa.gov/ost/nfuse/MacEachren_et_al_FINAL.pdf'>(MacEachren et al. 2005) </a> <div id='scriptiny'><ul> <li onclick='TINY.box.show({iframe:'images/focus_metaphors.png/',boxid:'frameless',width:550,height:450,fixed:false,maskid:'bluemask',maskopacity:40,closejs:function (){closeJS()}})'><img src='images/focus_metaphors.png' width = '50' height = '40'/><em>Image taken from MacEachren et al. 2005</em></li> </ul></div> ";

    /**
     * 
     * @param uT
     * @param dF
     * @param dT
     */
    public SymbolFocus(UncertaintyType uT, DataFormat dF, DataType dT) {
        super(uT, dF, dT, name, description);
    }
}
