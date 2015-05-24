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
		
		final JLabel lblKvekSzma_1 = new JLabel("Kövek száma: " + Malom.playerOne.getStones());
		lblKvekSzma_1.setBounds(305, 32, 112, 14);
		imgPanel.add(lblKvekSzma_1);
		
		final JLabel lblKvekATablan_1 = new JLabel("Kövek a táblán: " + Malom.playerOne.getOnBoardStones());
		lblKvekATablan_1.setBounds(305, 57, 112, 14);
		imgPanel.add(lblKvekATablan_1);
		
		final JLabel lblKvekSzma_2 = new JLabel("Kövek száma: " + Malom.playerTwo.getStones());
		lblKvekSzma_2.setBounds(305, 142, 101, 14);
		imgPanel.add(lblKvekSzma_2);
		
		final JLabel lblKvekATablan_2 = new JLabel("Kövek a táblán: " + Malom.playerTwo.getOnBoardStones());
		lblKvekATablan_2.setBounds(305, 167, 112, 14);
		imgPanel.add(lblKvekATablan_2);
		
		JLabel lblMostUgorhatsz_1 = new JLabel("Most ugorhatsz!");
		lblMostUgorhatsz_1.setBounds(305, 82, 112, 14);
		if (Malom.playerOne.canJump())
			lblMostUgorhatsz_1.setVisible(true);
		else
			lblMostUgorhatsz_1.setVisible(false);
		imgPanel.add(lblMostUgorhatsz_1);
		
		JLabel lblMostUgorhatsz_2 = new JLabel("Most ugorhatsz!");
		lblMostUgorhatsz_2.setBounds(305, 192, 101, 14);
		if (Malom.playerTwo.canJump())
			lblMostUgorhatsz_2.setVisible(true);
		else
			lblMostUgorhatsz_2.setVisible(false);
		imgPanel.add(lblMostUgorhatsz_2);
		
		JCheckBox stepHelper = new JCheckBox("Lépés segítőt mutat");
		stepHelper.setBounds(294, 284, 144, 23);
		stepHelper.setSelected(true);
		imgPanel.add(stepHelper);

		for (StoneType s : stones) {
			imgPanel.add(s.getLabel());
		}
		
		Algoritmusok.updateTable();
		Algoritmusok.showAvailableSpots();
		
		frmMalom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					for (StoneType st : stones) {
						if (st.getVisible())
							if (Algoritmusok.isClicked(st, arg0)) {
								Algoritmusok.updateTable();
								frmMalom.repaint();
							}
					}
				}

				if (arg0.getButton() == MouseEvent.BUTTON3) {
					Algoritmusok.putStone(arg0); 
					frmMalom.repaint();
					lblKvekSzma_1.setText("Kövek száma: " + Malom.playerOne.getStones());
					lblKvekATablan_1.setText("Kövek a táblán: " + Malom.playerOne.getOnBoardStones());
					lblKvekSzma_2.setText("Kövek száma: " + Malom.playerTwo.getStones());
					lblKvekATablan_2.setText("Kövek a táblán: " + Malom.playerTwo.getOnBoardStones());
				}
			}
		});

		frmMalom.setVisible(true);
	}
}
