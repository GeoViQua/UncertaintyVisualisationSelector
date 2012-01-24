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
public class HierachicalSpatialDataStructures extends StaticVisualizationMethod {

    private static String n = "Hierachical Spatial Data Structures";

    private static String d = "Hierarchical data structures (e.g. Quadtrees) are used as a transparent tessellated layer on top of the data  to represent the amount of associated uncertainty. A finer tessellation can indicate less uncertain areas whereas a coarser tessellation can indicate more uncertain regions.";

    /**
     * 
     * @param uT
     * @param dF
     * @param dT
     */
    public HierachicalSpatialDataStructures(UncertaintyType uT, DataFormat dF, DataType dT) {
        super(uT, dF, dT, n, d);
        
        images.put("images/Hierarchical_spatial_data_structures.png", "Image taken from Kardos et al. 2004");
        referenceLinks.put("Kardos et al. 2003",
                           "http://www.geocomputation.org/2003/Papers/Kardos_Paper.pdf");
    }

}
