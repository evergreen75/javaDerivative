// Author: Joshua Bowen
// Date:   11/18/2017
// Class:  CSCI 3675


import java.io.*;

//==========================================================
//                     Class Expression
//==========================================================


abstract class Expression
{
  //-----------------------------------------------------
  // simplify() returns a possibly simplified version of
  // this expression.
  //-----------------------------------------------------

  public abstract Expression simplify();

  //-----------------------------------------------------
  // toString() returns a string that described this
  // expression.  It can be used for printing this
  // expression.
  //-----------------------------------------------------

  public abstract String toString();

  //-----------------------------------------------------
  // rawderiv() returns the derivative of this expression,
  // without simplification.
  //-----------------------------------------------------

  public abstract Expression rawderiv();

  //-----------------------------------------------------
  // deriv() returns the derivative of this expression,
  // with simplification.
  //-----------------------------------------------------

  public Expression deriv()
  {
      return rawderiv().simplify();
  }
}

//==========================================================
//                     Class ConstantExpression
//==========================================================
// A constant expression is the expression equivalent of a
// real number.
//==========================================================

class ConstantExpression extends Expression
{
  private double value;

  public ConstantExpression(double v)
  {
    value = v;
  }

  public Expression rawderiv()
  {
    return new ConstantExpression(0.0);
  }

  public Expression simplify()
  {
    return this;
  }

  public String toString()
  {
    return "" + value;
  }

  public double getvalue()
  {
    return value;
  }
}

//==========================================================
//                     Class VarExpression
//==========================================================
// A VarExpression is the independent variable x.
//==========================================================

class VarExpression extends Expression
{
  public VarExpression()
  {
  }

  public Expression rawderiv()
  {
    return new ConstantExpression(1.0);
  }

  public Expression simplify()
  {
    return this;
  }

  public String toString()
  {
    return "x";
  }

}

//==========================================================
//                    Class SumExpression
//==========================================================
// A SumExpression is an expression that is the sum of two
// other expressions.
//==========================================================

class SumExpression extends Expression
{
  private Expression addend1, addend2;

  public SumExpression(Expression e1, Expression e2)
  {
    addend1 = e1;
    addend2 = e2;
  }

  public Expression rawderiv()
  {
    return new SumExpression(addend1.rawderiv(), addend2.rawderiv());
  }

  public Expression simplify()
  {
    return simplifySum(addend1.simplify(), addend2.simplify());
  }

  public String toString()
  {
    return "(" + addend1.toString() + "+" + addend2.toString() + ")";
  }

  private static Expression simplifySum(Expression e1, Expression e2)
  {
    // Simplifications
    // -------------------
    // x + 0 = 0
    // 0 + x = x
    // constant arithmetic
    // -------------------

    if(e1 instanceof ConstantExpression) {
      ConstantExpression ce = (ConstantExpression) e1;

      // 0 + x = x

      if(ce.getvalue() == 0.0) {
        return e2;
      }

      // Constant arithmetic

     if(e2 instanceof ConstantExpression) {
      ConstantExpression ce2 = (ConstantExpression) e2;
      return new ConstantExpression(ce.getvalue() + ce2.getvalue());
      }
    }

    // x + 0 = x

    if(e2 instanceof ConstantExpression) {
      ConstantExpression ce = (ConstantExpression) e2;
      if(ce.getvalue() == 0.0) {
          return e1;
      }
    }

    // Default: do not simplify.

    return new SumExpression(e1, e2);

  }

}

//==========================================================
//                    Class SubtractionExpression
//==========================================================
// A SubtractionExpression is an expression that is equal to
// an expression minus another expression
//==========================================================

class SubtractionExpression extends Expression
{
  private Expression expr1, expr2;

  public SubtractionExpression(Expression e1, Expression e2)
  {
    expr1 = e1;
    expr2 = e2;
  }

  public Expression simplify()
  {
    return simplifySubtraction(expr1.simplify(), expr2.simplify());
  }

  public Expression rawderiv()
  {
    return new SubtractionExpression(expr1.rawderiv(), expr2.rawderiv());
  }

  public String toString()
  {
    return "(" + expr1.toString() + "-" + expr2.toString() + ")";
  }

  private static Expression simplifySubtraction(Expression e1, Expression e2)
  {
    // Simplifications
    // -------------------
    // x - 0 = x
    // constant arithmetic
    // -------------------

    if (e2 instanceof ConstantExpression)
    {
      ConstantExpression ce2 = (ConstantExpression) e2;

      // x - 0 = x

      if (ce2.getvalue() == 0.0)
      {
        return e1;
      }

      // constant arithmetic

      if (e1 instanceof ConstantExpression)
      {
        ConstantExpression ce1 = (ConstantExpression) e1;
        return new ConstantExpression(ce1.getvalue() - ce2.getvalue());
      }

    }
      // Default: do not simplify.

      return new SubtractionExpression(e1, e2);
  }
}

//==========================================================
//                    Class ProductExpression
//==========================================================
// A ProductExpression is an expression that is the product of
// two other expressions.
//==========================================================

class ProductExpression extends Expression
{
  Expression expr1, expr2;

  public ProductExpression(Expression e1, Expression e2)
  {
    expr1 = e1;
    expr2 = e2;
  }

  public Expression rawderiv()
  {
    Expression a = new ProductExpression(expr1, expr2.rawderiv());
    Expression b = new ProductExpression(expr1.rawderiv(), expr2);
    return new SumExpression(a, b);
  }

  public Expression simplify()
  {
    return simplifyProduct(expr1.simplify(), expr2.simplify());
  }

  public String toString()
  {
    return "(" + expr1.toString() + "*" + expr2.toString() + ")";
  }

  private static Expression simplifyProduct(Expression e1, Expression e2)
  {
    // Simplifications
    // --------------------
    // x * 0 = 0
    // 0 * x = 0
    // x * 1 = x
    // 1 * x = x
    // constant arithmetic
    // --------------------

    if (e1 instanceof ConstantExpression)
    {
      ConstantExpression ce1 = (ConstantExpression) e1;

      // 0 * x = 0

      if (ce1.getvalue() == 0.0)
      {
        return new ConstantExpression(0.0);
      }

      // 1 * x = x

      else if (ce1.getvalue() == 1.0)
      {
        return e2;
      }

    }
    if (e2 instanceof ConstantExpression)
    {
      ConstantExpression ce2 = (ConstantExpression) e2;

      // x * 0 = 0

      if (ce2.getvalue() == 0.0)
      {
        return new ConstantExpression(0.0);
      }

      // x * 1 = x

      else if (ce2.getvalue() == 1.0)
      {
        return e1;
      }

      // constant arithmetic

      else if (e1 instanceof ConstantExpression)
      {
        ConstantExpression ce1 = (ConstantExpression) e1;
        return new ConstantExpression(ce1.getvalue() * ce2.getvalue());
      }
    }

    // Default: do not simplify.

    return new ProductExpression(e1, e2);

  }
}

//==========================================================
//                    Class PowerExpression
//==========================================================
// A PowerExpression is an expression that is equal to
// another expression to the power of yet another expression.
//==========================================================

class PowerExpression extends Expression
{
  Expression expr1, expr2;
  public PowerExpression(Expression e1, Expression e2)
  {
    expr1 = e1;
    expr2 = e2;
  }
  public Expression simplify()
  {
    return simplifyPower(expr1.simplify(), expr2.simplify());
  }

  public Expression rawderiv()
  {
    // Second expression must be a constant.

    if (!(expr2 instanceof ConstantExpression))
    {
      System.out.println("ERROR. Cannot take the derivative of a non constant power expression");
      return new PowerExpression(expr1, expr2);
    }

    // Second expression is a constant so we continue on.

    else
    {
      ConstantExpression ce2 = (ConstantExpression) expr2;
      Expression constMinusOne = new ConstantExpression(ce2.getvalue() - 1);
      Expression constant = ce2;
      Expression firstPower = new PowerExpression(expr1, constMinusOne);
      Expression firstProduct = new ProductExpression(constant, firstPower);
      Expression secondProduct = new ProductExpression(firstProduct, expr1.rawderiv());
      return secondProduct;
    }
  }

  public String toString()
  {
    return "(" + expr1.toString() + "^" + expr2.toString() + ")";
  }

  private static Expression simplifyPower(Expression e1, Expression e2)
  {
    // Simplifications
    // ---------------
    // x ^ 1 = x
    // 1 ^ x = 1
    // constant arithmetic
    // ---------------

    if (e1 instanceof ConstantExpression)
    {
      ConstantExpression ce1 = (ConstantExpression) e1;

      // 1 ^ x = 1

      if (ce1.getvalue() == 1)
      {
        return new ConstantExpression(1.0);
      }
    }
    if (e2 instanceof ConstantExpression)
    {
      ConstantExpression ce2 = (ConstantExpression) e2;

      // x ^ 1 = x

      if (ce2.getvalue() == 1)
      {
        return e1;
      }

      // constant arithmetic

      if (e1 instanceof ConstantExpression)
      {
        ConstantExpression ce1 = (ConstantExpression) e1;
        return new ConstantExpression(Math.pow(ce1.getvalue(), ce2.getvalue()));
      }
    }
    // Default: do not simplify.

    return new PowerExpression(e1, e2);
  }
}
