package s4.spring.td3.components;

import java.io.IOException;

import io.github.jeemv.springboot.vuejs.components.VueComponent;

public class ConfirmButton {

	public static void main(String[] args) {
		
		VueComponent compo = new VueComponent("m-confirm-button");
		
		try {
			
			compo.addData("dialog",false);
			compo.setProps("title","message","validatecaption","denycaption");
			compo.addProp("width", 500);
			compo.addMethod("validation", "this.$emit('validation'); this.dialog=true; ");
			compo.addMethod("cancelation", "this.$emit('cancelation'); this.dialog=false; ");

			compo.setDefaultTemplateFile();
			compo.createFile(false);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
