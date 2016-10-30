%{
Author:Jingjing Wang
Date: Feb 11
This script will prompt the user for a quiz and error-check until the user enters
a valid quiz grade.
%}

M = 0: 0.5 : 10;
disp(M);
a = input('Enter a quiz grade: ');
disp(a);
check = M == a;
while any(check) == 0
    disp('It is not a valid quiz grade.');
    a = input('Enter a quiz grade: ');
    disp(a);
    check = M == a;
end