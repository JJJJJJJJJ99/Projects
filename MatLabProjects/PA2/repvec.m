%{
Author:Jingjing Wang
Date: Feb 11
This script  receives a vector and the number of times each
element is to be duplicated.
%}
function y = repvec(v, n)
Vtrans = v';
rows = size(Vtrans,2);
M = repmat(Vtrans, rows, n);
y = reshape(M', 1, []);
end