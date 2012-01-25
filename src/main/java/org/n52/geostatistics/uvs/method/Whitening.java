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
public class Whitening extends StaticVisualizationMethod {

    private static String n = "Whitening";

    private static String d =
            "A colour model is used where the colour hue is used to represent the data and the saturation-intensity (whiteness) is used to represent the associated uncertainty. The amount of white colour, proportional to the uncertinty is mixed in with the hue which represents the prediction.";

    /**
     * 
     * @param uT
     * @param dF
     * @param dT
     */
    public Whitening(UncertaintyType uT, DataFormat dF, DataType dT) {
        super(uT, dF, dT, n, d);
        
        images.put("images/Whitening.png", "Image taken from Hengl & Toomanian 2006");
        referenceLinks.put("Hengl 2006", "http://www.spatial-accuracy.org/system/files/Hengl2006accuracy.pdf");
    }
}
