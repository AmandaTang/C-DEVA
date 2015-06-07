package clustere;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		
		String editArea = layout.getEditorArea();
		layout.addView("CDEVA.view1", IPageLayout.LEFT, 0.25f, editArea);
//		layout.addView("CDEVA.view4", IPageLayout.LEFT, 0.2f, editArea);
//		IFolderLayout leftTop = layout.createFolder("left", IPageLayout.LEFT, 0.25f, editArea);
//		leftTop.addView("CDEVA.view1");   //Ê÷  µ¼º½
//		leftTop.addView("CDEVA.view4");  //±í
//		layout.addView("CDEVA.view2", IPageLayout.BOTTOM, 0.8f, editArea);
		IFolderLayout leftBottom = layout.createFolder("left", IPageLayout.BOTTOM, 0.73f,  "CDEVA.view1");
		leftBottom.addView("CDEVA.view3");   //current network
//		leftBottom.addView("CDEVA.view2");   //
		leftBottom.addView("CDEVA.view4");  // using algorithms
	}
}
