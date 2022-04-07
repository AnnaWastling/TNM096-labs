#include "node.cpp"

int main()
{
    // Define the start state, randomized
    State current;
    current.puzzleState[0] = 3;
    current.puzzleState[1] = 6;
    current.puzzleState[2] = 0;
    current.puzzleState[3] = 1;
    current.puzzleState[4] = 4;
    current.puzzleState[5] = 2;
    current.puzzleState[6] = 8;
    current.puzzleState[7] = 5;
    current.puzzleState[8] = 7;

    int zeroPos = 2;

    Board board(current, zeroPos);

    // board.print();
    // board.print(board.getGoal());

    return 0;
}