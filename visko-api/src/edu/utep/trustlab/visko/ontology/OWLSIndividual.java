package edu.utep.trustlab.visko.ontology;

import java.io.StringWriter;

import org.mindswap.owl.OWLIndividual;

import edu.utep.trustlab.repository.Repository;
import edu.utep.trustlab.visko.ontology.model.OWLSModel;
import edu.utep.trustlab.visko.util.OWLSRDFCleanup;

public abstract class OWLSIndividual implements ViskoIndividual {
	private String name;
	protected String uri;
	private String fullURL;
	private String fileName;
	private String baseURL;

	protected boolean isForReading;

	protected OWLSModel model;

	public OWLSIndividual(String baseURL, String name, OWLSModel owlsModel) {
		System.out
				.println("asssuming that the object will be used for writing...");

		setURI(baseURL, name);

		model = owlsModel;
		model.createOntology(fullURL);
		model.addImportsToOntology();

		setProperties();
	}

	public OWLSIndividual(String individualURI, OWLSModel owlsModel) {
		System.out
				.println("asssuming that the object will be used for reading...");
		isForReading = true;
		model = owlsModel;
		uri = individualURI;

		setProperties();

		getIndividual();
	}

	public String toString() {
		StringWriter wtr = new StringWriter();
		model.getOntology().write(wtr, model.getOntology().getURI());
		String rdfString = wtr.toString();
		rdfString = OWLSRDFCleanup.fixURIForImplementsOperator(rdfString, Repository.getRepository().getBaseURL());
		rdfString = OWLSRDFCleanup.fixURIForSupportedByToolkit(rdfString, Repository.getRepository().getBaseURL());
		return rdfString;
	}

	public String getFileName() {
		return fileName;
	}

	public String getURI() {
		return uri;
	}

	public abstract OWLIndividual getIndividual();

	protected abstract void setProperties();

	protected abstract boolean allFieldsPopulated();

	protected abstract void initializeFields();

	/********************* PRIVATE METHODS *****************************/
	private void setURI(String base, String individualName) {
		baseURL = base;
		name = individualName;
		fileName = name + ".owl";
		fullURL = baseURL + fileName;
		uri = baseURL + fileName + "#" + name;
	}
}
