main begin
  int a;
  int b;
  int[] array1;
  int[] array2;
  a = 0;
  b = 0;
  array1 = new int [3];
  array2 = new int [3];
  array1[0] = 0;
  array1[1] = 10;
  array1[2] = 100;
  array2[0] = 1;
  array2[1] = 11;
  array2[2] = 101;
  do
  begin
    do
    begin
      if (b < 1) then
      begin
        print(array1[a]);
      end
      if (!(b < 1)) then
      begin
        print(array2[a]);
      end
      b = b + 1;
    end
    while(b < 2);

    b = 0;
    a = a + 1;
  end
  while(a < 3);

  return 0;
end

