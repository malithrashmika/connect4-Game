package lk.ijse.dep.service;


public class BoardImpl implements Board {

    private Piece[][] pieces;
    private BoardUI boardUI;


    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        pieces = new Piece[Num_of_cols][Num_of_rows];

        for (int i = 0; i < Num_of_cols; i++) {
            for (int j = 0; j < Num_of_rows; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }

    }

    public BoardImpl(Piece[][] copiedPieces) {
        Piece[][] pieces1;
        pieces1 = pieces;
        pieces1 = copiedPieces;
        this.pieces = pieces1;
    }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int i = 0; i < Num_of_rows; i++) {
            if (pieces[col][i] == Piece.EMPTY) {
                return (i);
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        for (int i = 0; i < Num_of_rows; i++) {
            if (pieces[col][i] == Piece.EMPTY) {
                return true;
            }
        }
        return false;

    }

    @Override
    public boolean existLegalMove() {
        for (int i = 0; i < Num_of_cols; i++) {
            for (int j = 0; j < Num_of_rows; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void updateMove(int col, Piece move) throws ArrayIndexOutOfBoundsException {
        int spot = findNextAvailableSpot(col);
        pieces[col][spot] = move;

    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row] = move;
    }

    @Override
    public Winner findWinner() {
        for (int row = 0; row < Num_of_rows; row++) {
            for (int cols = 0; cols < 3; cols++) {
                Piece piece = pieces[cols][row];
                if (piece != Piece.EMPTY && piece == pieces[cols + 1][row] && piece == pieces[cols + 2][row] && piece == pieces[cols + 3][row]) {
                    return new Winner(piece, cols, cols + 3, row, row);
                }
            }
        }
        for (int cols = 0; cols < Num_of_cols; cols++) {
            for (int row = 0; row < 2; row++) {
                Piece piece = pieces[cols][row];
                if (piece != Piece.EMPTY && piece == pieces[cols][row + 1] && piece == pieces[cols][row + 2] && piece == pieces[cols][row + 3]) {
                    return new Winner(piece, cols, cols, row, row + 3);
                }
            }
        }
        return new Winner(Piece.EMPTY, -1, -1, -1, -1);
    }


    /*@Override
    public Winner findWinner() {

        for (int i = 0; i < Num_of_cols; i++) {
            for (int j = 0; j <Num_of_rows ; j++) {
                if (pieces[i][j] == Piece.BLUE && pieces[i][j]==pieces[i][j+1]&&pieces[i][j]==pieces[i][j+2]&&pieces[i][j]==pieces[i][j+3]) {
                    green=0;
                    blue++;
                    if (blue == 4) {
                        return new Winner(Piece.BLUE,i,j,i,j+3);
                    }
                } else if (pieces[i][j] == Piece.GREEN&& pieces[i][j]==pieces[i][j+1]&&pieces[i][j]==pieces[i][j+2]&&pieces[i][j]==pieces[i][j+3]) {
                    blue=0;
                    green++;
                    if (green == 4) {
                        return new Winner(Piece.GREEN,i,j,i,j+3);
                    }
                }
            }
        }
        for (int i = 0; i < Num_of_cols; i++) {
            for (int j = 0; j <Num_of_rows ; j++) {
                if (pieces[i][j+1] == Piece.BLUE && pieces[i][j+1]==pieces[i][j+2]&&pieces[i][j+1]==pieces[i][j+3]&&pieces[i][j+1]==pieces[i][j+5]) {
                    green=0;
                    blue++;
                    if (blue == 4) {
                        return new Winner(Piece.BLUE,i,j+1,i,j+4);
                    }
                } else if (pieces[i][j+1] == Piece.GREEN && pieces[i][j+1]==pieces[i][j+2]&&pieces[i][j+1]==pieces[i][j+3]&&pieces[i][j+1]==pieces[i][j+5]) {
                    blue=0;
                    green++;
                    if (green == 4) {
                        return new Winner(Piece.GREEN,i,j+1,i,j+4);
                    }
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }*/
}
