function f = histequalization(I, m)
    [c, r] = size(I);
    [x, y] = size(I);
    X = zeros(1,256); 
    temp = 0;
    for i = 1:256
           %Create a matrix of probabilities
           X(1, i) = m(1, i)/(x*y);
    end

    % Sum of previous probabilities in a matrix
    for i = 1:256
        temp = temp + X(1, i);
        X(1, i) = temp;
    end


    for i = 1:c
        for j = 1:r
            I(i, j) = floor((256-1)* X(1, I(i,j)))-1;
        end
    end
    
    f = I;
end
