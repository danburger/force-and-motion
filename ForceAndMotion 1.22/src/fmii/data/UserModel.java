package fmii.data;

public class UserModel extends Model{
    
    public UserModel(int a, int b, int c)
    {
        super(a,b,c);
    }
    
    // modifiers:
    public void setMassEffect(int x){massEffect = x;}
    public void setForceEffect(int x){forceEffect = x;}
    public void setFrictionEffect(int x){frictionEffect = x;}
}
