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
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class ViskoS {
	public static final String ONTOLOGY_VISKO_S_URI = Visko.VISKO_S;

	// Concepts
	public static final String CLASS_URI_TOOLKIT = ONTOLOGY_VISKO_S_URI	+ "#Toolkit";
	public static final String CLASS_URI_EXTRACTOR = ONTOLOGY_VISKO_S_URI + "#Extractor";
	public static final String CLASS_URI_TOOLKIT_PROFILE = ONTOLOGY_VISKO_S_URI	+ "#ToolkitProfile";

	/*****************************************************************************************/

	// Properties
	public static final String PROPERTY_URI_EXTRACTS_FROM_FORMAT = ONTOLOGY_VISKO_S_URI + "#extractsFromFormat";
	public static final String PROPERTY_URI_IMPLEMENTS_EXTRACTOR = ONTOLOGY_VISKO_S_URI + "#implementsExtractor";
	public static final String PROPERTY_URI_IMPLEMENTS_OPERATOR = ONTOLOGY_VISKO_S_URI + "#implementsOperator";
	public static final String PROPERTY_URI_BASED_ON = ONTOLOGY_VISKO_S_URI + "#basedOn";
	public static final String PROPERTY_URI_SUPPORTED_BY = ONTOLOGY_VISKO_S_URI + "#supportedBy";
	public static final String PROPERTY_URI_DECLARES_BINDINGS = ONTOLOGY_VISKO_S_URI + "#declaresBindings";

	// Datatype Properties
	public static final String DATATYPE_PROPERTY_URI_CREATES_PROFILE = ONTOLOGY_VISKO_S_URI	+ "#createsProfile";
	public static final String DATATYPE_PROPERTY_URI_PROFILES = ONTOLOGY_VISKO_S_URI + "#profiles";
	public static final String DATATYPE_PROPERTY_URI_EXTRACTS_DATA_OFTYPE = ONTOLOGY_VISKO_S_URI + "#extractsFromDataOfType";

	// model and ontology
	private static OntModel model;
	private static Ontology ontology;

	static {
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		model.read(ONTOLOGY_VISKO_S_URI);
		ontology = model.getOntology(ONTOLOGY_VISKO_S_URI);
	}

	public static OntModel getModel() {
		return model;
	}

	public static Ontology getOntology() {
		return ontology;
	}
}
