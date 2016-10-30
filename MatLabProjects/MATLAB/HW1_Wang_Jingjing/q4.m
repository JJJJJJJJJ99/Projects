%{ 
Author:Jingjing Wang
Date: Feb 2
Describtion: A script that will read from a file oldfile.dat into a matrix. It will create a
square matrix by deleting rows or columns as necessary, and then write this new square
matrix to a new file called squarefile.dat.
********************
File oldfile.dat is created this script.
********************
%}

% Sample file, you can comment it out for testing
file = fopen('oldfile.dat','w');
M = [1 2 3 4 5; 
    2 3 4 5 6;
    3 4 5 6 7];

for i=1:size(M, 1)
    fprintf(file, ' %d', M(i,:));
    fprintf(file, '\n');
end
% Sample file, you can comment it out for testing

load oldfile.dat;
rows = size(oldfile(:,1), 1);
columns = size(oldfile(1,:), 2);
squareL = min(rows,columns);
new = oldfile (1:squareL,1:squareL);


newfile = fopen('squarefile.dat','w');

for i=1:size(new, 1)
    fprintf(newfile,' %f', new(i,:));
    fprintf(newfile, '\n');
end
