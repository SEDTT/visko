package edu.utep.trustlab.visko.knowledge.universal.profiles;

import edu.utep.trustlab.visko.ontology.service.writer.ToolkitProfileWriter;

public class CoverageModelProfile {
	public static void create() {
		String documentURL;

		/************ profile for velocity ******************************/
		String dataTypeURI = "http://rio.cs.utep.edu/ciserver/ciprojects/HolesCode/HolesCodeSAW3.owl#d7-0";

		ToolkitProfileWriter wtr = new ToolkitProfileWriter("coverageModelProfile");
		wtr.addDataType(dataTypeURI);

		// for vtkImageReader
		wtr.addInputBinding("https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkImageDataReaderService.owl#littleEndian","true");
		wtr.addInputBinding("https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkImageDataReaderService.owl#dim", "3");
		wtr.addInputBinding("https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkImageDataReaderService.owl#dataOrigin", "0/0/0");
		wtr.addInputBinding("https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkImageDataReaderService.owl#dataSpacing", "1/1/1");
		wtr.addInputBinding("https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkImageDataReaderService.owl#dataExtent", "0/229/0/24/0/67");
		wtr.addInputBinding("https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkImageDataReaderService.owl#numScalarComponents", "1");
		wtr.addInputBinding("https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkImageDataReaderService.owl#readLowerLeft","true");

		// for vtkContourFilter
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkContourFilterService.owl#numContours",
		"35");
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkContourFilterService.owl#scalarRange",
		"0.0/9000.0");

		// for vtkPolyDataMapper
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkPolyDataMapperService.owl#scalarRange",
		"0.0/9000.0");
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkPolyDataMapperService.owl#xRotation",
		"105");
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkPolyDataMapperService.owl#yRotation",
		"0");
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkPolyDataMapperService.owl#zRotation",
		"0");
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkPolyDataMapperService.owl#size",
		"400/300");
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkPolyDataMapperService.owl#backgroundColor",
		"1/1/1");
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkPolyDataMapperService.owl#magnification",
		"3");

		// for vtkVolume
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkVolumeService.owl#xRotation",
		"105");
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkVolumeService.owl#yRotation",
		"0");
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkVolumeService.owl#zRotation",
		"0");
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkVolumeService.owl#size",
		"400/300");
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkVolumeService.owl#backgroundColor",
		"0/0/0");
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkVolumeService.owl#magnification",
		"3");
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkVolumeService.owl#colorFunction",
		"20,1.0,0.0,0.3/80,1.0,0.0,0.3");
		wtr.addInputBinding(
		"https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtkVolumeService.owl#opacityFunction",
		"0,0.0/40,1.0");

		wtr.setSupportingToolkit("https://raw.github.com/nicholasdelrio/visko/master/visko-rdf/vtk.owl#vtk");
		documentURL = wtr.saveDocument();
		System.out.println(documentURL);
		}
}
