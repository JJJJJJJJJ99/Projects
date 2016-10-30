clear;

in = 'lena.pgm';
I = imread(in);
in2 = 'portofino.pgm';
I2 = imread(in2);

isp = imnoise(I, 'salt & pepper', 0.3);
figure, imshow(isp);
title('salt&pepper lena');

%Apply median filtering
ispfilted =  medianfilt(isp, [7 7]);
figure, imshow(ispfilted);
title('median filted lena');
%Apply averaging
ispavrgfilted =  avrgfilt(isp, [7 7]);
figure, imshow(ispavrgfilted);
title('average filted lena');


isp2 = imnoise(I2, 'salt & pepper', 0.5);
figure, imshow(isp2);
title('salt&pepper protofino');

%Apply median filtering
ispfilted2 = medianfilt(isp2, [15 15]);
figure, imshow(ispfilted2);
title('median filted protofino');
%Apply averaging
ispavrgfilted2 = avrgfilt(isp2, [15 15]);
figure, imshow(ispavrgfilted2);
title('average filted protofino');

% Discuss: From all the figures I got, I found that median filtering have
% better results than average filtering. Also the more salt and pepper
% degree, the more blur the result is. The more mask size is, harder the
% image is going to restore, however, to some little sizes of mask, it is
% hard to even the image either. 

  