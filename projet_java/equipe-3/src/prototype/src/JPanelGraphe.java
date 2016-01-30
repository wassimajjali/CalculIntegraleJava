
package prototype;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDifferenceRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public class JPanelGraphe extends JPanelShadow
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JPanelGraphe(PanelResultat panelResult)
		{
		super(Tools.colorGrayLight);
		this.panelResult = panelResult;
		this.choix_aire = 1;
		this.nbPoint = 500;
		this.echantillon = 50;
		this.fonction = "0";
		this.borneA = -Math.PI;
		this.borneB = Math.PI;
		this.serie_fonction = new XYSeries("fonction");
		this.serie_aire = new XYSeries("aire");
		this.collection_fonction = new XYSeriesCollection();
		this.collection_aire = new XYSeriesCollection();
		this.collection_aire_prolongement = new XYSeriesCollection();
		this.color_neg = Tools.colorRed;
		this.color_pos = Tools.colorGreen;
		this.basicStroke_prolongement = new BasicStroke(4.0f);

		chart = createChart();
		chart.setBackground(Tools.colorGrayLight);
		chart.setOpaque(false);
		chart.setMouseWheelEnabled(true);
		setLayout(new BorderLayout());
		add(chart, BorderLayout.CENTER);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void setFonction(String _fonction, int _choix_aire, double _a, double _b, int _echantillon)
		{
		this.fonction = _fonction;
		this.borneA = _a;
		this.borneB = _b;
		this.echantillon = _echantillon;
		this.choix_aire = _choix_aire;

		datasetFonction();
		plot.setDataset(0, dataset_fonction);
		datasetAire(choix_aire);
		plot.setDataset(1, dataset_aire_prolongement);
		plot.setDataset(2, dataset_aire);
		}

	public void setAire(int choix_aire)
		{
		this.choix_aire = choix_aire;
		/*datasetAire(choix_aire);
		plot.setDataset(1, dataset_aire);*/
		}

	public void setBorneB(double val)
		{
		this.borneB = val;
		}

	public void setBorneA(double val)
		{
		this.borneA = val;
		}

	public void setEchantillon(int val)
		{
		this.echantillon = val;
		}

	public void copy()
	{
		chart.doCopy();
	}

	public void saveAsPNG()
		{
		try
			{
			chart.doSaveAs();
			}
		catch (IOException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		/*try
			{
			JFileChooser fc = new JFileChooser();
			FileFilter filter = new FileNameExtensionFilter("PNG file", new String[] { "png" });
			fc.addChoosableFileFilter(filter);
			fc.setFileFilter(filter);
			fc.setAcceptAllFileFilterUsed(false);
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION)
				{
				ChartUtilities.saveChartAsPNG(new File(fc.getSelectedFile().getPath() + ".png"), xylineChart, 500, 300);
				}
			else
				{
				}
			}
		catch (Exception e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}*/
		}

	public void save()
		{
		File imageFile = new File("XYLineChart.png");
		int width = 800;
		int height = 480;

		try
			{
			ChartUtilities.saveChartAsPNG(imageFile, xylineChart, width, height);
			}
		catch (IOException ex)
			{
			System.err.println(ex);
			}
		}

	public void zoom()
		{
		plot.zoomDomainAxes(0.5, null, new Point2D.Double(0, 0));
		}

	public void dezoom()
		{
		plot.zoomDomainAxes(1.5, null, new Point2D.Double(0, 0));
		}

	public void properties()
	{
		chart.doEditChartProperties();

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

	private ChartPanel createChart()
		{
		//datasetFonction();
		//datasetAire(3);

		plot = new XYPlot();
		plot.setDomainAxis(new NumberAxis("x"));
		plot.setRangeAxis(new NumberAxis("y"));
		plot.setBackgroundPaint(Color.white);
		plot.setRangeGridlinePaint(Color.black);
		ValueMarker marker = new ValueMarker(0); // position is the value on the axis
		marker.setPaint(Color.black);
		marker.setStroke(new BasicStroke(1.5f));

		plot.addRangeMarker(marker);
		plot.addDomainMarker(marker);

		plot.setDataset(0, dataset_fonction);
		plot.setDataset(1, dataset_aire_prolongement);
		plot.setDataset(2, dataset_aire);

		XYItemRenderer renderer_aire = new XYDifferenceRenderer(color_pos, color_neg, false);
		renderer_aire.setSeriesStroke(0, new BasicStroke(1.0f));
		renderer_aire.setSeriesPaint(0, Color.BLACK);
		renderer_aire.setSeriesVisibleInLegend(0, false);

		XYLineAndShapeRenderer renderer_fonction = new XYLineAndShapeRenderer(true, false);
		renderer_fonction.setSeriesPaint(0, Color.BLUE);
		renderer_fonction.setSeriesStroke(0, new BasicStroke(2.0f));

		renderer_simpson_prolongement = new XYLineAndShapeRenderer(true, false);
		renderer_simpson_prolongement.setSeriesVisibleInLegend(false);

		plot.setRenderer(0, renderer_fonction);
		plot.setRenderer(1, renderer_simpson_prolongement);
		plot.setRenderer(2, renderer_aire);

		xylineChart = new JFreeChart("", new Font("Arial", 1, 18), plot, true);
		xylineChart.setAntiAlias(true);
		xylineChart.setBackgroundPaint(Tools.colorGrayLight);

		return new ChartPanel(xylineChart);
		}

	private void datasetFonction()
		{
		try
			{
			this.calcul_y = new ExpressionBuilder(fonction).withVariable("x", 0).build();
			this.serie_fonction.clear();

			double x = borneA;

			double interval = calcul_Interval(this.borneA, this.borneB);
			double y = 0;

			for(int i = 0; i <= nbPoint; i++)
				{
				y = calcul_y.calculate(x);
				this.serie_fonction.add(x, y);
				x += interval / nbPoint;
				}

			collection_fonction.removeAllSeries();
			collection_fonction.addSeries(serie_fonction);
			dataset_fonction = collection_fonction;

			}
		catch (UnknownFunctionException | UnparsableExpressionException e)
			{
			System.out.println("Erreur saisie - " + e.getMessage());
			}
		}

	private void prolongementSimpson(String f, double a, double b, int cpt)
		{
		try
			{
			this.calcul_y_simpson = new ExpressionBuilder(f).withVariable("x", 0).build();

			XYSeries serie_simpson_prolongement = new XYSeries(cpt + "");

			if (cpt % 2 == 0)
				{
				renderer_simpson_prolongement.setSeriesPaint(cpt, Color.ORANGE);
				}
			else
				{
				renderer_simpson_prolongement.setSeriesPaint(cpt, Color.MAGENTA);
				}

			renderer_simpson_prolongement.setSeriesStroke(cpt, basicStroke_prolongement);

			double interval = calcul_Interval(a, b);
			double y = 0;
			double x = a - (4 * (interval / 10.0));

			for(int i = 0; i <= 18; i++)
				{
				y = calcul_y_simpson.calculate(x);
				serie_simpson_prolongement.add(x, y);
				x += interval / 10.0;
				}

			collection_aire_prolongement.addSeries(serie_simpson_prolongement);
			}
		catch (Exception e)
			{
			// TODO Auto-generated catch block
			System.out.println("erreur");
			e.printStackTrace();
			}
		}

	private double calcul_Interval(double a, double b)
		{
		if (a < 0 && b < 0 || a > 0 && b > 0)
			{
			return Math.abs(Math.abs(a) - Math.abs(b));
			}
		else
			{
			return Math.abs(a) + Math.abs(b);
			}
		}

	private double calculAireRect(double x1, double x2, double y)
		{
		return (x2 - x1) * y;
		}

	private double calculAireTrapeze(double x1, double x2, double y1, double y2)
		{
		return ((y1 + y2) * (x2 - x1)) / 2;
		}

	private void datasetAire(int choix_aire)
		{
		collection_aire_prolongement.removeAllSeries();
		this.serie_aire.clear();
		double x = borneA;
		double interval = calcul_Interval(this.borneA, this.borneB);
		double offset = interval / echantillon / 2.0;

		double y = 0;
		double ytemp = 0;
		double xtemp = 0;
		double aire = 0;

		switch(choix_aire)
			{
			case 1:
				for(int i = 0; i <= echantillon; i++)
					{
					y = calcul_y.calculate(x);

					serie_aire.add(x - offset, y);
					xtemp = x - offset;
					x += interval / echantillon;
					serie_aire.add(x - offset, y);
					serie_aire.add(x - offset, 0);
					aire += calculAireRect(xtemp, x - offset, y);
					}
				break;

			case 2:
				for(int i = 0; i < echantillon; i++)
					{
					y = calcul_y.calculate(x);
					ytemp = y;
					serie_aire.add(x, y);
					xtemp = x;
					x += interval / echantillon;
					y = calcul_y.calculate(x);
					serie_aire.add(x, y);
					serie_aire.add(x, 0);
					aire += calculAireTrapeze(xtemp, x, ytemp, y);
					}
				break;

			case 3:
				double increment = interval / echantillon;
				double x1 = x;
				double x2 = x1 + increment / 2.0;
				double x3 = x1 + increment;
				double interval_simpson = calcul_Interval(x1, x3);
				int nbPoint_simpson = 10;

				String f = "";

				for(int j = 0; j < echantillon; j++)
					{
					f = equationParabole(new Point2D.Double(x1, calcul_y.calculate(x1)), new Point2D.Double(x2, calcul_y.calculate(x2)), new Point2D.Double(x3, calcul_y.calculate(x3)));
					prolongementSimpson(f, x1, x3, j);

					try
						{
						//this.calcul_y_simpson = new ExpressionBuilder(f).withVariable("x", 0).build();

						for(int i = 0; i <= nbPoint_simpson; i++)
							{
							y = calcul_y_simpson.calculate(x);
							this.serie_aire.add(x, y);
							x += interval_simpson / nbPoint_simpson;
							}

						this.serie_aire.add(x - (interval_simpson / nbPoint_simpson), 0);
						this.serie_aire.add(x - (interval_simpson / nbPoint_simpson), y);
						aire += simpson(nbPoint_simpson, x1, x3, f);
						}
					catch (Exception e)
						{
						// TODO Auto-generated catch block
						e.printStackTrace();
						}

					x1 = x3;
					x2 = x1 + increment / 2.0;
					x3 = x1 + increment;
					x = x1;
					}

				break;
			}

		collection_aire.removeAllSeries();
		collection_aire.addSeries(serie_aire);

		dataset_aire = collection_aire;
		dataset_aire_prolongement = collection_aire_prolongement;

		panelResult.getAreaCalcText().setText("Aire : " + String.valueOf(aire));
		}

	public static String equationParabole(Point2D p1, Point2D p2, Point2D p3)
		{
		double a1 = Math.pow(p1.getX(), 2);
		double b1 = p1.getX();
		double c1 = 1;
		double d1 = p1.getY();

		double a2 = Math.pow(p2.getX(), 2);
		double b2 = p2.getX();
		double c2 = 1;
		double d2 = p2.getY();

		double a3 = Math.pow(p3.getX(), 2);
		double b3 = p3.getX();
		double c3 = 1;
		double d3 = p3.getY();

		double det = a1 * (b2 * c3 - c2 * b3) - b1 * (a2 * c3 - a3 * c2) + c1 * (a2 * b3 - a3 * b2);
		double x = (d1 * (b2 * c3 - c2 * b3) - b1 * (d2 * c3 - d3 * c2) + c1 * (d2 * b3 - d3 * b2)) / (det);
		double y = (a1 * (d2 * c3 - c2 * d3) - d1 * (a2 * c3 - a3 * c2) + c1 * (a2 * d3 - a3 * d2)) / (det);
		double z = (a1 * (b2 * d3 - d2 * b3) - b1 * (a2 * d3 - a3 * d2) + d1 * (a2 * b3 - a3 * b2)) / (det);
		return x + "*x^2+" + y + "*x+" + z;
		}

	public static double simpson(int N, double A, double B, String txt)
		{
		double X, h, Iapp0, Iapp1, Iapp2, Iapp = 0, tamp1, tamp2, tamp3;
		int NN, i;
		h = (B - A) / N;
		try
			{
			try
				{
				Calculable calc = new ExpressionBuilder(txt).withVariable("x", A).build();
				tamp1 = calc.calculate();
				Calculable calc2 = new ExpressionBuilder(txt).withVariable("x", B).build();
				tamp2 = calc2.calculate();

				Iapp0 = tamp1 + tamp2;
				Iapp1 = 0.0;
				Iapp2 = 0.0;
				NN = N - 1;
				for(i = 1; i <= NN; i++)
					{
					X = A + i * h;
					try
						{
						try
							{

							Calculable calc3 = new ExpressionBuilder(txt).withVariable("x", X).build();
							tamp3 = calc3.calculate();

							if ((i % 2) == 0)
								{
								Iapp2 = Iapp2 + tamp3;
								}
							else
								{
								Iapp1 = Iapp1 + tamp3;
								}

							}
						catch (UnknownFunctionException e1)
							{
							e1.printStackTrace();
							}
						}
					catch (UnparsableExpressionException ex)
						{
						ex.printStackTrace();
						}

					}

				Iapp = (Iapp0 + 2.0 * Iapp2 + 4.0 * Iapp1) * h / 3.0;
				}
			catch (UnknownFunctionException e1)
				{
				e1.printStackTrace();
				}
			}
		catch (UnparsableExpressionException ex)
			{
			ex.printStackTrace();
			}
		return (Iapp);

		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private PanelResultat panelResult;
	private String fonction;
	private double borneA;
	private double borneB;
	private XYPlot plot;
	private JFreeChart xylineChart;
	private ChartPanel chart;
	private XYDataset dataset_fonction;
	private XYDataset dataset_aire;
	private XYDataset dataset_aire_prolongement;
	private Calculable calcul_y;
	private Calculable calcul_y_simpson;
	private XYSeries serie_fonction;
	private XYSeries serie_aire;
	private XYSeriesCollection collection_fonction;
	private XYSeriesCollection collection_aire;
	private XYSeriesCollection collection_aire_prolongement;
	private int nbPoint;
	private int echantillon;
	private Color color_pos;
	private Color color_neg;
	private int choix_aire;

	private XYLineAndShapeRenderer renderer_simpson_prolongement;
	private BasicStroke basicStroke_prolongement;
	}
