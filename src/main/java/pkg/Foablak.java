package pkg;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Foablak {

	private static Logger logger = LoggerFactory.getLogger(Foablak.class);

	private JFrame frmMalom;

	/**
	 * Elindítja az alkalmazást
	 */
	public static void start(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Foablak window = new Foablak();
					window.frmMalom.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Elkészíti az alkalmazást.
	 */
	public Foablak() {
		initialize();
	}
	
	/***
	 * Megadja hogy az adott pozíción történő kattintással követ választottak-e ki.
	 * @param la a követ szimbolizáló label
	 * @param arg0 az egér-esemény
	 * @return visszaadja a találat 
	 */
	private boolean isClicked(JLabel la, MouseEvent arg0) { 
		int halfWidth = (int) (la.getSize().getWidth()/2);
		int halfHeight = (int) (la.getSize().getHeight()/2);
		int laMidX = (int) (la.getX() + halfWidth);
		int laMidY = (int) (la.getY() + halfHeight + 20);
		
		logger.debug("x " + Math.abs(laMidX - arg0.getX()) + " " +laMidX + " "+  arg0.getX() + " " +  halfWidth);
		logger.debug("y " + Math.abs(laMidY - arg0.getY()) + " " +laMidY + " "+  arg0.getY() + " " +  halfHeight);
		if (Math.abs(laMidX - arg0.getX()) < halfWidth) {
			 if (Math.abs(laMidY - arg0.getY()) < halfHeight) {
				 logger.info("kattintás talált {} {}",la.getX()/45, la.getY()/45);
				 return true;
			 }
		 }
		return false;
	}
	
	/***
	 * Elkészíti a keret tartalmát a megjelenítésre
	 */
	private void initialize() {
		frmMalom = new JFrame();
		frmMalom.setResizable(false);
		frmMalom.setTitle("Malom");
		frmMalom.setBounds(100, 100, 450, 340);
		frmMalom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImagePanel imgPanel = new ImagePanel(new ImageIcon(this.getClass()
				.getResource("/background.png")).getImage());
		imgPanel.setBorder(null);

		frmMalom.getContentPane().add(imgPanel);
		
		List<JLabel> labels = new ArrayList<JLabel>();
		
		try {
			Class cl = Class.forName("pkg.Algoritmusok");
			Constructor<?> cons = cl.getConstructor(null);
			Object o = cons.newInstance();
			Method m = cl.getMethod("getStones");
			labels = (List<JLabel>) m.invoke(o);
			logger.debug("Labels size: {}", labels.size());

			for (JLabel jL : labels) {
				imgPanel.add(jL);
			}
		} catch (ClassNotFoundException | NoSuchMethodException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | InstantiationException e) {
			logger.warn(e.toString());
		}
		
		final List<JLabel> l = labels; 

		frmMalom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					for (JLabel la : l) {
						if (isClicked(la, arg0)) {
							//TODO
						}
					}
				}
				
				if (arg0.getButton() == MouseEvent.BUTTON2) {
				}
			}
		});
		
		frmMalom.setVisible(true);
	}
}
