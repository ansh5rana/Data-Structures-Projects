package tides;

import java.lang.reflect.Array;
import java.util.*;


public class RisingTides {

    private double[][] terrain;     
    private GridLocation[] sources;

    public RisingTides(Terrain terrain) {
        this.terrain = terrain.heights;
        this.sources = terrain.sources;
    }

    public double[] elevationExtrema() {
        double max = Integer.MIN_VALUE;
        double[] extremes = new double[2];
        double min = Integer.MAX_VALUE;
        for(int i=0; i<terrain.length; i++)
        {
            for(int j=0; j<terrain[i].length; j++)
            {
                if(terrain[i][j] > max)
                {
                    max = terrain[i][j];
                    extremes[1] = max;
                }
                if(terrain[i][j] < min)
                {
                    min = terrain[i][j];
                    extremes[0] = min;
                }
            }
        }
        return extremes; 
    }

    
    public boolean[][] floodedRegionsIn(double height) {
        
        boolean[][] resultingArr = new boolean[terrain.length][terrain[0].length];
        for(int i=0; i<resultingArr.length; i++)
        {
            for(int j=0; j<resultingArr[i].length; j++)
            {
                resultingArr[i][j] = false;
            }
        }
        ArrayList<GridLocation> locations = new ArrayList<GridLocation>();
        for(int i=0; i<sources.length; i++)
        {
            locations.add(sources[i]);
            resultingArr[sources[i].row][sources[i].col] = true;
        }
        
        while(locations.size()>0)
        {
            int x = locations.get(0).row;
            int y = locations.get(0).col;
            locations.remove(0);
            
            if(x==0 && y==0)
            {
                if(height>=terrain[x+1][y] && resultingArr[x+1][y] == false)
                {
                    resultingArr[x+1][y] = true;
                    locations.add(new GridLocation(x+1, y));
                }
                if(height>=terrain[x][y+1] && resultingArr[x][y+1] == false)
                {
                    resultingArr[x][y+1] = true;
                    locations.add(new GridLocation(x, y+1));
                }
            }
            else if(x==terrain.length -1  && y==0)
            {
                if(height>=terrain[x-1][y] && resultingArr[x-1][y] == false)
                {
                    resultingArr[x-1][y] = true;
                    locations.add(new GridLocation(x-1, y));
                }
                if(height>=terrain[x][y+1] && resultingArr[x][y+1] == false)
                {
                    resultingArr[x][y+1] = true;
                    locations.add(new GridLocation(x, y+1));
                }
            }
            else if(x==0 && y==terrain[0].length - 1)
            {
                if(height>=terrain[x+1][y] && resultingArr[x+1][y] == false)
                {
                    resultingArr[x+1][y] = true;
                    locations.add(new GridLocation(x+1, y));
                }
                if(height>=terrain[x][y-1] && resultingArr[x][y-1] == false)
                {
                    resultingArr[x][y-1] = true;
                    locations.add(new GridLocation(x, y-1));
                }
            }
            else if(x==terrain.length -1 && y==terrain[0].length -1)
            {
                if(height>=terrain[x-1][y] && resultingArr[x-1][y] == false)
                {
                    resultingArr[x-1][y] = true;
                    locations.add(new GridLocation(x-1, y));
                }
                if(height>=terrain[x][y-1] && resultingArr[x][y-1] == false)
                {
                    resultingArr[x][y-1] = true;
                    locations.add(new GridLocation(x, y-1));
                }
            }
            else if(x==0 && (y!=0 || y!=terrain[0].length-1))
            {
                if(height>=terrain[x+1][y] && resultingArr[x+1][y] == false)
                {
                    resultingArr[x+1][y] = true;
                    locations.add(new GridLocation(x+1, y));
                }
                if(height>=terrain[x][y+1] && resultingArr[x][y+1] == false)
                {
                    resultingArr[x][y+1] = true;
                    locations.add(new GridLocation(x, y+1));
                }
                if(height>=terrain[x][y-1] && resultingArr[x][y-1] == false)
                {
                    resultingArr[x][y-1] = true;
                    locations.add(new GridLocation(x, y-1));
                }
            }
            else if(x==terrain.length-1 && (y!=0 || y!=terrain[0].length-1))
            {
                if(height>=terrain[x-1][y] && resultingArr[x-1][y] == false)
                {
                    resultingArr[x-1][y] = true;
                    locations.add(new GridLocation(x-1, y));
                }
                if(height>=terrain[x][y+1] && resultingArr[x][y+1] == false)
                {
                    resultingArr[x][y+1] = true;
                    locations.add(new GridLocation(x, y+1));
                }
                if(height>=terrain[x][y-1] && resultingArr[x][y-1] == false)
                {
                    resultingArr[x][y-1] = true;
                    locations.add(new GridLocation(x, y-1));
                }
            }
            else if(y==0 && (x!=0 || x!=terrain.length-1))
            {
                if(height>=terrain[x+1][y] && resultingArr[x+1][y] == false)
                {
                    resultingArr[x+1][y] = true;
                    locations.add(new GridLocation(x+1, y));
                }
                if(height>=terrain[x-1][y] && resultingArr[x-1][y] == false)
                {
                    resultingArr[x-1][y] = true;
                    locations.add(new GridLocation(x-1, y));
                }
                if(height>=terrain[x][y+1] && resultingArr[x][y+1] == false)
                {
                    resultingArr[x][y+1] = true;
                    locations.add(new GridLocation(x, y+1));
                }
            }
            else if(y==terrain[0].length-1 && (x!=0 || x!=terrain.length-1))
            {
                if(height>=terrain[x+1][y] && resultingArr[x+1][y] == false)
                {
                    resultingArr[x+1][y] = true;
                    locations.add(new GridLocation(x+1, y));
                }
                if(height>=terrain[x-1][y] && resultingArr[x-1][y] == false)
                {
                    resultingArr[x-1][y] = true;
                    locations.add(new GridLocation(x-1, y));
                }
                if(height>=terrain[x][y-1] && resultingArr[x][y-1] == false)
                {
                    resultingArr[x][y-1] = true;
                    locations.add(new GridLocation(x, y-1));
                }
            }
            else
            {
                if(height>=terrain[x+1][y] && resultingArr[x+1][y] == false)
                {
                    resultingArr[x+1][y] = true;
                    locations.add(new GridLocation(x+1, y));
                }
                if(height>=terrain[x-1][y] && resultingArr[x-1][y] == false)
                {
                    resultingArr[x-1][y] = true;
                    locations.add(new GridLocation(x-1, y));
                }
                if(height>=terrain[x][y+1] && resultingArr[x][y+1] == false)
                {
                    resultingArr[x][y+1] = true;
                    locations.add(new GridLocation(x, y+1));
                }
                if(height>=terrain[x][y-1] && resultingArr[x][y-1] == false)
                {
                    resultingArr[x][y-1] = true;
                    locations.add(new GridLocation(x, y-1));
                }
            }
            
        }
        
        return resultingArr; 
    }

    public boolean isFlooded(double height, GridLocation cell) {
        int row = cell.row;
        int col = cell.col;
        boolean[][] resultingArray = floodedRegionsIn(height);
        return resultingArray[row][col];
    }

    public double heightAboveWater(double height, GridLocation cell) {
        return terrain[cell.row][cell.col]-height;
    }

    public int totalVisibleLand(double height) {
        int counter = 0;
        boolean[][] resultingArray = floodedRegionsIn(height);
        for(int i=0; i<resultingArray.length; i++)
        {
            for(int j=0; j<resultingArray[i].length; j++)
            {
                if(resultingArray[i][j] == false)
                {
                    counter++;
                }
            }
        }
        return counter;
    } 

    public int landLost(double height, double newHeight) {
        
        int counter1 = 0;
        boolean[][] resultingArray1 = floodedRegionsIn(height);
        for(int i=0; i<resultingArray1.length; i++)
        {
            for(int j=0; j<resultingArray1[i].length; j++)
            {
                if(resultingArray1[i][j] == false)
                {
                    counter1++;
                }
            }
        }

        int counter2 = 0;
        boolean[][] resultingArray2 = floodedRegionsIn(newHeight);
        for(int x=0; x<resultingArray2.length; x++)
        {
            for(int y=0; y<resultingArray2[x].length; y++)
            {
                if(resultingArray2[x][y] == false)
                {
                    counter2++;
                }
            }
        }
        return counter1 - counter2;
    }

    public int numOfIslands(double height) {
        WeightedQuickUnionUF wuf= new WeightedQuickUnionUF(terrain.length, terrain[0].length);
        boolean[][] arr = floodedRegionsIn(height);
        for(int i=0; i<arr.length; i++)
        {
            for(int j=0; j<arr[i].length; j++)
            {
                if(arr[i][j] == false)
                {
                    GridLocation loc = new GridLocation(i, j);
                    try{
                        if(arr[i+1][j] == false)
                        {
                            GridLocation loc2 = new GridLocation(i+1, j);
                            wuf.union(loc, loc2);
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e){}
                    
                    try{
                        if(arr[i-1][j] == false)
                        {
                            GridLocation loc2 = new GridLocation(i-1, j);
                            wuf.union(loc, loc2);
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e){}

                    try{
                        if(arr[i][j+1] == false)
                        {
                            GridLocation loc2 = new GridLocation(i, j+1);
                            wuf.union(loc, loc2);
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e){}

                    try{
                        if(arr[i][j-1] == false)
                        {
                            GridLocation loc2 = new GridLocation(i, j-1);
                            wuf.union(loc, loc2);
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e){}
                    
                    try{
                        if(arr[i+1][j+1] == false)
                        {
                            GridLocation loc2 = new GridLocation(i+1, j+1);
                            wuf.union(loc, loc2);
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e){}

                    try{
                    if(arr[i-1][j-1] == false)
                        {
                            GridLocation loc2 = new GridLocation(i-1, j-1);
                            wuf.union(loc, loc2);
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e){}

                    try{
                        if(arr[i-1][j+1] == false)
                        {
                            GridLocation loc2 = new GridLocation(i-1, j+1);
                            wuf.union(loc, loc2);
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e){}

                    try{
                        if(arr[i+1][j-1] == false)
                        {
                            GridLocation loc2 = new GridLocation(i+1, j-1);
                            wuf.union(loc, loc2);
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e){}
                }
            }
        }
        ArrayList<GridLocation> wufLocations = new ArrayList<GridLocation>();
        for(int i=0; i<arr.length; i++)
        {
            for(int j=0; j<arr[i].length; j++)
            {
                if(arr[i][j] == false)
                {
                    GridLocation found = wuf.find(new GridLocation(i, j));
                    boolean isIn = false;
                    for(int k=0; k<wufLocations.size(); k++)
                    {
                        if(wufLocations.get(k) == found)
                        {isIn = true;}
                    }
                    if(!isIn)
                    {
                        wufLocations.add(found);
                    }
                }
            }
        }
        return wufLocations.size();
    }
}
