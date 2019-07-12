%**********************************************
% *  341  Programming Languages               *
% *  Fall 13.12.2017  -Parts(1,2,3,4)         *
% *  Author: 141044073                        *
% *  		Muhammet Bedirhan  ÇAÐLAR     *
% *	        Prolog                        *
% *********************************************

%**************** PART-1 **********************

flight(istanbul,izmir,3).
flight(istanbul,ankara,5).
flight(istanbul,trabzon,3).
flight(izmir,ankara,6).
flight(izmir,antalya,1).
flight(izmir,istanbul,3).
flight(ankara,istanbul,5).
flight(ankara,konya,8).
flight(ankara,trabzon,2).
flight(ankara,izmir,6).
flight(trabzon,ankara,2).
flight(trabzon,istanbul,3).
flight(konya,ankara,8).
flight(konya,kars,5).
flight(konya,diyarbakir,1).
flight(kars,konya,5).
flight(kars,gaziantep,3).
flight(diyarbakir,konya,1).
flight(diyarbakir,antalya,5).
flight(antalya,diyarbakir,5).
flight(antalya,izmir,1).
flight(gaziantep,kars,3).
flight(edirne,edremit,5).
flight(edremit,erzincan,7).
flight(edremit,edirne,5).
flight(erzincan,edremit,7).

route(X,Y,C) :- flight(X,Y,C).
route(X,Y,C) :- flight(Y,X,C).

route(X,Y,C) :- flight(X,Z,P),
		route(Z,Y,D),C is P+D.

%********************* PART-2 ******************
distance(istanbul,izmir,3).
distance(istanbul,ankara,5).
distance(istanbul,trabzon,3).
distance(izmir,ankara,6).
distance(izmir,antalya,1).
distance(izmir,istanbul,3).
distance(ankara,istanbul,5).
distance(ankara,konya,8).
distance(ankara,trabzon,2).
distance(ankara,izmir,6).
distance(trabzon,ankara,2).
distance(trabzon,istanbul,3).
distance(konya,ankara,8).
distance(konya,kars,5).
distance(konya,diyarbakir,1).
distance(kars,konya,5).
distance(kars,gaziantep,3).
distance(diyarbakir,konya,1).
distance(diyarbakir,antalya,5).
distance(antalya,diyarbakir,5).
distance(antalya,izmir,1).
distance(gaziantep,kars,3).
distance(edirne,edremit,5).
distance(edremit,erzincan,7).
distance(edremit,edirne,5).
distance(erzincan,edremit,7).

%distance(X,Y,Z) :- distance(X,Y,Z).
%distance(X,Y,Z) :- distance(Y,X,Z).

croute(X,Y,Z) :- flight(X,Y),distance(X,Y,Z) ;
		 flight(Y,X) ,distance(Y,X,Z).


%********************* PART-3 ******************
when(a,10).
when(b,12).
when(c,11).
when(d,16).
when(e,17).
when(f,18).
when(g,18).

where(a,101).
where(b,104).
where(c,102).
where(d,103).
where(e,103).
where(f,101).
where(g,102).

enroll(1,a).
enroll(1,b).
enroll(2,a).
enroll(3,b).
enroll(4,c).
enroll(5,d).
enroll(6,d).
enroll(6,a).

enroll(7,e).
enroll(7,f).
enroll(8,b).
enroll(8,f).
enroll(9,c).
enroll(9,e).

enroll(10,g).
enroll(10,d).
enroll(11,g).
enroll(11,d).

schedule(S,P,T) :- enroll(S,C),where(C,P),when(C,T).

usage(P,T) :- where(C,P),when(C,T).

conflict(X,Y) :- where(X,C1),where(Y,C2),when(X,P1),when(Y,P2),A is P1-1,B is P2-1,
                 ((C1==C2),!;((P1==P2),!;(P1==B),!;(P2==A))).

meet(X,Y) :- enroll(X,C),where(C,P1),when(C,T1),

	     enroll(Y,C1),where(C1,P2),when(C1,T2),A is T1-1,B is T2-1,
                 ((P1==P2),((T2==A),!;(T1==B),!;(T1==T2))).



%*********************** PART-4 ******************


intersect([], _, []).

intersect([Head1|Tail1], List2, [Head1|Res]) :- member(Head1, List2),intersect(Tail1, List2, Res).
intersect([_|Tail1], List2, Res) :- intersect(Tail1, List2, Res).


union([],[],[]).
union(List1,[],List1).

union(List1, [Head2|Tail2], [Head2|Output]):- \+(member(Head2,List1)), union(List1,Tail2,Output).
union(List1, [Head2|Tail2], Output):- member(Head2,List1), union(List1,Tail2,Output).


flatten(Liste, [Liste]).

flatten([], []) :- !.
flatten([Liste|Ls], flat_list) :- !,flatten(Liste, yeni_liste),flatten(Ls, NewLs),append(yeni_liste,NewLs , flat_list).
