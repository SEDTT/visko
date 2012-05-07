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


package edu.utep.trustlab.visko.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.utep.trustlab.visko.web.requestHandler.knowledgeBaseInfo.KnowledgeBaseInformationJSONServlet;
import edu.utep.trustlab.visko.web.requestHandler.queryExecution.ExecutePipelineServlet;
import edu.utep.trustlab.visko.web.requestHandler.queryExecution.ExecuteQueryServlet;
import edu.utep.trustlab.visko.web.requestHandler.queryExecution.GetPipelineJSONServlet;
import edu.utep.trustlab.visko.web.requestHandler.queryExecution.ShowPipelineServlet;
import edu.utep.trustlab.visko.web.requestHandler.queryExecutionService.ExecuteQueryServiceServlet;
import edu.utep.trustlab.visko.web.requestHandler.sparql.ExecuteSPARQLQueryServlet;

/**
 * Servlet implementation class ViskoServletManager
 */
public class ViskoServletManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViskoServletManager() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestType = request.getParameter("requestType");
		String content;
		
		if(requestType == null)
			content = "<html><body><p>Invalid argument specified for <b>requestType</b></body></html>";
		else if(requestType.equalsIgnoreCase("execute-pipeline"))
			content = new ExecutePipelineServlet().getHTMLPage(request, response);
		else if(requestType.equalsIgnoreCase("execute-query-service"))
			content = new ExecuteQueryServiceServlet().getXMLResults(request, response);
		else if(requestType.equalsIgnoreCase("execute-query"))
			content = new ExecuteQueryServlet().getHTMLPage(request, response);
		else if(requestType.equalsIgnoreCase("knowledge-base-info"))
			content = new KnowledgeBaseInformationJSONServlet().getJSON(request, response);
		else if(requestType.equalsIgnoreCase("get-pipeline-json"))
			content = new GetPipelineJSONServlet().getJSON(request, response);
		else if(requestType.equalsIgnoreCase("show-pipeline"))
			content = new ShowPipelineServlet().getHTMLPage(request, response);
		else if(requestType.equals("query-triple-store")){
			response.setContentType("application/sparql-results+xml ");
			content = new ExecuteSPARQLQueryServlet().getXMLResults(request, response);
		}
		else
			content = "<html><body><p>Invalid argument specified for <b>requestType</b></body></html>";
		
		response.getWriter().write(content);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}