package ru.kostikov.figures;

import ru.kostikov.board.*;
import ru.kostikov.players.Player;

/**
 * Created by Алексей on 26.07.2016.
 */
public abstract class Figure {
    /** Игральная доска */
    protected Cell[][] board;

    /** Возможные ходы фигуры */
    private   Cell[]   moves = null;

    /** Ячейка в которой находится фигура */
    protected Cell     cell;

    /** Тип фигуры белые\черные */
    protected Player   player;

    /** Возможные ходы фигуры  */
    protected Cell[] allmoves = new Cell[24];

    /** Стороны в которые может ходить фигура*/
    protected int[] offsetSideX;

    protected int[] offsetSideY;

    /** Кол-во ячеек на которое может шагнуть фигура*/
    protected int stepsCnt;


    /**
     * Конструктор, принимаем игрока(белые или черные)
     * @param player
     */
    Figure(Player player){
        this.player = player;
    }

    /**
     * Установка, где находится фигура
     * @param cell
     */
    public void setCell(Cell cell){
        this.cell = cell;
    }

    /**
     * @param cellTo
     * @return
     */
    public boolean moveTo(Cell cellTo) {
        boolean result = false;

        if (cellTo != null) {
            // Просчитываем все возможные ходы
            this.moves = calcAllMoves();

            // Если для эта фгура не стоит на какй-то клетке
            if (this.cell == null) {
                // Устанавливаем туда куда ходим
                result = cellTo.setFigure(this);
                result = true;
            }
            // Если ячейки куда и откуда не равны, можно ходить
            else if (!this.cell.getName().equals(cellTo.getName())) {
                if (this.moves != null){
                    for(Cell move: this.moves){
                        if(move != null && move.getName() == cellTo.getName()){
                            Cell preCell = this.cell;

                            result = cellTo.setFigure(this);
                            if (result){
                                preCell.clearCell();
                            }
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Устанавливаем поле где находится фигура
     * @param board
     */
    public void setBoard(Cell[][] board){
        this.board = board;
    }


    /**
     * Установка поведения фигуры
     * @param stepsCnt      кол-во клеток на которое может ходить фигура за ход
     * @param offsetSideX   смещения координат для сторон на которые может ходить фигура
     * @param offsetSideY
     */
    protected void setBehavior(int stepsCnt, int[] offsetSideX, int[] offsetSideY){
        this.stepsCnt    = stepsCnt;
        this.offsetSideX = offsetSideX;
        this.offsetSideY = offsetSideY;
    }

    /**
     * Расчет всех возможных ходов для фигуры в текущей ячейке
     * @return
     */
    protected Cell[] calcAllMoves(){
        int moveCnt        = 0;

        for(int side = 0; side < this.offsetSideX.length; side++){
            for(int  i = 1; i <= this.stepsCnt; i++){
                // Проверяем выход за границы поля
                if (((this.cell.getX() + this.offsetSideX[side]*i) >= 0 && (this.cell.getX() + this.offsetSideX[side]*i) < this.board.length) &&
                    ((this.cell.getY() + this.offsetSideY[side]*i) >= 0 && (this.cell.getY() + this.offsetSideY[side]*i) < this.board[this.cell.getX()].length)){

                    Cell movingCell = this.board[this.cell.getX() + this.offsetSideX[side]*i][this.cell.getY() + this.offsetSideY[side]*i];

                    if (movingCell.getFigure() == null){
                        this.allmoves[moveCnt++] = movingCell;
                    }else{
                        break;
                    }
                }else{
                    break;
                }
            }
        }

        return this.allmoves;
    }

}