import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MinRangeFinder implements RangeFinder   
{ 
	public ArrayList getRange(ArrayList<Integer []> inputRangeArrayList) throws WrongRangeException{
	     ArrayList<Integer []> rangeList=new ArrayList<Integer []>();
	 try
	 {
		 Collections.sort(inputRangeArrayList, compareRange);
		 checkValidRange(inputRangeArrayList); 
		 for(Integer[] intArr : inputRangeArrayList )
		 {
			 findMinRange(intArr ,rangeList);
		 }		
	 }
	 catch(WrongRangeException ex)
	 {
		 System.out.println(ex.getMessage());
		 throw ex;
	 }
	 catch(Exception exp)
	 {
		 exp.printStackTrace();
	 }
	 return rangeList;
  }
  
  private void findMinRange(Integer[] intArrObj , ArrayList<Integer[]> rangeList ){
	 boolean doadd=true;
	 boolean isOverLap=false;
	 for(int i=0; i<rangeList.size();i++) 
	 {
		if (Arrays.deepEquals(intArrObj, rangeList.get(i)))
		{
			doadd=false;
			break;
		} 
		isOverLap=isRangeOverlap(intArrObj,rangeList.get(i));
		if(isOverLap && inRange(intArrObj[0],rangeList.get(i)) && inRange(intArrObj[1],rangeList.get(i)) )
		{
				doadd=false;
				break;
		}
		else if(isOverLap && inRange(intArrObj[0],rangeList.get(i)) && !inRange(intArrObj[1],rangeList.get(i)))
		{
				rangeList.get(i)[1]=intArrObj[1];
				doadd=false;
				break;
		}
		else if(isOverLap && !inRange(intArrObj[0],rangeList.get(i)) && inRange(intArrObj[1],rangeList.get(i)))
		{
			rangeList.get(i)[0]=intArrObj[0];
			doadd=false;
			break;
		} 
		else if(isOverLap && !inRange(intArrObj[0],rangeList.get(i)) && !inRange(intArrObj[1],rangeList.get(i)))
		{
			rangeList.get(i)[0]=intArrObj[0];
			rangeList.get(i)[1]=intArrObj[1];
			doadd=false;
			break;
		} 
	 }
		if(doadd)
		{
			rangeList.add(intArrObj);
		} 
  }
  private boolean inRange(int rangeElement, Integer[] intArr){
	boolean isInRange=false;
	if(rangeElement>=intArr[0] && rangeElement<=intArr[1])
	{
		isInRange=true;
	}
	return isInRange;
  }
  private boolean isRangeOverlap(Integer[] rangeOne, Integer[] rangeTwo){
	  boolean isRangeOverlap=false;
	if(inRange(rangeOne[0],rangeTwo) || inRange(rangeOne[1],rangeTwo) || inRange(rangeTwo[0],rangeOne) || inRange(rangeTwo[1],rangeOne))
	{
		isRangeOverlap= true;
	}
	return isRangeOverlap;
  }
  
  private void checkValidRange(ArrayList<Integer[]> rangeList) throws WrongRangeException{
	for(Integer[] intr : rangeList )
	 {
		 if(intr[1]< intr[0])
		 {
			throw new WrongRangeException(" Wrong Range"); 
		 }
	 } 
	 
  }
  public  Comparator<Integer[]> compareRange = new Comparator<Integer[]>() {

	public int compare(Integer[] arr1, Integer[] arr2) {

	   int firstObjNo = arr1[0];
	   int secondObjNo = arr2[0];

	   return firstObjNo-secondObjNo;

   }};
 }