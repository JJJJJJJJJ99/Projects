%{ 
Author:Jingjing Wang
Date: Feb 2
Describtion: Function ispythag will receive three positive integers (a, b, c in that order) and will
return logical 1 for true if they form a Pythagorean triple, or 0 for false if not.
%}


function y = ispythag(a, b, c)
if  rem(a,1) ~= 0 || a <= 0
    error('Input must be a positive integer')
elseif rem(b,1) ~= 0 || b <= 0
    error('Input must be a positive integer')
elseif rem(c,1) ~= 0 || c <= 0
    error('Input must be a positive integer')
elseif a^2 + b^2 == c^2
    y = 1;
else
    y = 0;
end
end