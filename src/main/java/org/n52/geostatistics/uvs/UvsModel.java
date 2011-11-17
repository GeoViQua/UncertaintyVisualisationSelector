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
import java.util.Arrays;

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
import org.n52.geostatistics.uvs.method.Contouring;
import org.n52.geostatistics.uvs.method.DynamicVisualizationMethod;
import org.n52.geostatistics.uvs.method.InteractiveVisualizationMethod;
import org.n52.geostatistics.uvs.method.StaticVisualizationMethod;
import org.n52.geostatistics.uvs.method.VisualizationMethod;
import org.n52.geostatistics.uvs.type.AttributeUncertainty;
import org.n52.geostatistics.uvs.type.CategoricalType;
import org.n52.geostatistics.uvs.type.ContinuousType;
import org.n52.geostatistics.uvs.type.DataType;
import org.n52.geostatistics.uvs.type.PositionalUncertainty;
import org.n52.geostatistics.uvs.type.UncertaintyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Daniel
 * 
 */
public class UvsModel {

	Logger logger = LoggerFactory.getLogger(UvsModel.class);

	/**
	 * 
	 * @param uncertaintyType
	 * @param dataFormat
	 * @param dataType
	 * @param domain
	 * @return
	 */
	public ArrayList<VisualizationMethod> getCorrecVisMethodsForUserInputs(
			String uncertaintyType, String dataFormat, String dataType,
			String domain) {

		UncertaintyType uT = null;
		if (uncertaintyType.equals("Attribute")) {
			uT = new AttributeUncertainty();
		} else if (uncertaintyType.equals("Positional")) {
			uT = new PositionalUncertainty();
		}
		DataFormat dF = null;
		if (dataFormat.equals("Raster")) {
			dF = new RasterFormat();
		} else if (dataFormat.equals("Vector")) {
			dF = new VectorFormat();
		}
		DataType dT = null;
		if (dataType.equals("Continuous")) {
			dT = new ContinuousType();
		} else if (dataType.equals("Categorical")) {
			dT = new CategoricalType();
		}
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

		ArrayList<VisualizationMethod> visList = getCorrecVisMethodsForUserInputs(
				uT, dF, dT, d);

		return visList;
	}

	// methods:
	// 1. visibility
	// 2. type of return value
	// 3. name of method
	// 4. (parameters) -> type + name
	public ArrayList<VisualizationMethod> getCorrecVisMethodsForUserInputs(
			UncertaintyType uT, DataFormat dF, DataType dT, Domain d) {
		logger.debug(String
				.format("New methods requested for uncertainty type = %s, data format = %s, data type = %s, domain = %s",
						uT, dF, dT, d));

		ArrayList<VisualizationMethod> visMethodsList = new ArrayList<VisualizationMethod>();

		if (d != null) {
			// First branch
			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType
					&& d instanceof MapVisualisationDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Statistical Dimension in a GIS",
						"The uncertainty of the data is represented by the cumulative probability functions for each pixel or vector object. Depending on the chosen quantile or threshold value, the map colour scale shows the associated value or probability (Pebesma et al. 2007) ");
				visMethodsList.add(statisticalDimensionInaGISMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType
					&& d instanceof UrbanPlanningDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Statistical Dimension in a GIS",
						"The uncertainty of the data is represented by the cumulative probability functions for each pixel or vector object. Depending on the chosen quantile or threshold value, the map colour scale shows the associated value or probability (Pebesma et al. 2007) ");
				visMethodsList.add(statisticalDimensionInaGISMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType
					&& d instanceof DecisionSupportDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Statistical Dimension in a GIS",
						"The uncertainty of the data is represented by the cumulative probability functions for each pixel or vector object. Depending on the chosen quantile or threshold value, the map colour scale shows the associated value or probability (Pebesma et al. 2007) ");
				visMethodsList.add(statisticalDimensionInaGISMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType && d instanceof GISDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Statistical Dimension in a GIS",
						"The uncertainty of the data is represented by the cumulative probability functions for each pixel or vector object. Depending on the chosen quantile or threshold value, the map colour scale shows the associated value or probability (Pebesma et al. 2007) ");
				visMethodsList.add(statisticalDimensionInaGISMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType
					&& d instanceof StatisticsDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Statistical Dimension in a GIS",
						"The uncertainty of the data is represented by the cumulative probability functions for each pixel or vector object. Depending on the chosen quantile or threshold value, the map colour scale shows the associated value or probability (Pebesma et al. 2007)");
				visMethodsList.add(statisticalDimensionInaGISMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType && d instanceof OtherDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);

			}
			// Second branch
			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType
					&& d instanceof MapVisualisationDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Statistical Dimension in a GIS",
						"The uncertainty of the data is represented by the cumulative probability functions for each pixel or vector object. Depending on the chosen quantile or threshold value, the map colour scale shows the associated value or probability (Pebesma et al. 2007) ");
				visMethodsList.add(statisticalDimensionInaGISMethod);
				InteractiveVisualizationMethod intervalsAndErrorBarsMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Intervals and Errorbars",
						"The length of the error bars represents the low and high uncertainties, where wider bars represent higher uncertainties and narrower bars represent lower uncertainties. Similarly, wider intervals indicate higher uncertainties and narrower intervals indicate lower uncerrtainties");
				visMethodsList.add(intervalsAndErrorBarsMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType
					&& d instanceof UrbanPlanningDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				StaticVisualizationMethod glyphsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Glyphs",
						"The uncertainty and the data are represented in a bivariate depiction through pictorial symbols,  known as glyphs (Pang 2001)");
				visMethodsList.add(glyphsMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Statistical Dimension in a GIS",
						"The uncertainty of the data is represented by the cumulative probability functions for each pixel or vector object. Depending on the chosen quantile or threshold value, the map colour scale shows the associated value or probability (Pebesma et al. 2007)");
				visMethodsList.add(statisticalDimensionInaGISMethod);
				InteractiveVisualizationMethod intervalsAndErrorBarsMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Intervals and Errorbars",
						"The length of the error bars represents the low and high uncertainties, where wider bars represent higher uncertainties and narrower bars represent lower uncertainties. Similarly, wider intervals indicate higher uncertainties and narrower intervals indicate lower uncerrtainties");
				visMethodsList.add(intervalsAndErrorBarsMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType
					&& d instanceof DecisionSupportDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Statistical Dimension in a GIS",
						"The uncertainty of the data is represented by the cumulative probability functions for each pixel or vector object. Depending on the chosen quantile or threshold value, the map colour scale shows the associated value or probability (Pebesma et al. 2007)");
				visMethodsList.add(statisticalDimensionInaGISMethod);
				InteractiveVisualizationMethod intervalsAndErrorBarsMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Intervals and Errorbars",
						"The length of the error bars represents the low and high uncertainties, where wider bars represent higher uncertainties and narrower bars represent lower uncertainties. Similarly, wider intervals indicate higher uncertainties and narrower intervals indicate lower uncerrtainties");
				visMethodsList.add(intervalsAndErrorBarsMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType && d instanceof GISDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);

				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Statistical Dimension in a GIS",
						"The uncertainty of the data is represented by the cumulative probability functions for each pixel or vector object. Depending on the chosen quantile or threshold value, the map colour scale shows the associated value or probability (Pebesma et al. 2007)");
				visMethodsList.add(statisticalDimensionInaGISMethod);
				InteractiveVisualizationMethod intervalsAndErrorBarsMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Intervals and Errorbars",
						"The length of the error bars represents the low and high uncertainties, where wider bars represent higher uncertainties and narrower bars represent lower uncertainties. Similarly, wider intervals indicate higher uncertainties and narrower intervals indicate lower uncerrtainties");
				visMethodsList.add(intervalsAndErrorBarsMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType
					&& d instanceof StatisticsDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Statistical Dimension in a GIS",
						"The uncertainty of the data is represented by the cumulative probability functions for each pixel or vector object. Depending on the chosen quantile or threshold value, the map colour scale shows the associated value or probability (Pebesma et al. 2007) ");
				visMethodsList.add(statisticalDimensionInaGISMethod);
				InteractiveVisualizationMethod intervalsAndErrorBarsMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Intervals and Errorbars",
						"The length of the error bars represents the low and high uncertainties, where wider bars represent higher uncertainties and narrower bars represent lower uncertainties. Similarly, wider intervals indicate higher uncertainties and narrower intervals indicate lower uncerrtainties");
				visMethodsList.add(intervalsAndErrorBarsMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType && d instanceof OtherDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);

			}
			// Third branch
			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof MapVisualisationDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
			}
			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof UrbanPlanningDomain) {
				// return null
			}
			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof DecisionSupportDomain) {
				// return null
			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType && d instanceof GISDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof StatisticsDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof OtherDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
			}
			// Fourth branch
			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof CategoricalType
					&& d instanceof MapVisualisationDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
			}
			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof CategoricalType
					&& d instanceof UrbanPlanningDomain) {
				StaticVisualizationMethod glyphsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Glyphs",
						"The uncertainty and the data are represented in a bivariate depiction through pictorial symbols,  known as glyphs (Pang 2001)");
				visMethodsList.add(glyphsMethod);
			}
			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof CategoricalType
					&& d instanceof DecisionSupportDomain) {
				// return null
			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof CategoricalType && d instanceof GISDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof CategoricalType
					&& d instanceof StatisticsDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof CategoricalType
					&& d instanceof OtherDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
			}
			// Fifth branch
			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType
					&& d instanceof MapVisualisationDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
			}
			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType
					&& d instanceof UrbanPlanningDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
			}
			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType
					&& d instanceof DecisionSupportDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType && d instanceof GISDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType
					&& d instanceof StatisticsDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType && d instanceof OtherDomain) {
				// return null
			}
			// Sixth branch
			if (uT instanceof PositionalUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType
					&& d instanceof MapVisualisationDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
			}
			if (uT instanceof PositionalUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType
					&& d instanceof UrbanPlanningDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				StaticVisualizationMethod glyphsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Glyphs",
						"The uncertainty and the data are represented in a bivariate depiction through pictorial symbols,  known as glyphs (Pang 2001)");
				visMethodsList.add(glyphsMethod);
			}
			if (uT instanceof PositionalUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType
					&& d instanceof DecisionSupportDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType && d instanceof GISDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType
					&& d instanceof StatisticsDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType && d instanceof OtherDomain) {
				// return null

			}
			// Seventh branch
			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof MapVisualisationDomain) {
				System.out.println("");
			}
			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof UrbanPlanningDomain) {
				// return null
			}
			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof DecisionSupportDomain) {
				// return null
			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType && d instanceof GISDomain) {
				// return null
			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof StatisticsDomain) {
				// return null
			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof OtherDomain) {
				// return null
			}
			// Eighth branch
			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof MapVisualisationDomain) {
				// return null
			}
			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof UrbanPlanningDomain) {
				// return null
			}
			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof DecisionSupportDomain) {
				// return null
			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType && d instanceof GISDomain) {
				// return null
			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof StatisticsDomain) {
				// return null
			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof OtherDomain) {
				// return null
			}

		}
		// ///// without domain:
		else {

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				StaticVisualizationMethod exceedanceProbabilityMappingMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"ExceedanceProbabilityMapping",
						"    Exceedance probability is used to depict the probability of exceeding a threshold in a certain pixel or area. This method is commonly used for environmental concentration maps where the exceedance of concentration thresholds are mapped (Van de Kassteele & Velders 2006).");
				visMethodsList.add(exceedanceProbabilityMappingMethod);
				StaticVisualizationMethod rgbColourModelMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"RgbColourModel",
						"In a scenario where the uncertainty has to be generated for three variables, the colours red, green and blue are used to represent these variables whereas the amount of uncertainty is represented through the intensity of these colours, i.e. higher intensity indicates lower uncertainty. In instances where more than three variables are involved, the technique of colour mixing is incorporated (Hengl et al. 2002).");
				visMethodsList.add(rgbColourModelMethod);
				StaticVisualizationMethod whiteningMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Whitening",
						"A colour model is used where the colour hue is used to represent the data and the saturation-intensity (whiteness) is used to represent the associated uncertainty. The amount of white colour, proportional to the uncertinty is mixed in with the hue which represents the prediction (Hengl 2003).");
				visMethodsList.add(whiteningMethod);
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
				StaticVisualizationMethod symbolFocusMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Symbol Focus",
						"The focus metaphor is based on the human perception of focussed and non-focused (blurred) views. Uncertain data is depicted out of focus, making it less precisely visible, e.g. foggy. More certain data is depicted in focus, e.g. crisp boundaries (MacEachren et al. 2005).");
				visMethodsList.add(symbolFocusMethod);
				StaticVisualizationMethod opacityMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Opacity",
						"Less uncertain data is seen less opaque and more uncertain data is more opaque. This concept can also be used in reverse where uncertain data is shown more transparently (MacEachren et al. 2005). ");
				visMethodsList.add(opacityMethod);
				DynamicVisualizationMethod animatedIsolinesMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Animated Isolines",
						"The concept of contouring or isolines can also be used in an animated environment (Fauerbach et al. 1996).");
				visMethodsList.add(animatedIsolinesMethod);
				DynamicVisualizationMethod animationMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Animation",
						"Different realisations of the uncertain attribute are animated together in 2D to emphasise the uncertainty (Ehlschlaeger et al. 1997).");
				visMethodsList.add(animationMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Statistical Dimension in a GIS",
						"The uncertainty of the data is represented by the cumulative probability functions for each pixel or vector object. Depending on the chosen quantile or threshold value, the map colour scale shows the associated value or probability (Pebesma et al. 2007) ");
				visMethodsList.add(statisticalDimensionInaGISMethod);
				DynamicVisualizationMethod blinkingPixelsMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Blinking Pixels",
						"The categorical data in each grid cell of a map is represented by colour. The colour remains stable for pixels with less uncertain classifications and changes continuously proportional to the uncertainty in the data creating a flickering environment (Fisher 1993). ");
				visMethodsList.add(blinkingPixelsMethod);
				DynamicVisualizationMethod blinkingRegionsMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Blinking Regions",
						"Two layers of attribute data and uncertainty data are identified by their legends of varying classes of data and uncertainty respectively. These two images are overlaid on top of another and alternately displayed (Kardos et al. 2006).");
				visMethodsList.add(blinkingRegionsMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType) {
				StaticVisualizationMethod exceedanceProbabilityMappingMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"ExceedanceProbabilityMapping",
						"    Exceedance probability is used to depict the probability of exceeding a threshold in a certain pixel or area. This method is commonly used for environmental concentration maps where the exceedance of concentration thresholds are mapped (Van de Kassteele & Velders 2006). ");
				visMethodsList.add(exceedanceProbabilityMappingMethod);
				StaticVisualizationMethod rgbColourModelMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"RgbColourModel",
						"In a scenario where the uncertainty has to be generated for three variables, the colours red, green and blue are used to represent these variables whereas the amount of uncertainty is represented through the intensity of these colours, i.e. higher intensity indicates lower uncertainty. In instances where more than three variables are involved, the technique of colour mixing is incorporated (Hengl et al. 2002).");
				visMethodsList.add(rgbColourModelMethod);
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				StaticVisualizationMethod hierarchicalSpatialDataStructuresMethod = new StaticVisualizationMethod(
						uT, dF, dT, "Hierarchical Spatial Data Structures", "");
				visMethodsList.add(hierarchicalSpatialDataStructuresMethod);
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
				StaticVisualizationMethod symbolFocusMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Symbol Focus",
						"The focus metaphor is based on the human perception of focussed and non-focused (blurred) views. Uncertain data is depicted out of focus, making it less precisely visible, e.g. foggy. More certain data is depicted in focus, e.g. crisp boundaries (MacEachren et al. 2005).");
				visMethodsList.add(symbolFocusMethod);
				StaticVisualizationMethod opacityMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Opacity",
						"Less uncertain data is seen less opaque and more uncertain data is more opaque. This concept can also be used in reverse where uncertain data is shown more transparently (MacEachren et al. 2005).");
				visMethodsList.add(opacityMethod);
				StaticVisualizationMethod glyphsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Glyphs",
						"The uncertainty and the data are represented in a bivariate depiction through pictorial symbols,  known as glyphs (Pang 2001)");
				visMethodsList.add(glyphsMethod);
				StaticVisualizationMethod whiteningMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Whitening",
						"A colour model is used where the colour hue is used to represent the data and the saturation-intensity (whiteness) is used to represent the associated uncertainty. The amount of white colour, proportional to the uncertinty is mixed in with the hue which represents the prediction (Hengl 2003).");
				visMethodsList.add(whiteningMethod);
				DynamicVisualizationMethod animationMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Animation",
						"Different realisations of the uncertain attribute are animated together in 2D to emphasise the uncertainty (Ehlschlaeger et al. 1997).");
				visMethodsList.add(animationMethod);
				DynamicVisualizationMethod animatedIsolinesMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Animated Isolines",
						"The concept of contouring or isolines can also be used in an animated environment (Fauerbach et al. 1996).");
				visMethodsList.add(animatedIsolinesMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Statistical Dimension in a GIS",
						"The uncertainty of the data is represented by the cumulative probability functions for each pixel or vector object. Depending on the chosen quantile or threshold value, the map colour scale shows the associated value or probability (Pebesma et al. 2007) ");
				visMethodsList.add(statisticalDimensionInaGISMethod);
				DynamicVisualizationMethod blinkingRegionsMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Blinking Regions",
						"Two layers of attribute data and uncertainty data are identified by their legends of varying classes of data and uncertainty respectively. These two images are overlaid on top of another and alternately displayed (Kardos et al. 2006).");
				visMethodsList.add(blinkingRegionsMethod);
				InteractiveVisualizationMethod intervalsAndErrorBarsMethod = new InteractiveVisualizationMethod(
						uT,
						dF,
						dT,
						"Intervals and Errorbars",
						"The length of the error bars represents the low and high uncertainties, where wider bars represent higher uncertainties and narrower bars represent lower uncertainties. Similarly, wider intervals indicate higher uncertainties and narrower intervals indicate lower uncerrtainties");
				visMethodsList.add(intervalsAndErrorBarsMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType) {
				StaticVisualizationMethod whiteningMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Whitening",
						"A colour model is used where the colour hue is used to represent the data and the saturation-intensity (whiteness) is used to represent the associated uncertainty. The amount of white colour, proportional to the uncertinty is mixed in with the hue which represents the prediction (Hengl 2003).");
				visMethodsList.add(whiteningMethod);
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
				StaticVisualizationMethod symbolFocusMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Symbol Focus",
						"The focus metaphor is based on the human perception of focussed and non-focused (blurred) views. Uncertain data is depicted out of focus, making it less precisely visible, e.g. foggy. More certain data is depicted in focus, e.g. crisp boundaries (MacEachren et al. 2005).");
				visMethodsList.add(symbolFocusMethod);
				StaticVisualizationMethod opacityMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Opacity",
						"Less uncertain data is seen less opaque and more uncertain data is more opaque. This concept can also be used in reverse where uncertain data is shown more transparently (MacEachren et al. 2005).");
				visMethodsList.add(opacityMethod);
				DynamicVisualizationMethod animationMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Animation",
						"Different realisations of the uncertain attribute are animated together in 2D to emphasise the uncertainty (Ehlschlaeger et al. 1997).");
				visMethodsList.add(animationMethod);
				DynamicVisualizationMethod blinkingPixelsMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Blinking Pixels",
						"The categorical data in each grid cell of a map is represented by colour. The colour remains stable for pixels with less uncertain classifications and changes continuously proportional to the uncertainty in the data creating a flickering environment (Fisher 1993).");
				visMethodsList.add(blinkingPixelsMethod);
				DynamicVisualizationMethod blinkingRegionsMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Blinking Regions",
						"Two layers of attribute data and uncertainty data are identified by their legends of varying classes of data and uncertainty respectively. These two images are overlaid on top of another and alternately displayed (Kardos et al. 2006).");
				visMethodsList.add(blinkingRegionsMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof CategoricalType) {
				StaticVisualizationMethod whiteningMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Whitening",
						"A colour model is used where the colour hue is used to represent the data and the saturation-intensity (whiteness) is used to represent the associated uncertainty. The amount of white colour, proportional to the uncertinty is mixed in with the hue which represents the prediction (Hengl 2003).");
				visMethodsList.add(whiteningMethod);
				StaticVisualizationMethod adjacentMapsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Adjacent Maps",
						"This method presents value and uncertainty on two separate maps adjacent to each other. Through comparing the two maps the degree of uncertainty at different points can be comprehended (MacEachren et al. 1998).");
				visMethodsList.add(adjacentMapsMethod);
				StaticVisualizationMethod symbolFocusMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Symbol Focus",
						"The focus metaphor is based on the human perception of focussed and non-focused (blurred) views. Uncertain data is depicted out of focus, making it less precisely visible, e.g. foggy. More certain data is depicted in focus, e.g. crisp boundaries (MacEachren et al. 2005).");
				visMethodsList.add(symbolFocusMethod);
				StaticVisualizationMethod opacityMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Opacity",
						"Less uncertain data is seen less opaque and more uncertain data is more opaque. This concept can also be used in reverse where uncertain data is shown more transparently (MacEachren et al. 2005).");
				visMethodsList.add(opacityMethod);
				StaticVisualizationMethod glyphsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Glyphs",
						"The uncertainty and the data are represented in a bivariate depiction through pictorial symbols,  known as glyphs (Pang 2001)");
				visMethodsList.add(glyphsMethod);
				StaticVisualizationMethod hierarchicalSpatialDataStructuresMethod = new StaticVisualizationMethod(
						uT, dF, dT, "Hierarchical Spatial Data Structures", "");
				visMethodsList.add(hierarchicalSpatialDataStructuresMethod);
				DynamicVisualizationMethod animationMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Animation",
						"Different realisations of the uncertain attribute are animated together in 2D to emphasise the uncertainty (Ehlschlaeger et al. 1997).");
				visMethodsList.add(animationMethod);
				DynamicVisualizationMethod blinkingRegionsMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Blinking Regions",
						"Two layers of attribute data and uncertainty data are identified by their legends of varying classes of data and uncertainty respectively. These two images are overlaid on top of another and alternately displayed (Kardos et al. 2006).");
				visMethodsList.add(blinkingRegionsMethod);

			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				DynamicVisualizationMethod animationMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Animation",
						"Different realisations of the uncertain attribute are animated together in 2D to emphasise the uncertainty (Ehlschlaeger et al. 1997).");
				visMethodsList.add(animationMethod);
				DynamicVisualizationMethod animatedIsolinesMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Animated Isolines",
						"The concept of contouring or isolines can also be used in an animated environment (Fauerbach et al. 1996).");
				visMethodsList.add(animatedIsolinesMethod);
				DynamicVisualizationMethod blinkingPixelsMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Blinking Pixels",
						"The categorical data in each grid cell of a map is represented by colour. The colour remains stable for pixels with less uncertain classifications and changes continuously proportional to the uncertainty in the data creating a flickering environment (Fisher 1993).");
				visMethodsList.add(blinkingPixelsMethod);

			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				StaticVisualizationMethod glyphsMethod = new StaticVisualizationMethod(
						uT,
						dF,
						dT,
						"Glyphs",
						"The uncertainty and the data are represented in a bivariate depiction through pictorial symbols,  known as glyphs (Pang 2001)");
				visMethodsList.add(glyphsMethod);
				DynamicVisualizationMethod animationMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Animation",
						"Different realisations of the uncertain attribute are animated together in 2D to emphasise the uncertainty (Ehlschlaeger et al. 1997).");
				visMethodsList.add(animationMethod);
				DynamicVisualizationMethod animatedIsolinesMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Animated Isolines",
						"The concept of contouring or isolines can also be used in an animated environment (Fauerbach et al. 1996).");
				visMethodsList.add(animatedIsolinesMethod);
			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType) {
				DynamicVisualizationMethod animationMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Animation",
						"Different realisations of the uncertain attribute are animated together in 2D to emphasise the uncertainty (Ehlschlaeger et al. 1997).");
				visMethodsList.add(animationMethod);
				DynamicVisualizationMethod blinkingPixelsMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Blinking Pixels",
						"The categorical data in each grid cell of a map is represented by colour. The colour remains stable for pixels with less uncertain classifications and changes continuously proportional to the uncertainty in the data creating a flickering environment (Fisher 1993).");
				visMethodsList.add(blinkingPixelsMethod);

			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof CategoricalType) {
				DynamicVisualizationMethod animationMethod = new DynamicVisualizationMethod(
						uT,
						dF,
						dT,
						"Animation",
						"Different realisations of the uncertain attribute are animated together in 2D to emphasise the uncertainty (Ehlschlaeger et al. 1997).");
				visMethodsList.add(animationMethod);

				// end
			}
		}

		logger.info("Found {} elements: {}", visMethodsList.size(),
				Arrays.deepToString(visMethodsList.toArray()));

		return visMethodsList;
	}

}