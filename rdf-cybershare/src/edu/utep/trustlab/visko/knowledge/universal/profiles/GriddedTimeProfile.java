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


package edu.utep.trustlab.visko.knowledge.universal.profiles;

import edu.utep.trustlab.repository.Repository;
import edu.utep.trustlab.visko.ontology.service.writer.ToolkitProfileWriter;

public class GriddedTimeProfile {
	public static void create() {
		String baseURL = Repository.getRepository().getBaseURL();
		/************ profile for time ******************************/
		String dataTypeURI = "http://rio.cs.utep.edu/ciserver/ciprojects/HolesCode/HolesCodeSAW3.owl#d4-0";
		String dataTypeURI1 = "http://rio.cs.utep.edu/ciserver/ciprojects/HolesCode/HolesCodeWDO.owl#d4";
		ToolkitProfileWriter wtr1 = new ToolkitProfileWriter("timeModelProfile");
		wtr1.addDataType(dataTypeURI);
		wtr1.addDataType(dataTypeURI1);

		// for float2shortThr
		wtr1.addInputBinding(
		baseURL + "Float2ShortThrService.owl#scalingFactor",
		"1000");
		wtr1.addInputBinding(
		baseURL + "Float2ShortThrService.owl#offset",
		"0");

		// for vtkImageReader
		wtr1.addInputBinding(
		baseURL + "vtkImageDataReaderService.owl#littleEndian",
		"true");
		wtr1.addInputBinding(
		baseURL + "vtkImageDataReaderService.owl#dim",
		"3");
		wtr1.addInputBinding(
		baseURL + "vtkImageDataReaderService.owl#dataOrigin",
		"0/0/0");
		wtr1.addInputBinding(
		baseURL + "vtkImageDataReaderService.owl#dataSpacing",
		"1/1/1");
		wtr1.addInputBinding(
		baseURL + "vtkImageDataReaderService.owl#dataExtent",
		"0/230/0/25/0/68");
		wtr1.addInputBinding(
		baseURL + "vtkImageDataReaderService.owl#numScalarComponents",
		"1");
		wtr1.addInputBinding(
		baseURL + "vtkImageDataReaderService.owl#readLowerLeft",
		"true");

		// for vtkContourFilter
		wtr1.addInputBinding(
		baseURL + "vtkContourFilterService.owl#numContours",
		"30");
		wtr1.addInputBinding(
		baseURL + "vtkContourFilterService.owl#scalarRange",
		"0.0/30315.0");

		// for vtkPolyDataMapper
		wtr1.addInputBinding(
		baseURL + "vtkPolyDataMapperService.owl#scalarRange",
		"0.0/30315.0");
		wtr1.addInputBinding(
		baseURL + "vtkPolyDataMapperService.owl#xRotation",
		"105");
		wtr1.addInputBinding(
		baseURL + "vtkPolyDataMapperService.owl#yRotation",
		"0");
		wtr1.addInputBinding(
		baseURL + "vtkPolyDataMapperService.owl#zRotation",
		"0");
		wtr1.addInputBinding(
		baseURL + "vtkPolyDataMapperService.owl#size",
		"400/300");
		wtr1.addInputBinding(
		baseURL + "vtkPolyDataMapperService.owl#backgroundColor",
		"1/1/1");
		wtr1.addInputBinding(
		baseURL + "vtkPolyDataMapperService.owl#magnification",
		"3");

		// for vtkVolume
		wtr1.addInputBinding(
		baseURL + "vtkVolumeService.owl#xRotation",
		"105");
		wtr1.addInputBinding(
		baseURL + "vtkVolumeService.owl#yRotation",
		"0");
		wtr1.addInputBinding(
		baseURL + "vtkVolumeService.owl#zRotation",
		"0");
		wtr1.addInputBinding(
		baseURL + "vtkVolumeService.owl#size",
		"400/300");
		wtr1.addInputBinding(
		baseURL + "vtkVolumeService.owl#backgroundColor",
		"1/1/1");
		wtr1.addInputBinding(
		baseURL + "vtkVolumeService.owl#magnification",
		"3");
		wtr1.addInputBinding(
		baseURL + "vtkVolumeService.owl#colorFunction",
		"0.0,0.0,0.0,0.0/1000.0,1.0,0.0,0.0/3000.0,0.0,0.0,1.0/5000.0,0.0,1.0,0.0/7000.0,0.0,0.2,0.0");
		wtr1.addInputBinding(
		baseURL + "vtkVolumeService.owl#opacityFunction",
		"20,0.0/255,0.2");

		wtr1.setSupportingToolkit(baseURL + "vtk.owl#vtk");
		String documentURL = wtr1.saveDocument();
		System.out.println(documentURL);

		}
}
