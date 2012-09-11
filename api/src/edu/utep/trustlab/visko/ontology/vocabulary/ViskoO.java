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


package edu.utep.trustlab.visko.ontology.vocabulary;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class ViskoO {

	// Classes
	public static final String CLASS_URI_Operator = Visko.CORE_VISKO_O + "#Operator";
	public static final String CLASS_URI_ViewMapper = Visko.CORE_VISKO_O + "#ViewMapper";
	public static final String CLASS_URI_FormatConverter = Visko.CORE_VISKO_O + "#FormatConverter";
	public static final String CLASS_URI_DataTransformer = Visko.CORE_VISKO_O + "#DataTransformer";
	public static final String CLASS_URI_DataFilter = Visko.CORE_VISKO_O + "#DataFilter";

	public static final String CLASS_URI_Viewer = Visko.CORE_VISKO_O + "#Viewer";
	public static final String CLASS_URI_ViewerSet = Visko.CORE_VISKO_O + "#ViewerSet";

	// Object Properties
	public static final String PROPERTY_URI_hasInputFormat = Visko.CORE_VISKO_O + "#hasInputFormat";
	public static final String PROPERTY_URI_hasOutputFormat = Visko.CORE_VISKO_O + "#hasOutputFormat";
	
	public static final String PROPERTY_URI_hasInputDataType = Visko.CORE_VISKO_O + "#hasInputDataType";
	public static final String PROPERTY_URI_hasOutputDataType = Visko.CORE_VISKO_O + "#hasOutputDataType";	
	
	public static final String PROPERTY_URI_mapsToView = Visko.CORE_VISKO_O + "#mapsToView";
	public static final String PROPERTY_URI_partOfViewerSet = Visko.CORE_VISKO_O + "#partOfViewerSet";
	
	private static OntModel model;
	private static Ontology ontology;

	static {
		model = ModelFactory.createOntologyModel();
		model.read(Visko.CORE_VISKO_O);
		ontology = model.getOntology(Visko.CORE_VISKO_O);
	}

	public static OntModel getModel() {
		return model;
	}

	public static Ontology getOntology() {
		return ontology;
	}
}