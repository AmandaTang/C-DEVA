package clustere.views;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.part.ViewPart;

public class PredictionView extends ViewPart{

	private static Table geneTable;
	private static Composite mainComposite;
	
	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		SashForm sashForm = new SashForm(parent,SWT.HORIZONTAL);
		mainComposite = new Composite(sashForm,SWT.BORDER);
		geneTable = new Table(mainComposite, SWT.FULL_SELECTION);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
