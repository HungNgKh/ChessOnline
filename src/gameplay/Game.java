package gameplay;

import Client.Client_server;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class Game extends JFrame{
        private Client_server client;
	private JPanel contentPane;
	private Board board;
	private Player player ;
	private boolean canMove =false;
	private ArrayList<Square> movableSquare = new ArrayList<Square>();
	private Square selectedSquare;
	/**
	 * Launch the application.
	 */
	
	private void loadComponents(String name){
		setTitle(name);
		setResizable(false);
		board = new Board(10, 10);
		board.LoadPieces(player.getColor());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout());
		setContentPane(contentPane);		
		contentPane.add(board);
		pack();
		
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++){
				board.squares[i][j].addActionListener(new ActionListener() {			
					@Override
					public void actionPerformed(ActionEvent e) {
						Square listening = (Square)e.getSource();				
						click(listening.x, listening.y);				
					}
				});
				
			}
            setLocationRelativeTo(null);    
	}
	
	public Game(Client_server client,int color,String name){
		super();
                this.client = client;
		player = new Player(PieceColor.values()[color]);
		loadComponents(name);
                this.client.remote_Game(this);
                if(color == 1 ) canMove(false);
                else canMove(true);
	}
	
	
	private void click(int x,int y){
		if(selectedSquare!=null)
			selectedSquare.setBorder(UIManager.getBorder("Button.border"));	
		selectedSquare = board.squares[x][y];
		Move command = selectedSquare.availableMove;
		Piece clickedpiece = selectedSquare.getOccupyingPiece();
		for(Square i : movableSquare){
			i.removeMouseListener(board.moverable);
			i.setBorder(UIManager.getBorder("Button.border"));	
			i.availableMove = null;
		}
		movableSquare.clear();
		if(command != null){
                        MOVE(command);
                }
		else if(clickedpiece != null){
			selectedSquare.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(51, 102, 102)), new LineBorder(new Color(0, 51, 51), 4)));
			for(Move i : clickedpiece.getMoves()){
				int dx = i.desx;
				int dy = i.desy;
				board.squares[dx][dy].addMouseListener(board.moverable);
				board.squares[dx][dy].setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(51, 102, 102)), new LineBorder(new Color(255, 204, 0), 4)));
				board.squares[dx][dy].availableMove = i;
				movableSquare.add(board.squares[dx][dy]);
			}
		}
	}
	
        public void MOVE(Move command)
        {
            if(!canMove) return;
            client.getTransferMethod().SEND("MOVE");
            client.getTransferMethod().SEND(command);
            updateBoard(command);
            canMove(false);
        }
        public void updateBoard(Move command)
        {
            command.execute(board);
        }
        public void canMove(boolean b)
        {
            canMove = b;
        }
}
