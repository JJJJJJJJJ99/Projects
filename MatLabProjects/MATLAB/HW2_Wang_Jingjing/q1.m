%{
Author:Jingjing Wang
Date: Feb 11
This script  will read from a file oldfile.dat into a matrix. It will create a square
matrix (same number of rows and columns) by deleting rows or columns as
necessary, and then write this new square matrix to a new file called squarefile.dat. 
%}

% Sample file, you can comment it out for testing
M = [1 2 3 4 5; 
    2 3 4 5 6;
    3 4 5 6 7];
save ('oldfile.dat', 'M','-ascii');
% Sample file, you can comment it out for testing

load oldfile.dat;
rows = size(oldfile(:,1), 1);
columns = size(oldfile(1,:), 2);
squareL = min(rows,columns);
new = oldfile (1:squareL,1:squareL);

save('squarefile.dat', 'new', '-ascii');