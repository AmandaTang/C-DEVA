package clustere.editors;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColorCellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import clustere.editors.EvaluationView.Controllor1;
import clustere.editors.EvaluationView.Controllor2;
import clustere.tableViewer.ColorContentProvider;
import clustere.tableViewer.ColorLabelProvider;
import clustere.tableViewer.ColorTableViewerCellModifier;

import com.wuxuehong.bean.AlgorithmColor;
import com.wuxuehong.bean.Paramater;
import com.wuxuehong.interfaces.NewAlgorithm;
import com.wuxuehong.interfaces.PredictionPlugin;

public class PredictionEditor extends EditorPart {

	public static final String NAME = "disease genes predition";
	private static Combo combo;
	private static Group group2 ;
	private static Composite operateComposite;
	private static Composite mainComposite;
	private static Label label;
	
	public static PredictionPlugin section = null;
	public static List<AlgorithmColor> ac = new ArrayList<AlgorithmColor>();
	public static Vector<String> algorithms = new Vector<String>();
	public static Vector<Button> buttonlist = new Vector<Button>();
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// TODO Auto-generated method stub
		setSite(site);
        setInput(input);
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		SashForm sashForm = new SashForm(parent,SWT.HORIZONTAL);
		mainComposite = new Composite(sashForm,SWT.BORDER);
		Composite controlComposite = new Composite(sashForm,SWT.BORDER);
		sashForm.setWeights(new int[]{2,1});
		mainComposite.setLayout(new GridLayout());
		controlComposite.setLayout(new GridLayout());
		
		label = new Label(mainComposite,SWT.BORDER|SWT.CENTER);
		label.setFont(new Font(parent.getDisplay(), "Arial", 10, SWT.BOLD
				| SWT.ITALIC));
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		operateComposite = new Composite(mainComposite,SWT.BORDER|SWT.DOUBLE_BUFFERED);
		operateComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Group group1 = new Group(controlComposite,SWT.NONE);
		group1.setText("Choose Algorithm");
		group1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		group1.setLayout(new GridLayout());
		combo = new Combo(group1,SWT.READ_ONLY);
		Button button1 = new Button(group1,SWT.NONE);
		button1.setText("Confirm");
		button1.addSelectionListener(new Controllor1());
		
		group2 = new Group(controlComposite,SWT.NONE);
		group2.setText("Compare");
		group2.setLayout(new GridLayout());
		group2.setLayoutData(new GridData(GridData.FILL_BOTH));
		Button button2 = new Button(group2,SWT.NONE);
		button2.setText("Begin Compare");
		button2.addSelectionListener(new Controllor2());
		
		initCombo();
		initGroup();
	}
	
	public void initCombo(){
		combo.removeAll();
		for(int i=0;i<algorithms.size();i++){
			combo.add(algorithms.get(i));
			combo.setText(algorithms.get(0));
		}
	}
	public void initGroup(){
		for(int i=0;i<algorithms.size();i++){
		  Button b = new Button(group2,SWT.CHECK);
		  b.setText(algorithms.get(i));
		  buttonlist.add(b);
	}
	}
	
	public static void updateCombo(String name){
		if(combo==null||combo.isDisposed())return;
		combo.add(name);
		combo.setText(name);
	}
	
	public static void updateGroup(String name){
		if(group2==null||group2.isDisposed())return;
        Button b = new Button(group2,SWT.CHECK);
		b.setText(name);
		buttonlist.add(b);
		group2.layout();
	}


	public static Composite getMainComposite(){
		operateComposite.dispose();
		operateComposite = new Composite(mainComposite,SWT.BORDER|SWT.DOUBLE_BUFFERED);
		operateComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		mainComposite.layout();
		return operateComposite;
	}
	
	public static void refresh(){
		ac.clear();
		algorithms.clear();
		buttonlist.clear();
		section = null;
	}
	
	public static void updateLabel(String name){
		label.setText(name);
	}
	
	
	class Controllor1 extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			String algorithm = combo.getText();
			String[] algorithms = new String[]{algorithm};
			if(Paramater.algorithmsResults.size()==0){
				MessageDialog.openWarning(combo.getShell(), "waning", "No cluster results to evaluate!");
				return ;
			}
			section.drawCharts(algorithms, Paramater.algorithmsResults, operateComposite, Paramater.algorithmsColorList);
		}
	}
	
	class Controllor2 extends SelectionAdapter{
		public void widgetSelected(SelectionEvent e) {
			Vector<String> algorithms = new Vector<String>();
			for(int i=0;i<buttonlist.size();i++){
				if(!buttonlist.get(i).isDisposed() && buttonlist.get(i).getSelection()){
					algorithms.add(buttonlist.get(i).getText());
				}
			}
			String[] algs = new String[algorithms.size()];
			for(int i=0;i<algorithms.size();i++)
				algs[i] = algorithms.get(i);

			section.drawCharts(algs, Paramater.algorithmsResults, operateComposite, Paramater.algorithmsColorList);
		}
	}


	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
