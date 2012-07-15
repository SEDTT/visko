package edu.utep.trustlab.visko.web.requestHandler.execution;

import java.util.Vector;

import edu.utep.trustlab.visko.execution.PipelineExecutorJob;
import edu.utep.trustlab.visko.execution.PipelineExecutorJobStatus;

public class PipelineExecutionStatusBean{
    private int refreshRate = 2;
    private String linkToQuery = "";

    private PipelineExecutorJob job;
    
    public PipelineExecutionStatusBean(PipelineExecutorJob executorJob){
    	job = executorJob;
    }
    
    public String getMessage(){
    	String message;
    	switch(job.getJobStatus().getPipelineState()){
    		case NODATA:
    			message = job.getJobStatus().toString();
    			break;
    		case EMPTYPIPELINE:
    			message = job.getJobStatus().toString();
    			break;
    		case ERROR:
    			message = job.getJobStatus().toString();
    			break;
    		case INTERRUPTED:
    			message = job.getJobStatus().toString();
    		case COMPLETE:
    			message = getCompletedMessage();
    			break;
    		case RUNNING:
    			message = job.getJobStatus().toString();
    			break;
    		default:
    			message = job.getJobStatus().toString();
    			break;
    	}
    	
    	return message;
    }
    
    public String getCompletedMessage(){
    	String resultURL = job.getFinalResultURL();
    	String resultMessage = "";
    	
    	if(job.getProvenanceLogging()){
    		resultMessage += "<h4>Provenance Information</h4>";
    		resultMessage += "<p>PML Query URI: " + job.getPMLQueryURI();
    	}
    	
    	if(
    			resultURL.endsWith(".jpg") ||
    			resultURL.endsWith(".JPG") ||
    			resultURL.endsWith(".png") ||
    			resultURL.endsWith(".PNG") ||
    			resultURL.endsWith(".gif") ||
    			resultURL.endsWith(".GIF"))
    		resultMessage += "<img src=\"" + resultURL + "\" />";
    	
    	else if(resultURL.endsWith(".pdf") || resultURL.endsWith(".PDF"))
    		resultMessage += "<a href=\"" + resultURL + "\">Click to view PDF</a>";
    	
    	else{
    		Vector<String> viewerSets = job.getPipeline().getViewerSets();
    		resultMessage += "<h4>Result</h4>";
    		resultMessage += "<ul>";
    		resultMessage += "<li>Resultant URL: <a href=\"" + resultURL + "\">" + resultURL + "</a></li>";
    		resultMessage += "<li>Viewed Using: " + job.getPipeline().getViewerURI();
    		resultMessage += "<li>Using the ViewerSet(s)</li>";
    		resultMessage += "<ul>";
    		
    		for(String viewerSet : viewerSets)
    			resultMessage += "<li>" + viewerSet + "</li>";
    		
    		resultMessage += "</ul>";
    		resultMessage += "</ul>";
    	}
    	return resultMessage;
    }
   
    public void setLinkToQuery(){
    	linkToQuery = "<p><a href=\"ViskoServletManager?requestType=get-query\">Click to export query responsible for this visualization</a></p>";
    }
    
    public String getLinkToQuery(){
    	return linkToQuery;
    }

    public int getRefreshRate(){
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate){
        this.refreshRate = refreshRate;
    }

    public String getRefreshTag(){
    	PipelineExecutorJobStatus status = job.getJobStatus();
    	if(!status.isJobCompleted())
            return "<meta http-equiv=\"refresh\""
                   + " content=\""
                   + getRefreshRate()
                   + "\";URL=\"ViskoServletManager?requestType=execute-pipeline\">";
    	return "";
    }
}