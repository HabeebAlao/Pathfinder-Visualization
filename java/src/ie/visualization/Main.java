/*
Program Description: Path finder visualization

Development Environment: This program was developed in Visual Studio Code.

Author: Habeeb Alao

Created: 11/06/2022    Modified: 19/06/2022
*/

package ie.visualization;

public class Main
{
    public void PathVisualization()
    {
        String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Grid());
    }
  
    

    public static void main(String[] args)
    {
        Main main = new Main();
        main.PathVisualization();
    }
} 