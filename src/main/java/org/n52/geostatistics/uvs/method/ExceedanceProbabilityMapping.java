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
public class ExceedanceProbabilityMapping extends StaticVisualizationMethod {

    private static String n = "Exceedance Probability Mapping";

    private static String d = "Exceedance probability is used to depict the probability of exceeding a threshold in a certain pixel or area. This method is commonly used for environmental concentration maps where the exceedance of concentration thresholds are mapped.";

    /**
     * 
     * @param uT
     * @param dF
     * @param dT
     */
    public ExceedanceProbabilityMapping(UncertaintyType uT, DataFormat dF, DataType dT) {
        super(uT, dF, dT, n, d);

        images.put("images/exceedance_probability.png", "Image taken from Van de Kassteele & Velders 2006");
        referenceLinks.put("Van de Kassteele & Velders 2006",
                           "http://www.sciencedirect.com/science?_ob=MiamiImageURL&_cid=271798&_user=2160112&_pii=S1352231006000100&_check=y&_origin=&_coverDate=31-May-2006&view=c&wchp=dGLzVlt-zSkWA&md5=4d0318ee5b8ddf38fe36734577ecdab1/1-s2.0-S1352231006000100-main.pdf");
    }

}
