package trustlab.visko.web.json;
import java.util.ArrayList;
import java.util.HashMap;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import org.json.*;


import trustlab.server.Server;
import trustlab.visko.knowledge.NickCIServer;
import trustlab.visko.sparql.ViskoTripleStore;
import trustlab.visko.web.context.ViskoContext;
public class InstanceBarGraphData
{	
	public static String getBarGraph()
	{
		Server.setServer(NickCIServer.getServer());
		
		ViskoTripleStore ts = new ViskoTripleStore("http://iw.cs.utep.edu:8080/joseki/visko");
		
		int paramCount = numResults(ts.getAllParameters());
		int viewerSetCount = numResults(ts.getViewerSets());
		int viewerCount = numResults(ts.getViewers());
		int transformerCount = numResults(ts.getTransformers());
		int toolkitCount = numResults(ts.getToolkits());
		int mapperCount = numResults(ts.getMappers());
		int formatCount = numResults(ts.getOperatedOnFormats());
		int serviceCount = numResults(ts.getOWLSServices());
		int viewCount = numResults(ts.getViews());
		
		JSONObject jsonGraphData = new JSONObject();
		
		try
		{
			ArrayList<JSONObject> data = new ArrayList<JSONObject>();
		
			data.add(new JSONObject().put("viskoType", "Parameters").put("count", paramCount));
			data.add(new JSONObject().put("viskoType", "Viewer Sets").put("count", viewerSetCount));
			data.add(new JSONObject().put("viskoType", "Views").put("count", viewCount));
			data.add(new JSONObject().put("viskoType", "Viewers").put("count", viewerCount));
			data.add(new JSONObject().put("viskoType", "Transformers").put("count", transformerCount));
			data.add(new JSONObject().put("viskoType", "Toolkits").put("count", toolkitCount));
			data.add(new JSONObject().put("viskoType", "Mappers").put("count", mapperCount));
			data.add(new JSONObject().put("viskoType", "Used Formats").put("count", formatCount));
			data.add(new JSONObject().put("viskoType", "Services").put("count", serviceCount));
			
			jsonGraphData.put("instanceGraphData", data);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return jsonGraphData.toString();
	}
	
	private static int numResults(ResultSet rs)
	{
		int count = 0;
		while(rs.hasNext())
		{
			count++;
			rs.next();
		}
		return count;
	}

}