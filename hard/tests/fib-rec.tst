int fib(int n) begin
  int ret;
  if (2 < n) then
  begin
    ret = fib(n - 1) + fib(n - 2);
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

