%{
Author:Jingjing Wang
Date: Feb 11
This script will receive a matrix argument, and will return a
vector consisting of the main diagonal. 
%}
function y = mymatdiag(M)
    if size(M,1) ~= size(M,2)
        msg = 'Error: your input matrix is not a square matrix.';
        disp(msg);
        y = []; % Return an empty vector
    else
        A = zeros(1, size(M,1));
        for i = 1 : size(M,1)
            A(1, i) = M(i,i);
        end
        y = A;
    end
end