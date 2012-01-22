package fmii.control;

import java.util.List;

public class SimulationControl extends Control
{
    
    public SimulationControl()
    {
        mass = 9;
        force = 4;
    }

    public void set_mass(int x)
    {
        mass = x;
        setup_simulation();
    }

    public void set_force(int x)
    {
        force = x;
        setup_simulation();
    }
    
    public void pass_parameters()
    {
        if (!(mass > 0))
        {
            mass = 1;
        }
        command("set mass " + mass);
        command("set force " + force);
    }
    
    public void setup_simulation()
    {
        pass_parameters();
        command("setup");// put it back on the left
    }    
    public void run_simulation()
    {
        setup_simulation();
        command("repeat 570 [go]"); // move it to the right!
    }

    int mass;
    int force;
}
