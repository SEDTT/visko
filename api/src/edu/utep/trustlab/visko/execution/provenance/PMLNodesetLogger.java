package edu.utep.trustlab.visko.execution.provenance;
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

import java.io.StringWriter;
import java.util.Vector;

import org.inference_web.pml.v2.pmlj.*;
import org.inference_web.pml.v2.pmlp.*;
import org.inference_web.pml.v2.vocabulary.*;
import org.inference_web.pml.v2.pmlj.IWNodeSet;
import org.inference_web.pml.v2.util.PMLObjectManager;
import org.mindswap.owl.OWLValue;
import org.mindswap.owls.process.variable.Input;
import org.mindswap.query.ValueMap;

import edu.utep.trustlab.contentManagement.ContentManager;
import edu.utep.trustlab.visko.ontology.service.OWLSService;
import edu.utep.trustlab.visko.util.FileUtils;

public class PMLNodesetLogger {
	
	private Vector<IWNodeSet> serviceNodesets;
	
	private String baseURL;
	private String baseFileName = "visko-pipeline-provenance";
	private String baseNodesetNameService = "pipeline-step";
	private String baseNodesetNameParameter = "parameter-assertion";
	
	private String fileName;

	public PMLNodesetLogger() {
		serviceNodesets = new Vector<IWNodeSet>();
		
		fileName = baseFileName + "-" + FileUtils.getRandomFileName() + ".owl";
		baseURL = ContentManager.getProvenanceContentManager().getBaseURL(fileName);
	}

	public void captureProcessingStep(OWLSService service, String inDatasetURL, String outDatasetURL, ValueMap<Input, OWLValue> inputValueMap) {
		//set up nodeset for service pipeline step
		//set up rule
		IWInferenceRule ir = (IWInferenceRule) PMLObjectManager.getPMLObjectFromURI(service.getConceptualOperator().getURI());
		
		//set up inference step
		IWInferenceStep is = (IWInferenceStep) PMLObjectManager.createPMLObject(PMLJ.InferenceStep_lname);
		is.setHasInferenceRule(ir);
		
		//set up conclusion
		IWInformation conclusion = (IWInformation) PMLObjectManager.createPMLObject(PMLP.Information_lname);
		String formatURI = service.getConceptualOperator().getOperatesOnFormats().get(0).getURI();
		conclusion.setHasFormat(formatURI);
		conclusion.setHasURL(outDatasetURL);
		
		//set up nodeset
		String nodesetNameService = baseNodesetNameService + "-" + FileUtils.getRandomFileName();
		IWNodeSet ns = (IWNodeSet) PMLObjectManager.createPMLObject(PMLJ.NodeSet_lname);
		ns.setIdentifier(PMLObjectManager.getObjectID(baseURL + "#" + nodesetNameService));
		ns.setHasConclusion(conclusion);
		ns.addIsConsequentOf(is);
		
		for (Input var : inputValueMap.getVariables()) {			
			OWLValue value = inputValueMap.getValue(var);

			if (value.equals(inDatasetURL)) {

				//set up rule
				IWInferenceRule paramIR = (IWInferenceRule) PMLObjectManager.getPMLObjectFromURI(service.getConceptualOperator().getURI());
				
				//set up inference step
				IWInferenceStep paramIS = (IWInferenceStep) PMLObjectManager.createPMLObject(PMLJ.InferenceStep_lname);
				is.setHasInferenceRule(paramIR);
				
				//set up conclusion
				IWInformation paramConclusion = (IWInformation) PMLObjectManager.createPMLObject(PMLP.Information_lname);
				String paramFormatURI = "https://raw.github.com/nicholasdelrio/visko-rdf/master/rdf/formats/PLAIN.owl#PLAIN";
				conclusion.setHasFormat(paramFormatURI);
				conclusion.setHasURL(value.toString());
				
				//set up nodeset
				String nodesetNameParameter = baseNodesetNameParameter + "-" + FileUtils.getRandomFileName();
				IWNodeSet paramNS = (IWNodeSet) PMLObjectManager.createPMLObject(PMLJ.NodeSet_lname);
				paramNS.setIdentifier(PMLObjectManager.getObjectID(baseURL + "#" + nodesetNameParameter));
				paramNS.setHasConclusion(conclusion);
				paramNS.addIsConsequentOf(is);

				is.addAntecedent(paramNS);
			}
		}
		serviceNodesets.add(ns);
	}

	public String dumpNodesets() {

		IWInferenceStep is;
		for (int i = (serviceNodesets.size() - 1); i > 0; i--){
			is = (IWInferenceStep) serviceNodesets.get(i).getIsConsequentOf().get(0);
			is.addAntecedent(serviceNodesets.get(i - 1));
		}

		StringWriter writer = new StringWriter();
		IWNodeSet rootNodeset = serviceNodesets.get(serviceNodesets.size() - 1);
		PMLObjectManager.getOntModel(rootNodeset).write(writer, "RDF/XML-ABBREV");
		
		ContentManager manager = ContentManager.getProvenanceContentManager();
		manager.saveDocument(writer.toString(), fileName);
		
		return rootNodeset.getIdentifier().getURIString();
	}
}