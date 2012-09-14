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


package edu.utep.trustlab.visko.planning.paths;

import java.util.*;

public class OperatorPaths extends Vector<OperatorPath> {
	public boolean add(OperatorPath path) {
		if (!isExistingPath(path)) {
			return super.add(path);
		}

		return false;
	}

	private boolean isExistingPath(OperatorPath path) {
		for (OperatorPath aPath : this) {
			if (aPath.toString().equals(path.toString()))
				return true;
		}
		return false;
	}

	public void filterByView(String requiredViewURI) {
		OperatorPath operatorPath;
		
		Vector<OperatorPath> nonCompliantPaths = new Vector<OperatorPath>();
		
		for(int i = 0; i < this.size(); i ++){
			operatorPath = this.get(i);
			if(!operatorPath.generatesView(requiredViewURI))
				nonCompliantPaths.add(operatorPath);
		}
		
		this.removeAll(nonCompliantPaths);
	}
	
	public void filterByViewerSet(String viewerSetURI){
		Vector<OperatorPath> nonCompliantPaths = new Vector<OperatorPath>();

		OperatorPath operatorPath;
		for(int i = 0; i < this.size(); i ++){
			operatorPath = this.get(i);
			if(!operatorPath.outputCanBeViewedByViewerSet(viewerSetURI))
				nonCompliantPaths.add(operatorPath);
		}
		this.removeAll(nonCompliantPaths);
	}
	
	public void filterByType(String inputDataType) {
		Vector<OperatorPath> nonCompliantPaths = new Vector<OperatorPath>();

		OperatorPath operatorPath;
		for(int i = 0; i < this.size();  i++){
			operatorPath = this.get(i);
			if(!operatorPath.adheresToDataTypeRestriction(inputDataType))
				nonCompliantPaths.add(operatorPath);
		}
		
		this.removeAll(nonCompliantPaths);
	}
}
