package edu.utep.trustlab.visko.ontology.operator.writer;

import java.util.Vector;

import edu.utep.trustlab.repository.Repository;
import edu.utep.trustlab.visko.ontology.model.ViskoModel;
import edu.utep.trustlab.visko.ontology.operator.*;
import edu.utep.trustlab.visko.ontology.pmlp.Format;
import edu.utep.trustlab.visko.ontology.view.View;
import edu.utep.trustlab.visko.ontology.writer.ViskoWriter;

public class TransformerWriter extends ViskoWriter {
	private String label;
	private Vector<Format> inputFormats;
	private Format outputFormat;
	private Transformer trans;
	private String name;
	private Mapper mapper;
	private View view;
	private ViskoModel loadingModel = new ViskoModel();

	public TransformerWriter(String name) {
		trans = new Transformer(Repository.getServer().getBaseURL(), name, viskoModel);
		mapper = new Mapper(Repository.getServer().getBaseURL(), name, viskoModel);
		inputFormats = new Vector<Format>();
	}

	public void setName(String aName) {
		name = aName;
	}

	public void addInputFormat(String fmtURI) {
		Format fmt = new Format(fmtURI, loadingModel);
		inputFormats.add(fmt);
	}

	public void setMappedToView(String viewURI) {
		view = new View(viewURI, loadingModel);
	}

	public void setLabel(String lbl) {
		label = lbl;
	}

	public void setOutputFormat(String fmtURI) {
		Format fmt = new Format(fmtURI, loadingModel);
		outputFormat = fmt;
	}

	public String toRDFString() {
		if (view == null) {
			trans.setOperatesOnFormats(inputFormats);
			trans.setTransformsToFormat(outputFormat);
			trans.setLabel(label);
			trans.setName(name);

			// adds the individual to the model and returns a reference to that
			// Individual
			trans.getIndividual();
			this.viskoIndividual = trans;
		} else {
			mapper.setViewToMapTo(view);
			mapper.setOperatesOnFormats(inputFormats);
			mapper.setTransformsToFormat(outputFormat);
			mapper.setLabel(label);
			mapper.setName(name);

			// adds the individual to the model and returns a reference to that
			// Individual
			mapper.getIndividual();
			this.viskoIndividual = mapper;
		}

		String viewerRDFString = viskoIndividual.toString();
		return viewerRDFString;
	}
}
