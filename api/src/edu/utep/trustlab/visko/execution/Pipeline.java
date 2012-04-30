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


package edu.utep.trustlab.visko.execution;

import java.util.HashMap;
import java.util.Vector;
import org.mindswap.owls.process.Process;
import org.mindswap.owls.process.variable.Input;

import edu.utep.trustlab.visko.ontology.model.OWLSModel;
import edu.utep.trustlab.visko.ontology.model.ViskoModel;
import edu.utep.trustlab.visko.ontology.operator.Viewer;
import edu.utep.trustlab.visko.ontology.service.OWLSService;


public class Pipeline extends Vector<String> {
	private String viewer;
	private String view;
	private OWLSModel owlsLoadingModel;
	private ViskoModel viskoLoadingModel;
	private PipelineSet parentContainer;

	public Pipeline(String viewerURI, String viewURI, PipelineSet parent) {
		super();
		viskoLoadingModel = new ViskoModel();
		owlsLoadingModel = new OWLSModel();
		parentContainer = parent;
		viewer = viewerURI;
	}

	public String getViewURI(){
		return view;
	}
	
	public Viewer getViewer() {
		return new Viewer(viewer, viskoLoadingModel);
	}


	public String getViewerURI() {
		return new Viewer(viewer, viskoLoadingModel).getURI();
	}
	
	public HashMap<String, String> getParameterBindings() {
		return parentContainer.getParameterBindings();
	}

	public String getArtfifactURL() {
		return parentContainer.getArtifactURL();
	}

	public void addOWLSServiceURI(String serviceURI) {
		add(serviceURI);
	}

	public void setOWLSServiceURIs(Vector<String> serviceURIs) {
		for (String serviceImplURI : serviceURIs) {
			addOWLSServiceURI(serviceImplURI);
		}
	}
	
	public OWLSService getService(int i){
		return new OWLSService(get(i), owlsLoadingModel);
	}
	
	private boolean hasAllInputParameters(OWLSService service){
		Process process = service.getIndividual().getProcess();
		String parameterURI;
		String boundedValue;
		boolean allParamsBounded = true;
		for (Input input : process.getInputs()) {
			parameterURI = input.getURI().toASCIIString();
			boundedValue = getParameterBindings().get(parameterURI);
			if(boundedValue == null){
				allParamsBounded = false;
				break;
			}
		}
		return allParamsBounded;
	}

	public boolean hasAllInputParameters(){
		boolean allParametersBound = true;
		OWLSService owlsService;
		for(int i = 0; i < size(); i ++){
			owlsService = getService(i);
			if(!hasAllInputParameters(owlsService)){
				allParametersBound = false;
				break;
			}
		}
		return allParametersBound;
	}
	
	public String executePath(boolean provenance) {
		PipelineExecutor executor = new PipelineExecutor(provenance);

		String artifactURL = parentContainer.getArtifactURL();

		String visualizationURL = "NULL: Artifact to be visualized was never specified!!!";

		if (artifactURL != null) {
			// null object behavior
			if (size() == 0) {
				visualizationURL = parentContainer.getArtifactURL();
			}

			try {
				visualizationURL = executor.executeServiceChain(this,
						parentContainer.getArtifactURL());
			} catch (Exception e) {
				e.printStackTrace();
				visualizationURL = e.getMessage();
			}
		}

		return visualizationURL;
	}
}
