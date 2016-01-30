
package prototype;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

public class JSliderCustomUI extends BasicSliderUI
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JSliderCustomUI(JSlider slider)
		{
		super(slider);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	protected Dimension getThumbSize()
		{
		return new Dimension(12, 12);
		}

	@Override
	protected Color getFocusColor()
		{
		return Tools.colorGrayDark;
		}


	@Override
	public void paintThumb(Graphics g)
		{
		Rectangle knobBounds = thumbRect;
		int w = knobBounds.width;
		int h = knobBounds.height;

		// Create graphics copy.
		Graphics2D g2d = (Graphics2D)g.create();

		// Create default thumb shape.
		Shape thumbShape = createThumbShape(w - 1, h - 1);

		// Draw thumb.
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.translate(knobBounds.x, knobBounds.y);

		g2d.setColor(Tools.colorGreen);
		g2d.fill(thumbShape);

		g2d.setColor(Color.BLACK);
		g2d.draw(thumbShape);

		// Dispose graphics.
		g2d.dispose();
		}

	private Shape createThumbShape(int width, int height)
		{
		// Use circular shape.
		Ellipse2D shape = new Ellipse2D.Double(0, 0, width, height);
		return shape;
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

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}
