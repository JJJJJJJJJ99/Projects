X = imread('coca6.jpg');
%Y = rgb2hsv(X);
imshow(X);

Image_r = X(:,:,1);
Image_b = X(:,:,2);
Image_g = X(:,:,3);

ind_plain = Image_r > 240 & Image_b > 240 & Image_g > 240;
imshow(ind_plain * X);


%pixel_values6 = impixel;
