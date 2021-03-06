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

import java.util.HashMap;

import org.n52.geostatistics.uvs.format.DataFormat;
import org.n52.geostatistics.uvs.type.DataType;
import org.n52.geostatistics.uvs.type.UncertaintyType;

/**
 * @author Daniel Nüst (d.nuest@52north.org)
 * 
 */
public class Contouring extends StaticVisualizationMethod {

    private static String d = "This method is used to visualise uncertainty through contour lines. In a multivariate mapping environment, contour lines of different colours can be used to distinguish between different variables and their uncertainties with the intensity of colour, as well as the line thickness. Similarly positional uncertainty is depicted through the gap widths in the dots of these contour lines where higher uncertainty leads to wider gaps.";

    private static String n = "Contouring";

    /**
     * 
     * @param uT
     * @param dF
     * @param dT
     */
    public Contouring(UncertaintyType uT, DataFormat dF, DataType dT) {
        super(uT, dF, dT, n, d);

        referenceLinks = new HashMap<String, String>();
        referenceLinks.put("Osorio & Brodlie 2008", "http://eprints.whiterose.ac.uk/5398/1/kwbEGUK08paper.pdf");
        images.put("images/Contouring.png", "");
    }

}
