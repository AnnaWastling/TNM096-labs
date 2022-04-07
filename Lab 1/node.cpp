#include "node.h"

Board::Board(State curr, int zero) : zeroPos{zero}
{
    // Define the goal state
    for (int i = 0; i < sizeof(goal.puzzleState); i++)
    {
        goal.puzzleState[i] = i;
    };

    current = curr;
}

void Board::print()
{
    cout << "The current state is" << endl;
    for (auto index : current.puzzleState)
    {
        cout << index << endl;
    }
}

State Board::getGoal()
{
    return goal;
};

std::vector<int> Board::allowedMoves()
{
    std::vector<int> moves;

    switch (zeroPos)
    {
    case 0:
        moves = {1, 3};
        break;
    case 1:
        moves = {0, 2, 4};
        break;
    case 2:
        moves = {1, 5};
        break;
    case 3:
        moves = {4, 0, 6};
        break;
    case 4:
        moves = {3, 1, 7, 5};
        break;
    case 5:
        moves = {4, 2, 8};
        break;
    case 6:
        moves = {7, 3};
        break;
    case 7:
        moves = {6, 8, 4};
        break;
    case 8:
        moves = {7, 5};
        break;

    default:
        break;
    }
    return moves;
};

int Board::hamming()
{
    int heuristic = 0;

    for (int i = 0; i < sizeof(current.puzzleState); i++)
    {
        if (current.puzzleState[i] != goal.puzzleState[i])
        {
            ++heuristic;
        }
    }

    return heuristic;
};

int Board::manhattan()
{
    int heuristic = 0;

    for (int i = 0; i < sizeof(current.puzzleState); i++)
    {
        if (current.puzzleState[i] != goal.puzzleState[i])
        {                        // if tile is in wrong place
            int atCol = (i % 3); // ex. i = 4 gives 4 % 3 = 1, where the colums are [0,1,2]
            int atRow = (i / 3); // ex. i = 4 gives 4 / 3 = 1.33.. = int 1, where the rows are [0,1,2]

            int corrCol = goal.puzzleState[goal.puzzleState[i]] % 3;
            int corrRow = goal.puzzleState[goal.puzzleState[i]] / 3;

            heuristic += abs(atRow - corrRow) + abs(atCol - corrCol);
        }
    };

    return heuristic;
}