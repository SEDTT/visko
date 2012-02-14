package edu.utep.trustlab.visko.knowledge.ghostscript;

import edu.utep.trustlab.publish.Server;
import edu.utep.trustlab.visko.knowledge.NickCIServer;
import edu.utep.trustlab.visko.ontology.service.writer.ToolkitWriter;

public class Toolkits {
	public static void main(String[] args) {
	
		String documentURL;

		Server.setServer(NickCIServer.getServer());
		
		ToolkitWriter wtr1 = new ToolkitWriter("ghostscript1");
		wtr1.setLabel("Ghostscript");
		documentURL = wtr1.saveDocument();
		System.out.println(documentURL);
	}
}
