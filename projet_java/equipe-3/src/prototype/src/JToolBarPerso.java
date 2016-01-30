
package prototype;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

public class JToolBarPerso extends JToolBar
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JToolBarPerso(JPanelGraphe panelGraphe)
		{
		this.panelGraphe = panelGraphe;
		rule = new Rule();
		geometry();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		Color shadowColorA = new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), 150);
		Graphics2D graphics = (Graphics2D)g;

		if (shady)
			{
			graphics.setColor(shadowColorA);
			graphics.fillRoundRect(shadowOffset,// X position
					shadowOffset,// Y position
					this.getWidth() - strokeSize - shadowOffset, // width
					this.getHeight() - strokeSize - shadowOffset, // height
					arcs.width, arcs.height);// arc Dimension
			}
		else
			{
			shadowGap = 1;
			}

		//Draws the rounded opaque panel with borders.
		graphics.setColor(Tools.colorGrayDark);
		graphics.fillRoundRect(0, 0, this.getWidth() - shadowGap, this.getHeight() - shadowGap, arcs.width, arcs.height);
		graphics.setColor(getForeground());
		graphics.setStroke(new BasicStroke(strokeSize));
		graphics.drawRoundRect(0, 0, this.getWidth() - shadowGap, this.getHeight() - shadowGap, arcs.width, arcs.height);
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		buttonPNG = new JButton();
		buttonPNG.setToolTipText("Nouveau Schéma");

		buttonCopy = new JButton();
		buttonCopy.setToolTipText("Ouvrir Schéma");

		buttonZoomIn = new JButton();
		buttonZoomIn.setToolTipText("Enregistrer");

		buttonZoomOut = new JButton();
		buttonZoomOut.setToolTipText("Enregistrer sous");

		buttonProperties = new JButton();
		buttonProperties.setToolTipText("Exporter");

		buttonQuitter = new JButton();
		buttonQuitter.setToolTipText("Quitter");

		buttonAide = new JButton();
		buttonAide.setToolTipText("Aide");

		URL imageurl = getClass().getResource("../images/export.png");
		Image myPicture = Toolkit.getDefaultToolkit().getImage(imageurl);
		buttonPNG.setIcon(new ImageIcon(myPicture));
		buttonPNG.setBackground(Tools.colorGrayDark);

		imageurl = getClass().getResource("../images/copy.png");
		myPicture = Toolkit.getDefaultToolkit().getImage(imageurl);
		buttonCopy.setIcon(new ImageIcon(myPicture));
		buttonCopy.setBackground(Tools.colorGrayDark);

		imageurl = getClass().getResource("../images/zoomin.png");
		myPicture = Toolkit.getDefaultToolkit().getImage(imageurl);
		buttonZoomIn.setIcon(new ImageIcon(myPicture));
		buttonZoomIn.setBackground(Tools.colorGrayDark);

		imageurl = getClass().getResource("../images/zoomout.png");
		myPicture = Toolkit.getDefaultToolkit().getImage(imageurl);
		buttonZoomOut.setIcon(new ImageIcon(myPicture));
		buttonZoomOut.setBackground(Tools.colorGrayDark);

		imageurl = getClass().getResource("../images/properties.png");
		myPicture = Toolkit.getDefaultToolkit().getImage(imageurl);
		buttonProperties.setIcon(new ImageIcon(myPicture));
		buttonProperties.setBackground(Tools.colorGrayDark);

		imageurl = getClass().getResource("../images/aide.png");
		myPicture = Toolkit.getDefaultToolkit().getImage(imageurl);
		buttonAide.setIcon(new ImageIcon(myPicture));
		buttonAide.setBackground(Tools.colorGrayDark);

		imageurl = getClass().getResource("../images/quitter.png");
		myPicture = Toolkit.getDefaultToolkit().getImage(imageurl);
		buttonQuitter.setIcon(new ImageIcon(myPicture));
		buttonQuitter.setBackground(Tools.colorGrayDark);

		}

	private void control()
		{
		//createActions();

		buttonPNG.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					// TODO Auto-generated method stub
					panelGraphe.saveAsPNG();
					}
			});

		buttonCopy.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					// TODO Auto-generated method stub
					panelGraphe.copy();
					}
			});
		buttonZoomIn.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					// TODO Auto-generated method stub
					panelGraphe.zoom();
					}
			});
		buttonZoomOut.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					// TODO Auto-generated method stub
					panelGraphe.dezoom();
					}
			});

		buttonProperties.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					// TODO Auto-generated method stub
					panelGraphe.properties();
					}
			});

		buttonAide.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					// TODO Auto-generated method stub
					rule.setVisible(true);
					}
			});

		buttonQuitter.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					// TODO Auto-generated method stub

					}
			});

		add(buttonPNG);
		add(buttonCopy);
		addSeparator();
		add(buttonZoomIn);
		add(buttonZoomOut);

		addSeparator();

		add(buttonProperties);

		addSeparator();

		add(buttonAide);

		addSeparator();

		add(buttonQuitter);
		}

	private void apparence()
		{
		this.setBorder(new EmptyBorder(5, 5, 10, 10));
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//Attributs
	private JButton buttonPNG;
	private JButton buttonCopy;
	private JButton buttonZoomIn;
	private JButton buttonZoomOut;
	private JButton buttonProperties;
	private JButton buttonAide;
	private JButton buttonQuitter;

	protected int strokeSize = 1;
	protected Color shadowColor = Color.gray;
	protected boolean shady = true;
	protected boolean highQuality = true;
	protected Dimension arcs = new Dimension(0, 0);
	//protected Dimension arcs = new Dimension(20, 20);//creates curved borders and panel
	protected int shadowGap = 5;
	protected int shadowOffset = 4;
	private JPanelGraphe panelGraphe;
	private Rule rule;
	}
