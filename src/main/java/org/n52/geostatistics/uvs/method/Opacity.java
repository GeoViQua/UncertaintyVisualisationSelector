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
public class Opacity extends StaticVisualizationMethod {

    private static String n = "Opacity";

    private static String d ="Less uncertain data is seen less opaque and more uncertain data is more opaque. This concept can also be used in reverse where uncertain data is shown more transparently.";

    /**
     * 
     * @param uT
     * @param dF
     * @param dT
     */
    public Opacity(UncertaintyType uT, DataFormat dF, DataType dT) {
        super(uT, dF, dT, n, d);

        images.put("images/Opacity.png", "Image taken from Grigoryan & Rheingans 2004");
        referenceLinks.put("MacEachren et al. 2005", "http://www.nws.noaa.gov/ost/nfuse/MacEachren_et_al_FINAL.pdf");
    }

}
