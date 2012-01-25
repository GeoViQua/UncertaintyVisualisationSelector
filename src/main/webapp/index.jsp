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

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="style.css" type="text/css" />
<script type="text/javascript" src="tinybox.js"></script>



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
				</select>
				<div class="learnMore">
					<ul>
						<li
							onclick="TINY.box.show ({iframe:'uncertaintyTypesDescription.html',boxid:'frameless',width:500,height:350, fixed:false,maskid:'bluemask',maskopacity:40,closejs:function(){closeJS ()}})">Learn
							more...</li>
					</ul>
				</div>


				<p>
					What <em>format</em> is your uncertainty data encoded in?
				</p>
				<select name="DataFormat">
					<option value="Raster">Raster</option>
					<option value="Vector">Vector</option>
				</select>
				<div class="learnMore">
					<ul>
						<li
							onclick="TINY.box.show ({iframe:'dataFormatDescription.html',boxid:'frameless',width:500,height:350,fixed:false,maskid:'bluemask',maskopacity:40,closejs:function(){closeJS ()}})">Learn
							more...</li>
					</ul>
				</div>

				<p>
					Which <em>measurement scale</em> does your uncertainty data belong
					to?
				</p>
				<select name="DataType">
					<option value="Continuous">Continuous</option>
					<option value="Categorical">Categorical</option>
				</select>
				<div class="learnMore">
					<ul>
						<li
							onclick="TINY.box.show ({iframe:'dataTypeDescription.html',boxid:'frameless',width:500,height:350,fixed:false,maskid:'bluemask',maskopacity:40,closejs:function(){closeJS ()}})">Learn
							more...</li>
					</ul>
				</div>


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
				</select>
				<div class="learnMore">
					<ul>
						<li
							onclick="TINY.box.show ({iframe:'domainDescription.html',boxid:'frameless',width:500,height:350,fixed:false,maskid:'bluemask',maskopacity:40,closejs:function(){closeJS ()}})">Learn
							more...</li>
					</ul>

				</div>

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
					href="mailto:hansi.senaratne@uni-muenster.de">hansi.senaratne@uni-muenster.de</a>
			</p>



			<h2>Further Resources</h2>
			<p>
				<a href="<%=request.getContextPath()%>/doc/Poster.pdf">AGILE
					2011 - Poster</a> [winner of best poster award] and <a
					href="http://plone.itc.nl/agile_old/Conference/2011-utrecht/contents/pdf/posters/p_144.pdf">
					AGILE 2011 - Poster Abstract</a>
			</p>
			<p>
				<a
					href="https://wiki.52north.org/bin/view/Geostatistics/UncertaintyVisualisationSelector">Development
					Documentation</a> in the 52&deg;North Wiki.
			</p>



			<jsp:include page="footer.jsp" />

			<div class="center">
				<a href="http://validator.w3.org/check?uri=referer"> <img
					src="<%=request.getContextPath()%>/images/valid-xhtml11.png"
					alt="Valid XHTML 1.1" />
				</a> <a href="http://jigsaw.w3.org/css-validator/check/referer"> <img
					src="<%=request.getContextPath()%>/images/vcss.gif"
					alt="CSS is valid!" />
				</a>

				<p class="infotext">
					<em>Google Analytics Disclaimer</em><br />This website uses Google
					Analytics, a web analytics service provided by Google, Inc.
					("Google"). Google Analytics uses "cookies", which are text files
					placed on your computer, to help the website analyze how users use
					the site. The information generated by the cookie about your use of
					the website (including your IP address truncated by the last octet
					prior to its storage using the "_anonymizeIp()" method) will be
					transmitted to and stored by Google on servers in the United
					States. Google will use this information for the purpose of
					evaluating your use of the website, compiling reports on website
					activity for website operators and providing other services
					relating to website activity and internet usage. Google may also
					transfer this information to third parties where required to do so
					by law, or where such third parties process the information on
					Google's behalf. Google will not associate your IP address with any
					other data held by Google. You may refuse the use of cookies by
					selecting the appropriate settings on your browser, however please
					note that if you do this you may not be able to use the full
					functionality of this website. By using this website, you consent
					to the processing of data about you by Google in the manner and for
					the purposes set out above.
				</p>
			</div>
		</div>

	</div>
	<!-- main -->
</body>

</html>