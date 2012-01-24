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

package org.n52.geostatistics.uvs.method;

/**
 * @author v_sena01
 * 
 */
public class VideoDemo {

    public String videoLink;

    public String tinyImageLink;

    public int iframeWidth;

    public int iframeHeight;

    public VideoDemo(String videoLink, String tinyImageLink, int iframeWidth, int iframeHeight) {
        this.videoLink = videoLink;
        this.tinyImageLink = tinyImageLink;
        this.iframeWidth = iframeWidth;
        this.iframeHeight = iframeHeight;
    }

}
