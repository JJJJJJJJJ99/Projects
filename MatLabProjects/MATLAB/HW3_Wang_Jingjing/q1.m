close all;
clear();

% Compare with all the images shown, the effect of gray level 2 is the most
% obvious one.

in = 'lena_256.pgm';
I = imread(in);
subplot(2,5,1);
imshow(I);

I2 =(I-1)/2;
subplot(2,5,2);
imshow(I2,[0,127]);

I3 = (I-1)/8;
subplot(2,5,3);
imshow(I3,[0, 31]);

I4 = (I-1)/32;
subplot(2,5,4);
imshow(I4,[0,7]);

I5 = (I-1)/128;
subplot(2,5,5);
imshow(I5, [0,1]);

in2 = 'peppers_256.pgm';
Im = imread(in2);
subplot(2,5,6);
imshow(Im);

Im2 = Im/2;
subplot(2,5,7);
imshow(Im2, [0,127]);

Im3 =  (Im-1)/8;
subplot(2,5,8);
imshow(Im3, [0,31]);

Im4 = (Im-1)/32;
subplot(2,5,9);
imshow(Im4,[0,7]);

Im5 = (Im-1)/128;
subplot(2,5,10);
imshow(Im5, [0,1]);


