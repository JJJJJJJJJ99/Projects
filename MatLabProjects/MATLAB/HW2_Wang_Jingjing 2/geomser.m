%{
Author:Jingjing Wang
Date: Feb 11
This script will receive values of r and n, and will
calculate and return the sum of the geometric series
%}
function y = geomser(r, n)
m = (0:n);
y = sum (r .^ m);
end 
