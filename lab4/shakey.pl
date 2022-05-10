%Shakey the robot

%actions
%go(x,y)
act(go(X,Y),
[location(X), location(Y), door(X,Y), shakey(X)],
[],
[shakey(Y)],
).

%push(b,x,y)
act(push(B,X,Y),
[box(B), boxpos(B,X), door(X,Y), shakey(X), location(X), location(Y),  ], %we are here!
).
%predicate box(...)
%lightOn=true for shakey to find a box
%shakey must climb onto box to switch lightswitch
%climbUp(b)
act(climbUp(B),[]).
%climbDown(B)
act(climbDown(B),[]).
%turnOn(s)
act(turnOn(S),
[],
[],
[]
).
%turnOff(s)
act(turnOff(S),[]).
%States
goal_state([location(room1)])
initial_state([
box(a),
box(b),
box(c),
box(d),
boxPos(a, room1),
boxPos(b, room1),
boxPos(c, room1),
boxPos(d, room1),
diff(a,b),
diff(a,c),
diff(a,d),
diff(b,a),
diff(b,c),
diff(b,d),
diff(c,a),
diff(c,b),
diff(c,d),
diff(d,a),
diff(d,b),
diff(d,d),
switch1(on),
switch2(off),
switch3(off),
switch4(on),
switch(one,room1),
switch(two,room2),
switch(three,room3),
switch(four,room4),
location(room1),
location(room2),
location(room3),
location(room4),
location(corridor),
shakey(room3),
door(room1,corridor),
door(room2,corridor),
door(room3,corridor),
door(room4,corridor),
door(corridor, room1),
door(corridor, room2),
door(corridor, room3),
door(corridor, room4),
]).
