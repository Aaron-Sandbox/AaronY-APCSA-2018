package textExcel;
/*
 * @author Aaron Yu
 * @version March 2019
 * Class that contains a location in [row][col] form
 */

//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
	private int row;
	private int col;
	
    @Override
    public int getRow()
    {
        // TODO Auto-generated method stub
        return row;
    }

    @Override
    public int getCol()
    {
        // TODO Auto-generated method stub
        return col;
    }
    
    public SpreadsheetLocation(String cellName)
    {
        char col = cellName.charAt(0);
        String row = cellName.substring(1);
        
        this.row = Integer.parseInt(row)-1;
        if(Character.isLetter(col)) {
        	if(Character.isLowerCase(col)) {
        		col = Character.toUpperCase(col);
        	}
        	this.col = col - 65;
        }
        
    }

}
