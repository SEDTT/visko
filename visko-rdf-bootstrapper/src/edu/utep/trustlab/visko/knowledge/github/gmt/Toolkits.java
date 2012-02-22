package edu.utep.trustlab.visko.knowledge.github.gmt;

import edu.utep.trustlab.repository.Repository;
import edu.utep.trustlab.visko.knowledge.NickConfigurations;
import edu.utep.trustlab.visko.ontology.service.writer.ToolkitWriter;

public class Toolkits {
	public static void main(String[] args) {

		Repository.setRepository(NickConfigurations.getLocalFileSystem());

		String documentURL;
		ToolkitWriter wtr1 = new ToolkitWriter("gmt");
		wtr1.setLabel("Generic Mapping Tools");
		System.out.println(wtr1.toRDFString());
		documentURL = wtr1.saveDocument();
		System.out.println(documentURL);
	}
}
