package edu.utep.trustlab.visko.knowledge.ncl;


import edu.utep.trustlab.publish.Server;
import edu.utep.trustlab.visko.knowledge.NickCIServer;
import edu.utep.trustlab.visko.ontology.service.writer.ToolkitWriter;

public class Toolkits {
	public static void main(String[] args) {
		
		Server.setServer(NickCIServer.getServer());
		
		ToolkitWriter wtr = new ToolkitWriter("ncl");
		wtr.setLabel("NCAR Command Language");
		String documentURL = wtr.saveDocument();
		System.out.println(documentURL);
	}
}
