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
 * 
 */
package org.n52.geostatistics.uvs;

import java.util.ArrayList;

import org.n52.geostatistics.uvs.domain.DecisionSupportDomain;
import org.n52.geostatistics.uvs.domain.Domain;
import org.n52.geostatistics.uvs.domain.GISDomain;
import org.n52.geostatistics.uvs.domain.MapVisualisationDomain;
import org.n52.geostatistics.uvs.domain.OtherDomain;
import org.n52.geostatistics.uvs.domain.StatisticsDomain;
import org.n52.geostatistics.uvs.domain.UrbanPlanningDomain;
import org.n52.geostatistics.uvs.format.DataFormat;
import org.n52.geostatistics.uvs.format.RasterFormat;
import org.n52.geostatistics.uvs.format.VectorFormat;
import org.n52.geostatistics.uvs.method.VisualizationMethod;
import org.n52.geostatistics.uvs.type.AttributeUncertainty;
import org.n52.geostatistics.uvs.type.CategoricalType;
import org.n52.geostatistics.uvs.type.ContinuousType;
import org.n52.geostatistics.uvs.type.DataType;
import org.n52.geostatistics.uvs.type.PositionalUncertainty;
import org.n52.geostatistics.uvs.type.UncertaintyType;

/**
 * 
 * @author Daniel
 * 
 */
public class TestUvsModel {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String uncertaintyType = "Attribute";
        String dataFormat = "Raster";
        String dataType = "Continuous";
        // String domain = "Map Visualisation";
        // String domain = "Urban Planning";
        String domain = "Decision Support";
        // String domain = "";

        // initialize the object according to the String value:
        UncertaintyType uT = getUncertaintyType(uncertaintyType);
        DataFormat dF = getDataFormat(dataFormat);
        DataType dT = getDataType(dataType);
        Domain d = getDomain(domain);

        ArrayList<VisualizationMethod> visList = new UvsModel().getCorrecVisMethodsForUserInputs(uT, dF, dT, d);

        for (VisualizationMethod visListElement : visList) {
            System.out.println("name: " + visListElement.name);
            System.out.println("description: " + visListElement.description);
        }
    }

    private static Domain getDomain(String domain) {
        Domain d = null;
        if (domain.equals("Map Visualisation")) {
            d = new MapVisualisationDomain();
        } else if (domain.equals("Urban Planning")) {
            d = new UrbanPlanningDomain();
        } else if (domain.equals("Decision Support")) {
            d = new DecisionSupportDomain();
        } else if (domain.equals("GIS")) {
            d = new GISDomain();
        } else if (domain.equals("Statistics")) {
            d = new StatisticsDomain();
        } else if (domain.equals("Other")) {
            d = new OtherDomain();
        }
        return d;
    }

    private static DataType getDataType(String dataType) {
        DataType dT = null;
        if (dataType.equals("Continuous")) {
            dT = new ContinuousType();
        } else if (dataType.equals("Categorical")) {
            dT = new CategoricalType();
        }
        return dT;
    }

    private static DataFormat getDataFormat(String dataFormat) {
        DataFormat dF = null;
        if (dataFormat.equals("Raster")) {
            dF = new RasterFormat();
        } else if (dataFormat.equals("Vector")) {
            dF = new VectorFormat();
        }
        return dF;
    }

    private static UncertaintyType getUncertaintyType(String uncertaintyType) {
        UncertaintyType uT = null;
        if (uncertaintyType.equals("Attribute")) {
            uT = new AttributeUncertainty();
        } else if (uncertaintyType.equals("Positional")) {
            uT = new PositionalUncertainty();
        }
        return uT;
    }
}
