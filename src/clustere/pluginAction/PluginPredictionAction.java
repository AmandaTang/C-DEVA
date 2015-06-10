package clustere.pluginAction;

import org.eclipse.jface.action.Action;

import com.wuxuehong.interfaces.NewAlgorithm;

public class PluginPredictionAction extends Action{
	private NewAlgorithm section;
    private String editorID = "CDEVA.editor4";
	
	public PluginPredictionAction(NewAlgorithm section) {
		this.setText("&"+section.getEvaluateNames());
		this.section = section;
    }
	
}
