package s4.spring.td3.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;

public class DataTable {

	public static void main(String[] args) {
		
		VueComponent compo = new VueComponent("m-data-table");
		
		try {
			
			compo.addProp("headers","Array",true);
			compo.addProp("items","Array",true);

			compo.addProp("noData", "	Aucun élément à afficher");

			compo.setDefaultTemplateFile();
			compo.createFile(false);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	
}
