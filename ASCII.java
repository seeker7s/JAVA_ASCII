import java.util.Arrays;
import java.util.Scanner;

public class ASCII {
	String shape;
	int shapeSize;
	String shapeLabel = "LU";
	int labelPos;
	Scanner user_input;
	String[] dailyShapes;
	
	public ASCII(){
		user_input = new Scanner(System.in);
		dailyShapes = new String[3];
		dailyShapes[0] = "diamond";
		dailyShapes[1] = "square";
		dailyShapes[2] = "triangle";
		
		//Welcome and Pick Shape
		boolean isFinished = false;
		while(!isFinished) {
			System.out.println("What shape would you like to make today?");
			shape = getShape(user_input, dailyShapes);
			System.out.println("You have chosen to make a "+shape+" today.\n");
			
			System.out.println("How tall would you like your "+shape+" to be?");
			shapeSize = getNumber(user_input);
			
			System.out.println("Thank you, User. Your "+shape+" will have a size of "+shapeSize+".\n");
			System.out.println("Please add a label to your "+shape+". (Empty labels will have 'LU' added.)");
			
			shapeLabel = getShapeLabel(user_input);
			
			System.out.println("What row do you what the label to appear in?");
			labelPos = getLabelPosition(user_input, shapeSize);
			
			drawShapes(shape, shapeSize, labelPos, shapeLabel);
			
			System.out.println("\nWould you like to make another shape today User? Y/N");
			isFinished = isUserFinished(user_input);			
		};
	}
	
	public static void main(String[] args){
		ASCII obj = new ASCII();

	}
	
	public static void drawShapes(String shape, int size, int position, String label) {
		if("diamond".equals(shape)) {
			drawDiamond(size, position, label);
		}else if("square".equals(shape)) {
			drawSquare(size, position, label);
		}else if("triangle".equals(shape)) {
			drawTriangle(size, position, label);
		}
	}
	
	public static void drawTriangle (int size, int atLine, String label) {
		int printSpace = size-1;
		int printStart = (atLine/2) - (label.length()/2);
		int pos = 0;
		
		for(int i=0; i<size; i++) {
			for(int k=i; k < printSpace; k++) {
				System.out.print(" ");
			}
			for(int j=0; j<=i; j++) {
				char letter = 'X';
				if(i == atLine-1) {
					if(j >= printStart && j < printStart+label.length()) {
						letter = label.charAt(pos);
						pos++;
					}
				}
				if(j==i) {
					System.out.print(letter);
				}else {
					System.out.print(letter+" ");
				}
			}
			System.out.println();
		}
	}
	
	public static void drawSquare (int size, int atLine, String label) {
		int stopAt = size-1;
		int printStart = (size/2) - (label.length()/2);
		int pos = 0;
		
		for(int i=0; i < size; i++) {
			for(int j=0; j < size; j++) {
				char letter = 'X';
				if(i == atLine-1) {
					if(j >= printStart && j < printStart+label.length()) {
						letter = label.charAt(pos);
						pos++;
					}
				}
				if(j==stopAt) {
					System.out.print(letter);
				}else {
					System.out.print(letter+" ");
				}
			}
			System.out.println("");
		}
	}
	
	public static void drawDiamond(int size, int atLine, String label) {
		int width=0;
		if ( (size & 1) == 0 ) {
			//Even Height
			width = (size/2);
		}else {
			//Odd Height
			width = (size/2)+1;
		}
		int printSpace = width-1;
		int printStart = atLine - (label.length()/2);
		int curLine = 1;
		int pos = 0;
		
		for(int i=1; i<=width; i++) {
			for(int k=1; k <= printSpace; k++) {
				System.out.print("  ");
			}
			printSpace--;
			
			for(int j=1; j<=2*i-1; j++) {
				char letter = 'X';
				if(curLine == atLine) {
					if(j >= printStart && j < printStart+label.length()) {
						letter = label.charAt(pos);
						pos++;
					}
				}
				System.out.print(letter+" ");
			}
			System.out.println();
			curLine++;
		}
		
		if ( (size & 1) == 0 ) {
			//Even Height
			printSpace= 0;
			width += 1;
		}else{
			//Odd Height
			printSpace = 1;
		}
		
		for(int i=1; i<=width-1; i++) {
			for(int k=1; k <= printSpace; k++) {
				System.out.print("  ");
			}
			printSpace++;
			
			for(int j=1; j<=2*(width-i)-1; j++) {
				char letter = 'X';
				if(curLine == atLine) {
					if(j >= printStart && j < printStart+label.length()) {
						letter = label.charAt(pos);
						pos++;
					}
				}

				System.out.print(letter+" ");
			}
			System.out.println();
		}
	}
	
	public static String getShape(Scanner sc, String[] shapes) {
		String input = "";
		String check = Arrays.toString(shapes).replace("[", "").replace("]", "");

		System.out.println("Today's daily shapes are: "+check+".");
		while("".equals(input)){
			if(sc.hasNext("diamond") 
					|| sc.hasNext("square")
					|| sc.hasNext("triangle")) {
				input = sc.nextLine();
			}else {
				System.out.println("User, that is not one of today's daily shapes.");
				sc.next();
			}
		}
			
		return input;
	}
	
	public static String getShapeLabel(Scanner sc) {
		String input = "";
		
		input = sc.nextLine();
		if(input.isEmpty()){
			input = "LU";
		}
			
		return input;
	}
	
	public static int getNumber(Scanner sc) {
		int input=0;
		
		while (input <= 0){
		    System.out.println("Please enter a positive whole number.");
		    while (!sc.hasNextInt()) {
		        System.out.println("User, that is not a number.");
		        sc.next();
		    }
		    input = sc.nextInt();
		    sc.nextLine();
		}

		return input;
	}
	
	public static int getLabelPosition(Scanner sc, int size) {
		int input = getNumber(sc);
		while (input > size){
		    System.out.println("User, that number is bigger than your shape.");
		    input = getNumber(sc);
		}
		return input;
	}
	
	public static boolean isUserFinished(Scanner sc) {
		String input = "";
		char choice;
		boolean check = false;
		boolean done = false;
		while(!check){
		    input = sc.next();
		    choice = input.toLowerCase().charAt(0);
		    switch(choice){
		    case 'y':
		        done = false;
		        check = true;
		        break;
		    case 'n':
		        done = true;
		        check = true;
		        System.out.println("Good bye, User.");
		        break;
		    default:
		        System.out.println("User, please enter Y or N.");
		        break;
		    }
		}
		return done;
	}
}
