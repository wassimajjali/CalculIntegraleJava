
package prototype;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class JPanelShadow extends JPanel
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	private static final long serialVersionUID = 1L;

	public int pixels;

	protected int strokeSize = 1;
	/**
	 * Color of shadow
	 */
	protected Color shadowColor = Color.black;
	/**
	 * Sets if it drops shadow
	 */
	protected boolean shady = true;
	/**
	 * Sets if it has an High Quality view
	 */
	protected boolean highQuality = true;
	/**
	 * Double values for Horizontal and Vertical radius of corner arcs
	 */
	protected Dimension arcs = new Dimension(0, 0);
	//protected Dimension arcs = new Dimension(20, 20);//creates curved borders and panel
	/**
	 * Distance between shadow border and opaque panel border
	 */
	protected int shadowGap = 5;
	/**
	 * The offset of shadow.
	 */
	protected int shadowOffset = 4;
	protected Color color;
	public JPanelShadow(Color color)
		{
		super();
		this.color = color;
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		}

	@Override
	protected void paintComponent(Graphics g)
		{
		super.paintComponent(g);
		Color shadowColorA = new Color(Color.GRAY.getRed(), Color.GRAY.getGreen(), Color.GRAY.getBlue(), 150);
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
		graphics.setColor(this.color);
		graphics.fillRoundRect(0, 0, this.getWidth() - shadowGap, this.getHeight() - shadowGap, arcs.width, arcs.height);
		graphics.setColor(getForeground());
		graphics.setStroke(new BasicStroke(strokeSize));
		graphics.drawRoundRect(0, 0, this.getWidth() - shadowGap, this.getHeight() - shadowGap, arcs.width, arcs.height);

		//methode2

		/*int shade = 0;
		int topOpacity = 80;
		for (int i = 0; i < pixels; i++) {
		    g.setColor(new Color(shade, shade, shade, ((topOpacity / pixels) * i)));
		    g.drawRect(i, i, this.getWidth() - ((i * 2) + 1), this.getHeight() - ((i * 2) + 1));
		}*/
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

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}
