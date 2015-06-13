main begin
  int l;
  int r;
  int i;
  int j;
  int k;
  int[] array;
  boolean changed;
  boolean flag;
  array = new int [10];
  array[0] = 123;
  array[1] = 12;
  array[2] = 13;
  array[3] = 23;
  array[4] = 3;
  array[5] =  - 3;
  array[6] = 412;
  array[7] = 12345;
  array[8] = 2;
  array[9] = 0;
  do
  begin
    changed = false;
    i =  - 1;
    j = 0;
    do
    begin
      i = j;
      j = j + 1;
      flag = j < array.length;
      if (flag) then
      begin
        flag = array[i] < array[j];
      end
      if (!(flag)) then
      begin
      end
    end
    while(flag);

    if (j < array.length) then
    begin
      if (array[i] < array[j]) then
      begin
      end
      if (!(array[i] < array[j])) then
      begin
        k = array[i];
        array[i] = array[j];
        array[j] = k;
        changed = true;
      end
    end
    if (!(j < array.length)) then
    begin
    end
  end
  while(changed);

  j = 0;
  do
  begin
    print(array[j]);
    j = j + 1;
  end
  while(j < array.length);

  return 0;
end

