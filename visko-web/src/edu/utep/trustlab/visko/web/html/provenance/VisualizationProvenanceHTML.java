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
 * package edu.utep.trustlab.visko.web.html.provenance;
 * 
 * import java.util.List; import java.util.Vector;
 * 
 * import org.inference_web.pml.v2.pmlj.IWInferenceStep; import
 * org.inference_web.pml.v2.pmlj.IWNodeSet; import
 * org.inference_web.pml.v2.util.PMLObjectManager;
 * 
 * import edu.utep.trustlab.visko.ontology.vocabulary.ViskoO; import
 * edu.utep.trustlab.visko.util.ResultSetToVector; import
 * edu.utep.trustlab.visko.web.context.ViskoContext;
 * 
 * 
 * import com.hp.hpl.jena.ontology.OntModel; import
 * com.hp.hpl.jena.ontology.OntModelSpec; import com.hp.hpl.jena.query.Query;
 * import com.hp.hpl.jena.query.QueryExecution; import
 * com.hp.hpl.jena.query.QueryExecutionFactory; import
 * com.hp.hpl.jena.query.QueryFactory; import com.hp.hpl.jena.query.ResultSet;
 * import com.hp.hpl.jena.rdf.model.Model; import
 * com.hp.hpl.jena.rdf.model.ModelFactory; import
 * com.hp.hpl.jena.tdb.TDBFactory;
 * 
 * public class VisualizationProvenanceHTML {
 * 
 * private static final String PARAMETER_ONTOLOGY_URI =
 * "http://rio.cs.utep.edu/ciserver/ciprojects/wdo/Visualization-Parameters";
 * private static final int MAX_COMMENT_CHARS = 120;
 * 
 * private IWNodeSet rootNodeset; private Vector<IWNodeSet> visNodesets;
 * 
 * public static void main(String[] args) { String test1 =
 * "http://rio.cs.utep.edu/ciserver/ciprojects/pmlj/visko_013856485257477014.owl#pipelineStep_00404611553222175"
 * ; //String test2 =
 * "http://rio.cs.utep.edu/ciserver/ciprojects/pmlj/visko_08851426230154651.owl#pipelineStep_0023208395479532018"
 * ; //String test3 =
 * "http://rio.cs.utep.edu/ciserver/ciprojects/pmlj/visko_09715614114341692.owl#pipelineStep_0818297541947945"
 * ;
 * 
 * VisualizationProvenanceHTML view = new VisualizationProvenanceHTML(test1);
 * 
 * System.out.println(view.getPlotHTML());
 * System.out.println(view.getParameterTable()); }
 * 
 * public VisualizationProvenanceHTML(String nodesetURI) { Model model =
 * getModel(nodesetURI); Vector<String> visNodesetURIs =
 * getNodesetURIsAssociatedWithVisualization(model);
 * 
 * visNodesets = new Vector<IWNodeSet>(); setRootNodeset(nodesetURI);
 * setMapperNodeset(visNodesetURIs); }
 * 
 * private void setRootNodeset(String rootNodesetURI) {rootNodeset =
 * PMLObjectManager.getNodeSet(rootNodesetURI, 100000);}
 * 
 * private void setMapperNodeset(Vector<String> nodesetURIs) { for(String
 * nodesetURI : nodesetURIs)
 * visNodesets.add(PMLObjectManager.getNodeSet(nodesetURI, 100000)); }
 * 
 * private Model getModel(String nodesetURI) { Model tripleStoreModel =
 * TDBFactory.createModel(ViskoContext.VISKO_TRIPLE_STORE_LOCATION);
 * System.out.println("statements from vis provenance html: " +
 * tripleStoreModel.size()); OntModel model =
 * ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
 * 
 * model.add(tripleStoreModel); tripleStoreModel.close();
 * 
 * model.read(ViskoO.ONTOLOGY_VISKO_O_URI); model.read(PARAMETER_ONTOLOGY_URI);
 * model.read(nodesetURI);
 * 
 * return model; }
 * 
 * private Vector<String> getNodesetURIsAssociatedWithVisualization(Model
 * inferredModel) { String mapperClassURI = "<" + ViskoO.CLASS_URI_MAPPER + ">";
 * String transfClassURI = "<" + ViskoO.CLASS_URI_TRANSFORMER + ">"; String
 * stringQuery = QUERY_PREFIX + "SELECT ?nodeset " + "WHERE {{" +
 * "?inferRule rdf:type " + mapperClassURI + " ." +
 * "?inferStep pmlj:hasInferenceRule ?inferRule ." +
 * "?nodeset pmlj:isConsequentOf ?inferStep .}" + "UNION {" +
 * "?inferRule rdf:type " + transfClassURI + " ." +
 * "?inferStep pmlj:hasInferenceRule ?inferRule ." +
 * "?nodeset pmlj:isConsequentOf ?inferStep ." + "}}";
 * 
 * Query query = QueryFactory.create(stringQuery); QueryExecution qe =
 * QueryExecutionFactory.create(query, inferredModel); ResultSet results =
 * qe.execSelect();
 * 
 * Vector<String> nodesetURIs =
 * ResultSetToVector.getVectorFromResultSet(results, "nodeset"); qe.close();
 * return nodesetURIs; }
 * 
 * public String getParameterTable() { String html =
 * "<table border=1><tr><td><b>Parameter</b></td><td><b>Argument</b></td><td><b>Comment</b></td></tr>"
 * ;
 * 
 * for(IWNodeSet nodeset : visNodesets) {
 * 
 * List inferenceSteps = nodeset.getIsConsequentOf(); IWNodeSet antecedent; List
 * antecedents =
 * ((IWInferenceStep)inferenceSteps.get(0)).getHasAntecedentList();
 * 
 * if(antecedents != null) { for(int i = 0; i < antecedents.size(); i ++) {
 * antecedent = (IWNodeSet)antecedents.get(i); String value =
 * antecedent.getHasConclusion().getHasRawString();
 * 
 * if(value != null) { value = value.replace(",", "<br>");
 * 
 * String parameterURI =
 * antecedent.getHasConclusion().getOntologyClass().getURI();
 * 
 * String parameterLabel =
 * antecedent.getHasConclusion().getOntologyClass().getLabel(null); String
 * comment = antecedent.getHasConclusion().getOntologyClass().getComment(null);
 * 
 * if(comment.length() > MAX_COMMENT_CHARS) comment = comment.substring(0,
 * MAX_COMMENT_CHARS) + "...";
 * 
 * html += "<tr><td><a href=\"" + parameterURI + "\">" + parameterLabel +
 * "</a></td><td>" + value + "</td><td>" + comment + "</td></tr>";
 * 
 * } } } }
 * 
 * html += "</table>";
 * 
 * return html; }
 * 
 * public String getPlotHTML() { String plotURL =
 * rootNodeset.getHasConclusion().getHasURL(); String html = "<img src=\"" +
 * plotURL + "\">"; return html; } }
 */
