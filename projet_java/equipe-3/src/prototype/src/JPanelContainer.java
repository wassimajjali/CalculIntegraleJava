
package prototype;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class JPanelContainer extends JPanel
	{
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JPanelContainer()
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	private void appearance()
		{
			setBorder(new EmptyBorder(10, 10, 10, 10));
		}

	private void control()
		{
		//rien
		}

	private void geometry()
		{
		setLayout(new BorderLayout());

		panelResultat = new PanelResultat();
		jPanelGraphe = new JPanelGraphe(panelResultat);
		jPanelControlGraphe = new JPanelControlGraphe(jPanelGraphe);
		toolBar = new JToolBarPerso(jPanelGraphe);

		add(jPanelGraphe, BorderLayout.CENTER);
		add(toolBar, BorderLayout.NORTH);
		add(jPanelControlGraphe, BorderLayout.WEST);
		add(panelResultat, BorderLayout.SOUTH);
		}
	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public JPanelGraphe getjPanelGraphe()
		{
		return this.jPanelGraphe;
		}


	public PanelResultat getPanelResultat()
		{
		return this.panelResultat;
		}


	public JPanelControlGraphe getjPanelControlGraphe()
		{
		return this.jPanelControlGraphe;
		}


	public JToolBarPerso getToolBar()
		{
		return this.toolBar;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private JPanelGraphe jPanelGraphe;
	private PanelResultat panelResultat;
	private JPanelControlGraphe jPanelControlGraphe;
	//	private String filePath = "";
	//	private String imagePath = "";
	private JToolBarPerso toolBar;
	}

