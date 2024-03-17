package lk.ijse.dep.service;

public class HumanPlayer extends Player {

    Winner winner;

    public HumanPlayer(Board newBoard) {
        super(newBoard);
    }

    @Override
    public void movePiece(int col) {
        boolean human = board.isLegalMove(col);
        if (human) {
            board.updateMove(col, Piece.BLUE);
            board.getBoardUI().update(col, true);
            winner = board.findWinner();
            if (board.findWinner().getWinningPiece() != Piece.EMPTY) {
                board.getBoardUI().notifyWinner(winner);
            }
            if (!board.existLegalMove()) {
                board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        }
    }
}
