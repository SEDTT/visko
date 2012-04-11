/*
Copyright (c) 2012, University of Texas at El Paso
All rights reserved.
Redistribution and use in source and binary forms, with or without modification, are permitted
provided that the following conditions are met:

	-Redistributions of source code must retain the above copyright notice, this list of conditions
	 and the following disclaimer.
	-Redistributions in binary form must reproduce the above copyright notice, this list of conditions
	 and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/


package edu.utep.trustlab.visko.web.context;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import edu.utep.trustlab.visko.sparql.ViskoTripleStore;
import edu.utep.trustlab.visko.web.html.Template;
import edu.utep.trustlab.visko.web.requestHandler.sparql.TDBTripleStore;

public class WebAppContext {
	public static void setContext(HttpServlet servlet) {
		setViskoSPARQLEndpointURL(servlet);
		setViskoTDBFilePath(servlet);
		setLogo(servlet);
	}
	
	public static void setContext(ServletConfig config) {
		setViskoSPARQLEndpointURL(config);
		setViskoTDBFilePath(config);
		setLogo(config);
	}

	private static void setLogo(ServletConfig config){
		String logoPath = config.getInitParameter("logo-path");
		Template.setLogoPath(logoPath);
	}
	
	private static void setViskoTDBFilePath(ServletConfig config){
		String tdbFilePath = config.getInitParameter("visko-tdb-path");
		TDBTripleStore.setTDBStoreFilePath(tdbFilePath);
		
	}
	private static void setViskoSPARQLEndpointURL(ServletConfig config) {
		String viskoEndpoint = config.getInitParameter("visko-sparql-endpoint");
		ViskoTripleStore.setEndpointURL(viskoEndpoint);
	}
}
