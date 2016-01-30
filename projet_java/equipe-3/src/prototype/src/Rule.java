
package prototype;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class Rule extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Rule()
		{
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

	private void geometry()
		{
			BorderLayout borderLayout = new BorderLayout();
			setLayout(borderLayout);
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance()
		{
		StyleContext sc = new StyleContext();
		final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
		JTextPane pane = new JTextPane(doc);
		pane.setEditable(false);

		// Create and add the style
		final javax.swing.text.Style heading2Style = sc.addStyle("Heading2", null);
		heading2Style.addAttribute(StyleConstants.Foreground, Color.black);
		heading2Style.addAttribute(StyleConstants.FontSize, new Integer(16));
		heading2Style.addAttribute(StyleConstants.FontFamily, "serif");
		heading2Style.addAttribute(StyleConstants.Bold, new Boolean(true));

		try
			{
			doc.insertString(0, text, null);
			}
		catch (BadLocationException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}

		// Finally, apply the style to the heading
		doc.setParagraphAttributes(0, 1, heading2Style, false);

		getContentPane().add(new JScrollPane(pane));
		setSize(300, 500);
		setLocationRelativeTo(null); // frame centrer
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	public static final String text =
			"Mots clés pour les fonctions :\n\n" +
			"variable inconnue : x\n\n" +
			"abs : valeur absolue\n" +
			"acos : arc cosinus\n" +
			"asin : arc sinus\n" +
			"atan : arc tangente\n" +
			"cbrt : racine cubique\n" +
			"ceil : arrondi supérieur\n" +
			"cos : cosinus\n" +
			"cosh : cosinus hyperbolique\n" +
			"exp : exponentielle (e^x)\n" +
			"floor : arrondi inférieur\n" +
			"log : logarithme naturel (base e)\n" +
			"log10 : logarithme base 10\n" +
			"log2 : logarithme base 2\n" +
			"sin : sinus\n" +
			"sinh : sinus hyperbolique\n" +
			"sqrt : racine carrée\n" +
			"tan : tangente\n" +
			"tanh : tangente hyperbolique\n" +
			"^x : puissance x\n\n" +
			"exemple : 3*x + sin(x) - x^2";
	}
