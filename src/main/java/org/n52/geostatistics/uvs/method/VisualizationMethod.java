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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.n52.geostatistics.uvs.format.DataFormat;
import org.n52.geostatistics.uvs.type.DataType;
import org.n52.geostatistics.uvs.type.UncertaintyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hansi Senaratne (hansi.senaratne@wwu.de)
 * @version 1.0
 * @created 30-Aug-2011 09:00:42
 */
public abstract class VisualizationMethod {

    public String descriptionText = "NA";

    public Map<String, String> referenceLinks = new HashMap<String, String>();

    public Map<String, String> images = new HashMap<String, String>();
    
    public List<VideoDemo> videos = new ArrayList<VideoDemo>();

    Logger logger = LoggerFactory.getLogger(VisualizationMethod.class);

    public DataFormat m_DataFormat;

    public DataType m_DataType;

    public UncertaintyType m_UncertaintyType;

    public String name;

    /**
     * 
     * @param uT
     * @param dF
     * @param dT
     * @param n
     * @param des
     */
    public VisualizationMethod(UncertaintyType uT, DataFormat dF, DataType dT, String n, String des) {
        m_UncertaintyType = uT;
        m_DataType = dT;
        m_DataFormat = dF;
        name = n;
        descriptionText = des;

        logger.debug("NEW {}", this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.name);
        sb.append(" [");
        sb.append(this.m_UncertaintyType);
        sb.append(", ");
        sb.append(this.m_DataFormat);
        sb.append(", ");
        sb.append(this.m_DataType);
        sb.append("]");

        return sb.toString();
    }

}// end VisualizationMethod