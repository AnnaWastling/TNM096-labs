%Shakey the robot

%actions
%go(x,y)
act(go(X,Y),
[connected(X,Y), in(s,X), on(floor)],
[in(s,X)],
[in(s,Y)]
).

%push(b,x,y)
act(push(B,X,Y),
[box(B), in(B,X), connected(X,Y), in(s,X), on(floor), switchedOn(X)],
[in(B,X), in(s,X)],
[in(s,Y), in(B,Y)]
).

%climbUp(b)
act(climbUp(B),
[in(s,X), on(floor), box(B), in(B, X)],
[on(floor)],
[on(B)]
).
%climbDown(B)
act(climbDown(B),
[box(B), on(B)],
[on(B)],
[on(floor)]
).
%turnOn(s)
act(turnOn(S),
[box(B), on(B), in(B, S), location(X), connected(S, X), switch(S), switchedOff(X)],
[switchedOff(X)],
[switchedOn(X)]
).
%turnOff(s)
act(turnOff(S),
[box(B), on(B), in(B, S), location(X), connected(S, X), switch(S), switchedOn(X)],
[switchedOn(X)],
[switchedOff(X)]
).

%States
goal_state(
[switchedOn(room2)]%in(s, room1), switchedOff(room1), in(b2, room2)
).

initial_state([
shakey(s),
floor,
on(floor),
in(s, room3),
box(b1),
box(b2),
box(b3),
box(b4),

in(b1, room1),
in(b2, room1),
in(b3, room1),
in(b4, room1),

switch(s1),
switch(s2),
switch(s3),
switch(s4),

switchedOn(room1),
switchedOff(room2),
switchedOff(room3),
switchedOn(room4),
switchedOn(corridor),

location(room1),
location(room2),
location(room3),
location(room4),

connected(room1,s1),
connected(room2,s2),
connected(room3,s3),
connected(room4,s4),
connected(room1,corridor),
connected(room2,corridor),
connected(room3,corridor),
connected(room4,corridor),

connected(s1,room1),
connected(s2,room2),
connected(s3,room3),
connected(s4,room4),
connected(s1,corridor),
connected(s2,corridor),
connected(s3,corridor),
connected(s4,corridor),

connected(corridor, room1),
connected(corridor, room2),
connected(corridor, room3),
connected(corridor, room4),
connected(corridor, s1),
connected(corridor, s2),
connected(corridor, s3),
connected(corridor, s4)

]).
