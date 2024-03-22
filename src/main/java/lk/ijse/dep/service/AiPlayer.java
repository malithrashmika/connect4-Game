package lk.ijse.dep.service;

public class AiPlayer extends Player {
    private final Board board;

    public AiPlayer(Board newBoard) {
        super(newBoard);
        this.board = newBoard;
    }

    @Override
    public void movePiece(int col) {
        int bestMove = findBestMoveForAI();

        if (bestMove != -1) {
            col = bestMove;
        } else {
            do {
                int range = Board.Num_of_cols;
                col = (int) (Math.random() * range);
            } while (!board.isLegalMove(col));
        }

        board.updateMove(col, Piece.GREEN);
        board.getBoardUI().update(col, false);

        if (board.findWinner().getWinningPiece() != Piece.EMPTY) {
            board.getBoardUI().notifyWinner(board.findWinner());
        } else if (board.existLegalMove()) {
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }
    }

    private int findBestMoveForAI() {
        for (int col = 0; col < Board.Num_of_cols; col++) {
            if (board.isLegalMove(col)) {
                int row = board.findNextAvailableSpot(col);
                board.updateMove(col, Piece.GREEN); // Simulate AI move

                if (board.findWinner().getWinningPiece() == Piece.GREEN) {
                    board.updateMove(col, row, Piece.EMPTY); // Undo simulation
                    return col; // Winning move found for AI
                }

                board.updateMove(col, row, Piece.EMPTY); // Undo simulation
            }
        }

        // Check for blocking moves for opponent (BLUE)
        for (int col = 0; col < Board.Num_of_cols; col++) {
            if (board.isLegalMove(col)) {
                int row = board.findNextAvailableSpot(col);
                board.updateMove(col, Piece.BLUE); // Simulate opponent move

                if (board.findWinner().getWinningPiece() == Piece.BLUE) {
                    board.updateMove(col, row, Piece.EMPTY); // Undo simulation
                    return col; // Blocking move found for opponent
                }

                board.updateMove(col, row, Piece.EMPTY); // Undo simulation
            }
        }

        return -1; // No winning or blocking move found
    }
}
