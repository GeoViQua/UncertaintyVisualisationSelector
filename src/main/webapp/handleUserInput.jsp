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

<%@ page import="org.n52.geostatistics.uvs.*"%>
<%@ page import="org.n52.geostatistics.uvs.domain.*"%>
<%@ page import="org.n52.geostatistics.uvs.format.*"%>
<%@ page import="org.n52.geostatistics.uvs.method.*"%>
<%@ page import="org.n52.geostatistics.uvs.type.*"%>
<%@ page import="java.util.ArrayList"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Uncertainty Visualisation Selection Result</title>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="style.css" type="text/css" />

<script type="text/javascript" src="tinybox.js"> </script>
<script type="text/javascript">
	function openJS() {
		alert('loaded')
	}
	function closeJS() {
		alert('closed')
	}
</script>

<link href="<%=request.getContextPath()%>/favicon.png"
	rel="shortcut icon" type="image/x-icon" />
	<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-28300786-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
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

		<h2>The most suitable visualisation methods for this selection
			are:</h2>

		<ol>
			<%
				for (VisualizationMethod visListElement : visList) {
					out.println("<li>");
					out.println("<b>Method name:</b> " + visListElement.name);
					out.println("<br>");
					out.println("<b>Description:</b> " + visListElement.description);
					out.println("</li>");
				}
			
				
			%>
						
		</ol>
		<jsp:include page="footer.jsp" />
	</div>

</body>
</html>