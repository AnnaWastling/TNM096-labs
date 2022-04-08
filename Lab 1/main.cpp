#include "node.h"

int main()
{
    // Define the start state, randomized
    State start;
    start.puzzleState[0] = 7;
    start.puzzleState[1] = 2;
    start.puzzleState[2] = 4;
    start.puzzleState[3] = 5;
    start.puzzleState[4] = 0;
    start.puzzleState[5] = 6;
    start.puzzleState[6] = 8;
    start.puzzleState[7] = 3;
    start.puzzleState[8] = 1;

    int zeroPos = 4;

    Board board(start, zeroPos);

    board.solveBoard(board);

    board.print();
    //priority_queue<int, vector<int>, greater<int>> openList;

    //const auto data = {8,5,2,1,4,3 };
    //for (int n : data) {
    //    openList.push(n);
    //    cout<<(openList.top());

    //}




    return 0;
}