function f = histogramPlot(I)
    %Create a scale to store each gray level
    m = zeros(1, 256);
    [x, y] = size(I);
    for i = 1:x
        for j = 1:y
            m(1, I(i,j)+1) = m(1, I(i,j)+1) + 1;
        end
    end
    % Plot histogram
    f = m;
end