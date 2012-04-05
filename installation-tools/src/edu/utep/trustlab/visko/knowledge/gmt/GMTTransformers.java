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


package edu.utep.trustlab.visko.knowledge.gmt;
import edu.utep.trustlab.visko.knowledge.universal.Views;
import edu.utep.trustlab.visko.ontology.operator.writer.TransformerWriter;

public class GMTTransformers {

	public static String contourer;
	public static String gridder;
	public static String plotter;
	public static String rasterer;
	public static String csv2tabular;
	
	public static void create() {

		String name;
		TransformerWriter wtr = new TransformerWriter("contourer", true);
		wtr.addInputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/ESRIGRID.owl#ESRIGRID");
		wtr.setOutputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/POSTSCRIPT.owl#POSTSCRIPT");
		name = "contour";
		wtr.setLabel(name);
		wtr.setName(name);
		wtr.setMappedToView(Views.contourLines);
		System.out.println(wtr.saveDocument());
		contourer = wtr.getURI();
		
		System.out.println(wtr);
		TransformerWriter wtr1 = new TransformerWriter("gridder", false);
		wtr1.addInputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/SPACEDELIMITEDTABULARASCII.owl#SPACEDELIMITEDTABULARASCII");
		wtr1.setOutputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/ESRIGRID.owl#ESRIGRID");
		name = "Data Gridder";
		wtr1.setLabel(name);
		wtr1.setName(name);
		System.out.println(wtr1.saveDocument());
		gridder = wtr1.getURI();
		
		TransformerWriter wtr2 = new TransformerWriter("plotter", true);
		wtr2.addInputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/SPACEDELIMITEDTABULARASCII.owl#SPACEDELIMITEDTABULARASCII");
		wtr2.setOutputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/POSTSCRIPT.owl#POSTSCRIPT");
		name = "2D plotter";
		wtr2.setLabel(name);
		wtr2.setName(name);
		wtr2.setMappedToView(Views.plot2D);
		System.out.println(wtr2.saveDocument());
		plotter = wtr2.getURI();
		
		
		TransformerWriter wtr3 = new TransformerWriter("rasterer", true);
		wtr3.addInputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/ESRIGRID.owl#ESRIGRID");
		wtr3.setOutputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/POSTSCRIPT.owl#POSTSCRIPT");
		name = "Raster Map Generator";
		wtr3.setLabel(name);
		wtr3.setName(name);
		wtr3.setMappedToView(Views.raster);
		System.out.println(wtr3.saveDocument());
		rasterer = wtr3.getURI();
		
		TransformerWriter wtr4 = new TransformerWriter("csv-to-tabular-ascii", false);
		wtr4.setOutputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/SPACEDELIMITEDTABULARASCII.owl#SPACEDELIMITEDTABULARASCII");
		wtr4.addInputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/CSV.owl#CSV");
		name = "Comma Separated Values to Tabular ASCII Format";
		wtr4.setLabel(name);
		wtr4.setName(name);
		System.out.println(wtr4.saveDocument());
		csv2tabular = wtr4.getURI();
	}
}
