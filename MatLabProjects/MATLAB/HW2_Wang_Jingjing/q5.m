%{
Author:Jingjing Wang
Date: Feb 11
This script determins Which method is faster using false or using logical(0) 
to preallocate a matrix to all logical zeros.
%}

tic 
m = zeros(100,100000);
x = logical(m);
timeLogical = toc;

tic
y = false(100,100000);
timeFalse = toc;

won = min(timeLogical, timeFalse);

false = sprintf('Time for using false is %f', timeFalse);
logic = sprintf('Time for using logical(0) is %f', timeLogical);
X = sprintf('The method with %f time elspsed is faster.', won);

disp(false);
disp(logic);
disp(X);