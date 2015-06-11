package clustere.pluginAction;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

import clustere.Activator;
import clustere.editors.PredictionViewInput;

import com.wuxuehong.interfaces.NewAlgorithm;
import com.wuxuehong.interfaces.PredictionPlugin;

public class PluginPredictionAction extends Action{
	private PredictionPlugin section;
    private String editorID = "CDEVA.editor4";
    private String viewID = "CDEVA.view5";
	
	public PluginPredictionAction(NewAlgorithm section) {
		this.setText("&"+section.getEvaluateNames());
		this.section = (PredictionPlugin) section;
    }
	public void run() {
		IWorkbenchPage workbenchpage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
		
		try {
			workbenchpage.showView(viewID);
		} catch (PartInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		IEditorInput editorinput = PredictionViewInput.getInstance();
		IEditorPart editor = workbenchpage.findEditor(editorinput);
		if (editor != null) {
			workbenchpage.bringToTop(editor);
		}else {
			try {
				workbenchpage.openEditor(editorinput, editorID);
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		section.getCandidateGenes();
	}
	
}
