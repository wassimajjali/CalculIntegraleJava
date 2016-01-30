
package prototype;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class JFrameMain extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public JFrameMain()
		{
		geometry();
		control();
		apparence();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		setLayout(new BorderLayout());

		jPanelContainer = new JPanelContainer();
		add(jPanelContainer, BorderLayout.CENTER);

		addMenu();
		}

	private void control()
		{
		// resize de la fenetre
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		}

	private void apparence()
		{
		setTitle("Calcul de l'air sous l'intégrale");
		setSize(1280, 720);
		setMinimumSize(new Dimension(1280, 720));
		setLocationRelativeTo(null);
		setVisible(true);
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
	private void addMenu()
		{
		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem;
		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu("Fichier");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription("Ouvrir, Sauvegarder, Charger, Exporter");
		menuBar.add(menu);
		menuBar.setBackground(Tools.colorGrayLight);
		// a group of JMenuItems
		menuItem = new JMenuItem("Sauvegarder en png");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		menuItem.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					jPanelContainer.getjPanelGraphe().saveAsPNG();
					}
			});

		menu.add(menuItem);

		menuItem = new JMenuItem("Copier");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		menuItem.setMnemonic(KeyEvent.VK_C);
		menuItem.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					jPanelContainer.getjPanelGraphe().copy();
					}
			});
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Zoomer");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		menuItem.setMnemonic(KeyEvent.VK_Z);
		menuItem.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					jPanelContainer.getjPanelGraphe().zoom();
					}
			});

		menu.add(menuItem);

		menuItem = new JMenuItem("Dézoomer");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		menuItem.setMnemonic(KeyEvent.VK_D);
		menuItem.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					jPanelContainer.getjPanelGraphe().dezoom();
					}
			});
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Propriétés");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		menuItem.setMnemonic(KeyEvent.VK_P);
		menuItem.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					jPanelContainer.getjPanelGraphe().properties();
					}
			});
		menu.add(menuItem);

		menu.addSeparator();

		menuItem = new JMenuItem("Quitter");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		menuItem.setMnemonic(KeyEvent.VK_F4);

		menuItem.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
					{
					JFrameMain.this.dispose();
					}
			});
		menu.add(menuItem);

		menu = new JMenu("Aide");
		menu.setMnemonic(KeyEvent.VK_F);
		//	menu.getAccessibleContext().setAccessibleDescription("?");
		menuBar.add(menu);

		// a group of JMenuItems
		menuItem = new JMenuItem("?", KeyEvent.VK_N);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		//	menuItem.getAccessibleContext().setAccessibleDescription("Ouvrir un fichier");

		menuItem.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
					{
					//openHelp();
					}
			});
		menu.add(menuItem);
		this.setJMenuBar(menuBar);

		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	//private static final File FILE_PROPERTIES = new File("./config.ini");
	private JPanelContainer jPanelContainer;
	//	private Stack<Schema> pileZ, pileY;
	}
