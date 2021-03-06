
package prototype;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JPanelControlGraphe extends JPanelShadow
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelControlGraphe(JPanelGraphe panelGraphe)
		{
		super(Tools.colorGrayDark);
		setLabelPI();
		setLabelINT();
		this.panelGraphe = panelGraphe;
		//gca
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void setLabelPI()
		{
		for(int i = -10; i < 11; i++)
			{
			if (i % 3 == 0)
				{
				labelsPI.put(i, new JLabel(i + " pi"));
				}
			}
		}

	private void setLabelINT()
		{
		for(int i = -50; i < 51; i++)
			{
			if (i % 15 == 0)
				{
				labelsINT.put(i, new JLabel(i + ""));
				}
			}
		}

	private void control()
		{

		ActionListener actionRadioButton = new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					// TODO Auto-generated method stub
					panelGraphe.setAire(Integer.valueOf(group.getSelection().getActionCommand()));
					}
			};

		carre.addActionListener(actionRadioButton);
		triangle.addActionListener(actionRadioButton);
		simpson.addActionListener(actionRadioButton);
		cbxPI.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					sliderPI = true;
					range.setMaximum(10);
					range.setMinimum(-10);
					range.setUpperValue(1);
					range.setValue(-1);
					range.setMajorTickSpacing(1);
					range.setMinorTickSpacing(0);
					range.setLabelTable(labelsPI);
					}
			});

		cbxInt.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					sliderPI = false;
					range.setMaximum(50);
					range.setMinimum(-50);
					range.setUpperValue(3);
					range.setValue(-3);
					range.setMajorTickSpacing(10);
					range.setMinorTickSpacing(2);
					range.setLabelTable(labelsINT);
					}
			});

		bornes1.addFocusListener(new FocusListener()
			{

				@Override
				public void focusLost(FocusEvent e)
					{
					// TODO Auto-generated method stub
					if (!bornes2.getText().trim().isEmpty() && !bornes1.getText().trim().isEmpty())
						{
						if (Double.valueOf(bornes1.getText()) > Double.valueOf(bornes2.getText()))
							{
							bornes1.setText(bornes2.getText());
							}
						}
					}

				@Override
				public void focusGained(FocusEvent e)
					{
					// TODO Auto-generated method stub

					}
			});

		bornes2.addFocusListener(new FocusListener()
			{

				@Override
				public void focusLost(FocusEvent e)
					{
					// TODO Auto-generated method stub
					if (!bornes1.getText().trim().isEmpty() && !bornes2.getText().trim().isEmpty())
						{
						if (Double.valueOf(bornes2.getText()) < Double.valueOf(bornes1.getText()))
							{
							bornes2.setText(bornes1.getText());
							}
						}
					}

				@Override
				public void focusGained(FocusEvent e)
					{
					// TODO Auto-generated method stub

					}
			});

		range.addChangeListener(new ChangeListener()
			{

				@Override
				public void stateChanged(ChangeEvent e)
					{
					// TODO Auto-generated method stub

					bornes1.setText(range.getValue() + "");
					bornes2.setText(range.getUpperValue() + "");

					}
			});

		btnFonction.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					try
						{
						if (fonction.getText().trim().isEmpty() == false && fonction.isShowingHint() == false)
							{
							double a = Double.valueOf(bornes1.getText());
							double b = Double.valueOf(bornes2.getText());
							if (sliderPI)
								{
								a = a * Math.PI;
								b = b * Math.PI;
								}

							if (!Double.valueOf(a).equals(b))
								{
								panelGraphe.setFonction(fonction.getText().trim(), Integer.valueOf(group.getSelection().getActionCommand()), a, b, jSliderEch.getValue());
								}
							else
								{
								javax.swing.JOptionPane.showMessageDialog(null, "Bornes identiques : Aire = 0");
								}
							}
						}
					catch (Exception e)
						{
						javax.swing.JOptionPane.showMessageDialog(null, "V�rifier vos bornes d'int�gration!");
						}
					}
			});

		jSliderEch.addChangeListener(new ChangeListener()
			{

				@Override
				public void stateChanged(ChangeEvent e)
					{
					echantillon.setText("Echantillon :" + String.valueOf(jSliderEch.getValue()));
					//panelGraphe.setEchantillon(jSliderEch.getValue());
					}
			});
		}

	private void geometry()
		{
		fonction = new HintTextField("Entrer une fonction");

		btnFonction = new JButton("Calculer");

		jSliderEch = new JSlider(SwingConstants.HORIZONTAL);
		jSliderEch.setMajorTickSpacing(50);
		jSliderEch.setMinorTickSpacing(10);
		jSliderEch.setMaximum(500);
		jSliderEch.setMinimum(1);
		jSliderEch.setValue(50);
		jSliderEch.setPaintTicks(true);
		jSliderEch.setPaintTrack(true);
		jSliderEch.setLabelTable(jSliderEch.createStandardLabels(200, 50));
		jSliderEch.setPaintLabels(true);
		jSliderEch.setUI(new JSliderCustomUI(jSliderEch));

		range = new RangeSlider(-50, 50);
		range.setOrientation(SwingConstants.HORIZONTAL);
		range.setUpperValue(3);
		range.setValue(-3);
		range.setMajorTickSpacing(10);
		range.setMinorTickSpacing(2);
		range.setLabelTable(labelsINT);
		range.setPaintTicks(true);
		range.setPaintLabels(true);

		KeyListener keyListener = new KeyAdapter()
			{

				@Override
				public void keyTyped(KeyEvent e)
					{
					char vChar = e.getKeyChar();
					if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE) || (vChar == KeyEvent.VK_PERIOD) || (vChar == KeyEvent.VK_MINUS)))
						{
						e.consume();
						}
					}
			};

		bornes1 = new JTextField(range.getValue() + "");
		bornes1.addKeyListener(keyListener);
		bornes2 = new JTextField(range.getUpperValue() + "");
		bornes2.addKeyListener(keyListener);

		labBorne1 = new JLabel("borne A: ");
		labBorne2 = new JLabel("borne B: ");

		//labelBorne = new JLabel("[" + range.getValue() + " ; " + range.getUpperValue() + "]");
		echantillon = new JLabel("Echantillon : " + String.valueOf(jSliderEch.getValue()));

		group = new ButtonGroup();

		carre = new JRadioButton("Carre");

		triangle = new JRadioButton("Triangle");
		simpson = new JRadioButton("Simpson");

		carre.setMnemonic(KeyEvent.VK_1);
		carre.setActionCommand("1");
		carre.setSelected(true);

		triangle.setMnemonic(KeyEvent.VK_2);
		triangle.setActionCommand("2");

		simpson.setMnemonic(KeyEvent.VK_3);
		simpson.setActionCommand("3");

		group.add(carre);
		group.add(triangle);
		group.add(simpson);

		boxV = Box.createVerticalBox();
		boxH1 = Box.createHorizontalBox();
		boxH2 = Box.createHorizontalBox();
		boxH3 = Box.createHorizontalBox();
		boxH6 = Box.createHorizontalBox();
		boxH7 = Box.createHorizontalBox();

		boxH2.add(carre);
		boxH2.add(triangle);
		boxH2.add(simpson);

		//numerique
		boxH6.add(labBorne1);
		boxH6.add(bornes1);
		boxH7.add(labBorne2);
		boxH7.add(bornes2);

		ButtonGroup groupChoix = new ButtonGroup();
		cbxPI = new JRadioButton("Pi");
		cbxInt = new JRadioButton("R�el");
		cbxInt.setSelected(true);
		groupChoix.add(cbxPI);
		groupChoix.add(cbxInt);

		boxH1.add(cbxInt);
		boxH1.add(cbxPI);

		boxH3.add(btnFonction);

		boxV.add(fonction);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(boxH1);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(boxH6);
		boxV.add(boxH7);
		boxV.add(Box.createVerticalStrut(20));
		//boxV.add(labelBorne);
		boxV.add(range);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(echantillon);
		boxV.add(jSliderEch);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(boxH2);
		boxV.add(Box.createVerticalStrut(20));
		boxV.add(boxH3);

		add(boxV, BorderLayout.CENTER);

		}

	private void appearance()
		{
		btnFonction.setBackground(Tools.colorGrayLight);

		jSliderEch.setBackground(Tools.colorGrayDark);
		jSliderEch.setForeground(Color.BLACK);

		range.setBackground(Tools.colorGrayDark);
		range.setForeground(Color.BLACK);

		//labelBorne.setForeground(Color.BLACK);
		echantillon.setForeground(Color.BLACK);

		carre.setBackground(Tools.colorGrayDark);
		carre.setForeground(Color.BLACK);

		triangle.setBackground(Tools.colorGrayDark);
		triangle.setForeground(Color.BLACK);

		simpson.setBackground(Tools.colorGrayDark);
		simpson.setForeground(Color.BLACK);

		cbxPI.setBackground(Tools.colorGrayDark);
		cbxInt.setBackground(Tools.colorGrayDark);

		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private JPanelGraphe panelGraphe;
	private HintTextField fonction;
	private JButton btnFonction;
	private ButtonGroup group;
	private boolean sliderPI = false;

	//Box
	private Box boxV;
	private Box boxH1;
	private Box boxH2;
	private Box boxH3;
	private Box boxH6;
	private Box boxH7;

	//borne num
	private JTextField bornes1;
	private JTextField bornes2;
	private JLabel labBorne1;
	private JLabel labBorne2;

	//label
	private JLabel labelBorne;
	private JLabel echantillon;

	//slider
	private JSlider jSliderEch;
	private RangeSlider range;

	//radioButton
	private JRadioButton cbxPI;
	private JRadioButton cbxInt;
	private JRadioButton carre;
	private JRadioButton triangle;
	private JRadioButton simpson;

	//label JSlider
	private Hashtable<Integer, JLabel> labelsPI = new Hashtable<Integer, JLabel>();
	private Hashtable<Integer, JLabel> labelsINT = new Hashtable<Integer, JLabel>();

	}
