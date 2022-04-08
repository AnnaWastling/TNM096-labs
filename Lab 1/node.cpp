#include "node.h"
State::State(int puzzle[], int cost) : cost{ cost } {
	for (int i = 0; i < 8; i++) {
		puzzleState[i] = puzzle[i];
	}
};


Board::Board(State curr, int zero) : zeroPos{ zero }
{
	// Define the goal state
	for (int i = 0; i < size(goal.puzzleState); i++)
	{
		goal.puzzleState[i] = i;
	};

	current = curr;
};

void Board::print()
{
	cout << "The current state is" << endl;
	for (auto index : current.puzzleState)
	{
		cout << index << endl;
	}
};

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
		moves = { 1, 3 };
		break;
	case 1:
		moves = { 0, 2, 4 };
		break;
	case 2:
		moves = { 1, 5 };
		break;
	case 3:
		moves = { 4, 0, 6 };
		break;
	case 4:
		moves = { 3, 1, 7, 5 };
		break;
	case 5:
		moves = { 4, 2, 8 };
		break;
	case 6:
		moves = { 7, 3 };
		break;
	case 7:
		moves = { 6, 8, 4 };
		break;
	case 8:
		moves = { 7, 5 };
		break;

	default:
		break;
	}
	return moves;
};

int Board::hamming()
{
	int heuristic = 0;

	for (int i = 0; i < size(current.puzzleState); i++)
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

	for (int i = 0; i < size(current.puzzleState); i++)
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

bool Board::isGoal() {
	for (int i = 0; i < size(current.puzzleState); i++) {
		if (current.puzzleState[i] != goal.puzzleState[i]) {
			return false;
		}
	}
	return true;
}

void Board::moveZero(int move) {
	swap(zeroPos, current.puzzleState[move]);
	zeroPos = move;
}

void Board::solveBoard(Board& b) {
	//1. expand first state/node in openlist
	//2. check if state/node is in closedlist
	//3. move state/node to closedlist
	//4. add all states/nodes with possible moves to openlist
	//5. get first state/node in openlist

	/*from labassistent*/
	//store path == closedlist
	//zero is in last place
	// create new state for every possible movement
	// cost get larger the further down you get, add to cost at each level. do it in a star 

	//add root state to the openlist
	openList.push(b.current);
	//heap to get states with smallest vlues at top
	//make_heap(closedList.begin(), closedList.end());

	vector<State>::iterator it;
	vector<int>moves;

	while (!openList.empty()) {
		//get smallest value state
		State temp = openList.top();
		//remove state from openlist
		openList.pop();
		//add to closed list
		closedList.push_back(temp);
		if (b.isGoal()) {
			cout << " Goal is found! " << endl;
			cout << " The cost is: " << temp.cost << endl;
			b.print();
			break;
		}

		moves = allowedMoves();

		for (int i = 0; i < moves.size(); i++) {
			State newState = State(temp.puzzleState, temp.cost + 1);
			//create a new board for the state, needed to update current
			Board newBoard(newState, zeroPos);
			newBoard.moveZero(moves[i]);


			//need to be after because we use current i manhattan
			newState.heuristic = newBoard.hamming();
			newState.evaluation = newState.cost + newState.heuristic;
			//check if already exist in closed list
			it = find(closedList.begin(), closedList.end(), newState);

			//need to specify own operator==
			if (it == closedList.end()) {
				openList.push(newState);

			}
		}
	}

}

bool State::operator==(const State& s)const
{
	for (int i = 0; i < 9; i++)
	{
		if (puzzleState[i] != s.puzzleState[i])
		{
			return false;
		}
	}
	return true;
}