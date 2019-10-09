import java.io.*;
import java.util.*;

public class Perceptron
{
	public static double Weight_Vec[] = new double[4];
	public static int C;
	
	
	public static void DisplayList(ArrayList<ArrayList<Double>> vectors)
	{
		System.out.println("\n\n------------------------------------\nThe Input Vectors Are : ");
		for (int i=0;i<vectors.size();i++)
		{
            for (int j=0;j<vectors.get(i).size();j++)
            { 
                System.out.print(vectors.get(i).get(j)+" "); 
            }
            System.out.println();
        } 
	}
	
	
	
	
	
	public static void Perceptron_Learning(ArrayList<ArrayList<Double>> vectors, double Weight_Vec[], int outputs[])
	{
		double X[] = new double[4];
		
		//Calculate the net
		for (int i=0;i<vectors.size();i++)
		{
			double net = 0.0;
			int O = 0;
			
			System.out.print("\n\n----------STAGE:"+(i+1)+"----------\n\n");
            for (int j=0;j<vectors.get(i).size();j++)
            {
                net+=vectors.get(i).get(j)*Weight_Vec[j];
                X[j] = vectors.get(i).get(j);
            }
            System.out.print("\n> NET : "+net);
    		
    		//Calculate the Signum Function
    		O = Signum(net);
    		System.out.print("\n> O : "+O);
    		
    		if(O==outputs[i])
    		{
    			System.out.print("\n> The O and D have matched...");
    			System.out.print("\n> No Need to Update the Weight Vector...");
    			System.out.print("\n> Weight Vector is : ");
    			disp_Weight_Vector(Weight_Vec);
    		}
    		else
    		{
    			System.out.print("\n> The O and D have not matched...");
    			System.out.print("\n> Update the Weight Vector...");
    			Update_Weight_Vector(Weight_Vec, outputs[i], O, X);
    			System.out.print("\n> Weight Vector is : ");
    			disp_Weight_Vector(Weight_Vec);
    		}
    		System.out.println("\n-----------------------------------------");
        }
	}
	
	
	
	
	public static int Signum(double net)
	{
		int flag = 0;
		
		if(net>0)
		{
			flag = 1;
		}
		else
		{
			flag = -1;
		}
		
		return flag;
	}
	
	
	
	
	public static void Update_Weight_Vector(double Weight_Vector[], int Dk, int Ok, double X[])
	{
		double new_Weight_Vector[] = new double[4];
		double r = Dk - Ok;
		
		for(int i=0;i<4;i++)
		{
			new_Weight_Vector[i] = Weight_Vector[i] + C*r*X[i];
		}
		
		for(int i=0;i<4;i++)
		{
			Weight_Vector[i] = new_Weight_Vector[i];
		}
	}
	
	
	
	public static void disp_Weight_Vector(double Weight_Vector[])
	{
		for(int i=0;i<4;i++)
		{
			System.out.print(Weight_Vector[i]+", ");
		}
	}
	
	
	
	
	public static void main(String args[])throws IOException
	{
		Scanner sc = new Scanner(System.in);
		int num = 0;
		double var = 0.0;
		
		System.out.println("--------------------Perceptron-Learning--------------------\n\n");
		System.out.println("\n##Enter the Weight Vector : \n");
		for(int i=0;i<4;i++)
		{
			System.out.print("W["+(i+1)+"] : ");
			Weight_Vec[i] = sc.nextDouble();
		}
		
		System.out.println("\n> Entered Weight Vector : ");
		for(int i=0;i<4;i++)
		{
			System.out.print(Weight_Vec[i]+",");
		}
		System.out.print("\n\n");
		
		System.out.print("\n> Enter the Number of Inputs Vectors : ");
		num = sc.nextInt();
		
		ArrayList<ArrayList<Double>> Vectors = new ArrayList<ArrayList<Double>>(num);
		
		for(int i=0;i<num;i++)
		{
			System.out.println("\n----->Input Vector "+(i+1)+"<-----");
			ArrayList<Double> Input_Vector = new ArrayList<Double>(4);
			for(int j=0;j<4;j++)
			{
				System.out.print("-> Vec["+(j+1)+"] : ");
				var = sc.nextDouble();
				Input_Vector.add(var);
			}
			Vectors.add(Input_Vector);
		}
		DisplayList(Vectors);
		
		int Outputs[] = new int[num];
		System.out.print("\n> Enter the Desired Outputs : \n");
		for(int i=0;i<num;i++)
		{
			System.out.print(">");
			Outputs[i] = sc.nextInt();
		}
		
		System.out.print("\n> Enter the Value of C : ");
		C = sc.nextInt();
		
		
		System.out.println("\n##PERCEPTRON LEARNING....");
		Perceptron_Learning(Vectors, Weight_Vec, Outputs);
	}
}
