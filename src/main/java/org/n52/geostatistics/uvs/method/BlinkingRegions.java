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
public class BlinkingRegions extends DynamicVisualizationMethod {

    private static String n = "Blinking Regions";

    private static String d =
            "Two layers of attribute data and uncertainty data are identified by their legends of varying classes of data and uncertainty respectively. These two images are overlaid on top of another and alternately displayed.";

    /**
     * 
     * @param uT
     * @param dF
     * @param dT
     */
    public BlinkingRegions(UncertaintyType uT, DataFormat dF, DataType dT) {
        super(uT, dF, dT, n, d);

        referenceLinks.put("Kardos et al. 2006", "http://www.spatial-accuracy.org/system/files/Kardos2006accuracy.pdf");
    }

}
