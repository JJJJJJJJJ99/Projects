im=imread('coca6.jpg');
im1=rgb2gray(im);
im1=medfilt2(im1,[3 3]); %Remove noise%
BW = edge(im1,'sobel'); %finding edges 
[imx,imy]=size(BW);
mask = createLogoMask(im1);

msk=mask(1);

imshow(mask(2));