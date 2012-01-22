package fmii.data;

public abstract class Model {
    
    // data members:
    // int values:
    //  -1  Decreasing
    //  0   Constant
    //  1   Increasing
    
    public Model(int a, int b, int c)
    {
        massEffect = a;
        forceEffect = b;
        frictionEffect = c;
    }
    
    protected int massEffect, forceEffect, frictionEffect;
    
    public int getMassEffect(){return massEffect;}
    public int getForceEffect(){return forceEffect;}
    public int getFrictionEffect(){return frictionEffect;}
}
