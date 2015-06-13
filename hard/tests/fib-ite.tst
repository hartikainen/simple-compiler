int fib(int n) begin
  int i1;
  int i2;
  int k;
  int ret;
  if (2 < n) then
  begin
    i1 = 1;
    i2 = 1;
    k = 2;
    do
    begin
      ret = i1 + i2;
      i1 = i2;
      i2 = ret;
      k = k + 1;
    end
    while(k < n);

  end
  if (!(2 < n)) then
  begin
    ret = 1;
  end
  return ret;
end

main begin
  int result;
  int n;
  int count;
  count = 0;
  n = 15;
  do
  begin
    result = fib(count + 1);
    print(result);
    count = count + 1;
  end
  while(count < n);

  return result;
end

