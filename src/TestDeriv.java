public class TestDeriv
{
    public static void main(String args[])
    {
  Expression zero = new ConstantExpression(0.0);
  Expression one = new ConstantExpression(1.0);
	Expression x = new VarExpression();

  Expression xPlusZero = new SumExpression(x, zero);
  Expression zeroPlusX = new SumExpression(zero, x);
  Expression xMinusZero = new SubtractionExpression(x, zero);
  Expression zeroTimesX = new ProductExpression(zero, x);
  Expression xTimesZero = new ProductExpression(x, zero);
  Expression oneTimesX = new ProductExpression(one, x);
  Expression xTimesOne = new ProductExpression(x, one);
  Expression xPowerOne = new PowerExpression(x, one);
  Expression onePowerX = new PowerExpression(one, x);

        Expression s1 = xPlusZero.simplify();
        Expression s2 = zeroPlusX.simplify();
        Expression s3 = xMinusZero.simplify();
        Expression s4 = zeroTimesX.simplify();
        Expression s5 = xTimesZero.simplify();
        Expression s6 = oneTimesX.simplify();
        Expression s7 = xTimesOne.simplify();
        Expression s8 = xPowerOne.simplify();
        Expression s9 = onePowerX.simplify();

  Expression a = new ConstantExpression(4.0);
  Expression s = new SumExpression(x, x);


  Expression l = new PowerExpression(x, a);
  Expression t = new SumExpression(x, a);

  Expression z = new SubtractionExpression(x, zero);
  Expression z2 = new SubtractionExpression(z, z);
  Expression z3 = new SubtractionExpression(l, z2);
  Expression z4 = new SubtractionExpression(z3, s);
  Expression b = new PowerExpression(z, s);
  Expression b2 = new PowerExpression(x, s);
  Expression b3 = new PowerExpression(b, s);
  Expression b4 = new PowerExpression(x, one);
  Expression b5 = new PowerExpression(zero, l);
  Expression p = new ProductExpression(x, s);
  Expression p2 = new ProductExpression(t, z);
  Expression p3 = new ProductExpression(z, p);
  Expression p4 = new ProductExpression(a, l);

        Expression d1 = s.deriv();
        Expression d2 = t.deriv();
        Expression d3 = z.deriv();
        Expression d4 = p.deriv();
        Expression d5 = l.deriv();
        Expression d6 = z2.deriv();
        Expression d7 = z3.deriv();
        Expression d8 = z4.deriv();
        Expression d9 = b.deriv();
        Expression d10 = b2.deriv();
        Expression d11 = b3.deriv();
        Expression d12 = b4.deriv();
        Expression d13 = b5.deriv();
        Expression d14 = p2.deriv();
        Expression d15 = p3.deriv();
        Expression d16 = p4.deriv();


  // ------------------------------------------------------
  // Simplify Tests
  System.out.println("simplify(" + xPlusZero + ") = " + s1);
  System.out.println("simplify(" + zeroPlusX + ") = " + s2);
  System.out.println("simplify(" + xMinusZero + ") = " + s3);
  System.out.println("simplify(" + zeroTimesX + ") = " + s4);
  System.out.println("simplify(" + xTimesZero + ") = " + s5);
  System.out.println("simplify(" + oneTimesX + ") = " + s6);
  System.out.println("simplify(" + xTimesOne + ") = " + s7);
  System.out.println("simplify(" + xPowerOne + ") = " + s8);
  System.out.println("simplify(" + onePowerX + ") = " + s9);
  // ------------------------------------------------------

  System.out.println();

  // ------------------------------------------------------
  // Deriv Tests
	System.out.println("deriv(" + s + ") = " + d1);
  System.out.println("deriv(" + t + ") = " + d2);
  System.out.println("deriv(" + z + ") = " + d3);
  System.out.println("deriv(" + p + ") = " + d4);
  System.out.println("deriv(" + l + ") = " + d5);
  System.out.println("deriv(" + z2 + ") = " + d6);
  System.out.println("deriv(" + z3 + ") = " + d7);
  System.out.println("deriv(" + z4 + ") = " + d8);
  System.out.println("deriv(" + b + ") = " + d9);
  System.out.println("deriv(" + b2 + ") = " + d10);
  System.out.println("deriv(" + b3 + ") = " + d11);
  System.out.println("deriv(" + b4 + ") = " + d12);
  System.out.println("deriv(" + b5 + ") = " + d13);
  System.out.println("deriv(" + p2 + ") = " + d14);
  System.out.println("deriv(" + p3 + ") = " + d15);
  System.out.println("deriv(" + p4 + ") = " + d16);
  // ------------------------------------------------------
    }
}
