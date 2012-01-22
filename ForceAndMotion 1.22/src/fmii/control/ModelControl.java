package fmii.control;

import fmii.data.*;

public class ModelControl extends Control
{

    private int hint = -1;
    
    public ModelControl()
    {
        exp = new ExpertModel(-1, 1, -1);
        usr = new UserModel(0, 0, 0);
    }

    public void edit_massEffect(int x)
    {
        if (x == 1)
        {
            usr.setMassEffect(-1);
        }
        else if (x == 2)
        {
            usr.setMassEffect(1);
        }
        else
        {
            usr.setMassEffect(x);
        }
        
        command("set mass-effect " + usr.getMassEffect());
    }

    public void edit_forceEffect(int x)
    {
       if (x == 1)
        {
            usr.setForceEffect(-1);
        }
        else if (x == 2)
        {
            usr.setForceEffect(1);
        }
        else
        {
            usr.setForceEffect(x);
        }
       
        command("set force-effect " + usr.getForceEffect());
    }

    public void edit_frictionEffect(int x)
    {
       if (x == 1)
        {
            usr.setFrictionEffect(-1);
        }
        else if (x == 2)
        {
            usr.setFrictionEffect(1);
        }
        else
        {
            usr.setFrictionEffect(x);
        }
       
        command("set friction-effect " + usr.getFrictionEffect());
    }

    public void new_model()
    {
        usr = new UserModel(0, 0, 0);
    }

    public String give_help()
    {
        return gen_diff();
    }

    public String gen_diff()
    {
        if (exp.getMassEffect() != usr.getMassEffect())
        {
            hint = (hint+1) % 3;
            if(hint == 0)
            {
                return "Is it easier to move a box filled with rocks than an empty box?";
            }
            else if(hint == 1)
            {
                return "Think about what happens when you push a brick instead of a pencil.";
            }
            else
            {
                return "Have you ever tried to push an elephant out of your way? It's hard.";
            }
        }
        else if (exp.getForceEffect() != usr.getForceEffect())
        {
            hint = (hint+1) % 3;
            if(hint == 0)
            {
                return "What happens if you and a friend push a heavy box? Together the two of you apply more force; does the box accelerate more?";
            }
            else if(hint == 1)
            {
                return "Think about baseball: if a player hits a home run, she imparts more force to the ball than if she bunts.";
            }
            else
            {
                return "What's the difference when you pull a wagon with your pinky instead of your entire hand?";
            }
        }
        else if (exp.getFrictionEffect() != usr.getFrictionEffect())
        {
            hint = (hint+1) % 3;
            if(hint == 0)
            {
                return "An air hockey table is (virtually) frictionless. How does the lack of friction affect the movement of pucks?";
            }
            else if(hint == 1)
            {
                return "Do you accelerate faster when you're sliding on a carpeted floor or a hardwood floor?";
            }
            else
            {
                return "Hint: a frictional force acts in the opposite direction of the applied force.";
            }
        }
        else
        {
            return "Looks good to me!";
        }
    }

    ExpertModel exp;
    UserModel usr;
}