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
public class StatisticalDimensionInAGIS extends InteractiveVisualizationMethod {

    private static String n = "Statistical Dimension in a GIS";

    private static String d =
            "The uncertainty of the data is represented by the cumulative probability functions for each pixel or vector object. Depending on the chosen quantile or threshold value, the map colour scale shows the associated value or probability.  ";

    /**
     * 
     * @param uT
     * @param dF
     * @param dT
     */
    public StatisticalDimensionInAGIS(UncertaintyType uT, DataFormat dF, DataType dT) {
        super(uT, dF, dT, n, d);

        referenceLinks.put("Pebesma et al. 2007", "http://www.tandfonline.com/doi/pdf/10.1080/13658810601064009");
        videos.add(new VideoDemo("video_aguila.html", "images/Aguila.png", 620, 380));
    }
}
