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


package edu.utep.trustlab.visko.sparql;

import com.hp.hpl.jena.query.*;

import edu.utep.trustlab.visko.ontology.vocabulary.Visko;
import edu.utep.trustlab.visko.ontology.vocabulary.supplemental.OWLS_Process;
import edu.utep.trustlab.visko.ontology.vocabulary.supplemental.OWLS_Service;

public class ViskoTripleStore {
	
	private static String endpointURL;
	
	public static void setEndpointURL(String url){
		endpointURL = url;
	}
	
	public static String getEndpontURL(){
		return endpointURL;
	}
	
	private ViskoSPARQLEndpoint endpoint;
	
	public ViskoTripleStore(){
		endpoint = new ViskoSPARQLEndpoint(endpointURL);
	}
	
	public static String QUERY_PREFIX = 
			  "PREFIX viskoV: <" + Visko.CORE_VISKO_V + "#> "
			+ "PREFIX viskoO: <" + Visko.CORE_VISKO_O + "#> "
			+ "PREFIX viskoS: <" + Visko.CORE_VISKO_S + "#> "
			+ "PREFIX owlsService: <" + OWLS_Service.ONTOLOGY_OWLS_SERVICE_URI + "#> "
			+ "PREFIX owlsProcess: <" + OWLS_Process.ONTOLOGY_OWLS_PROCESS_URI + "#> "
			+ "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
			+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
			+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "
			+ "PREFIX pmlp: <http://inference-web.org/2.0/pml-provenance.owl#> "
			+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ";

	public ResultSet getParameterBindingsQuery(String typeURI) {
		typeURI = "\"" + typeURI + "\"^^xsd:anyURI";
		String stringQuery = QUERY_PREFIX + "SELECT ?param ?value " + "WHERE {"
				+ "?profile viskoS:profiles " + typeURI + " . "
				+ "?profile viskoS:declaresBindings ?binding ."
				+ "?binding owlsProcess:toParam ?param . "
				+ "?binding owlsProcess:valueData ?value . " + "}";

		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getProfiles(String typeURI) {
		typeURI = "\"" + typeURI + "\"^^xsd:anyURI";

		String stringQuery = QUERY_PREFIX + "SELECT ?profile " + "WHERE {"
				+ "?profile viskoS:profiles " + typeURI + " . " + "}";

		return endpoint.executeQuery(stringQuery);
	}
	
	public ResultSet getInformationSubclasses(){
		String query = QUERY_PREFIX + "SELECT ?subclass ?label WHERE {?subclass rdfs:subClassOf pmlp:Information . ?subclass rdfs:label ?label . } ORDER BY ?label";
		return endpoint.executeQuery(query);
	}

	public ResultSet getFormatsSupportedByViewer(String viewerURI) {
		viewerURI = "<" + viewerURI + ">";

		String stringQuery = QUERY_PREFIX + "SELECT ?format " + "WHERE {"
				+ viewerURI + " viskoO:hasInputFormat ?format . " + "}";

		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getFormats() {
		String stringQuery = QUERY_PREFIX + "SELECT ?format " + "WHERE {?format a pmlp:Format . }";
		return endpoint.executeQuery(stringQuery);
	}
	
	public ResultSet getAllParameters() {
		String stringQuery = QUERY_PREFIX + "SELECT ?param " + "WHERE { "
				+ "?service rdf:type owlsService:Service . "
				+ "?service owlsService:describedBy ?process ."
				+ "?process owlsProcess:hasInput ?param ." + "} ORDER BY ?param";
		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getOWLSServices() {
		String stringQuery = QUERY_PREFIX + "SELECT ?service " + "WHERE { "
				+ "?service rdf:type owlsService:Service . " + "}";

		return endpoint.executeQuery(stringQuery);

	}

	public ResultSet getDataTransformers() {
		String stringQuery = QUERY_PREFIX + "SELECT ?operator ?lbl "
				+ "WHERE { " + "?operator rdf:type viskoO:DataTransformer . "
				+ "?operator rdfs:label ?lbl . " + "}";

		return endpoint.executeQuery(stringQuery);
	}
	
	public ResultSet getDataFilters() {
		String stringQuery = QUERY_PREFIX + "SELECT ?operator ?lbl "
				+ "WHERE { " + "?operator rdf:type viskoO:DataFilter . "
				+ "?operator rdfs:label ?lbl . " + "}";

		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getFormatConverters() {
		String stringQuery = QUERY_PREFIX + "SELECT ?operator ?lbl "
				+ "WHERE { " + "?operator rdf:type viskoO:FormatConverter . "
				+ "?operator rdfs:label ?lbl . " + "}";

		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getOperatorInformation() {
		String stringQuery = QUERY_PREFIX
				+ "SELECT ?operator ?lbl ?input ?output " + "WHERE { "
				+ "?operator rdf:type viskoO:Transformer . "
				+ "?operator rdfs:label ?lbl . "
				+ "?operator viskoO:hasInputFormat ?input. "
				+ "?operator viskoO:hasOutputFormat ?output. " + "}";
		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getToolkits() {
		String stringQuery = QUERY_PREFIX + "SELECT ?toolkit " + "WHERE { "
				+ "?toolkit rdf:type viskoS:Toolkit . " + "}";

		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getOperatedOnFormats(String viskoOperatorURI) {
		viskoOperatorURI = "<" + viskoOperatorURI + ">";

		String stringQuery = QUERY_PREFIX + "SELECT ?inputFormat " + "WHERE { "
				+ viskoOperatorURI + " viskoO:operatesOn ?inputFormat . " + "}";
		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getOperatedOnFormats() {
		String stringQuery = QUERY_PREFIX + "SELECT DISTINCT ?inputFormat "
				+ "WHERE { " + "?operator viskoO:operatesOn ?inputFormat . "
				+ "}"
				+ "ORDER BY ?inputFormat";
		return endpoint.executeQuery(stringQuery);
	}
	
	public ResultSet getOperatedOnDataTypes() {
		String stringQuery = QUERY_PREFIX + "SELECT DISTINCT ?dataType "
				+ "WHERE { " + "?operator viskoO:operatesOnDataType ?dataType . "
				+ "}"
				+ "ORDER BY ?dataType";
		return endpoint.executeQuery(stringQuery);
	}

	public boolean canBeVisualizedWithViewerSet(String formatURI,
			String viewerSetURI) {
		formatURI = "<" + formatURI + ">";
		viewerSetURI = "<" + viewerSetURI + ">";

		String stringQuery = QUERY_PREFIX + "ASK " + "WHERE {{ " + "?viewer"
				+ " viskoO:partOfViewerSet " + viewerSetURI + ". "
				+ "?viewer viskoO:operatesOn ?format . " + formatURI
				+ " viskoO:canBeTransformedToTransitive ?format . } UNION { "
				+ "?viewer" + " viskoO:partOfViewerSet " + viewerSetURI + ". "
				+ "?viewer viskoO:operatesOn ?format . " + formatURI
				+ " viskoO:canBeTransformedTo ?format . } UNION { " + "?viewer"
				+ " viskoO:partOfViewerSet " + viewerSetURI + ". "
				+ "?viewer viskoO:operatesOn ?format . " + "}}";

		return endpoint.executeAskQuery(stringQuery);
	}

	public boolean isAlreadyVisualizableWithViewerSet(String formatURI,
			String viewerSetURI) {

		formatURI = "<" + formatURI + ">";
		viewerSetURI = "<" + viewerSetURI + ">";

		String stringQuery = QUERY_PREFIX + "ASK " + "WHERE { " + "?viewer"
				+ " viskoO:partOfViewerSet " + viewerSetURI + ". "
				+ "?viewer viskoO:operatesOn " + formatURI + " . }";
		return endpoint.executeAskQuery(stringQuery);
	}

	public boolean isDataTypeAlreadyVisualizableWithViewerSet(String dataTypeURI, String viewerSetURI) {

		dataTypeURI = "<" + dataTypeURI + ">";
		viewerSetURI = "<" + viewerSetURI + ">";

		String stringQuery = QUERY_PREFIX + "ASK " + "WHERE { " + "?viewer"
				+ " viskoO:partOfViewerSet " + viewerSetURI + ". "
				+ "?viewer viskoO:operatesOnDataType " + dataTypeURI + " . }";
		return endpoint.executeAskQuery(stringQuery);
	}

	public boolean isSubClassOfDataTypeAlreadyVisualizableWithViewerSet(String dataTypeURI, String viewerSetURI) {

		dataTypeURI = "<" + dataTypeURI + ">";
		viewerSetURI = "<" + viewerSetURI + ">";

		String stringQuery = QUERY_PREFIX + "ASK " + "WHERE { " + "?viewer"
				+ " viskoO:partOfViewerSet " + viewerSetURI + ". "
				+ "?viewer viskoO:operatesOnDataType ?superClassDataType . "
				+ dataTypeURI + " rdfs:subClassOf ?superClassDataType . }";
		return endpoint.executeAskQuery(stringQuery);
	}
	
	public boolean isAlreadyVisualizable(String formatURI) {

		formatURI = "<" + formatURI + ">";

		String stringQuery = QUERY_PREFIX + "ASK " + "WHERE { "
				+ "?viewer a viskoO:Viewer. " + "?viewer viskoO:operatesOn "
				+ formatURI + " . }";
		return endpoint.executeAskQuery(stringQuery);
	}

	public ResultSet getViewerSetsOfViewer(String viewerURI) {
		viewerURI = "<" + viewerURI + ">";

		String stringQuery = QUERY_PREFIX + "SELECT ?viewerSet " + "WHERE { " +
				viewerURI + " viskoO:partOfViewerSet ?viewerSet .}";

		return endpoint.executeQuery(stringQuery);
	}
	
	public ResultSet getTargetViewerOfViewerSet(String formatURI,
			String viewerSetURI) {

		formatURI = "<" + formatURI + ">";
		viewerSetURI = "<" + viewerSetURI + ">";

		String stringQuery = QUERY_PREFIX + "SELECT ?viewer " + "WHERE { "
				+ "?viewer" + " viskoO:partOfViewerSet " + viewerSetURI + ". "
				+ "?viewer viskoO:operatesOn " + formatURI + " . }";

		return endpoint.executeQuery(stringQuery);
	}

	public boolean isTransformer(String uri) {
		uri = "<" + uri + ">";

		String stringQuery = QUERY_PREFIX
				+ "ASK "
				+ "WHERE { "
				+ uri
				+ " rdf:type viskoO:Transformer> . }";

		return endpoint.executeAskQuery(stringQuery);
	}

	public boolean isMapper(String uri) {
		uri = "<" + uri + ">";

		String stringQuery = QUERY_PREFIX
				+ "ASK "
				+ "WHERE { "
				+ uri
				+ " rdf:type viskoO:Mapper . }";

		return endpoint.executeAskQuery(stringQuery);
	}

	public ResultSet getViewGeneratedByMapper(String mapperURI) {
		String uri = "<" + mapperURI + ">";

		String stringQuery = QUERY_PREFIX
				+ "SELECT ?view "
				+ "WHERE { "
				+ uri
				+ " viskoO:mapsTo ?view . }";

		return endpoint.executeQuery(stringQuery);
	}
	
	public boolean canBeVisualizedWithTargetFormat(String formatURI,
			String targetFormatURI) {

		formatURI = "<" + formatURI + ">";
		targetFormatURI = "<" + targetFormatURI + ">";

		String stringQuery = QUERY_PREFIX + "ASK " + "WHERE {{ " + formatURI
				+ " viskoO:canBeTransformedToTransitive " + targetFormatURI
				+ " . " + "      } UNION {" + formatURI
				+ " viskoO:canBeTransformedTo " + targetFormatURI + " . "
				+ "}}";

		return endpoint.executeAskQuery(stringQuery);
	}

	public ResultSet getTargetFormatsFromViewerSet(String formatURI,
			String viewerSetURI) {
		formatURI = "<" + formatURI + ">";
		viewerSetURI = "<" + viewerSetURI + ">";

		String stringQuery = QUERY_PREFIX + "SELECT ?format ?viewer "
				+ "WHERE {{ " + "?viewer viskoO:partOfViewerSet "
				+ viewerSetURI + " . " + "?viewer viskoO:operatesOn ?format . "
				+ formatURI + " viskoO:canBeTransformedToTransitive ?format . "
				+ "} UNION { " + "?viewer viskoO:partOfViewerSet "
				+ viewerSetURI + " . " + "?viewer viskoO:operatesOn ?format . "
				+ formatURI + "viskoO:canBeTransformedTo ?format . " + "}}";

		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getNextFormats(String formatURI, String targetFormat) {
		formatURI = "<" + formatURI + ">";
		targetFormat = "<" + targetFormat + ">";

		String stringQuery = QUERY_PREFIX + "SELECT ?format " + "WHERE {{ "
				+ formatURI + " viskoO:canBeTransformedTo ?format . "
				+ "} UNION {" + formatURI
				+ " viskoO:canBeTransformedTo ?format . }}";

		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getTransformers(String inFormatURI, String outFormatURI) {
		inFormatURI = "<" + inFormatURI + ">";
		outFormatURI = "<" + outFormatURI + ">";

		String stringQuery = QUERY_PREFIX + "SELECT ?transformer " + "WHERE { "
				+ "?transformer viskoO:operatesOn " + inFormatURI + " . "
				+ "?transformer viskoO:transformsTo " + outFormatURI + " . "
				+ " }";

		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getOWLSServiceImplementationsURIs(String operatorURI) {
		operatorURI = "<" + operatorURI + ">";

		String stringQuery = QUERY_PREFIX + "SELECT ?opImpl " + "WHERE {"
				+ "?opImpl viskoS:implementsOperator " + operatorURI + " . "
				+ "}";
		return endpoint.executeQuery(stringQuery);
	}
	
	public ResultSet getImplemenationOf(String serviceURI) {
		serviceURI = "<" + serviceURI + ">";

		String stringQuery = QUERY_PREFIX + "SELECT ?operator " + "WHERE {"
				+ serviceURI + " viskoS:implementsOperator ?operator . "
				+ "}";
		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getOutputFormatsOfTransformer(String transformerURI) {
		transformerURI = "<" + transformerURI + ">";

		String stringQuery = QUERY_PREFIX + "SELECT ?outputFormat "
				+ "WHERE { " + transformerURI
				+ " viskoO:transformsTo ?outputFormat . " + "      }";

		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getTransformedFormats() {
		String stringQuery = QUERY_PREFIX
				+ "SELECT ?format ?outputOf ?inputTo " + "WHERE {"
				+ "?outputOf viskoO:transformsTo ?format . "
				+ "?inputTo viskoO:operatesOn ?format . " + "}";
		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getViews() {
		String stringQuery = QUERY_PREFIX + "SELECT ?view " + "WHERE {"
				+ "?view rdf:type viskoV:View . " + "} ORDER BY ?view";
		return endpoint.executeQuery(stringQuery);
	}

	public boolean canOperateOnDataType(String operatorURI, String dataTypeURI){
		operatorURI = "<" + operatorURI + ">";
		dataTypeURI = "<" + dataTypeURI + ">";
		
		String stringQuery = QUERY_PREFIX +
				"ASK WHERE {" + operatorURI + " viskoO:operatesOnDataType " + dataTypeURI + " . }";
		
		return endpoint.executeAskQuery(stringQuery);
	}

	public ResultSet getTransformedToDataType(String operatorURI){
		operatorURI = "<" + operatorURI + ">";
		
		String stringQuery = QUERY_PREFIX +
				"SELECT ?dataType WHERE { " + operatorURI + " viskoO:transformsToDataType ?dataType . }";
		
		return endpoint.executeQuery(stringQuery);
	}
	
	public boolean canOperateOnSuperTypeOfDataType(String operatorURI, String dataTypeURI){
		operatorURI = "<" + operatorURI + ">";
		dataTypeURI = "<" + dataTypeURI + ">";
		
		String stringQuery = QUERY_PREFIX +
				"ASK WHERE {" + operatorURI + " viskoO:operatesOnDataType ?superType . "
				+ dataTypeURI + " rdfs:subClassOf ?superType . }";
		
		return endpoint.executeAskQuery(stringQuery);
	}
	
	public ResultSet getViewsGeneratedFrom(String mapperURI) {
		mapperURI = "<" + mapperURI + ">";
		String stringQuery = QUERY_PREFIX + "SELECT ?view " + "WHERE {"
				+ mapperURI + " viskoO:mapsTo ?view . " + "}";

		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getViewerSets() {
		String stringQuery = QUERY_PREFIX + "SELECT ?viewerSet " + "WHERE {"
				+ "      ?viewerSet rdf:type viskoO:ViewerSet . " + " } ORDER BY ?viewerSet";

		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getViewersOfViewerSet(String viewerSetURI) {
		viewerSetURI = "<" + viewerSetURI + ">";
		String stringQuery = QUERY_PREFIX + "SELECT ?viewer " + "WHERE {"
				+ "      ?viewer viskoO:partOfViewerSet " + viewerSetURI
				+ " . " + "      }";

		return endpoint.executeQuery(stringQuery);
	}
	
	public ResultSet getEquivalentSADI(String owlsServiceURI) {
		owlsServiceURI = "<" + owlsServiceURI + ">";
		String stringQuery = QUERY_PREFIX + "SELECT ?sadiService " 
		+ "WHERE {" + owlsServiceURI + " <https://raw.github.com/nicholasdelrio/visko-rdf/master/rdf/ontology/visko-service.owl#hasEquivalentSADI> ?sadiService . }";

		return endpoint.executeQuery(stringQuery);
	}


	public ResultSet getMappers() {
		String stringQuery = QUERY_PREFIX + "SELECT ?mapper " + "WHERE {"
				+ "      ?mapper rdf:type viskoO:Mapper . " + "      }";

		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getViewers() {
		String stringQuery = QUERY_PREFIX + "SELECT ?viewer " + "WHERE {"
				+ "      ?viewer rdf:type viskoO:Viewer . " + "      }";

		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getExtractedProfile(String typeURI, String formatURI) {
		String dataTypeURI = "\"" + typeURI + "\"^^xsd:anyURI";
		String dataFormatURI = "<" + formatURI + ">";

		String stringQuery = QUERY_PREFIX + "SELECT ?profile " + "WHERE {"
				+ "?extractor viskoS:extractsFromDataOfType " + dataTypeURI
				+ " . " + "?extractor viskoS:extractsFromFormat "
				+ dataFormatURI + " . "
				+ "?extractor viskoS:createsProfile ?profile . " + "}";

		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getExtractorService(String typeURI, String formatURI) {
		String dataTypeURI = "\"" + typeURI + "\"^^xsd:anyURI";
		String dataFormatURI = "<" + formatURI + ">";

		String stringQuery = QUERY_PREFIX + "SELECT ?service " + "WHERE {"
				+ "?extractor viskoS:extractsFromDataOfType " + dataTypeURI
				+ " . " + "?extractor viskoS:extractsFromFormat "
				+ dataFormatURI + " . "
				+ "?service viskoS:implementsExtractor ?extractor . " + "}";

		return endpoint.executeQuery(stringQuery);
	}

	public ResultSet getParameterBindings(String typeURI) {
		typeURI = "\"" + typeURI + "\"^^xsd:anyURI";
		String stringQuery = QUERY_PREFIX + "SELECT ?param ?value " + "WHERE {"
				+ "?profile viskoS:profiles " + typeURI + " . "
				+ "?profile viskoS:declaresBindings ?binding ."
				+ "?binding owlsProcess:toParam ?param . "
				+ "?binding owlsProcess:valueData ?value . " + "}";

		return endpoint.executeQuery(stringQuery);
	}
	
	public ResultSet getInputParameters(String serviceURI) {
		serviceURI = "<" + serviceURI + ">";
		String stringQuery = QUERY_PREFIX + "SELECT ?parameter "
				+ "WHERE {"
				+ serviceURI + " viskoS:supportedByOWLSService ?owlsService . "
				+ "?owlsService owlsService:describedBy ?process . "
				+ "?process owlsProcess:hasInput ?parameter . " + "}";

		return endpoint.executeQuery(stringQuery);
	}
	
	public ResultSet submitQuery(String query){
		return endpoint.executeQuery(query);
	}
}
