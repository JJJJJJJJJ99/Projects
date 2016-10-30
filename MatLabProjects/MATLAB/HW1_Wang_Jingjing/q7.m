%{ 
Author:Jingjing Wang
Date: Feb 2
Describtion: This is a function that will receive a matrix as an input argument, and will calculate and
return the overall average of all numbers in the matrix.
%}

function y = q7(M)
sum = 0;
    for i = 1 : size(M, 1)
        for j = 1 : size(M, 2)
            sum = sum + M(i,j);
        end 
    end
 y = sum / (size(M,1)*size(M,2));
end