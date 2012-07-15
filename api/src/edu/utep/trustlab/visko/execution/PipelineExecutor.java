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

import org.mindswap.exceptions.ExecutionException;
import org.mindswap.owl.OWLDataValue;
import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owl.OWLValue;
import org.mindswap.owls.OWLSFactory;
import org.mindswap.owls.process.Process;
import org.mindswap.owls.process.execution.ProcessExecutionEngine;
import org.mindswap.owls.process.variable.Input;
import org.mindswap.owls.process.variable.Output;
import org.mindswap.owls.service.Service;
import org.mindswap.query.ValueMap;

import edu.utep.trustlab.visko.execution.provenance.PMLNodesetLogger;
import edu.utep.trustlab.visko.execution.provenance.PMLQueryLogger;
import edu.utep.trustlab.visko.ontology.service.OWLSService;
import edu.utep.trustlab.visko.util.OWLSParameterBinder;

public class PipelineExecutor implements Runnable {	

    private PipelineExecutorJob job;
	private ProcessExecutionEngine exec;
    private Thread t;
    
    private PMLNodesetLogger traceLogger;
    private PMLQueryLogger queryLogger;
	
	public PipelineExecutor(PipelineExecutorJob pipelineJob) {
		job = pipelineJob;
		
		if(job.getProvenanceLogging()){
			traceLogger = new PMLNodesetLogger();
			queryLogger = new PMLQueryLogger();
		}

    	job.getJobStatus().setTotalServiceCount(job.getPipeline().size());
	  	exec = OWLSFactory.createExecutionEngine();	
	}

	public PipelineExecutorJob getJob(){
		return job;
	}
	
	public boolean isAlive(){
		return t.isAlive();
	}

	public void process(){
		if(!isAlive()){
			t = new Thread(this);
			t.setDaemon(true);
			t.start();
		}	
	}
	
    public void run(){    	
    	if(!job.getPipeline().hasInputData())
    		job.getJobStatus().setPipelineState(JobStatus.PipelineState.NODATA);
    	
    	else if(job.getPipeline().isEmpty())
    		job.getJobStatus().setPipelineState(JobStatus.PipelineState.EMPTYPIPELINE);
    	
    	else
    		executePipeline();
    	
    	if(job.getProvenanceLogging())
    		dumpProvenance();
    }
   
    private void executePipeline(){		
		job.getJobStatus().setPipelineState(JobStatus.PipelineState.RUNNING);
    	String resultURL = job.getPipeline().getArtifactURL();
    	
    	for(int i = 0; i < job.getPipeline().size(); i ++){
    		job.getJobStatus().setCurrentServiceIndex(i);
    		OWLSService viskoService = job.getPipeline().getService(i);
    		resultURL = executeService(viskoService, resultURL, i);	    
    	}
    	
    	job.setFinalResultURL(resultURL);
    	job.getJobStatus().setPipelineState(JobStatus.PipelineState.COMPLETE);
    }
    
    private void dumpProvenance(){
    	String pmlNodesetURI = traceLogger.dumpNodesets();
    	queryLogger.setViskoQuery(job.getPipeline().getParentPipelineSet().getQuery().toString());
    	queryLogger.addAnswer(pmlNodesetURI);
    	String pmlQueryURI = queryLogger.dumpPMLQuery();
    	
    	job.setPMLNodesetURI(pmlNodesetURI);
    	job.setPMLQueryURI(pmlQueryURI);
    }
    
    private String executeService(OWLSService owlsService, String inputDataURL, int serviceIndex){		
		OWLKnowledgeBase kb = OWLFactory.createKB();
		Service service = owlsService.getIndividual();
		Process process = service.getProcess();

		ValueMap<Input, OWLValue> inputs = OWLSParameterBinder.buildInputValueMap(process, inputDataURL, job.getPipeline().getParameterBindings(), kb);
		
		String outputDataURL = null;
		
		if (inputs != null){		
        	ValueMap<Output, OWLValue> outputs;
			try {
				outputs = exec.execute(process, inputs, kb);
	        	OWLDataValue out = (OWLDataValue) outputs.getValue(process.getOutput());
	        	outputDataURL =  out.toString();

	        	if(job.getProvenanceLogging())
	        		traceLogger.captureProcessingStep(owlsService, inputDataURL, outputDataURL, inputs);

			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return outputDataURL;
    }
}