
package prototype;

import javax.swing.Box;
import javax.swing.JLabel;

public class PanelResultat extends JPanelShadow
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public PanelResultat()
		{
		super(Tools.colorGrayLight);
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/
	private void appearance()
		{
		//setBorder(BorderFactory.createTitledBorder("Resultat"));
		//setBackground(Tools.colorGrayLight);
		//setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		}

	private void control()
		{
		//rien
		}

	private void geometry()
		{

		areaCalc = new JLabel("");
		areaReal = new JLabel("");
		areaCalcText = new JLabel();
		areaRealText = new JLabel();

		add(Box.createVerticalGlue());
		add(areaCalc);
		add(Box.createVerticalGlue());
		add(areaCalcText);

		add(Box.createVerticalGlue());
		add(areaReal);
		add(Box.createVerticalGlue());
		add(areaRealText);

		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public JLabel getAreaCalcText()
		{
		return this.areaCalcText;
		}

	public JLabel getAreaRealText()
		{
		return this.areaRealText;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	private JLabel areaCalc;

	private JLabel areaReal;
	private JLabel areaCalcText;
	private JLabel areaRealText;

	}
