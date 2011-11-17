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
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>Uncertainty Visualisation Selector: Select the
	appropriate uncertainty visualisation method for your needs!</title>

<script language="javascript" type="text/javascript">
<!--
	var popupWindow = null;
	function popup(mypage, myname, w, h, pos, infocus) {

		if (pos == 'random') {
			LeftPosition = (screen.width) ? Math.floor(Math.random()
					* (screen.width - w)) : 100;
			TopPosition = (screen.height) ? Math.floor(Math.random()
					* ((screen.height - h) - 75)) : 100;
		} else {
			LeftPosition = (screen.width) ? (screen.width - w) / 2 : 100;
			TopPosition = (screen.height) ? (screen.height - h) / 2 : 100;
		}
		settings = 'width='
				+ w
				+ ',height='
				+ h
				+ ',top='
				+ TopPosition
				+ ',left='
				+ LeftPosition
				+ ',scrollbars=no,location=no,directories=no,status=no,menubar=no,toolbar=no,resizable=no';
		popupWindow = window.open('', myname, settings);
		if (infocus == 'front') {
			popupWindow.focus();
			popupWindow.location = mypage;
		}
		if (infocus == 'back') {
			popupWindow.blur();
			popupWindow.location = mypage;
			popupWindow.blur();
		}

	}
// -->
</script>

<link href="styles.css" rel="stylesheet" type="text/css" />

<link href="<%=request.getContextPath()%>/favicon.png"
	rel="shortcut icon" type="image/x-icon" />

</head>
<body>

	<div id="main">

		<h1>Uncertainty Visualisation Selector</h1>
		<div id="logo">
			<img src="<%=request.getContextPath()%>/images/logo.png" width="300"
				height="300" alt="UVS Logo" />
		</div>

		<div>
			<form name="form1" id="form1" action="handleUserInput.jsp"
				method="get">
				<p>
					What <em>type of uncertainty</em> do you have in your data?
				</p>
				<select name="UncertaintyType">
					<option value="Attribute">Attribute</option>
					<option value="Positional">Positional</option>
				</select> <a
					href="javascript:popup('uncertaintyTypesDescription.html','Uncertainy types','640','480','center','front');">Learn
					more ...</a>

				<p>
					What <em>format</em> is your uncertainty data encoded in?
				</p>
				<select name="DataFormat">
					<option value="Raster">Raster</option>
					<option value="Vector">Vector</option>
				</select> <a
					href="javascript:popup('dataFormatDescription.html','Data formats','640','480','center','front');">Learn
					more ...</a>

				<p>
					Which <em>measurement scale</em> does your uncertainty data belong
					to?
				</p>
				<select name="DataType">
					<option value="Continuous">Continuous</option>
					<option value="Categorical">Categorical</option>
				</select> <a
					href="javascript:popup('dataTypeDescription.html','Data types','640','480','center','front');">Learn
					more ...</a>

				<p>
					Which one of the following <em>domains</em> do you belong to?
					Please check one that applies.
				</p>
				<select name="domain">
					<option value="None">None</option>
					<option value="Map Visualisation">Map Visualisation</option>
					<option value="Urban Planning">Urban Planning</option>
					<option value="Decision Support">Decision Support</option>
					<option value="GIS">GIS</option>
					<option value="Statistics">Statistics</option>
					<option value="Other">Other</option>
				</select> <a
					href="javascript:popup('domainDescription.html','Domains','640','480','center','front');">Learn
					more ...</a>

				<p>
					<input name="submitButton" type="submit" value="Submit" />
				</p>
			</form>

			<h2>About</h2>
			<p>This research was carried out to assess the usability of
				selected spatio-temporal uncertainty visualisation methods. These
				selected methods were priorly categorised according to a set of
				parameters (supported uncertainty type, data type and data format)
				that are believed to be the most influential for a successful
				visualisation. Basing on the hypothesis that usability not only
				varies between different uncertainty visualisation methods, but also
				between different user groups who makes use of these methods, an
				online survey was conducted on 81 participants from different
				domains to assess which uncertainty visualisation method(s) suited
				best for each domain user. A poster and its abstract that was done
				on this research can be found on the links below. A full review of
				this research can be expected in the near future.</p>
			<p>
				For further inquiries please drop an email to <a
					href="mailto:hansi.senaratne@uni-muenster.de">hansi.senaratne@uni-muenster.de</a>.
			</p>


			<h2>Further Resources</h2>
			<p>
				<a href="<%=request.getContextPath()%>/doc/Poster.pdf">AGILE
					2011 - Poster</a> [winner of best poster award] and <a
					href="http://plone.itc.nl/agile_old/Conference/2011-utrecht/contents/pdf/posters/p_144.pdf">
					AGILE 2011 - Poster Abstract</a>
			</p>

		</div>

		<jsp:include page="footer.jsp" />

		<div class="center">
			<a href="http://validator.w3.org/check?uri=referer"> <img
				src="<%=request.getContextPath()%>/images/valid-xhtml11.png"
				alt="Valid XHTML 1.1" />
			</a> <a href="http://jigsaw.w3.org/css-validator/check/referer"> <img
				src="<%=request.getContextPath()%>/images/vcss.gif"
				alt="CSS is valid!" />
			</a>
		</div>

	</div>

</body>

</html>