package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import frameWork.Board;
import frameWork.Game;
import frameWork.Location;
import frameWork.Move;
import frameWork.Spot;
import id.PieceColor;
import id.PieceID;
import pieces.King;
import pieces.Piece;

public class UIBoard {

	private boolean logVisible;
	private boolean userMove;
	private boolean hasGameEnded;
	private int h = 400, w = 474;
	private boolean quisce;
	private int depth = 1;
	private PieceColor color = PieceColor.BLACK;
	private UIBoard b;

	private LinkedList<Location> possibleMovesLocations;
	private Location selected;
	private Location destination;
	private String whiteMove;
	private String blackMove;
	private Location lastCompMove;

	public static int moves = 0;
	private int rows = 8;
	private int columns = 8;
	private Color col1 = Color.WHITE;
	private Color col2 = new Color(12, 137, 28);
	private CustomJPanel[][] panels = new CustomJPanel[8][8];
	private JLabel[][] labels = new JLabel[8][8];
	private Game game;
	private JPanel panel;

	private ImageIcon black_rooki;
	private ImageIcon black_bishopi;
	private ImageIcon black_kingi;
	private ImageIcon black_queeni;
	private ImageIcon black_knighti;
	private ImageIcon black_pawni;
	private Image icon;

	private ImageIcon white_rooki;
	private ImageIcon white_bishopi;
	private ImageIcon white_kingi;
	private ImageIcon white_queeni;
	private ImageIcon white_knighti;
	private ImageIcon white_pawni;
	private JLabel lblStatus;
	private JTextArea moveText;
	private JFrame chessEngine;
	private static JTextArea logArea;
	private JButton btnNewButton;
	private JButton btnNew;
	private JButton btnResign;
	private JLabel lblRajarshiBasuc;

	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {

		} catch (InstantiationException ex) {

		} catch (IllegalAccessException ex) {

		} catch (javax.swing.UnsupportedLookAndFeelException ex) {

		}

		UIBoard b = new UIBoard();
		b.show();
		b.hasGameEnded = true;
	}

	public UIBoard() {

		chessEngine = new JFrame();
		b = this;
		chessEngine.setResizable(false);
		chessEngine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessEngine.setSize(w, h);
		chessEngine.setTitle("JCERB");

		possibleMovesLocations = new LinkedList<>();

		try {
			icon = ImageIO.read(UIBoard.class.getResource("/IconImage2.png"));
			chessEngine.setIconImage(icon);

			black_rooki = new ImageIcon(ImageIO.read(UIBoard.class.getResource("/black_Rook.png")));
			black_queeni = new ImageIcon(ImageIO.read(UIBoard.class.getResource("/black_queen.png")));
			black_kingi = new ImageIcon(ImageIO.read(UIBoard.class.getResource("/black_King.png")));
			black_bishopi = new ImageIcon(ImageIO.read(UIBoard.class.getResource("/black_bishop.png")));
			black_knighti = new ImageIcon(ImageIO.read(UIBoard.class.getResource("/black_Knight.png")));
			black_pawni = new ImageIcon(ImageIO.read(UIBoard.class.getResource("/black_Pawn.png")));

			white_rooki = new ImageIcon(ImageIO.read(UIBoard.class.getResource("/white_Rook.png")));
			white_queeni = new ImageIcon(ImageIO.read(UIBoard.class.getResource("/white_queen.png")));
			white_kingi = new ImageIcon(ImageIO.read(UIBoard.class.getResource("/white_King.png")));
			white_bishopi = new ImageIcon(ImageIO.read(UIBoard.class.getResource("/white_bishop.png")));
			white_knighti = new ImageIcon(ImageIO.read(UIBoard.class.getResource("/white_Knight.png")));
			white_pawni = new ImageIcon(ImageIO.read(UIBoard.class.getResource("/white_Pawn.png")));

		} catch (Exception e) {
			e.printStackTrace();

		}

		// rook=ImageIO.read(new File("res/Rook.png"));
		// rooki=new ImageIcon(rook);

		panel = new JPanel();
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				panels[x][y] = new CustomJPanel(new Location(x, y));
				panels[x][y].setLayout(new FlowLayout());
				panels[x][y].addMouseListener(new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						if (hasGameEnded || !userMove)
							return;
						if (selected == null) {
							selected = ((CustomJPanel) e.getComponent()).getRowAndCol();
							Spot sp = Game.getCurrentPosition().getSpot(selected);
							if (sp.getOccupant() != null && sp.getOccupant().getColor() != color) {

								// add moves destinations
								LinkedList<Move> list = Game.getCurrentPosition()
										.trimIllegalMoves(sp.getOccupant().getMoveList(Game.getCurrentPosition()));
								for (Move m : list) {
									possibleMovesLocations.add(new Location(m.getTo().NumberForm()));
								}

								// add moves for short and long castle if
								// selected piece is king
								if (sp.getOccupant().getID() == PieceID.KING) {
									if (((King) sp.getOccupant()).canCastleShort(Game.getCurrentPosition())) {
										possibleMovesLocations.add((sp.getOccupant().getColor() == PieceColor.WHITE)
												? new Location(7, 6) : new Location(0, 6));

									}
									if (((King) sp.getOccupant()).canCastleLong(Game.getCurrentPosition())) {
										possibleMovesLocations.add((sp.getOccupant().getColor() == PieceColor.WHITE)
												? new Location(7, 2) : new Location(0, 2));

									}

								}

								highlight(possibleMovesLocations);
							} else {
								selected = null;
							}
						} else {
							boolean has = false;
							destination = ((CustomJPanel) e.getComponent()).getRowAndCol();
							for (Location l : possibleMovesLocations) {
								if (l.equals(destination))
									has = true;
							}
							unHighlight(possibleMovesLocations);

							possibleMovesLocations.clear();

							if (has) {
								if (lastCompMove != null) {
									System.out.println(lastCompMove);
									unHighlightLastMove(lastCompMove);
								} else {
									System.out.println("Got a Null place");
								}
								execUserMove(selected, destination);

								new Thread() {
									public void run() {
										execCompMove();
										highlightLastMove(lastCompMove);
									}
								}.start();
							}

							selected = null;
						}
						destination = null;

					}

					public void mouseEntered(MouseEvent arg0) {

					}

					public void mouseExited(MouseEvent arg0) {

					}

					public void mousePressed(MouseEvent arg0) {

					}

					public void mouseReleased(MouseEvent arg0) {

					}
				});
				labels[x][y] = new JLabel();
				panels[x][y].add(labels[x][y]);
				panel.add(panels[x][y]);
			}
		}

		panel = new JPanel();

		chessEngine.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(4, 25, 300, 300);
		chessEngine.getContentPane().add(panel);
		GridLayout gbl_panel = new GridLayout(rows, columns);

		panel.setLayout(gbl_panel);

		lblStatus = new JLabel("");
		lblStatus.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 14));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(4, 324, 300, 23);
		chessEngine.getContentPane().add(lblStatus);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(314, 25, 144, 300);
		chessEngine.getContentPane().add(scrollPane);

		moveText = new JTextArea();
		moveText.setEditable(false);
		moveText.setFont(new Font("Verdana", Font.PLAIN, 10));
		moveText.setForeground(new Color(139, 0, 0));
		moveText.setBackground(new Color(248, 248, 255));
		scrollPane.setViewportView(moveText);

		JLabel label = new JLabel("Moves");
		label.setForeground(new Color(160, 82, 45));
		label.setFont(new Font("Verdana", Font.BOLD, 12));
		label.setBackground(new Color(245, 245, 220));
		scrollPane.setColumnHeaderView(label);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 370, 448, 240);
		chessEngine.getContentPane().add(scrollPane_1);

		logArea = new JTextArea();
		logArea.setEditable(false);
		logArea.setFont(new Font("Verdana", Font.PLAIN, 10));
		scrollPane_1.setViewportView(logArea);
		scrollPane_1.setVisible(logVisible);

		btnNewButton = new JButton("Show Log");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logVisible = !logVisible;
				if (btnNewButton.getText().equalsIgnoreCase("Show Log")) {
					btnNewButton.setText("Hide Log");
					h = 650;
				} else {
					h = 400;
					btnNewButton.setText("Show Log");
				}
				chessEngine.setSize(w, h);
				scrollPane_1.setVisible(logVisible);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBounds(347, 335, 107, 31);
		chessEngine.getContentPane().add(btnNewButton);

		btnNew = new JButton("NEW");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StartScreen(b);

			}
		});
		btnNew.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		btnNew.setBackground(new Color(0, 0, 0));
		btnNew.setForeground(Color.WHITE);
		btnNew.setBounds(121, 0, 79, 23);
		chessEngine.getContentPane().add(btnNew);

		btnResign = new JButton("RESIGN");
		btnResign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblStatus.setText(color.reverse().toString() + " Resigns");
				hasGameEnded = true;
			}
		});
		btnResign.setBackground(new Color(0, 0, 0));
		btnResign.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnResign.setForeground(Color.WHITE);
		btnResign.setBounds(4, 0, 107, 23);
		chessEngine.getContentPane().add(btnResign);

		lblRajarshiBasuc = new JLabel("Rajarshi Basu (c) 2017  v7.2");
		lblRajarshiBasuc.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblRajarshiBasuc.setBounds(314, 6, 144, 14);
		chessEngine.getContentPane().add(lblRajarshiBasuc);
		
		JButton btnDebugPrint = new JButton("Debug Print");
		btnDebugPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.debugPrint();
			}
		});
		btnDebugPrint.setBounds(215, 2, 89, 23);
		chessEngine.getContentPane().add(btnDebugPrint);

		colorInit();

	}

	private void colorInit() {
		Color temp;

		for (int i = 0; i < rows; i++) {
			if (i % 2 == 0) {
				temp = col1;
			} else {
				temp = col2;
			}
			for (int j = 0; j < columns; j++) {

				panels[i][j].setBackground(temp);

				if (temp.equals(col1)) {
					temp = col2;
				} else {
					temp = col1;
				}
				panel.add(panels[i][j]);
				panels[i][j].setColor();
			}
		}
	}

	private synchronized void showPieces(Board board) {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				labels[x][y].setIcon(null);
			}
		}

		LinkedList<Piece> piece = board.getBlackPieces();
		for (Piece p : piece) {

			if (p.getID() == PieceID.ROOK) {
				int i = p.getLocation().getRow();
				int j = p.getLocation().getCol();
				labels[i][j].setIcon(black_rooki);
			} else if (p.getID() == PieceID.KING) {
				int i = p.getLocation().getRow();
				int j = p.getLocation().getCol();
				labels[i][j].setIcon(black_kingi);
			} else if (p.getID() == PieceID.QUEEN) {
				int i = p.getLocation().getRow();
				int j = p.getLocation().getCol();
				labels[i][j].setIcon(black_queeni);
			} else if (p.getID() == PieceID.BISHOP) {
				int i = p.getLocation().getRow();
				int j = p.getLocation().getCol();
				labels[i][j].setIcon(black_bishopi);
			} else if (p.getID() == PieceID.KNIGHT) {
				int i = p.getLocation().getRow();
				int j = p.getLocation().getCol();
				labels[i][j].setIcon(black_knighti);
			} else if (p.getID() == PieceID.PAWN) {
				int i = p.getLocation().getRow();
				int j = p.getLocation().getCol();
				labels[i][j].setIcon(black_pawni);
			}

		}
		piece = board.getWhitePieces();
		for (Piece p : piece) {
			if (p.getID() == PieceID.ROOK) {
				int i = p.getLocation().getRow();
				int j = p.getLocation().getCol();
				labels[i][j].setIcon(white_rooki);
			} else if (p.getID() == PieceID.KING) {
				int i = p.getLocation().getRow();
				int j = p.getLocation().getCol();
				labels[i][j].setIcon(white_kingi);
			} else if (p.getID() == PieceID.QUEEN) {
				int i = p.getLocation().getRow();
				int j = p.getLocation().getCol();
				labels[i][j].setIcon(white_queeni);
			} else if (p.getID() == PieceID.BISHOP) {
				int i = p.getLocation().getRow();
				int j = p.getLocation().getCol();
				labels[i][j].setIcon(white_bishopi);
			} else if (p.getID() == PieceID.KNIGHT) {
				int i = p.getLocation().getRow();
				int j = p.getLocation().getCol();
				labels[i][j].setIcon(white_knighti);
			} else if (p.getID() == PieceID.PAWN) {
				int i = p.getLocation().getRow();
				int j = p.getLocation().getCol();
				labels[i][j].setIcon(white_pawni);
			}

		}

	}

	private synchronized void highlight(LinkedList<Location> l) {
		for (Location ll : l) {
			panels[ll.getRow()][ll.getCol()].highlight();
		}
	}

	private synchronized void highlightLastMove(Location l) {
		// if(l==null)System.out.println("vdshjfgvbk");
		panels[l.getRow()][l.getCol()].highlight(Color.YELLOW);
	}

	private synchronized void unHighlightLastMove(Location l) {
		panels[l.getRow()][l.getCol()].setDefaultColor();
	}

	private synchronized void unHighlight(LinkedList<Location> l) {
		for (Location ll : l) {
			panels[ll.getRow()][ll.getCol()].setDefaultColor();
		}
	}

	private synchronized void execUserMove(Location from, Location to) {
		String r = game.playerMove(from.getRow() * 10 + from.getCol(), to.getRow() * 10 + to.getCol());
		showPieces(Game.getCurrentPosition());
		if (color.reverse() == PieceColor.WHITE) {
			moves++;
			whiteMove = r;
		} else {
			blackMove = r;
			printMoves();
		}
		PieceColor cll = null;
		if (Game.getCurrentPosition().isCheckMate(cll = PieceColor.WHITE)
				|| Game.getCurrentPosition().isCheckMate(cll = PieceColor.BLACK)) {
			lblStatus.setText(cll + " is mated");
		} else
			lblStatus.setText("Thinking... ");
	}

	private synchronized void execCompMove() {

		userMove = false;
		String r = game.compMove();

		showPieces(Game.getCurrentPosition());

		if (color == PieceColor.WHITE) {
			moves++;
			whiteMove = r;
		} else {
			blackMove = r;
			printMoves();
		}
		PieceColor cll = null;
		if (Game.getCurrentPosition().isCheckMate(cll = PieceColor.WHITE)
				|| Game.getCurrentPosition().isCheckMate(cll = PieceColor.BLACK)) {
			lblStatus.setText(cll + " is mated");
			hasGameEnded = true;
		} else if (Game.getCurrentPosition().isStaleMate(cll = PieceColor.WHITE)
				|| Game.getCurrentPosition().isStaleMate(cll = PieceColor.BLACK)) {
			lblStatus.setText(cll + " is stalemated");
			hasGameEnded = true;
		}else{
			lblStatus.setText("Your Move");
		}

		lastCompMove = (game.last.getTo());
		
		userMove = true;
		
	}

	private synchronized void printMoves() {
		moveText.append(moves + ": " + whiteMove + "      " + blackMove + " \n");
	}

	public synchronized void show() {
		chessEngine.setVisible(true);
	}

	public synchronized static void println(String msg) {
		logArea.append(msg + " \n ");
	}

	public synchronized void newGame(GameStateInfo i) {
		userMove = true;
		this.quisce = i.isQuiesce();
		this.color = i.getC();
		this.depth = i.getDepth();
		hasGameEnded = false;
		logArea.setText("");
		possibleMovesLocations.clear();
		selected = null;
		destination = null;
		lastCompMove = null;
		moves = 0;
		moveText.setText("");
		lblStatus.setText("");
		colorInit();
		// MoveType.LONGCASTLE_BLACK.init();
		// MoveType.LONGCASTLE_WHITE.init();
		// MoveType.SHORTCASTLE_BLACK.init();
		// MoveType.SHORTCASTLE_WHITE.init();

		try {

			game = new Game(color, depth, quisce, i.getTime());
			game.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// panels[1][1].add(new JLabel(new ImageIcon(rook)));
		if (color == PieceColor.WHITE) {
			execCompMove();
		}
		showPieces(Game.getCurrentPosition());

	}
}