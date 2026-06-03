public class usingRoots
{
  public static void main(String[] args)
  {
     double a=2, b=-10, c=2;

     Roots.calculate_roots(a,b,c);
     System.out.println(Roots.num_roots());
	 System.out.println(Roots.first_root());
	 System.out.println(Roots.second_root());


 }
}