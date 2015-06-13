main begin
  int a;
  a = 2;
  if (5 < a) then
  begin
    a = 1;
  end
  if (!(5 < a)) then
  begin
    a = 0;
  end
  print(a);
  return a;
end

