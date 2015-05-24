package pkg;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/***
 * A főképernyő grafikus felületi elemeit, az eseménykezelőket, és az azokhoz tartozó {@code private} metódusokat tartalmazó osztály.
 */
public class MainScreen {

	/***
	 * A loggoláshoz szükséges {@code Logger} objektum.
	 */
	private static Logger logger = LoggerFactory.getLogger(MainScreen.class);

	/***
	 * A {@code JFrame} objektum amin a grafikus felület elemei találhatóak.
	 */
	private JFrame frmMalom;
	
	/***
	 * A felületen található köveket tartalmazó {@code StoneType} típusú elemeket tartalmazó lista.
	 */
	public static final List<StoneType> stones = Algoritmusok.getStones();

	/**
	 * Elindítja az alkalmazást.
	 * 
	 * @param args az intáshoz felhasználható paraméterek
	 */
	public static void start(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
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
	public MainScreen() {
		initialize();
	}

	/***
	 * Megadja hogy az adott pozíción történő kattintással követ választottak-e
	 * ki.
	 * 
	 * @param st
	 *            a követ szimbolizáló kő típus
	 * @param arg0
	 *            az egér-esemény
	 * @return visszaadja a találatot
	 */
	private boolean isClicked(StoneType st, MouseEvent arg0) {
		Integer[] data = Algoritmusok.getCorrectDataForStones(st.getRow(), st.getCol());
		int laMidX = (int) ((data[1])*45 + 15);
		int laMidY = (int) ((data[0])*45 + 15 + 20);

		
		if (Math.abs(laMidX - arg0.getX()) < 15) {
			if (Math.abs(laMidY - arg0.getY()) < 15) {
				//logger.info("kattintás talált {} {}", data[0], data[1]);

				return true;
			}
		}
		return false;
	}

	/***
	 * Elkészíti a keret tartalmát a megjelenítésre.
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

		JLabel lblJtkos = new JLabel("1. játékos:");
		lblJtkos.setForeground(Color.RED);
		lblJtkos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJtkos.setBounds(305, 11, 79, 21);
		imgPanel.add(lblJtkos);

		JLabel lblJtkos_1 = new JLabel("2. játékos:");
		lblJtkos_1.setForeground(Color.BLUE);
		lblJtkos_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblJtkos_1.setBounds(305, 117, 79, 14);
		imgPanel.add(lblJtkos_1);

		JButton btnNextButton = new JButton("Következő");
		btnNextButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Malom.roundCounter++;
				
			}
		});
		btnNextButton.setBounds(315, 220, 119, 23);
		imgPanel.add(btnNextButton);

		JButton btnNewButton_1 = new JButton("Toplista");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TopList.main(null);
			}
		});
		btnNewButton_1.setBounds(328, 254, 89, 23);
		imgPanel.add(btnNewButton_1);
		
		JLabel lblKvekSzma_1 = new JLabel("Kövek száma:");
		lblKvekSzma_1.setBounds(305, 32, 112, 14);
		imgPanel.add(lblKvekSzma_1);
		
		JLabel lblKvekATablan_1 = new JLabel("Kövek a táblán: ");
		lblKvekATablan_1.setBounds(305, 57, 112, 14);
		imgPanel.add(lblKvekATablan_1);
		
		JLabel lblKvekSzma_2 = new JLabel("Kövek száma:");
		lblKvekSzma_2.setBounds(305, 142, 101, 14);
		imgPanel.add(lblKvekSzma_2);
		
		JLabel lblKvekATablan_2 = new JLabel("Kövek a táblán:");
		lblKvekATablan_2.setBounds(305, 167, 112, 14);
		imgPanel.add(lblKvekATablan_2);
		
		JLabel lblMostUgorhatsz_1 = new JLabel("Most ugorhatsz!");
		lblMostUgorhatsz_1.setBounds(305, 82, 112, 14);
		imgPanel.add(lblMostUgorhatsz_1);
		
		JLabel lblMostUgorhatsz_2 = new JLabel("Most ugorhatsz!");
		lblMostUgorhatsz_2.setBounds(305, 192, 101, 14);
		imgPanel.add(lblMostUgorhatsz_2);
		
		JCheckBox stepHelper = new JCheckBox("Lépés segítőt mutat");
		stepHelper.setBounds(294, 284, 144, 23);
		stepHelper.setSelected(true);
		imgPanel.add(stepHelper);

		
		for (StoneType s : stones) {
			imgPanel.add(s.getLabel());
		}
		
		Algoritmusok.updateTable();

		frmMalom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					for (StoneType st : stones) {
						if (st.getVisible())
							if (isClicked(st, arg0)) {
								Algoritmusok.updateTable();
								frmMalom.repaint();
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
