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
import org.n52.geostatistics.uvs.method.AdjacentMaps;
import org.n52.geostatistics.uvs.method.AnimatedIsolines;
import org.n52.geostatistics.uvs.method.Animation;
import org.n52.geostatistics.uvs.method.BlinkingPixels;
import org.n52.geostatistics.uvs.method.BlinkingRegions;
import org.n52.geostatistics.uvs.method.Contouring;
import org.n52.geostatistics.uvs.method.DynamicVisualizationMethod;
import org.n52.geostatistics.uvs.method.ExceedanceProbabilityMapping;
import org.n52.geostatistics.uvs.method.Glyphs;
import org.n52.geostatistics.uvs.method.HierachicalSpatialDataStructures;
import org.n52.geostatistics.uvs.method.InteractiveVisualizationMethod;
import org.n52.geostatistics.uvs.method.IntervalsAndErrorbars;
import org.n52.geostatistics.uvs.method.Opacity;
import org.n52.geostatistics.uvs.method.RGBColourModel;
import org.n52.geostatistics.uvs.method.StaticVisualizationMethod;
import org.n52.geostatistics.uvs.method.StatisticalDimensionInAGIS;
import org.n52.geostatistics.uvs.method.SymbolFocus;
import org.n52.geostatistics.uvs.method.VisualizationMethod;
import org.n52.geostatistics.uvs.method.Whitening;
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
 * @author Hansi Senaratne (hansi.senaratne@wwu.de), Daniel Nüst (d.nuest@52north.org)
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
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new StatisticalDimensionInAGIS(
						uT,
						dF,
						dT);
				visMethodsList.add(statisticalDimensionInaGISMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType
					&& d instanceof UrbanPlanningDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new StatisticalDimensionInAGIS(
						uT,
						dF,
						dT);
				visMethodsList.add(statisticalDimensionInaGISMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType
					&& d instanceof DecisionSupportDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new StatisticalDimensionInAGIS(
						uT,
						dF,
						dT);
				visMethodsList.add(statisticalDimensionInaGISMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType && d instanceof GISDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new StatisticalDimensionInAGIS(
						uT,
						dF,
						dT);
				visMethodsList.add(statisticalDimensionInaGISMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType
					&& d instanceof StatisticsDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new StatisticalDimensionInAGIS(
						uT,
						dF,
						dT);
				visMethodsList.add(statisticalDimensionInaGISMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType && d instanceof OtherDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
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
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new StatisticalDimensionInAGIS(
						uT,
						dF,
						dT);
				visMethodsList.add(statisticalDimensionInaGISMethod);
				InteractiveVisualizationMethod intervalsAndErrorBarsMethod = new IntervalsAndErrorbars(
						uT,
						dF,
						dT);
				visMethodsList.add(intervalsAndErrorBarsMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType
					&& d instanceof UrbanPlanningDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				StaticVisualizationMethod glyphsMethod = new Glyphs(
						uT,
						dF,
						dT);
				visMethodsList.add(glyphsMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new StatisticalDimensionInAGIS(
						uT,
						dF,
						dT);
				visMethodsList.add(statisticalDimensionInaGISMethod);
				InteractiveVisualizationMethod intervalsAndErrorBarsMethod = new IntervalsAndErrorbars(
						uT,
						dF,
						dT);
				visMethodsList.add(intervalsAndErrorBarsMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType
					&& d instanceof DecisionSupportDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new StatisticalDimensionInAGIS(
						uT,
						dF,
						dT);
				visMethodsList.add(statisticalDimensionInaGISMethod);
				InteractiveVisualizationMethod intervalsAndErrorBarsMethod = new IntervalsAndErrorbars(
						uT,
						dF,
						dT);
				visMethodsList.add(intervalsAndErrorBarsMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType && d instanceof GISDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);

				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new StatisticalDimensionInAGIS(
						uT,
						dF,
						dT);
				visMethodsList.add(statisticalDimensionInaGISMethod);
				InteractiveVisualizationMethod intervalsAndErrorBarsMethod = new IntervalsAndErrorbars(
						uT,
						dF,
						dT);
				visMethodsList.add(intervalsAndErrorBarsMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType
					&& d instanceof StatisticsDomain) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new StatisticalDimensionInAGIS(
						uT,
						dF,
						dT);
				visMethodsList.add(statisticalDimensionInaGISMethod);
				InteractiveVisualizationMethod intervalsAndErrorBarsMethod = new IntervalsAndErrorbars(
						uT,
						dF,
						dT);
				visMethodsList.add(intervalsAndErrorBarsMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType && d instanceof OtherDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);

			}
			// Third branch
			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof MapVisualisationDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
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
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);
			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof StatisticsDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);
			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType
					&& d instanceof OtherDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);
			}
			// Fourth branch
			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof CategoricalType
					&& d instanceof MapVisualisationDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);
			}
			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof CategoricalType
					&& d instanceof UrbanPlanningDomain) {
				StaticVisualizationMethod glyphsMethod = new Glyphs(
						uT,
						dF,
						dT);
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
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);
			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof CategoricalType
					&& d instanceof StatisticsDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);
			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof CategoricalType
					&& d instanceof OtherDomain) {
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
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
				StaticVisualizationMethod glyphsMethod = new Glyphs(
						uT,
						dF,
						dT);
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
				StaticVisualizationMethod exceedanceProbabilityMappingMethod = new ExceedanceProbabilityMapping(
						uT,
						dF,
						dT);
				visMethodsList.add(exceedanceProbabilityMappingMethod);
				StaticVisualizationMethod rgbColourModelMethod = new RGBColourModel(
						uT,
						dF,
						dT);
				visMethodsList.add(rgbColourModelMethod);
				StaticVisualizationMethod whiteningMethod = new Whitening(
						uT,
						dF,
						dT);
				visMethodsList.add(whiteningMethod);
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);
				StaticVisualizationMethod symbolFocusMethod = new SymbolFocus(
						uT,
						dF,
						dT);
				visMethodsList.add(symbolFocusMethod);
				StaticVisualizationMethod opacityMethod = new Opacity(
						uT,
						dF,
						dT);
				visMethodsList.add(opacityMethod);
				DynamicVisualizationMethod animatedIsolinesMethod = new AnimatedIsolines(
						uT,
						dF,
						dT);
				visMethodsList.add(animatedIsolinesMethod);
				DynamicVisualizationMethod animationMethod = new Animation(
						uT,
						dF,
						dT);
				visMethodsList.add(animationMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new StatisticalDimensionInAGIS(
						uT,
						dF,
						dT);
				visMethodsList.add(statisticalDimensionInaGISMethod);
				DynamicVisualizationMethod blinkingPixelsMethod = new BlinkingPixels(
						uT,
						dF,
						dT);
				visMethodsList.add(blinkingPixelsMethod);
				DynamicVisualizationMethod blinkingRegionsMethod = new BlinkingRegions(
						uT,
						dF,
						dT);
				visMethodsList.add(blinkingRegionsMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType) {
				StaticVisualizationMethod exceedanceProbabilityMappingMethod = new ExceedanceProbabilityMapping(
						uT,
						dF,
						dT);
				visMethodsList.add(exceedanceProbabilityMappingMethod);
				StaticVisualizationMethod rgbColourModelMethod = new RGBColourModel(
						uT,
						dF,
						dT);
				visMethodsList.add(rgbColourModelMethod);
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				StaticVisualizationMethod hierarchicalSpatialDataStructuresMethod = new HierachicalSpatialDataStructures(
						uT, dF, dT);
				visMethodsList.add(hierarchicalSpatialDataStructuresMethod);
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);
				StaticVisualizationMethod symbolFocusMethod = new SymbolFocus(
						uT,
						dF,
						dT);
				visMethodsList.add(symbolFocusMethod);
				StaticVisualizationMethod opacityMethod = new Opacity(
						uT,
						dF,
						dT);
				visMethodsList.add(opacityMethod);
				StaticVisualizationMethod glyphsMethod = new Glyphs(
						uT,
						dF,
						dT);
				visMethodsList.add(glyphsMethod);
				StaticVisualizationMethod whiteningMethod = new Whitening(
						uT,
						dF,
						dT);
				visMethodsList.add(whiteningMethod);
				DynamicVisualizationMethod animationMethod = new Animation(
						uT,
						dF,
						dT);
				visMethodsList.add(animationMethod);
				DynamicVisualizationMethod animatedIsolinesMethod = new AnimatedIsolines(
						uT,
						dF,
						dT);
				visMethodsList.add(animatedIsolinesMethod);
				InteractiveVisualizationMethod statisticalDimensionInaGISMethod = new StatisticalDimensionInAGIS(
						uT,
						dF,
						dT);
				visMethodsList.add(statisticalDimensionInaGISMethod);
				DynamicVisualizationMethod blinkingRegionsMethod = new BlinkingRegions(
						uT,
						dF,
						dT);
				visMethodsList.add(blinkingRegionsMethod);
				InteractiveVisualizationMethod intervalsAndErrorBarsMethod = new IntervalsAndErrorbars(
						uT,
						dF,
						dT);
				visMethodsList.add(intervalsAndErrorBarsMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType) {
				StaticVisualizationMethod whiteningMethod = new Whitening(
						uT,
						dF,
						dT);
				visMethodsList.add(whiteningMethod);
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);
				StaticVisualizationMethod symbolFocusMethod = new SymbolFocus(
						uT,
						dF,
						dT);
				visMethodsList.add(symbolFocusMethod);
				StaticVisualizationMethod opacityMethod = new Opacity(
						uT,
						dF,
						dT);
				visMethodsList.add(opacityMethod);
				DynamicVisualizationMethod animationMethod = new Animation(
						uT,
						dF,
						dT);
				visMethodsList.add(animationMethod);
				DynamicVisualizationMethod blinkingPixelsMethod = new BlinkingPixels(
						uT,
						dF,
						dT);
				visMethodsList.add(blinkingPixelsMethod);
				DynamicVisualizationMethod blinkingRegionsMethod = new BlinkingRegions(
						uT,
						dF,
						dT);
				visMethodsList.add(blinkingRegionsMethod);

			}

			if (uT instanceof AttributeUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof CategoricalType) {
				StaticVisualizationMethod whiteningMethod = new Whitening(
						uT,
						dF,
						dT);
				visMethodsList.add(whiteningMethod);
				StaticVisualizationMethod adjacentMapsMethod = new AdjacentMaps(
						uT,
						dF,
						dT);
				visMethodsList.add(adjacentMapsMethod);
				StaticVisualizationMethod symbolFocusMethod = new SymbolFocus(
						uT,
						dF,
						dT);
				visMethodsList.add(symbolFocusMethod);
				StaticVisualizationMethod opacityMethod = new Opacity(
						uT,
						dF,
						dT);
				visMethodsList.add(opacityMethod);
				StaticVisualizationMethod glyphsMethod = new Glyphs(
						uT,
						dF,
						dT);
				visMethodsList.add(glyphsMethod);
				StaticVisualizationMethod hierarchicalSpatialDataStructuresMethod = new HierachicalSpatialDataStructures(
						uT, dF, dT);
				visMethodsList.add(hierarchicalSpatialDataStructuresMethod);
				DynamicVisualizationMethod animationMethod = new Animation(
						uT,
						dF,
						dT);
				visMethodsList.add(animationMethod);
				DynamicVisualizationMethod blinkingRegionsMethod = new BlinkingRegions(
						uT,
						dF,
						dT);
				visMethodsList.add(blinkingRegionsMethod);

			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof ContinuousType) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				DynamicVisualizationMethod animationMethod = new Animation(
						uT,
						dF,
						dT);
				visMethodsList.add(animationMethod);
				DynamicVisualizationMethod animatedIsolinesMethod = new AnimatedIsolines(
						uT,
						dF,
						dT);
				visMethodsList.add(animatedIsolinesMethod);
				DynamicVisualizationMethod blinkingPixelsMethod = new BlinkingPixels(
						uT,
						dF,
						dT);
				visMethodsList.add(blinkingPixelsMethod);

			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof ContinuousType) {
				VisualizationMethod contouringMethod = new Contouring(uT, dF,
						dT);
				visMethodsList.add(contouringMethod);
				StaticVisualizationMethod glyphsMethod = new Glyphs(
						uT,
						dF,
						dT);
				visMethodsList.add(glyphsMethod);
				DynamicVisualizationMethod animationMethod = new Animation(
						uT,
						dF,
						dT);
				visMethodsList.add(animationMethod);
				DynamicVisualizationMethod animatedIsolinesMethod = new AnimatedIsolines(
						uT,
						dF,
						dT);
				visMethodsList.add(animatedIsolinesMethod);
			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof RasterFormat
					&& dT instanceof CategoricalType) {
				DynamicVisualizationMethod animationMethod = new Animation(
						uT,
						dF,
						dT);
				visMethodsList.add(animationMethod);
				DynamicVisualizationMethod blinkingPixelsMethod = new BlinkingPixels(
						uT,
						dF,
						dT);
				visMethodsList.add(blinkingPixelsMethod);

			}

			if (uT instanceof PositionalUncertainty
					&& dF instanceof VectorFormat
					&& dT instanceof CategoricalType) {
				DynamicVisualizationMethod animationMethod = new Animation(
						uT,
						dF,
						dT);
				visMethodsList.add(animationMethod);

				// end
			}
		}

		logger.info("Found {} elements: {}", visMethodsList.size(),
				Arrays.deepToString(visMethodsList.toArray()));

		return visMethodsList;
	}

}