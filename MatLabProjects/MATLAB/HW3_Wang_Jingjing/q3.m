in = 'portofino.pgm';
I = imread(in);
figure, imshow(I);
title('orginal');
%Create a scale to store each gray level
m = histogramPlot(I);
% Plot histogram
figure, bar(m);
title('original histogram');

I = histequalization(I, m);
figure, imshow(I);
title('after');

n = histogramPlot(I);
figure, bar(n);
title('new histogram');

%Discuss: Original image has low contrast of its gray level. Image's
%contrast enhanced after applying histogram equalization. Bars on original
%histogram are condense while bars on new histogram are sparse.