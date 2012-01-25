<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
Copyright 2011 52°North Initiative for Geospatial Open Source Software GmbH

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
 
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
-->

<%@page import="javax.swing.text.StyledEditorKit.ForegroundAction"%>
<%@page import="java.util.Iterator"%>
<%@ page import="org.n52.geostatistics.uvs.*"%>
<%@ page import="org.n52.geostatistics.uvs.domain.*"%>
<%@ page import="org.n52.geostatistics.uvs.format.*"%>
<%@ page import="org.n52.geostatistics.uvs.method.*"%>
<%@ page import="org.n52.geostatistics.uvs.type.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Map.Entry"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Uncertainty Visualisation Selection Result</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="style.css" type="text/css" />

<script type="text/javascript" src="tinybox.js">
	
</script>
<script type="text/javascript">
	function openJS() {
		// 		alert('loaded')
	}
	function closeJS() {
		// 		alert('closed')
	}
</script>

<link href="<%=request.getContextPath()%>/favicon.png"
	rel="shortcut icon" type="image/x-icon" />

<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'UA-28300786-1' ]);
	_gaq.push([ '_trackPageview' ]);

	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();
</script>

</head>
<body>

	<div id="main">
		<h1>Uncertainty Visualisation Selection Result</h1>
		<%
			String uncertaintyType = request.getParameter("UncertaintyType");
			String dataFormat = request.getParameter("DataFormat");
			String dataType = request.getParameter("DataType");
			String domain = request.getParameter("domain");

			UvsModel model = new UvsModel();
			ArrayList<VisualizationMethod> visList = model
					.getCorrecVisMethodsForUserInputs(uncertaintyType,
							dataFormat, dataType, domain);
		%>

		<h2>Your selection was:</h2>
		<ul>
			<li>uncertainty type = <%=uncertaintyType%></li>
			<li>data format = <%=dataFormat%></li>
			<li>data type = <%=dataType%></li>
			<li>domain = <%=domain%></li>
		</ul>

		<%
			if (visList.isEmpty()) {
				out.println("<h2>Currently there are no methods assessed for this selection. Please select 'None' from domain and submit again.");
			} else {
				out.println("<h2>The most suitable visualisation methods for this selection are (ordered, best at the top):</h2>");
			}
		%>

		<ol>
			<%
				for (VisualizationMethod visListElement : visList) {
					out.println("<div>");

					out.print("<b>Method name:</b> " + visListElement.name);
					out.println("<br />");

					out.print("<b>Description:</b> "
							+ visListElement.descriptionText);
					
					// refernce links:
					out.print(" (");
					Set<Entry<String, String>> links = visListElement.referenceLinks
							.entrySet();
					Iterator<Entry<String, String>> iter = links.iterator();
					while (iter.hasNext()) {
						Entry<String, String> e = iter.next();
						out.print("<a href=\"" + e.getValue() + "\"");
						out.print("title=\"");
						out.print(e.getKey());
						out.print("\">");
						out.print(e.getKey());
						out.print("</a>");

						if (iter.hasNext()) {
							out.print(", ");
						}
					}
					out.println(")");

					Set<Entry<String, String>> images = visListElement.images
							.entrySet();
					Iterator<Entry<String, String>> iter2 = images.iterator();
					
					boolean imageOrVideo = false;

					while (iter2.hasNext()) {
					    imageOrVideo = true;
						Entry<String, String> e2 = iter2.next();
						out.println("<ul>");
						out.println("<li>");
						out.print("<span onclick='TINY.box.show({iframe:\"");
						out.print(e2.getKey());
						out.print("\",boxid:\"frameless\",width:550,height:450,fixed:false,maskid:\"bluemask\",");
						out.print("maskopacity:40,closejs:function (){closeJS()}})' >");
						out.print("<p><img src=\"");
						out.print(e2.getKey());
						out.print("\" width=\"40\" height=\"30\"/><br /><em>");
						out.print(e2.getValue());
						out.print("</em></p></span>");
						out.println("</li>");
						out.println("</ul>");
					}

					for (VideoDemo video : visListElement.videos) {
					    imageOrVideo = true;
						out.println("<div>");
						out.println("<em>Click image for a video demo</em> ");
						out.println("<ul>");
						out.println("<li>");
						out.println("<span onclick='TINY.box.show(");
						out.println("{iframe:");
						out.println("\"" + video.videoLink + "\",");
						out.println("boxid:\"frameless\",");
						out.println("width:");
						out.println("\"" + video.iframeWidth + "\",");
						out.println("height:");
						out.println("\"" + video.iframeHeight + "\",");
						out.println("fixed:false,");
						out.println("maskid:\"bluemask\",");
						out.println("maskopacity:40,closejs:function (){closeJS()}})'>");
						out.println("<p><img src='" + video.tinyImageLink + "' width = '40' height = '30' />");
						out.println("</p>");
						out.println("</span>");
						out.println("</li>");
						out.println("</ul>");
						out.println("</div>");
					}
					
					if(!imageOrVideo) out.println("<div style=\"height: 50px;\">&nbsp;</div>");

					out.println("</div>");
				}
			%>
		</ol>

		<jsp:include page="footer.jsp" />
	</div>

</body>
</html>