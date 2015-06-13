main begin
  boolean a;
  boolean b;
  a = true;
  b = false;
  if (a && b) then
  begin
    print(1);
  end
  if (!(a && b)) then
  begin
    print(0);
  end
  return a;
end

