package edu.utep.trustlab.visko.web.sparql;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;

public class TDBTripleStore {
	private static String tdbStoreFilePath;

	private InfModel model;
	
	public static void setTDBStoreFilePath(String filePath){
		tdbStoreFilePath = filePath;
	}
	public TDBTripleStore(){
		Model assertedModel = TDBFactory.createModel(tdbStoreFilePath);
		model = ModelFactory.createInfModel(PelletReasonerFactory.theInstance().create(), assertedModel);
	}
	
	public ResultSet execute(String queryString){
		  Query query = QueryFactory.create(queryString) ;
		  QueryExecution qexec = QueryExecutionFactory.create(query, model);
		  return qexec.execSelect();
	}
	
	public static String toXML(ResultSet results){
		return ResultSetFormatter.asXMLString(results);
	}
}
