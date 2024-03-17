package lk.ijse.dep.service;

public interface Board {
    int Num_of_cols=6;
    int Num_of_rows=5;


    BoardUI getBoardUI();
    int findNextAvalibleSpot(int col);
    boolean isLegalMove(int col);
    boolean existLegalMove();
    void updateMove(int col,Piece move);
    Winner findWinner();

    void updateMove(int x, int y, Piece move);

    Piece[][] getPieces();
}
