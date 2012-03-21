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


package edu.utep.trustlab.visko.knowledge.vtk;

import edu.utep.trustlab.repository.Repository;
import edu.utep.trustlab.visko.ontology.operator.writer.TransformerWriter;

public class VTKTransformers {
	public static void create() {
		String name;
		String baseURL = Repository.getRepository().getBaseURL();
		TransformerWriter wtr = new TransformerWriter("floatArrayToShortIntArray");
		wtr.addInputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/BINARYFLOATARRAYLENDIAN.owl#BINARYFLOATARRAYLENDIAN");
		wtr.setOutputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/BINARYSHORTINTARRAYLENDIAN.owl#BINARYSHORTINTARRAYLENDIAN");
		name = "Float Array To Short Int Array";
		wtr.setLabel(name);
		wtr.setName(name);
		wtr.saveDocument();

		TransformerWriter wtr1 = new TransformerWriter("shortIntArrayToGriddedData");
		wtr1.addInputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/BINARYSHORTINTARRAYLENDIAN.owl#BINARYSHORTINTARRAYLENDIAN");
		wtr1.setOutputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/VTKIMAGEDATA.owl#VTKIMAGEDATA");
		name = "Short Int to Image Data";
		wtr1.setLabel(name);
		wtr1.setName(name);
		wtr1.saveDocument();

		TransformerWriter wtr2 = new TransformerWriter("contourer3D");
		wtr2.addInputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/VTKIMAGEDATA.owl#VTKIMAGEDATA");
		wtr2.setOutputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/VTKPOLYDATA.owl#VTKPOLYDATA");
		name = "ImageData to Contours PolyData";
		wtr2.setLabel(name);
		wtr2.setName(name);
		wtr2.setMappedToView(baseURL + "iso-surfaces.owl#iso-surfaces");
		wtr2.saveDocument();

		TransformerWriter wtr3 = new TransformerWriter("contoursPolyDataToImageOperator");
		wtr3.addInputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/VTKPOLYDATA.owl#VTKPOLYDATA");
		wtr3.setOutputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/JPEG.owl#JPEG");
		name = "Contours PolyData to Image";
		wtr3.setLabel(name);
		wtr3.setName(name);
		wtr3.saveDocument();

		TransformerWriter wtr4 = new TransformerWriter("volume-generator");
		wtr4.addInputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/VTKIMAGEDATA.owl#VTKIMAGEDATA");
		wtr4.setOutputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/JPEG.owl#JPEG");
		name = "ImageData to Volume Image JPEG";
		wtr4.setLabel(name);
		wtr4.setName(name);
		wtr4.setMappedToView(baseURL + "volume.owl#volume");
		wtr4.saveDocument();

		TransformerWriter wtr5 = new TransformerWriter("intToShortIntOperator");
		wtr5.addInputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/BINARYINTARRAYLENDIAN.owl#BINARYINTARRAYLENDIAN");
		wtr5.setOutputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/BINARYSHORTINTARRAYLENDIAN.owl#BINARYSHORTINTARRAYLENDIAN");
		name = "Int Array To Short Int Array";
		wtr5.setLabel(name);
		wtr5.setName(name);
		wtr5.saveDocument();
		
		TransformerWriter wtr6 = new TransformerWriter("floatArrayToGriddedData");
		wtr6.addInputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/BINARYFLOATARRAYLENDIAN.owl#BINARYFLOATARRAYLENDIAN");
		wtr6.setOutputFormat("https://raw.github.com/nicholasdelrio/visko/master/rdf/formats/VTKIMAGEDATA.owl#VTKIMAGEDATA");
		name = "Floats to Image Data";
		wtr6.setLabel(name);
		wtr6.setName(name);
		wtr6.saveDocument();
	}
}
