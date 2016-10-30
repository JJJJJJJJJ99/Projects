%{
Author:Jingjing Wang
Date: Feb 11
This script will read this file into a matrix, create a new matrix that
stores the years correctly as 19xx, and then write this to a new file called
y2ktemp.dat. 
%}

% Sample file, you can comment it out for testing
M = [89 42 49 55 72 63 68 77 82 76 67;
    90 45 50 56 59 62 68 75 77 75 66;
    91 44 43 60 60 60 65 69 74 70 70];
save ('hightemp.dat', 'M','-ascii');
% Sample file, you can comment it out for testing

load hightemp.dat;

M(:,1) = M(:,1) + 1900;

save('y2ktemp.dat','M','-ascii');